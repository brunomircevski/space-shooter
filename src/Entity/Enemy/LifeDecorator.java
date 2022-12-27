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
    public void draw(Graphics2D g2) {
        super.draw(g2);
        g2.setColor(Color.CYAN);
        g2.fillRect(enemy.getX() - enemy.getSize()/4, enemy.getY() - enemy.getSize()/4, enemy.getSize()/2, enemy.getSize()/2);
    }
}
