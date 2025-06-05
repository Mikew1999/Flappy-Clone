package main;

public class ScoreHandler {
    private int score;
    public long lastScoreTime = System.nanoTime();
    public long scoreIncreaseAfter = 1000000000;

    public ScoreHandler() {
        this.score = 0;
    }

    public int incrementScore() {
        this.score += 1;
        return this.score;
    }

    public int getScore() {
        return this.score;
    }

    public void resetScore() {
        this.score = 0;
    }
}