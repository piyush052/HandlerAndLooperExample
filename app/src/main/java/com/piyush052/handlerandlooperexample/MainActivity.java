package com.piyush052.handlerandlooperexample;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SimpleWorker worker;
    private TextView txtMsg;
    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            txtMsg.append(msg.obj + "\n");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMsg= findViewById(R.id.textviewMsg);
        worker= new SimpleWorker();
        worker.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = Message.obtain();
                message.obj= "Task1 completed ";
                handler.sendMessage(message);
            }
        }).execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = Message.obtain();
                message.obj= "Task2 completed ";
                handler.sendMessage(message);
            }
        }).execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = Message.obtain();
                message.obj= "Task3 completed ";
                handler.sendMessage(message);
            }
        });


        txtMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                worker.quit();
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        worker.quit();
    }
}
