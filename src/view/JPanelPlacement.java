/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.BatailleNavale;
import model.Coordinate;
import model.player.Bot;
import model.player.Bot.structPlacement;
import model.ship.Epoque;
import model.ship.Ship;
import model.ship.Ship.Etat;
import model.ship.TypeShip;

/**
 *
 * @author nikolai
 */
public class JPanelPlacement extends JPanel implements Observer {

    public static final String id = "jpanelplacement";
    private JPanel grille;
    private final BatailleNavale model;
    private JList list;
    private TypeShip typeShip;
    private int headColonne;
    private int headLigne;
    private int tailColonne;
    private int tailLigne;
    private final int SHIP_SELECTED = 0;
    private final int HEAD_SELECTED = 1;
    private final int TAIL_SELECTED = 2;
    private final int NOTHING_SELECTED = 3;
    private final JPanel east;
    private int state = NOTHING_SELECTED;
    private JButtonPlacementBateau[][] grilleButton;
    private final JButton annulerHead;

    private List<JButtonPlacementBateau> disabledJButton;

    private class JButtonPlacementBateau extends JButton {

        final BatailleNavale model;
        final int ligne;
        final int colonne;

        public JButtonPlacementBateau(final int ligne, final int colonne, final BatailleNavale model) {
            super();
            this.model = model;
            this.ligne = ligne;
            this.colonne = colonne;
            this.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (state) {
                        case NOTHING_SELECTED:
                            JOptionPane.showMessageDialog(GUI.getInstance(), "Veuillez choisir le type de bateau que vous voulez placer, dans la liste");

                            break;
                        case SHIP_SELECTED:
                            headColonne = colonne;
                            headLigne = ligne;
                            grilleButton[headLigne][headColonne].setEnabled(false);
                            grilleButton[headLigne][headColonne].setText("head");
                            // afficher les buttons higlight
                            List<JButtonPlacementBateau> tailsImpossibles = tailsImpossibles();

                            for (JButtonPlacementBateau jbp : tailsImpossibles) {
                                jbp.setEnabled(false);
                                disabledJButton.add(jbp);
                            }

                            //@todo un truc moin bourrin pour savoir si on peut placer la tete ou pas, le mieux serait de faire un algo qui nous donne les heads possibles
                            boolean queuePossible = false;
                            int j = 0;
                            int i = 0;
                            while (!queuePossible && i < grilleButton.length) {
                                while (!queuePossible & j < grilleButton[0].length) {
                                    System.out.println("grilleButton[" + i + "][" + j + "].isEnabled()" + grilleButton[i][j].isEnabled());
                                    queuePossible = queuePossible || grilleButton[i][j].isEnabled();
                                    j++;
                                }
                                j = 0;
                                i++;
                            }

                            if (queuePossible) {
                                state = HEAD_SELECTED;
                                annulerHead.setEnabled(true);
                            } else {
                                JOptionPane.showMessageDialog(GUI.getInstance(), "Impossible de placer la tete de ce bateau ici");
                                // on enable les buttons disabled
                                for (JButtonPlacementBateau jbp : disabledJButton) {
                                    jbp.setEnabled(true);
                                }
                                disabledJButton.clear();
                                grilleButton[headLigne][headColonne].setText("");
                                grilleButton[headLigne][headColonne].setEnabled(true);
                                annulerHead.setEnabled(false);
//                                list.clearSelection();

                            }

                            break;
                        case HEAD_SELECTED:
                            tailColonne = colonne;
                            tailLigne = ligne;
                            state = TAIL_SELECTED;
                            // je place le bateau
                            int incr_c = 0;
                            int incr_l = 0;

                            if (headColonne > tailColonne) {
                                incr_c = 1;
                                if (headLigne > tailLigne) {
                                    incr_l = 1;
                                } else {
                                    incr_l = -1;
                                }
                            } else {
                                incr_c = -1;
                                if (headLigne > tailLigne) {
                                    incr_l = 1;
                                } else {
                                    incr_l = -1;
                                }
                            }
                            int max_c = Math.abs(tailColonne - headColonne);// == 0 ? 1 : Math.abs(tailColonne - headColonne);
                            int max_l = Math.abs(tailLigne - headLigne);// == 0 ? 1 : Math.abs(tailLigne - headLigne);
                            if (Math.abs(tailColonne - headColonne) == 0 || Math.abs(tailLigne - headLigne) == 0) {
                                for (int c = 0; c <= max_c; c++) {
                                    for (int l = 0; l <= max_l; l++) {
                                        System.out.println("(l,c) : " + l + "," + c);
                                        JButtonPlacementBateau jbp = grilleButton[tailLigne + incr_l * l][tailColonne + incr_c * c];
                                        jbp.setEnabled(false);
                                        jbp.setBackground(typeShip.getRepresentationGraphique());
                                        disabledJButton.remove(jbp);
                                    }
                                }
                            } else {
                                for (int c = 0; c <= max_c; c++) {
//                                    System.out.println("(l,c) : " + l + "," + c);
                                    JButtonPlacementBateau jbp = grilleButton[tailLigne + incr_l * c][tailColonne + incr_c * c];
                                    jbp.setEnabled(false);
                                    jbp.setBackground(typeShip.getRepresentationGraphique());
                                    disabledJButton.remove(jbp);
                                }
                            }
                            try {
                                // on ajoute le bateau à la flotte
                                model.addShip(typeShip, new Coordinate(tailLigne, tailColonne), new Coordinate(headLigne, headColonne));
                                //model.addShip2(typeShip, new Coordinate(tailLigne, tailColonne), new Coordinate(headLigne, headColonne));
                            } catch (Exception ex) {
                                Logger.getLogger(JPanelPlacement.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            // on enable les buttons disabled
                            for (JButtonPlacementBateau jbp : disabledJButton) {
                                jbp.setEnabled(true);
                            }
                            disabledJButton.clear();

                            annulerHead.setEnabled(false);
                            list.clearSelection();
                            state = NOTHING_SELECTED;
                            break;
                        case TAIL_SELECTED:

                            break;
                        default:
                        //throw something
                    }
                }
            });
        }

    }

    /**
     * renvoi tous les boutons qui ne sont pas cliquables afin de determiner la
     * queue du bateau (sachant une tête déjà choisie)
     *
     * @return
     */
    private List<JButtonPlacementBateau> tailsImpossibles() {
        List<JButtonPlacementBateau> list = new ArrayList<>();
        List<Ship> listShip = model.getJ1().getFlotte().getVaisseaux();
        boolean ajout = true;
        disabledJButton = new ArrayList<>();
        int l_max, l_min;
        int c_max, c_min;
//        list.add(grilleButton[0][0]);
//        list.add(grilleButton[1][1]);
        for (int i = 0; i < grilleButton.length; i++) {
            for (int j = 0; j < grilleButton[i].length; j++) {
                ajout = true;
                /* 1ere condition : bateau en colonne */
                /* 2e condition : bateau en ligne */
                /* 3e condition : bateau en diagonale */
                if (!(((Math.abs(headColonne - j) == this.typeShip.getPuissance() - 1) && (i == headLigne))
                        || ((j == headColonne) && (Math.abs(headLigne - i) == this.typeShip.getPuissance() - 1))
                        || ((Math.abs(headColonne - j) == this.typeShip.getPuissance() - 1) && (Math.abs(headLigne - i) == this.typeShip.getPuissance() - 1)))) {
                    list.add(grilleButton[i][j]);
                }
                /* cas bateau vertical/horizontal, teste des chevauchements possibles */
                //On compare les coord de tous les bateaux du joueur
                for (Ship s : model.getJ1().getFlotte().getVaisseaux()) {
                    for (Etat e : s.getEtats()) {
                        //Avec toutes les coordonées des placements possibles du bateau
                        if (i > headLigne) {
                            l_max = i;
                            l_min = headLigne;
                        } else {
                            l_max = headLigne;
                            l_min = i;
                        }
                        if (j > headColonne) {
                            c_max = j;
                            c_min = headColonne;
                        } else {
                            c_max = headColonne;
                            c_min = j;
                        }
                        for (int k = l_min; k <= l_max; k++) {
                            for (int l = c_min; l <= c_max; l++) {
                                /* cas des chevauchements */
                                if ((e.getC().x == k) && (e.getC().y == l)) {
                                    list.add(grilleButton[i][j]);
                                    System.out.println("Chevauchement");
                                    //ajout = false;
                                } /* cas bateau en diagonale, teste des croisements possibles */

                                if ((headColonne != j) && (headLigne != i)) {
                                    //si case voisine du dessous occupé par un bateau
                                    if ((e.getC().x == k + 1) && (e.getC().y) == l) {
                                        for (Etat ep : s.getEtats()) {
                                            if (!(ep.equals(e))) {
                                                //on teste la case voisine de droite
                                                if ((ep.getC().x == k) && (ep.getC().y == l + 1)) {
                                                    list.add(grilleButton[i][j]);
                                                    ajout = false;
                                                } //on teste la case voisine de gauche
                                                else if ((ep.getC().x == k) && (ep.getC().y == l - 1)) {
                                                    list.add(grilleButton[i][j]);
                                                    ajout = false;
                                                }
                                            }
                                        }
                                    }
//                                    //si case voisine de droite occupée par un bateau
//                                    if ((e.getC().x == k) && (e.getC().y) == l + 1) {
//                                        for (Etat ep : s.getEtats()) {
//                                            if(!(e.equals(ep))){
//                                                //on teste case voisine du dessous
//                                                if ((ep.getC().x == k + 1) && (ep.getC().y == l)) {
//                                                    list.add(grilleButton[i][j]);
//                                                }
//                                            }
//                                        }
//                                    } //si case voisine de gauche occupée par un bateau
//                                    if ((e.getC().x == k - 1) && (e.getC().y) == l) {
//                                        for (Etat ep : s.getEtats()) {
//                                            if(!(e.equals(ep))){
//                                                //on teste case voisine du dessous
//                                                if ((ep.getC().x == k) && (ep.getC().y == l + 1)) {
//                                                    list.add(grilleButton[i][j]);
//                                                }
//                                            }
//                                        }
//                                    }
                                }
                            }

                        }

//                        for (int k = headLigne; k <= i; k++) {
//                            for (int l = headColonne; l <= j; l++) {
//                                if ((e.getC().x == k) && (e.getC().y == l)) {
//                                    list.add(grilleButton[i][j]);
//                                    System.out.println("Chevauchement");
//                                    //ajout = false;
//                                } /* cas bateau en diagonale, teste des croisements possibles */
//
//                                if ((headColonne != j) && (headLigne != i)) {
//                                    //si case voisine du dessous occupé par un bateau
//                                    if ((e.getC().x == k + 1) && (e.getC().y) == l) {
//                                        for (Etat ep : s.getEtats()) {
//                                            //on teste la case voisine de droite
//                                            if ((ep.getC().x == k) && (ep.getC().y == l + 1)) {
//                                                list.add(grilleButton[i][j]);
//                                                ajout = false;
//                                            } //on teste la case voisine de gauche
//                                            else if ((ep.getC().x == k) && (ep.getC().y == l - 1)) {
//                                                list.add(grilleButton[i][j]);
//                                                ajout = false;
//                                            }
//                                        }
//                                    } //si case voisine de droite occupée par un bateau
//                                    if ((e.getC().x == k) && (e.getC().y) == l + 1) {
//                                        for (Etat ep : s.getEtats()) {
//                                            //on teste case voisine du dessous
//                                            if ((ep.getC().x == k + 1) && (ep.getC().y == l)) {
//                                                list.add(grilleButton[i][j]);
//                                            }
//                                        }
//                                    } //si case voisine de gauche occupée par un bateau
//                                    if ((e.getC().x == k - 1) && (e.getC().y) == l) {
//                                        for (Etat ep : s.getEtats()) {
//                                            //on teste case voisine du dessous
//                                            if ((ep.getC().x == k) && (ep.getC().y == l + 1)) {
//                                                list.add(grilleButton[i][j]);
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        }
                    }

                }
            }
        }
        return list; // @todo thomas
    }

    public JPanelPlacement(final BatailleNavale model, final JPanelWizard wizard) {
        super(new BorderLayout());
        this.model = model;
        east = new JPanel(new BorderLayout());
        model.addObserver(this);
        JPanel south = new JPanel(new GridLayout(1, 2));
        JButton backToCreer = new JButton("retour");
        backToCreer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                state = NOTHING_SELECTED;
                wizard.show(JPanelCreer.id);
            }
        });
        south.add(backToCreer);

        JButton valider = new JButton("valider");
        valider.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    model.switchTurn();
                    System.out.println("BIT place bateau");
                    List<structPlacement> listStruct = new ArrayList<>();
                    listStruct = model.getCurrentPlayer().placerBateau(model.getOtherPlayer().getFlotte());
                    while(listStruct==null){
                        System.out.println("en cours...");
                        model.getCurrentPlayer().placerBateau(model.getOtherPlayer().getFlotte());
                    }
                    for(structPlacement sp : listStruct){
                        model.addShip(sp.getType(), sp.getCHead(),sp.getCQueue());
                    }
                    System.out.println("Placement Bot effectué!");
                    model.switchTurn();
                } catch (Exception ex) {
                    Logger.getLogger(JPanelPlacement.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                wizard.getJpanelJouer().initialize();
                wizard.show(JPanelJouer.id);
            }
        });
        south.add(valider);
        add(south, BorderLayout.SOUTH);

        annulerHead = new JButton("annuler");
        annulerHead.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                for (JButtonPlacementBateau jbp : disabledJButton) {
                    jbp.setEnabled(true);
                }
                disabledJButton.clear();
                grilleButton[headLigne][headColonne].setText("");
                state = SHIP_SELECTED;
                annulerHead.setEnabled(false);
            }
        });
        annulerHead.setEnabled(false);
        east.add(annulerHead, BorderLayout.SOUTH);

        add(east, BorderLayout.EAST);
    }

    public void constructList(Epoque epoque) {
        if (list != null) {
            east.remove(list);
        }
        list = new JList(TypeShip.get(epoque));
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(-1);
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 80));
        list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                typeShip = (TypeShip) list.getSelectedValue();
                state = SHIP_SELECTED;
                System.out.println("type : " + typeShip);
            }
        });
        list.clearSelection();
        east.add(/*new JScrollPane(*/list/*)*/, BorderLayout.CENTER);
    }

    public void constuctGrille(int width, int height) {
        if (grille != null) {
            remove(grille);
        }
        grille = new JPanel(new GridLayout(height, width));
        grilleButton = new JButtonPlacementBateau[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                JButtonPlacementBateau jpb = new JButtonPlacementBateau(i, j, model);
                grille.add(jpb);
                grilleButton[i][j] = jpb;
            }
        }
        add(grille, BorderLayout.CENTER);
    }

    @Override
    public void update(Observable o, Object o1) {
        System.out.println("UPDATE JPANELPLACEMENT");

    }
}
