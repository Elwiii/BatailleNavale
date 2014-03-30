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
 */
public abstract class Relation<T extends Serializable, K extends Serializable> {
    public abstract ArrayList<T> get(K p);
    public abstract ArrayList<K> get(T g);
    public abstract int persiste(T g,K p);
    public abstract void removeRelation(T g, K p);
    public abstract Dao getInstance();
    
}
