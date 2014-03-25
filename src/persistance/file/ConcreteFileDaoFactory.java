/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistance.file;

import persistance.AbstractDaoFactory;
import persistance.Dao;

/**
 * @todo
 * @author Nikolai
 */
public class ConcreteFileDaoFactory extends AbstractDaoFactory{
    
    private ConcreteFileDaoFactory(){
        
    }
    
    public static ConcreteFileDaoFactory getInstance(){
        return null;
    }

    @Override
    public Dao getInstanceDaoGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Dao getInstanceDaoPlayer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
