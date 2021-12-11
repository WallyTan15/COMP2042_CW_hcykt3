package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {
    Score score = new Score();

    @Test
    public void addGameScore(){
        score.addScore();
        assertEquals(1, score.getScore());
    }

    //Prerequisite: the first game record in file Score.txt is not zero or null
    @Test
    public void copyGameRecord(){
        score.readFile();
        int[] record = new int[10];
        score.copyRecord(record);
        assertTrue(record[0] > 0);
    }

    @Test
    public void resetGameScore(){
        score.addScore();
        score.addScore();
        assertEquals(2, score.getScore());
        score.resetScore();
        assertEquals(0, score.getScore());
    }
}