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

    public Vector2D position;

    public int width;
    public int height;

    public Vector2D velocity;

    private static int timeIntervalEnemy = 0;

    private List<BulletEnemy> bulletEnemies;
    private int timeIntervalBullet = 0;

    public static Random random = new Random();

    public Enemy() {
        this.position = new Vector2D();
        this.velocity = new Vector2D();
        this.bulletEnemies = new ArrayList<>();
    }

    public static void createEnemy() {
        if (timeIntervalEnemy == 500) {
            Enemy enemy = new Enemy();
            enemy.position.set(random.nextInt(1024), random.nextInt(600));
            enemy.image = GameCanvas.loadImage("resources/images/circle.png");
            enemy.width = 20;
            enemy.height = 20;
            enemy.velocity.set(random.nextInt(2)+1, 0);
            GameCanvas.enemies.add(enemy);
            timeIntervalEnemy = 0;
        } else {
            timeIntervalEnemy += 1;
        }
    }

    public void render(Graphics graphics) {
        graphics.drawImage(this.image, (int) this.position.x, (int) this.position.y, this.width, this.height, null);
        this.bulletEnemies.forEach(bulletEnemy -> bulletEnemy.render(graphics));
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
            bulletEnemy.position.set(this.position.x, this.position.y);
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
