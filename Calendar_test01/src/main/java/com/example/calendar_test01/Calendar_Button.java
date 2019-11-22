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
/*
        events=getSharedPreferences("Events", Activity.MODE_PRIVATE);
        int judge_2=events.getInt("jg_2",1);
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

            }
        });

    }
    public SharedPreferences events;

}
