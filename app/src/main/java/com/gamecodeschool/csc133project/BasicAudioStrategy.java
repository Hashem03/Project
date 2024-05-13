// BasicAudioStrategy.java
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
    private int mTakeDamageSoundId;
    private Context mContext; // Member variable to store context

    public BasicAudioStrategy(Context context) {
        mContext = context; // Assign the passed context to the member variable
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
        loadSounds();
    }

    private void loadSounds() {
        try {
            mEatSoundId = mSoundPool.load(mContext.getAssets().openFd("get_apple.mp3"), 1);
            mCrashSoundId = mSoundPool.load(mContext.getAssets().openFd("snake_death.mp3"), 1);
            mTakeDamageSoundId = mSoundPool.load(mContext.getAssets().openFd("takedamage.mp3"), 1);
        } catch (IOException e) {
            // Handle exceptions here
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

    @Override
    public void playTakeDamageSound() {
        mSoundPool.play(mTakeDamageSoundId, 1, 1, 0, 0, 1);
    }
}
