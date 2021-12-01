package com.example.finalproject;

public class Animator extends Thread{

    GameActivity surfaceActivity;
    boolean isRunning = false;

    public Animator(GameActivity surfaceActivity) { this.surfaceActivity = surfaceActivity; }

    public void run() {
        isRunning = true;

        while(isRunning) {
            surfaceActivity.draw();

            //Draw images then sleep for 50ms before drawing the image again
            try {
                sleep(50);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void finish() { isRunning = false; }
}
