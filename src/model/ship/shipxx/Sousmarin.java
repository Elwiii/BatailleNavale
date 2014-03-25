/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.ship.shipxx;

import java.awt.Color;
import model.Coordinate;
import model.ship.ShipXX;

/**
 * @author Nikolai
 */
public class Sousmarin extends ShipXX{
    
    public Sousmarin(Coordinate queue, Coordinate nez) throws Exception{
        representationGraphique = new Color(255, 26,255);
        puissance = 3;
        initializeEtats(queue,nez);
    }
}
