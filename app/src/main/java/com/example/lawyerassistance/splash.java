package com.example.lawyerassistance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread td = new Thread(){
            public void run(){
                try {
                    sleep(1000);
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
                finally {
                    Intent sp = new Intent(splash.this,login.class);
                    startActivity(sp);
                }
            }
        };td.start();
    }
}