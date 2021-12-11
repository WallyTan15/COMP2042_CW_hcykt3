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
 * SteelBrick is the subclass of the Brick class.
 * Inherits the variable and methods from Brick class.
 * Deal with the features of SteelBrick.
 */
public class SteelBrick extends Brick {

    private static final String NAME = "Steel Brick";
    private static final Color DEF_INNER = new Color(203, 203, 201);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int STEEL_STRENGTH = 1;
    private static final double STEEL_PROBABILITY = 0.4;

    private Random rnd;
    private Shape brickFace;

    /**
     * SteelBrick constructor call the constructor of its parent class, Brick.
     * Pass the name, position, size and strength to Brick class.
     * Create a Random object.
     * Get the brickFace from Brick class.
     * @param point  the position of the brick
     * @param size   the size of the brick
     */
    public SteelBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,STEEL_STRENGTH);
        rnd = new Random();
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
     * getBrick is an override method that returns the brickFace.
     * Encapsulation for brickFace.
     * @return  return the shape and the size of the brick
     */
    @Override
    public Shape getBrick() {
        return brickFace;
    }

    /**
     * setImpact is an method that update the status of the brick after impact is occurred.
     * @param point  the point of impact
     * @param dir    the direction of impact
     * @return       return the boolean values to show if the brick is broken
     */
    public  boolean setImpact(Point2D point , int dir){
        if(super.isBroken())
            return false;
        impact();
        return  super.isBroken();
    }

    /**
     * impact is the method that reduce the strength of the brick and set the boolean value broken with a possibility.
     */
    public void impact(){
        if(rnd.nextDouble() < STEEL_PROBABILITY){
            super.impact();
        }
    }

}
