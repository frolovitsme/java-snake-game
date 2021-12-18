package com.company.entities;

import com.company.enums.Direction;

import java.awt.*;

public class Head {
    private Direction direction = Direction.RIGHT;
    private Point point;

    public Head(Point point) {
        this.point = point;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
