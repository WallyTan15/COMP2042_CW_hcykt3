package Model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class SlimeBrickTest {
    SlimeBrick slimeBrick = new SlimeBrick(new Point(), new Dimension(10,10));

    @Test
    public void makeSlimeBrickFace(){
        Shape cementBrickFace;
        cementBrickFace = slimeBrick.makeBrickFace(new Point(0,0), new Dimension(10,15));
        assertEquals(0,cementBrickFace.getBounds().getX());
        assertEquals(0,cementBrickFace.getBounds().getY());
        assertEquals(10,cementBrickFace.getBounds().getWidth());
        assertEquals(15,cementBrickFace.getBounds().getHeight());
    }

    @Test
    public void isSlimeBrickBroken(){
        assertFalse(slimeBrick.isBroken());
    }

    @Test
    public void impactSlimeBrick(){
        slimeBrick.impact();
        assertEquals(2,slimeBrick.getStrength());
        slimeBrick.impact();
        assertFalse(slimeBrick.isBroken());
    }

    @Test
    public void repairSlimeBrick(){
        slimeBrick.impact();
        slimeBrick.impact();
        slimeBrick.repair();
        assertEquals(3, slimeBrick.getStrength());
    }
}