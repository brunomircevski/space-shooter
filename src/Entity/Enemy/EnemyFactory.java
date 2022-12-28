package Entity.Enemy;

import Game.GamePanel;

public class EnemyFactory {

    private GamePanel gp;

    public EnemyFactory(GamePanel _gp) {
        gp = _gp;
    }

    public Enemy createEnemy() {
        Enemy e;

        int random = (int) ((Math.random() * 100) + 1);
        if(random < 70) e = new EnemySmall();
        else if(random < 90) e = new EnemyMedium();
        else e = new EnemyBig();

        if(Math.random() < 0.1) e = new SpeedDecorator(e);
        if(Math.random() < 0.1) e = new LifeDecorator(e);
        if(Math.random() < 0.025) e = new BonusDecorator(e, gp);

        return new EnemyProxy(e, gp);
    }
}
