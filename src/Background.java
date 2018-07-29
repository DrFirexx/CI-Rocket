import java.awt.*;

public class Background {

    public Vector2D position;

    public int width;
    public int height;

    public Background() {
        this.position = new Vector2D();
        this.position.x = 0;
        this.position.y = 0;
        this.width = 1024;
        this.height = 600;
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect((int) this.position.x, (int) this.position.y,this.width,this.height);
    }
}
