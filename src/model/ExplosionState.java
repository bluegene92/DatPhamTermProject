/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Main;

public class ExplosionState implements State{

    @Override
    public void doAction(GameFigure gameFigure) {
        if (gameFigure instanceof Car) {
        Main.gameData.friendFigures.add(new Explosion(gameFigure.x,gameFigure.y));
        Car car = (Car) gameFigure;
        car.setState(new DeadState());
        car.myState.doAction(car);
        }
    }
    
}
