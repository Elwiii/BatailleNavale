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
import persistance.Dao;
import persistance.DaoGame;

/**
 *
 * @author Thomas
 */
public class DaoFileGame extends DaoFile<Game> implements DaoGame{
    
    /** Singleton **/
    private DaoFileGame(){}
    
    /** Instance unique non préinitialisée */
    private static DaoFileGame INSTANCE = null;

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
    public int persiste(Game g) {
        File dossier = new File("Game");
        //Si le dossier n'existe pas, on le crée
        if (!(dossier.exists() && dossier.isDirectory())){
            dossier.mkdir();
        }
        //Création du fichier
        try{
            FileOutputStream fichier = new FileOutputStream(g.getId());
            ObjectOutputStream oos = new ObjectOutputStream(fichier);
            oos.writeObject(g);
            oos.flush();
        } 
        catch (java.io.IOException e) {
          e.printStackTrace();
        }                
        return 0;
    }


    @Override
    public void update(Game g) {
        File fichier = new File("Game/"+g.getId());
        //Si le fichier a déjà été créé, on le supprime
        if(fichier.exists()){
            fichier.delete();
        } 
        //On (re)crée le fichier correspondant
        persiste(g);    
    }

    //@Override
    public static Dao getInstance()
    {			
        if (INSTANCE == null)
        { 	INSTANCE = new DaoFileGame();	
        }
        return INSTANCE;
    }
    
}
