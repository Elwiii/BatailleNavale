/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Observable;

/**
 * @OK
 * @author Nikolai
 */
public class BatailleNavaleAdapter extends Observable{

    public BatailleNavaleAdapter(){
        
    }
    
    public void update(){
        setChanged();
        notifyAll();
    }
    
}
