package entity;

import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.Queue;

import main.GamePanel;

public class PipeHandler {
    Queue<Pipe> pipes = new LinkedList<>();
    public GamePanel gamePanel;
    public int maxHeight;
    public int moveSpeed = 2;
    public int minHeight = 50;

    public PipeHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.maxHeight = (this.gamePanel.screenHeight / 2) - 50;
        setupPipes();
    }

    private void setupPipes() {
        int lastTopPos = (gamePanel.screenWidth / 2) + 40;
        int lastBottomPos = (gamePanel.screenWidth / 2) + 40;
        for (int i = 0; i < 25; i++) {
            if (Math.random() >= 0.5) {
                lastBottomPos += gamePanel.getIntWithinRange(40, 200);
                pipes.add(new Pipe(lastBottomPos, lastBottomPos + 20, gamePanel.getIntWithinRange(minHeight, maxHeight), true));
            } else {
                lastTopPos += gamePanel.getIntWithinRange(40, 200);
                pipes.add(new Pipe(lastTopPos, lastTopPos + 20, gamePanel.getIntWithinRange(minHeight, maxHeight), false));
            }
        }
    }

    public void update() {
        Pipe firstPipe = pipes.peek();
        if ((firstPipe.rightPos - moveSpeed) <= 0) {
            System.out.println("Checking first pipe: " + firstPipe);
            pipes.remove();
            pipes.add(
                new Pipe(
                    this.gamePanel.screenWidth + 50,
                    this.gamePanel.screenWidth + 70,
                    gamePanel.getIntWithinRange(minHeight, maxHeight),
                    Math.random() >= 0.5
                )
            );
        }

        for (Pipe pipe : pipes) {
            pipe.leftPos -= moveSpeed;
            pipe.rightPos -= moveSpeed;
        }
    }

    public void paint(Graphics2D g2) {
        for (Pipe pipe : pipes) {
            g2.fillRect(pipe.leftPos, pipe.bottom ? gamePanel.screenHeight - pipe.height : 0, pipe.rightPos - pipe.leftPos, pipe.height);
        }
    }
}
