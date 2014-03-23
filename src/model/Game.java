/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import model.player.Player;
import model.player.State;

/**
 * @todo
 * @author Nikolai
 */
public class Game implements Serializable{
    private Player j1;
    private Player j2;
    private int score;
    private State state; //Le type n'est peut etre pas le bon
    
    //attention pas en accord avec le DC
    public Game(Player j1, Player j2, int score, State state){
        this.j1 = j1;
        this.j2 = j2;
        this.score = score;
        this.state = state;
    }
}
