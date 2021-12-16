package com.company.utils;

import javax.swing.*;

public class GameFrame extends JFrame {

    public GameFrame() {
        GamePanel panel = new GamePanel();
        this.add(panel);
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false); // запрещает менять размер окна
        this.pack(); // размер окна (минимальный) = размеру всех его компонентов
        this.setVisible(true);
        this.setLocationRelativeTo(null); // центрирует окно
    }
}
