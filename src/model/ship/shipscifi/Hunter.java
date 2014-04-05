/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.ship.shipscifi;

import java.awt.Color;
import model.Coordinate;
import static model.ship.Ship.representationGraphique;
import model.ship.ShipScifi;
import model.ship.TypeShip;

/**
 * @author Nikolai
 */
public class Hunter extends ShipScifi{
    
    public Hunter(Coordinate queue, Coordinate nez) throws Exception{
        super(TypeShip.HUNTER);
        initializeEtats(queue,nez);
    }
    
}
