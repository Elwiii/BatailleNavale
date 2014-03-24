/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.ship.shipxx;

import java.awt.Color;
import model.ship.ShipXX;

/**
 * @author Nikolai
 */
public class Fregate extends ShipXX{
    
    public Fregate(){
        representationGraphique = new Color(255, 66,255);
        puissance = 5;
    }
}