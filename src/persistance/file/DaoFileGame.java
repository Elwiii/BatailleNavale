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
            File fich = new File("Game/"+id+".ser");
            if(fich.exists()){
                try {
                    //On deserialise le fichier
                    FileInputStream fichier = new FileInputStream("Game/"+id+".ser");
                    ObjectInputStream ois = new ObjectInputStream(fichier);
                    Game game = (Game) ois.readObject();
                    ois.close();
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
        ArrayList<Game> listGame = new ArrayList<>();//liste de Game a retourner
        if(dossier.exists()){
            File[] listFile = dossier.listFiles();//Liste de tous les fichiers contenus dans le repertoire
            String nom = "";//utilise pour le nom du fichier
            String nom_modif="";
            String carAsup=".ser";

            for(File f : listFile){
                nom = f.getName();
                nom_modif = nom.replaceAll(carAsup,"");

                listGame.add(find(nom_modif));


            }
        }
        return listGame;
    }
    
    @Override
    public int persiste(Game g) throws FileAlreadyPersisted {
        File dossier = new File("Game");
        
        //Si le dossier n'existe pas, on le cree
        if (!(dossier.exists() /*&& dossier.isDirectory()*/)){
            dossier.mkdir();
        }
        if (new File("Game/"+g.getId()+".ser").exists()){
            throw new FileAlreadyPersisted((g));
        }
        //Creation du fichier
        try{
            FileOutputStream fichier = new FileOutputStream("Game/"+g.getId()+".ser");
            ObjectOutputStream oos = new ObjectOutputStream(fichier);
            oos.writeObject(g);
            oos.flush();
            oos.close();
        } 
        catch (java.io.IOException e) {
          e.printStackTrace();
        }                
        return 0;
    }


    @Override
    public void update(Game g) throws FileAlreadyPersisted {
        File fichier = new File("Game/"+g.getId()+".ser");
        //Si le fichier a deja ete cree, on le supprime
        if(fichier.exists()){
            boolean b = fichier.delete();
        } 
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
