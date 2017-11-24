package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import view.GamePanel;

public class Meteor extends GameFigure {

    private final int WIDTH = 50;
    private final int HEIGHT = 40;
    private final int UNIT_TRAVEL = 5; // per frame
    private Image image;

    private int direction = 1; // +1: to the right; -1 to the left
    private Random rand = new Random();
    private int speed = rand.nextInt(5)+1;
    public Meteor(float x, float y) {
        super(x, y); // origin: upper-left corner
        super.state = GameFigureState.METEOR_STATE_APPEARED;
        image = null;

        try {
            image = ImageIO.read(getClass().getResource("meteor.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open shooter.png");
            System.exit(-1);
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(image, (int) super.x, (int) super.y,
                WIDTH, HEIGHT, null);   
    }

    @Override
    public void update() {
        if(state == GameFigureState.METEOR_STATE_DAMAGED)
        {
            super.y += UNIT_TRAVEL;
            if ( super.y < GamePanel.height)
            {
                state = 0;
            }
        }
        else if (direction > 0) {
            // moving down
            super.y += speed;
            if (super.y > GamePanel.height) {
                x = rand.nextInt(1000-30);
                y = -10;
            }
        }
    }

    public Rectangle2D getCollisionBox(){
        Rectangle2D.Float rect = new Rectangle2D.Float(((float)(super.x)),((float)(super.y)),WIDTH,HEIGHT);
        return rect;
    }

}
