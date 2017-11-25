package controller;

import java.util.concurrent.TimeUnit;
import model.Car;
import model.DeadState;
import model.ExplosionState;
import model.Frog;
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
        Frog frog = (Frog) Main.gameData.friendFigures.get(0);
        
        for (GameFigure ef : Main.gameData.enemyFigures) {
            if (frog.getCollisionBox().intersects(ef.getCollisionBox())) {
                    if (!frog.isHit) {
                        System.out.println(Main.gameData.frogHealth);
                        Main.gameData.frogHealth--;
                        frog.isHit = true;
                      
                    }
                    
                    if (Main.gameData.frogHealth == 1) {
                        Main.animator.gameOver = true;
                    }
                    
                    if (Main.animator.gameOver) {
                        Main.gameData.frogHealth = 3;
                    } 
            }
        }
        
        for (GameFigure ef : Main.gameData.enemyFigures) {
            for (GameFigure ff : Main.gameData.friendFigures) {
                if (ef.getCollisionBox().intersects(ff.getCollisionBox())) {    
                    
                    if (ff instanceof FrogBullet)
                    {
                        FrogBullet fb = (FrogBullet) ff;
                        fb.myState.doAction(ff);
                    }
                    
                    if (ef instanceof Car) {
                        Car car = (Car) ef;
                        car.setState(new ExplosionState());
                        car.myState.doAction(car);

                        car.setState(new DeadState());
                        car.myState.doAction(car);
                    }    
                }
            } // End for
        }
    } // End processCollision()
}
