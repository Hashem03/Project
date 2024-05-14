package com.gamecodeschool.csc133project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Snake class extends GameObject class
 * and implements Movable and Collidable interfaces to create a snake object

 */
class Snake extends GameObject implements Movable, Collidable{

    // The location in the grid of all the segments
    private final ArrayList<Point> segmentLocations;

    // How big is each segment of the snake?
    private final int mSegmentSize;

    // How big is the entire grid
    private final Point mMoveRange;

    // Where is the centre of the screen
    // horizontally in pixels?
    private final int halfWayPoint;

    // For tracking movement Heading
    enum Heading {
        UP, RIGHT, DOWN, LEFT
    }

    // Start by heading to the right
    protected Heading heading = Heading.RIGHT;

    // A bitmap for each direction the head can face
    private Bitmap mBitmapHeadRight;
    private Bitmap mBitmapHeadLeft;
    private Bitmap mBitmapHeadUp;
    private Bitmap mBitmapHeadDown;

    // A bitmap for the body
    private Bitmap mBitmapBody;

    private final Move move = new Move();
    private final DrawSnake drawsnake = new DrawSnake();

    Snake(Context context, Point mr, int ss) {
        super(mr, ss);
        // Initialize our ArrayList
        segmentLocations = new ArrayList<>();

        // Initialize the segment size and movement
        // range from the passed in parameters
        mSegmentSize = ss;
        mMoveRange = mr;

        Bitmap head = BitmapFactory
                .decodeResource(context.getResources(),
                        R.drawable.head);
        // Create and scale the bitmaps
        mBitmapHeadRight = head;

        // Create 3 more versions of the head for different headings
        mBitmapHeadLeft = head;

        mBitmapHeadUp = head;

        mBitmapHeadDown = head;

        // Modify the bitmaps to face the snake head
        // in the correct direction
        mBitmapHeadRight = Bitmap
                .createScaledBitmap(mBitmapHeadRight,
                        ss, ss, false);

        // A matrix for scaling
        Matrix matrix = new Matrix();
        matrix.preScale(-1, 1);

        mBitmapHeadLeft = Bitmap
                .createBitmap(mBitmapHeadRight,
                        0, 0, ss, ss, matrix, true);

        // A matrix for rotating
        matrix.preRotate(-90);
        mBitmapHeadUp = Bitmap
                .createBitmap(mBitmapHeadRight,
                        0, 0, ss, ss, matrix, true);

        // Matrix operations are cumulative
        // so rotate by 180 to face down
        matrix.preRotate(180);
        mBitmapHeadDown = Bitmap
                .createBitmap(mBitmapHeadRight,
                        0, 0, ss, ss, matrix, true);

        // Create and scale the body
        mBitmapBody = BitmapFactory
                .decodeResource(context.getResources(),
                        R.drawable.body);

        mBitmapBody = Bitmap
                .createScaledBitmap(mBitmapBody,
                        ss, ss, false);

        // The halfway point across the screen in pixels
        // Used to detect which side of screen was pressed
        halfWayPoint = mr.x * ss / 2;
    }

    // Get the snake ready for a new game
    void reset(int w, int h) {

        // Reset the heading
        heading = Heading.RIGHT;

        // Delete the old contents of the ArrayList
        segmentLocations.clear();

        // Start with a single snake segment
        segmentLocations.add(new Point(w / 2, h / 2));
    }


    void move() {
        move.initiate_movement(segmentLocations);
        // Move the head in the appropriate heading
        // Get the existing head position
        Point p = segmentLocations.get(0);
        // Move it appropriately
        move.moveDirection(p, heading);
    }


    /**
     * Detect if the snake has died
     * @return true if the snake has died, false otherwise
     */
    boolean detectDeath() {
        // Has the snake died?
        boolean out_of_bounds = segmentLocations.get(0).x == -1 ||
                segmentLocations.get(0).x > mMoveRange.x ||
                segmentLocations.get(0).y == -1 ||
                segmentLocations.get(0).y > mMoveRange.y;
        if (out_of_bounds) {
            return true;
        }
        return eaten_itself();
    }

    boolean eaten_itself(){
        // Eaten itself?
        for (int i = segmentLocations.size() - 1; i > 0; i--) {
            // Have any of the sections collided with the head
            boolean head_collision = segmentLocations.get(0).x == segmentLocations.get(i).x &&
                    segmentLocations.get(0).y == segmentLocations.get(i).y;
            if (head_collision) {
                return true;
            }
        }
        return false;
    }

    boolean checkDinner(Point l) {
        boolean coordinates_match = segmentLocations.get(0).x == l.x &&
                segmentLocations.get(0).y == l.y;
        if (coordinates_match) {
            segmentLocations.add(new Point(-10, -10));
            return true;
        }
        return false;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        drawsnake.setVals(canvas, paint, mSegmentSize, segmentLocations);
        drawsnake.setBitmapVals(mBitmapHeadRight, mBitmapHeadLeft, mBitmapHeadUp, mBitmapHeadDown);
        drawsnake.draw_object(mBitmapBody, heading);
        // Don't run this code if ArrayList has nothing in it

    }

    // Handle changing direction
    void switchHeading(MotionEvent motionEvent) {

        // Is the tap on the right hand side?
        if (motionEvent.getX() >= halfWayPoint) {
            heading = move.rotate_right(heading);
        } else {
            heading = move.rotate_left(heading);
        }
    }
    public boolean checkCollide(Wall mWall) {
        // Assuming Wall has a method getLocation() to get its position
        Point wallLocation = mWall.getLocation();
        Point headLocation = segmentLocations.get(0); // Get the head of the snake

        return headLocation.equals(wallLocation);
    }
    public List<Point> getSnakeBody() {
        return segmentLocations;
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