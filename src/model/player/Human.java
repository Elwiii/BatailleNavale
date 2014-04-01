/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.player;

import model.Flotte;
import model.OrdreTir;

/**
 *
 * @author Nikolai
 */
public class Human extends Player {

    public Human(String nom, int score, int nombrePartieJouees) {
        this.nom = nom;
        this.score = score;
        this.nombrePartieJouees = nombrePartieJouees;
    }

    @Override
    public int fire(OrdreTir order, Flotte target) throws Exception {
        int res = flotte.fire(target, order);
        return res;
    }

}
