package com.gamecodeschool.csc133project;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Color;
import java.util.Random;

public class Wall extends GameObject{
    private Point mSpawnRange;
    private Context mContext;
    private Bitmap mBitmapWall;

    Wall(Context context, Point sr, int size) {
        super((new Point()), size);
        mContext = context;
        mSpawnRange = sr;
        mBitmapWall = BitmapFactory.decodeResource(context.getResources(), R.drawable.wall);
        mBitmapWall = Bitmap.createScaledBitmap(
                mBitmapWall,
                size,
                size,
                false);
    }
    void spawn(Snake snake, Apple apple) {
        Random random = new Random();
        boolean valid;
        do {
            valid = true;
            location.x = random.nextInt(mSpawnRange.x) + 1;
            location.y = random.nextInt(mSpawnRange.y - 1) + 1;

            // Check for overlap with snake
            for (Point p : snake.getSnakeBody()) {
                if (p.equals(location)) {
                    valid = false;
                    break;
                }
            }

            // Check for overlap with apple
            if (apple.getLocation().equals(location)) {
                valid = false;
            }
        } while (!valid);
    }
    @Override
    public void draw(Canvas canvas, Paint paint)
    {
        if (mBitmapWall != null) {
            int left = location.x * size;
            int top = location.y * size;
            canvas.drawBitmap(mBitmapWall, left, top, null);
        }
    }
}