package com.gamecodeschool.csc133project;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.IOException;
import java.util.*;

class SnakeGame extends SurfaceView implements Runnable, GameOverListener{

    // Objects for the game loop/thread
    private Thread mThread = null;
    // Control pausing between updates
    private long mNextFrameTime;
    // Is the game currently playing and or paused?
    private volatile boolean mPlaying = false;
    private volatile boolean mPaused = true;

    private boolean tap_to_play = true;
    private AudioStrategy audioStrategy;
    private GreenApple mGreenApple;


    // for playing sound effects
    //private SoundPool mSP;
//    private int mEat_ID = -1;
//    private int mCrashID = -1;

    // The size in segments of the playable area
    private final int NUM_BLOCKS_WIDE = 40;
    private int mNumBlocksHigh;

    // How many points does the player have
    private int mScore;

    // Objects for drawing
    private Canvas mCanvas;
    private SurfaceHolder mSurfaceHolder;
    private Paint mPaint;

    // A snake ssss
    private Snake mSnake;
    // And an apple
    private Apple mApple;

    private List<Wall> wList;

    private Wall mWall;

    //    Adding an Apple icon to be displayed beside the players score
    private Apple appleIcon;
    private int mBlockSize;
    private Context mContext;

    // This is the constructor method that gets called
    // from SnakeActivity
    public SnakeGame(Context context, Point size) {
        super(context);

        // Work out how many pixels each block is
        int blockSize = size.x / NUM_BLOCKS_WIDE;
        mBlockSize = blockSize;
        mContext = context;

        // How many blocks of the same size will fit into the height
        mNumBlocksHigh = size.y / blockSize;


        this.audioStrategy = new BasicAudioStrategy(context); // Assuming BasicAudioStrategy is your strategy implementation class
        this.audioStrategy = new BasicAudioStrategy(getContext());
        this.audioStrategy = new BasicAudioStrategy(context);

        // Initialize the SoundPool
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            AudioAttributes audioAttributes = new AudioAttributes.Builder()
//                    .setUsage(AudioAttributes.USAGE_MEDIA)
//                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
//                    .build();
//
//            mSP = new SoundPool.Builder()
//                    .setMaxStreams(5)
//                    .setAudioAttributes(audioAttributes)
//                    .build();
//        } else {
//            mSP = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
//        }
//        try {
//            AssetManager assetManager = context.getAssets();
//            AssetFileDescriptor descriptor;
//
//            // Prepare the sounds in memory
//            descriptor = assetManager.openFd("get_apple.mp3");
//            mEat_ID = mSP.load(descriptor, 0);
//
//            descriptor = assetManager.openFd("snake_death.mp3");
//            mCrashID = mSP.load(descriptor, 0);
//
//        } catch (IOException e) {
//            // Error
//        }

        // Initialize the drawing objects
        mSurfaceHolder = getHolder();
        mPaint = new Paint();

        // Call the constructors of our two game objects
        mApple = new Apple(context,
                new Point(NUM_BLOCKS_WIDE,
                        mNumBlocksHigh),
                blockSize);

        mSnake = new Snake(context,
                new Point(NUM_BLOCKS_WIDE,
                        mNumBlocksHigh),
                blockSize);
        mWall = new Wall(context, new Point(NUM_BLOCKS_WIDE, mNumBlocksHigh), blockSize);
        wList = new ArrayList<Wall>();
        // Call constructors to initialize the apple icon object, this is icon for the players score
        appleIcon = new Apple(context, new Point(NUM_BLOCKS_WIDE,
                mNumBlocksHigh),
                150);

        mGreenApple = new GreenApple(context, new Point(NUM_BLOCKS_WIDE, mNumBlocksHigh), blockSize);


    }


    // Called to start a new game
    public void newGame() {
        wList.clear();
        // reset the snake
        mSnake.reset(NUM_BLOCKS_WIDE, mNumBlocksHigh);

        // Get the apple ready for dinner
        mApple.spawn();
        mGreenApple.spawn(); // Spawn green apple
        mWall.spawn(mSnake, mApple);
        wList.add(mWall);

        // Reset the mScore
        mScore = 0;

        // Setup mNextFrameTime so an update can triggered
        mNextFrameTime = System.currentTimeMillis();
    }
    public void StartNewGame() {
        wList.clear();
        // reset the snake
        mSnake.reset(NUM_BLOCKS_WIDE, mNumBlocksHigh);

        // Get the apple ready for dinner
        mApple.spawn();
        mWall.spawn(mSnake, mApple);
        wList.add(mWall);
        // Reset the mScore
        mScore = 0;

        // Setup mNextFrameTime so an update can triggered
        mNextFrameTime = System.currentTimeMillis();


    }

    // Handles the game loop
    @Override
    public void run() {
        while (mPlaying) {
            if(!mPaused) {
                // Update 10 times a second
                if (updateRequired()) {
                    update();
                }
            }

            draw();
        }
    }
    @Override
    public void onRestartGame() {
        newGame();
        mPaused = false; // Ensure the game is not paused after restarting
    }
    public void onStartGame() {
        StartNewGame();
        mPaused = false;
        // Ensure the game is not paused after restarting
    }

