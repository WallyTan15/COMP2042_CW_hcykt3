package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Score {
    private int score;
    private int[] gameRecord;

    private FileWriter fileWriter;

    private String path = "resources/Score.txt";
    private File file;

    public Score() {

        gameRecord = new int[10];

        file = new File(path);
    }

    public int getScore(){
        return score;
    }

    public void addScore(){
        score += 1;
    }

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

    public void writeFile() {
        sortRanking();
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

    public void sortRanking() {
        int tmp;
        int gameScore = score;
        readFile();
        for(int i = 0; i < 10; i++){
            if(gameScore > gameRecord[i]){

                tmp = gameRecord[i];
                gameRecord[i] = gameScore;
                gameScore = tmp;
            }
        }
    }
}
