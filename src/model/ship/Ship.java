/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ship;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.Coordinate;
import model.Flotte;

/**
 * @author Nikolai
 */
public class Ship implements Serializable, Comparable {

    /**
     * @return the representationGraphique
     */
    public Color getRepresentationGraphique() {
        return representationGraphique;
    }

    public class Etat implements Serializable {

        Coordinate c;

        private int etat;

        protected Etat(Coordinate c, int etat) {
            this.c = c;
            this.etat = etat;
        }

        public Coordinate getC() {
            return c;
        }

        /**
         * @return the etat
         */
        public int getEtat() {
            return etat;
        }

        /**
         * @param etat the etat to set
         */
        public void setEtat(int etat) {
            this.etat = etat;
        }

    }

    public static final int SAFE = 0;
    public static final int DAMAGED = 1;
    //protected URL representationGraphique;
    private Color representationGraphique; // on fait simple
    protected List<Etat> etats;
    protected int puissance;
    protected TypeShip type;

    protected Ship(TypeShip type) {
        this.type = type;
        representationGraphique = type.getRepresentationGraphique();
        puissance = type.getPuissance();
    }

    protected Ship() {

    }

    ;
    
    public TypeShip getType() {
        return type;
    }

    public List<Etat> getEtats() {
        return etats;
    }

    /**
     * evalue si le point de coordonne est atteingable par ce bateau Est
     * fonction de la puissance du bateau (faire un rayon autour de chacun des
     * points du bateau on recupere les coordonnees des points du bateau dans la
     * variable etats
     *
     * @param coordonnee
     * @return
     */
    public boolean estAporteeDeTir(Coordinate coordonnee) {
        for (Etat e : this.etats) {
            if (e.getEtat() == 0) {
                if ((Math.abs(e.c.x - coordonnee.x) <= puissance) && (Math.abs(e.c.y - coordonnee.y) <= puissance)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * return vrai si tout les etats sont endomagé
     *
     * @return
     */
    public boolean isDetroy() {
        //@tester!!
        for (Etat e : this.etats) {
            if (e.getEtat() == SAFE) {
                return false;
            }
        }
        return true;
    }

    public int getLife(){
        int life = 0;
        for (Etat e : this.etats) {
            if (e.getEtat() == SAFE) {
                life++;
            }
        }
        return life;
    }
    
    /**
     * remplir la liste etats en fonction de la position de la tete et la queue
     * la queue est la tete doivent former une ligne ou une diagonale
     *
     * @todotest
     * @param queue
     * @param nez
     */
    protected void initializeEtats(Coordinate queue, Coordinate nez) throws Exception {
        etats = new ArrayList<>();
        int nombreCase = 0;

        int headColonne = nez.x;
        int headLigne = nez.y;
        int tailColonne = queue.x;
        int tailLigne = queue.y;
        int incr_c = 0;
        int incr_l = 0;

        if (headColonne > tailColonne) {
            incr_c = 1;
            if (headLigne > tailLigne) {
                incr_l = 1;
            } else {
                incr_l = -1;
            }
        } else {
            incr_c = -1;
            if (headLigne > tailLigne) {
                incr_l = 1;
            } else {
                incr_l = -1;
            }
        }
        int max_c = Math.abs(tailColonne - headColonne);// == 0 ? 1 : Math.abs(tailColonne - headColonne);
        int max_l = Math.abs(tailLigne - headLigne);// == 0 ? 1 : Math.abs(tailLigne - headLigne);
        if (Math.abs(tailColonne - headColonne) == 0 || Math.abs(tailLigne - headLigne) == 0) {
            for (int c = 0; c <= max_c; c++) {
                for (int l = 0; l <= max_l; l++) {
                    etats.add(new Etat(new Coordinate(tailColonne + incr_c * c, tailLigne + incr_l * l), SAFE));
                    nombreCase++;
                }
            }
        } else {
            for (int c = 0; c <= max_c; c++) {
                etats.add(new Etat(new Coordinate(tailColonne + incr_c * c, tailLigne + incr_l * c), SAFE));
                nombreCase++;
            }
        }

        if (nombreCase != puissance) {
            throw new Exception("le nombre de case doit être égale à la puissance du bateau");
        }
    }

    /**
     * tire sur un flotte Il n'y a que la classe flotte qui peut apeller cette
     * fonction Le bateau doit etre sur de toucher sa cible pour fire.
     *
     * @param target
     * @param coordinate
     */
    public void fire(Flotte target, Coordinate coordinate) throws Exception {
        target.receiveDamage(coordinate);
    }

    /**
     * la reception de dommage par default d'un bateau peut etre override selon
     * le bateau
     *
     * @param coordinate
     */
    public void receivedDamage(Coordinate coordinate) throws Exception {
        boolean stop = false;
        Etat e = null;
        int i = 0;
        while (!stop) {
            e = etats.get(i);
            stop = e.c.equals(coordinate);
            i++;
        }
        if (i <= etats.size()) {
            e.setEtat(DAMAGED);
        } else {
            throw new Exception("le bateau ne possède aucune de ses parties à cette coordonée");
        }
    }
    
    
    @Override
    public String toString() {
        return "" + type;
    }

    @Override
    public int compareTo(Object s) {
        if (this.puissance > ((Ship) s).puissance) {
            return -1;
        } else if (this.puissance < ((Ship) s).puissance) {
            return 1;
        } else {
            return 0;
        }
    }

}
