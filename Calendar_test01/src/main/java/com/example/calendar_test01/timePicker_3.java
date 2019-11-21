package com.example.calendar_test01;



import androidx.annotation.RequiresApi;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import static android.content.ContentValues.TAG;

public class timePicker_3 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker_3);
        initialComponent();
    }

    private void initialComponent() {

        this.context = this;
        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarm_timepicker = (TimePicker) findViewById(R.id.timePicker);
        update_text = (TextView) findViewById(R.id.update_text);
        calendar = Calendar.getInstance();
        my_intent = new Intent(this.context, Alarm_Receiver_3.class);
        Button alarm_on = (Button) findViewById(R.id.alarm_on);
        Button alarm_off = (Button) findViewById(R.id.alarm_off);
        alarm_on.setOnClickListener(alarm_on_Click);
        alarm_off.setOnClickListener(alarm_off_Click);

        SharedPreferences table=getSharedPreferences("timePicker_3", Activity.MODE_PRIVATE);
        data_3 =table.getString("KEY_3","00:00");
        set_alarm_text("Alarm set to :"+ data_3 );

    }
    public View.OnClickListener alarm_on_Click =new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onClick(View v) {
            calendar.set(Calendar.HOUR_OF_DAY,alarm_timepicker.getHour());
            calendar.set(Calendar.MINUTE,alarm_timepicker.getMinute());

            int hour = alarm_timepicker.getHour();
            int minute = alarm_timepicker.getMinute();

            String hour_string = String.valueOf(hour);
            String minute_string = String.valueOf(minute);
            if (hour >12){
                hour_string = String.valueOf(hour-12);
            }

            if(minute <10){
                minute_string = "0" + String.valueOf(minute);
            }

            SharedPreferences table_3=getSharedPreferences("timePicker_3", Activity.MODE_PRIVATE);
            SharedPreferences.Editor row=table_3.edit();
            row.putString("KEY_3",hour_string + ":" + minute_string).commit();



            set_alarm_text("Alarm set to :"+hour_string + ":" + minute_string);



            bund.putString("extra","alarm on");
            my_intent.putExtras( bund);
//--------------------------------------------------------------------------------------------------------------

            setAlarm();
//-----------------------------------------------------------------------

        }
    };

    private void setAlarm() {
        alarm_manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        pending_intent = PendingIntent.getBroadcast(timePicker_3.this,0, my_intent,0);

        alarm_manager.setRepeating(AlarmManager.RTC,calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pending_intent);

        //sendBroadcast(my_intent);
    }

    public View.OnClickListener alarm_off_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            set_alarm_text("Alarm off!");

            bund.putString("extra","alarm off");
            my_intent.putExtras(bund);

            pending_intent = PendingIntent.getBroadcast(timePicker_3.this, 0, my_intent, PendingIntent.FLAG_NO_CREATE);
            if (pending_intent != null){
                Log.i("lily","cancel alarm");
                alarm_manager.cancel(pending_intent);
                sendBroadcast(my_intent);
                Log.e("55555","8888888");

                try {
                    if (media_song.isPlaying()) {
                        media_song.stop();
                    }
                    media_song.release();
                } catch (IllegalStateException e) {
                    Log.e(TAG, "stopOnlineMedia error=" + e.getMessage());
                }


            }else{
                Log.i("lily","sender == null");
            }


            //   alarm_manager.cancel(pending_intent);

        }
    };

    public void set_alarm_text(String output) {
        update_text.setText(output);
    }

    public Calendar calendar;
    public Intent my_intent;
    public String data_3;
    public AlarmManager alarm_manager;
    public TimePicker alarm_timepicker;
    public TextView update_text;
    public Context context;
    public PendingIntent pending_intent;
    private static timePicker_3 inst;
    public static MediaPlayer media_song;
    public Bundle bund = new Bundle();
    android.widget.TimePicker timePicker;
}
