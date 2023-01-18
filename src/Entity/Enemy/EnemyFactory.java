package Entity.Enemy;

import Game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EnemyFactory {

    private GamePanel gp;

    private BufferedImage smallImg, mediumImg, bigImg;

    public EnemyFactory(GamePanel _gp) {
        gp = _gp;

        try {
            smallImg = ImageIO.read(new File("res/images/enemy_small.png"));
            mediumImg = ImageIO.read(new File("res/images/enemy_normal.png"));
            bigImg = ImageIO.read(new File("res/images/enemy_big.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Enemy createEnemy() {
        Enemy e;

        int random = (int) ((Math.random() * 100) + 1);
        if(random < 70) e = new EnemySmall(smallImg);
        else if(random < 90) e = new EnemyMedium(mediumImg);
        else e = new EnemyBig(bigImg);

        if(Math.random() < 0.1) e = new SpeedDecorator(e);
        if(Math.random() < 0.1) e = new LifeDecorator(e);
        if(Math.random() < 0.025) e = new BonusDecorator(e, gp);

        return new EnemyProxy(e, gp);
    }
}
