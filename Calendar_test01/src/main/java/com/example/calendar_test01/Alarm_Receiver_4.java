package com.example.calendar_test01;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;


import androidx.annotation.RequiresApi;

import static android.content.ContentValues.TAG;
import static android.content.Context.NOTIFICATION_SERVICE;


public class Alarm_Receiver_4 extends BroadcastReceiver {


    public int startId;
    boolean isRunning;



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("We are in the receiver","Yay!");

        String get_your_string = intent.getExtras().getString("extra");
        Log.e("What is the key?",get_your_string);

        calendarMain.media_song= MediaPlayer.create(context,R.raw.alarm);
     /*   Intent service_intent = new Intent(context,timePicker_1.class);
        Bundle bund = new Bundle();
        bund.putString("extra01",get_your_string);
        service_intent.putExtras(bund);*/
        Log.e("What is the key?",get_your_string);

        // context.startService(service_intent);
        String state = intent.getExtras().getString("extra");

        assert state != null;
        switch (state) {
            case "alarm on":
                startId = 1;
                break;
            case "alarm off":
                startId = 0;
                Log.e("Start ID is",state);
                break;
            default:
                startId = 0;
                break;
        }

        //if else statements

        //if there is no music playing,and the user pressed"alarm on"
        //music should start plaing
        if(!isRunning && startId ==1){
            Log.e("there is no music, " ,"and you want start");
            //create an instance of the media player

            //start the ringtone


//----------------------------------------------------------------------提示
            NotificationChannel channel = new NotificationChannel(
                    "myid",
                    "Chcnel Name",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.enableVibration(true);//震动
            channel.enableLights(true);//提示灯

            Intent intent_main_activity = new Intent(context, calendarMain.class);
            intent_main_activity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//
            //set up a pending intent
            PendingIntent pending_intent_main_activity = PendingIntent.getActivity(context, 0,
                    intent_main_activity, 0);


            Notification message = new Notification.Builder(context)
                    .setContentTitle("睡前該吃藥囉 ")
                    .setContentText("訊息來自 I-Medicine")
                    .setContentIntent(pending_intent_main_activity)
                    .setAutoCancel(true)
                    .setSmallIcon(R.drawable.ic_perm_phone_msg_black_24dp)
                    .setChannelId("myid")
                    .build();

            NotificationManager manager =
                    (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);
            manager.notify(0, message);

            calendarMain.media_song.start();

            isRunning = true;
            startId = 0;
            //this converts the extra strings from the intent
            //to start IDs, values 0 or 1

//------------------------------------------------------------------------------------
        }

        //if there is music playing, and the user pressed "alarm off"
        //music should stop playing
        else if (isRunning && startId ==0){
            Log.e("there is  music, " ,"and you want end");

            //stop the ringtone
            calendarMain.media_song.stop();




            isRunning = false;
            startId =0;
            Log.e("there is  music, " ,"and you want end");
        }

        //there are if the user presses random buttons
        //just to bug-proof the app
        //if there is no music playing, and the user pressed "alarm off"
        //do nothing
        else if (!this.isRunning && startId ==0){
            Log.e("there is no music, " ,"and you want end");

            isRunning = false;
            startId =0;


        }

        //if there is music playing and the user pressed "alarm on"
        //do nothing
        else if (isRunning && startId ==1){
            Log.e("there is  music, " ,"and you want start");

            isRunning = true;
            startId =1;


        }

        //can't think of anything else, just to catch the odd event
        else {
            Log.e("else " ,"somehow you reached this");
        }
return;

    }



}
