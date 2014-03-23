/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.ship;

import java.net.URL;
import model.Coordinate;
import model.Flotte;

/**
 * @todo
 * @author Nikolai
 */
public abstract class Ship {
    private static int SAFE;
    private static int DAMAGED;
    private URL representationGraphique;
    private int [] etat;
    private int puissance;
    private Coordinate queue;
    private Coordinate nez; //tete, non?

    public Ship(){
        
    }
    
    /**
     * 
     * @param coordinate 
     */
    public void receivedDamage(Coordinate coordinate){
        
    }   
    
    /**
     * @return the SAFE
     */
    public static int getSAFE() {
        return SAFE;
    }

    /**
     * @param aSAFE the SAFE to set
     */
    public static void setSAFE(int aSAFE) {
        SAFE = aSAFE;
    }

    /**
     * @return the DAMAGED
     */
    public static int getDAMAGED() {
        return DAMAGED;
    }

    /**
     * @param aDAMAGED the DAMAGED to set
     */
    public static void setDAMAGED(int aDAMAGED) {
        DAMAGED = aDAMAGED;
    }

    public int fire(Flotte targe, int coordinate){
        return 0;
        
    }

    /**
     * @return the representationGraphique
     */
    public URL getRepresentationGraphique() {
        return representationGraphique;
    }

    /**
     * @param representationGraphique the representationGraphique to set
     */
    public void setRepresentationGraphique(URL representationGraphique) {
        this.representationGraphique = representationGraphique;
    }

    /**
     * @return the etat
     */
    public int[] getEtat() {
        return etat;
    }

    /**
     * @param etat the etat to set
     */
    public void setEtat(int[] etat) {
        this.etat = etat;
    }

    /**
     * @return the puissance
     */
    public int getPuissance() {
        return puissance;
    }

    /**
     * @param puissance the puissance to set
     */
    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }

    /**
     * @return the queue
     */
    public Coordinate getQueue() {
        return queue;
    }

    /**
     * @param queue the queue to set
     */
    public void setQueue(Coordinate queue) {
        this.queue = queue;
    }

    /**
     * @return the nez
     */
    public Coordinate getNez() {
        return nez;
    }

    /**
     * @param nez the nez to set
     */
    public void setNez(Coordinate nez) {
        this.nez = nez;
    }
}
