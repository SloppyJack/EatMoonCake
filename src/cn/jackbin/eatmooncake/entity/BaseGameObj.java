package cn.jackbin.eatmooncake.entity;

import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.geom.Ellipse2D;
import java.io.IOException;
import java.io.File;
import java.awt.image.BufferedImage;
import java.net.URL;


/**
 * 基础对象
 */
public class BaseGameObj
{
    final protected static int RECTANGLE = 1;    // 长方形
    final protected static int CIRCLE = 2;    // 圆形

    protected int x;    // 画布中的位置
    protected int y;
    protected int width;
    protected int height;
    protected int type; // 类型
    protected BufferedImage image;

    public BaseGameObj(int x, int y, String imageFile, int type)
    {
        this.x = x;
        this.y = y;
        this.type = type;
        loadImage(imageFile);
        getImageDimensions();
    }

    public Shape getShape()
    {
        Shape shape = null;
        switch (type) {
            case RECTANGLE:
                shape = new Rectangle(x, y, width, height);
                break;
            case CIRCLE:
                shape = new Ellipse2D.Double(x, y, Math.max(width, height), Math.max(width, height));
        }
        return shape;
    }

    protected void getImageDimensions()
    {
        this.width = image.getWidth();
        this.height = image.getHeight();
    }
    protected void loadImage(String imageFile)
    {   
        try {
            URL path = Thread.currentThread().getContextClassLoader().getResource(imageFile);
            assert path != null;
            image = ImageIO.read(new File(path.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // getter and setter


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
