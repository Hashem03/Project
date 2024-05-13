package com.gamecodeschool.csc133project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Paint;
import android.graphics.Canvas;
import java.util.Random;

/**
 * Banana class extends Fruit class
 */
public class Banana extends Fruit {

    private final Point mSpawnRange;
    private Bitmap mBitmapBanana;
    private long mStartTime;
    private static final int DISPLAY_TIME = 4000;
    private long getLastSpawnTime;

    /**
     * Constructs a new Banana object.
     *
     * @param context the context of the application
     * @param sr the spawn range for the banana
     * @param size the size of the banana
     */
    public Banana(Context context, Point sr, int size) {
        super((new Point()), size);
        mSpawnRange = sr;

        mBitmapBanana = BitmapFactory.decodeResource(
            context.getResources(),
            R.drawable.banana
        );
        //  Scales the bitmap to the size of the banana
        mBitmapBanana = Bitmap.createScaledBitmap(
            mBitmapBanana,
            size,
            size,
            true);
    }
    public long getLastSpawnTime() {
        return getLastSpawnTime;
    }
    public void setLastSpawnTime(long lastSpawnTime) {
        getLastSpawnTime = lastSpawnTime;
    }

    public void setVisible(boolean visible) {
        mStartTime = visible ? System.currentTimeMillis() : 0;
    }



    /**
     * Spawns the banana at a random location within the spawn range.
     */
    public void spawn() {
        Random random = new Random();
        location.x = random.nextInt(mSpawnRange.x) + 1;
        location.y = random.nextInt(mSpawnRange.y) + 1;
        mStartTime = System.currentTimeMillis();
        getLastSpawnTime = mStartTime;
    }

    /**
     * Draws the banana on the canvas if it is visible.
     *
     * @param canvas the canvas to draw on
     * @param paint the paint used for drawing
     */
    @Override
    public void draw(Canvas canvas, Paint paint) {
        if (isVisible()) {
            canvas.drawBitmap(
                mBitmapBanana,
                location.x * size,
                location.y * size,
                paint);
        }
    }

    /**
     * Checks if the banana is currently visible.
     * @return true if the banana is visible, false otherwise
     */
    public boolean isVisible() {
        return (System.currentTimeMillis() - mStartTime) < DISPLAY_TIME;
    }

}
