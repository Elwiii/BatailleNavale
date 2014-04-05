/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.BatailleNavale;
import model.Coordinate;
import model.ship.Epoque;
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
    private final JButton add;
    private int state = NOTHING_SELECTED;
    private JButtonPlacementBateau[][] grilleButton;

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
                            break;
                        case SHIP_SELECTED:
                            headColonne = colonne;
                            headLigne = ligne;
                            grilleButton[headLigne][headColonne].setEnabled(false);
                            grilleButton[headLigne][headColonne].setText("head");
                            // afficher les buttons higlight
                            for (JButtonPlacementBateau jbp : tailsImpossibles()) {
                                jbp.setEnabled(false);
                            }
                            state = HEAD_SELECTED;
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
                                        grilleButton[tailLigne + incr_l * l][tailColonne + incr_c * c].setEnabled(false);
                                        grilleButton[tailLigne + incr_l * l][tailColonne + incr_c * c].setBackground(typeShip.getRepresentationGraphique());
                                    }
                                }
                            } else {
                                for (int c = 0; c <= max_c; c++) {
//                                    System.out.println("(l,c) : " + l + "," + c);
                                    grilleButton[tailLigne + incr_l * c][tailColonne + incr_c * c].setEnabled(false);
                                    grilleButton[tailLigne + incr_l * c][tailColonne + incr_c * c].setBackground(typeShip.getRepresentationGraphique());
                                }
                            }
                            try {
                                // on ajoute le bateau à la flotte
                                model.addShip(typeShip, new Coordinate(tailLigne, tailColonne), new Coordinate(headLigne, headColonne));
                                System.out.println(""+model.getJ1().getFlotte().getVaisseaux());
                            } catch (Exception ex) {
                                Logger.getLogger(JPanelPlacement.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                        case TAIL_SELECTED:

                            break;
                        default:
                        //throw something
                    }
//                    System.out.println("ligne : " + ligne + " colonne : " + colonne);
//                    previousColonne = currentColonne;
//                    previousLigne = currentLigne;
//                    currentColonne = colonne;
//                    currentLigne = ligne;
//                    System.out.println("typeShip(button) : " + typeShip);
//                    System.out.println("previous : " + previousLigne + " " + previousColonne);
//                    System.out.println("current : " + currentLigne + " " + currentColonne);

                }
            });
        }

    }

    /**
     * renvoi tout les boutons qui ne sont pas cliquable afin de determiner la
     * queue du bateau (sachant une tête déjà choisie)
     *
     * @return
     */
    private List<JButtonPlacementBateau> tailsImpossibles() {
        List<JButtonPlacementBateau> list = new ArrayList<>();
//        list.add(grilleButton[0][0]);
//        list.add(grilleButton[1][1]);
        for(int i = 0; i < grilleButton.length; i++){
            for(int j = 0; j < grilleButton[i].length; i++){
                /* 1ere condition : bateau en ligne */
                /* 2e condition : bateau en colonne */
                /* 3e condition : bateau en diagonale */
                if(!(((Math.abs(headColonne - i)==this.typeShip.getPuissance() -1)&&(j==headLigne))
                        ||((i==headColonne) && (Math.abs(headLigne - j)== this.typeShip.getPuissance() - 1))
                        ||((Math.abs(headColonne - i)==this.typeShip.getPuissance() -1)&&(Math.abs(headLigne - j)== this.typeShip.getPuissance() - 1)))){
                    list.add(grilleButton[i][j]);
                }
            }
        }
        return list; // @todo thomas
    }

    public JPanelPlacement(final BatailleNavale model, final JPanelWizard wizard) {
        super(new BorderLayout());
        this.model = model;
        east = new JPanel();
        model.addObserver(this);
        add(new JLabel(id));
        JPanel south = new JPanel();
        JButton backToCreer = new JButton("retour");
        backToCreer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                wizard.show(JPanelCreer.id);
            }
        });
        south.add(backToCreer);

        JButton valider = new JButton("valider");
        valider.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                wizard.show(JPanelJouer.id);
            }
        });
        south.add(valider);
        add(south, BorderLayout.SOUTH);
        add = new JButton("add");
        add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("add");
            }
        });
//        east.add(add);
        add(east, BorderLayout.EAST);

    }

    public void constructList(Epoque epoque) {
        if (list != null) {
            remove(list);
        }
        list = new JList(TypeShip.get(epoque));
//        list.setSelectedIndex(0);
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
        east.add(list);
    }

    public void constuctGrille(int width, int height) {
        if (grille != null) {
            remove(grille);
        }
        grille = new JPanel(new GridLayout(height, width));
        grilleButton = new JButtonPlacementBateau[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                JButtonPlacementBateau jpb = new JButtonPlacementBateau(i, j,model);
                grille.add(jpb);
                grilleButton[i][j] = jpb;
            }
        }
        add(grille, BorderLayout.CENTER);
    }

    @Override
    public void update(Observable o, Object o1) {

    }
}
