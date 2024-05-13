// GreenApple.java
package com.gamecodeschool.csc133project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.Random;

/**
 * GreenApple class extends GameObject class
 */
public class GreenApple extends GameObject {

  private Bitmap mBitmapGreenApple;
  private Point mSpawnRange;

  /**
   * Constructs a new GreenApple object.
   *
   * @param context the context of the application
   * @param sr      the spawn range for the green apple
   * @param size    the size of the green apple
   */
  public GreenApple(Context context, Point sr, int size) {
    super(new Point(), size);
    mSpawnRange = sr;
    mBitmapGreenApple = BitmapFactory.decodeResource(context.getResources(), R.drawable.greenbadapple);
    mBitmapGreenApple = Bitmap.createScaledBitmap(mBitmapGreenApple, size, size, false);
  }

  /**
   * Spawns the green apple at a random location.
   * The location is within the spawn range.
   */
  public void spawn() {
    Random random = new Random();
    location.x = random.nextInt(mSpawnRange.x) + 1;
    location.y = random.nextInt(mSpawnRange.y) + 1;
  }

  /**
   * Draws the green apple on the canvas.
   *
   * @param canvas The canvas to draw the green apple on
   * @param paint  The paint obj used for drawing
   */
  @Override
  public void draw(Canvas canvas, Paint paint) {
    canvas.drawBitmap(mBitmapGreenApple, location.x * size, location.y * size, paint);
  }
}