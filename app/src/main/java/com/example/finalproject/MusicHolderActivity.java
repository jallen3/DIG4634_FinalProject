package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MusicHolderActivity extends AppCompatActivity {

    public static Button btn3;
    public static Button btn5;
    public static Boolean isEnabled1 = false;
    public static Boolean isEnabled2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_holder);

        //TODO: Deactivate buttons
        btn3 = (Button) findViewById(R.id.button3);
        btn3.setEnabled(false);

        btn5 = (Button) findViewById(R.id.button5);
        btn5.setEnabled(false);
    }

    @Override
    protected void onResume(){
        super.onResume();
        GameActivity.score = 0;
        GameActivity2.score = 0;
        GameActivity3.score = 0;

        if(isEnabled1){
            btn3.setEnabled(true);

        }

        if(isEnabled2){
            btn5.setEnabled(true);
        }

    }

    public void onLevel1Clicked(View view){
        Intent intent = new Intent(getBaseContext(),GameActivity.class);
        startActivity(intent);
    }

    public void onLevel2Clicked(View view){
        Intent intent = new Intent(getBaseContext(),GameActivity2.class);
        startActivity(intent);
    }

    public void onLevel3Clicked(View view){
        Intent intent = new Intent(getBaseContext(),GameActivity3.class);
        startActivity(intent);
    }
}