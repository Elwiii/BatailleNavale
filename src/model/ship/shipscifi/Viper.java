/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.ship.shipscifi;

import java.io.Serializable;
import model.Coordinate;
import model.ship.ShipScifi;
import model.ship.TypeShip;

/**
 * @author Nikolai
 */
public class Viper extends ShipScifi implements Serializable{
    
    public Viper(Coordinate queue, Coordinate nez) throws Exception{
        super(TypeShip.VIPER);
        initializeEtats(queue,nez);
    }
}
