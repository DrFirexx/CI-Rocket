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

    public static List<Star> stars;
    public static Player player;
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
        this.renderBackground();
        this.stars.forEach(star -> star.render(graphics));
        this.player.render(graphics);
        this.enemies.forEach(enemy -> enemy.render(graphics));
        this.repaint();
    }

    public void playerMovement() {
        if (player.x[0] > 1024){
            player.x[0] = 0 + player.width;
            player.y[0] = random.nextInt(600);
        } else if (player.x[0] < 0) {
            player.x[0] = 1024;
            player.y[0] = random.nextInt(600);
        }

        if (player.y[0] > 600) {
            player.y[0] = 0 + player.height;
            player.x[0] = random.nextInt(1024);
        } else if (player.y[0] < 0) {
            player.y[0] = 600 - player.height;
            player.x[0] = random.nextInt(1024);
        }
    }

    public void runAll() {
        Star.createStar();
        this.stars.forEach(star -> star.run());

        player.createPlayer();
        this.playerMovement();

        Enemy.createEnemy();
        this.enemies.forEach(enemy -> enemy.run());
    }

    private void renderBackground() {
        // Ve background
        this.graphics.setColor(Color.BLACK);
        this.graphics.fillRect(0, 0, 1024, 600);
    }

    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            return null;
        }
    }
}
