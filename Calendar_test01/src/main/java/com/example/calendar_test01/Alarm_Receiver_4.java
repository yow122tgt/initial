package com.example.calendar_test01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Alarm_Receiver_4 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("We are in the receiver","Yay!");

        String get_your_string = intent.getExtras().getString("extra");

        Log.e("What is the key?",get_your_string);

        Intent service_intent = new Intent(context, RingtonePlayingService_4.class);

        service_intent.putExtra("extra",get_your_string);

        context.startService(service_intent);
    }

}
