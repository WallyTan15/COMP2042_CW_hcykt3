package Controller;

import Model.Ball;
import Model.Brick;
import Model.Player;
import Model.Wall;

public class GameController {
    private Wall wall;

    public GameController(Wall wall){
        this.wall = wall;
    }

    public Wall getWall() {
        return wall;
    }

    public void nextGameLevel(){
        wall.nextLevel();
    }

    public void move(){
        wall.move();
    }

    public void findWallImpacts(){
        wall.findImpacts();
    }

    public int getCurrentBrickCount(){
        return wall.getBrickCount();
    }

    public int getCurrentBallCount(){
        return wall.getBallCount();
    }

    public boolean isCurrentBallLost(){
        return wall.isBallLost();
    }

    public boolean isCurrentBallEnd(){
        return wall.ballEnd();
    }

    public void wallReset(){
        wall.wallReset();
    }

    public void ballReset(){
        wall.ballReset();
    }

    public boolean isGameDone(){
        return wall.isDone();
    }

    public boolean hasNextLevel(){
        return wall.hasLevel();
    }

    public Ball getBall(){
        return wall.ball;
    }

    public Brick[] getBrick(){
        return wall.bricks;
    }

    public Player getPlayer(){
        return wall.player;
    }
}
