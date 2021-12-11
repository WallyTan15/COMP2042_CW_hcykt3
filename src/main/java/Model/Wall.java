/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Model;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Wall is a class that deals with the implementations of ball, player, score, crack, brick and levels.
 */
public class Wall {

    private static final int LEVELS_COUNT = 7;

    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;
    private static final int SLIME = 4;

    private Random rnd;
    private Rectangle area;

    public Brick[] bricks;
    public Crack crack;
    public Ball ball;
    public Player player;
    public Score score;

    private Brick[][] levels;
    private int level;

    private Point startPoint;
    private int brickCount;
    private int ballCount;
    private boolean ballLost;

    /**
     * Wall constructor initialize the values and integrate the classes in model to create a fundamental gameplay output.
     * Create levels.
     * Create a Random object.
     * Create a Ball object.
     * Create a Player object.
     * Create a Score object.
     * @param drawArea             the area of the game screen in rectangle
     * @param brickCount           the total number of bricks in a single level
     * @param lineCount            the total number of lines
     * @param brickDimensionRatio  the dimension ratio of the brick
     * @param ballPos              the initial position of the ball
     */
    public Wall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos){

        this.startPoint = new Point(ballPos);

        levels = makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);
        level = 0;

        ballCount = 3;
        ballLost = false;

        rnd = new Random();

        makeBall(ballPos);
        int speedX,speedY;
        do{
            speedX = rnd.nextInt(5) - 2;
        }while(speedX == 0);
        do{
            speedY = -rnd.nextInt(3);
        }while(speedY == 0);

        ball.setSpeed(speedX,speedY);

        player = new Player((Point) ballPos.clone(),150,10, drawArea);

        area = drawArea;

        score = new Score();

    }

    /**
     * makeSingleTypeLevel is a method that create the wall with one type of the brick.
     * @param drawArea        the area of the game screen in rectangle
     * @param brickCnt        the total number of bricks in a single level
     * @param lineCnt         the total number of lines
     * @param brickSizeRatio  the dimension ratio of the brick
     * @param type            the type of the brick
     * @return                return a three-layer wall with single type of brick
     */
    private Brick[] makeSingleTypeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int type){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            double x = (i % brickOnLine) * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize,type);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = new ClayBrick(p,brickSize);
        }
        return tmp;

    }

    /**
     * makeChessboardLevel is a method that create the wall with two type of the brick.
     * @param drawArea        the area of the game screen in rectangle
     * @param brickCnt        the total number of bricks in a single level
     * @param lineCnt         the total number of lines
     * @param brickSizeRatio  the dimension ratio of the brick
     * @param typeA           the type of the brick
     * @param typeB           the type of the brick
     * @return                return a three-layer wall with two type of brick
     */
    private Brick[] makeChessboardLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            int posX = i % brickOnLine;
            double x = posX * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
            tmp[i] = b ?  makeBrick(p,brickSize,typeA) : makeBrick(p,brickSize,typeB);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize,typeA);
        }
        return tmp;
    }

    /**
     * makeBall is a method that create the RubberBall object at the specified position.
     * @param ballPos  the initial position of the ball
     */
    private void makeBall(Point2D ballPos){
        ball = new RubberBall(ballPos);
    }

    /**
     * makeLevels is a method that create the levels and determines the type of the wall.
     * @param drawArea             the area of the game screen in rectangle
     * @param brickCount           the total number of bricks in a single level
     * @param lineCount            the total number of lines
     * @param brickDimensionRatio  the dimension ratio of the brick
     * @return                     return the levels with different wall
     */
    private Brick[][] makeLevels(Rectangle drawArea,int brickCount,int lineCount,double brickDimensionRatio){
        Brick[][] tmp = new Brick[LEVELS_COUNT][];
        tmp[0] = makeSingleTypeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY);
        tmp[1] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,CEMENT);
        tmp[2] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,STEEL);
        tmp[3] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,SLIME);
        tmp[4] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,STEEL,CEMENT);
        tmp[5] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,SLIME,CEMENT);
        tmp[6] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,SLIME,STEEL);
        return tmp;
    }

    /**
     * move is a method that deals with the movement of the player and ball.
     */
    public void move(){
        player.move();
        ball.move();
    }

    /**
     * findImpacts is a method that update the status of the ball and brick when impact is occurred between them.
     */
    public void findImpacts(){
        if(player.impact(ball)){
            ball.reverseY();
        }
        else if(impactWall()){
            /*for efficiency reverse is done into method impactWall
            * because for every brick program checks for horizontal and vertical impacts
            */
            brickCount--;
            score.addScore();
        }
        else if(impactBorder()) {
            ball.reverseX();
        }
        else if(ball.getPosition().getY() < area.getY()){
            ball.reverseY();
        }
        else if(ball.getPosition().getY() > area.getY() + area.getHeight()){
            ballCount--;
            ballLost = true;
        }
    }

    /**
     * impactWall is a method that returns the boolean value to determine if the impact between the ball and brick is occurred.
     * @return  return the boolean value to show if the impact is occurred
     */
    private boolean impactWall(){
        for(Brick b : bricks){
            switch(b.findImpact(ball)) {
                //Vertical Impact
                case Brick.UP_IMPACT:
                    ball.reverseY();
                    return b.setImpact(ball.getDownPoint(), crack.UP);
                case Brick.DOWN_IMPACT:
                    ball.reverseY();
                    return b.setImpact(ball.getUpPoint(),crack.DOWN);

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    ball.reverseX();
                    return b.setImpact(ball.getRightPoint(),crack.RIGHT);
                case Brick.RIGHT_IMPACT:
                    ball.reverseX();
                    return b.setImpact(ball.getLeftPoint(),crack.LEFT);
            }
        }
        return false;
    }

    /**
     * impactBorder is a method that return the boolean values.
     * to show if the impact between ball and game border is occurred.
     * @return  return the boolean value to show if the impact is occurred
     */
    private boolean impactBorder(){
        Point2D p = ball.getPosition();
        return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())));
    }

    /**
     * getBrickCount is a method that returns the number of the bricks.
     * @return  return the number of the bricks
     */
    public int getBrickCount(){
        return brickCount;
    }

    /**
     * getBallCount is a method that returns the number of the balls.
     * @return  return the number of the balls
     */
    public int getBallCount(){
        return ballCount;
    }

    /**
     * isBallLost is a method that returns the boolean value to show if the ball is lost.
     * @return  return the boolean value to show if the ball is lost
     */
    public boolean isBallLost(){
        return ballLost;
    }

    /**
     * ballReset is a method that reset the status of the ball and player.
     */
    public void ballReset(){
        player.moveTo(startPoint);
        ball.moveTo(startPoint);
        int speedX,speedY;
        do{
            speedX = rnd.nextInt(5) - 2;
        }while(speedX == 0);
        do{
            speedY = -rnd.nextInt(3);
        }while(speedY == 0);

        ball.setSpeed(speedX,speedY);
        ballLost = false;
    }

    /**
     * wallReset is the method that reset the status of brick and wall.
     */
    public void wallReset(){
        for(Brick b : bricks)
            b.repair();
        brickCount = bricks.length;
        ballCount = 3;
    }

    /**
     * ballEnd is a method that returns that boolean value to show if there is any remaining balls.
     * @return  returns the boolean value to show if there is any remaining balls
     */
    public boolean ballEnd(){
        return ballCount == 0;
    }

    /**
     * isDone is a method that return the boolean value to show if the wall is completely destroyed.
     * @return  return the boolean value to show if the wall is completely destroyed
     */
    public boolean isDone(){
        return brickCount == 0;
    }

    /**
     * nextLevel is a method that move the current level to the next level.
     */
    public void nextLevel(){
        bricks = levels[level++];
        this.brickCount = bricks.length;
    }

    /**
     * hasLevel is a method that returns the boolean value to show if there are next levels.
     * @return  return the boolean value to show if there are next levels
     */
    public boolean hasLevel(){
        return level < levels.length;
    }

    /**
     * setBallXSpeed is a method that calls the method in ball to set the speed of the ball in x-axis direction.
     * @param s  speed of the ball
     */
    public void setBallXSpeed(int s){
        ball.setXSpeed(s);
    }

    /**
     * setBallYSpeed is a method that calls the method in ball to set the speed of the ball in y-axis direction.
     * @param s  speed of the ball
     */
    public void setBallYSpeed(int s){
        ball.setYSpeed(s);
    }

    /**
     * resetBallCount is a method that resets the number of ball.
     */
    public void resetBallCount(){
        ballCount = 3;
    }

    /**
     * makeBrick is a method that create the different Brick object based on the specified type.
     * @param point  the position of the brick
     * @param size   the size of the brick
     * @param type   the type of the brick
     * @return       return the Brick object
     */
    private Brick makeBrick(Point point, Dimension size, int type){
        Brick out;
        switch(type){
            case CLAY:
                out = new ClayBrick(point,size);
                break;
            case STEEL:
                out = new SteelBrick(point,size);
                break;
            case CEMENT:
                out = new CementBrick(point, size);
                break;
            case SLIME:
                out = new SlimeBrick(point,size); //this
                break;
            default:
                throw  new IllegalArgumentException(String.format("Unknown Type:%d\n",type));
        }
        return  out;
    }

}
