package Entity.Enemy;

import java.awt.*;

public class LifeDecorator extends EnemyDecorator {
    public LifeDecorator(Enemy _enemy) {
        super(_enemy);
    }

    @Override
    public void damage(int amount) {
        super.damage(amount/2);

    }

    @Override
    public int getHealth() {
        return 2 * super.getHealth();
    }

    @Override
    public void draw(Graphics2D g2, int animationStep) {
        g2.setColor(Color.CYAN);
        g2.drawOval(enemy.getX() - enemy.getSize()/2 - 10, enemy.getY() - enemy.getSize()/2 - 10, enemy.getSize() + 20, enemy.getSize() + 20);
        super.draw(g2, animationStep);
    }
}
