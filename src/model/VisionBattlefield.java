/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 * Permet de stocker une réprésentation du champs de bataille du point de vue
 * d'un joueur Affiche les coups manqués , reussis et les positions encore
 * vierges.
 *
 * @author nikolai
 */
public class VisionBattlefield implements Serializable{

    private final int[][] map;

    /* état d'une case sur la carte */
    private final int UNKNOWN = 0;
    private final int HIT = 1;
    private final int MISS = 2;

    public VisionBattlefield(int hauteur, int largeur) {
        map = new int[hauteur][largeur];
    }
    
    public void setState(Coordinate coordinate, int state){
        assert(!(state == UNKNOWN || state==HIT || state == MISS)):"Etat inconnu";
        map[coordinate.x][coordinate.y] = state;
    }
    
    public void setState(int x, int y, int state){
        assert(!(state == UNKNOWN || state==HIT || state == MISS)):"Etat inconnu";
        map[x][y] = state;
    }
    
    public int getState(int x, int y){
        return map[x][y];
    }
    
    public int getState(Coordinate coordinate){
        return map[coordinate.x][coordinate.y];
    }

}
