import java.awt.*;

public class EnemyFollow {

    public Vector2D position;
    public Vector2D velocity;
    public Renderer renderer;


    public EnemyFollow() {
        this.position = new Vector2D();
        this.velocity = new Vector2D();
        this.renderer = new ImageRenderer("resources/images/circle.png", 20, 20);
    }

    public void render(Graphics graphics) {
        this.renderer.render(graphics, this.position);
    }

    public void run() {
        this.position.addUp(this.velocity);
    }

    public void update(Vector2D position) {
        this.velocity.set(position.subtract(this.position).normalize()).multiply(1.5f);
    }

}
