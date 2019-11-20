package com.example.calendar_test01;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

public class Ring extends calendarMain {

    public void Ring(){

    String id ="channel_1";//channel的id
    String description = "123";//channel的描述信息
    int importance = NotificationManager.IMPORTANCE_LOW;//channel的重要性
    NotificationChannel channel = new NotificationChannel(id, "123", importance);//生成channel
            channel.enableVibration(true);//震动
            channel.enableLights(true);//提示灯
    //notification
    //set up the notification services
    NotificationManager notify_manager;
        notify_manager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        //set up an intent that goes to the timePicker_1
    Intent intent_main_activity = new Intent(this,timePicker_1.class);
            intent_main_activity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//
    //set up a pending intent
    PendingIntent pending_intent_main_activity = PendingIntent.getActivity(this.getApplicationContext(),0,
            intent_main_activity,0);
            notify_manager.createNotificationChannel(channel);
    //make the notification parameters
    Notification notification_popup = new Notification.Builder(this,id)
            .setContentTitle("An alarm is going off!")
            .setContentText("Click me!")
            .setContentIntent(pending_intent_main_activity)
            .setSmallIcon(R.drawable.ic_phone)
            .setAutoCancel(true)
            .build();
            Log.i("1","2");
    //set up notification start commend
            notify_manager.notify(0,notification_popup);

}}
