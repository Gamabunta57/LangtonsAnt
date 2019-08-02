package com.langtonsant.application.element.ant;

import com.langtonsant.math.Vector2;

public interface IMachine {
    Vector2 getPosition();
    void setPosition(Vector2 position);

    Vector2 getHeading();
    void setHeading(Vector2 heading);

    void move();
    void resetOnGrid(int width, int height);
}
