// GreenApple.java
package com.gamecodeschool.csc133project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import java.util.Random;

public class GreenApple extends GameObject {

    private Bitmap mBitmapGreenApple;
    private Point mSpawnRange;

    public GreenApple(Context context, Point sr, int size) {
        super(new Point(), size);
        mSpawnRange = sr;
        mBitmapGreenApple = BitmapFactory.decodeResource(context.getResources(), R.drawable.greenbadapple);
        mBitmapGreenApple = Bitmap.createScaledBitmap(mBitmapGreenApple, size, size, false);
    }

    public void spawn() {
        Random random = new Random();
        location.x = random.nextInt(mSpawnRange.x) + 1;
        location.y = random.nextInt(mSpawnRange.y) + 1;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(mBitmapGreenApple, location.x * size, location.y * size, paint);
    }
}