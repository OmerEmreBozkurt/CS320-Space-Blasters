package com.github.OmerEmreBozkurt;

public class Medium_Alien extends Alien {
    public Medium_Alien() {
        super(AlienType.Medium);
        life = 2;
    }

    public int death(){
        alive = false;
        return 100;
    }
}
