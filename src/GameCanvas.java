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

        // The front buffer is displayed on screen and you draw to the back buffer,
        // then you swap them when you're done drawing (so the image in the back buffer is shown).
        // This is so the user never sees a partially drawn image as you're updating the screen
        // (they see the last complete image, i.e. the front buffer).

        // Khoi tao backbuffer
        this.backBuffered = new BufferedImage(1024, 600, BufferedImage.TYPE_INT_ARGB);
        // Lay co ve tu backbuffer => Dung graphics la ve len backbuffer
        this.graphics = this.backBuffered.getGraphics();

        // Load anh
        try {
            this.starImage = ImageIO.read(new File("resources/images/star.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            this.playerImage = ImageIO.read(new File("resources/images/circle.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            this.enemyImage = ImageIO.read(new File("resources/images/circle.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // draw
        this.setVisible(true);
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
        // Ve background
        this.graphics.setColor(Color.BLACK);
        this.graphics.fillRect(0, 0, 1024, 600);

        // Ve ngoi sao
        this.graphics.drawImage(this.starImage, positionXStar, positionYStar,5,5,null);
        // Ve player
        this.graphics.drawImage(this.playerImage, positionXPlayer, positionYPlayer, 20, 20, Color.RED, null);
        // Ve enemy
        this.graphics.drawImage(this.enemyImage, positionXEnemy, positionYEnemy, 20, 20, null);

        this.repaint();
    }
}
