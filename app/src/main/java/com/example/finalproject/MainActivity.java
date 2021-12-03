package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button4;
    public static boolean quickplay = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onPlayClicked(View view){
        quickplay = true;
        Intent intent = new Intent(getBaseContext(),GameActivity.class);
        startActivity(intent);

    }

    public void onMusicClicked(View view){
        quickplay = false;
        Intent intent = new Intent(getBaseContext(),MusicHolderActivity.class);
        startActivity(intent);
    }

}

