package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Score is a class that deals with the record, sorting and calculations of the game score.
 */
public class Score {
    private int score;
    private int[] gameRecord;

    private FileWriter fileWriter;

    private String path = "resources/Score.txt";
    private File file;

    /**
     * Score constructor initialize the gameRecord.
     * Create a new File object.
     */
    public Score() {

        score = 0;

        gameRecord = new int[10];

        file = new File(path);
    }

    /**
     * getScore is a method that returns the game score.
     * Encapsulation for score.
     * @return  return the game score
     */
    public int getScore(){
        return score;
    }

    /**
     * addScore is a method that add the score.
     */
    public void addScore(){
        score += 1;
    }

    /**
     * readFile is a method that read the game records from the Score.txt.
     */
    public void readFile() {
        try{
            Scanner fileReader = new Scanner(file);

            int i = 0;
            while (fileReader.hasNextInt()) {
                gameRecord[i] = fileReader.nextInt();
                i++;
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * writeFile is a method that write the new game record to the Score.txt in the right order ranking.
     */
    public void writeFile() {

        try{
            fileWriter = new FileWriter(file);
            int i;
            for(i = 0; i < 10; i++) {
                fileWriter.write( gameRecord[i] + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * sortRanking is a method that sorts the game records in an array.
     */
    public void sortRanking() {
        int tmp;
        int gameScore = score;

        for(int i = 0; i < 10; i++){
            if(gameScore > gameRecord[i]){

                tmp = gameRecord[i];
                gameRecord[i] = gameScore;
                gameScore = tmp;
            }
        }
    }

    /**
     * copyRecord is a method that copy the game records from one array to another array.
     * @param record  the array for storing the game records
     */
    public void copyRecord(int[] record){
        System.arraycopy(gameRecord, 0, record, 0, gameRecord.length);
    }

    /**
     * resetScore is a method that resets the score to zero
     */
    public void resetScore(){
        score = 0;
    }
}
