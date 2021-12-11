package Model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CementBrickTest {
    CementBrick cementBrick = new CementBrick(new Point(), new Dimension(10,10));

    @Test
    public void makeCementBrickFace(){
        Shape cementBrickFace;
        cementBrickFace = cementBrick.makeBrickFace(new Point(0,0), new Dimension(10,15));
        assertEquals(0,cementBrickFace.getBounds().getX());
        assertEquals(0,cementBrickFace.getBounds().getY());
        assertEquals(10,cementBrickFace.getBounds().getWidth());
        assertEquals(15,cementBrickFace.getBounds().getHeight());
    }

    @Test
    public void isCementBrickBroken(){
        assertFalse(cementBrick.isBroken());
    }

    @Test
    public void impactCementBrick(){
        cementBrick.impact();
        assertEquals(1,cementBrick.getStrength());
        cementBrick.impact();
        assertTrue(cementBrick.isBroken());
    }

    @Test
    public void repairCementBrick(){
        cementBrick.impact();
        cementBrick.repair();
        assertEquals(2, cementBrick.getStrength());
    }
}