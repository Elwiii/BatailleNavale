/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistance;

import java.io.Serializable;
import java.util.ArrayList;


/**
 *
 * @author Nikolai
 * @param <T>
 */
public abstract class Dao<T extends Serializable> {
    public abstract T find(String id) throws PersistanceException;
    public abstract ArrayList<T> find() throws PersistanceException;
    public abstract int persiste(T g) throws PersistanceException;
    public abstract void update(T g) throws PersistanceException;    
}
