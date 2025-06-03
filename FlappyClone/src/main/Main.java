package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) throws Exception {
         System.out.println("Starting game...");
        JFrame window = new JFrame();
        window.setCursor(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Flappy clone");
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}