package com.github.OmerEmreBozkurt;

import com.badlogic.gdx.graphics.Texture;

public class Hard_Alien extends Alien {
    public Hard_Alien() {
        super(AlienType.Hard);
        life = 3;
        sprite.setTexture(texturePicker());
    }

    public int death(){
        alive = false;
        return 150;
    }

    Texture texturePicker(){
        switch (life) {
            case 3:
                if (power_up == null) {
                    return new Texture("Tier3.png");
                } else if (power_up.getPowerUpType() == 1) {
                    return new Texture("Tier3Pow1.png");
                } else if (power_up.getPowerUpType() == 2) {
                    return new Texture("Tier3Pow2.png");
                } else if (power_up.getPowerUpType() == 3) {
                    return new Texture("Tier3Pow3.png");
                }
            case 2:
                if (power_up == null) {
                    return new Texture("Tier2.png");
                } else if (power_up.getPowerUpType() == 1) {
                    return new Texture("Tier2Pow1.png");
                } else if (power_up.getPowerUpType() == 2) {
                    return new Texture("Tier2Pow2.png");
                } else if (power_up.getPowerUpType() == 3) {
                    return new Texture("Tier2Pow3.png");
                }
            case 1:
                if (power_up == null) {
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
