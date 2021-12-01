package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

//public class GameActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_game);
//    }
//}

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

public class GameActivity extends AppCompatActivity implements SensorEventListener, SurfaceHolder.Callback {
    // Use to Randomize the location and speed of objects
    static Random random = new Random();

    //Define paints and images


    //Set message
    String message = ""; //TODO - Is there a way to time the message placed on the screen?

    //Define surfaceHolder and Animator
    SurfaceHolder surfaceHolder;
    Animator animator;

    //Initialize score
    int score = 0;

    //Initialize img positions


    //Initialize Accelerometer values - Professor initializes over draw(), does that matter?
    float acc_x = 0;
    float acc_y = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Initiate Paints


        //Initiate Bitmap images


        //Initiate SensorManager
        SensorManager manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE); //controls sensor devices
        Sensor accelerometer = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); //select sensor you want to access and manage
        //Check to see if something is in the accelerometer or if it is an empty function
        if(accelerometer != null) {
            manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_UI); //what does this line do?
        }

        //Initialize SurfaceView
        SurfaceView surfaceView = findViewById(R.id.surfaceView);
        surfaceView.getHolder().addCallback(this); //Get hold of the surface and associate this class as a callback (What does that mean? Associated this class with the surfaceView obj in activity_main.xml)

        //Initialize Animator
        animator = new Animator(this);
        animator.start();//starts while loop in animator.java
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //Retrieve accelerometer values at x, y, & z positions
        acc_x = sensorEvent.values[0];
        acc_y = sensorEvent.values[1];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void update(int width, int height){

        //Update img positions every time its drawn


        //Ensure images don't go beyond canvas limits

        //TODO: [INSERT_PLAYER_IMG] = height-245; //sets the actual y position of the player on the screen

        //Check if objects are close to the player object


        //If music notes fall off screen reset to top


    }

    public void draw() {

        if(surfaceHolder == null) { return; } //If canvas is empty, ignore code below

        //Draw on canvas
        Canvas canvas = surfaceHolder.lockCanvas(); //canvas holds objects, colors, and shapes in surfaceView & lockCanvas makes it so that its only for us* to use (so users cant use it?)

        update(canvas.getWidth(), canvas.getHeight()); //Update animated variables & define canvas size

        //TODO: Draw to canvas


        surfaceHolder.unlockCanvasAndPost(canvas); //unlocks canvas and posts the 'drawings'
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        Log.d("Final", "The surface has been created.");
        this.surfaceHolder = surfaceHolder;
        draw();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        Log.d("Final", "The surface has been changed.");
        this.surfaceHolder = surfaceHolder;
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        this.surfaceHolder = null;
    }

    @Override
    public void onDestroy() {

        animator.finish();
        SensorManager manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        manager.unregisterListener(this, manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
        super.onDestroy();
    }
}