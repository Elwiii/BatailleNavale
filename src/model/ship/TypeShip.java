/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ship;

/**
 * @author Nikolai
 */
public enum TypeShip {
    CRUSADER, HUNTER, MOTHER, VIPER, GALLION, BARQUE, GALERE, TRANSPORT, CROISEUR, FREGATE, JETSKI, PORTEAVION, SOUSMARIN;

    private static final TypeShip[] xx = {CROISEUR, FREGATE, JETSKI, PORTEAVION, SOUSMARIN};
    private static final TypeShip[] xviii = {GALLION, BARQUE, GALERE, TRANSPORT};
    private static final TypeShip[] sf = {CRUSADER, HUNTER, MOTHER, VIPER};
    
    public static TypeShip[] get(Epoque epoque){
        switch(epoque){
            case SCIENCEFICTION :
                return sf;
            case XVIII :
                return xviii;
            case XX :
                return xx;
            default :
                return null;// pas beau
        }
    }
    

}


