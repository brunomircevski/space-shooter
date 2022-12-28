package Difficulty;

public class DifficultyHard extends Difficulty {

    public DifficultyHard() {
        name = "Hard";
    }

    public boolean getChance() {
        return Math.random() < 0.01 + difficultyCounter/1000.0;
    }

    public int getMaxEnemies() {
        return 10 + difficultyCounter/900;
    }
}
