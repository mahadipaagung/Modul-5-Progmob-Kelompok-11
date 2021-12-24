package com.example.Modul5Progmob.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.Modul5Progmob.App;
import com.example.Modul5Progmob.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class FcmReceiver extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d("FCM", "onMessageReceived: ");
        RemoteMessage.Notification dataFcmNotification = remoteMessage.getNotification();
        Map<String, String> dataFcm = remoteMessage.getData();

        String strTitle = dataFcmNotification.getTitle();
        String strBody = dataFcmNotification.getBody();

        sendNotification(strTitle, strBody);
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.d("FCM-TOKEN", "onNewToken: "+s);
        App.setupFcm(this);
    }

    private void sendNotification(String title, String body){
        String channelid = "default_channel_id";
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelid)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setAutoCancel(true);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel =  new NotificationChannel(channelid, "channel", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        notificationManager.notify(0, notificationBuilder.build());
    }
}
