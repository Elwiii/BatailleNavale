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
                    //On déserialise le fichier
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
        File[] listFile = dossier.listFiles();//Liste de tous les fichiers contenus dans le repertoire
        ArrayList<Game> listGame = new ArrayList<>();//liste de Game à retourner
        String nom = "";//utilisé pour le nom du fichier
        String nom_modif="";
        String carAsup=".ser";
        
        for(File f : listFile){
            nom = f.getName();
            nom_modif = nom.replaceAll(carAsup,"");
            
            listGame.add(find(nom_modif));

            
        }
//        System.out.println("taille liste  = "+listGame.size());

        return listGame;
    }
    
    @Override
    public int persiste(Game g) throws FileAlreadyPersisted {
        File dossier = new File("Game");
        
        //Si le dossier n'existe pas, on le crée
        if (!(dossier.exists() && dossier.isDirectory())){
            dossier.mkdir();
        }
        if (new File("Game/"+g.getId()+".ser").exists()){
            throw new FileAlreadyPersisted((g));
        }
        //Création du fichier
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
//        System.out.println("Game/"+g.getId()+".ser");
        File fichier = new File("Game/"+g.getId()+".ser");
        //Si le fichier a déjà été créé, on le supprime
        if(fichier.exists()){
//            System.out.println("Fichier déjà existant : deleting "+fichier+"...");
//            fichier.setWritable(true);
            boolean b = fichier.delete();
//            System.out.println("is delete ? "+b);
        } 
        //On (re)crée le fichier correspondant
//        System.out.println("Recreating "+fichier+" ... ");
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
