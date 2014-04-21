/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.BatailleNavale;
import model.Coordinate;
import model.OrdreTir;
import model.ScoreException;
import model.State;
import model.StateCase;
import static model.StateCase.DESTROYED_SHIP;
import static model.StateCase.FLOTTE_DETRUITE;
import static model.StateCase.HIT;
import static model.StateCase.MISS;
import static model.StateCase.UNKNOWN;
import model.ship.Ship;
import model.ship.Ship.Etat;

/**
 * 
 * 
 * @todo thomas : corriger bug de croix qui se place pas si le bateau est detruit
 * corriger bug des boutons pas disable alors qu'ils ont une croix des fois
 * @author nikolai
 */
public class JPanelJouer extends JPanel implements Observer {

    public static final String id = "jpaneljouer";

    private JPanel grilleEnnemi;
    private JPanel grilleFlotte;
    private final JPanelWizard wizard;
    private final BatailleNavale model;
    private JList list;
    private final ShipsListModel listModel;
    private final List<JButtonFire> listTir;
    private final int SHIP_SELECTED = 0;
    private final int NOTHING_SELECTED = 1;
    private int state = NOTHING_SELECTED;
    private Ship selectedShip;

    class JButtonFire extends JButton implements Observer {

        private final Coordinate c;
        private final BatailleNavale model2;

