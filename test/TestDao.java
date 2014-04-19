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
import persistance.DaoFactoryException;
import persistance.PersistanceException;
import persistance.ScoreManager;

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
    public void setUp() throws DaoFactoryException, PersistanceException, Exception {
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
    public void test1() throws PersistanceException {
        
        bn.save();

    }
    
//    @Test
//    public void test2(){
//        System.out.println("game1 = "+bn.getAdf().getInstanceDaoGame().find("game6"));
//     @Test
//    public void test2(){
//        System.out.println("game1 = "+bn.getAdf().getInstanceDaoGame().find("game6"));
//        assert(bn.getAdf().getInstanceDaoGame().find("game1")!=null);
//    }   assert(bn.getAdf().getInstanceDaoGame().find("game1")!=null);
//    }
    
    @Test
    public void test3() throws PersistanceException{
//        System.out.println("test 3 :"+bn.getAdf().getInstanceDaoGame().find());
//        System.out.println(ScoreManager.getInstance().getScoreOf("coucou"));
    }
}
