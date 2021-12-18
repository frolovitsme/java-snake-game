package com.company.entities;

import java.awt.*;
import java.util.ArrayList;

import static com.company.utils.Constants.UNIT_SIZE;

public class Snake {
    private static Color color = Color.GREEN;

    private static Head head = new Head(new Point(0, 0));
    private static ArrayList<Tail> body = new ArrayList<>();

    public static Color getColor() {
        return color;
    }

    public static Head getHead() {
        return head;
    }

    public static ArrayList<Tail> getBody() {
        return body;
    }

    public static void move() {
        if (body.size() >= 2) {
            for (int i = body.size() - 1; i >= 1; i--) {
                body.get(i).setPoint(new Point(
                        (int) body.get(i - 1).getPoint().getX(),
                        (int) body.get(i - 1).getPoint().getY()
                ));
            }
        }
        if (body.size() >= 1) {
            body.get(0).setPoint(new Point(
                    (int) head.getPoint().getX(),
                    (int) head.getPoint().getY()
            ));
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

    public static void addTail() {
        int size = body.size();
        if (size < 1) {
            body.add(new Tail(
                    new Point(
                            (int) head.getPoint().getX(),
                            (int) head.getPoint().getY()
                    )
            ));
        } else {
            body.add(new Tail(
                    new Point(
                            (int) body.get(size - 1).getPoint().getX(),
                            (int) body.get(size - 1).getPoint().getY()
                    )
            ));
        }
    }
}
