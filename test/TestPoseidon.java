/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.logging.Level;
import java.util.logging.Logger;
import model.BatailleNavale;
import model.Coordinate;
import model.OrdreTir;
import model.StateCase;
import static model.StateCase.FLOTTE_DETRUITE;
import static model.StateCase.HIT;
import model.player.Bot;
import model.player.Difficulty;
import model.ship.TypeShip;
import static model.ship.TypeShip.BARQUE;
import static model.ship.TypeShip.TRANSPORT;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import persistance.DaoFactoryException;

/**
 *
 * @author Nikolai
 */
public class TestPoseidon {

    private BatailleNavale bn;

    public TestPoseidon() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        try {
            bn = new BatailleNavale();
            bn.setDifficulty(Difficulty.POSEIDON);
            bn.setPseudoHumun("testingPoseidon");
            bn.setLongeurGrille(5);
            bn.setLargeurGrille(5);
            bn.constructPlayers();
            bn.newGame();

        } catch (DaoFactoryException ex) {
            Logger.getLogger(TestPoseidon.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TestPoseidon.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void poseidonFire() throws Exception {
        bn.addShip(TypeShip.BARQUE, new Coordinate(0, 0), new Coordinate(0, 4));    
            bn.switchTurn();
            TypeShip[] bateaux = {BARQUE,BARQUE,BARQUE};
            ((Bot)bn.getJ2()).placerBateaux(bateaux);
        bn.fire(OrdreTir.NO_ORDER);
        bn.fire(OrdreTir.NO_ORDER);
        bn.fire(OrdreTir.NO_ORDER);
        bn.fire(OrdreTir.NO_ORDER);
//        bn.fire(OrdreTir.NO_ORDER);
        assertEquals(bn.getJ1().getFlotte().getVaisseaux().get(0).getLife(),1);
        
    }
    
    @Test
    public void poseidonFire2() throws Exception {
        bn.addShip(TypeShip.BARQUE, new Coordinate(0, 0), new Coordinate(0, 4));    
            bn.switchTurn();
            TypeShip[] bateaux = {BARQUE,BARQUE,BARQUE};
            ((Bot)bn.getJ2()).placerBateaux(bateaux);
        bn.fire(OrdreTir.NO_ORDER);
        bn.fire(OrdreTir.NO_ORDER);
        bn.fire(OrdreTir.NO_ORDER);
        bn.fire(OrdreTir.NO_ORDER);
        StateCase state = bn.fire(OrdreTir.NO_ORDER);
        assertEquals(FLOTTE_DETRUITE,state);
        
    }
    @Test
    public void poseidonFire3() throws Exception {
        bn.addShip(TypeShip.BARQUE, new Coordinate(0, 0), new Coordinate(0, 4));
        bn.addShip(TypeShip.TRANSPORT, new Coordinate(1, 0), new Coordinate(1, 1));
        bn.switchTurn();
        TypeShip[] bateaux = {BARQUE, TRANSPORT};
        ((Bot) bn.getJ2()).placerBateaux(bateaux);
        int i = 0;
//        int j = 0;
        StateCase s;
        while (i < 5) {
            s = bn.fire(OrdreTir.NO_ORDER);
            if (s == HIT) {
                i++;
            }
        }
//        bn.fire(OrdreTir.NO_ORDER);
//        bn.fire(OrdreTir.NO_ORDER);
//        bn.fire(OrdreTir.NO_ORDER);
//        bn.fire(OrdreTir.NO_ORDER);
        assertEquals(bn.getJ1().getFlotte().getVaisseaux().size(), 1);

    }
}
