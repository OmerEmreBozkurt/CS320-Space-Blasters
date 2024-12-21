package com.github.OmerEmreBozkurt;

public class PlayerControlsManager {
    private Platform platform;
    private Game game;
    private int boundary_left;
    private int boundary_right;

    public PlayerControlsManager(Platform platform, Game game, int boundary_left, int boundary_right) {
        this.platform = platform;
        this.game = game;
        this.boundary_left = boundary_left;
        this.boundary_right = boundary_right;
    }

    public void processInput(String input) {
        if (input.equals("Left")) {
            platform.go_left();
        } else if (input.equals("Right")) {
            platform.go_right();
        }
    }

    public void updatePosition() {
        if (platform.getX() < boundary_left) {
            platform.setX(boundary_left);
        } else if (platform.getX() > boundary_right) {
            platform.setX(boundary_right);
        }
    }

//    public void handleGameState() {
//        if (game.getBallCount() <= 0) {
//            game.stop(); // Stop the game logic
//        }
//
//        Power_up activePowerUp = game.getActivePowerUp();
//        if (activePowerUp != null && activePowerUp.isActive() && activePowerUp.getPowerUpType() == 1) {
//            activePowerUp.live_giver(game);
//        }
   // }
}
