package Model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class WallTest {
    Wall wall = new Wall(new Rectangle(0,0,600,450),30,3,6/2,new Point(300,430));

    @Test
    public void wallReset(){
        wall.nextLevel();
        wall.bricks[0].impact();
        wall.bricks[10].impact();
        assertTrue(wall.bricks[0].isBroken());
        assertTrue(wall.bricks[10].isBroken());
        wall.wallReset();
        assertFalse(wall.bricks[0].isBroken());
        assertFalse(wall.bricks[10].isBroken());
    }

    @Test
    public void setBallXSpeed(){
        wall.setBallXSpeed(10);
        assertEquals(10, wall.ball.getSpeedX());
    }

    @Test
    public void setBallYSpeed(){
        wall.setBallYSpeed(10);
        assertEquals(10, wall.ball.getSpeedY());
    }
}