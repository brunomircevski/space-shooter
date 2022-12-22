package Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public final class Settings {
    private static Settings INSTANCE;
    private final String filename = "settings.txt";
    private int score = -1;

    public void writeHighScore(int _score) {
        if(_score < score) return;

        score = _score;

        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(String.valueOf(score));
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
            e.printStackTrace();
        }
    }

    public int getHighScore() {
        if(score != -1) return score;

        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            if (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                score = Integer.parseInt(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading from file.");
            return 0;
        }

        return score;
    }

    private Settings() {
    }

    public static Settings getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Settings();
        }

        return INSTANCE;
    }
}
