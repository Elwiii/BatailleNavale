/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import javax.swing.AbstractListModel;
import model.BatailleNavale;

/**
 *
 * @author Nikolai
 */
public class ShipsListModel extends AbstractListModel{
    
    private final BatailleNavale model;
    
    
    
    public ShipsListModel(final BatailleNavale model){
        this.model = model;
    }

    
    public void update(){
        fireContentsChanged(this,0,getSize());
    }
    
    
    @Override
    public int getSize() {
        return model.getJ1().getFlotte().getVaisseaux().size();
    }

    @Override
    public Object getElementAt(int i) {
        return model.getJ1().getFlotte().getVaisseaux().get(i);
    }
    
}