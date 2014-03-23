/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.net.URL;
import java.util.List;
import model.player.Player;
import model.player.State;
import model.ship.ShipFactory;
import model.ship.TypeShip;

/**
 * @author nikolai
 * @todo
 */
public class BatailleNavale {
    private String id;
    private int score;
    private Thread.State state;
    private Player j1;
    private Player j2;
    private ShipFactory shipFactory;
    private BatailleNavaleAdapter adapter;
    private Game save;
    
    public BatailleNavale(String id, int score, State state){
        save = new Game(j1, j2, score, state);
        this.id = id;
    }
    
    /**
     * 
     * @param order 
     */
    public void fire(OrdreTir order){
        
    }
    
    /**
     * 
     */
    public void save(){
        
    }
    
    
    /**
     * 
     */
    public void giveup(){
        
    }
    
    /**
     * 
     */
    public void switchTurn(){
        
    }
    
    /**
     * 
     * @param id 
     */
    public void load(String id){
        
    }
    
    /**
     * 
     * @param idShip
     * @param joueur 
     */
    public void removeShip(int idShip, int joueur){
        
    }
    
    /**
     * 
     * @param joueur
     * @param typeShip 
     */
    public void addShip(int joueur, TypeShip typeShip){
        
    }
    
    /**
     * 
     * @return 
     */
    public List<URL> getSaveGame(){
        return null;
        
    }
}
