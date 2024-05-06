package com.gamecodeschool.csc133project;


import android.app.Dialog;
import android.content.Context;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameOver {
    private int mScore;
    public GameOver(int mScore){
        this.mScore = this.mScore;
    }

    public static void showGameOverScreen(Context context, int score, boolean paused, GameOverListener listener) {
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Game Over");
                builder.setMessage("Your score: " + score);

                // Customizing the dialog layout
                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.setPadding(50, 50, 50, 50);

                builder.setView(layout);
                builder.setCancelable(false);
                AlertDialog dialog = builder.create();

                TextView messageTextView = new TextView(context);
                messageTextView.setText("Your score: " + score);
                messageTextView.setTextSize(18);
                messageTextView.setTextColor(Color.BLACK);
                messageTextView.setGravity(Gravity.CENTER);
                layout.addView(messageTextView);

                Button restartButton = new Button(context);
                restartButton.setText("Restart");
                restartButton.setTextColor(Color.WHITE);
                restartButton.setBackgroundColor(Color.BLUE);
                restartButton.setPadding(20, 10, 20, 10);
                restartButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Handle restart logic here
                        listener.onRestartGame();
                        dialog.dismiss();
                    }
                });
                layout.addView(restartButton);

                Button exitButton = new Button(context);
                exitButton.setText("Exit");
                exitButton.setTextColor(Color.WHITE);
                exitButton.setBackgroundColor(Color.RED);
                exitButton.setPadding(20, 10, 20, 10);
                exitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((Activity) context).finish();
                    }
                });
                layout.addView(exitButton);



                // Setting custom styles for the dialog buttons
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        Button positiveButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_POSITIVE);
                        positiveButton.setTextColor(Color.WHITE);
                        positiveButton.setBackgroundColor(Color.BLUE);

                        Button negativeButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_NEGATIVE);
                        negativeButton.setTextColor(Color.WHITE);
                        negativeButton.setBackgroundColor(Color.RED);
                    }
                });

                dialog.show();
            }
        });
    }
}
