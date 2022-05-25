package com.example.myownapplications;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imgWheel, imgPointer;
    Button btnSpin;
    Random random;
    TextView tvScores;

    double factor = 0.0d;
    double currPosition = 0.0d;

    private int oldDegrees = 0, newDegrees = 0, allPassedDegrees = 0;
    private String[] numbers = {"32 RED", "15 BLACK", "19 RED", "4 BLACK",
            "21 RED", "2 BLACK", "25 RED", "17 BLACK", "34 RED",
            "6 BLACK", "27 RED", "13 BLACK", "36 RED", "11 BLACK", "30 RED",
            "8 BLACK", "23 RED", "10 BLACK", "5 RED", "24 BLACK", "16 RED", "33 BLACK",
            "1 RED", "20 BLACK", "14 RED", "31 BLACK", "9 RED", "22 BLACK", "18 RED",
            "29 BLACK", "7 RED", "28 BLACK", "12 RED", "35 BLACK", "3 RED", "26 BLACK", "0"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        random = new Random();

        imgWheel = (ImageView) findViewById(R.id.imgWheel);
        imgPointer = (ImageView) findViewById(R.id.imgPointer);
        tvScores = (TextView) findViewById(R.id.tvScores);

        btnSpin = (Button) findViewById(R.id.btnSpin);
        btnSpin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        oldDegrees = newDegrees % 360;
        newDegrees = random.nextInt(3600) + 720;


        RotateAnimation rotate = new RotateAnimation(oldDegrees, newDegrees,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(4000);
        rotate.setFillAfter(true);
        rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                allPassedDegrees += newDegrees;
                factor = allPassedDegrees / 360.0;
                currPosition = allPassedDegrees - factor*360;
                currPosition -= (360.0/37.0)/2.0;
                currPosition /= (360.0/37.0);
                tvScores.setText("Current Degrees " + newDegrees + "\nScores" + currPosition);
//                Toast.makeText(MainActivity.this, "Current Degrees " + newDegrees + " \nOld degrees " + oldDegrees, Toast.LENGTH_LONG);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        imgWheel.startAnimation(rotate);
    }
}