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

    private Thread gameThread;

    public GamePanel() {
        this.pipeHandler = new PipeHandler(this);
        this.bird = new Bird(screenWidth / 2, screenHeight / 2);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        // this.addMouseMotionListener(game.mouseHandler);
        // this.addMouseListener(game.mouseHandler);
        this.setFocusable(true);
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
        while (true) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        this.pipeHandler.update();
        this.bird.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);
        this.pipeHandler.paint(g2);
        this.bird.paint(g2);
        g2.dispose();
    }
}