/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.List;
import model.ship.Ship;

/**
 * L'ensemble des bateau d'un joueur
 * @todo 
 * @author nikolai
 */
public class Flotte {
    // sa map
    // ses bateaux
    // des fonctions tirer sur la flotte
    //return la map de portée
    
    private final int UNREACHABLE = 0;
    private final int HIT = 1;
    private final int MISS = 2;
    private List<Ship> vaisseaux; //List?
    
    public Flotte(){
        //init vaisseaux
    }
    
    /**
     * 
     * @param target
     * @param order
     * @return 
     */
    public int fire(Flotte target, OrdreTir order){
        return 0;
    }
    
    /**
     * 
     * @param ship 
     */
    public void addShip(Ship ship){
        
    }
    
    /**
     * 
     * @param coordinate 
     */
    public void receiveDamage(Coordinate coordinate){
        
    }
    
    
}
