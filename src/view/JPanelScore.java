/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.BatailleNavale;

/**
 *
 * @author nikolai
 */
public class JPanelScore extends JPanel implements Observer{

    public static final String id = "jpanelscore";
    
    public JPanelScore(final BatailleNavale model){
        super();
        model.addObserver(this);
        add(new JLabel(id));
        
    }
    
    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
