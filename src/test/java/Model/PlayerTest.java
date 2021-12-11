package Model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player player = new Player(new Point(300,430),150,10,new Rectangle(0,0,600,450));

    @Test
    public void impactPlayer(){
        Ball b = new RubberBall(new Point(300,430));
        assertTrue(player.impact(b));
    }

    @Test
    public void movePlayerLeft(){
        player.moveLeft();
        player.move();
        assertEquals(430, player.getPlayerFace().getBounds().getY());
        assertEquals(220, player.getPlayerFace().getBounds().getX());
    }

    @Test
    public void movePlayerRight(){
        player.moveRight();
        player.move();
        assertEquals(430, player.getPlayerFace().getBounds().getY());
        assertEquals(230, player.getPlayerFace().getBounds().getX());
    }

    @Test
    public void stopPlayer(){
        player.stop();
        player.move();
        assertEquals(430, player.getPlayerFace().getBounds().getY());
        assertEquals(225, player.getPlayerFace().getBounds().getX());
    }

    @Test
    public void movePlayerTo(){
        player.moveTo(new Point (100,430));
        assertEquals(430, player.getPlayerFace().getBounds().getY());
        assertEquals(25, player.getPlayerFace().getBounds().getX());
    }
}