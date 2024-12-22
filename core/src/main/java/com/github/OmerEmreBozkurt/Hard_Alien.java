package com.github.OmerEmreBozkurt;

public class Hard_Alien extends Alien {
    public Hard_Alien() {
        super(AlienType.Hard);
        life = 3;
    }

    public int death(){
        alive = false;
        return 150;
    }
}
