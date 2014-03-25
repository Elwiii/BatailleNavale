/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.List;

/**
 * Une couple de point (x,y) / (longitude/latitude)
 * @OK
 * @author nikolai
 */
public class Coordinate implements Serializable{
    
    private List<Coordinate> coordinates;
    
    public int x;
    public int y;
    
    public Coordinate(int x, int y){
        this.x= x;
        this.y = y;
    }
    
    public Coordinate(){
        
    }
    
    
    @Override
    public boolean equals(Object o){
        Coordinate c = (Coordinate)o;
        return c.x == x && c.y == y;
    }
    
    @Override
    public String toString(){
        return "("+x+","+y+")";
    }
}
