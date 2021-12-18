package com.company.utils;

import com.company.entities.Apple;
import com.company.entities.Snake;
import com.company.entities.Tail;

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
        if (running) {
            this.drawMarkup(g);
            this.drawApple(g);
            this.drawSnake(g);
            repaint();
        } else {
            gameOver(g);
        }
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

    private void gameOver(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);

        timer.stop();
    }

    private boolean isHit() {
        return Snake.getHead().getPoint().distance(apple.getPoint()) == 0.0;
    }

    private boolean checkBarrier() {
        return Snake.getHead().getPoint().getX() == SCREEN_WIDTH ||
                Snake.getHead().getPoint().getX() < 0 ||
                Snake.getHead().getPoint().getY() == SCREEN_HEIGHT ||
                Snake.getHead().getPoint().getY() < 0 ||
                Snake.getBody().stream()
                        .map(Tail::getPoint)
                        .anyMatch(point -> point.distance(Snake.getHead().getPoint()) == 0.0 &&
                                Snake.getBody().get(0).getPoint().distance(point) != 0.0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            Snake.move();
            if (isHit()) {
                isAppleExist = false;
                Snake.addTail();
            }
            if (checkBarrier()) {
                running = false;
            }
        }
    }
}
