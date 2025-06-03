package entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bird {
    public int xPos;
    public int yPos;
    public Image image;
    public boolean isJumping = false;
    public int descendSpeed = 5;

    public Bird(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/res/Bird.png")).getScaledInstance(50, 50, 0);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void update() {
        if (!isJumping) {
            this.yPos += descendSpeed;
        }
    }

    public void paint(Graphics2D g2) {
        g2.drawImage(image, xPos, yPos, null);
    }
}
