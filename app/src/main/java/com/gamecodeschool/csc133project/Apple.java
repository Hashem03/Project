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
    GOOD(5), BAD(2);

    private final int pointVal;
    AppleType(int pointVal){
        this.pointVal = pointVal;
    }
    public int getPointVal() {
        return pointVal;
    }
}

class Apple {

    // The location of the apple on the grid
    // Not in pixels
    private Point location = new Point();

    // The range of values we can choose from
    // to spawn an apple
    private Point mSpawnRange;
    private int mSize;
    private AppleType type;

    // An image to represent the apple
    private Bitmap mBitmapApple;

    /// Set up the apple in the constructor
    Apple(Context context, Point sr, int s){

        // Make a note of the passed in spawn range
        mSpawnRange = sr;
        // Make a note of the size of an apple
        mSize = s;
        // Hide the apple off-screen until the game starts
        location.x = -10;

        // Load the image to the bitmap
        mBitmapApple = BitmapFactory.decodeResource(context.getResources(), R.drawable.apple);
        // Resize the bitmap
        mBitmapApple = Bitmap.createScaledBitmap(mBitmapApple, s, s, false);

        // the type of apple is randomly selected to be either good or bad
        Random rand = new Random();
        type = rand.nextBoolean() ? AppleType.GOOD : AppleType.BAD;
    }

    // This is called every time an apple is eaten
    void spawn(){
        // Choose two random values and place the apple
        Random random = new Random();
        location.x = random.nextInt(mSpawnRange.x) + 1;
        location.y = random.nextInt(mSpawnRange.y - 1) + 1;
    }

    // Let SnakeGame know where the apple is
    // SnakeGame can share this with the snake
    Point getLocation(){
        return location;
    }

    // Draw the apple
    void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(mBitmapApple,
                location.x * mSize, location.y * mSize, paint);

    }

    // return the point value of the apple type
    int getPoints() {
        return type.getPointVal();
    }

}