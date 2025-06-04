package main;

import entity.Pipe;

public class CollisionDetection {
    public GamePanel gamePanel;

    public CollisionDetection(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    
    public boolean checkCollision() {
        return (checkHitWall() || checkHitPipe());
    }

    public boolean checkHitWall() {
        return (
            gamePanel.bird.yPos <= 25 ||
            gamePanel.bird.yPos >= (gamePanel.screenHeight - 25)
        );
    }

    public boolean checkHitPipe() {
        for (Pipe pipe : gamePanel.pipeHandler.pipes) {
            // ignore pipes to the left of bird
            if (pipe.leftPos < (gamePanel.bird.xPos - 25)) continue;
            // check if birds position is within any pipe
            if (
                pipe.leftPos >= (gamePanel.bird.xPos - 25) &&
                pipe.rightPos <= (gamePanel.bird.xPos + 25) &&
                (
                    (
                        pipe.bottom &&
                        gamePanel.bird.yPos >= (gamePanel.screenHeight - 25) &&
                        gamePanel.bird.yPos <= (gamePanel.screenHeight - 25 - pipe.height)
                    ) ||
                    (
                        !pipe.bottom && gamePanel.bird.yPos <= pipe.height
                    )
                )
            ) return true;
        }
        return false;
    }
}
