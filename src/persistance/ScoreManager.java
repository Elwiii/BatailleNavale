/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nikolai
 */
public class ScoreManager {

    private HashMap<String, Integer> hm;

    private static ScoreManager instance;

    private ScoreManager() {
        FileInputStream fichier = null;
        try {
            fichier = new FileInputStream("score.ser");
            ObjectInputStream ois = new ObjectInputStream(fichier);
            hm = (HashMap) ois.readObject();
            fichier.close();
        } catch (FileNotFoundException ex) {
            hm = new HashMap();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ScoreManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getScoreOf(String pseudo) {
        return hm.get(pseudo);
    }

    public void udpate(String pseudo, int score) {
        if (hm.containsKey(pseudo)) {
            int ancien_score = hm.get(pseudo);
            if (ancien_score < score) {
                hm.put(pseudo, score);
            }
        } else {
            hm.put(pseudo, score);
        }
    }

    public void save() {
        try {
            FileOutputStream fichier = new FileOutputStream("score.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fichier);
            oos.writeObject(hm);
            oos.flush();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static ScoreManager getInstance() {
        if (instance == null) {
            instance = new ScoreManager();
        }
        return instance;
    }

}
