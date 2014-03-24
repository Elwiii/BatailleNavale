/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.net.URL;
import java.util.List;
import java.util.Observer;
import model.player.Player;
import model.ship.ShipFactory;
import model.ship.TypeShip;
import persistance.AbstractDaoFactory;
import persistance.DaoFactoryException;
import persistance.TypePersistance;

/**
 * @author nikolai
 * @todo
 */
public class BatailleNavale {

    private String id;
    private int score;
    private State state;
    private Player j1;
    private Player j2; // le bot par defaut
    private Player currentPlayer = j1;
    private Player otherPlayer = j2;
    private ShipFactory shipFactory;
    private AbstractDaoFactory adf;
    private BatailleNavaleAdapter adapter;
    private Game save;

    private BatailleNavaleAdapter bna;

    public BatailleNavale() throws DaoFactoryException {
        bna = new BatailleNavaleAdapter();
        shipFactory = ShipFactory.getInstance();
        adf = AbstractDaoFactory.getAbstractDaoFactory(TypePersistance.FILE);
    }

    public void addObserver(Observer o) {
        bna.addObserver(o);
    }

    public void update() {
        bna.update();
    }

    /**
     *
     * @param order
     * @throws java.lang.Exception
     */
    public void fire(OrdreTir order) throws Exception {
        currentPlayer.fire(order, otherPlayer.getFlotte());
    }

    /**
     *
     * @throws java.lang.Exception
     */
    public void giveup() throws Exception {
        switch (state) {
            case JOUEUR1:
                state = State.GIVEUPJ1;
                break;
            case JOUEUR2:
                state = State.GIVEUPJ2;
                break;
            default:
                throw new Exception("partie terminée");
        }
    }

    /**
     *
     */
    public void switchTurn() throws Exception {
        switch (state) {
            case JOUEUR1:
                state = State.JOUEUR2;
                currentPlayer = j2;
                otherPlayer = j1;
                break;
            case JOUEUR2:
                state = State.JOUEUR1;
                currentPlayer = j1;
                otherPlayer = j2;
                break;
            default:
                throw new Exception("partie terminée");
        }
    }

    /**
     *
     */
    public void save() {
        save = new Game(this);
        adf.getInstanceDaoGame()
    }

    /**
     *
     * @param id
     */
    public void load(String id) {
        //@todo
    }

    /**
     *
     * @param idShip
     * @param joueur
     */
    public void removeShip(int idShip, int joueur) {
        currentPlayer.removeShip(idShip);
    }

    /**
     *
     * @param typeShip
     * @param queue
     * @param nez
     * @throws java.lang.Exception
     */
    public void addShip(TypeShip typeShip, Coordinate queue, Coordinate nez) throws Exception {
        currentPlayer.addShip(shipFactory.buildShip(typeShip, queue, nez));
    }

    /**
     *
     * @return
     */
    public List<URL> getSaveGame() {
        //@todo
        return null;

    }
    
    
    
    
    
    
    
    
    
    //---------------------------------getteur/setteurs ------------------------------------------

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @return the state
     */
    public State getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * @return the j1
     */
    public Player getJ1() {
        return j1;
    }

    /**
     * @param j1 the j1 to set
     */
    public void setJ1(Player j1) {
        this.j1 = j1;
    }

    /**
     * @return the j2
     */
    public Player getJ2() {
        return j2;
    }

    /**
     * @param j2 the j2 to set
     */
    public void setJ2(Player j2) {
        this.j2 = j2;
    }

    /**
     * @return the shipFactory
     */
    public ShipFactory getShipFactory() {
        return shipFactory;
    }

    /**
     * @param shipFactory the shipFactory to set
     */
    public void setShipFactory(ShipFactory shipFactory) {
        this.shipFactory = shipFactory;
    }

    /**
     * @return the adapter
     */
    public BatailleNavaleAdapter getAdapter() {
        return adapter;
    }

    /**
     * @param adapter the adapter to set
     */
    public void setAdapter(BatailleNavaleAdapter adapter) {
        this.adapter = adapter;
    }

    /**
     * @return the save
     */
    public Game getSave() {
        return save;
    }

    /**
     * @param save the save to set
     */
    public void setSave(Game save) {
        this.save = save;
    }

    /**
     * @return the bna
     */
    public BatailleNavaleAdapter getBna() {
        return bna;
    }

    /**
     * @param bna the bna to set
     */
    public void setBna(BatailleNavaleAdapter bna) {
        this.bna = bna;
    }
}
