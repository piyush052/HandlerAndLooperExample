package com.piyush052.handlerandlooperexample;

import android.os.Handler;
import android.os.HandlerThread;

public class Worker extends HandlerThread {
    final String TAG = "HandlerThread ";
    private Handler handler;

    public Worker(String name) {
        super(name);
        start();
        handler = new Handler(getLooper());
    }
    public Worker execute (Runnable task){
            handler.post(task);
        return this;
    }
    public void quitTask() {
        this.quit();
    }
}
