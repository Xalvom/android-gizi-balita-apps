package com.himman.gizibalita.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.himman.gizibalita.R;

public class SplashScreenActivity extends AppCompatActivity {
    private ImageView iv;
    private TextView tv;
    private TextView tvC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        iv = findViewById(R.id.iv);
        tv = findViewById(R.id.tv);
        tvC = findViewById(R.id.tvCredit);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.mytrans);
        iv.startAnimation(anim);
        tv.startAnimation(anim);
        tvC.startAnimation(anim);
        final Intent i = new Intent(this, LoginActivity.class);
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }
        };
                timer.start();
    }
}
