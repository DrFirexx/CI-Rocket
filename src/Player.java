import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class Player {

    public Vector2D position;
    public Vector2D velocity;

    public List<Vector2D> vertices;

    private Polygon polygon;

    public double angle = 0.0;

    public Player() {
        this.position = new Vector2D();
        this.velocity = new Vector2D();

        this.vertices = Arrays.asList(
                new Vector2D(),
                new Vector2D(0, 16),
                new Vector2D(20,8)
        );
        this.polygon = new Polygon();
    }

    public void run() {
        this.position.addUp(this.velocity);

//        public void playerMovement() {
//            if (player.x[0] > 1024){
//                player.x[0] = 0 + player.width;
//                player.y[0] = random.nextInt(600);
//            } else if (player.x[0] < 0) {
//                player.x[0] = 1024;
//                player.y[0] = random.nextInt(600);
//            }
//
//            if (player.y[0] > 600) {
//                player.y[0] = 0 + player.height;
//                player.x[0] = random.nextInt(1024);
//            } else if (player.y[0] < 0) {
//                player.y[0] = 600 - player.height;
//                player.x[0] = random.nextInt(1024);
//            }
//        }
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.RED);
        this.updateTriangle();
        graphics.drawPolygon(this.polygon);
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
}

