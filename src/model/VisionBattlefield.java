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
public class VisionBattlefield implements Serializable {

    private final StateCase[][] map;

    private final int hauteur;
    private final int largeur;

    public VisionBattlefield(int hauteur, int largeur) {
        map = new StateCase[hauteur][largeur];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
               map[i][j] = StateCase.UNKNOWN;
            }
        }
        this.hauteur = hauteur;
        this.largeur = largeur;
    }

    public void setState(Coordinate coordinate, StateCase state) {
        map[coordinate.x][coordinate.y] = state;
    }

    public void setState(int x, int y, StateCase state) {
        map[x][y] = state;
    }

    public StateCase getState(int x, int y) {
        return map[x][y];
    }

    public StateCase getState(Coordinate coordinate) {
        return map[coordinate.x][coordinate.y];
    }

    public StateCase[][] getMap() {
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
