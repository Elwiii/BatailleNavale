/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.player;

import model.Coordinate;
import model.Flotte;
import model.OrdreTir;
import model.VisionBattlefield;
import model.ship.Ship;

/**
 *
 * @author Nikolai
 */
public class Player {
    private String nom;
    private int score;
    private int nombrePartieJouees;   
    private VisionBattlefield map;
    
    public Player(String nom, int score, int nombrePartiesJouees){
        this.nom = nom;
        this.score = score;
        this.nombrePartieJouees = nombrePartieJouees;
    }
    /**
     * 
     * @param order
     * @param target
     * @return 
     */
    public int fire(OrdreTir order, Flotte target){
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
    public void receivedDamage(Coordinate coordinate){
        
    }
    
}
