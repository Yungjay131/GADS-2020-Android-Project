package com.slyworks.gads2020_android_project;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Joshua Sylvanus, 2:37AM, 2/09/2020.
 */
public class SplashScreenActivity extends AppCompatActivity {
    //region Vars
    private static final String TAG = "SplashActivity";
    private Handler mHandler;
    //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "run: Thread = " + Thread.currentThread().getName());
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                finish();
            }
        },3000);
    }

}