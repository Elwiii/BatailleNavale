/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;
import persistance.AbstractDaoFactory;
import persistance.DaoFactoryException;
import persistance.PersistanceException;
import persistance.TypePersistance;

/**
 *
 * @author nikolai
 */
public class SavedGameListModel extends AbstractListModel{

    @Override
    public int getSize() {
        try {
            return AbstractDaoFactory.getAbstractDaoFactory(TypePersistance.FILE).getInstanceDaoGame().find().size();
        } catch (PersistanceException | DaoFactoryException ex) {
            Logger.getLogger(SavedGameListModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public Object getElementAt(int i) {
        try {
            return AbstractDaoFactory.getAbstractDaoFactory(TypePersistance.FILE).getInstanceDaoGame().find().get(i);
        } catch (PersistanceException | DaoFactoryException ex) {
            Logger.getLogger(SavedGameListModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
}
