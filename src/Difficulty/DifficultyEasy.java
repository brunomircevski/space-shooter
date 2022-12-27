package Difficulty;

public class DifficultyEasy extends Difficulty{
    public DifficultyEasy() {
        name = "Easy";
    }

    public boolean getChance() {
        return Math.random() < 0.01 + difficultyCounter/3000.0;
    }

    public int getMaxEnemies() {
        return 5 + difficultyCounter/1200;
    }
}
