package com.example.datas.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.datas.R;

public class ForegoundService extends AppCompatActivity implements View.OnClickListener {


    private Button start;
    private Button stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foreground_services);


        //getting buttons from xml
        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);

        //attaching onclicklistener to buttons
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        if (view == start) {
            //start the service here
            startService(new Intent(this, MyService.class));
        } else if (view == stop) {
            //stop the service here
            stopService(new Intent(this, MyService.class));
        }
    }

}

