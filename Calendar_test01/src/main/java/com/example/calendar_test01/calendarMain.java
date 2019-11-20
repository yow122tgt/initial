package com.example.calendar_test01;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class calendarMain extends AppCompatActivity {



    private View.OnClickListener btn_timepicker_1_Click =new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(calendarMain.this,timePicker_1.class);
            startActivity(intent);

        }
    };
    private View.OnClickListener btn_timepicker_2_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(calendarMain.this,timePicker_2.class);
            startActivity(intent);
        }
    };
    private View.OnClickListener btn_timepicker_3_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(calendarMain.this,timePicker_3.class);
            startActivity(intent);
        }
    };
    private View.OnClickListener btn_timepicker_4_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(calendarMain.this,timePicker_4.class);
            startActivity(intent);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        //InitialComponent();

        SharedPreferences table_1=getSharedPreferences("timePicker_1", Activity.MODE_PRIVATE);
        String data_1=table_1.getString("KEY_1","00:00");
        btn_timepicker_1.setText(data_1);

        SharedPreferences table_2=getSharedPreferences("timePicker_2", Activity.MODE_PRIVATE);
        String data_2=table_2.getString("KEY_2","00:00");
        btn_timepicker_2.setText(data_2);

        SharedPreferences table_3=getSharedPreferences("timePicker_3", Activity.MODE_PRIVATE);
        String data_3=table_3.getString("KEY_3","00:00");
        btn_timepicker_3.setText(data_3);

        SharedPreferences table_4=getSharedPreferences("timePicker_4", Activity.MODE_PRIVATE);
        String data_4=table_4.getString("KEY_4","00:00");
        btn_timepicker_4.setText(data_4);

    }

    private View.OnClickListener Left_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            compactCalendarView.scrollLeft();
        }
    };
    private View.OnClickListener Right_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            compactCalendarView.scrollRight();
        }
    };
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendarmain);
        InitialComponent();
        TimeStart();
    }



    private void InitialComponent() {

        //Toast.makeText(this, "onCreate", Toast.LENGTH_LONG).show();
Log.e("123","321");
        compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendarView.setLocale(TimeZone.getTimeZone("GMT 08:00"),Locale.CHINESE);
        compactCalendarView.setUseThreeLetterAbbreviation(true);
        compactCalendarView.displayOtherMonthDays(true);
        compactCalendarView.setSelected(true);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(dateFormatMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Log.e("123","456");
      //actionBar = getSupportActionBar();
        Log.e("123","4456");
     // actionBar.setDisplayHomeAsUpEnabled(true);
        Log.e("123","44456");
      //actionBar.setTitle(null);
      //toolbar.setTitle(dateFormatMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));
        Log.e("123","789");
        showPreviousMonthBut = (Button) findViewById(R.id.prev_button);
        showPreviousMonthBut.setOnClickListener(Left_Click);
        showNextMonthBut = (Button) findViewById(R.id.next_button);
        showNextMonthBut.setOnClickListener(Right_Click);





//---------------------------------------------------------演算

        try {
            Log.d("123","321");
            IS事件日期 = new Date_Event("2017/05/24" , 28 ,1 ,new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}).dayday;
            IS日期長串顯示 = new Date_Event("2017/05/24" ,  28 ,1,new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}).DEE;
            Log.d("123","3563211");
            IS確認吃藥 = new Date_Event("2017/05/24" ,  28 ,1,new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}).BB;
        } catch (Exception e) {
            e.printStackTrace();
        }
//-------------------------------------------------------在行事曆上顯示顏色

        for( day_1 =0;day_1<IS事件日期.length;day_1++) {
            date = new toUnix(IS事件日期[day_1]).epoch;

            if(IS確認吃藥[day_1]==1){
                Event ev1 = new Event(Color.GREEN, date, "已吃完藥");
                compactCalendarView.addEvent(ev1);
            }
            else{
                Event ev1 = new Event(Color.RED, date, "未吃完藥");
                compactCalendarView.addEvent(ev1);
            }

        }
//-----------------------------------------------------點擊有事件日期顯示Calendar_Button.class
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
               // Context context = getApplicationContext();
                for (day_2 = 0; day_2 < IS日期長串顯示.length;day_2++) {
                    if (dateClicked.toString().compareTo(IS日期長串顯示[day_2]) == 0) {
                        Intent intent = new Intent(calendarMain.this, Calendar_Button.class);
                        startActivity(intent);
                        day_3=day_2;
                    }
                }
            }
//----------------------------------------------------------------------------
            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                getSupportActionBar().setTitle(dateFormatMonth.format(firstDayOfNewMonth));

            }
        });
    }

//----------------------------------------------------------------------------
    private void TimeStart() {
        btn_timepicker_1 = (Button) findViewById(R.id.btn_timePicker_1);
        btn_timepicker_1.setOnClickListener(btn_timepicker_1_Click);
        btn_timepicker_2 = (Button) findViewById(R.id.btn_timePicker_2);
        btn_timepicker_2.setOnClickListener( btn_timepicker_2_Click );
        btn_timepicker_3 = (Button) findViewById(R.id.btn_timePicker_3);
        btn_timepicker_3.setOnClickListener( btn_timepicker_3_Click );
        btn_timepicker_4 = (Button) findViewById(R.id.btn_timePicker_4);
        btn_timepicker_4.setOnClickListener( btn_timepicker_4_Click );



    }


    private CompactCalendarView compactCalendarView;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMM- yyyy", Locale.CHINESE);
    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    public ActionBar actionBar;

    private Button showPreviousMonthBut ;
    private Button showNextMonthBut ;
    private String IS日期長串顯示[ ];
    private String IS事件日期[ ];
    public static int IS確認吃藥[ ];
    private long date;
    public static int day_1,day_2,day_3;


    public Button btn_timepicker_1, btn_timepicker_2, btn_timepicker_3, btn_timepicker_4;

    private NotificationManagerCompat notificationManager;

}
