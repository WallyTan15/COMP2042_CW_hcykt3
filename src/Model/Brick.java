package Model;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Brick is an abstract class that deals with all the general functions of brick.
 */
abstract public class Brick  {

    public static final int MIN_CRACK = 1;
    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;

    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;

    private String name;
    Shape brickFace;

    private Color border;
    private Color inner;

    private int fullStrength;
    private int strength;

    private boolean broken;

    /**
     * Brick constructor initialize the basic values and features of the brick when a brick object is being created.
     * Set the name of the brick.
     * Set the color of the brick.
     * Set the strength of the brick.
     * @param name      the name of the brick
     * @param pos       the position of the brick
     * @param size      the size of the brick
     * @param border    the border color of the brick
     * @param inner     the inner color of the brick
     * @param strength  the strength of the  brick
     */
    public Brick(String name, Point pos,Dimension size,Color border,Color inner,int strength){

        broken = false;
        this.name = name;
        brickFace = makeBrickFace(pos,size);
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;

    }

    /**
     * makeBall is an abstract method that create the brick.
     * @param pos   the position of the brick
     * @param size  the size of the brick
     * @return      return the shape and size of the brick
     */
    protected abstract Shape makeBrickFace(Point pos,Dimension size);

    /**
     * setImpact is a method that update the status of the brick after impact is occurred.
     * @param point  the point of impact
     * @param dir    the direction of impact
     * @return       return the boolean values to show if the brick is broken
     */
    public boolean setImpact(Point2D point , int dir){
        if(broken)
            return false;
        impact();
        return  broken;
    }

    /**
     * getBrick is an abstract method that returns the brickFace.
     * @return  return the shape and the size of the brick
     */
    public abstract Shape getBrick();

    /**
     * getBorderColor is a method that returns the border color of the brick.
     * Encapsulation for border.
     * @return  return the border color of the brick
     */
    public Color getBorderColor(){
        return  border;
    }

    /**
     * getInnerColor is a method that returns the inner color of the brick.
     * Encapsulation for inner.
     * @return  return the inner color of the brick
     */
    public Color getInnerColor(){
        return inner;
    }

    /**
     * findImpact is a method that decides the impact direction between the ball and the brick.
     * @param b  the ball
     * @return   return the direction of the impact
     */
    public final int findImpact(Ball b){
        if(broken)
            return 0;
        int out  = 0;
        if(brickFace.contains(b.getRightPoint()))
            out = LEFT_IMPACT;
        else if(brickFace.contains(b.getLeftPoint()))
            out = RIGHT_IMPACT;
        else if(brickFace.contains(b.getUpPoint()))
            out = DOWN_IMPACT;
        else if(brickFace.contains(b.getDownPoint()))
            out = UP_IMPACT;
        return out;
    }

    /**
     * isBroken is a method that determines if the brick is broken and returns the result.
     * @return  return the boolean values to show if the brick is broken
     */
    public final boolean isBroken(){
        return broken;
    }

    /**
     * repair is a method that reset the status of the brick.
     */
    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    /**
     * impact is the method that reduce the strength of the brick and set the boolean value broken.
     */
    public void impact(){
        strength--;
        broken = (strength == 0);
    }

    /**
     * getBrickFace is a method that returns brickFace.
     * Encapsulation for brickFace.
     * @return  return the shape and size of  the brick
     */
    public Shape getBrickFace(){
        return brickFace;
    }

    /**
     * getStrength is a method that returns the strength of the brick
     * @return  return the strength of the brick
     */
    public int getStrength() {
        return strength;
    }
}





