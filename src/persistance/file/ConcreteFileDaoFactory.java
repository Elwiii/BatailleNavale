/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistance.file;

import persistance.AbstractDaoFactory;
import persistance.Dao;

/**
 * @todo Thomas
 * @author Nikolai
 */
public class ConcreteFileDaoFactory extends AbstractDaoFactory{
    
    private static ConcreteFileDaoFactory INSTANCE = null;
    private ConcreteFileDaoFactory(){
        
    }
    
    public static ConcreteFileDaoFactory getInstance(){
        if (INSTANCE == null)
        { 	INSTANCE = new ConcreteFileDaoFactory();	
        }
        return INSTANCE;
    }

    @Override
    public Dao getInstanceDaoGame() {
        return DaoFileGame.getInstance();        
    }

    @Override
    public Dao getInstanceDaoPlayer() {
        return null;
    }
    
}
