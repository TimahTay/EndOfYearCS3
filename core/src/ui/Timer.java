/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.badlogic.gdx.Gdx;

/**
 *
 * @author Admin
 */
public class Timer {
    
    private float time;
    
    public Timer() {
            time = 0;
    }
    
    public Timer(int time) {
        this.time = time;
    }

    public void start() {
        int totalTime = 0;
        float deltaTime = Gdx.graphics.getDeltaTime();
        totalTime += deltaTime;
        int seconds = ((int)time) % 60;
    }
    
    public int getTime() {
        return (int) Math.round(time);
    }

    public void setTime(int time) {
        this.time = time;
    }
    
}
