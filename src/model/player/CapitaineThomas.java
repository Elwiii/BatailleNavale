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
import model.StateCase;
import static model.StateCase.ERROR;
import model.Flotte;
import model.OrdreTir;
import model.VisionBattlefield;

/**
 * Ce bot parcouru la grille de gauche a droite et de haut en bas. A chaque
 * position, il choisit un bateau aleatoirement dans sa flotte pour tirer, tant
 * pis si il est pas a portee
 *
 * @author Nikolai
 */
public class CapitaineThomas extends Bot implements Serializable {

    private int lastColonneFired = 0;
    private int lastLigneFired = 0;

    private final Random rand;

    public CapitaineThomas() {
        this.nom = "Cpt Thomas";
        rand = new Random();
    }

    @Override
    public StateCase autoFire(VisionBattlefield bf, Flotte target) {
        try {
            int shooter = rand.nextInt(flotte.getVaisseaux().size());

            Coordinate c = new Coordinate(lastLigneFired, lastColonneFired);
            lastCoordinateFired = c;
            OrdreTir o = new OrdreTir(c, shooter);

            StateCase res = flotte.fire(target, o);
            if (lastColonneFired + 1 < map.getLargeur()) {
                lastColonneFired++;

            } else {
                lastColonneFired = 0;
                if (lastLigneFired + 1 < map.getHauteur()) {
                    lastLigneFired++;
                } else {
                    lastLigneFired = 0;
                }
            }
            return res;
        } catch (Exception ex) {
            Logger.getLogger(CapitaineThomas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ERROR;
    }

}
