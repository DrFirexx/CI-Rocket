import java.awt.*;

public class Background {

    public Vector2D position;
    public Renderer renderer;

    public Background() {
        this.position = new Vector2D();
        this.position.set(0, 0);
        this.renderer = new BackgroundRenderer(Color.BLACK, 1024, 600);
    }

    public void render(Graphics graphics) {
        this.renderer.render(graphics, this.position);
    }
}
