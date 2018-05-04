package com.example.meda.minesweeper;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.meda.minesweeper.view.MineSweeperView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout layoutContent;
    private MineSweeperView mineField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutContent = (LinearLayout) findViewById(R.id.layoutContent);

        mineField = (MineSweeperView) findViewById(R.id.mineField);

        final ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isFlag) {
                if (isFlag) {
                   mineField.flagMode = true;
                } else {
                    // The toggle is disabled
                    mineField.flagMode = false;
                }//if-else

            }//CheckedChangeListener
        });

            final Button restartButton = (Button) findViewById(R.id.restartButton);
            restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleButton.setChecked(false);
                mineField.clearGameField();
            }
        });

    }//onCreate

    public void showMessage(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }//showMessage

    public void showSnackbarMessage(String msg) {
        Snackbar.make(layoutContent, msg, Snackbar.LENGTH_LONG).setAction(
                R.string.playAgain, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mineField.clearGameField();
                    }
                }
        ).show();
    }

}//MainActivity
