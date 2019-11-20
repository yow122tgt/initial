package com.example.calendar_test01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;


import androidx.annotation.RequiresApi;

public class Alarm_Receiver_1 extends BroadcastReceiver {


    //public static String get_your_string;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("We are in the receiver","Yay!");

        String get_your_string = intent.getExtras().getString("extra");
        Log.e("What is the key?",get_your_string);


        Intent service_intent = new Intent(context, RingtonePlayingService_1.class);

        Bundle bund = new Bundle();
        bund.putString("extra01",get_your_string);
        service_intent.putExtras(bund);

        Log.e("What is the key?",get_your_string);



        //context.startService(service_intent);
      //  context.startService(service_intent);
        context.startService(service_intent);
}



}
