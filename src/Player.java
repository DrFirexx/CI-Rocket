import java.awt.*;

public class Player {

    public int[] x = new int[3];
    public int[] y = new int[3];

    public int width;
    public int height;

    public int velocity;

    public Player() {
        this.x[0] = 512;
        this.y[0] = 300;
    }

    public void createPlayer() {
        this.x[1] = this.x[0] - this.width;
        this.x[2] = this.x[0] + this.width;
        this.y[1] = this.y[0] - this.height;
        this.y[2] = this.y[0] - this.height;
        this.width = 12;
        this.height = 20;
        this.velocity = 25;
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.drawPolygon(x, y,3);
        graphics.setColor(Color.WHITE);
        graphics.fillPolygon(x, y,3);
    }
}
