package com.company.entities;

import java.awt.*;
import java.util.ArrayList;

import static com.company.utils.Constants.UNIT_SIZE;

public class Snake {
    private static Color color = Color.GREEN;

    private static Head head = new Head(new Point(0, 0));
    private static ArrayList<Tail> body;

    public static void addTail() {
        int size = body.size();
        if (size > 1) {
            Point newPoint = new Point(
                    (int) body.get(size - 1).getPoint().getX(),
                    (int) body.get(size - 1).getPoint().getY()
            );
            body.add(new Tail(newPoint));
        }
    }

    public static Color getColor() {
        return color;
    }

    public static void move() {
        if (body != null) {
            if (body.size() >= 2) {

            }
            if (body.size() == 1) {

            }
        }

        switch (head.getDirection()) {
            case LEFT:
                head.setPoint(new Point(
                        (int) (head.getPoint().getX() - UNIT_SIZE),
                        (int) head.getPoint().getY()
                ));
                break;
            case RIGHT:
                head.setPoint(new Point(
                        (int) (head.getPoint().getX() + UNIT_SIZE),
                        (int) head.getPoint().getY()
                ));
                break;
            case UP:
                head.setPoint(new Point(
                        (int) head.getPoint().getX(),
                        (int) (head.getPoint().getY() - UNIT_SIZE)
                ));
                break;
            case DOWN:
                head.setPoint(new Point(
                        (int) head.getPoint().getX(),
                        (int) (head.getPoint().getY() + UNIT_SIZE)
                ));
                break;
        }
    }

    public static Head getHead() {
        return head;
    }

    public static ArrayList<Tail> getBody() {
        return body;
    }
}
