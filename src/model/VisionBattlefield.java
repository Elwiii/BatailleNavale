/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import static model.StateCase.HIT;
import static model.StateCase.MISS;
import static model.StateCase.UNKNOWN;

/**
 * Permet de stocker une réprésentation du champs de bataille du point de vue
 * d'un joueur Affiche les coups manqués , reussis et les positions encore
 * vierges.
 * @author nikolai
 */
public class VisionBattlefield implements Serializable{

    private final StateCase[][] map;

    /* état d'une case sur la carte */
//    private final int UNKNOWN = 0;
//    private final int HIT = 1;
//    private final int MISS = 2;
    
    private final int hauteur;
    private final int largeur;

    public VisionBattlefield(int hauteur, int largeur) {
        map = new StateCase[hauteur][largeur];
        this.hauteur = hauteur;
        this.largeur = largeur;
    }
    
    
    public void setState(Coordinate coordinate, StateCase state){
        assert(!(state == UNKNOWN || state==HIT || state == MISS)):"Etat inconnu";
        map[coordinate.x][coordinate.y] = state;
    }
    
    public void setState(int x, int y, StateCase state){
        assert(!(state == UNKNOWN || state==HIT || state == MISS)):"Etat inconnu";
        map[x][y] = state;
    }
    
    public StateCase getState(int x, int y){
        return map[x][y];
    }
    
    public StateCase getState(Coordinate coordinate){
        return map[coordinate.x][coordinate.y];
    }
    
    public StateCase[][] getMap(){
        return this.map;
    }

    /**
     * @return the hauteur
     */
    public int getHauteur() {
        return hauteur;
    }

    /**
     * @return the largeur
     */
    public int getLargeur() {
        return largeur;
    }

}
