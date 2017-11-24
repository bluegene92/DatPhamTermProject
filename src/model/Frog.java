package model;

import controller.Main;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Frog extends GameFigure implements Observer {

    private final int WIDTH = 80;
    private final int HEIGHT = 80;
    private Image image2;
    private double moveX = 0;
    private double moveY=0;
    //private int direction = 1; // +1: to the right; -1 to the left
    
    public boolean isHit = false;
    
    public Frog(float x, float y) {
        super(x, y); // origin: upper-left corner
        super.state = GameFigureState.FROG_STATE_APPEARED;

        image2 = null;

        try {
            image2 = ImageIO.read(getClass().getResource("Frog.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open Frog.png");
            System.exit(-1);
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.YELLOW);
        g.drawImage(image2, (int) super.x- WIDTH/2, (int) super.y,
                WIDTH, HEIGHT, null);
    }
    
    @Override
    public void change() {
        super.y = Main.WIN_HEIGHT;
    }
    
    @Override
    public void update() {
        x+= moveX;
        y+= moveY;
        
        if(x <=0+30)
            x=0+30;
        if(x >= 1000-30)
            x=1000-30;
        if(y <=0-10)
            y=0-10;
        if(y >= 625)
            y=625;
    }
    
    public float getXofMissileShoot() {
        return x;
    }
    
    public float getYofMissileShoot() {
        return y;
    }
        
    public void setmoveX(int moveX)
    {
        this.moveX = moveX;
    }
    
    public void setmoveY(int moveY)
    {
        this.moveY = moveY;
    }
    
    public Rectangle2D getCollisionBox(){
        float colSize =(float) ((float) WIDTH *0.6);
        return new Rectangle2D.Float((int)super.x - colSize/2, (int)super.y+colSize/4, colSize, colSize);
    }

}
