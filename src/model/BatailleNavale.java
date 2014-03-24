/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.net.URL;
import java.util.List;
import java.util.Observer;
import model.player.Player;
import model.ship.ShipFactory;
import model.ship.TypeShip;

/**
 * @author nikolai
 * @todo
 */
public class BatailleNavale {
    private String id;
    private int score;
    private State state;
    private Player j1;
    private Player j2; // le bot par defaut
    private ShipFactory shipFactory;
    private BatailleNavaleAdapter adapter;
    private Game save;
    
    private BatailleNavaleAdapter bna;
    
    public BatailleNavale(){
        bna = new BatailleNavaleAdapter();
    }
    
    
    
    public void addObserver(Observer o){
        bna.addObserver(o);
    }
    
    public void update(){
        bna.update();
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

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @return the state
     */
    public State getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * @return the j1
     */
    public Player getJ1() {
        return j1;
    }

    /**
     * @param j1 the j1 to set
     */
    public void setJ1(Player j1) {
        this.j1 = j1;
    }

    /**
     * @return the j2
     */
    public Player getJ2() {
        return j2;
    }

    /**
     * @param j2 the j2 to set
     */
    public void setJ2(Player j2) {
        this.j2 = j2;
    }

    /**
     * @return the shipFactory
     */
    public ShipFactory getShipFactory() {
        return shipFactory;
    }

    /**
     * @param shipFactory the shipFactory to set
     */
    public void setShipFactory(ShipFactory shipFactory) {
        this.shipFactory = shipFactory;
    }

    /**
     * @return the adapter
     */
    public BatailleNavaleAdapter getAdapter() {
        return adapter;
    }

    /**
     * @param adapter the adapter to set
     */
    public void setAdapter(BatailleNavaleAdapter adapter) {
        this.adapter = adapter;
    }

    /**
     * @return the save
     */
    public Game getSave() {
        return save;
    }

    /**
     * @param save the save to set
     */
    public void setSave(Game save) {
        this.save = save;
    }

    /**
     * @return the bna
     */
    public BatailleNavaleAdapter getBna() {
        return bna;
    }

    /**
     * @param bna the bna to set
     */
    public void setBna(BatailleNavaleAdapter bna) {
        this.bna = bna;
    }
}
