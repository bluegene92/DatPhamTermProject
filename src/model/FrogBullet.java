package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class FrogBullet extends GameFigure {

    // missile size
    private static final int SIZE = 5;
    private static final int MAX_EXPLOSION_SIZE = 50;
    private float dx; // displacement at each frame
    private float dy; // displacement at each frame
    

    // public properties for quick access
    public Point2D.Float target;

    private static final int UNIT_TRAVEL_DISTANCE = 4; // per frame move
    public boolean explode = false;
    private int size = SIZE;
    public State myState = new AliveState();
    public FrogBullet(float sx, float sy, float tx, float ty, Color color) {
        super(sx, sy);
        super.state = GameFigureState.FROGBULLET_STATE_LAUCHED;
        this.target = new Point2D.Float(tx, ty);
        setState(new LaunchBullet());
        double angle = Math.atan2(ty - sy, tx - sx);
        dx = (float) (UNIT_TRAVEL_DISTANCE * Math.cos(angle));
        dy = (float) (UNIT_TRAVEL_DISTANCE * Math.sin(angle));
    }
    
    @Override
    public void setState(State state) {
        myState = state;
        
    }

    @Override
    public void render(Graphics2D g) {
        //g.setColor(color);
        g.setStroke(new BasicStroke(10)); // thickness of the line
        g.drawRect((int) (super.x - size / 2),
                (int) (super.y - size / 2),
                size, size);
        if(myState.getClass().equals(new BulletExploded().getClass())){
            g.fillRect((int)(x),(int)(y-size/2),size*2,size*2);
        }
    }

    @Override
    public void update() {
        updateState();
        if (state == GameFigureState.FROGBULLET_STATE_LAUCHED) {
            updateLocation();
        } else if (explode=true) {
            updateSize();
        }
    }

    public void updateLocation() {
        
        super.x += dx;
        super.y += dy;
    }

    public void updateSize() {
        size += 10;
    }

    public void updateState() {
        if (state == GameFigureState.FROGBULLET_STATE_LAUCHED) {
            double distance = target.distance(super.x, super.y);
            boolean targetReached = distance <= 2.0;
            if (targetReached) {
                myState.doAction(this);
                
            }
        } else if (state == GameFigureState.FROGBULLET_STATE_EXPLODED) {
            if (size >= MAX_EXPLOSION_SIZE) {
                state = GameFigureState.STATE_DONE;
            }
        }
    }
    public Rectangle2D getCollisionBox(){
        Rectangle2D.Float rect = new Rectangle2D.Float((int)(x),(int)(y-size/2),size*2,size*2);
        return rect;
    }

}
