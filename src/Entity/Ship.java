package Entity;

import java.awt.*;

public class Ship extends Entity {
    public int height = 50, width = 100;
    public Ship(int _x, int _y) {
        x = _x;
        y = _y;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.fillRect(x, y, width, height);
    }
}
