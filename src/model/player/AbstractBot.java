/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.player;

import java.util.List;
import model.VisionBattlefield;
import model.OrdreTir;
import model.Ship;

/**
 * TODO
 * @author nikolai
 */
public abstract class AbstractBot extends AbstractPlayer{

    @Override
    public void play(VisionBattlefield bf, OrdreTir od, List<Ship> shipsEnnemi) {
        assert(od != OrdreTir.FIND_YOURSELF_ORDER):"ordre incorrect pour ce bot";
        autoPlay(bf,shipsEnnemi);
    }
    
    public abstract void autoPlay(VisionBattlefield bf,List<Ship> shipsEnnemi);
    
}
