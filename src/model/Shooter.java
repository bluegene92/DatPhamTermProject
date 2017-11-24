package model;

import controller.Main;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import view.MainWindow;
import javax.swing.Timer;

public class Shooter extends GameFigure {
    
    Line2D.Float barrel;
    private final int BARREL_LEN = 20;
    private final int BASE_SIZE = 20;
    private Image image3;
    public int timeCount =0;
    public Timer time;
    
    public Shooter(int x, int y) {
        super(x, y);
        super.state = GameFigureState.SHOOTER_STATE_HEALTH_LEVEL_5;
        barrel = new Line2D.Float(super.x, super.y, super.x, super.y-BARREL_LEN);
        time= new Timer(1000, taskPerform);
        time.start();
       
//        timer.scheduleAtFixedRate(new TimerTask(){
//            @Override
//        public void run(){
//            timeCount++;
//            if (timeCount % 2 == 0) {
//                Missile m = new Missile(
//                x,
//                y,
//                Main.gameData.friendFigures.get(0).x, 
//                Main.gameData.friendFigures.get(0).y + 40,            
//                Color.BLUE);
//
//                if (!Main.animator.gameOver) {
//                    Main.gameData.enemyFigures.add(m);
//                }
//               // timeCount =0;
//            }   
//            }
//        } , 0, 1000);
            
               
        try {
            image3 = ImageIO.read(getClass().getResource("shooter.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open base2.png");
            System.exit(-1);
        }
    }
//    public void pause(int pauseTime) {
//        time = new Timer(pauseTime, taskPerform);
//        time.start();
//    }

        ActionListener taskPerform = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            Missile m = new Missile(
                x,
                y,
                Main.gameData.friendFigures.get(0).x, 
                Main.gameData.friendFigures.get(0).y + 40,            
                Color.BLUE);

                if (!Main.animator.gameOver) {
                    Main.gameData.enemyFigures.add(m);
                }
        }
    };

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.RED);
        int tx = (int) Main.gameData.friendFigures.get(0).x;
        int ty = (int) Main.gameData.friendFigures.get(0).y;
        double rad = Math.atan2(ty - super.y, tx - super.x);
        int bendy = (int)(BARREL_LEN * Math.sin(rad));
        int bendx = (int)(BARREL_LEN * Math.cos(rad));
        barrel.x1 = super.x;
        barrel.y1 = super.y;
        barrel.x2 = super.x + bendx;
        barrel.y2 = super.y + bendy;
        g.setStroke(new BasicStroke(7)); // thickness of the line
        g.drawImage(image3, (int) super.x- BASE_SIZE, (int) super.y- BASE_SIZE,
                100, 100, null);
        g.draw(barrel);
    }

    @Override
    public void update() {
        // no periodic update is required (not animated)
        // if health level is implemented, update level
        // update is done via 'translate' when a key is pressed
    }
    
    // Missile shoot location: adjut x and y to the image
    public float getXofMissileShoot() {
        return barrel.x2;
    }
    
    public float getYofMissileShoot() {
        return barrel.y2;
    }

    public Rectangle2D getCollisionBox(){
        float colSize = (float) (BASE_SIZE*(0.9));
        return new Rectangle2D.Float((int) super.x - colSize, (int) super.y -colSize, colSize*2, colSize*2);
    }
}
