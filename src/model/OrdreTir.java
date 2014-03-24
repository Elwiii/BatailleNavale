/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 * @author nikolai
 */
public class OrdreTir {
    
    private int launcherid;
    
    private Coordinate coordinate;
    
    /* ordre à donner si on deal avec un bot */
    public static final OrdreTir NO_ORDER = new OrdreTir(null,-1);
    
    public OrdreTir(Coordinate coordinate, int launcherid){
        this.coordinate = coordinate;
        this.launcherid = launcherid;
    }

    /**
     * @return the launcher
     */
    public int getLauncher() {
        return launcherid;
    }

    /**
     * @param launcher the launcher to set
     */
    public void setLauncher(int launcher) {
        this.launcherid = launcher;
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
