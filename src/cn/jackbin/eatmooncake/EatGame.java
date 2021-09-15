package cn.jackbin.eatmooncake;

import cn.jackbin.eatmooncake.entity.Cake;
import cn.jackbin.eatmooncake.entity.Moon;
import cn.jackbin.eatmooncake.entity.ChangeE;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.net.URISyntaxException;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.Timer;


public class EatGame extends JFrame implements ActionListener, MouseListener
{
    private final int WIDTH = 400;
    private final int HEIGHT = 800;
    private int score;
    private int ticks; 
    private boolean gameOver = false;
    private GameControl control; 
    private List<Moon> moons;
    private List<Cake> cakes;
    private Timer timer;
    private ChangeE changeE;
    /**
     * Constructor for objects of class FallingGame
     */
    public EatGame() throws URISyntaxException {


        changeE = new ChangeE(150, 650, "player.jpg", WIDTH);
        moons = new ArrayList<>();
        cakes = new ArrayList<>();
        addMoonAndCake();
        
        control = new GameControl(changeE, moons, cakes);
        timer = new Timer(20, this); 
        
        //add keybinds
        control.addAction("Left", -20, KeyEvent.VK_LEFT);
        control.addAction("Right", 20, KeyEvent.VK_RIGHT);
        
        //add components
        add(control);
        addMouseListener(this);
        control.addMouseListener(this);
        setTitle("吃月饼");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setVisible(true);
       
        timer.start();

    }

    /**
     * 添加月亮和月饼
     */
    public void addMoonAndCake() {
        Random r = new Random();
        int x, y;
        x = 60 + (r.nextInt(35) * 2);
        y = r.nextInt(20) + moons.size() * 30;
        Moon moon1 = new Moon(x, y, "moon.png");
        moons.add(moon1);

        x = 190 + r.nextInt(35) * 2;
        y = r.nextInt(20) + moons.size() * 30;
        Moon moon2 = new Moon(x, y, "moon.png");
        moons.add(moon2);

        x = 80 + (r.nextInt(35) * 2);
        y = r.nextInt(20) + moons.size() * 50;
        Cake cake = new Cake(x, y, "cake.png");
        cakes.add(cake);
    }

    /**
     * 更新窗口
     */
    private void updateFrame() {
        ticks++;
        for(int i = 0; i < moons.size(); i++)
        {
            Moon moon = moons.get(i);
            if(ticks % 25 == 0 && moon.getSpeed() < 10)
            {
                moon.setSpeed(moon.getSpeed() + 2);
            }
        }
        Iterator<Moon> moonIterator = moons.iterator();
        while (moonIterator.hasNext()) {
            Moon moon = moonIterator.next();
            // 超出屏幕
            if(moon.getY() > HEIGHT) {
                moonIterator.remove();
            } else
                moon.move();
        }
        Iterator<Cake> cakeIterator = cakes.iterator();
        while (cakeIterator.hasNext()) {
            Cake cake = cakeIterator.next();
            // 超出屏幕
            if(cake.getY() > HEIGHT) {
                cakeIterator.remove();
            } else
                cake.move();
        }
        if(moons.size() == 0) {
            addMoonAndCake();
        }
    }

    private boolean checkCollision() {
        Rectangle rectangle = (Rectangle) changeE.getShape();
        for(Moon moon : moons) {
            Ellipse2D circle = (Ellipse2D) moon.getShape();
            // 判断是否与圆形相撞
            if (circle.intersects(rectangle)) {
                gameOver = true;
            }
        }
        Iterator<Cake> cakeIterator = cakes.iterator();
        while (cakeIterator.hasNext()) {
            Cake cake = cakeIterator.next();
            Ellipse2D circle = (Ellipse2D) cake.getShape();
            if (circle.intersects(rectangle)) {
                score ++;   // 得分
                cakeIterator.remove();
            }
        }
        return gameOver;
    }
    public void actionPerformed(ActionEvent e)
    {
        if(gameOver) {
            timer.stop(); 
            control.drawEnd(control.getGraphics(), score);
        } else {
            //continue with game
            updateFrame();
            checkCollision();
            control.repaint(); 
        }
    }
    public void mouseClicked(MouseEvent e) {

    }
    public void mousePressed(MouseEvent e)
    {
        if(gameOver)
        {
            //reset game
            moons.clear();
            control.removeAll(); 
            control.updateUI(); 
            score = 0;
            changeE.setX(150);
            changeE.setY(650);
            addMoonAndCake();
            timer.start();
            repaint();
            gameOver = false; 
        }
    }
    public void mouseReleased(MouseEvent e)
    {
        
    }
    public void mouseEntered(MouseEvent e)
    {
        
    }
    public void mouseExited(MouseEvent e)
    {
        
    }
}
