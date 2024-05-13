package com.gamecodeschool.csc133project;
import android.graphics.Point;
import android.view.MotionEvent;

import java.util.ArrayList;


public interface Movable {
    public void initiate_movement(ArrayList<Point> segmentLocations);
    public void moveDirection(Point p, Snake.Heading heading);
    public Snake.Heading rotate_right(Snake.Heading heading);

    public Snake.Heading rotate_left(Snake.Heading heading);
}
