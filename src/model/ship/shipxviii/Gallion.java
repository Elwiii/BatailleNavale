/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.ship.shipxviii;

import java.awt.Color;
import model.Coordinate;
import model.ship.ShipXVIII;

/**
 * @author Nikolai
 */
public class Gallion extends ShipXVIII{
    
    public Gallion(Coordinate queue, Coordinate nez) throws Exception{
        representationGraphique = new Color(155, 100,255);
        puissance = 6;
        initializeEtats(queue,nez);
    }
}
