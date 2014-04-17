/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Coordinate;
import model.Flotte;
import model.OrdreTir;
import model.StateCase;
import model.VisionBattlefield;
import model.ship.Ship;
import model.ship.Ship.Etat;
import model.ship.ShipFactory;
import model.ship.TypeShip;

/**
 * @author Nikolai
 */
public abstract class Bot extends Player implements Serializable {

    private static final int MAX_TRY = 100;

    protected Coordinate lastCoordinateFired;

    /**
     *
     * @param bf
     * @param target
     * @return
     */
    public abstract StateCase autoFire(VisionBattlefield bf, Flotte target);

    @Override
    public void updateBattlefield(OrdreTir order, StateCase state) {
        map.setState(lastCoordinateFired, state);
    }

    /**
     *
     * @param order
     * @param target
     * @return
     */
    @Override
    public StateCase fire(OrdreTir order, Flotte target) {
        System.out.println("TIR BOT");
        return autoFire(getMap(), target);
    }

    /**
     * Place les bateaux de manière aléatoire par defaut. Peut être override par
     * un type de bot pour rendre le placement plus efficace
     *
     * @param bateaux les bateaux à placer (PS : on peut avoir plusieur type
     * similaire dans le meme tableau)
     */
    public void placerBateaux(TypeShip[] bateaux) {
        //@todo thomas => verifie juste que c'est bon 
        ShipFactory factory = ShipFactory.getInstance();
        Random generator = new Random();
        List<Coordinate> listCoord = new ArrayList<>();
        Coordinate c1 = null;
        int headColonne = 0;
        int headLigne = 0;
        int taille = this.getMap().getMap()[0].length;
        for (TypeShip s : bateaux) {
            int i = 0;
            while (listCoord.isEmpty() && i < MAX_TRY) {
                headColonne = generator.nextInt(taille);
                headLigne = generator.nextInt(taille);
                c1 = new Coordinate(headLigne, headColonne);
                listCoord = tailsPossible(c1, s);
                i++;
            }
            if (listCoord.isEmpty()) {
                // nothing to do
            } else {
                System.out.println("choix de la 2e coord");
                Coordinate randCoord = listCoord.get(generator.nextInt(listCoord.size()));
                try {
                    addShip(factory.buildShip(s, c1, randCoord));
                } catch (Exception ex) {
                    Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            listCoord.clear();
        }
    }

    public void placerBateaux(List<TypeShip> bateaux) {
        placerBateaux((TypeShip[]) bateaux.toArray());
    }

//    
    private List<Coordinate> tailsPossible(Coordinate head, TypeShip s) {
        int taille = this.getMap().getMap().length;
        List<Coordinate> list = new ArrayList<>();
        boolean ajout = true;
        int l_max, l_min;
        int c_max, c_min;
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                ajout = true;
                /* 1ere condition : bateau en colonne */
                /* 2e condition : bateau en ligne */
                /* 3e condition : bateau en diagonale */
                if (!(((Math.abs(head.y - j) == s.getPuissance() - 1) && (i == head.x))
                        || ((j == head.y) && (Math.abs(head.x - i) == s.getPuissance() - 1))
                        || ((Math.abs(head.y - j) == s.getPuissance() - 1) && (Math.abs(head.x - i) == s.getPuissance() - 1)))) {
                    ajout = false;
                } /* cas bateau en diagonale, teste des croisements possibles */ /*On considère le placement en diagonale impossible */ else if ((head.y != j) && (head.x != i)) {//                                    
                    ajout = false;
                } else {
                    /* cas bateau vertical/horizontal, teste des chevauchements possibles */
                    //On compare les coord de tous les bateaux du joueur
                    for (Ship s1 : this.getFlotte().getVaisseaux()) {
                        for (Etat e : s1.getEtats()) {
                            //Avec toutes les coordonées des placements possibles du bateau
                            if (i > head.x) {
                                l_max = i;
                                l_min = head.x;
                            } else {
                                l_max = head.x;
                                l_min = i;
                            }
                            if (j > head.y) {
                                c_max = j;
                                c_min = head.y;
                            } else {
                                c_max = head.y;
                                c_min = j;
                            }
                            for (int k = l_min; k <= l_max; k++) {
                                for (int l = c_min; l <= c_max; l++) {
                                    /* cas des chevauchements */
                                    if ((e.getC().x == k) && (e.getC().y == l)) {
                                        ajout = false;
                                    }

                                }

                            }
                        }
                    }
                }
            }
        }
    }
}
//                    
//                }
//   
//                if(ajout == true){
//                    list.add(new Coordinate(i,j));
//                }
//            }
//        }
//        return list;
//        
//    }
//    
//    public class structPlacement{
//        protected Coordinate chead;
//        protected Coordinate cqueue;
//        protected TypeShip type;
//        
//        public structPlacement(Coordinate c1, Coordinate c2, TypeShip t){
//            this.chead = c1;
//            this.cqueue = c2;
//            this.type = t;
//        }
//        
//        public Coordinate getCHead(){
//            return this.chead;
//        }
//        
//        public Coordinate getCQueue(){
//            return this.cqueue;
//        }
//        
//        public TypeShip getType(){
//            return this.type;
//        }
//    }

