package main;

import javax.swing.JPanel;

import entity.Bird;
import entity.PipeHandler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {

    public final int tileSize = 50;
    public final int maxScreenCol = 25;
    public final int maxScreenRow = 10;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    public PipeHandler pipeHandler;
    public Bird bird;
    public CollisionDetection collisionDetection;
    public ScoreHandler scoreHandler;

    private Thread gameThread;
    private boolean gameRunning = true;

    public GamePanel() {
        this.pipeHandler = new PipeHandler(this);
        this.bird = new Bird(screenWidth / 2, screenHeight / 2);
        this.scoreHandler = new ScoreHandler();
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(bird);
        this.setFocusable(true);
        this.collisionDetection = new CollisionDetection(this);
        startGameThread();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public int getIntWithinRange(int min, int max) {
        Random random = new Random();
        int numOfMultiplesOf10 = (max - min) / 10 + 1;
        return min + (random.nextInt(numOfMultiplesOf10) * 10);
    };

    @Override
    public void run() {
        double drawInterval = 1000000000/60;  // 1 second / 60
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (gameRunning) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }

            if (scoreHandler.lastScoreTime < (currentTime - scoreHandler.scoreIncreaseAfter)) {
                scoreHandler.incrementScore();
                scoreHandler.lastScoreTime = currentTime;
            }

        }
    }

    public void update() {
        if (this.bird.gravity != 0) {
            this.pipeHandler.update();
        }
        this.bird.update();
        if (collisionDetection.checkCollision()) {
            gameRunning = false;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);
        g2.setColor(Color.BLUE);
        if (gameRunning) {
            this.pipeHandler.paint(g2);
            this.bird.paint(g2);
            g2.drawString(String.format("Current Score: %d", scoreHandler.getScore()), screenWidth - 150, 30);
        } else {
            g2.drawString(String.format("Game over!! Final score: %d", scoreHandler.getScore()), screenWidth / 2, (screenHeight     / 2) - 50);
        }
        g2.dispose();
    }
}