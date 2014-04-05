/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistance.file;
import java.io.FileOutputStream;
import java.io.Serializable;
import model.Game;
import persistance.*;

/**
 *
 * @author Nikolai
 */
public abstract class DaoFile<T extends Serializable> extends Dao<T> {
    FileOutputStream os;
}
