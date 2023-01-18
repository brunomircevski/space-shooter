package Entity.Enemy;

import java.awt.*;

public abstract class EnemyDecorator extends Enemy implements IEnemy {
    protected Enemy enemy;

    public EnemyDecorator(Enemy _enemy) {
        enemy = _enemy;
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
