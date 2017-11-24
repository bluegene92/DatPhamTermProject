
package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class BackGround  {
    private Image image4;
    private final int DIRT_LEN = 10;
    private final int DIRT_SIZE = 50;
    public BackGround(){
            try {
        image4 = ImageIO.read(getClass().getResource("space.jpg"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open base2.png");
            System.exit(-1);
        }
    }
    public void render(Graphics2D g)
    {
        //g.setColor(Color.RED);
        //g.fill(new Rectangle2D.Float(0,0,100,100));
        

    g.drawImage(image4,0,0,null);
}
}
