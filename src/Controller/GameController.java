package Controller;

import Model.Ball;
import Model.Brick;
import Model.Player;
import Model.Wall;

/**
 * GameController is a class that act as the coordinator between Wall class and the classes in package View.
 */
public class GameController {
    private Wall wall;

    /**
     * GameController constructor gets the Wall object reference.
     * @param wall  the Wall object
     */
    public GameController(Wall wall){
        this.wall = wall;
    }

    /**
     * getWall is a method that returns the Wall object.
     * @return  return the Wall object
     */
    public Wall getWall(){
        return wall;
    }

    /**
     * nextGameLevel is a method that invokes the nextLevel method in the Wall class.
     * Move the current level to the next level.
     */
    public void nextGameLevel(){
        wall.nextLevel();
    }

    /**
     * move is a method that invokes the move method in the Wall class.
     * Deal with the movement of the player and ball.
     */
    public void move(){
        wall.move();
    }

    /**
     * findWallImpacts is a method that invokes the findImpacts method in the Wall class.
     * Update the status of the ball and brick when impact is occurred between them.
     */
    public void findWallImpacts(){
        wall.findImpacts();
    }

    /**
     * getCurrentBrickCount is a method that invokes the getBrickCount method in the Wall class.
     * @return  return the number of the bricks
     */
    public int getCurrentBrickCount(){
        return wall.getBrickCount();
    }

    /**
     * getCurrentBallCount is a method that invokes the getBallCount method in the Wall class.
     * @return  return the number of the balls
     */
    public int getCurrentBallCount(){
        return wall.getBallCount();
    }

    /**
     * isCurrentBallLost is a method that invokes the isBallLost method in the Wall class.
     * @return  return the boolean value to show if the ball is lost
     */
    public boolean isCurrentBallLost(){
        return wall.isBallLost();
    }

    /**
     * isCurrentBallEnd is a method that invokes the ballEnd method in the Wall class.
     * @return  return the boolean value to show if there is any remaining balls
     */
    public boolean isCurrentBallEnd(){
        return wall.ballEnd();
    }

    /**
     * wallReset is a method that invokes the wallReset method in the Wall class.
     * Reset the status of brick and wall.
     */
    public void wallReset(){
        wall.wallReset();
    }

    /**
     * ballReset is a method that invokes the ballReset method in the Wall class.
     * Reset the status of the ball and player.
     */
    public void ballReset(){
        wall.ballReset();
    }

    /**
     * isGameDone is a method that invokes the isDone method in the Wall class.
     * @return return the boolean value to show if the wall is completely destroyed
     */
    public boolean isGameDone(){
        return wall.isDone();
    }

    /**
     * hasNextLevel is a method that invokes the hasLevel method in the Wall class.
     * @return  return the boolean value to show if there are next levels
     */
    public boolean hasNextLevel(){
        return wall.hasLevel();
    }

    /**
     * getBall is a method that gets the Ball object reference from the Wall class.
     * @return  the Ball object
     */
    public Ball getBall(){
        return wall.ball;
    }

    /**
     * getBrick is a method that gets the Brick object reference from the Wall class.
     * @return  the Brick object
     */
    public Brick[] getBrick(){
        return wall.bricks;
    }

    /**
     * getPlayer is a method that gets the Player object reference from the Wall class.
     * @return  the Player object
     */
    public Player getPlayer(){
        return wall.player;
    }
}
