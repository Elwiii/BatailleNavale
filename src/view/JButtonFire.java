/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import model.BatailleNavale;
import model.Coordinate;

/**
 * @todo thomas
 * @author nikolai
 */
public class JButtonFire extends JButton implements Observer{
    private final Coordinate c;
    
    public JButtonFire(final BatailleNavale model,final Coordinate c){
        super("D");
        this.c  = c;
        model.addObserver(this);
        addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("CLIQUE");
                System.out.println("voici les coord"+c);
            }
        });
    }

    @Override
    public void update(Observable o, Object o1) {
    }
    
}
