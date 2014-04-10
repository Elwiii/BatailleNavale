/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import persistance.ScoreManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Nikolai
 */
public class TestScore {
    
    private static ScoreManager score;
    
    public TestScore() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        score = ScoreManager.getInstance();
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
     @Test
     public void hello() {
         score.udpate("omg", 666);
         for (int i = 0; i < 100; i++) {
             score.udpate("test"+i, i);
             
         }
         score.udpate("lol", 1000);
         
         score.save();
         System.out.println(""+score.getScoreOf("test1"));
     }
     
     @Test
     public void test1(){
         System.out.println("test1 : "+score.getScoreOf("coucou"));
     }
}
