package Difficulty;

public abstract class Difficulty {
    protected int difficultyCounter = 0;
    public String name;

    public boolean getChance() {
        return false;
    }

    public int getMaxEnemies() {
        return 10;
    }

    public void update() {
        difficultyCounter++;
    }
}
