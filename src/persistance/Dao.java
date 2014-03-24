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
public abstract class Dao<T implements Serializable> {
    public T find(String id){};
    public ArrayList<T> find();
    public int persiste(T g);
    public void update(T g){};
    
}
