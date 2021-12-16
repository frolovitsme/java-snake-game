package com.company.utils;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private static final int SCREEN_WIDTH = 600;
    private static final int SCREEN_HEIGHT = 600;
    private static final int UNIT_SIZE = 25;
    private boolean running = false;

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.startGame();
    }

    private void startGame() {
        running = true;
    }

    /*
    изменяем компонент
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.drawMarkup(g);
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
}
