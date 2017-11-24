package model;

import java.awt.Graphics2D;

public abstract class GameFigure implements CollisionBox {

    // public for a faster access during animation
    public float x;
    public float y;
    
    public int state;
    public State myState;


    public GameFigure(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public void setState(State state) {
        myState = state;
    }
    
    // how to render on the canvas
    public abstract void render(Graphics2D g);

    // changes per frame
    public abstract void update();

    public State getState() {
        return myState;
    }
}
