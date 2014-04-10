/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Observer;
import model.player.CrossBot;
import model.player.Difficulty;
import model.player.Human;
import model.player.Player;
import model.player.RandomBot;
import model.ship.ShipFactory;
import model.ship.TypeShip;
import persistance.AbstractDaoFactory;
import persistance.DaoFactoryException;
import persistance.ScoreManager;
import persistance.TypePersistance;

/**
 * @author nikolai
 */
public class BatailleNavale {

    private String id;
    private int score;
    private State state;
    private Player j1;
    private Player j2; // le bot par defaut
    private Player currentPlayer;
    private Player otherPlayer;
    private ShipFactory shipFactory;
    private AbstractDaoFactory adf;
    private BatailleNavaleAdapter adapter;
    private Game save;
    private Difficulty difficulty;
    private String pseudoHumun;
    private int largeurGrille;
    private int hauteurGrille;
    private ScoreManager scoreManager;
    private BatailleNavaleAdapter bna;

    public BatailleNavale() throws DaoFactoryException {
        bna = new BatailleNavaleAdapter();
        shipFactory = ShipFactory.getInstance();
        adf = AbstractDaoFactory.getAbstractDaoFactory(TypePersistance.FILE);
        
    }
    
    public void newGame(){
        id = "game"+adf.getInstanceDaoGame().find().size();
        state = State.JOUEUR1;
    }

    public void constructPlayers() {
        
        j1 = new Human(pseudoHumun, 0, 0); //@todo Nicolas
        j1.constructFlotte();
        j1.constructMap(hauteurGrille, largeurGrille);
        // faudrait faire une factory pour les bots ça serait plus propre
        switch (difficulty) {
            case CROSSBOT:
                j2 = new CrossBot();
                j2.constructFlotte();
                j2.constructMap(hauteurGrille, largeurGrille);
                break;
            case RANDOMBOT:
                j2 = new RandomBot();
                j2.constructFlotte();
                j2.constructMap(hauteurGrille, largeurGrille);
        }
        currentPlayer = j1;
        otherPlayer = j2;
        update();
    }


    /**
     *
     * @param order
     * @return
     * @throws java.lang.Exception
     */
    public int fire(OrdreTir order) throws Exception {
        int res = currentPlayer.fire(order, otherPlayer.getFlotte());
        if (res == Flotte.FLOTTE_DETRUITE) {
            switch (state) {
                case JOUEUR1:
                    state = State.WINJ1;
                    scoreManager.udpate(j1.getNom(), score);
                    break;
                case JOUEUR2:
                    state = State.WINJ2;
                    scoreManager.udpate(j2.getNom(), score);
                    break;
                default:
                    throw new Exception("error");
            }
        } else {
            // on met à jour la vison du battlefield qu'a le joueur courant
            currentPlayer.getMap().setState(order.getCoordinate(), res);
        }
        return res;
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
        System.out.println("save : "+save);
        adf.getInstanceDaoGame().persiste(save);
    }

    /**
     *
     * @param id
     */
    public void load(String id) {
        //@todo Nicolas (load)

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
    
    
    public void addObserver(Observer o) {
        bna.addObserver(o);
    }

    public void update() {
        bna.update();
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

    /**
     * @return the currentPlayer
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * @param currentPlayer the currentPlayer to set
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * @return the otherPlayer
     */
    public Player getOtherPlayer() {
        return otherPlayer;
    }

    /**
     * @param otherPlayer the otherPlayer to set
     */
    public void setOtherPlayer(Player otherPlayer) {
        this.otherPlayer = otherPlayer;
    }

    /**
     * @return the adf
     */
    public AbstractDaoFactory getAdf() {
        return adf;
    }

    /**
     * @param adf the adf to set
     */
    public void setAdf(AbstractDaoFactory adf) {
        this.adf = adf;
    }

    /**
     * @return the difficulty
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * @param difficulty the difficulty to set
     */
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * @return the pseudoHumun
     */
    public String getPseudoHumun() {
        return pseudoHumun;
    }

    /**
     * @param pseudoHumun the pseudoHumun to set
     */
    public void setPseudoHumun(String pseudoHumun) {
        this.pseudoHumun = pseudoHumun;
    }

    /**
     * @return the longeurGrille
     */
    public int getLongeurGrille() {
        return largeurGrille;
    }

    /**
     * @param longeurGrille the longeurGrille to set
     */
    public void setLongeurGrille(int longeurGrille) {
        this.largeurGrille = longeurGrille;
    }

    /**
     * @return the largeurGrille
     */
    public int getLargeurGrille() {
        return hauteurGrille;
    }

    /**
     * @param largeurGrille the largeurGrille to set
     */
    public void setLargeurGrille(int largeurGrille) {
        this.hauteurGrille = largeurGrille;
    }
}
