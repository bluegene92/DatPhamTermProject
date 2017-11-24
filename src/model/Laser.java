/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Main;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author Dat
 */
public class Laser extends GameFigure {
            
    int dx = 10;
    int dy = -10;
    
    public Laser(float x, float y) {
        super(x, y);
        state = 3;
    }
    
    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.RED);
        g.draw(new Ellipse2D.Float(x, y, 3, 3));
    }

    @Override
    public void update() {
            super.x += dx;
            super.y += dy;

            if (super.x > Main.WIN_WIDTH) {
                dx = -dx;
                super.x = Main.WIN_WIDTH;
            } else if (super.x < 0) {
                dx = -dx;
                super.x = 3;
            }

            if (super.y > Main.WIN_HEIGHT) {
                dy = -dy;
                super.y = Main.WIN_HEIGHT;
            } else if (super.y < 0) {
                dy = -dy;
                super.y = 3;
            }
 
    }

    @Override
    public Rectangle2D getCollisionBox() {
        return new Rectangle2D.Float(x, y, 2, 2);
    }
    
}
