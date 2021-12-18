package com.company.entities;

import java.awt.*;

import static com.company.utils.Constants.*;

public class Apple {
    private Point point;
    private Color color = Color.RED;

    public Apple() {
        point = new Point(
                random.nextInt(SCREEN_WIDTH / UNIT_SIZE) * UNIT_SIZE,
                random.nextInt(SCREEN_HEIGHT / UNIT_SIZE) * UNIT_SIZE
        );
    }

    public Point getPoint() {
        return point;
    }

    public Color getColor() {
        return color;
    }
}
