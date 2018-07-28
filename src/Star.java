import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Star {

    public BufferedImage image;

    public Vector2D position;

    public int width;
    public int height;

    public Vector2D velocity;

    private static int timeIntervalStar = 0;
    private static Random random = new Random();

    public Star() {
        this.position = new Vector2D();
        this.velocity = new Vector2D();
    }

    public static void createStar() {
        if (timeIntervalStar == 30) {
            Star star = new Star();
            star.position.set(1024, random.nextInt(600));
            star.image = GameCanvas.loadImage("resources/images/star.png");
            star.width = 5;
            star.height = 5;
            star.velocity.set(random.nextInt(10)+1, 0);
            GameCanvas.stars.add(star);
            timeIntervalStar = 0;
        } else {
            timeIntervalStar += 1;
        }
    }

    public void render(Graphics graphics) {
        if (this.image != null) {
            graphics.drawImage(
                    this.image,
                    (int) this.position.x,
                    (int) this.position.y,
                    this.width,
                    this.height,
                    null);
        }
    }

    public void run() {
        this.position.subtractBy(this.velocity);
    }
}
