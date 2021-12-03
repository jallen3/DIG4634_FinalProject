package com.example.finalproject;

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
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

public class GameActivity2 extends AppCompatActivity implements SensorEventListener, SurfaceHolder.Callback {
    // Use to Randomize the location and speed of objects
    static Random random = new Random();

    //Define Music Player
    MediaPlayer mediaPlayer;

    //Define paints and images
    Paint whiteText;
    Bitmap player;
    Bitmap c1;
    Bitmap c2;
    Bitmap c3;
    Bitmap c4;

    //Set message
    String message = ""; //TODO - Is there a way to time the message placed on the screen?

    //Define surfaceHolder and Animator
    SurfaceHolder surfaceHolder;
    Animator2 animator;

    //Initialize score
    public static int score = 0;

    //Initialize img positions
    int player_x_pos = 0;
    int player_y_pos = 0;
    int c1_x_pos = 100;
    int c1_y_pos = 0;
    int c2_x_pos = 300;
    int c2_y_pos = 0;
    int c3_x_pos = 500;
    int c3_y_pos = 0;
    int c4_x_pos = 800;
    int c4_y_pos = 0;

    //Initialize Accelerometer values - Professor initializes over draw(), does that matter?
    float acc_x = 0;
    float acc_y = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        //Sound
        mediaPlayer = MediaPlayer.create(this, R.raw.medium);
        mediaPlayer.start();

        //Initialize Paints
        whiteText = new Paint();
        whiteText.setColor(Color.WHITE); //will paint object white
        whiteText.setTextSize(100);

        //Initialize Bitmap images
        player = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.c1), 200, 200, false); //Load image into bitMap variable
        c1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.c2), 200, 200, false);
        c2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.c3), 200, 200, false);
        c3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.c4), 200, 200, false);
        c4 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.c5), 200, 200, false);

        //Initialize SensorManager
        SensorManager manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE); //controls sensor devices
        Sensor accelerometer = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); //select sensor you want to access and manage
        //Check to see if something is in the accelerometer or if it is an empty function
        if(accelerometer != null) {
            manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_UI); //what does this line do?
        }

        //Initialize SurfaceView
        SurfaceView surfaceView = findViewById(R.id.surfaceView2);
        surfaceView.getHolder().addCallback(this); //Get hold of the surface and associate this class as a callback (What does that mean? Associated this class with the surfaceView obj in activity_main.xml)

        //Initialize Animator
        animator = new Animator2(this);
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
        player_x_pos -= acc_x * 2;
        c1_y_pos += random.nextInt(5) + 10;
        c2_y_pos += random.nextInt(5) + 10;
        c3_y_pos += random.nextInt(5) + 10;
        c4_y_pos += random.nextInt(5) + 10;

        //Ensure player doesn't go beyond canvas limits
        if(player_x_pos < 0) {
            player_x_pos = 0;
        }
        else if(player_x_pos > width - 200) { //width - size of moneyBag
            player_x_pos = width - 200;
        }

        player_y_pos = height-200; //sets the actual y position of the player on the screen

        //Check if objects are close to the player object
        if(Math.abs(player_x_pos - c1_x_pos) < 200 && Math.abs(player_y_pos - c1_y_pos) < 200 ) {
            c1_y_pos = 0;
            score++;
        }

        if(Math.abs(player_x_pos - c2_x_pos) < 200 && Math.abs(player_y_pos - c2_y_pos) < 200 ) {
            c2_y_pos = 0;
            score++;
        }

        if(Math.abs(player_x_pos - c3_x_pos) < 200 && Math.abs(player_y_pos - c3_y_pos) < 200 ) {
            c3_y_pos = 0;
            score++;
        }

        if(Math.abs(player_x_pos - c4_x_pos) < 200 && Math.abs(player_y_pos - c4_y_pos) < 200) {
            c4_y_pos = 0;
            score++;
        }

        if(score == 10) {
            MusicHolderActivity.isEnabled2 = true;
            message = "New Level Unlocked";

        }

        //if sound stops close activity
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                finish();
            }
        });


        //If music notes fall off screen reset to top
        if(c1_y_pos > height || c2_y_pos > height || c3_y_pos > height || c4_y_pos > height) {
            c1_y_pos = 0;
            c2_y_pos = 0;
            c3_y_pos = 0;
            c4_y_pos = 0;

            //Place objects in different locations along the width of the screen
            c1_x_pos = random.nextInt(width);
            c2_x_pos = random.nextInt(width);
            c3_x_pos = random.nextInt(width);
            c4_x_pos = random.nextInt(width);
        }

    }

    public void draw() {

        if(surfaceHolder == null) { return; } //If canvas is empty, ignore code below

        //Draw on canvas
        Canvas canvas = surfaceHolder.lockCanvas(); //canvas holds objects, colors, and shapes in surfaceView & lockCanvas makes it so that its only for us* to use (so users cant use it?)

        update(canvas.getWidth(), canvas.getHeight()); //Update animated variables & define canvas size

        //TODO: set background as moving image
        canvas.drawColor(Color.rgb(210, 159,227)); //Set background color
        canvas.drawText("Score: " + score, 20 , 125, whiteText); //Set score text
        canvas.drawText(message, 30 , 300, whiteText); //Set game message text
        canvas.drawBitmap(player, player_x_pos, player_y_pos, null); //TODO - Position player in middle of the screen
        canvas.drawBitmap(c1, c1_x_pos, c1_y_pos, null);
        canvas.drawBitmap(c2, c2_x_pos, c2_y_pos, null);
        canvas.drawBitmap(c3, c3_x_pos, c3_y_pos, null);
        canvas.drawBitmap(c4, c4_x_pos, c4_y_pos, null);

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