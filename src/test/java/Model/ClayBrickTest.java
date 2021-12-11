package Model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ClayBrickTest {
    ClayBrick clayBrick = new ClayBrick(new Point(0,0), new Dimension(10,10));

    @Test
    public void makeClayBrickFace(){
        Shape cementBrickFace;
        cementBrickFace = clayBrick.makeBrickFace(new Point(0,0), new Dimension(10,15));
        assertEquals(0,cementBrickFace.getBounds().getX());
        assertEquals(0,cementBrickFace.getBounds().getY());
        assertEquals(10,cementBrickFace.getBounds().getWidth());
        assertEquals(15,cementBrickFace.getBounds().getHeight());
    }

    @Test
    public void isClayBrickBroken(){
        assertFalse(clayBrick.isBroken());
    }

    @Test
    public void impactClayBrick(){
        clayBrick.impact();
        assertEquals(0,clayBrick.getStrength());
        assertTrue(clayBrick.isBroken());
    }

    @Test
    public void repairClayBrick(){
        clayBrick.impact();
        clayBrick.repair();
        assertEquals(1, clayBrick.getStrength());
        assertFalse(clayBrick.isBroken());
    }
}