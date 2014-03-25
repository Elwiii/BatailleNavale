/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import model.BatailleNavale;
import model.Coordinate;

/**
 *
 * @author nikolai
 */
public class JPanelJouer extends JPanel implements Observer {

    public static final String id = "jpaneljouer";

    public JPanelJouer(final BatailleNavale model) {
        super(new BorderLayout());
        model.addObserver(this);
        add(new JLabel(id));
        JPanel center = new JPanel();
        JPanel grilleEnnemi = new JPanel(new GridLayout(10, 10/* à recup sur model)*/));
        JPanel grilleFlotte = new JPanel(new GridLayout(10, 10/* à recup sur model)*/));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grilleEnnemi.add(new JButtonFire(model, new Coordinate(i, j)));
            }
        }
        for (int i = 0; i < 100; i++) {
            grilleFlotte.add(new JLabel("todo f"));

        }
        center.add(grilleEnnemi);
        center.add(grilleFlotte);
        add(center, BorderLayout.CENTER);
        
        
        JList list = new JList(); //data has type Object[]
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(-1);
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 80));
        
        add(list,BorderLayout.EAST);

    }

    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
