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

    @Override
    public void doAction(GameFigure gameFigure) {
            Main.gameData.removeEnemies.add(gameFigure);
    }
    
}
