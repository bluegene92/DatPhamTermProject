package controller;

import java.util.concurrent.TimeUnit;
import model.Car;
import model.ExplosionState;
import model.FrogBullet;
import model.GameFigure;

public class Animator implements Runnable {

    public boolean running = true;
    private final int FRAMES_PER_SECOND = 40;

    public boolean gameOver = true;
    @Override
    public void run() {

        while (running) {
            long startTime = System.currentTimeMillis();
            
            processCollisions();

            if (!gameOver) {
                Main.gameData.update();
            }
            
            Main.gamePanel.gameRender();
            Main.gamePanel.printScreen();

            long endTime = System.currentTimeMillis();
            int sleepTime = (int) (1.0 / FRAMES_PER_SECOND * 1000)
                    - (int) (endTime - startTime);

            if (sleepTime > 0) {
                try {
                    TimeUnit.MILLISECONDS.sleep(sleepTime);
                } catch (InterruptedException e) {

                }
            }
        }
        System.exit(0);
    }
    
    private void processCollisions() {
        // detect collisions between friendFigure and enemyFigures
        // if detected, mark it as STATE_DONE, so that
        // they can be removed at update() method
        for (GameFigure ef : Main.gameData.enemyFigures) {
            for (GameFigure ff : Main.gameData.friendFigures) {
                if (ef.getCollisionBox().intersects(ff.getCollisionBox())) {
                    if (ef instanceof Car) {
                    Car car = (Car) ef;
                    car.setState(new ExplosionState());
                    car.myState.doAction(car);
                   
                    //removeEnemies.add(ef);
                    }
                    if (ff instanceof FrogBullet)
                    {
                        FrogBullet fb = (FrogBullet) ff;
                        fb.myState.doAction(ff);
                    }
                }       
            }
        }
        
        
    }

}
