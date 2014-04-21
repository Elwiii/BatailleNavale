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
import model.StateCase;
import static model.StateCase.ERROR;
import static model.StateCase.HIT;
import static model.StateCase.MISS;
import model.Flotte;
import model.OrdreTir;
import static model.StateCase.UNREACHABLE;
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
    protected int comeFrom;
    protected boolean aDirection = false;
    protected boolean shipFired;

    public CrossBot() {
        this.nom = "crossBot";
        this.listTirs = new ArrayList<>();
        this.state = NOTHING;
        this.firstCoordinate = null;
        this.lastCoordinateFired = null;
        this.shipFired = false;
    }


    @Override
    public StateCase autoFire(VisionBattlefield bf, Flotte target) {
        int taille = flotte.getVaisseaux().size();
        int taille_grille = bf.getHauteur();
        List<Coordinate> tailsPossibles = null;
        Random generator = new Random();
        int choixCoordTir = 0;
        Coordinate coordTir = null;
        StateCase res = null;
        boolean tirOK = false;
        Coordinate coordCalcul = null;
        int i = 0;

        int choixBateau = 0;
        //Si on est pas en train de couler un bateau
        if (this.shipFired == false) {
            //Il faut choisir une coordonnee non utilisee
            while (tirOK == false) {
                /* choix d'un bateau dans la flotte */
                choixBateau = generator.nextInt(taille);
                Ship s = flotte.getVaisseaux().get(choixBateau);
                /* choix de la coordonnee */
                tailsPossibles = tailsPossibles(s);
                choixCoordTir = generator.nextInt(tailsPossibles.size());
                coordTir = tailsPossibles.get(choixCoordTir);
                if (!(listTirs.contains(coordTir))) {
                    try {
                        res = flotte.fire(target,new OrdreTir(coordTir, choixBateau));
                    } catch (Exception ex) {
                        Logger.getLogger(CrossBot.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    tirOK = true;
                }
            }
            if (res == HIT) {
                firstCoordinate = coordTir;
                /* on commence a couler un bateau */
                shipFired = true;
                /* on ne connait pas encore sa direction : HORIZONTAL, VERTICAL */
                aDirection = false;
                state = NOTHING;
                comeFrom= NOTHING;
            }
            lastCoordinateFired=coordTir;
            listTirs.add(coordTir);
            tirOK = false;
            return res;
        } else {
            //On laisse 50 coups a l'ordinateur pour choisir une coordonnee valide
            while (i != 50) {
                i++;
                if(this.lastCoordinateFired.y + 1 < taille_grille){
                }
                if (((state == NOTHING) || (state == HORIZONTAL_DROITE)) && (this.lastCoordinateFired.y + 1 < taille_grille)) {
                    coordCalcul = new Coordinate(this.lastCoordinateFired.x, this.lastCoordinateFired.y + 1);
                    //Si on a pas deja utilise cette coordonnee
                    if (!(listTirs.contains(coordCalcul))) {
                        /* On regarde si on peut atteindre cette coordonnee */
                        for (Ship s : this.flotte.getVaisseaux()) {
                            if (s.estAporteeDeTir(coordCalcul)) {
                                try {
                                    res = flotte.fire(target,new OrdreTir(coordCalcul, flotte.getVaisseaux().indexOf(s)));
                                } catch (Exception ex) {
                                    Logger.getLogger(CrossBot.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                /* Cas tir rate ou depassement de grille */
                                if ((res == MISS) || (coordCalcul.y + 1 >= taille_grille)) {
                                    if(aDirection == false){
                                        this.state = VERTICAL_HAUT;
                                    }
                                    else{
                                        /* On regarde si on a deja fait l'autre partie du bateau */
                                        if (comeFrom != HORIZONTAL_GAUCHE) {
                                            this.state = HORIZONTAL_GAUCHE;
                                            this.comeFrom = HORIZONTAL_DROITE;
                                            this.lastCoordinateFired = this.firstCoordinate;
                                        } else {
                                            /* si oui on a certainement coule le bateau */
                                            /* on retire une coordonnee aleatoire*/ 
                                            this.state = NOTHING;
                                            shipFired = false;
                                            aDirection=false;
                                            lastCoordinateFired = coordCalcul;
                                        }
                                    }
                                } else if (res == HIT) {
                                    /* on connait maintenant la direction du bateau */
                                    aDirection =  true;
                                    state = HORIZONTAL_DROITE;
                                    lastCoordinateFired = coordCalcul;
                                }
                                /* la coordonnee etant a portee et utilisee, on l'ajoute a notre liste */
                                listTirs.add(new Coordinate(coordCalcul.x, coordCalcul.y));
                                return (res);
                            }
                        }
                    }
                }
                else if(this.lastCoordinateFired.y + 1 >= taille_grille){
                    state = HORIZONTAL_GAUCHE;
                    comeFrom = HORIZONTAL_DROITE;
                    lastCoordinateFired = firstCoordinate;
                }
                else if ((state == HORIZONTAL_GAUCHE) && (this.lastCoordinateFired.y - 1 >= 0)) {
                    coordCalcul = new Coordinate(this.lastCoordinateFired.x, this.lastCoordinateFired.y-1);
                    //Si on a pas deja utilise cette coordonnee
                    if (!(listTirs.contains(coordCalcul))) {
                        /* On regarde si on peut atteindre cette coordonnee */
                        for (Ship s : this.flotte.getVaisseaux()) {
                            if (s.estAporteeDeTir(coordCalcul)) {
                                try {
                                    res=flotte.fire(target,new OrdreTir(coordCalcul, flotte.getVaisseaux().indexOf(s)));
                                } catch (Exception ex) {
                                    Logger.getLogger(CrossBot.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                /* Cas tir rate ou depassement de grille */
                                if ((res == MISS) || (coordCalcul.y - 1 < 0)) {
                                    if(aDirection == false){
                                        this.state = VERTICAL_BAS;
                                    }
                                    else{
                                        /* On regarde si on a deja fait l'autre partie du bateau */
                                        if (comeFrom != HORIZONTAL_DROITE) {
                                            this.state = HORIZONTAL_DROITE;
                                            this.comeFrom = HORIZONTAL_GAUCHE;
                                            this.lastCoordinateFired = this.firstCoordinate;
                                        } else {
                                            /* si oui on a certainement coule le bateau */
                                            /* on retire une coordonnee aleatoire*/ 
                                            this.state = NOTHING;
                                            shipFired = false;
                                            aDirection=false;
                                            lastCoordinateFired = coordCalcul;
                                        }
                                    }
                                } else if (res == HIT) {                                    
                                    /* on connait maintenant la direction du bateau */
                                    aDirection =  true;
                                    state = HORIZONTAL_GAUCHE;
                                    lastCoordinateFired = coordCalcul;
                                }
                                /* la coordonnee etant a portee et utilisee, on l'ajoute a notre liste */
                                listTirs.add(new Coordinate(coordCalcul.x, coordCalcul.y));
                                return (res);
                            }
                        }
                    }
                }
                else if(this.lastCoordinateFired.y - 1 < 0){
                    state = HORIZONTAL_DROITE;
                    comeFrom = HORIZONTAL_GAUCHE;
                    lastCoordinateFired = firstCoordinate;
                }
                else if ((state == VERTICAL_HAUT) && (this.lastCoordinateFired.x - 1 >= 0)) {
                    coordCalcul = new Coordinate(this.lastCoordinateFired.x-1, this.lastCoordinateFired.y);
                    //Si on a pas deja utilise cette coordonnee
                    if (!(listTirs.contains(coordCalcul))) {
                        /* On regarde si on peut atteindre cette coordonnee */
                        for (Ship s : this.flotte.getVaisseaux()) {
                            if (s.estAporteeDeTir(coordCalcul)) {
                                try {
                                    res=flotte.fire(target,new OrdreTir(coordCalcul, flotte.getVaisseaux().indexOf(s)));
                                } catch (Exception ex) {
                                    Logger.getLogger(CrossBot.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                /* Cas tir rate ou depassement de grille */
                                if ((res == MISS) || (coordCalcul.x - 1 < 0)) {
                                    if(aDirection == false){
                                        this.state = HORIZONTAL_GAUCHE;
                                    }
                                    else{
                                        /* On regarde si on a deja fait l'autre partie du bateau */
                                        if (comeFrom != VERTICAL_BAS) {
                                            this.state = VERTICAL_BAS;
                                            this.comeFrom = VERTICAL_HAUT;
                                            this.lastCoordinateFired = this.firstCoordinate;
                                        } else {
                                            /* si oui on a certainement coule le bateau */
                                            /* on retire une coordonnee aleatoire*/ 
                                            this.state = NOTHING;
                                            shipFired = false;
                                            aDirection=false;
                                            lastCoordinateFired = coordCalcul;
                                        }
                                    }
                                } else if (res == HIT) {
                                    /* on connait maintenant la direction du bateau */
                                    aDirection =  true;
                                    /* on continue a tirer vers le haut */
                                    state = VERTICAL_HAUT;
                                    lastCoordinateFired = coordCalcul;
                                }
                                /* la coordonnee etant a portee et utilisee, on l'ajoute a notre liste */
                                listTirs.add(new Coordinate(coordCalcul.x, coordCalcul.y));
                                return (res);
                            }
                        }
                    }
                }
                else if(this.lastCoordinateFired.x - 1 < 0){
                    state = VERTICAL_BAS;
                    comeFrom = VERTICAL_HAUT;
                    lastCoordinateFired = firstCoordinate;
                }
                else if ((state == VERTICAL_BAS) && (this.lastCoordinateFired.x + 1 < taille_grille)) {
                    coordCalcul = new Coordinate(this.lastCoordinateFired.x + 1, this.lastCoordinateFired.y);
                    //Si on a pas deja utilise cette coordonnee
                    if (!(listTirs.contains(coordCalcul))) {
                        /* On regarde si on peut atteindre cette coordonnee */
                        for (Ship s : this.flotte.getVaisseaux()) {
                            if (s.estAporteeDeTir(coordCalcul)) {
                                try {
                                    res=flotte.fire(target,new OrdreTir(coordCalcul, flotte.getVaisseaux().indexOf(s)));
                                } catch (Exception ex) {
                                    Logger.getLogger(CrossBot.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                /* Cas tir rate ou depassement de grille */
                                if ((res == MISS) || (coordCalcul.x + 1 >= taille_grille)) {
                                    if(aDirection == false){
                                        this.state = HORIZONTAL_DROITE;
                                    }
                                    else{
                                        /* On regarde si on a deja fait l'autre partie du bateau */
                                        if (comeFrom != VERTICAL_HAUT) {
                                            this.state = VERTICAL_HAUT;
                                            this.comeFrom = VERTICAL_BAS;
                                            this.lastCoordinateFired = this.firstCoordinate;
                                        } else {
                                            /* si oui on a certainement coule le bateau */
                                            /* on retire une coordonnee aleatoire*/ 
                                            this.state = NOTHING;
                                            shipFired = false;
                                            aDirection=false;
                                            lastCoordinateFired = coordCalcul;
                                            
                                        }
                                    }
                                } else if (res == HIT) {
                                    /* on connait maintenant la direction du bateau */
                                    aDirection =  true;
                                    /* on continue a tirer vers le bas */
                                    state = VERTICAL_BAS;
                                    lastCoordinateFired = coordCalcul;
                                }
                                //on ajout la coordonnee peu importe si MISS ou HIT
                                /* la coordonnee etant a portee et utilisee, on l'ajoute a notre liste */
                                listTirs.add(new Coordinate(coordCalcul.x, coordCalcul.y));
                                return (res);
                            }
                        }
                    }
                }
                else if(this.lastCoordinateFired.x + 1 < taille_grille){
                    state = VERTICAL_HAUT;
                    comeFrom = VERTICAL_BAS;
                    lastCoordinateFired = firstCoordinate;
                }
            }
            /* les 50coups n'ont pas suffit, les coordonnees ne sont donc pas atteignables */
            /* l'ordinateur tirera a nouveau une coordonnee aleatoire au prochaine tour */
            /* on reinitialise les donnees de direction et de tir sur un bateau */
            state = NOTHING;
            aDirection = false;
            shipFired = false;
            return(UNREACHABLE);
        }
    }

    protected List<Coordinate> tailsPossibles(Ship s) {
        List<Coordinate> tirsPossibles = new ArrayList<>();
        for (int i = 0; i < this.getMap().getLargeur(); i++) {
            for (int j = 0; j < this.getMap().getHauteur(); j++) {
                if (s.estAporteeDeTir(new Coordinate(i, j))) {
                    tirsPossibles.add(new Coordinate(i, j));
                }
            }
        }
        return tirsPossibles;
    }
}