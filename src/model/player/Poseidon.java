/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.player;

import java.io.Serializable;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Coordinate;
import model.Flotte;
import model.OrdreTir;
import model.VisionBattlefield;
import model.ship.Ship;

/**
 * tir sans jamais MISS sauf si unreachable. Choisit un bateau de sa flotte au hasard pour tirer
 * @author Nikolai
 */
public class Poseidon extends Bot implements Serializable{
    
    private final Random rand;
    private int lastShipHit = 0;
    private int lastPartieShipHit = 0;
    
    public Poseidon(){
        this.nom = "Poseidon";
        rand = new Random();
    }

    @Override
    public int autoFire(VisionBattlefield bf, Flotte target) {
        try {
            Ship nextVictim = target.getVaisseaux().get(lastShipHit);
            int shooter = rand.nextInt(flotte.getVaisseaux().size());
            Coordinate c = nextVictim.getEtats().get(lastPartieShipHit).getC();
            lastCoordinateFired = c;
            OrdreTir o = new OrdreTir(c,shooter);
            
            int res = flotte.fire(target, o);
            lastPartieShipHit = nextVictim.isDetroy()? 0 : lastPartieShipHit + 1;
            System.out.println("Poseidon a tiré en "+c+" et a eu le resultat suivant  : "+res);
            return res;
        } catch (Exception ex) {
            Logger.getLogger(Poseidon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
        
    }
    
}
