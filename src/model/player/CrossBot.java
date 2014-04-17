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
import model.StateCase;
import static model.StateCase.ERROR;
import static model.StateCase.HIT;
import static model.StateCase.MISS;
import model.Flotte;
import model.OrdreTir;
import model.VisionBattlefield;
import model.ship.Ship;
import model.ship.Ship.Etat;

/**
 * @todo Rym
 * @author Nikolai
 */
public class CrossBot extends Bot implements Serializable {

    public static final int NOTHING = 0;
    public static final int VERTICAL_HAUT = 1;
    public static final int VERTICAL_BAS = 2;
    public static final int HORIZONTAL_DROITE = 3;
    public static final int HORIZONTAL_GAUCHE = 4;
    protected List<Coordinate> listTirs;
    protected Coordinate firstCoordinate;
    protected int state;
    protected Ship bateauEnCours;
    protected boolean shipFired;

    public CrossBot() {
        this.nom = "crossBot";
        this.listTirs = new ArrayList<>();
        this.state = NOTHING;
        this.firstCoordinate = null;
        this.lastCoordinateFired = null;
        this.shipFired = false;
        this.bateauEnCours = null;
    }

    @Override
    public StateCase autoFire(VisionBattlefield bf, Flotte target) {
        boolean coord = false;
        int taille = this.map.getMap().length;
        boolean choixTir = false;
        boolean shipAPortee = false;
        boolean tirSurBateau = false;
        StateCase res = ERROR;
        Coordinate coordCalcul = null;
        Coordinate c = null;

        //Si on a coulé le bateau qu'on visait, on remet tout à zero
        if ((this.bateauEnCours != null) && (this.bateauEnCours.isDetroy())) {
            bateauEnCours = null;
            this.shipFired = false;
        }
        //Si on est pas en train de couler un bateau
        if (this.shipFired == false) {
            //Tant qu'on a pas une nouvelle coordonnée
            while (coord = false) {
                int x = (int) (Math.random());
                int y = (int) (Math.random());
                int l = (int) (Math.random());
                c = new Coordinate(x, y);
                if (!(listTirs.contains(c))) {
                    coord = true;
                }
            }
            coord = false;
            firstCoordinate = c;
            lastCoordinateFired = c;
            OrdreTir o = new OrdreTir(c, 0);
            if (bf != null) {
                try {
                    res = target.fire(target, o);
                    if (res == HIT) {
                        this.shipFired = true;
                        for (Ship bat : target.getVaisseaux()) {
                            for (Etat e : bat.getEtats()) {
                                if (e.getC().equals(c)) {
                                    this.bateauEnCours = bat;
                                }
                            }
                        }
                    }
                    listTirs.add(c);
                    return res;
                } catch (Exception ex) {
                    Logger.getLogger(RandomBot.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            for (Coordinate co : listTirs) {
                if (co.equals(c)) {
                    coord = true;
                    break;
                }
            }

        } else {
            while (choixTir == false) {
                if (((state == NOTHING) || (state == HORIZONTAL_DROITE)) && (this.lastCoordinateFired.x + 1 < taille) && (this.lastCoordinateFired.y < taille)) {
                    coordCalcul = new Coordinate(this.lastCoordinateFired.x + 1, this.lastCoordinateFired.y);
                    for (Ship s : this.flotte.getVaisseaux()) {
                        if (s.estAporteeDeTir(coordCalcul)) {
                            if (!(listTirs.contains(coordCalcul))) {
                                res = this.fire(new OrdreTir(coordCalcul, flotte.getVaisseaux().indexOf(s)), target);
                                if (res == MISS) {
                                    this.state = HORIZONTAL_GAUCHE;
                                    this.lastCoordinateFired = this.firstCoordinate;
                                }
                                listTirs.add(new Coordinate(this.lastCoordinateFired.x + 1, this.lastCoordinateFired.y));
                                return (res);
                            }
                        }
                    }
                } else if ((state == HORIZONTAL_GAUCHE) && (this.lastCoordinateFired.x - 1 >= 0)) {
                    coordCalcul = new Coordinate(this.lastCoordinateFired.x + 1, this.lastCoordinateFired.y);
                    for (Ship s : this.flotte.getVaisseaux()) {
                        if (s.estAporteeDeTir(coordCalcul)) {
                            if (!(listTirs.contains(coordCalcul))) {
                                res = this.fire(new OrdreTir(coordCalcul, flotte.getVaisseaux().indexOf(s)), target);
                                if (res == MISS) {
                                    this.state = HORIZONTAL_DROITE;
                                    this.lastCoordinateFired = this.firstCoordinate;
                                }
                                return (res);
                            }

                        }
                    }
                } else if ((state == VERTICAL_HAUT) && (this.lastCoordinateFired.y - 1 >= 0)) {
                    coordCalcul = new Coordinate(this.lastCoordinateFired.x, this.lastCoordinateFired.y - 1);
                    for (Ship s : this.flotte.getVaisseaux()) {
                        if (s.estAporteeDeTir(coordCalcul)) {
                            if (!(listTirs.contains(coordCalcul))) {
                                res = this.fire(new OrdreTir(coordCalcul, flotte.getVaisseaux().indexOf(s)), target);
                                if (res == MISS) {
                                    this.state = VERTICAL_BAS;
                                    this.lastCoordinateFired = this.firstCoordinate;
                                }
                                return (res);
                            }

                        }
                    }
                } else if ((state == VERTICAL_BAS) && (this.lastCoordinateFired.y + 1 < taille)) {
                    coordCalcul = new Coordinate(this.lastCoordinateFired.x, this.lastCoordinateFired.y + 1);
                    for (Ship s : this.flotte.getVaisseaux()) {
                        if (s.estAporteeDeTir(coordCalcul)) {
                            if (!(listTirs.contains(coordCalcul))) {
                                res = this.fire(new OrdreTir(coordCalcul, flotte.getVaisseaux().indexOf(s)), target);
                                if (res == MISS) {
                                    this.state = VERTICAL_HAUT;
                                    this.lastCoordinateFired = this.firstCoordinate;
                                }
                                return (res);
                            }

                        }
                    }
                }

            }

        }
        return res;
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
