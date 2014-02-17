/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.player;

import java.util.List;
import model.VisionBattlefield;
import model.Flotte;
import model.OrdreTir;
import model.Ship;

/**
 * TODO
 * @author nikolai
 */
public abstract class AbstractPlayer {
    
    /**
     * Applique un ordre de tir sur une liste de bateau ennemi. Met à jour le battefield.
     * @param bf
     * @param od 
     * @param flotte 
     */
    public abstract void play(VisionBattlefield bf, OrdreTir od, Flotte flotte);
    
}
