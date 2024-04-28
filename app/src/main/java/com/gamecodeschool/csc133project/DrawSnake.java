package com.gamecodeschool.csc133project;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.ArrayList;

public class DrawSnake {
    private Canvas canvas;
    private Paint paint;


    private ArrayList<Point> segmentLocations;
    private int mSegmentSize;


    private Bitmap mBitmapHeadRight;
    private Bitmap mBitmapHeadLeft;
    private Bitmap mBitmapHeadUp;
    private Bitmap mBitmapHeadDown;
    public DrawSnake(){


    }


    public void setVals(Canvas canvas, Paint paint, int mSegmentSize, ArrayList<Point> segmentLocations){
        this.canvas = canvas;
        this.paint = paint;
        this.mSegmentSize = mSegmentSize;
        this.segmentLocations = segmentLocations;
    }
    public void setBitmapVals(Bitmap mBitmapHeadRight, Bitmap mBitmapHeadLeft,
                              Bitmap mBitmapHeadUp, Bitmap mBitmapHeadDown){
        this.mBitmapHeadRight = mBitmapHeadRight;
        this.mBitmapHeadLeft = mBitmapHeadLeft;
        this.mBitmapHeadUp = mBitmapHeadUp;
        this.mBitmapHeadDown = mBitmapHeadDown;
    }
    public void draw_object( Bitmap mBitmapBody, Snake.Heading heading){
        if (!segmentLocations.isEmpty()) {
            // All the code from this method goes here
            // Draw the head
            draw_head(heading);
            // Draw the snake body one block at a time
            for (int i = 1; i < segmentLocations.size(); i++) {
                canvas.drawBitmap(mBitmapBody,
                        segmentLocations.get(i).x
                                * mSegmentSize,
                        segmentLocations.get(i).y
                                * mSegmentSize, paint);
            }
        }
    }


    private void draw_head(Snake.Heading heading){
        switch (heading) {
            case RIGHT:
                draw_rotated_head(mBitmapHeadRight,
                        segmentLocations);
                break;


            case LEFT:
                draw_rotated_head(mBitmapHeadLeft,
                        segmentLocations);
                break;


            case UP:
                draw_rotated_head(mBitmapHeadUp,
                        segmentLocations);
                break;


            case DOWN:
                draw_rotated_head(mBitmapHeadDown,
                        segmentLocations);
                break;
        }
    }


    public void draw_rotated_head(Bitmap mBitmapHeadDirection,
                                  ArrayList<Point> segmentLocations){
        canvas.drawBitmap(mBitmapHeadDirection,
                segmentLocations.get(0).x
                        * mSegmentSize,
                segmentLocations.get(0).y
                        * mSegmentSize, paint);
    }

}
