/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.awt.CardLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import model.BatailleNavale;

/**
 *
 * @author nikolai
 */
public class JPanelWizard extends JPanel implements Observer{

    private final CardLayout cl ;
    
    public JPanelWizard(final BatailleNavale model){
        super();
        cl = new CardLayout();
        this.setLayout(cl);
        model.addObserver(this);
        add(new JPanelAcceuil(model,this),JPanelAcceuil.id);
        add(new JPanelCreer(model,this),JPanelCreer.id);
        add(new JPanelJouer(model),JPanelJouer.id);
        add(new JPanelParties(model),JPanelParties.id);
        add(new JPanelScore(model),JPanelScore.id);
        add(new JPanelPlacement(model,this),JPanelPlacement.id);
        
        show(JPanelAcceuil.id);
        
    }
    
    public void show(String id){
        cl.show(this, id);
    }
    
    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
