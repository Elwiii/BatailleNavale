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
//        System.out.println("AVANT premier tir");
        if (this.shipFired == false) {
//            System.out.println("APRES");
            //Il faut choisir une coordonnée non utilisée
            while (tirOK == false) {
                /* choix d'un bateau dans la flotte */
                choixBateau = generator.nextInt(taille);
                Ship s = flotte.getVaisseaux().get(choixBateau);
                /* choix de la coordonnée */
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
                /* on commence à couler un bateau */
                shipFired = true;
                /* on ne connait pas encore sa direction : HORIZONTAL, VERTICAL */
                aDirection = false;
                state = NOTHING;
                comeFrom= NOTHING;
            }
            lastCoordinateFired=coordTir;
//            lastCoordinateFired.y = coordTir.y;
            listTirs.add(coordTir);
            tirOK = false;
            return res;
        } else {
            //On laisse 50 coups à l'ordinateur pour choisir une coordonnée valide
            while (i != 50) {
                System.out.println("On est dans la boucle");
                i++;
                System.out.println("taille = "+taille_grille+" lastCoordinate.x = "+lastCoordinateFired.x+" lastCoordinate.y = "+lastCoordinateFired.y);
                if(this.lastCoordinateFired.y + 1 < taille_grille){
//                    System.out.println("VAS Y RENTRE");
                }
                if (((state == NOTHING) || (state == HORIZONTAL_DROITE)) && (this.lastCoordinateFired.y + 1 < taille_grille)) {
                    coordCalcul = new Coordinate(this.lastCoordinateFired.x, this.lastCoordinateFired.y + 1);
                    System.out.println("coordCalcul.x = "+coordCalcul.x+" coordCalcul.y ="+coordCalcul.y);
                    //Si on a pas déjà utilisé cette coordonnée
                    if (!(listTirs.contains(coordCalcul))) {
                        /* On regarde si on peut atteindre cette coordonnée */
                        for (Ship s : this.flotte.getVaisseaux()) {
                            if (s.estAporteeDeTir(coordCalcul)) {
                                System.out.println("a portee de tir");
                                try {
                                    res = flotte.fire(target,new OrdreTir(coordCalcul, flotte.getVaisseaux().indexOf(s)));
                                    System.out.println("TIR VERTICAL DROITE");
                                    System.out.println("res = "+res);
                                } catch (Exception ex) {
                                    Logger.getLogger(CrossBot.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                /* Cas tir raté ou dépassement de grille */
                                if ((res == MISS) || (coordCalcul.y + 1 >= taille_grille)) {
                                    if(aDirection == false){
                                        this.state = VERTICAL_HAUT;
                                    }
                                    else{
                                        /* On regarde si on a déjà fait l'autre partie du bateau */
                                        if (comeFrom != HORIZONTAL_GAUCHE) {
                                            this.state = HORIZONTAL_GAUCHE;
                                            this.comeFrom = HORIZONTAL_DROITE;
                                        } else {
                                            /* si oui on a certainement coulé le bateau */
                                            /* on retire une coordonnée aléatoire*/ 
                                            this.state = NOTHING;
                                            shipFired = false;
                                            aDirection=false;
                                        }
                                    }
                                } else if (res == HIT) {
                                    /* on connait maintenant la direction du bateau */
                                    aDirection =  true;
                                    state = HORIZONTAL_DROITE;
                                }
                                lastCoordinateFired = coordCalcul;
                                /* la coordonnée étant à portée et utilisée, on l'ajoute à notre liste */
                                listTirs.add(new Coordinate(this.lastCoordinateFired.x, this.lastCoordinateFired.y));
                                return (res);
                            }
                            System.out.println("pas a portée");
                        }
                        System.out.println("sorti de boucle");
                    }
                    System.out.println("DEJA TIRE");
                    for(Coordinate c:listTirs){
                        System.out.println(c.x+" "+c.y);
                    }
                }
                else if(this.lastCoordinateFired.y + 1 >= taille_grille){
                    System.out.println("sorti de grille");
                    state = HORIZONTAL_GAUCHE;
                    comeFrom = HORIZONTAL_DROITE;
                    lastCoordinateFired = firstCoordinate;
                }
                else if ((state == HORIZONTAL_GAUCHE) && (this.lastCoordinateFired.y - 1 >= 0)) {
                    System.out.println("HORIZONTAL_GAUCHE");
                    coordCalcul = new Coordinate(this.lastCoordinateFired.x, this.lastCoordinateFired.y-1);
                    //Si on a pas déjà utilisé cette coordonnée
                    if (!(listTirs.contains(coordCalcul))) {
                        /* On regarde si on peut atteindre cette coordonnée */
                        for (Ship s : this.flotte.getVaisseaux()) {
                            if (s.estAporteeDeTir(coordCalcul)) {
                                try {
                                    res=flotte.fire(target,new OrdreTir(coordCalcul, flotte.getVaisseaux().indexOf(s)));
                                } catch (Exception ex) {
                                    Logger.getLogger(CrossBot.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                /* Cas tir raté ou dépassement de grille */
                                if ((res == MISS) || (coordCalcul.y - 1 < 0)) {
                                    if(aDirection == false){
                                        this.state = VERTICAL_BAS;
                                    }
                                    else{
                                        /* On regarde si on a déjà fait l'autre partie du bateau */
                                        if (comeFrom != HORIZONTAL_DROITE) {
                                            this.state = HORIZONTAL_DROITE;
                                            this.comeFrom = HORIZONTAL_GAUCHE;
                                        } else {
                                            /* si oui on a certainement coulé le bateau */
                                            /* on retire une coordonnée aléatoire*/ 
                                            this.state = NOTHING;
                                            shipFired = false;
                                            aDirection=false;
                                        }
                                    }
                                } else if (res == HIT) {                                    
                                    /* on connait maintenant la direction du bateau */
                                    aDirection =  true;
                                    state = HORIZONTAL_GAUCHE;
                                }
                                lastCoordinateFired = coordCalcul;
                                /* la coordonnée étant à portée et utilisée, on l'ajoute à notre liste */
                                listTirs.add(new Coordinate(this.lastCoordinateFired.x, this.lastCoordinateFired.y));
                                return (res);
                            }
                        }
                    }
                }
                else if(this.lastCoordinateFired.y - 1 < 0){
                    System.out.println("sorti de grille");
                    state = HORIZONTAL_DROITE;
                    comeFrom = HORIZONTAL_GAUCHE;
                    lastCoordinateFired = firstCoordinate;
                }
                else if ((state == VERTICAL_HAUT) && (this.lastCoordinateFired.x - 1 >= 0)) {
                    System.out.println("VERTICAL_HAUT");
                    coordCalcul = new Coordinate(this.lastCoordinateFired.x-1, this.lastCoordinateFired.y);
                    //Si on a pas déjà utilisé cette coordonnée
                    if (!(listTirs.contains(coordCalcul))) {
                        /* On regarde si on peut atteindre cette coordonnée */
                        for (Ship s : this.flotte.getVaisseaux()) {
                            if (s.estAporteeDeTir(coordCalcul)) {
                                try {
                                    res=flotte.fire(target,new OrdreTir(coordCalcul, flotte.getVaisseaux().indexOf(s)));
                                } catch (Exception ex) {
                                    Logger.getLogger(CrossBot.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                /* Cas tir raté ou dépassement de grille */
                                if ((res == MISS) || (coordCalcul.x - 1 < 0)) {
                                    if(aDirection == false){
                                        this.state = HORIZONTAL_GAUCHE;
                                    }
                                    else{
                                        /* On regarde si on a déjà fait l'autre partie du bateau */
                                        if (comeFrom != VERTICAL_BAS) {
                                            this.state = VERTICAL_BAS;
                                            this.comeFrom = VERTICAL_HAUT;
                                        } else {
                                            /* si oui on a certainement coulé le bateau */
                                            /* on retire une coordonnée aléatoire*/ 
                                            this.state = NOTHING;
                                            shipFired = false;
                                            aDirection=false;
                                        }
                                    }
                                } else if (res == HIT) {
                                    /* on connait maintenant la direction du bateau */
                                    aDirection =  true;
                                    /* on continue à tirer vers le haut */
                                    state = VERTICAL_HAUT;
                                }
                                lastCoordinateFired = coordCalcul;
                                /* la coordonnée étant à portée et utilisée, on l'ajoute à notre liste */
                                listTirs.add(new Coordinate(this.lastCoordinateFired.x, this.lastCoordinateFired.y));
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
                    System.out.println("VERTICAL_BAS");
                    coordCalcul = new Coordinate(this.lastCoordinateFired.x + 1, this.lastCoordinateFired.y);
                    //Si on a pas déjà utilisé cette coordonnée
                    if (!(listTirs.contains(coordCalcul))) {
                        /* On regarde si on peut atteindre cette coordonnée */
                        for (Ship s : this.flotte.getVaisseaux()) {
                            if (s.estAporteeDeTir(coordCalcul)) {
                                try {
                                    res=flotte.fire(target,new OrdreTir(coordCalcul, flotte.getVaisseaux().indexOf(s)));
                                } catch (Exception ex) {
                                    Logger.getLogger(CrossBot.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                /* Cas tir raté ou dépassement de grille */
                                if ((res == MISS) || (coordCalcul.x + 1 >= taille_grille)) {
                                    if(aDirection == false){
                                        this.state = HORIZONTAL_DROITE;
                                    }
                                    else{
                                        /* On regarde si on a déjà fait l'autre partie du bateau */
                                        if (comeFrom != VERTICAL_HAUT) {
                                            this.state = VERTICAL_HAUT;
                                            this.comeFrom = VERTICAL_BAS;
                                        } else {
                                            /* si oui on a certainement coulé le bateau */
                                            /* on retire une coordonnée aléatoire*/ 
                                            this.state = NOTHING;
                                            shipFired = false;
                                            aDirection=false;
                                        }
                                    }
                                } else if (res == HIT) {
                                    /* on connait maintenant la direction du bateau */
                                    aDirection =  true;
                                    /* on continue à tirer vers le bas */
                                    state = VERTICAL_BAS;
                                }
                                //on ajout la coordonnée peu importe si MISS ou HIT
                                lastCoordinateFired = coordCalcul;
                                /* la coordonnée étant à portée et utilisée, on l'ajoute à notre liste */
                                listTirs.add(new Coordinate(this.lastCoordinateFired.x, this.lastCoordinateFired.y));
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
            /* les 50coups n'ont pas suffit, les coordonnées ne sont donc pas atteignables */
            /* l'ordinateur tirera à nouveau une coordonnée aléatoire au prochaine tour */
            /* on réinitialise les données de direction et de tir sur un bateau */
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

//    @Override
//    public StateCase autoFire(VisionBattlefield bf, Flotte target) {
//        boolean coord = false;
//        int taille = this.map.getMap().length;
//        boolean choixTir = false;
//        boolean shipAPortee = false;
//        boolean tirSurBateau = false;
//        StateCase res = ERROR;
//        Coordinate coordCalcul = null;
//        Coordinate c = null;
//
//        //Si on a coulé le bateau qu'on visait, on remet tout à zero
//        if ((this.bateauEnCours != null) && (this.bateauEnCours.isDetroy())) {
//            bateauEnCours = null;
//            this.shipFired = false;
//        }
//        //Si on est pas en train de couler un bateau
//        if (this.shipFired == false) {
//            //Tant qu'on a pas une nouvelle coordonnée
//            while (coord = false) {
//                int x = (int) (Math.random());
//                int y = (int) (Math.random());
//                int l = (int) (Math.random());
//                c = new Coordinate(x, y);
//                if (!(listTirs.contains(c))) {
//                    coord = true;
//                }
//            }
//            coord = false;
//            firstCoordinate = c;
//            lastCoordinateFired = c;
//            OrdreTir o = new OrdreTir(c, 0);
//            if (bf != null) {
//                try {
//                    res = target.fire(target, o);
//                    if (res == HIT) {
//                        this.shipFired = true;
//                        for (Ship bat : target.getVaisseaux()) {
//                            for (Etat e : bat.getEtats()) {
//                                if (e.getC().equals(c)) {
//                                    this.bateauEnCours = bat;
//                                }
//                            }
//                        }
//                    }
//                    listTirs.add(c);
//                    return res;
//                } catch (Exception ex) {
//                    Logger.getLogger(RandomBot.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//            for (Coordinate co : listTirs) {
//                if (co.equals(c)) {
//                    coord = true;
//                    break;
//                }
//            }
//
//        } else {
//            while (choixTir == false) {
//                if (((state == NOTHING) || (state == HORIZONTAL_DROITE)) && (this.lastCoordinateFired.x + 1 < taille) && (this.lastCoordinateFired.y < taille)) {
//                    coordCalcul = new Coordinate(this.lastCoordinateFired.x + 1, this.lastCoordinateFired.y);
//                    for (Ship s : this.flotte.getVaisseaux()) {
//                        if (s.estAporteeDeTir(coordCalcul)) {
//                            if (!(listTirs.contains(coordCalcul))) {
//                                res = this.fire(new OrdreTir(coordCalcul, flotte.getVaisseaux().indexOf(s)), target);
//                                if (res == MISS) {
//                                    this.state = HORIZONTAL_GAUCHE;
//                                    this.lastCoordinateFired = this.firstCoordinate;
//                                }
//                                listTirs.add(new Coordinate(this.lastCoordinateFired.x + 1, this.lastCoordinateFired.y));
//                                return (res);
//                            }
//                        }
//                    }
//                } else if ((state == HORIZONTAL_GAUCHE) && (this.lastCoordinateFired.x - 1 >= 0)) {
//                    coordCalcul = new Coordinate(this.lastCoordinateFired.x + 1, this.lastCoordinateFired.y);
//                    for (Ship s : this.flotte.getVaisseaux()) {
//                        if (s.estAporteeDeTir(coordCalcul)) {
//                            if (!(listTirs.contains(coordCalcul))) {
//                                res = this.fire(new OrdreTir(coordCalcul, flotte.getVaisseaux().indexOf(s)), target);
//                                if (res == MISS) {
//                                    this.state = HORIZONTAL_DROITE;
//                                    this.lastCoordinateFired = this.firstCoordinate;
//                                }
//                                return (res);
//                            }
//
//                        }
//                    }
//                } else if ((state == VERTICAL_HAUT) && (this.lastCoordinateFired.y - 1 >= 0)) {
//                    coordCalcul = new Coordinate(this.lastCoordinateFired.x, this.lastCoordinateFired.y - 1);
//                    for (Ship s : this.flotte.getVaisseaux()) {
//                        if (s.estAporteeDeTir(coordCalcul)) {
//                            if (!(listTirs.contains(coordCalcul))) {
//                                res = this.fire(new OrdreTir(coordCalcul, flotte.getVaisseaux().indexOf(s)), target);
//                                if (res == MISS) {
//                                    this.state = VERTICAL_BAS;
//                                    this.lastCoordinateFired = this.firstCoordinate;
//                                }
//                                return (res);
//                            }
//
//                        }
//                    }
//                } else if ((state == VERTICAL_BAS) && (this.lastCoordinateFired.y + 1 < taille)) {
//                    coordCalcul = new Coordinate(this.lastCoordinateFired.x, this.lastCoordinateFired.y + 1);
//                    for (Ship s : this.flotte.getVaisseaux()) {
//                        if (s.estAporteeDeTir(coordCalcul)) {
//                            if (!(listTirs.contains(coordCalcul))) {
//                                res = this.fire(new OrdreTir(coordCalcul, flotte.getVaisseaux().indexOf(s)), target);
//                                if (res == MISS) {
//                                    this.state = VERTICAL_HAUT;
//                                    this.lastCoordinateFired = this.firstCoordinate;
//                                }
//                                return (res);
//                            }
//
//                        }
//                    }
//                }
//
//            }
//
//        }
//        return res;
//    }
//}
