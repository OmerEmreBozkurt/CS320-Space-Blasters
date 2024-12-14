public class Platform {
    private double x;

    public Platform(double initialPosition){
        this.x = initialPosition;
    }

    public double getX(){
        return x;
    }

    public void setX(double x) {
        this.x = x; //Setting the new coordinates of the platform
    }

    public void go_left() {
        this.x -= -1.5; // Moving the coordinates of the platform by 1.5 units to the left
    }

    public void go_right() {
        this.x += -1.5; // Moving the coordinates of the platform by 1.5 units to the right
    }


}
