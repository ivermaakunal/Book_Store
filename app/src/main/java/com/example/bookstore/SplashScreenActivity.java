package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences pref = getSharedPreferences("login",MODE_PRIVATE);
                Boolean check = pref.getBoolean("flag",false); //default value (if user opens the app for first time.

                Intent iNext;

                if(check){ // true for loggedin user

                    iNext = new Intent(SplashScreenActivity.this, MainActivity.class);

                }else{  //false for logged out user

                    iNext = new Intent(SplashScreenActivity.this,LoginActivity.class);
                }
                startActivity(iNext);
                finish();
            }
        },2500);
    }
}