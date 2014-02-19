/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistance;

import persistance.file.ConcreteFileDaoFactory;

/**
 * Renvoi une factory pour la persistence du type choisie.
 * @todo
 * @author Nikolai
 */
public abstract class AbstractDaoFactory {
    
    
    protected AbstractDaoFactory(){
        
    }
    
    public static AbstractDaoFactory getAbstractDaoFactory(TypePersistance persistance) throws DaoFactoryException{
        switch(persistance){
            case FILE : 
                return ConcreteFileDaoFactory.getInstance();
            default :
                throw new DaoFactoryException("Le type de la persistance n'est pas connu");
        }        
    }
    
    public Dao getInstanceDao(TypeDao typeDao) throws DaoFactoryException{
        switch(typeDao){
            case GAME :
                return getInstanceDaoGame();
            case PLAYER : 
                return getInstanceDaoPlayer();
            default :
                throw new DaoFactoryException("type de DAO inconnu");
        }
    }
    
    public abstract Dao getInstanceDaoGame();
    
    public abstract Dao getInstanceDaoPlayer();
    
    
    
}
