package com.gamecodeschool.csc133project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.Random;

/**
 * Apple Type determines how many points the consumed apple is worth
 * Adds randomness to the game by adding different value types to the apple
 */
enum AppleType {
    GOOD(5),
    BAD(2);

    private final int pointVal;

    /**
     * Construct an AppleType with a point value
     *
     * @param pointVal the point value of the apple type
     */
    AppleType(int pointVal) {
        this.pointVal = pointVal;
    }

    /**
     * Get the point value of the apple type
     *
     * @return point value of the apple
     */
    public int getPointVal() {
        return pointVal;
    }
}

/**
 * Represents an apple object in the game
 */
class Apple extends GameObject {

    // The location of the apple on the grid
    // Not in pixels
    //private Point location = new Point();

    // The range of values we can choose from
    // to spawn an apple
    private Point mSpawnRange;
    //private int mSize;

    // An image to represent the apple
    private Bitmap mBitmapApple;

    /// Set up the apple in the constructor

    /**
     * Initialize an instance of an Apple
     *
     * @param context The application context
     * @param sr      The spawn range for the apple
     * @param size    The size of the apple
     */
    Apple(Context context, Point sr, int size) {
        super((new Point()), size);
        mSpawnRange = sr;
        //mSize = size;
        location.x = -10;

        // Load the image to the bitmap
        mBitmapApple = BitmapFactory.decodeResource(
                context.getResources(),
                R.drawable.apple_hd
        );
        // Bitmap Resizing
        mBitmapApple = Bitmap.createScaledBitmap(
                mBitmapApple,
                size,
                size,
                false);

        // the type of apple is randomly selected to be either good or bad
        Random rand = new Random();
        AppleType type = rand.nextBoolean() ? AppleType.GOOD : AppleType.BAD;
    }

    // This is called every time an apple is eaten

    /**
     * Spawns apple at random location within the bounds of the spawn range
     */
    void spawn() {
        // Choose two random values and place the apple
        Random random = new Random();
        location.x = random.nextInt(mSpawnRange.x) + 1;
        location.y = random.nextInt(mSpawnRange.y - 1) + 1;
    }

    /**
     * Getter for the location of the apple
     * @return The location point on the bitmap of the apple
     *
     * Point getLocation(){
    return location;
    }*/

    /**
     * Draw the apple on the canvas
     *
     * @param canvas The canvas to draw the snake on
     * @param paint  The paint obj used for drawing
     */
    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(mBitmapApple,
                location.x * size, location.y * size, paint);

    }
    /**
     *
     * this method draws a apple icon next to the score
     * @param canvas        The canvas to draw the snake on
     * @param paint         The paint obj used for drawing
     * @param x             X- axis location to display the apple on the screen
     * @param y             Y- axis location to display the apple on the screen
     */
    public void drawIcon(Canvas canvas, Paint paint, int x, int y){
        canvas.drawBitmap(mBitmapApple,
                x,y, paint);

    }

}