package base;

import java.awt.event.KeyEvent;

public class KeyListener implements java.awt.event.KeyListener {

    static public KeyListener instance = new KeyListener();

    private String command;

    private KeyListener() {}

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            command = "turn right clockwise";
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            command = "turn left anticlockwise";
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            command = "speed up";
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            command = "speed dơwn";
        }
    }

    public String getCommand() {
        return this.command;
    }

    public void resetCommand() {
        this.command = null;
    }
}
