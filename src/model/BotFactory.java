/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.player.Bot;
import model.player.CapitaineThomas;
import model.player.CrossBot;
import model.player.Difficulty;
import model.player.Poseidon;
import model.player.RandomBot;

/**
 *
 * @author Nikolai
 */
public class BotFactory {

    private static BotFactory instance;

    private BotFactory() {
    }

    public static BotFactory getInstance() {
        if (instance == null) {
            instance = new BotFactory();
        }
        return instance;
    }

    public Bot getBot(Difficulty difficulty) throws Exception {
        switch (difficulty) {
            case CROSSBOT:
                return new CrossBot();
            case RANDOMBOT:
                return new RandomBot();
            case CAPTAIN_THOMAS:
                return new CapitaineThomas();
            case POSEIDON:
                return new Poseidon();
            default :
                throw new Exception("difficultée inconnue");
        }
    }

}
