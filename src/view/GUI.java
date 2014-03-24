/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import model.BatailleNavale;
import model.BatailleNavaleAdapter;
import model.player.State;

/**
 *
 * @author nikolai
 */
public class GUI extends JFrame {

    private final JPanelWizard cards;
    private final BatailleNavaleAdapter model_adapter;
    private final BatailleNavale model;
    
    
    public GUI() {
        super("bataille navale");
        model_adapter = new BatailleNavaleAdapter();
        
        model = new BatailleNavale();
        
        //Create the panel that contains the "cards".
        cards = new JPanelWizard(model);

        add(cards,BorderLayout.CENTER);
        
        //finalisation de la JFrame
        setPreferredSize(new Dimension(650, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }

    public static void main(String[] args) {
        new GUI();
    }
}
