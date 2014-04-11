/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.player;

import java.io.Serializable;
import model.Flotte;
import model.VisionBattlefield;

/**
 * @todo Rym
 * @author Nikolai
 */
public class CrossBot extends Bot implements Serializable{
    

    public CrossBot(){
        this.nom = "crossBot";
    }
    
    @Override
    public int autoFire(VisionBattlefield bf, Flotte target) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
