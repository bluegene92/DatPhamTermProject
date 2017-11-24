/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


public class Explosion extends GameFigure {
    
    public BufferedImage image;
    private int width;
    private int height;
    private int index;
    private double timeCount;
    public State myState = new AliveState();
    
    public Explosion (float x, float y){
        super(x,y);
        
        try {
            image = ImageIO.read(getClass().getResource("explosion.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open shooter.png");
            System.exit(-1);
        }
    }
    
    @Override
    public void render(Graphics2D g) {
        g.drawImage(image,(int) x,(int) y, null);
    }

    @Override
    public void update() {
    }

    @Override
    public Rectangle2D getCollisionBox() {
        return new Rectangle2D.Float(100,100,10,10);
    }
    
}
