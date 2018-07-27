import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Star {

    public BufferedImage image;

    public int x;
    public int y;

    public int width;
    public int height;

    public int velocityX;

    private static int timeIntervalStar = 0;
    private static Random random = new Random();

    public static void createStar() {
        if (timeIntervalStar == 30) {
            Star star = new Star();
            star.x = 1024;
            star.y = random.nextInt(600);
            star.image = GameCanvas.loadImage("resources/images/star.png");
            star.width = 5;
            star.height = 5;
            star.velocityX = random.nextInt(3) + 1;
            GameCanvas.stars.add(star);
            timeIntervalStar = 0;
        } else {
            timeIntervalStar += 1;
        }
    }

    public void render(Graphics graphics) {
        if (this.image != null) {
            graphics.drawImage(this.image, this.x, this.y, this.width, this.height, null);
        }
    }

    public void run() {
        this.x -= this.velocityX;
    }
}
