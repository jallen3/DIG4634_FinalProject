package com.example.finalproject;

public class Animator2 extends Thread{
    GameActivity2 surfaceActivity2;
    boolean isRunning = false;

    public Animator2(GameActivity2 surfaceActivity2) { this.surfaceActivity2 = surfaceActivity2; }

    public void run() {
        isRunning = true;

        while(isRunning) {
            surfaceActivity2.draw();

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
