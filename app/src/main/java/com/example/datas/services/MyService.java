package com.example.datas.services;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;

import com.example.datas.R;

import static android.app.Service.START_NOT_STICKY;

public class MyService extends Service {


    private NotificationManager mNotificationManager;
    private Notification notification;
    private NotificationCompat.Builder mBuilder;
    public static final String NOTIFICATION_CHANNEL_ID = "10001";


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String info = "New Arrivals Coming Soon!!!";

        String CHANNEL_ID = "hello gorgeous channel";


        Context context = getApplicationContext();

        PendingIntent action = PendingIntent.getActivity(context,
                0, new Intent(context, ForegoundService.class),
                PendingIntent.FLAG_CANCEL_CURRENT);
        // Flag indicating that if the described PendingIntent
        // already exists, the current one should be canceled before generating a new one.


        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);



        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "HelloGorgeousChannel",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            assert mNotificationManager != null;

            channel.setDescription("Hello Gorgeous channel description");
            mNotificationManager.createNotificationChannel(channel);



            mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID);
        }
        else {
            mBuilder = new NotificationCompat.Builder(context);
        }

        notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentIntent(action)
                .setContentTitle(info)
                .setTicker(info)
                .setContentText(info)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(action)
                .setOngoing(true).build();


        startForeground(1, notification);


        //return Service.START_STICKY;
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //stopping the player when service is destroyed
        stopForeground(true);
    }

}
