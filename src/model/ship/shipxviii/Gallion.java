/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.ship.shipxviii;

import java.awt.Color;
import model.Coordinate;
import static model.ship.Ship.representationGraphique;
import model.ship.ShipXVIII;
import model.ship.TypeShip;

/**
 * @author Nikolai
 */
public class Gallion extends ShipXVIII{
    
    public Gallion(Coordinate queue, Coordinate nez) throws Exception{
        super(TypeShip.GALLION);
        initializeEtats(queue,nez);
    }
}
