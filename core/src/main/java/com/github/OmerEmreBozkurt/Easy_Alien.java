package com.github.OmerEmreBozkurt;

import com.badlogic.gdx.graphics.Texture;

public class Easy_Alien extends Alien {
    public Easy_Alien(Game game) {
        super(AlienType.Easy, game);
        life = 1;
        sprite.setTexture(texturePicker());
    }

    public int death(){
        alive = false;
        game.updateScore(points);
        return 50;
    }

    Texture texturePicker(){
        if (power_up == null ) {
            return new Texture("Tier1.png");
        } else if (power_up.getPowerUpType() == 1) {
            return new Texture("Tier1Pow1.png");
        } else if (power_up.getPowerUpType() == 2) {
            return new Texture("Tier1Pow2.png");
        } else if (power_up.getPowerUpType() == 3) {
            return new Texture("Tier1Pow3.png");
        }
        return new Texture("BaseAlien.png");
    }
}