        public JButtonFire(final BatailleNavale model, final Coordinate c, final JPanelWizard wizard) {
            super("?");
            model2 = model;
            this.c = c;
            model2.addObserver(this);

            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    try {
                        Object[] os = {"Rejouer", "Voir les scores", "Revenir à l\'accueil", "Quitter"};
                        
                        switch (state) {
                            case NOTHING_SELECTED:
                                break;
                            case SHIP_SELECTED:
                                String s = " ";
                                int launcherid = model2.getJ1().getFlotte().getVaisseaux().indexOf(selectedShip);
                                StateCase res = StateCase.ERROR;
                                try {
                                    res = model2.fire(new OrdreTir(c, launcherid));
                                } catch (Exception ex) {
                                    Logger.getLogger(JPanelJouer.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                switch (model2.getState()) {
                                    case MATCH_NUL:
                                        model2.updateScore();
                                        s = (String) JOptionPane.showInputDialog(
                                                GUI.getInstance(),
                                                "Match nul! Dommage...\nQue voulez-vous faire?",
                                                "Fin de partie",
                                                JOptionPane.INFORMATION_MESSAGE,
                                                null,
                                                os, "Rejouer");
                                        break;
                                    case WINJ1:
                                        model2.updateScore();
                                        s = (String) JOptionPane.showInputDialog(
                                                GUI.getInstance(),
                                                "Vous avez gagné! Bravo!\nQue voulez-vous faire?",
                                                "Fin de partie",
                                                JOptionPane.INFORMATION_MESSAGE,
                                                null,
                                                os, "Voir les scores");
                                        break;
                                    case WINJ2:
                                        model2.updateScore();
                                        s = (String) JOptionPane.showInputDialog(
                                                GUI.getInstance(),
                                                "Vous avez perdu!\nQue voulez-vous faire?",
                                                "Fin de partie",
                                                JOptionPane.INFORMATION_MESSAGE,
                                                null,
                                                os, "Rejouer");
                                        break;
                                    default:
                                        if (res == MISS) {
                                            int i = 0;
                                            boolean end = false;
                                            boolean turnJ2 = false;
                                            if(model2.getJ1().horsDePortee(model2.getJ2().getFlotte())){
                                                turnJ2 = true;
                                            }
                                            else{
                                                for (JButtonFire jbf : listTir) {
                                                    if((jbf.isEnabled())){
                                                        turnJ2 = false;
                                                        break;
                                                    }
                                                    else
                                                        turnJ2 = true;                                                    
                                                 }
                                            }
                                            // on fait tirer le bot
                                            while((turnJ2 ||(i==0))&&(end == false)){
                                                
                                                try {
                                                    model2.switchTurn();
                                                    StateCase resBot = model2.fire(OrdreTir.NO_ORDER);
                                                    while (resBot == HIT || resBot == DESTROYED_SHIP) {
                                                        resBot = model2.fire(OrdreTir.NO_ORDER);
                                                        if (resBot == DESTROYED_SHIP) {
                                                            // on met à jour la list
                                                            listModel.update();
                                                            list.updateUI();
                                                            list.clearSelection();
                                                            for (JButtonFire jbf : listTir){
                                                                jbf.setEnabled(false);
                                                            }
                                                        }
                                                    }
                                                    System.out.println("model.getState() : " + model.getState());
                                                    switch (model.getState()) {
                                                        case WINJ2:
                                                            model2.updateScore();
                                                            s = (String) JOptionPane.showInputDialog(
                                                                    GUI.getInstance(),
                                                                    "Vous avez perdu!\nQue voulez-vous faire?",
                                                                    "Fin de partie",
                                                                    JOptionPane.INFORMATION_MESSAGE,
                                                                    null,
                                                                    os, "Rejouer");// valeur initiale
                                                            end = true;
                                                            break;
                                                        case MATCH_NUL:
                                                            model2.updateScore();
                                                            s = (String) JOptionPane.showInputDialog(
                                                                    GUI.getInstance(),
                                                                    "Match nul! Dommage...\nQue voulez-vous faire?",
                                                                    "Fin de partie",
                                                                    JOptionPane.INFORMATION_MESSAGE,
                                                                    null,
                                                                    os, "Rejouer");
                                                            end = true;
                                                            break;
                                                        default:
                                                            i++;
                                                            model2.switchTurn();
                                                            break;
                                                    }
                                                } catch (Exception ex) {
                                                    Logger.getLogger(JPanelJouer.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                            }
                                        }
                                }
                                
                                if (s != " ") {
                                    if(s!=null){
                                        wizard.clean(model2);
                                        if (s.equals("Rejouer")) {
                                            wizard.show(JPanelCreer.id);
                                        } else if (s.equals("Voir les scores")) {
                                            wizard.show(JPanelScore.id);
                                        } else if (s.equals("Revenir à l\'accueil")){
                                            wizard.clean(model2);
                                            wizard.show(JPanelAcceuil.id);
                                        } else {
                                            System.exit(0);
                                        }
                                    }
                                    else{
                                        wizard.clean(model2);
                                        wizard.show(JPanelAcceuil.id);
                                    }
                                }
                        }
                    } catch (ScoreException ex) {
                        Logger.getLogger(JPanelJouer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        }

        public Coordinate getC() {
            return this.c;
        }

        @Override
        public void update(Observable o, Object o1) {
            StateCase etat = model2.getJ1().getMap().getState(c);
            switch (etat) {
                case UNKNOWN:
                    setText("?"); // ? pour dire "pas encore attaqué"
                    break;
                case HIT:
                case DESTROYED_SHIP:
                case FLOTTE_DETRUITE:
                    setText("X"); // Croix pour dire "touché"
                    setEnabled(false); //Desactivation pour ne pas tirer au même endroit
                    break;
                case MISS:
                    setText(""); // " " pour dire "raté"  
                    setEnabled(false); //Desactivation pour ne pas tirer au même endroit
                    break;
                case UNREACHABLE:
                    // nothing to do
                    break;
            }

            if ((model2.getState() == State.WINJ1) || (model2.getState() == State.WINJ2) || (model2.getState() == State.MATCH_NUL)) {
                setEnabled(false);
            }
        }
    }

    public JPanelJouer(final BatailleNavale model, JPanelWizard wizard) {
        super(new BorderLayout());
        model.addObserver(this);
        this.model = model;
        this.wizard = wizard;
        listTir = new ArrayList<>();
        listModel = new ShipsListModel(model);
    }

    /**
     * enlève toute chose spécifique à une partie en particulier sur ce panel
     */
    public void clear() {
        removeAll(); // what else ? Nespresso
    }

    /**
     * initialize le panel
     */
    public void initialize() {
        add(new JLabel(id));
        JPanel center = new JPanel(new GridLayout(1, 2));
        grilleEnnemi = new JPanel(new GridLayout(model.getLargeurGrille(), model.getLongeurGrille()));
        grilleFlotte = new JPanel(new GridLayout(model.getLargeurGrille(), model.getLongeurGrille()));
        for (int i = 0; i < model.getLargeurGrille(); i++) {
            for (int j = 0; j < model.getLongeurGrille(); j++) {
                JButtonFire jbf = new JButtonFire(model, new Coordinate(i, j), wizard);
                jbf.setEnabled(false);
                grilleEnnemi.add(jbf);
                listTir.add(jbf);
            }
        }
        for (int i = 0; i < model.getLongeurGrille() * model.getLargeurGrille(); i++) {
            JButton j = new JButton("b");
            j.setEnabled(false);
            grilleFlotte.add(j);

        }
        center.add(grilleEnnemi);
        grilleEnnemi.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Battlefield", TitledBorder.LEFT, TitledBorder.TOP, new Font(Font.SERIF, Font.ITALIC, 16), Color.GRAY));
        center.add(grilleFlotte);
        add(center, BorderLayout.CENTER);
        grilleFlotte.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Etats de vos bateaux", TitledBorder.LEFT, TitledBorder.TOP, new Font(Font.SERIF, Font.ITALIC, 16), Color.GRAY));
        list = new JList(listModel);
        list.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Flotte", TitledBorder.LEFT, TitledBorder.TOP, new Font(Font.SERIF, Font.ITALIC, 16), Color.GRAY));

        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(-1);
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 80));
        list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedShip = (Ship) list.getSelectedValue();
                if (selectedShip != null) {
                    state = SHIP_SELECTED;
                    for (JButtonFire jbf : listTir) {
                        if (selectedShip.estAporteeDeTir(jbf.getC())) {
                            jbf.setEnabled(true);
                        } else {
                            jbf.setEnabled(false);
                        }
                        StateCase etat = model.getJ1().getMap().getState(jbf.getC());
                        if ((etat == HIT) || (etat == MISS)) {
                            jbf.setEnabled(false);
                        }
                    }
                }else{
                    state = NOTHING_SELECTED;
                }
            }
        });
        add(list, BorderLayout.EAST);
//        updateGrilleFlotte();
        updateGrilleFlotte();

    }

