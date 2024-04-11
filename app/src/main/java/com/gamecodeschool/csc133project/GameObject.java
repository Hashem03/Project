package com.gamecodeschool.csc133project;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public abstract class GameObject implements Drawable {
    protected Point location; // Represents the location of the game object.
    protected int size;       // The size of the game object, used in drawing.

    // Constructor to initialize the location and size of the game object.
    public GameObject(Point location, int size) {
        this.location = location;
        this.size = size;
    }

    // Abstract method draw defined by the Drawable interface.
    // Implementation will be provided by subclasses (e.g., Apple, Snake).
    @Override
    public abstract void draw(Canvas canvas, Paint paint);

    // Getters for location and size.
    public Point getLocation() {
        return location;
    }

    public int getSize() {
        return size;
    }
}
