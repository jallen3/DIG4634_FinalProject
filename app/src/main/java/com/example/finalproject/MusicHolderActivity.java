package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MusicHolderActivity extends AppCompatActivity {

    Button btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_holder);

        //TODO: Deactivate buttons
        btn3 = (Button) findViewById(R.id.button3);
        btn3.setEnabled(false);
    }

}