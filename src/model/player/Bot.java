/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.player;

import java.io.Serializable;
import model.Flotte;
import model.OrdreTir;
import model.VisionBattlefield;

/**
 * @author Nikolai
 */
public abstract class Bot extends Player implements Serializable{

    /**
     * 
     * @param bf
     * @param target
     * @return  
     */
    public abstract int autoFire(VisionBattlefield bf, Flotte target);
        
    
    
    /**
     * 
     * @param order
     * @param target 
     * @return  
     */
    @Override
    public int fire(OrdreTir order, Flotte target){
        return autoFire(getMap(),target);
    }
    
    
}
