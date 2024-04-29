package com.gamecodeschool.csc133project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Paint;
import android.graphics.Canvas;

public class Banana extends GameObject {

    private Bitmap mBitmapBanana;

    public Banana(Context context, Point location, int size) {
        super(location, size);

        mBitmapBanana = BitmapFactory.decodeResource(context.getResources(), R.drawable.banana);
        //  Scales the bitmap to the size of the banana
        mBitmapBanana = Bitmap.createScaledBitmap(mBitmapBanana, size, size, true);

    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(
                mBitmapBanana,
                location.x * size,
                location.y * size,
                paint);
    }

}
