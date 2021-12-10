package Model;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 * Ball is an abstract class that deals with all the general functions of ball.
 */
abstract public class Ball {

    private Shape ballFace;

    private static Point2D center;

    Point2D up;
    Point2D down;
    Point2D left;
    Point2D right;

    private Color border;
    private Color inner;

    private int speedX;
    private int speedY;

    /**
     * Ball constructor initialize the basic values and features of the ball when a ball object is being created.
     * Set the position of ball at the beginning of game.
     * Set the color of the ball.
     * Set the initial speedX and speedY at the beginning of game.
     * @param center   the center of the ball
     * @param radiusA  the width of the ball
     * @param radiusB  the height of the ball
     * @param inner    the inner color of the ball
     * @param border   the border color of the ball
     */
    public Ball(Point2D center,int radiusA,int radiusB,Color inner,Color border){
        this.center = center;

        up = new Point2D.Double();
        down = new Point2D.Double();
        left = new Point2D.Double();
        right = new Point2D.Double();

        up.setLocation(center.getX(),center.getY()-(radiusB / 2));
        down.setLocation(center.getX(),center.getY()+(radiusB / 2));

        left.setLocation(center.getX()-(radiusA /2),center.getY());
        right.setLocation(center.getX()+(radiusA /2),center.getY());


        ballFace = makeBall(center,radiusA,radiusB);
        this.border = border;
        this.inner  = inner;
        speedX = 0;
        speedY = 0;
    }

    /**
     * makeBall is an abstract method that create the ball.
     * @param center   the center of the ball
     * @param radiusA  the width of the ball
     * @param radiusB  the height of the ball
     * @return         return the shape and size of the ball
     */
    protected abstract Shape makeBall(Point2D center,int radiusA,int radiusB);

    /**
     * move is a method to show how the ball is being displayed when ball is in motion.
     */
    public void move(){
        RectangularShape tmp = (RectangularShape) ballFace;
        center.setLocation((center.getX() + speedX),(center.getY() + speedY));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        setPoints(w,h);


        ballFace = tmp;
    }

    /**
     * setSpeed is a method that set the ball speed in x-axis and y-axis directions.
     * @param x  speed of the ball in x-axis direction
     * @param y  speed of the ball in y-axis direction
     */
    public void setSpeed(int x,int y){
        speedX = x;
        speedY = y;
    }

    /**
     * setXSpeed is a method tha set the ball speed in x-axis direction.
     * @param s speed of the ball in x-axis direction
     */
    public void setXSpeed(int s){
        speedX = s;
    }

    /**
     * setYSpeed is a method tha set the ball speed in y-axis direction.
     * @param s speed of the ball in y-axis direction
     */
    public void setYSpeed(int s){
        speedY = s;
    }

    /**
     * reverseX is a method that reverse the ball's movement in x-axis direction.
     */
    public void reverseX(){
        speedX *= -1;
    }

    /**
     * reverseY is a method that reverse the ball's movement in y-axis direction.
     */
    public void reverseY(){
        speedY *= -1;
    }

    /**
     * getBorderColor is a method that returns the border color of the ball.
     * Encapsulation for border.
     * @return  return the border color of the ball
     */
    public Color getBorderColor(){
        return border;
    }

    /**
     * getBorderColor is a method that returns the inner color of the ball.
     * Encapsulation for inner.
     * @return  return the inner color of the ball
     */
    public Color getInnerColor(){
        return inner;
    }

    /**
     * getPosition is a method that returns the position of the center of the ball.
     * Encapsulation for center.
     * @return  return the center position of the the ball
     */
    public static Point2D getPosition(){
        return center;
    }

    /**
     * getBallFace is a method that returns the ballFace.
     * @return  return the shape, size and initial position of the ball
     */
    public Shape getBallFace(){
        return ballFace;
    }

    /**
     * moveTo is a method that moves the ball to the p position.
     * @param p  the position of center of the ball
     */
    public void moveTo(Point p){
        center.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        ballFace = tmp;
    }

    /**
     * setPoints is a method that set the up, down, left and right points of ball.
     * @param width   the width of the ball
     * @param height  the height of the ball
     */
    private void setPoints(double width,double height){
        up.setLocation(center.getX(),center.getY()-(height / 2));
        down.setLocation(center.getX(),center.getY()+(height / 2));

        left.setLocation(center.getX()-(width / 2),center.getY());
        right.setLocation(center.getX()+(width / 2),center.getY());
    }

    /**
     * getSpeedX is a method that returns the speed of the ball in x-axis direction.
     * Encapsulation for speedX.
     * @return  the speed of the ball in x-axis direction
     */
    public int getSpeedX(){
        return speedX;
    }

    /**
     * getSpeedY is a method that returns the speed of the ball in y-axis direction.
     * Encapsulation for speedY.
     * @return the speed of the ball in y-axis direction
     */
    public int getSpeedY(){
        return speedY;
    }

    /**
     * setCenter is a method that set the position of the center of the ball.
     * @param p  point that center of the ball to be set
     */
    public static void setCenter(Point2D p) {
        center.setLocation(p);
    }


}
