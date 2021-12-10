package Controller;

import Model.Score;

/**
 * ScoreController is a class that act as the coordinator between Score class and the classes in package View.
 */
public class ScoreController {
    private Score score;

    /**
     * ScoreController constructor gets the Wall object reference.
     * @param score  the Score object
     */
    public ScoreController(Score score) {
        this.score = score;
    }

    /**
     * getCurrentScore is a method that invokes the getScore method in the Score class.
     * @return  return the game score
     */
    public int getCurrentScore(){
        return score.getScore();
    }

    /**
     * copyGameRecord is a method that invokes the copyRecord in the Score class.
     * Copy the game records from one array to another array.
     * @param record  the array for storing the game records
     */
    public void copyGameRecord(int[] record){
        score.copyRecord(record);
    }

    /**
     * writeScoreFile is a method that invokes the writeFile method in the Score class.
     * Write the new game record to the Score.txt in the right order ranking.
     */
    public void writeScoreFile(){
        score.writeFile();
    }

    public void resetGameScore(){ score.resetScore();}
}
