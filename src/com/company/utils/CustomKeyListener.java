package com.company.utils;

import com.company.entities.Snake;
import com.company.enums.Direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CustomKeyListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                if (!(Snake.getHead().getDirection() == Direction.RIGHT)) {
                    Snake.getHead().setDirection(Direction.LEFT);
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (!(Snake.getHead().getDirection() == Direction.LEFT)) {
                    Snake.getHead().setDirection(Direction.RIGHT);
                }
                break;
            case KeyEvent.VK_UP:
                if (!(Snake.getHead().getDirection() == Direction.DOWN)) {
                    Snake.getHead().setDirection(Direction.UP);
                }
                break;
            case KeyEvent.VK_DOWN:
                if (!(Snake.getHead().getDirection() == Direction.UP)) {
                    Snake.getHead().setDirection(Direction.DOWN);
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
