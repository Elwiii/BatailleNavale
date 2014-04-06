/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.ship;

import java.io.Serializable;
import model.Coordinate;
import model.ship.shipscifi.Crusader;
import model.ship.shipscifi.Hunter;
import model.ship.shipscifi.MotherShip;
import model.ship.shipscifi.Viper;
import model.ship.shipxviii.Barque;
import model.ship.shipxviii.Galere;
import model.ship.shipxviii.Gallion;
import model.ship.shipxviii.Transport;
import model.ship.shipxx.Croiseur;
import model.ship.shipxx.Fregate;
import model.ship.shipxx.Jetski;
import model.ship.shipxx.PorteAvion;
import model.ship.shipxx.Sousmarin;

/**
 * @author Nikolai
 */
public class ShipFactory {
    private static ShipFactory instance = null;
    
    public ShipFactory(){
        
    }
    
    /**
     * 
     * @param epoque
     * @param puissance
     * @param queue 
     * @param nez
     * @return un bateau en fonction de l'epoque
     */
    public Ship buildShip(Epoque epoque, int puissance,Coordinate queue, Coordinate nez) throws Exception{
        Ship shipSF = null;
        Ship shipxviii = null;
        Ship shipxx = null;
        if(epoque == Epoque.SCIENCEFICTION){
            shipSF = new ShipScifi(); 
            shipSF.puissance = puissance;
            shipSF.initializeEtats(queue, nez);
            return shipSF;
        }
        
        else if (epoque == Epoque.XVIII){
            shipxviii = new ShipXVIII();
            shipxviii.puissance = puissance;
            shipxviii.initializeEtats(queue, nez);
            return shipxviii;
        }
        
        else if (epoque == Epoque.XX){
            shipxx = new ShipXX();
            shipxx.puissance = puissance;
            shipxx.initializeEtats(queue, nez);
            return shipxx;
        }
        return null;
        
    }
    
    /**
     * 
     * @param type
     * @param queue  
     * @param nez
     * @return 
     */
    public Ship buildShip(TypeShip type,Coordinate queue, Coordinate nez) throws Exception{
        Ship ship = null;
        switch(type){
            case CRUSADER:
                 ship = new Crusader(queue, nez);
                return ship;
            case HUNTER:
                ship = new Hunter(queue, nez);
                return ship;
            case MOTHER: 
                ship = new MotherShip(queue, nez);
                return ship;
            case VIPER:
                ship = new Viper(queue, nez);
                return ship;
            case GALLION: 
                ship = new Gallion(queue, nez);
                return ship;
            case BARQUE: 
                ship = new Barque(queue, nez);
                return ship;
            case GALERE: 
                ship = new Galere(queue, nez);
                return ship;
            case TRANSPORT: 
                ship = new Transport(queue, nez);
                return ship;
            case CROISEUR: 
                ship = new Croiseur(queue, nez);
                return ship;
            case FREGATE: 
                ship = new Fregate(queue, nez);
                return ship;
            case JETSKI: 
                ship = new Jetski(queue, nez);
                return ship;
            case PORTEAVION:
                ship = new PorteAvion(queue, nez);
                return ship;
            case SOUSMARIN:
                ship = new Sousmarin(queue, nez);
                return ship;
        }
        return null;
    }
    
    /**
     * 
     * @return instance
     */
    public static ShipFactory getInstance(){
        if (instance == null)   
            instance = new ShipFactory();
        return instance;    
    }
}
