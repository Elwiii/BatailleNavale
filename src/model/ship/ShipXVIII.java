/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.ship;

import java.io.Serializable;

/**
 * @author Nikolai
 */
public class ShipXVIII extends Ship implements Serializable{
 
    public ShipXVIII(){
    }
    
    protected ShipXVIII(TypeShip type){
        super(type);
    }
}
