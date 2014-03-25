/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistance.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import model.Game;

/**
 *
 * @author Thomas
 */
public class DaoFileGame extends DaoFile{

    @Override
    public Game find(String id){
        File dossier = new File("Game");
        //On teste si le dossier existe
        if (dossier.exists() && dossier.isDirectory()){
            //On teste si le fichier existe
            File fich = new File(id);
            if(fich.exists()){
                try {
                    //On déserialise le fichier
                    FileInputStream fichier = new FileInputStream(id);
                    ObjectInputStream ois = new ObjectInputStream(fichier);
                    Game game = (Game) ois.readObject();
                    return game;
                  } 
                  catch (java.io.IOException e) {
                    e.printStackTrace();
                  }
                  catch (ClassNotFoundException e) {
                    e.printStackTrace();
                  }
            }
        }
        return null;
    }
    
    @Override
    public ArrayList<Game> find(){
        File dossier = new File ("Game/");//Repertoire des fichiers
        File[] listFile = dossier.listFiles();//Liste de tous les fichiers contenus dans le repertoire
        ArrayList<Game> listGame = new ArrayList<>();//liste de Game à retourner
        String nom;//utilisé pour le nom du fichier
        
        for(File f : listFile){
            nom = f.getName();
            listGame.add(find(nom));
        }
        return listGame;
    }
    
    @Override
    public int persiste(Game g) throws FileNotFoundException, IOException {
        FileOutputStream fichier = new FileOutputStream(g.getId());
        try (ObjectOutputStream oos = new ObjectOutputStream(fichier)) {
            oos.writeObject(g);
            oos.flush();
        }
        
        return 0;
    }


    @Override
    public void update(Game g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
