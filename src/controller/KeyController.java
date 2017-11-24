package controller;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import model.Missile;
import model.Frog;
import model.FrogBullet;
import model.Laser;

public class KeyController extends KeyAdapter {
    //public int x;
    //public int y;

    @Override
    public void keyPressed(KeyEvent e) {
        Frog frog = (Frog) Main.gameData.friendFigures.get(0);

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                frog.setmoveX(-7);
                break;
            case KeyEvent.VK_RIGHT:
                frog.setmoveX(7);
                break;
            case KeyEvent.VK_UP:
                frog.setmoveY(-7);
                break;
            case KeyEvent.VK_DOWN:
                frog.setmoveY(7);
                break;
            case KeyEvent.VK_SPACE:
                FrogBullet m = new FrogBullet(
                frog.getXofMissileShoot(),
                frog.getYofMissileShoot(),
                frog.x, -50, // target location where the missile explodes
                Color.RED);
                Main.gameData.friendFigures.add(m);
                break;
            case KeyEvent.VK_L:
                Main.gameData.createLaser();
                break;

        
        }
    }
    
        @Override
    public void keyReleased(KeyEvent e) {
        Frog frog = (Frog) Main.gameData.friendFigures.get(0);

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if(frog.x > 40)
                //frog.translate(-40,0);
                frog.setmoveX(0);
                break;
            case KeyEvent.VK_RIGHT:
                frog.setmoveX(0);
                break;
            case KeyEvent.VK_UP:
                frog.setmoveY(0);
                break;
            case KeyEvent.VK_DOWN:
                frog.setmoveY(0);
                break;
        }
    }
}
