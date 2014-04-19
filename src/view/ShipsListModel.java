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
        fireIntervalAdded(this, getSize()-1,getSize()-1);
    }
    
    
    @Override
    public int getSize() {
//        System.out.println("getSize");
        return model.getJ1().getFlotte().getVaisseaux().size();
    }

    @Override
    public Object getElementAt(int i) {
//        System.out.println("getElementAt "+i);
        return model.getJ1().getFlotte().getVaisseaux().get(i);
    }
    
}