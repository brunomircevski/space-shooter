package Entity.Enemy;

import Bonus.Bonus;
import Game.GamePanel;

import java.awt.*;

public class BonusDecorator extends EnemyDecorator {
    private GamePanel gp;
    public BonusDecorator(Enemy _enemy, GamePanel _gp) {
        super(_enemy);
        gp = _gp;
    }

    @Override
    public void damage(int amount) {
        super.damage(amount*100);
        gp.bonuses.push(new Bonus(10, 4 + (int)(Math.random() * 5), 200 + (int)(Math.random() * 200)));
    }

    @Override
    public void draw(Graphics2D g2, int animationStep) {
        g2.setColor(Color.PINK);
        g2.fillOval(enemy.getX(), enemy.getY(), enemy.getSize(), enemy.getSize());
    }
}
