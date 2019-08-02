package com.langtonsant.application.element.ant;

import com.langtonsant.math.Vector2;

public class Machine implements IMachine {

    private Vector2 position;
    private Vector2 heading;

    @Override
    public Vector2 getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Vector2 position) {
        this.position = position;
    }

    @Override
    public Vector2 getHeading() {
        return this.heading;
    }

    @Override
    public void setHeading(Vector2 heading) {
        this.heading = heading;
    }

    @Override
    public void move() {
        position.add(heading);
    }

    @Override
    public void resetOnGrid(int width, int height) {
        setHeading(Vector2.right());
        setPosition(new Vector2(width / 2, height / 2));
    }
}
