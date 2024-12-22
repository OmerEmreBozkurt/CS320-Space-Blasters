package com.github.OmerEmreBozkurt;

import com.badlogic.gdx.graphics.Texture;

public class Hard_Alien extends Alien {
    public Hard_Alien() {
        super(AlienType.Hard);
        sprite.setTexture(texturePicker());
        life = 3;
    }

    public int death(){
        alive = false;
        return 150;
    }

    Texture texturePicker(){
        if (power_up == null ) {
            return new Texture("Tier3.png");
        } else if (power_up.getPowerUpType() == 1) {
            return new Texture("Tier3Pow1.png");
        } else if (power_up.getPowerUpType() == 2) {
            return new Texture("Tier3Pow2.png");
        } else if (power_up.getPowerUpType() == 3) {
            return new Texture("Tier3Pow3.png");
        }
        return new Texture("libgdx.png");
    }
}
