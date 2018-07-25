import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameCanvas extends JPanel {

    // Bien de chua anh
    private BufferedImage starImage;
    private BufferedImage playerImage;
    private BufferedImage enemyImage;
    // Tao 1 backbuffer
    private BufferedImage backBuffered;

    public int positionXStar = 1024;
    public int positionYStar = 200;
    public int positionXPlayer = 500;
    public int positionYPlayer = 300;
    public int positionXEnemy = 200;
    public int positionYEnemy = 200;

    // Khoi tao co ve cho backbuffer
    private Graphics graphics;

    public GameCanvas() {
        // snake case: user_name
        // camel case: userName

        // Cho de ve tat ca moi thu len
        this.setSize(1024, 600);
        this.setupBackBuffered();
        this.setupCharacter();

        // draw
        this.setVisible(true);
    }

    private void setupBackBuffered() {
        // The front buffer is displayed on screen and you draw to the back buffer,
        // then you swap them when you're done drawing (so the image in the back buffer is shown).
        // This is so the user never sees a partially drawn image as you're updating the screen
        // (they see the last complete image, i.e. the front buffer).

        // Khoi tao backbuffer
        this.backBuffered = new BufferedImage(1024, 600, BufferedImage.TYPE_INT_ARGB);
        // Lay co ve tu backbuffer => Dung graphics la ve len backbuffer
        this.graphics = this.backBuffered.getGraphics();
    }

    private void setupCharacter() {
        // Load anh
        this.starImage = this.loadImage("resources/images/star.png");
        this.playerImage = this.loadImage("resources/images/circle.png");
        this.enemyImage = this.loadImage("resources/images/circle.png");
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

        // Ve ngoi sao
        this.graphics.drawImage(this.starImage, positionXStar, positionYStar,5,5,null);
        // Ve player
        this.graphics.drawImage(this.playerImage, positionXPlayer, positionYPlayer, 20, 20, Color.RED, null);
        // Ve enemy
        this.graphics.drawImage(this.enemyImage, positionXEnemy, positionYEnemy, 20, 20, null);

        this.repaint();
    }

    private void renderBackground() {
        // Ve background
        this.graphics.setColor(Color.BLACK);
        this.graphics.fillRect(0, 0, 1024, 600);
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            return null;
        }

    }

}
