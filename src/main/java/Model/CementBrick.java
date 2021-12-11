package Model;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

/**
 * CementBrick is the subclass of the Brick class.
 * Inherits the variable and methods from Brick class.
 * Deal with the features of CementBrick.
 */
public class CementBrick extends Brick {


    private static final String NAME = "Cement Brick";
    private static final Color DEF_INNER = new Color(147, 147, 147);
    private static final Color DEF_BORDER = new Color(217, 199, 175);
    private static final int CEMENT_STRENGTH = 2;

    private Crack crack;
    private Shape brickFace;

    /**
     * CementBrick constructor call the constructor of its parent class, Brick.
     * Pass the name, position, size and strength to Brick class.
     * Create a Crack object.
     * Get the brickFace from Brick class.
     * @param point  the position of the brick
     * @param size   the size of the brick
     */
    public CementBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CEMENT_STRENGTH);
        crack = new Crack(this,DEF_CRACK_DEPTH,DEF_STEPS);
        brickFace = super.brickFace;
    }

    /**
     * makeBrickFace is an override method that create the features of brick.
     * @param pos   the position of the brick
     * @param size  the size of the brick
     * @return      return the shape and size of the brick
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /**
     * setImpact is an override method that update the status of the brick after impact is occurred.
     * @param point  the point of impact
     * @param dir    the direction of impact
     * @return       return the boolean values to show if the brick is broken
     */
    @Override
    public boolean setImpact(Point2D point, int dir) {
        if(super.isBroken())
            return false;
        super.impact();
        if(!super.isBroken()){
            crack.makeCrack(point,dir);
            updateBrick();
            return false;
        }
        return true;
    }

    /**
     * getBrick is an override method that returns the brickFace.
     * Encapsulation for brickFace.
     * @return  return the shape and the size of the brick.
     */
    @Override
    public Shape getBrick() {
        return brickFace;
    }

    /**
     * updateBrick is a method that updating the brickFace by drawing the crack if the brick is not broken.
     */
    private void updateBrick(){
        if(!super.isBroken()){
            GeneralPath gp = crack.draw();
            gp.append(super.brickFace,false);
            brickFace = gp;
        }
    }

    /**
     * repair is a method that reset the status and brickFace of the brick.
     */
    public void repair(){
        super.repair();
        crack.reset();
        brickFace = super.brickFace;
    }
}
