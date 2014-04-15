/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import model.BatailleNavale;
import persistance.ScoreManager;

/**
 * @author nikolai
 */
public class JPanelScore extends JPanel implements Observer{

    public static final String id = "jpanelscore";
    
    private JTable table;
    
    public JPanelScore(final BatailleNavale model,final JPanelWizard wizard){
        super(new BorderLayout());
        model.addObserver(this);
        table = new JTable(ScoreManager.getInstance());
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(table);
        

        add(scrollPane,BorderLayout.CENTER);
        add(new JButtonBackToAcceuil(model,wizard),BorderLayout.SOUTH);
    }
    
    @Override
    public void update(Observable o, Object o1) {
        //@todo reparation de fortune => faire moins moche, attaché un listener à la table ou un truc dans le genre
        table = new JTable(ScoreManager.getInstance());
    }
    
}
