/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Main;

/**
 *
 * @author Dat
 */
public class DeadState implements State{


    public DeadState() {}
    Frog frog = (Frog) Main.gameData.friendFigures.get(0);
    @Override
    public void doAction(GameFigure gameFigure) {
            Main.gameData.removeEnemies.add(gameFigure);
            frog.isHit = false;
    }
    
}
