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
 * @author Nikolai
 */
public abstract class Player {
    protected String nom;
    protected int score;
    protected int nombrePartieJouees;   
    protected VisionBattlefield map;
    protected Flotte flotte;
    
    
    // bof bof mais ça m'emebete de mettre ça dans les constructeurs (vu que c'est abstract ...)
    public void constructFlotte(){
        flotte = new Flotte();
    }
    
    // bof bof mais ça m'emebete de mettre ça dans les constructeurs (vu que c'est abstract ...)
    public void constructMap(int hauteur,int largeur){
        map = new VisionBattlefield(hauteur, largeur);
    }
   
    /**
     * 
     * @param order
     * @param target
     * @return 
     */
    public abstract int fire(OrdreTir order, Flotte target) throws Exception;
    
    /**
     * 
     * @param ship 
     */
    public void addShip(Ship ship){
        flotte.addShip(ship);
    }
    
    public void removeShip(int idShip){
        flotte.removeShip(idShip);
    }
    
    /**
     * 
     * @param coordinate 
     * @throws java.lang.Exception 
     */
    public void receivedDamage(Coordinate coordinate) throws Exception{
                flotte.receiveDamage(coordinate);
    }

    /**
     * @return the flotte
     */
    public Flotte getFlotte() {
        return flotte;
    }

    /**
     * @param flotte the flotte to set
     */
    public void setFlotte(Flotte flotte) {
        this.flotte = flotte;
    }

    /**
     * @return the map
     */
    public VisionBattlefield getMap() {
        return map;
    }

    /**
     * @param map the map to set
     */
    public void setMap(VisionBattlefield map) {
        this.map = map;
    }
    
}
