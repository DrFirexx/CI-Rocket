import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameCanvas extends JPanel {

    public Background background;
    public static Player player;
    public static List<Star> stars;
    public static List<Enemy> enemies;

    // Tao 1 backbuffer
    private BufferedImage backBuffered;
    public Random random = new Random();

    // Khoi tao co ve cho backbuffer
    private Graphics graphics;

    public GameCanvas() {
        // Cho de ve tat ca moi thu len
        this.setSize(1024, 600);
        this.setupBackBuffered();
        this.setupBackground();
        this.setupCharacter();

        // draw
        this.setVisible(true);
    }

    private void setupBackBuffered() {
        // Khoi tao backbuffer
        this.backBuffered = new BufferedImage(1024, 600, BufferedImage.TYPE_INT_ARGB);
        // Lay co ve tu backbuffer => Dung graphics la ve len backbuffer
        this.graphics = this.backBuffered.getGraphics();
    }

    private void setupBackground() {
        this.background = new Background();
    }

    private void setupCharacter() {
        this.setupStar();
        this.setupPlayer();
        this.setupEnemy();
    }

    private void setupStar() {
        this.stars = new ArrayList<>();
    }

    private void setupPlayer() {

        this.player = new Player();
        this.player.position.set(200, 300);
        this.player.velocity.set(3.5f, 0);
    }

    private void setupEnemy() {
        this.enemies = new ArrayList<>();
    }

    // Noi de ve len trong gameCanvas
    @Override
    protected void paintComponent(Graphics g) {
        // Ve backuffer len day roi chi can lat len
        // g la but ve dung de lat
        g.drawImage(this.backBuffered, 0, 0, null);
    }

    // Ve het len backbuffer
    public void renderAll() {
        this.background.render(graphics);
        this.stars.forEach(star -> star.render(graphics));
        this.player.render(this.graphics);
        this.enemies.forEach(enemy -> enemy.render(graphics));
        this.repaint();
    }

    public void runAll() {
        this.player.run();

        Star.createStar();
        this.stars.forEach(star -> star.run());

        Enemy.createEnemy();
        this.enemies.forEach(enemy -> enemy.run());
    }

    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            return null;
        }
    }
}
