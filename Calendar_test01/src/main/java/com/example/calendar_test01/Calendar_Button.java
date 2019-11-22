package com.example.calendar_test01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import static com.example.calendar_test01.calendarMain.*;
import static com.example.calendar_test01.calendarMain.day_3;


public class Calendar_Button extends AppCompatActivity {

    private Button Btn_1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar__button);

        btn_catch_events =getSharedPreferences("cal_Events", Activity.MODE_PRIVATE);
        for(int d=0;d< Integer.parseInt(event[0][1]);d++)
        {
            judge = new int[Integer.parseInt(event[0][1])];
            judge[d]= btn_catch_events.getInt("ad"+d,0);
        }


/*
        SharedPreferences.Editor row=events.edit();
        row.putInt("ad"+day_3,1).commit();
*/

        Btn_1 = (Button) findViewById(R.id.btn);

        if(IS確認吃藥[day_3]==1){
           Btn_1.setBackgroundColor(Color.YELLOW);
        }

        Btn_1.setOnClickListener (new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                Btn_1.setBackgroundColor(Color.YELLOW);
                calendarMain.IS確認吃藥[day_3] = 1;
                judge[day_3] =1;

                btn_to_events=getSharedPreferences("btn_Events", Activity.MODE_PRIVATE);
                SharedPreferences.Editor row=btn_to_events.edit();
                for(int d=0;d< Integer.parseInt(event[0][1]);d++)
                {
                    row.putInt("jg"+d,judge[d]).commit();
                }

            }
        });

    }
    public SharedPreferences btn_catch_events;
    public SharedPreferences btn_to_events;
    public int judge[];

}
