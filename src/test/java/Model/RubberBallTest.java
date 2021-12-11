package Model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class RubberBallTest {
    RubberBall rubberBall = new RubberBall(new Point());

    @Test
    public void setRubberBallSpeed(){
        rubberBall.setSpeed(10,15);
        assertEquals(10, rubberBall.getSpeedX());
        assertEquals(15, rubberBall.getSpeedY());
    }

    @Test
    public void setRubberBallSpeedX(){
        rubberBall.setXSpeed(10);
        assertEquals(10, rubberBall.getSpeedX());
    }

    @Test
    public void setRubberBallSpeedY(){
        rubberBall.setYSpeed(10);
        assertEquals(10, rubberBall.getSpeedY());
    }

    @Test
    public void reverseRubberBallSpeedX(){
        rubberBall.setXSpeed(10);
        rubberBall.reverseX();
        assertEquals(-10, rubberBall.getSpeedX());
        rubberBall.reverseX();
        assertEquals(10, rubberBall.getSpeedX());
    }

    @Test
    public void reverseRubberBallSpeedY(){
        rubberBall.setYSpeed(10);
        rubberBall.reverseY();
        assertEquals(-10, rubberBall.getSpeedY());
        rubberBall.reverseY();
        assertEquals(10, rubberBall.getSpeedY());
    }

    @Test
    public void setRubberBallCenter(){
        rubberBall.setCenter(new Point(10,15));
        assertEquals(10,rubberBall.getPosition().getX());
        assertEquals(15,rubberBall.getPosition().getY());
    }

    @Test
    public void makeRubberBall(){
        Shape ballFace;
        ballFace = rubberBall.makeBall(new Point(0,0), 10,15);
        assertEquals(-5,ballFace.getBounds().getX());
        assertEquals(-7,ballFace.getBounds().getY());
        assertEquals(10,ballFace.getBounds().getWidth());
        assertEquals(15,ballFace.getBounds().getHeight());
    }

    @Test
    public void moveRubberBall(){
        rubberBall.setXSpeed(10);
        rubberBall.setYSpeed(10);
        rubberBall.move();
        assertEquals(5,rubberBall.getBallFace().getBounds().getX());
        assertEquals(5,rubberBall.getBallFace().getBounds().getY());
    }

    @Test
    public void moveRubberBallTo(){
        rubberBall.moveTo(new Point(10,15));
        assertEquals(5,rubberBall.getBallFace().getBounds().getX());
        assertEquals(10,rubberBall.getBallFace().getBounds().getY());
    }
}