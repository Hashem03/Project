package com.gamecodeschool.csc133project;
import android.graphics.Rect;
import android.graphics.Point;
public interface Collidable {
    boolean checkCollision(Point location);
}