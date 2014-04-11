/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import model.player.Player;

/**
 * version sauvergardable du model
 * @author Nikolai
 */
public class Game implements Serializable{
    final String id;
    final Player j1;
    final Player j2;
    final int score;
    final State state; 
    
    public Game(BatailleNavale bn){
        this.id = bn.getId();
        this.j1 = bn.getJ1();
        this.j2 = bn.getJ2();
        this.score = bn.getScore();
        this.state = bn.getState();
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Game{" + "id=" + id + ", j1=" + j1.getNom() + ", j2=" + j2.getNom() + ", score=" + score + ", state=" + state + '}';
    }
    

}
