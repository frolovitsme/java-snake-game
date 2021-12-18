package com.company.utils;

//import com.company.entities.Apple;

import com.company.entities.Apple;
import com.company.entities.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.company.utils.Constants.*;

public class GamePanel extends JPanel implements ActionListener {

    private boolean running = false;
    private boolean isAppleExist = false;
    private static final int DELAY = 250;
    private Timer timer;
    private Apple apple;

    public GamePanel() {
        setBorder(BorderFactory.createLineBorder(Color.RED));
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        CustomKeyListener keyListener = new CustomKeyListener();
        this.addKeyListener(keyListener);
        this.startGame();
    }

    private void startGame() {
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    /*
    изменяем стандартный компонент
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.drawMarkup(g);
        this.drawApple(g);
        this.drawSnake(g);
        repaint();
    }

    /*
    рисуем разметку на поле
     */
    private void drawMarkup(Graphics g) {
        for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
            g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
            g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
        }
    }

    /*
    рисуем яблоко
     */
    private void drawApple(Graphics g) {
        if (!isAppleExist) {
            apple = new Apple();
            isAppleExist = true;
        }
        g.setColor(apple.getColor());
        g.fillOval(
                (int) apple.getPoint().getX(),
                (int) apple.getPoint().getY(),
                UNIT_SIZE,
                UNIT_SIZE
        );
    }

    /*
    рисуем змею
     */
    private void drawSnake(Graphics g) {
        g.setColor(Snake.getColor());
        g.fillRect(
                (int) Snake.getHead().getPoint().getX(),
                (int) Snake.getHead().getPoint().getY(),
                UNIT_SIZE,
                UNIT_SIZE
        );

        int size = Snake.getBody().size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                g.setColor(Color.CYAN);
                g.fillRect(
                        (int) Snake.getBody().get(i).getPoint().getX(),
                        (int) Snake.getBody().get(i).getPoint().getY(),
                        UNIT_SIZE,
                        UNIT_SIZE
                );
            }
        }
    }

    public boolean isHit() {
        if (Snake.getHead().getPoint().distance(apple.getPoint()) == 0.0) {
            return true;
        } else
            return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Snake.move();
        if (isHit()) {
            isAppleExist = false;
            Snake.addTail();
        }
    }
}
