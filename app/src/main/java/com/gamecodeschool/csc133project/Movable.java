package com.gamecodeschool.csc133project;
import android.graphics.Point;
import android.view.MotionEvent;

import java.util.ArrayList;


public interface Movable {
    void initiate_movement(ArrayList<Point> segmentLocations);
    void moveDirection(Point p, Snake.Heading heading);
    Snake.Heading rotate_right(Snake.Heading heading);

    Snake.Heading rotate_left(Snake.Heading heading);
}
