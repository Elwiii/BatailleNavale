/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.ship.shipscifi;

import java.awt.Color;
import model.Coordinate;
import model.ship.ShipScifi;

/**
 * @author Nikolai
 */
public class Hunter extends ShipScifi{
    
    public Hunter(Coordinate queue, Coordinate nez) throws Exception{
        representationGraphique = new Color(100, 200,255);
        puissance = 3;
        initializeEtats(queue,nez);
    }
    
}
