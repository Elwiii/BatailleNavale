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
    private final BatailleNavale model;
    
    public JButtonFire(final BatailleNavale model,final Coordinate c){
        super("?");
        this.c  = c;
        this.model=model;
        model.addObserver(this);
        addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println(""+c);
                //System.out.println(""+model.getJ1().getFlotte().getVaisseaux().get(0));
                model.update();
            }
        });
    }
    
    public Coordinate getC(){
        return c;
    }

    @Override
    public void update(Observable o, Object o1) {
        System.out.println("UPDATE JBUTTONFIRE");
        int state = model.getJ2().getMap().getState(c);
        if(state ==0){
            this.setText("?"); // ? pour dire "pas encore attaqué"
        }
        /* Cas d'une case touchée */
        else if(state == 1){
            this.setText("X"); // Croix pour dire "touché"
            this.setEnabled(false); //Desactivation pour ne pas tirer au même endroit
        }
        else if(state == 2){
            this.setText(""); // " " pour dire "raté"  
            this.setEnabled(false); //Desactivation pour ne pas tirer au même endroit
        }
    }
    
}
