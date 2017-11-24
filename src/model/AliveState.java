/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Dat
 */
public class AliveState implements State {

    @Override
    public void doAction(GameFigure gameFigure) {
        gameFigure.setState(new DeadState());
    }
    
}
