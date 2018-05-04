package com.example.meda.shoppinglist;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Animation sendAnim = AnimationUtils.loadAnimation(SplashActivity.this,
                R.anim.anim_send);

        final ImageView ivLogo = (ImageView) findViewById(
                R.id.ivLogo);

        ivLogo.startAnimation(sendAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intentShowDetails = new Intent(
                        SplashActivity.this,
                        MainActivity.class
                );
                startActivity(intentShowDetails);
            }
        }, 3000);


    }
}
