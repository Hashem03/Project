package com.gamecodeschool.csc133project;

import android.graphics.Bitmap;
import android.graphics.Point;

public abstract class Fruit extends GameObject {
  private int value;
  /**
   * Constructor for GameObject. Initializes the location and size of the game object.
   *
   * @param location The location of the game object.
   * @param size     The size of the game object.
   */
  protected Fruit(Point location, int size) {
    super(location, size);
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public abstract void draw();

}
