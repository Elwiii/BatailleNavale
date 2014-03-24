/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ship;

import java.awt.Color;
import java.net.URL;
import java.util.List;
import model.Coordinate;
import model.Flotte;

/**
 * @todotofinish @author Nikolai
 */
public class Ship {

    protected class Etat {

        Coordinate c;
        int etat;

        protected Etat(Coordinate c, int etat) {
            this.c = c;
            this.etat = etat;
        }
    }

    protected static final int SAFE = 0;
    protected static final int DAMAGED = 1;
    //protected URL representationGraphique;
    protected Color representationGraphique; // on fait simple
    protected List<Etat> etats;
    protected int puissance;
//    private Coordinate queue;
//    private Coordinate nez; //tete, non?
    
    
    /**
     * remplir la liste etats en fonction de la position de la tete et la queue 
     * la queue est la tête doivent former une ligne ou une diagonale
     * @todotest
     * @param queue
     * @param nez 
     */
    protected void initializeEtats(Coordinate queue, Coordinate nez){
        if(queue.x == nez.x && queue.y != nez.y){
            // en hauteur
            int maxY = Math.max(queue.y,nez.y);
            int minY = Math.min(queue.y,nez.y);
                for (int j = 0; j <= maxY; j++) {
                    etats.add(new Etat(new Coordinate(nez.x,minY+j), SAFE));
            }
        }else if(queue.x != nez.x && queue.y == nez.y){
            // en longueur
            int maxX = Math.max(queue.x,nez.x);
            int minX = Math.min(queue.x,nez.x);
                for (int j = 0; j <= maxX; j++) {
                    etats.add(new Etat(new Coordinate(minX+j,nez.y), SAFE));
            }
        }else if(queue.x == nez.x && queue.y == nez.y){
            // nez = queue
            etats.add(new Etat(new Coordinate(nez.x,nez.y), SAFE));
        }else {
            // diagonale
            int maxX = Math.max(queue.x,nez.x);
            int maxY = Math.max(queue.y,nez.y);
            int minX = Math.min(queue.x,nez.x);
            int minY = Math.min(queue.y,nez.y);
            for (int i = 0; i <= maxX; i++) {
                for (int j = 0; j <= maxY; j++) {
                    etats.add(new Etat(new Coordinate(minX+i,minY+i), SAFE));
                }
            }
        }
   }

    /**
     * tire sur un flotte Il n'y a que la classe flotte qui peut apeller cette
     * fonction Le bateau doit être sur de toucher sa cible pour fire.
     *
     * @param target
     * @param coordinate
     */
    public void fire(Flotte target, Coordinate coordinate) {
        target.receiveDamage(coordinate);
    }

    /**
     * la reception de dommage par default d'un bateau
     * peut être override selon le bateau
     * @param coordinate
     */
    public void receivedDamage(Coordinate coordinate) throws Exception {
        boolean stop = false;
        Etat e = null;
        int i = 0;
        while (!stop) {
            e = etats.get(i);
            stop = e.c.equals(coordinate);
            i++;
        }
        if (i <= etats.size()) {
            e.etat = DAMAGED;
        } else {
            throw new Exception("le bateau ne possède aucune de ses parties à cette coordonée");
        }
    }


   

    /**
     * // * @return the etat //
     */
//    public int[] getEtat() {
//        return etat;
//    }
//
//    /**
//     * @param etat the etat to set
//     */
//    public void setEtat(int[] etat) {
//        this.etat = etat;
//    }
//
//    /**
//     * @return the puissance
//     */
//    public int getPuissance() {
//        return puissance;
//    }
//
//    /**
//     * @param puissance the puissance to set
//     */
//    public void setPuissance(int puissance) {
//        this.puissance = puissance;
//    }
//
//    /**
//     * @return the queue
//     */
//    public Coordinate getQueue() {
//        return queue;
//    }
//
//    /**
//     * @param queue the queue to set
//     */
//    public void setQueue(Coordinate queue) {
//        this.queue = queue;
//    }
//
//    /**
//     * @return the nez
//     */
//    public Coordinate getNez() {
//        return nez;
//    }
//
//    /**
//     * @param nez the nez to set
//     */
//    public void setNez(Coordinate nez) {
//        this.nez = nez;
//    }
}
