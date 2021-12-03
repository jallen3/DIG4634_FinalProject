package com.example.finalproject;

public class Animator3 extends Thread{
    GameActivity3 surfaceActivity3;
    boolean isRunning = false;

    public Animator3(GameActivity3 surfaceActivity3) { this.surfaceActivity3 = surfaceActivity3; }

    public void run() {
        isRunning = true;

        while(isRunning) {
            surfaceActivity3.draw();

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
