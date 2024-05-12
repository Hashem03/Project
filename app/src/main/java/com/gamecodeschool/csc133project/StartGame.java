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


public class StartGame {

    public StartGame(){

    }


        public static void showStartGameScreen(Context context, GameOverListener listener) {
            ((Activity)context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("        SNAKE GAME");

                    builder.setMessage( "      Press play To Start!   ");

                    // Customizing the dialog layout
                    LinearLayout layout = new LinearLayout(context);
                    layout.setOrientation(LinearLayout.VERTICAL);
                    layout.setPadding(30, -10, 30, -10);

                    builder.setView(layout);
                    builder.setCancelable(false);

                    AlertDialog dialog = builder.create();

                    TextView messageTextView = new TextView(context);
                    messageTextView.setTextSize(100);
                    messageTextView.setTextColor(Color.BLACK);
                    messageTextView.setGravity(Gravity.LEFT);
                    layout.addView(messageTextView);

                    Button restartButton = new Button(context);
                    restartButton.setText(" PLAY");
                   restartButton.setTextSize(17);
                    restartButton.setTextColor(Color.WHITE);
                    restartButton.setBackgroundColor(Color.RED);
                    //restartButton.setPadding(100, 200, 200, 100);
                    restartButton.setPadding(90, 80, 90, 80);
                    restartButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Handle restart logic here
                            listener.onRestartGame();
                            dialog.dismiss();
                        }
                    });

                    layout.addView(restartButton);


                    // Setting custom styles for the dialog buttons
                    dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                        @Override

                        public void onShow(DialogInterface dialog) {
                            Button positiveButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_POSITIVE);
                            positiveButton.setTextColor(Color.WHITE);
                            positiveButton.setBackgroundColor(Color.GREEN);

                            Button negativeButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_NEGATIVE);
                            negativeButton.setTextColor(Color.WHITE);
                            negativeButton.setBackgroundColor(Color.GREEN);
                        }

                    });

                    dialog.show();

                }



           });


        }
    }
