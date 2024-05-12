package com.gamecodeschool.csc133project;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * GameObject is an abstract class that represents a game object in the game world.
 * It implements the Drawable interface, which requires the draw method to be implemented.
 * To use this class, create a subclass for each specific type of game object in your game
 * (e.g., Banana, Apple, Snake). Each subclass should provide its own implementation of the
 * draw method, and can also add additional properties and methods as needed.
 */
public abstract class GameObject implements Drawable {
  protected Point location; // Represents the location of the game object.
  protected int size;       // The size of the game object, used in drawing.

  /**
   * Constructor for GameObject. Initializes the location and size of the game object.
   *
   * @param location The location of the game object.
   * @param size     The size of the game object.
   */
  protected GameObject(Point location, int size) {
    this.location = location;
    this.size = size;
  }

  /**
   * Abstract method draw defined by the Drawable interface.
   * Implementation will be provided by subclasses (e.g., Apple, Snake).
   *
   * @param canvas The canvas to draw on.
   * @param paint  The paint to use for drawing.
   */
  @Override
  public abstract void draw(Canvas canvas, Paint paint);

  /**
   * Returns the location of the game object.
   *
   * @return The location of the game object.
   */
  public Point getLocation() {
    return location;
  }

  /**
   * Returns the size of the game object.
   *
   * @return The size of the game object.
   */
  public int getSize() {
    return size;
  }
}