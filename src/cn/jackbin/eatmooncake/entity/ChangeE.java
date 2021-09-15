package cn.jackbin.eatmooncake.entity;

public class ChangeE extends BaseGameObj
{
    private final int frameWith;

    public ChangeE(int x, int y, String imageFile, int frameWith) {
        super(x, y, imageFile, BaseGameObj.RECTANGLE);
        this.frameWith = frameWith;
    }

    public void move(int deltaX)
    {
        // 判断是否会超出左右边界
        int nextX = getX() + deltaX;
        if (nextX + getWidth() > frameWith) {
            nextX = frameWith - getWidth();
        } else if (nextX < 0) {
            nextX = 0;
        }
        setX(nextX);
    }
}
