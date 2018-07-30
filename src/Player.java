import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Player {

    public Vector2D position;
    public Vector2D velocity;

    public List<Vector2D> vertices;

    private Polygon polygon;

    public double angle = 0.0;

    public List<BulletPlayer> bulletPlayers;
    private int timeIntervalBullet = 0;
    public Vector2D bulletVelocity = new Vector2D(5, 0);

    private Random random = new Random();

    public Player() {
        this.position = new Vector2D();
        this.velocity = new Vector2D();

        this.vertices = Arrays.asList(
                new Vector2D(),
                new Vector2D(0, 16),
                new Vector2D(20,8)
        );
        this.polygon = new Polygon();

        this.bulletPlayers = new ArrayList<>();
    }

    public void run() {
        this.position.addUp(this.velocity);

        if (this.position.x > 1024) {
                this.position.x = 0;
                this.position.y = random.nextInt(600);
        } else if (this.position.x < 0) {
                this.position.x = 1024;
                this.position.y = random.nextInt(600);
        }

        if (this.position.y > 600) {
                this.position.y = 0;
                this.position.x = random.nextInt(1024);
        } else if (this.position.y < 0) {
                this.position.y = 600;
                this.position.x = random.nextInt(1024);
        }

        this.shoot();
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.RED);
        this.updateTriangle();
        graphics.fillPolygon(this.polygon);

        this.bulletPlayers.forEach(bulletPlayer -> bulletPlayer.render(graphics));
    }

    private void updateTriangle() {
        this.polygon.reset();

        // Tinh trung tam tam giac
        Vector2D center = this.vertices
                .stream()
                .reduce(new Vector2D(), (v1, v2) -> v1.add(v2))
                .multiply(1.0f / (float)this.vertices.size())
                .rotate(this.angle);

        // Tinh vector di chuyen
        Vector2D translate = this.position.subtract(center);

        // Tinh dinh moi
        this.vertices
                .stream()
                .map(vector2D -> vector2D.rotate(angle))
                .map(vector2D -> vector2D.add(translate))
                .forEach(vector2D -> polygon.addPoint((int) vector2D.x, (int) vector2D.y));
    }

    public void shoot() {
        if (this.timeIntervalBullet == 10) {
            BulletPlayer bulletPlayer = new BulletPlayer();
            try {
                bulletPlayer.image = ImageIO.read(new File("resources/images/circle.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            bulletPlayer.position.set(this.position.x, this.position.y);
            bulletPlayer.velocity.set(bulletVelocity);
            this.bulletPlayers.add(bulletPlayer);
            this.timeIntervalBullet = 0;
        } else {
            this.timeIntervalBullet += 1;
        }

        this.bulletPlayers.forEach(bulletPlayer -> bulletPlayer.run());
    }


}

