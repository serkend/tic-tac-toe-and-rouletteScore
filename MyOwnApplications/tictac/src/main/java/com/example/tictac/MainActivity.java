package com.example.tictac;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvPlOneScores, tvPlTwoScores, whoIsWinner;
    int score1 = 0, score2 = 0;
    Button[] buttons = new Button[9];
    int[] buttonState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winnerTable =
            {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                    {0, 4, 8}, {2, 4, 6},
                    {0, 3, 6}, {1, 4, 7}, {2, 5, 8}
            };
    boolean played;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        whoIsWinner = (TextView) findViewById(R.id.whoIsWinner);
        tvPlOneScores = (TextView) findViewById(R.id.playerOneScores);
        tvPlTwoScores = (TextView) findViewById(R.id.playerTwoScores);
        for (int i = 0; i < buttons.length; i++) {
            String btnId = "btn_" + i;
            int resourceId = getResources().getIdentifier(btnId, "id", getPackageName());
            buttons[i] = (Button) findViewById(resourceId);
            buttons[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        String strId = getResources().getResourceEntryName(v.getId());
        int numId = Integer.parseInt(strId.substring(strId.length() - 1));

        if (!played) {
            whoIsWinner.setText("Player 2 turn!");
            buttons[numId].setText("X");
            buttons[numId].setClickable(false);
            played = true;
            buttonState[numId] = 1;
        } else if (played) {
            whoIsWinner.setText("Player 1 turn!");
            buttons[numId].setText("0");
            buttons[numId].setClickable(false);
            played = false;
            buttonState[numId] = 0;
        }
        if (checkWinner() != 2) {
            if (checkWinner() == 1) {
                whoIsWinner.setText("Player 1 won!");
                score1++;
                tvPlOneScores.setText(Integer.toString(score2));

            } else if (checkWinner() == 0) {
                whoIsWinner.setText("Player 2 won!");
                score2++;
                tvPlOneScores.setText(Integer.toString(score2));
            }
            whoIsWinner.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate));
            for (int i = 0; i < buttons.length; i++) {
                buttons[i].setClickable(true);
                buttons[i].setText("");
                buttonState[i] = 2;
            }
        }

        if(checkIfDraw()) {
            whoIsWinner.setText("Player 1 turn!");
            played = false;
            for (int i = 0; i < buttons.length; i++) {
                buttons[i].setClickable(true);
                buttons[i].setText("");
                buttonState[i] = 2;
            }
        }
    }

    private int checkWinner() {
        for (int[] winArrPos : winnerTable) {
            if (buttonState[winArrPos[0]] == buttonState[winArrPos[1]] && buttonState[winArrPos[1]] == buttonState[winArrPos[2]]
                    && buttonState[winArrPos[0]] != 2) {
                return buttonState[winArrPos[0]];
            }
        }
        return 2;
    }

    private boolean checkIfDraw() {
        if (checkWinner() == 1 || checkWinner() == 0) {
            return false;
        }
        for (int i = 0; i < buttonState.length; i++) {
            if (buttonState[i] == 2) {
                return false;
            }
        }
        return true;
    }

    private void initArray(int[] arr) {
        int g = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                winnerTable[i][j] = arr[g];
                g++;
            }
        }
    }
}