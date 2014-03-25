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
import model.BatailleNavale;
import model.ship.TypeShip;

/**
 *
 * @author nikolai
 */
public class JPanelPlacement extends JPanel implements Observer {

    public static final String id = "jpanelplacement";

    public JPanelPlacement(final BatailleNavale model, final JPanelWizard wizard) {
        super(new BorderLayout());
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
        JPanel grille = new JPanel(new GridLayout(10, 10/* à recup sur model)*/));
        for (int i = 0; i < 100; i++) {
            grille.add(new JButtonPlacementBateau());

        }

        add(grille, BorderLayout.CENTER);
        String[] data = {"typeboat1", "typeboat2"};
        JList list = new JList(TypeShip.values()); //data has type Object[]
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(-1);
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 80));

        add(list, BorderLayout.EAST);

    }

    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
