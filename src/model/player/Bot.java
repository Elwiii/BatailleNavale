/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import model.Coordinate;
import model.Flotte;
import model.OrdreTir;
import model.VisionBattlefield;
import model.ship.Ship;
import model.ship.Ship.Etat;
import model.ship.TypeShip;

/**
 * @author Nikolai
 */
public abstract class Bot extends Player implements Serializable{
    
    protected Coordinate lastCoordinateFired ;

    /**
     * 
     * @param bf
     * @param target
     * @return  
     */
    public abstract int autoFire(VisionBattlefield bf, Flotte target);
        
    @Override
    public void updateBattlefield(OrdreTir order,int state){
        map.setState( lastCoordinateFired, state);
    }
    
    /**
     * 
     * @param order
     * @param target 
     * @return  
     */
    @Override
    public int fire(OrdreTir order, Flotte target){
        System.out.println("TIR BOT");
        return autoFire(getMap(),target);
    }
    
    @Override
    public List<structPlacement> placerBateau(Flotte fl){
        Random generator = new Random(); 
        List<Ship> listShip = fl.getVaisseaux();
        Collections.sort(listShip);
        System.out.println("1er Bateau = "+listShip.get(0));
        List<Coordinate> listCoord = new ArrayList<>();
        Coordinate c1 =null;
        List<structPlacement> listStruct= new ArrayList<>();
        int headColonne = 0;
        int headLigne = 0;
        int taille = this.getMap().getMap()[0].length;
        for(Ship s : listShip){
            while(listCoord.isEmpty()){
                headColonne = 0 + generator.nextInt(taille - 0);
                headLigne = 0 + generator.nextInt(taille - 0);
                c1 = new Coordinate(headLigne,headColonne);
                listCoord = tailsPossible(c1, s.getType());
            }
            if(listCoord.isEmpty()){
                System.out.println("list vide");
                return(null);
            }
            else{
                System.out.println("choix de la 2e coord");
                Coordinate randCoord = listCoord.get(0 + generator.nextInt(listCoord.size() - 0));
                structPlacement sp = new structPlacement(c1,randCoord, s.getType());
                listStruct.add(sp);
                //System.out.println("Type = "+s.getType()+" Puissance = "+s.getType().getPuissance()+" Coord head = ("+c1.x+","+c1.y+") Coord Queue =("+randCoord.x+","+randCoord.y+")");
            }
            listCoord.clear();
            if(listCoord.isEmpty()){
                System.out.println("liste vidé");
            }
        }
        return listStruct;
    }
    
    public List<Coordinate> tailsPossible(Coordinate head,TypeShip s){
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
                }
                    /* cas bateau en diagonale, teste des croisements possibles */
                    /*On considère le placement en diagonale impossible */
                else if ((head.y != j) && (head.x != i)) {//                                    
                    ajout = false;                                            
                }
                
                else{
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
   
                if(ajout == true){
                    list.add(new Coordinate(i,j));
                }
            }
        }
        return list;
        
    }
    
    public class structPlacement{
        protected Coordinate chead;
        protected Coordinate cqueue;
        protected TypeShip type;
        
        public structPlacement(Coordinate c1, Coordinate c2, TypeShip t){
            this.chead = c1;
            this.cqueue = c2;
            this.type = t;
        }
        
        public Coordinate getCHead(){
            return this.chead;
        }
        
        public Coordinate getCQueue(){
            return this.cqueue;
        }
        
        public TypeShip getType(){
            return this.type;
        }
    }
    
}
