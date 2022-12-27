package Entity.Enemy;

import java.awt.*;

public interface IEnemy {
    public void update();
    public void draw(Graphics2D g2);
    public void damage(int amount);
    public int getX();
    public int getY();
    public int getSize();
    public int getHealth();
}
