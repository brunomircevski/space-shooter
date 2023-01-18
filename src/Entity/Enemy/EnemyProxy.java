package Entity.Enemy;

import Game.GamePanel;

import java.awt.*;

public class EnemyProxy extends Enemy implements IEnemy{

    private Enemy enemy;
    private GamePanel gp;

    public EnemyProxy(Enemy _enemy, GamePanel _gp) {
        enemy = _enemy;
        gp = _gp;
    }

    @Override
    public void update() {
        enemy.update();
    }

    @Override
    public void draw(Graphics2D g2, int animationStep) {
        enemy.draw(g2, animationStep);
    }

    @Override
    public void damage(int amount) {
        enemy.damage(amount);
        gp.addPoints(10);
    }

    @Override
    public int getX() {
        return enemy.getX();
    }

    @Override
    public int getY() {
        return enemy.getY();
    }


    @Override
    public int getSize() {
        return enemy.getSize();
    }

    @Override
    public int getHealth() {
        return enemy.getHealth();
    }
}
