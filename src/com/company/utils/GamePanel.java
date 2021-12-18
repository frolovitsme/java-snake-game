package com.company.utils;

//import com.company.entities.Apple;
import com.company.entities.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.company.utils.Constants.*;

public class GamePanel extends JPanel implements ActionListener {

    private boolean running = false;
    private static final int DELAY = 75;
    private Timer timer;

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
    protected void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g);
        this.drawMarkup(g);
//        this.drawApple(g);
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
//    private void drawApple(Graphics g) {
//        Apple apple = new Apple();
//        g.setColor(apple.getColor());
//        g.fillOval(
//                apple.getCoordinates().getX(),
//                apple.getCoordinates().getY(),
//                UNIT_SIZE,
//                UNIT_SIZE
//        );
//    }

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Snake.move();
    }
}
