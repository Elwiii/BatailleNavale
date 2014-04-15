/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Coordinate;
import model.Flotte;
import model.OrdreTir;
import model.VisionBattlefield;
import model.ship.Ship;

/**
 * @todo Rym
 * @author Nikolai
 */
public class CrossBot extends Bot implements Serializable{
    public static final int NOTHING = 0;
    public static final int VERTICAL_HAUT = 1;
    public static final int VERTICAL_BAS = 2;
    public static final int HORIZONTAL_DROITE = 3;
    public static final int HORIZONTAL_GAUCHE = 4;
    protected List<Coordinate> listTirBateau;
    protected List<Coordinate> listTirs;
    protected Coordinate firstCoordinate;
    protected Coordinate lastCoordinate;
    protected int state;
    protected Ship bateauEnCours;
    

    

    public CrossBot(){
        this.nom = "crossBot";
        this.listTirs = new ArrayList<>();
        this.listTirBateau = new ArrayList<>();
        this.state=NOTHING;
    }
    
    @Override
    public int autoFire(VisionBattlefield bf, Flotte target){
         lastCoordinateFired = null; // a modifier
         boolean coord = false;
         int taille = this.map.getMap().length;
         boolean choixTir = false;
         boolean shipAPortee = false;
         //Si on est pas en train de couler un bateau0
         if(listTirBateau.isEmpty()){
             while(coord = false){
                    int x = (int)(Math.random());
                    int y = (int)(Math.random());
                    int l = (int)(Math.random());
                    Coordinate c = new Coordinate(x ,y);
                    firstCoordinate = c;
                    lastCoordinateFired = c;
                    OrdreTir o = new OrdreTir(c, 0);
                    if(bf != null)
                        try {                            
                            return target.fire(target, o);
                        } catch (Exception ex) {
                            Logger.getLogger(RandomBot.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    for(Coordinate co : listTirs){
                        if (co.equals(c)){
                            coord = true;
                            break;
                        }
                    }
             }
         }
         else{
             while(choixTir == false){
                  if (((state==NOTHING)||(state == HORIZONTAL_DROITE))&&(this.lastCoordinateFired.x+1<taille)&&(this.lastCoordinateFired.y<taille)){
                      for(Ship s : this.flotte.getVaisseaux()){
                          if(s.estAporteeDeTir(new Coordinate(this.lastCoordinateFired.x+1,this.lastCoordinateFired.y))){
                              this.fire(new OrdreTir(new Coordinate(this.lastCoordinateFired.x+1,this.lastCoordinateFired.y),flotte.getVaisseaux().indexOf(s)), target);
                              shipAPortee=true;
                              choixTir=true;
                              break;
                          }
                      }
                      if(shipAPortee == false){
                          this.state = HORIZONTAL_GAUCHE;
                      }
                  }
                  else if ((state==HORIZONTAL_GAUCHE)&&(this.lastCoordinate.x-1>=0)){
                      for(Ship s : this.flotte.getVaisseaux()){
                          if(s.estAporteeDeTir(new Coordinate(this.lastCoordinateFired.x-1,this.lastCoordinateFired.y))){
                              this.fire(new OrdreTir(new Coordinate(this.lastCoordinateFired.x-1,this.lastCoordinateFired.y),flotte.getVaisseaux().indexOf(s)), target);
                              shipAPortee=true;
                              choixTir=true;
                              break;
                          }
                      }
                      if(shipAPortee == false){
                          this.state = VERTICAL_BAS;
                      }
                  }
                 

                   
         }
            
    }
            
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        /*List <Ship> list = target.getVaisseaux();
        VisionBattlefield v;
        int totalShip = 0;
        int scoreSrc = Integer.MIN_VALUE;
        
        for(Ship ship : list){ 
            int score = bf.getState(null);
            if(score > scoreSrc){
                scoreSrc = score;
                v = bf;
                return scoreSrc;
            }
        }
        
        VisionBattlefield v2;
        int scoreDst = Integer.MAX_VALUE;
        
        for(Ship nShip : list){
            int score = bf.getState(null);
            if (score < scoreDst){
                scoreDst = score;
                v2 = bf;
                return scoreDst;
            }
                
         }*/
        return 0;
    }
}
