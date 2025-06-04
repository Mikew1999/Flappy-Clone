package entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bird implements KeyListener {
    public int xPos;
    public int yPos;
    public Image image;
    public float gravity = 0.5f;
    public float yVelocity = gravity;

    public Bird(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/res/BirdWithWings.png")).getScaledInstance(50, 50, 0);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void update() {
        yVelocity += gravity;
        yPos += yVelocity;
    }

    public void paint(Graphics2D g2) {
        g2.drawImage(image, xPos, yPos, null);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() != 32) return;
        yVelocity = -8;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}