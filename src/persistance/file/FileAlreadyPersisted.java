/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistance.file;

import persistance.PersistanceException;

/**
 *
 * @author nikolai
 */
public class FileAlreadyPersisted extends  PersistanceException{
    public FileAlreadyPersisted(Object o){
        super(""+o+" a déjà été serializé, peut être voulez vous l'update");
    }
}
