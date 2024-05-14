package com.gamecodeschool.csc133project;

import android.graphics.Point;

import java.util.ArrayList;

public class Move {

    public Move(){

    }

    public void initiate_movement(ArrayList<Point> segmentLocations){
        for (int i = segmentLocations.size() - 1; i > 0; i--) {
            segmentLocations.get(i).x = segmentLocations.get(i - 1).x;
            segmentLocations.get(i).y = segmentLocations.get(i - 1).y;
        }
    }

    public void moveDirection(Point p, Snake.Heading heading){
        switch (heading) {
            case UP:
                p.y--;
                break;

            case RIGHT:
                p.x++;
                break;

            case DOWN:
                p.y++;
                break;

            case LEFT:
                p.x--;
                break;
        }
    }
    public Snake.Heading rotate_right(Snake.Heading heading) {
        switch (heading) {
            case UP:
                return Snake.Heading.RIGHT;
            case RIGHT:
                return Snake.Heading.DOWN;
            case DOWN:
                return Snake.Heading.LEFT;
            case LEFT:
                return Snake.Heading.UP;
            default:
                return heading;
        }
    }

    public Snake.Heading rotate_left(Snake.Heading heading) {
        switch (heading) {
            case UP:
                return Snake.Heading.LEFT;
            case LEFT:
                return Snake.Heading.DOWN;
            case DOWN:
                return Snake.Heading.RIGHT;
            case RIGHT:
                return Snake.Heading.UP;
            default:
                return heading;
        }
    }
}
