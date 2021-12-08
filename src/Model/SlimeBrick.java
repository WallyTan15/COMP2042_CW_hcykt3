package Model;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

public class SlimeBrick extends Brick{
    private static final String NAME = "SlimeBrick";
    private static final Color DEF_INNER = new Color(100, 199, 90);
    private static final Color DEF_BORDER = Color.yellow;
    private static final int SLIME_STRENGTH = 3;
    private static final double SLIMEX_PROBABILITY = 0.6;

    private Random rnd;
    private Crack crack;
    private Shape brickFace;

    public SlimeBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,SLIME_STRENGTH);
        rnd = new Random();
        crack = new Crack(this, DEF_CRACK_DEPTH, DEF_STEPS);
        brickFace = super.brickFace;
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    @Override
    public boolean setImpact(Point2D point, int dir) {
        randomSpawn();
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

    @Override
    public Shape getBrick() {
        return brickFace;
    }

    public void randomSpawn() {
        double ballCenterX;
        double ballCenterY;
        int rndX;
        int rndY;

        do {
            rndX = rnd.nextInt(400);

            if(rnd.nextDouble() < SLIMEX_PROBABILITY){
                rndX *= -1;
            }

            ballCenterX = Ball.getPosition().getX() + rndX;

            if(ballCenterX <= 600 && ballCenterX >= 0)
                break;
        } while(ballCenterX > 600 || ballCenterX < 0);

        rndY = rnd.nextInt(100);
        ballCenterY = Ball.getPosition().getY() + rndY;

        Point2D p = new Point2D.Double(ballCenterX, ballCenterY);

        Ball.setCenter(p);
    }

    private void updateBrick(){
        if(!super.isBroken()){
            GeneralPath gp = crack.draw();
            gp.append(super.brickFace,false);
            brickFace = gp;
        }
    }

    public void repair(){
        super.repair();
        crack.reset();
        brickFace = super.brickFace;
    }
}
