package Model;

import java.awt.*;
import java.awt.Point;

/**
 * ClayBrick is the subclass of the Brick class.
 * Inherits the variable and methods from Brick class.
 * Deal with the features of ClayBrick.
 */
public class ClayBrick extends Brick {

    private static final String NAME = "Clay Brick";
    private static final Color DEF_INNER = new Color(178, 34, 34).darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int CLAY_STRENGTH = 1;

    /**
     * ClayBrick constructor call the constructor of its parent class, Brick.
     * Pass the name, position, size and strength to Brick class.
     * @param point  the position of the brick
     * @param size   the size of the brick
     */
    public ClayBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
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
     * getBrick is an override method that returns the brickFace.
     * Encapsulation for brickFace.
     * @return  return the shape and the size of the brick
     */
    @Override
    public Shape getBrick() {
        return super.brickFace;
    }


}
