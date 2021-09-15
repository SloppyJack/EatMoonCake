package cn.jackbin.eatmooncake;

import cn.jackbin.eatmooncake.entity.Cake;
import cn.jackbin.eatmooncake.entity.Moon;
import cn.jackbin.eatmooncake.entity.ChangeE;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;


/**
 * 画面绘制
 */
public class GameControl extends JPanel
{
    private InputMap inputMap; 
    private ChangeE myChangeE;
    private List<Moon> moons;
    private List<Cake> cakes;


    public GameControl(ChangeE changeE, List<Moon> moons, List<Cake> cakes)
    {
        this.myChangeE = changeE;
        this.moons = moons;
        this.cakes = cakes;
        setBackground(Color.white);
        inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);  
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        // 绘制背景
        ImageIcon icon=new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("background.png"));
        Image img=icon.getImage();
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        // 绘制玩家
        g.drawImage(myChangeE.getImage(), myChangeE.getX(), myChangeE.getY(), this);
        // 绘制月亮
        for(Moon moon : moons) {
            g.drawImage(moon.getImage(), moon.getX(), moon.getY(), this);
        }
        // 绘制月饼
        for (Cake cake : cakes) {
            g.drawImage(cake.getImage(), cake.getX(), cake.getY(), this);
        }
        
    }


    public void drawEnd(Graphics g, int score)
    {
        g.setColor(Color.WHITE);
        g.fillRect(50, 200, 300, 300);
        g.setColor(Color.RED);
        g.drawString("祝你中秋快乐，阖家欢乐!", 100, 300);
        g.setColor(Color.BLUE);
        g.drawString("您吃的到的月饼个数为：" + score, 100, 350);
        g.setColor(Color.BLACK);
        g.drawString("鼠标点击重玩", 100, 400);
    }


    public void addAction(String name, int deltaX, int keyCode) {
        MoveAction moveAction = new MoveAction(name, deltaX);
        inputMap.put(KeyStroke.getKeyStroke(keyCode, 0), name);
        getActionMap().put(name, moveAction);
    } 


    private class MoveAction extends AbstractAction implements ActionListener {
        private int myDeltaX;


        public MoveAction(String name, int deltaX) {
            super(name);
            myDeltaX = deltaX;
        }

        public void actionPerformed(ActionEvent e) {
            myChangeE.move(myDeltaX);
        }
    }
}
