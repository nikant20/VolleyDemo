package com.example.nikant20.volleydemo.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nikant20.volleydemo.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);

        handler.sendEmptyMessageDelayed(001,10000);

    }


    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 001) {

                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();


            }
        }
    };


}
