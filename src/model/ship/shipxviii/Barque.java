/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.ship.shipxviii;

import java.io.Serializable;
import model.Coordinate;
import model.ship.ShipXVIII;
import model.ship.TypeShip;

/**
 * @author Nikolai
 */
public class Barque extends ShipXVIII implements Serializable{
    
    public Barque(Coordinate queue, Coordinate nez) throws Exception{
        super(TypeShip.BARQUE);
        initializeEtats(queue,nez);
    }
}
