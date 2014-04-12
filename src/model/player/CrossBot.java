/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.player;

import java.io.Serializable;
import java.util.List;
import model.Flotte;
import model.VisionBattlefield;
import model.ship.Ship;

/**
 * @todo Rym
 * @author Nikolai
 */
public class CrossBot extends Bot implements Serializable{
    

    public CrossBot(){
        this.nom = "crossBot";
    }
    
    @Override
    public int autoFire(VisionBattlefield bf, Flotte target){
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        List <Ship> list = target.getVaisseaux();
        VisionBattlefield v;
        int totalShip = 0;
        int scoreSrc = Integer.MIN_VALUE;
     
        for(Ship ship : list){ 
            int score = bf.getState(null);
            if(score > scoreSrc){
                scoreSrc = score;
                v = bf;
                return scoreSrc;
}
        }
        
        VisionBattlefield v2;
        int scoreDst = Integer.MAX_VALUE;
        
        for(Ship nShip : !list){
            int score = bf.getState(coordinate);
            if (score < scoreDst){
                scoreDst = score;
                v2 = !bf;
                return scoreDst;
            }   
                
         }
        return 0;
    }
}
