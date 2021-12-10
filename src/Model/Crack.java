package Model;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Crack is a class that deals with all the features and functions of crack.
 */
public class Crack {
    private static final int CRACK_SECTIONS = 3;
    private static final double JUMP_PROBABILITY = 0.7;

    public static final int LEFT = 10;
    public static final int RIGHT = 20;
    public static final int UP = 30;
    public static final int DOWN = 40;
    public static final int VERTICAL = 100;
    public static final int HORIZONTAL = 200;

    private Brick brick;

    private GeneralPath crack;
    private Random rnd;

    private int crackDepth;
    private int steps;

    /**
     * Crack constructor sets the basic values of crack.
     * Get the brick.
     * Create a Random object.
     * @param brick       the brick
     * @param crackDepth  the depth of the crack
     * @param steps       the steps of the brick
     */
    public Crack(Brick brick, int crackDepth, int steps){

        this.brick = brick;
        crack = new GeneralPath();
        this.crackDepth = crackDepth;
        this.steps = steps;
        rnd = new Random();

    }

    /**
     * draw is a method that returns the crack.
     * @return  return crack
     */
    public GeneralPath draw(){
        return crack;
    }

    /**
     * reset is a method that reset the crack to empty.
     */
    public void reset(){
        crack.reset();
    }

    /**
     * makeCrack is a method that make the crack by setting the starting point and ending point in the brick.
     * @param point      the point of impact
     * @param direction  the direction of impact
     */
    protected void makeCrack(Point2D point, int direction){
        Rectangle bounds = brick.getBrickFace().getBounds();

        Point impact = new Point((int)point.getX(),(int)point.getY());
        Point start = new Point();
        Point end = new Point();


        switch(direction){
            case LEFT:
                start.setLocation(bounds.x + bounds.width, bounds.y);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                Point tmp = makeRandomPoint(start,end,VERTICAL);
                makeCrack(impact,tmp);

                break;
            case RIGHT:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x, bounds.y + bounds.height);
                tmp = makeRandomPoint(start,end,VERTICAL);
                makeCrack(impact,tmp);

                break;
            case UP:
                start.setLocation(bounds.x, bounds.y + bounds.height);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                tmp = makeRandomPoint(start,end,HORIZONTAL);
                makeCrack(impact,tmp);
                break;
            case DOWN:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x + bounds.width, bounds.y);
                tmp = makeRandomPoint(start,end,HORIZONTAL);
                makeCrack(impact,tmp);

                break;

        }
    }

    /**
     * makeCrack is a method that draw the crack based on the path specified.
     * @param start  the starting point of the crack
     * @param end    the ending point of the crack
     */
    protected void makeCrack(Point start, Point end){

        GeneralPath path = new GeneralPath();


        path.moveTo(start.x,start.y);

        double w = (end.x - start.x) / (double)steps;
        double h = (end.y - start.y) / (double)steps;

        int bound = crackDepth;
        int jump  = bound * 5;

        double x,y;

        for(int i = 1; i < steps;i++){

            x = (i * w) + start.x;
            y = (i * h) + start.y + randomInBounds(bound);

            if(inMiddle(i,CRACK_SECTIONS,steps))
                y += jumps(jump,JUMP_PROBABILITY);

            path.lineTo(x,y);

        }

        path.lineTo(end.x,end.y);
        crack.append(path,true);
    }

    /**
     * randomInBounds is a method that generates and returns the random integer with given bounds.
     * @param bound  the bound value
     * @return       return the random integer
     */
    private int randomInBounds(int bound){
        int n = (bound * 2) + 1;
        return rnd.nextInt(n) - bound;
    }

    /**
     * inMiddle is a method that returns the boolean value to show if it is in middle.
     * @param i          the checking integer
     * @param steps      the steps of the brick
     * @param divisions  the divisions of the brick
     * @return           return the boolean value to show if it is in middle
     */
   private boolean inMiddle(int i,int steps,int divisions){
        int low = (steps / divisions);
        int up = low * (divisions - 1);

        return  (i > low) && (i < up);
    }

    /**
     * jumps is a method that determine if the crack path is jumped.
     * @param bound        the bound value
     * @param probability  the jump probability
     * @return             return the random integer
     */
    private int jumps(int bound,double probability){

        if(rnd.nextDouble() > probability)
            return randomInBounds(bound);
        return  0;

    }

    /**
     * makeRandomPoint is a method that generate the random point.
     * @param from       the starting point
     * @param to         the ending point
     * @param direction  the direction of the crack
     * @return           return the random point
     */
    private Point makeRandomPoint(Point from,Point to, int direction){

        Point out = new Point();
        int pos;

        switch(direction){
            case HORIZONTAL:
                pos = rnd.nextInt(to.x - from.x) + from.x;
                out.setLocation(pos,to.y);
                break;
            case VERTICAL:
                pos = rnd.nextInt(to.y - from.y) + from.y;
                out.setLocation(to.x,pos);
                break;
        }
        return out;
    }
}
