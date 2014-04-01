/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import model.ship.Ship;

/**
 * L'ensemble des bateau d'un joueur
 *
 * @author nikolai
 */
public class Flotte {

    public static final int UNREACHABLE = 0;
    public static final int HIT = 1;
    public static final int MISS = 2;
    public static final int FLOTTE_DETRUITE = 3;
    private final List<Ship> vaisseaux;

    public Flotte() {
        vaisseaux = new ArrayList<>();
    }

    /**
     * tire sur une flotte adverse
     *
     * @param target
     * @param order
     * @return UNREACHABLE HIT ou MISS
     * @throws java.lang.Exception
     */
    public int fire(Flotte target, OrdreTir order) throws Exception {
        Ship launcher = vaisseaux.get(order.getLauncher());
        if (launcher.estAporteeDeTir(order.getCoordinate())) {
            return target.receiveDamage(order.getCoordinate());
        } else {
            return UNREACHABLE;
        }
    }

    /**
     *
     * @param ship
     */
    public void addShip(Ship ship) {
        vaisseaux.add(ship);
    }

    public void removeShip(int idShip) {
        vaisseaux.remove(idShip);
    }

    /**
     *
     * @param coordinate
     * @return HIT si un des bateaux dla flotte est touché, MISS sinon
     * @throws java.lang.Exception
     */
    public int receiveDamage(Coordinate coordinate) throws Exception {
        Ship shiphit = getShipHit(coordinate);
        if (shiphit != null) {
            shiphit.receivedDamage(coordinate);
            if (shiphit.isDetroy()) {
                vaisseaux.remove(shiphit);
            }
            if (vaisseaux.isEmpty()) {
                return FLOTTE_DETRUITE;
            } else {
                return HIT;
            }
        } else {
            return MISS;
        }
    }

    /**
     * return le bateau touché si la coordonée correspond à une de ses parties
     * return null si aucun bateau n'est atteint
     *
     * @param coordinate
     * @return
     */
    private Ship getShipHit(Coordinate coordinate) {
        //@todo Thomas
        return null;
    }

}
