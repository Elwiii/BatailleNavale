/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import model.BatailleNavale;
import model.player.Difficulty;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import persistance.DaoFactoryException;

/**
 *
 * @author Nikolai
 */
public class TestDao {

    BatailleNavale bn;

    public TestDao() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws DaoFactoryException {
        bn = new BatailleNavale();
        bn.setDifficulty(Difficulty.CROSSBOT);
        bn.setPseudoHumun("testDao");
        bn.setLongeurGrille(5);
        bn.setLargeurGrille(5);
        bn.constructPlayers();
        bn.newGame();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void test1() {
        
        bn.save();

    }
    
    @Test
    public void test2(){
        System.out.println("game6 = "+bn.getAdf().getInstanceDaoGame().find("game6"));
        assert(bn.getAdf().getInstanceDaoGame().find("game6")!=null);
    }
    
    @Test
    public void test3(){
        System.out.println(""+bn.getAdf().getInstanceDaoGame().find());
    }
}
