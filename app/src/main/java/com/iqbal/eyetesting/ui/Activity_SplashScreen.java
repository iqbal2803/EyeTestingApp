package com.iqbal.eyetesting.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.iqbal.eyetesting.R;

public class Activity_SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally{
                    Intent i = new Intent(getApplicationContext(),Activity_Home.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }
}