    // Check to see if it is time for an update
    public boolean updateRequired() {

        // Run at 10 frames per second
        final long TARGET_FPS = 10;
        // There are 1000 milliseconds in a second
        final long MILLIS_PER_SECOND = 1000;

        // Are we due to update the frame
        if(mNextFrameTime <= System.currentTimeMillis()){
            // Tenth of a second has passed

            // Setup when the next update will be triggered
            mNextFrameTime =System.currentTimeMillis()
                    + MILLIS_PER_SECOND / TARGET_FPS;

            // Return true so that the update and draw
            // methods are executed
            return true;
        }

        return false;
    }


    // Update all the game objects
    public void update() {

        // Move the snake
        mSnake.move();
        for(int i = 0; i< wList.size();i++)
        {
            if (mSnake.checkCollide(wList.get(i))) {
                audioStrategy.playCrashSound();
                GameOver game_over = new GameOver(mScore);
                tap_to_play = false;
                game_over.showGameOverScreen(getContext(),mScore,mPaused, this); // displays the showGameOverScreen when the snake would collide to the wall
                mPaused =true;

                mPaused =true;
            }
        }/* Did the head of the snake eat the apple?*/

        if(mSnake.checkDinner(mApple.getLocation())){
            // This reminds me of Edge of Tomorrow.
            // One day the apple will be ready!
            mApple.spawn();
            Wall temp = new Wall(mContext, new Point(NUM_BLOCKS_WIDE, mNumBlocksHigh), mBlockSize);
            temp.spawn(mSnake,mApple);
            wList.add(temp);
            // Add to  mScore
            mScore = mScore + 1;

            // Play a sound
            audioStrategy.playEatSound();
        }

        if (mSnake.checkDinner(mGreenApple.getLocation())) {
            mScore = Math.max(0, mScore - 1); // Decrease score by 1, ensuring it doesn't go below 0
            audioStrategy.playTakeDamageSound(); // Play damage sound through the strategy interface
            mGreenApple.spawn(); // Respawn the green apple
        }

        // Did the snake die?
        if (mSnake.detectDeath()) {
            // Pause the game ready to start again
            audioStrategy.playCrashSound();
            GameOver game_over = new GameOver(mScore);
            tap_to_play = false;
            game_over.showGameOverScreen(getContext(),mScore,mPaused, this);
            mPaused =true;

        }

    }




    // Do all the drawing
    public void draw() {
        // Get a lock on the mCanvas
        if (mSurfaceHolder.getSurface().isValid()) {
            mCanvas = mSurfaceHolder.lockCanvas();

            // Fill the screen with a color
            mCanvas.drawColor(Color.BLACK); // Set background to black

            // Set the size and color of the mPaint for the text
            mPaint.setColor(Color.argb(255, 255, 255, 255));
            mPaint.setTextSize(120);

            // Draw the score
            appleIcon.drawIcon(mCanvas, mPaint, 30,30);
            mCanvas.drawText(": " + mScore, 180, 170, mPaint);

            // Draw the apple and the snake
            mApple.draw(mCanvas, mPaint);
            mGreenApple.draw(mCanvas, mPaint);
            mSnake.draw(mCanvas, mPaint);

            for(int i = 0; i< wList.size();i++)
            {
                wList.get(i).draw(mCanvas,mPaint);
            }
            //mWall.draw(mCanvas, mPaint);

            //delcared the LevelScore
            int levelScore = mScore + 1;
           // displays the levels
            mCanvas.drawText("Level: " + levelScore, 2200, 170, mPaint);

            // Draw some text while paused
            if(mPaused) {
                // Set the size and color of the mPaint for the text
               mPaint.setColor(Color.argb(255, 255, 255, 255));
                mPaint.setTextSize(100);

                // Draw the message
                // We will give this an international upgrade soon

                if(tap_to_play) {
                    //System.out.println(tap_to_play);
                    StartGame start_game = new StartGame();
                    tap_to_play = false;


                    // displays the showGameOverScreen when the snake would collide to the wall
                    start_game.showStartGameScreen(getContext(), this);
                    mPaused =true;

                }

            }

            // Unlock the mCanvas and reveal the graphics for this frame
            mSurfaceHolder.unlockCanvasAndPost(mCanvas);


        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                if (mPaused) {
                    mPaused = false;
                    newGame();


                    // Don't want to process snake direction for this tap
                    return true;
                }

                // Let the Snake class handle the input
                mSnake.switchHeading(motionEvent);
                break;

            default:
                break;

        }
        return true;
    }


    // Stop the thread
    public void pause() {
        mPlaying = false;
        try {
            mThread.join();
        } catch (InterruptedException e) {
            // Error
        }
    }


    // Start the thread
    public void resume() {
        mPlaying = true;
        mThread = new Thread(this);
        mThread.start();
    }
}
