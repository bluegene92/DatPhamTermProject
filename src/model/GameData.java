package model;

import controller.Main;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameData {

    private final int RADIUS = 6;
    public final List<GameFigure> enemyFigures;
    public final List<GameFigure> friendFigures;
    
    public static Shooter shooter;
    public static Frog frog;
    private Random rand = new Random();
    private int meteorCount =10;
    public ScoreBoard scoreBoard;
    
    public int frogHealth = 3;

    private List<Laser> laserList = new CopyOnWriteArrayList<>();
    
    ArrayList<GameFigure> removeEnemies;
    
    public GameData() {
        enemyFigures = new CopyOnWriteArrayList<>();
        friendFigures = new CopyOnWriteArrayList<>();
        // GamePanel.width, height are known when rendered. 
        // Thus, at this moment,
        // we cannot use GamePanel.width and height.

        frog = new Frog(Main.WIN_WIDTH/2 , Main.WIN_HEIGHT -125);
        enemyFigures.add(new Shooter(Main.WIN_WIDTH/2, Main.WIN_HEIGHT/2));
        friendFigures.add(frog);
        scoreBoard = new ScoreBoard();
        
            //car
          for (int i = 0; i < 30; ++i) {
              int rx = rand.nextInt(Main.WIN_WIDTH);
              int ry = rand.nextInt(340 - 100 + 1) + 100;
              enemyFigures.add(new Car(rx, ry));
          }
          
          //car
          for (int i =0; i< 20; ++i){
              int rx = rand.nextInt(Main.WIN_WIDTH);
              int maxR = Main.WIN_HEIGHT;
              int ry = rand.nextInt(maxR - 640 + 1) + 450;
              enemyFigures.add(new Car(rx,ry));
          }
          
          //meteor
          for( int i =0; i < meteorCount; i++)
            {
                int rx = rand.nextInt(Main.WIN_WIDTH);
                int ry = rand.nextInt(11 - 10 + 1) -60;
                enemyFigures.add(new Meteor(rx, ry));
            }
    }
    
    public void createLaser() {
        for (int i = 0; i < 50; ++i) {
            float ix = frog.x + i;
            float iy = frog.y - (i);
            laserList.add(new Laser(ix, iy));
            friendFigures.add(laserList.get(i));
        }
    }

    public void update() {
        // no enemy is removed in the program
        // since collision detection is not implemented yet.
        // However, if collision detected, simply set
        // f.state = GameFigure.STATE_DONE
        removeEnemies = new ArrayList<>();
        GameFigure f;
        for (int i = 0; i < enemyFigures.size(); i++) {
            f = enemyFigures.get(i);
            if (f.state == GameFigureState.STATE_DONE) {
                removeEnemies.add(f);
            }
        }
        enemyFigures.removeAll(removeEnemies);

        for (GameFigure g : enemyFigures) {
            g.update();
        }
        // missiles are removed if explosion is done
        ArrayList<GameFigure> removeFriends = new ArrayList<>();
        for (int i = 0; i < friendFigures.size(); i++) {
            f = friendFigures.get(i);
            if (f.state == GameFigureState.STATE_DONE) {
                removeFriends.add(f);
            }
        }
        friendFigures.removeAll(removeFriends);

        
        for (GameFigure g : friendFigures) {
            g.update();
        }
        
        enemyFigures.removeAll(removeEnemies);
        
        
        for (GameFigure enemy : enemyFigures) {
            if (friendFigures.get(0).getCollisionBox().
                    intersects(enemy.getCollisionBox())) {
               
                if (frogHealth == 1) {
                    System.out.println("Game over");
                    Main.animator.gameOver = true;
                }
                
                frogHealth--;
                if (Main.animator.gameOver) {
                    frogHealth = 3;
                }
                if (!(enemy instanceof Shooter)) {
                    enemy.state = GameFigureState.STATE_DONE;
                }
            }
            
        }
        
        for(GameFigure friend : friendFigures)
        {
            if(Main.gameData.enemyFigures.get(0).getCollisionBox().
                    intersects(friend.getCollisionBox()))
            {
                
            }
        }
        
//        for(GameFigure mean : enemyFigures){
//            for(GameFigure nice : friendFigures){
//                if(mean.getCollisionBox().intersects(nice.getCollisionBox())){
//                    if(!(nice instanceof Frog)){
//                            nice.state = GameFigureState.STATE_DONE;  
//                            mean.state = GameFigureState.STATE_DONE;
//                            
//                            System.out.println("shooter hit frog!");
//                        }
// 
//                    
//                    //mean.state = GameFigureState.BOMB_STATE_EXPLODED;
//                }
//            }
//        }
    }
}
