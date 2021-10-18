package com.example.gstest.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Nullable;

import com.example.gstest.R;

public class SplashActivity extends Activity {
    private Handler mWaitHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        displaySplashScreen();
    }

    private void displaySplashScreen() {
        mWaitHandler.postDelayed(() -> {
            try {
                Intent intent = new Intent(getApplicationContext(), SelectDateActivity.class);
                startActivity(intent);
                finish();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }, 3000);
    }
}