    private void updateGrilleEnnemi() {
        if (selectedShip != null) {
            state = SHIP_SELECTED;
            for (JButtonFire jbf : listTir) {
                if (selectedShip.estAporteeDeTir(jbf.getC())) {
                    jbf.setEnabled(true);
                } else {
                    jbf.setEnabled(false);
                }
                StateCase etat = model.getJ1().getMap().getState(jbf.getC());
                if ((etat == HIT) || (etat == MISS)) {
                    jbf.setEnabled(false);
                }
            }
        }else{
            state = NOTHING_SELECTED;
        }
    }
    /**
     * update la grille de votre flotte
     */
    private void updateGrilleFlotte() {
        for (Ship s : model.getJ1().getFlotte().getVaisseaux()) {
            for (Etat e : s.getEtats()) {
                JButton b = new JButton();
                b.setEnabled(false);
                String t = "";
                switch (e.getEtat()) {
                    case Ship.DAMAGED:
                        t = "X";
                        break;
                    case Ship.SAFE:
                        t = "";
                        break;
                }
                b.setText(t);
                b.setBackground(s.getRepresentationGraphique());
                int pos = e.getC().y + model.getLargeurGrille() * e.getC().x;
                grilleFlotte.remove(pos);
                grilleFlotte.add(b, pos);
            }
        }
        for(Ship s:model.getJ1().getFlotte().getVaisseauxCoules()){
            for(Etat e : s.getEtats()){
                JButton b = new JButton();
                b.setEnabled(false);
                String t = "X";
                b.setText(t);
                b.setBackground(s.getRepresentationGraphique());
                int pos = e.getC().y + model.getLargeurGrille() * e.getC().x;
                grilleFlotte.remove(pos);
                grilleFlotte.add(b, pos);
            }
        }
    }
    
    
    @Override
    public void update(Observable o, Object o1) {
        updateGrilleFlotte();
        updateGrilleEnnemi();
    }
}
