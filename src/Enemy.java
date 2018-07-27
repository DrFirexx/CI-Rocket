import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
public class Enemy {

    public BufferedImage image;

    public int x;
    public int y;

    public int width;
    public int height;

    public int velocity;

    private static int timeIntervalEnemy = 0;
    public static Random random = new Random();

    public void render(Graphics graphics) {
        if (this.image != null) {
            graphics.drawImage(this.image, this.x, this.y, this.width, this.height, null);
        }
    }

    public static void createEnemy() {
        if (timeIntervalEnemy == 500) {
            Enemy enemy = new Enemy();
            enemy.x = random.nextInt(1024);
            enemy.y = random.nextInt(600);
            enemy.image = GameCanvas.loadImage("resources/images/circle.png");
            enemy.width = 20;
            enemy.height = 20;
            enemy.velocity = random.nextInt(5);
            GameCanvas.enemies.add(enemy);
            timeIntervalEnemy = 0;
        } else {
            timeIntervalEnemy += 1;
        }
    }

    public void run() {
        double angle = Math.atan2(GameCanvas.player.y[0] - this.y, GameCanvas.player.x[0] - this.x);
        this.x += velocity * Math.cos(angle);
        this.y += velocity * Math.sin(angle);
    }
}
