package com.gamecodeschool.csc133project;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import java.io.IOException;

public class BasicAudioStrategy implements AudioStrategy {
    private SoundPool mSoundPool;
    private int mEatSoundId;
    private int mCrashSoundId;

    public BasicAudioStrategy(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            mSoundPool = new SoundPool.Builder()
                    .setMaxStreams(5)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            mSoundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }
        loadSounds(context);
    }

    private void loadSounds(Context context) {
        try {
            mEatSoundId = mSoundPool.load(context.getAssets().openFd("get_apple.mp3"), 1);
            mCrashSoundId = mSoundPool.load(context.getAssets().openFd("snake_death.mp3"), 1);
        } catch (IOException e) {
            // Exception handling
        }
    }

    @Override
    public void playEatSound() {
        mSoundPool.play(mEatSoundId, 1, 1, 0, 0, 1);
    }

    @Override
    public void playCrashSound() {
        mSoundPool.play(mCrashSoundId, 1, 1, 0, 0, 1);
    }
}
