package com.github.OmerEmreBozkurt;

import com.badlogic.gdx.graphics.Texture;

public class ExtraHard_Alien extends Alien {
    public ExtraHard_Alien(Game game) {
        super(AlienType.ExtraHard, game);
        life = 4;
        sprite.setTexture(texturePicker());
    }

    public int death(){
        alive = false;
        game.updateScore(points);
        return 200;
    }

    Texture texturePicker(){
        switch (life){
            case 4:
                if (power_up == null ) {
                    return new Texture("Tier4.png");
                } else if (power_up.getPowerUpType() == 1) {
                    return new Texture("Tier4Pow1.png");
                } else if (power_up.getPowerUpType() == 2) {
                    return new Texture("Tier4Pow2.png");
                } else if (power_up.getPowerUpType() == 3) {
                    return new Texture("Tier4Pow3.png");
                }
            case 3:
                if (power_up == null ) {
                    return new Texture("Tier3.png");
                } else if (power_up.getPowerUpType() == 1) {
                    return new Texture("Tier3Pow1.png");
                } else if (power_up.getPowerUpType() == 2) {
                    return new Texture("Tier3Pow2.png");
                } else if (power_up.getPowerUpType() == 3) {
                    return new Texture("Tier3Pow3.png");
                }
            case 2:
                if (power_up == null ) {
                    return new Texture("Tier2.png");
                } else if (power_up.getPowerUpType() == 1) {
                    return new Texture("Tier2Pow1.png");
                } else if (power_up.getPowerUpType() == 2) {
                    return new Texture("Tier2Pow2.png");
                } else if (power_up.getPowerUpType() == 3) {
                    return new Texture("Tier2Pow3.png");
                }
            case 1:
                if (power_up == null ) {
                    return new Texture("Tier1.png");
                } else if (power_up.getPowerUpType() == 1) {
                    return new Texture("Tier1Pow1.png");
                } else if (power_up.getPowerUpType() == 2) {
                    return new Texture("Tier1Pow2.png");
                } else if (power_up.getPowerUpType() == 3) {
                    return new Texture("Tier1Pow3.png");
                }
        }
        return new Texture("BaseAlien.png");
    }
}
