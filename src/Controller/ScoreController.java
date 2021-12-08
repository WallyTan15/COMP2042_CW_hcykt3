package Controller;

import Model.Score;

public class ScoreController {
    private Score score;

    public ScoreController(Score score) {
        this.score = score;
    }

    public int getCurrentScore(){
        return  score.getScore();
    }

    public void copyGameRecord(int[] record){
        score.copyRecord(record);
    }

    public void writeScoreFile(){
        score.writeFile();
    }
}
