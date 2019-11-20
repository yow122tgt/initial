package com.example.calendar_test01;



import androidx.annotation.RequiresApi;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

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

        SharedPreferences table_3=getSharedPreferences("timePicker_3", Activity.MODE_PRIVATE);
        data_3 =table_3.getString("KEY_3","00:00");
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


            my_intent.putExtra("extra","alarm on");

            pending_intent = PendingIntent.getBroadcast(timePicker_3.this,0,
                    my_intent,PendingIntent.FLAG_UPDATE_CURRENT);

            alarm_manager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),
                    pending_intent);
        }
    };
    public View.OnClickListener alarm_off_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            set_alarm_text("Alarm off!");
//            alarm_manager.cancel(pending_intent);
            my_intent.putExtra("extra","alarm off");
            sendBroadcast(my_intent);
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
}
