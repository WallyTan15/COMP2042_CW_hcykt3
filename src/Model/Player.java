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

/**
 * Player is a class that deals with the function of the player.
 */
public class Player {


    public static final Color BORDER_COLOR = Color.GREEN.darker().darker();
    public static final Color INNER_COLOR = Color.GREEN;

    private static final int DEF_MOVE_AMOUNT = 5;

    private Rectangle playerFace;
    private Point ballPoint;
    private int moveAmount;
    private int min;
    private int max;

    /**
     * Player constructor initialize the values of the player when a player object is being created.
     * @param ballPoint  the position of the ball
     * @param width      the width of the player
     * @param height     the height of the player
     * @param container  the container of the screen
     */
    public Player(Point ballPoint,int width,int height,Rectangle container) {
        this.ballPoint = ballPoint;
        moveAmount = 0;
        playerFace = makeRectangle(width, height);
        min = container.x + (width / 2);
        max = min + container.width - width;

    }

    /**
     * makeRectangle is a method that create the features of the player.
     * @param width   the width of the player
     * @param height  the height of the player
     * @return        return the shape and size of the player
     */
    private Rectangle makeRectangle(int width,int height){
        Point p = new Point((int)(ballPoint.getX() - (width / 2)),(int)ballPoint.getY());
        return  new Rectangle(p,new Dimension(width,height));
    }

    /**
     * impact is a method that determines if the impact of the ball is occurred on the player.
     * @param b  the ball
     * @return   return the boolean value to show if the impact of the ball is occurred on the player
     */
    public boolean impact(Ball b){
        return playerFace.contains(b.getPosition()) && playerFace.contains(b.down) ;
    }

    /**
     * move is a method that deals with the movement of the player and the position of the ball.
     */
    public void move(){
        double x = ballPoint.getX() + moveAmount;
        if(x < min || x > max)
            return;
        ballPoint.setLocation(x,ballPoint.getY());
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }

    /**
     * moveLeft is a method that control the movement of the player towards left by setting the value of moveAmount.
     */
    public void moveLeft(){
        moveAmount = -DEF_MOVE_AMOUNT;
    }

    /**
     * moveRight is a method that control the movement of the player towards right by setting the value of moveAmount.
     */
    public void movRight(){
        moveAmount = DEF_MOVE_AMOUNT;
    }

    /**
     * stop is a method that stop the movement of the player by setting the value of moveAmount to 0.
     */
    public void stop(){
        moveAmount = 0;
    }

    /**
     * getPlayerFace is a method that returns the playerFace.
     * Encapsulation for playerFace.
     * @return  return the shape and size of the player
     */
    public Shape getPlayerFace(){
        return  playerFace;
    }

    /**
     * moveTo is a method that moves the player and ball to the specific position.
     * @param p the specified position to move
     */
    public void moveTo(Point p){
        ballPoint.setLocation(p);
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }
}
