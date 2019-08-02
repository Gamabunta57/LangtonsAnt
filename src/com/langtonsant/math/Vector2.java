package com.langtonsant.math;

public class Vector2 {
    public int x;
    public int y;

    public Vector2(){
        this(0,0);
    }

    public Vector2(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void add(Vector2 vectorToAdd){
        this.x += vectorToAdd.x;
        this.y += vectorToAdd.y;
    }

    public static Vector2 right(){
        return new Vector2(1,0);
    }
}
