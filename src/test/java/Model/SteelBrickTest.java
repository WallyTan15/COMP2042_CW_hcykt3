package Model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class SteelBrickTest {
    SteelBrick steelBrick = new SteelBrick(new Point(), new Dimension(10,10));

    @Test
    public void makeSteelBrickFace(){
        Shape cementBrickFace;
        cementBrickFace = steelBrick.makeBrickFace(new Point(0,0), new Dimension(10,15));
        assertEquals(0,cementBrickFace.getBounds().getX());
        assertEquals(0,cementBrickFace.getBounds().getY());
        assertEquals(10,cementBrickFace.getBounds().getWidth());
        assertEquals(15,cementBrickFace.getBounds().getHeight());
    }

    @Test
    public void isSteelBrickBroken(){
        assertFalse(steelBrick.isBroken());
    }

    @Test
    public void repairSteelBrick(){
        steelBrick.impact();
        steelBrick.repair();
        assertEquals(1, steelBrick.getStrength());
        assertFalse(steelBrick.isBroken());
    }
}