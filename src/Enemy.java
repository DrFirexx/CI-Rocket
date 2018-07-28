import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Enemy {

    public BufferedImage image;

    public int x;
    public int y;

    public int width;
    public int height;

    public int velocity;

    private List<BulletEnemy> bulletEnemies;

    private static int timeIntervalEnemy = 0;
    private int timeIntervalBullet = 0;
    public static Random random = new Random();

    public void render(Graphics graphics) {
        graphics.drawImage(this.image, this.x, this.y, this.width, this.height, null);
        this.bulletEnemies.forEach(bulletEnemy -> bulletEnemy.render(graphics));
    }

    public static void createEnemy() {
        if (timeIntervalEnemy == 500) {
            Enemy enemy = new Enemy();
            enemy.x = random.nextInt(1024);
            enemy.y = random.nextInt(600);
            enemy.image = GameCanvas.loadImage("resources/images/circle.png");
            enemy.width = 20;
            enemy.height = 20;
            enemy.velocity = random.nextInt(2) + 1;
            GameCanvas.enemies.add(enemy);
            timeIntervalEnemy = 0;
        } else {
            timeIntervalEnemy += 1;
        }
    }

    public Enemy() {
        this.bulletEnemies = new ArrayList<>();
    }

    public void run() {
        this.shoot();
    }

    public void shoot() {
        if (this.timeIntervalBullet == 30) {
            BulletEnemy bulletEnemy = new BulletEnemy();
            try {
                bulletEnemy.image = ImageIO.read(new File("resources/images/circle.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            bulletEnemy.position.set(this.x, this.y);
            bulletEnemy.velocity.set(2, 0);
            this.bulletEnemies.add(bulletEnemy);
            this.timeIntervalBullet = 0;
        } else {
            this.timeIntervalBullet += 1;
        }

        this.bulletEnemies.forEach(bulletEnemy -> bulletEnemy.run());
    }

//    public void run() {
//        double angle = Math.atan2(GameCanvas.player.y[0] - this.y, GameCanvas.player.x[0] - this.x);
//        this.x += velocity * Math.cos(angle);
//        this.y += velocity * Math.sin(angle);
//    }
}
