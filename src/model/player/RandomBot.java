/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.player;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;
import model.Coordinate;
import model.StateCase;
import static model.StateCase.ERROR;
import model.Flotte;
import model.OrdreTir;
import model.VisionBattlefield;

/**
 * @author Nikolai
 */
public class RandomBot extends Bot implements Serializable{
    
    public RandomBot(){
        this.nom = "randomBot";
    }

    @Override
    public StateCase autoFire(VisionBattlefield bf, Flotte target) {
        try {
            int x = (int)(Math.random());
            int y = (int)(Math.random());
            int l = (int)(Math.random());
            Coordinate c = new Coordinate(x ,y);
            lastCoordinateFired = c;
            
            int nbShip = target.getVaisseaux().size();
            Random rand = new Random();
            int nb = rand.nextInt(nbShip)+1;
            OrdreTir o = new OrdreTir(c, nb);
            return flotte.fire(target, o);
        } catch (Exception ex) {
            Logger.getLogger(RandomBot.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ERROR;
    }     
}