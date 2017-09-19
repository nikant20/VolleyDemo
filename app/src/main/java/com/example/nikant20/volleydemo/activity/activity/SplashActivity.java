package com.example.nikant20.volleydemo.activity.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nikant20.volleydemo.R;
import com.example.nikant20.volleydemo.activity.model.Util;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);


        sharedPreferences = getSharedPreferences(Util.PREFS_NAME, MODE_PRIVATE);
        boolean check = sharedPreferences.getBoolean(Util.KEY_LOGREG, false);


        if (check) {
            handler.sendEmptyMessageDelayed(000, 3000);
        } else
            handler.sendEmptyMessageDelayed(100, 3000);


    }


    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 100) {

                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }else{
                Intent intent = new Intent(SplashActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };


}
