package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvPlOneScores, tvPlTwoScores, whoIsWinner;
    Button[] buttons;
    boolean played;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < buttons.length; i++) {
            String btnId = "btn_" + i;
            int resourceId = getResources().getIdentifier(btnId, "id", getPackageName());
            buttons[i] = (Button) findViewById(resourceId);
        }
    }

    @Override
    public void onClick(View v) {
//        Button btnTemp = (Button) findViewById(v.getId());
//        btnTemp.setOnClickListener(this);
//        if (!played) {
//            btnTemp.setText("X");
//            played = true;
//        } else if (played) {
//            btnTemp.setText("0");
//            played = false;
//        }
    }
}