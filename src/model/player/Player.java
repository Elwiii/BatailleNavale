/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.player;

import java.io.Serializable;
import java.util.List;
import model.Coordinate;
import model.StateCase;
import model.Flotte;
import model.OrdreTir;
import model.VisionBattlefield;
import model.ship.Ship;
import model.ship.Ship.Etat;

/**
 * @author Nikolai
 */
public abstract class Player implements Serializable {

    protected String nom;
    protected int score;
    protected int nombrePartieJouees;
    protected VisionBattlefield map;
    protected Flotte flotte;

    // bof bof mais ça m'emebete de mettre ça dans les constructeurs (vu que c'est abstract ...)
    public void constructFlotte() {
        flotte = new Flotte();
    }

    // bof bof mais ça m'emebete de mettre ça dans les constructeurs (vu que c'est abstract ...)
    public void constructMap(int hauteur, int largeur) {
        map = new VisionBattlefield(hauteur, largeur);
    }

    public abstract void updateBattlefield(OrdreTir order, StateCase state);

    /**
     *
     * @param order
     * @param target
     * @return
     */
    public abstract StateCase fire(OrdreTir order, Flotte target) throws Exception;

    /**
     *
     * @param ship
     */
    public boolean horsDePortee(Flotte target) {
        for (Ship s : this.flotte.getVaisseaux()) {
            for (Ship st : target.getVaisseaux()) {
                for (Etat et : st.getEtats()) {
                    if(et.getEtat()==Ship.SAFE){
                        if (s.estAporteeDeTir(et.getC())) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public void addShip(Ship ship) {
        flotte.addShip(ship);
    }

    public void removeShip(int idShip) {
        flotte.removeShip(idShip);
    }

    /**
     *
     * @param coordinate
     * @throws java.lang.Exception
     */
    public void receivedDamage(Coordinate coordinate) throws Exception {
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

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
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

}
