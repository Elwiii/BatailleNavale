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
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.BatailleNavale;
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
    private int currentColonne;
    private int currentLigne;
    private int previousColonne;
    private int previousLigne;
    private int state;
    private final int EPOQUE_SELECTED = 0;
    private final int HEAD_SELECTED = 1;
    private final int TAIL_SELECTED = 2;
    private final JPanel east;
    private final JButton add;
    
    private class JButtonPlacementBateau extends JButton {

        final int ligne;
        final int colonne;

        public JButtonPlacementBateau(final int ligne, final int colonne) {
            super();
            this.ligne = ligne;
            this.colonne = colonne;
            this.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("ligne : " + ligne + " colonne : " + colonne);
                    previousColonne = currentColonne;
                    previousLigne = currentLigne;
                    currentColonne = colonne;
                    currentLigne = ligne;
                    System.out.println("typeShip(button) : "+typeShip);
                    
                }
            });
        }

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
        add(east,BorderLayout.EAST);

    }

    public void constructList(Epoque epoque) {
        if (list != null) {
            remove(list);
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
                state = EPOQUE_SELECTED;
                System.out.println("type : "+typeShip);
            }
        });
        east.add(list);
    }

    public void constuctGrille(int longueur, int largeur) {
        if (grille != null) {
            remove(grille);
        }
        grille = new JPanel(new GridLayout(largeur, longueur));
        for (int i = 0; i < longueur; i++) {
            for (int j = 0; j < largeur; j++) {
                grille.add(new JButtonPlacementBateau(i, j));
            }
        }
        add(grille, BorderLayout.CENTER);
    }

    @Override
    public void update(Observable o, Object o1) {

    }
}
