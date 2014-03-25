/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;

/**
 * Une couple de point (x,y) / (longitude/latitude)
 * @todo 
 * @author nikolai
 */
public class Coordinate implements Serializable{
    
    public int x;
    public int y;
    
    public Coordinate(int x, int y){
        this.x= x;
        this.y = y;
    }
    
    public Coordinate(){
        
    }
    
    @Override
    public String toString(){
        return "("+x+","+y+")";
    }
}
