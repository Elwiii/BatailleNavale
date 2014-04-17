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
import javax.swing.DefaultListModel;
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
import model.State;
import model.StateCase;
import static model.StateCase.HIT;
import static model.StateCase.MISS;
import static model.StateCase.UNKNOWN;
import model.ship.Ship;
import model.ship.Ship.Etat;

/**
 * @todo thomas
 * @author nikolai
 */
public class JPanelJouer extends JPanel implements Observer {

    public static final String id = "jpaneljouer";

    private JPanel grilleEnnemi;
    private JPanel grilleFlotte;
    private final BatailleNavale model;
    private JList list;
    DefaultListModel<String> listModel;
    private List<JButtonFire> listTir;
    private final int SHIP_SELECTED = 0;
    private final int HEAD_SELECTED = 1;
    private final int TAIL_SELECTED = 2;
    private final int NOTHING_SELECTED = 3;
    private int state = NOTHING_SELECTED;
    private Ship selectedShip;

    public class JButtonFire extends JButton implements Observer {

        private final Coordinate c;
        private final BatailleNavale model;
        private int touche;

        public JButtonFire(final BatailleNavale model, final Coordinate c) {
            super("?");
            this.model = model;
            this.c = c;
            model.addObserver(this);
            addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    StateCase res;
                    switch (state) {
                        case NOTHING_SELECTED:
                            break;
                        case SHIP_SELECTED:
                            try {
                                if ((model.getState() != State.WINJ2)&&(model.getState() != State.WINJ1)) {
                                    int launcherid = model.getJ1().getFlotte().getVaisseaux().indexOf(selectedShip);
                                    res = model.fire(new OrdreTir(c, launcherid));
                                    System.out.println("RES J1 = "+res);
                                    /* Pas sur du tout*/
                                    model.update();
                                    if(model.getState()==State.MATCH_NUL){
                                        JOptionPane.showMessageDialog(GUI.getInstance(), "Match nul! Dommage..");
                                    }
                                    if (!(model.getState() == State.WINJ1)) {
                                        if (res == MISS /* 2 */) {
                                            System.out.println("BOT");
                                            model.switchTurn();
                                            res = model.fire(OrdreTir.NO_ORDER/*null*/);
                                            System.out.println("RES J2 = "+res);
                                            while (res == HIT) {
                                                System.out.println("RES J22 = "+res);
                                                res = model.fire(OrdreTir.NO_ORDER/*null*/);
                                                model.update();
                                                updateList();
                                                
                                            }
                                            if (!(model.getState() == State.WINJ2)) {
                                                model.switchTurn();
                                            }
                                            else
                                                JOptionPane.showMessageDialog(GUI.getInstance(), "Vous avez perdu! Retentez votre chance!");
                                        }
                                    }
                                    else
                                        JOptionPane.showMessageDialog(GUI.getInstance(), "Vous avez gagné! Bravo!");
                                }

                            } catch (Exception ex) {
                                Logger.getLogger(JPanelJouer.class.getName()).log(Level.SEVERE, null, ex);
                            }
                    }
                    /* Toujours pas sur */
                    model.update();
                    System.out.println("" + c);
                }
            });
        }

        ;
        
        public Coordinate getC() {
            return this.c;
        }

        @Override
        public void update(Observable o, Object o1) {
            //System.out.println("Bateau : "+model.getJ2().getFlotte().getVaisseaux().get(0));
            StateCase etat = model.getJ1().getMap().getState(c);
            if (etat == UNKNOWN) {
                this.setText("?"); // ? pour dire "pas encore attaqué"
            } /* Cas d'une case touchée */ 
            else if (etat == HIT) {
                this.setText("X"); // Croix pour dire "touché"
                this.setEnabled(false); //Desactivation pour ne pas tirer au même endroit
            } else if (etat == MISS) {
                this.setText(""); // " " pour dire "raté"  
                this.setEnabled(false); //Desactivation pour ne pas tirer au même endroit
            }
            if((model.getState()==State.WINJ1 )|| (model.getState()==State.WINJ2)||(model.getState() == State.MATCH_NUL)){
                this.setEnabled(false);
            }
        }

    }

    public JPanelJouer(final BatailleNavale model) {
        super(new BorderLayout());
        model.addObserver(this);
        this.model = model;
        listTir = new ArrayList<>();
    }

    /**
     * enlève toute chose spécifique à une partie en particulier sur ce panel
     */
    public void clear() {
        removeAll(); // what else ? Nespresso
    }

    public void initialize() {
        add(new JLabel(id));
        JPanel center = new JPanel(new GridLayout(1, 2));
        grilleEnnemi = new JPanel(new GridLayout(model.getLargeurGrille(), model.getLongeurGrille()));
        grilleFlotte = new JPanel(new GridLayout(model.getLargeurGrille(), model.getLongeurGrille()));
        for (int i = 0; i < model.getLargeurGrille(); i++) {
            for (int j = 0; j < model.getLongeurGrille(); j++) {
                JButtonFire jbf = new JButtonFire(model, new Coordinate(i, j));
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

        Ship[] tabShip = new Ship[model.getJ1().getFlotte().getVaisseaux().size()];
        int i = 0;
        for (Ship s : model.getJ1().getFlotte().getVaisseaux()) {
            tabShip[i] = s;
            i++;
        }
        /*final JList*/ list = new JList(tabShip); //data has type Object[]
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
                state = SHIP_SELECTED;
                for (JButtonFire jbf : listTir) {
                    if (selectedShip.estAporteeDeTir(jbf.getC())) {
                        jbf.setEnabled(true);
                    } else {
                        jbf.setEnabled(false);
                    }
                    StateCase etat = model.getJ1().getMap().getState(jbf.getC());
                    if((etat == HIT)||(etat == MISS)){
                        jbf.setEnabled(false);
                    }

                }
            }
        });

        add(list, BorderLayout.EAST);

        updateGrilleEnnemi();
        updateGrilleFlotte();
        //updateList();

    }

    private void updateGrilleEnnemi() {
//        System.out.println("UPDATE GRILLE ENNEMI");
//        for(JButtonFire jbf : listTir){
//            for(Ship s : model.getJ1().getFlotte().getVaisseaux())
//            {
//                if(s.estAporteeDeTir(jbf.getC())){
//                    jbf.setEnabled(true);
//                    System.out.println("OUI");
//                }
//                else{
//                    jbf.setEnabled(false);
//                }
//            }
//        }
    }

    private void updateGrilleFlotte() {
//        grilleFlotte.removeAll();
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
//                System.out.println("x : "+e.getC().x);
//                System.out.println("y : "+e.getC().y);
                int pos = e.getC().y + model.getLargeurGrille() * e.getC().x;
//                System.out.println("pos : "+pos);
                grilleFlotte.remove(pos);
                grilleFlotte.add(b, pos);
//                updateList();
            }
        }

    }

    public void updateList() {
        Ship[] tabShip = new Ship[model.getJ1().getFlotte().getVaisseaux().size()];
        int i = 0;
        for (Ship s : model.getJ1().getFlotte().getVaisseaux()) {
            tabShip[i] = s;
            i++;
        }
        list = new JList(tabShip);
//        list.removeAll();
//        list.setListData(tabShip);

    }

    @Override
    public void update(Observable o, Object o1) {
        System.out.println("UPDATE JPANELJOUER");
        updateGrilleFlotte();
        
//        updateGrilleEnnemi();
    }
}
