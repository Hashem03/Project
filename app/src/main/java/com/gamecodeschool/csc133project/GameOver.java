package com.gamecodeschool.csc133project;


import android.app.Dialog;
import android.content.Context;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.text.Spanned;

public class GameOver {
    private int mScore;
    public GameOver(int mScore){
        this.mScore = this.mScore;
    }

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

                TextView title = new TextView(context);
                title.setText("Game Over!");
                title.setTextSize(30);
                title.setGravity(Gravity.CENTER);
                title.setTextColor(Color.WHITE); // Example color
                title.setBackgroundColor(Color.BLACK); // Example background color
                title.setTypeface(Typeface.SANS_SERIF);

                TTF customTypefaceSpan = new TTF(context);
                SpannableString spannableString = new SpannableString(title.getText());
                spannableString.setSpan(customTypefaceSpan, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                title.setText(spannableString);


// Set the custom title view
                builder.setCustomTitle(title);

                LinearLayout scoreLayout = new LinearLayout(context);
                scoreLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                scoreLayout.setGravity(Gravity.CENTER);
                scoreLayout.setBackgroundColor(Color.BLACK); // White background color
                int padding = (int) (context.getResources().getDisplayMetrics().density * 10); // Convert 10dp to pixels
                scoreLayout.setPadding(padding, padding, padding, padding); // Add padding

// Create a TextView for the score inside the custom layout
                TextView scoreView = new TextView(context);
                scoreView.setText(String.valueOf(score));
                scoreView.setTypeface(Typeface.SANS_SERIF); // Set font to sans serif
                scoreView.setTextColor(Color.WHITE); // Set text color to white
                scoreView.setTextSize(40);
                scoreLayout.addView(scoreView);

// Add the custom layout to the dialog layout


                layout.addView(scoreLayout);


// Set the custom title view
                builder.setCustomTitle(title);




                AlertDialog dialog = builder.create();
                // Create a Restart button
                Button btnRestart = new Button(context);
                btnRestart.setText("Restart");
                btnRestart.setTextColor(Color.WHITE);
                btnRestart.setBackgroundColor(Color.parseColor("#8B008B")); // Dark purple color
                btnRestart.setPadding(20, 10, 20, 10);
                btnRestart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onRestartGame();
                        dialog.dismiss();
                    }
                });
                layout.addView(btnRestart);

                // Create an Exit button
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

                // Create the AlertDialog


                // Show the dialog
                dialog.show();


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
