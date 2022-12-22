package Entity.Enemy;

public class EnemyFactory {
    public Enemy createEnemy() {
        int random = (int) ((Math.random() * 100) + 1);
        if(random < 70) return new EnemySmall();
        if(random < 90) return new EnemyMedium();
        return new EnemyBig();
    }
}
