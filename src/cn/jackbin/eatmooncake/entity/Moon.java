package cn.jackbin.eatmooncake.entity;

/**
 * 障碍物-月亮，碰到会死亡
 */
public class Moon extends BaseGameObj {
    private int speed = 10;

    public Moon(int x, int y, String imageFile) {
        super(x, y, imageFile, BaseGameObj.CIRCLE);
    }


    public void move() {
        setY(y += speed);
    }


    // getter and setter
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
