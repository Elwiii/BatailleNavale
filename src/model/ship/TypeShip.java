/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ship;

import java.awt.Color;

/**
 * @author Nikolai
 */
public enum TypeShip {
    CRUSADER(6,new Color(155, 200,255))
    , HUNTER(3,new Color(100, 200,255))
    , MOTHER(7,new Color(155, 200,255))
    , VIPER(2,new Color(155, 200,0))
    , GALLION(1,new Color(155, 100,255))
    , BARQUE(5,new Color(0, 100,0))
    , GALERE(6,new Color(155, 100,255))
    , TRANSPORT(2,new Color(155, 255,255))
    , CROISEUR(6,new Color(255, 100,255))
    , FREGATE(5,new Color(255, 66,255))
    , JETSKI(1,new Color(255, 100,255))
    , PORTEAVION(10,new Color(5, 100,0))
    , SOUSMARIN(3,new Color(255, 26,255));

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
    
    /*private final String nom;*/
    private final int puissance;
    private final Color representationGraphique;
    
    TypeShip(/*String nom,*/ int puissance, Color representationGraphique)
    {
        /*this.nom = nom;*/
        this.puissance = puissance;
        this.representationGraphique = representationGraphique;
    }

    /**
     * @return the puissance
     */
    public int getPuissance() {
        return puissance;
    }

    /**
     * @return the representationGraphique
     */
    public Color getRepresentationGraphique() {
        return representationGraphique;
    }
    
    
    

}


