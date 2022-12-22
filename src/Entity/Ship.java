package Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ship extends Entity {
    public int size = 100;
    private BufferedImage shipBasic, shipLaser, shipExplosive, image;
    public String shipType = "basic";

    public Ship(int _x, int _y) {
        x = _x;
        y = _y;

        try {
            shipBasic = ImageIO.read(new File("res/images/basic_ship.png"));
            shipLaser = ImageIO.read(new File("res/images/laser_ship.png"));
            shipExplosive = ImageIO.read(new File("res/images/explosion_ship.png"));

            image = shipBasic;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2, int animationStep) {
        g2.drawImage(image, x, y, x + size, y + size, 0, image.getWidth() * animationStep, image.getWidth(), image.getWidth() * (animationStep + 1), null);
    }

    public void changeShip() {
        switch (shipType) {
            case "basic":
                shipType = "laser";
                image = shipLaser;
                break;
            case "laser":
                shipType = "explosive";
                image = shipExplosive;
                break;
            case "explosive":
                shipType = "basic";
                image = shipBasic;
                break;
        }
    }
}
