/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 *
 * @author Dat
 */
public class ScoreBoard {
    int score= 0;
    public ScoreBoard()
    {
        
    }
    
    public void render(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setFont(new  Font("arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 2, 20);
    }
    public void update()
    {
        
    }
}

