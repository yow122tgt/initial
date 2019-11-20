package com.example.calendar_test01;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class RingtonePlayingService_3 extends Service {

    MediaPlayer media_song;
    public int startId;
    boolean isRunning;
    private Context context;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @SuppressLint("LongLogTag")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        String state = intent.getExtras().getString("extra");

        Log.e("Ringtone state:extra is ",state);


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
        if(!this.isRunning && startId ==1){
            Log.e("there is no music, " ,"and you want start");
            //create an instance of the media player
            media_song = MediaPlayer.create(this,R.raw.alarm);
            //start the ringtone
            media_song.start();

//----------------------------------------------------------------------提示
            NotificationChannel channel = new NotificationChannel(
                    "myid",
                    "Chcnel Name",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.enableVibration(true);//震动
            channel.enableLights(true);//提示灯

            Intent intent_main_activity = new Intent(this, timePicker_3.class);
            intent_main_activity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//
            //set up a pending intent
            PendingIntent pending_intent_main_activity = PendingIntent.getActivity(this, 0,
                    intent_main_activity, 0);


            Notification message = new Notification.Builder(this)
                    .setContentTitle("您有三封簡訊未讀")
                    .setContentText("訊息來自 Marco")
                    .setContentIntent(pending_intent_main_activity)
                    .setAutoCancel(true)
                    .setSmallIcon(R.drawable.cellphone )
                    .setChannelId("myid")
                    .build();

            NotificationManager manager =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);
            manager.notify(0, message);

            this.isRunning = true;
            this.startId = 0;
            //this converts the extra strings from the intent
            //to start IDs, values 0 or 1

//------------------------------------------------------------------------------------
        }

        //if there is music playing, and the user pressed "alarm off"
        //music should stop playing
        else if (this.isRunning && startId ==0){
            Log.e("there is  music, " ,"and you want end");

            //stop the ringtone
            media_song.stop();
            media_song.reset();

            this.isRunning = false;
            this.startId =0;
        }

        //there are if the user presses random buttons
        //just to bug-proof the app
        //if there is no music playing, and the user pressed "alarm off"
        //do nothing
        else if (!this.isRunning && startId ==0){
            Log.e("there is no music, " ,"and you want end");

            this.isRunning = false;
            this.startId =0;


        }

        //if there is music playing and the user pressed "alarm on"
        //do nothing
        else if (this.isRunning && startId ==1){
            Log.e("there is  music, " ,"and you want start");

            this.isRunning = true;
            this.startId =1;


        }

        //can't think of anything else, just to catch the odd event
        else {
            Log.e("else " ,"somehow you reached this");
        }


        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {

      Log.e("on Destroy called","ta da");

      super.onDestroy();
      this.isRunning =false;
    }





}
