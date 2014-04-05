/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.ship.shipxx;

import java.awt.Color;
import model.Coordinate;
import static model.ship.Ship.representationGraphique;
import model.ship.ShipXX;
import model.ship.TypeShip;

/**
 * @author Nikolai
 */
public class Croiseur extends ShipXX{
    
    public Croiseur(Coordinate queue, Coordinate nez) throws Exception{
        super(TypeShip.CROISEUR);
        initializeEtats(queue,nez);
    }
}
