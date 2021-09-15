package cn.jackbin.eatmooncake.entity;

/**
 * 得分物体-月饼，碰到加一分
 */
public class Cake extends BaseGameObj {

    private int speed = 5; // 月饼跑慢一点，利于得分

    public Cake(int x, int y, String imageFile) {
        super(x, y, imageFile, CIRCLE);
    }

    public void move() {
        ++speed;
        setY(y += speed);
    }

    // getter and setter
    public int getSpeed() {
        return speed;
    }
}
