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
public class LaunchBullet implements State{

    @Override
    public void doAction(GameFigure gameFigure) {
        FrogBullet fb = (FrogBullet) gameFigure;
        fb.explode = true;
        gameFigure.setState(new BulletExploded());
    }
    
}
