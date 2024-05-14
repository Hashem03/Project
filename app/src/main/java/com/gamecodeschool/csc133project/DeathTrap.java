// DeathTrap.java
package com.gamecodeschool.csc133project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import java.util.Random;

/**
 *
 *
 */
public class DeathTrap extends GameObject {

    private Bitmap mBitmapDeathTrap;
    private final Point mSpawnRange;

    public DeathTrap(Context context, Point sr, int size) {
        super(new Point(), size);
        mSpawnRange = sr;
        mBitmapDeathTrap = BitmapFactory.decodeResource(context.getResources(), R.drawable.deathtrap);
        mBitmapDeathTrap = Bitmap.createScaledBitmap(mBitmapDeathTrap, size, size, false);
        spawn();
    }

    public void spawn() {
        Random random = new Random();
        location.x = random.nextInt(mSpawnRange.x) + 1;
        location.y = random.nextInt(mSpawnRange.y) + 1;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(mBitmapDeathTrap, location.x * size, location.y * size, paint);
    }
}
