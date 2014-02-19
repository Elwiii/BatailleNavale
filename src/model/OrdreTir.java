/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 * TODO
 * @author nikolai
 */
public class OrdreTir {
    
    private Ship launcher;
    
    private Coordinate coordinate;
    
    /* ordre à donner si on deal avec un bot */
    public static final OrdreTir NO_ORDER = new OrdreTir(null,null);
    
    public OrdreTir(Coordinate coordinate, Ship launcher){
        this.coordinate = coordinate;
        this.launcher = launcher;
    }

    /**
     * @return the launcher
     */
    public Ship getLauncher() {
        return launcher;
    }

    /**
     * @param launcher the launcher to set
     */
    public void setLauncher(Ship launcher) {
        this.launcher = launcher;
    }

    /**
     * @return the coordinate
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * @param coordinate the coordinate to set
     */
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
    
}
