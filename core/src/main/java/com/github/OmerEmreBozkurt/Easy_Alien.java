package com.github.OmerEmreBozkurt;

public class Easy_Alien extends Alien {
    public Easy_Alien() {
        super(AlienType.Easy);
        life = 1;
    }

    public int death(){
        alive = false;
        return 50;
    }
}
