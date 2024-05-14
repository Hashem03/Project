package com.gamecodeschool.csc133project;


import android.content.Context;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * GameOver class displays the game over screen
 * and allows the user to restart the game or exit the game
 */
public class GameOver {
    private int mScore;
    public GameOver(int mScore){
        this.mScore = this.mScore;
    }

    /**
     * Interface for the GameOverListener
     * @param context the context of the application
     * @param score the score of the game
     * @param paused whether the game is paused
     * @param listener the listener for the game over screen
     */
    public static void showGameOverScreen(Context context, int score, boolean paused, GameOverListener listener) {
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {

                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.setPadding(20, 20, 20, 20);
                layout.setBackgroundColor(Color.BLACK);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(layout);
                builder.setCancelable(false);

                create_title(context, score,builder);

                create_score(context, score, layout);

                AlertDialog dialog = builder.create();

                create_restart_button(context, listener, layout, dialog);

                create_exit_button(context, listener, layout, dialog);

                dialog.show();
            }
        });
    }

    private static int get_high_score(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyScore", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("high_score", 0);
    }

    private static void save_score(Context context, int high_score) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyScore", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("high_score", high_score);
        editor.apply();
    }

    private static void create_title(Context context, int score, AlertDialog.Builder builder){
        TextView title = new TextView(context);

        // Check for High Score
        int high_score = get_high_score(context);
        if (score > high_score) {
            title.setText("New High Score!");
            save_score(context, score);
        } else {
            title.setText("Game Over!");
        }
        title.setTextSize(30);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.WHITE);
        title.setBackgroundColor(Color.BLACK);
        title.setTypeface(Typeface.SANS_SERIF);
        builder.setCustomTitle(title);
    }

    /**
     * Creates the score layout
     * @param context the context of the application
     * @param score the score of the game
     * @param layout the layout of the game over screen
     */
    private static void create_score(Context context, int score, LinearLayout layout){
        LinearLayout scoreLayout = new LinearLayout(context);
        scoreLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        scoreLayout.setGravity(Gravity.CENTER);
        scoreLayout.setBackgroundColor(Color.BLACK);
        int padding = (int) (context.getResources().getDisplayMetrics().density * 10);
        scoreLayout.setPadding(padding, padding, padding, padding);
        TextView scoreView = new TextView(context);
        scoreView.setText(String.valueOf(score));
        scoreView.setTypeface(Typeface.SANS_SERIF);
        scoreView.setTextColor(Color.WHITE);
        scoreView.setTextSize(40);

        scoreLayout.addView(scoreView);
        layout.addView(scoreLayout);
    }

    private static void create_restart_button(Context context,GameOverListener listener,LinearLayout layout, AlertDialog dialog ){
        // Create Restart Button
        Button btnRestart = new Button(context);
        btnRestart.setText("Restart");
        btnRestart.setTextColor(Color.WHITE);
        btnRestart.setBackgroundColor(Color.parseColor("#8B008B"));
        btnRestart.setPadding(20, 10, 20, 10);
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRestartGame();
                dialog.dismiss();
            }
        });
        layout.addView(btnRestart);
    }

    private static void create_exit_button(Context context,GameOverListener listener,LinearLayout layout, AlertDialog dialog ){
        // Create Exit button
        Button btnExit = new Button(context);
        btnExit.setText("Exit");
        btnExit.setTextColor(Color.WHITE);
        btnExit.setBackgroundColor(Color.parseColor("#8B0000")); // Dark red color
        btnExit.setPadding(20, 10, 20, 10);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) context).finish();
            }
        });
        layout.addView(btnExit);
    }


}
