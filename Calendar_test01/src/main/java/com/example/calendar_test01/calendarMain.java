package com.example.calendar_test01;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static android.content.ContentValues.TAG;


public class calendarMain extends AppCompatActivity {
    private String showUri = "http://52.243.63.197/readMedicineDetailT.php";
    String result;
    com.android.volley.RequestQueue requestQueue;


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


        String data_1=table_1.getString("KEY_1","OFF");
        btn_timepicker_1.setText(data_1);


        String data_2=table_2.getString("KEY_2","OFF");
        btn_timepicker_2.setText(data_2);


        String data_3=table_3.getString("KEY_3","OFF");
        btn_timepicker_3.setText(data_3);


        String data_4=table_4.getString("KEY_4","OFF");
        btn_timepicker_4.setText(data_4);

        table_1=getSharedPreferences("timePicker_1", Activity.MODE_PRIVATE);
        int judge_1=table_1.getInt("jg_1",1);
        if(judge_1 == 0)
            btn_timepicker_1.setTextColor(Color.RED);
        else
            btn_timepicker_1.setTextColor(Color.BLACK);

        table_2=getSharedPreferences("timePicker_2", Activity.MODE_PRIVATE);
        int judge_2=table_2.getInt("jg_2",1);
        if(judge_2 == 0)
            btn_timepicker_2.setTextColor(Color.RED);
        else
            btn_timepicker_2.setTextColor(Color.BLACK);

        table_3=getSharedPreferences("timePicker_3", Activity.MODE_PRIVATE);
        int judge_3=table_3.getInt("jg_3",1);
        if(judge_3 == 0)
            btn_timepicker_3.setTextColor(Color.RED);
        else
            btn_timepicker_3.setTextColor(Color.BLACK);

        table_4=getSharedPreferences("timePicker_4", Activity.MODE_PRIVATE);
        int judge_4=table_4.getInt("jg_4",1);
        if(judge_4 == 0)
            btn_timepicker_4.setTextColor(Color.RED);
        else
            btn_timepicker_4.setTextColor(Color.BLACK);

        InitialComponent();
//------------------------------------------------------------------------------------------------
/*
        cal_catch_events =getSharedPreferences("btn_Events", Activity.MODE_PRIVATE);
        for(int d=0;d< Integer.parseInt(event[0][1]);d++)
        {
            endjudge = new int[Integer.parseInt(event[0][1])];
            endjudge[d]= cal_catch_events.getInt("jg"+String.valueOf(d),0);
        }
        Log.e("day2", String.valueOf(endjudge[0]));

        IS確認吃藥 = endjudge;
        Log.e("day21", String.valueOf(IS確認吃藥[0]));

        for( day_1 =0;day_1<IS事件日期.length;day_1++) {
            date = new toUnix(IS事件日期[day_1]).epoch;

            if(IS確認吃藥[day_1]==1){
                Log.e("day21", String.valueOf(IS確認吃藥[0]));
                Event ev1 = new Event(Color.GREEN, date, "已吃完藥");
                Log.e("789","6666666666");
                compactCalendarView.addEvent(ev1);
            }
            else{
                Event ev1 = new Event(Color.RED, date, "未吃完藥");
                Log.e("789","5555555555");
                compactCalendarView.addEvent(ev1);
            }

        }
*//*
        if(IS確認吃藥[day_3]==1){
            Event ev1 = new Event(Color.GREEN, date, "已吃完藥");
            Log.e("789","6666666666");
            compactCalendarView.addEvent(ev1);
        }
*/
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

        requestQueue = Volley.newRequestQueue(calendarMain.this);
        //testJSON();

        InitialComponent();
        TimeStart();
    }

    private void InitialComponent() {

        compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendarView.setLocale(TimeZone.getTimeZone("GMT 08:00"), Locale.CHINESE);
        compactCalendarView.setUseThreeLetterAbbreviation(true);
        compactCalendarView.displayOtherMonthDays(true);
        compactCalendarView.setSelected(true);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("    " + dateFormatMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        showPreviousMonthBut = (Button) findViewById(R.id.prev_button);
        showPreviousMonthBut.setOnClickListener(Left_Click);
        showNextMonthBut = (Button) findViewById(R.id.next_button);
        showNextMonthBut.setOnClickListener(Right_Click);


//---------------------------------------------------------鈴聲
        media_song = MediaPlayer.create(calendarMain.this, R.raw.alarm);
        try {
            if (media_song.isPlaying()) {
                media_song.stop();
            }
        } catch (IllegalStateException e) {
            Log.e(TAG, "stopOnlineMedia error=" + e.getMessage());
        }

//---------------------------------------------------------對話盒

        altDlgBuilder = new AlertDialog.Builder(calendarMain.this);
        altDlgBuilder.setTitle("確認是否吃藥");
        altDlgBuilder.setMessage("今日是否已吃完藥了??");
        altDlgBuilder.setIcon(android.R.drawable.ic_dialog_info);
        altDlgBuilder.setCancelable(false);

        altDlgBuilder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                judge[day_3] = 1;
                Log.e("abc", "52555");
                btn_to_events = getSharedPreferences("btn_Events", Activity.MODE_PRIVATE);
                for (int d = 0; d < Integer.parseInt(event[2][1]); d++) {
                    String JG = "jg" + String.valueOf(d);
                    if (judge[d] == 1) {
                        btn_to_events.edit().putInt(JG, 1).commit();
                    } else
                        btn_to_events.edit().putInt(JG, 0).commit();
                }
                InitialComponent();
            }

        });

        altDlgBuilder.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                judge[day_3] = 0;
                Log.e("abc", "52555");
                btn_to_events = getSharedPreferences("btn_Events", Activity.MODE_PRIVATE);
                for (int d = 0; d < Integer.parseInt(event[2][1]); d++) {
                    String JG = "jg" + String.valueOf(d);
                    if (judge[d] == 1) {
                        btn_to_events.edit().putInt(JG, 1).commit();
                    } else
                        btn_to_events.edit().putInt(JG, 0).commit();
                }
                InitialComponent();

            }
        });

        altDlgBuilder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });



//-----------------------------------------------------------演算
        event = new String[3][3];
        event[0][0] = "2019-9-15 00:00:00";
        event[0][1] = "5";
        event[0][2] = "3";
         event[1][0] = "2019-10-10 00:00:00";
        event[1][1] = "10";
        event[1][2] = "2";
        event[2][0] = "2019-11-10 00:00:00";
        event[2][1] = "22";
        event[2][2] = "1";

         for (int i = 0; i < 3; i++) {

        cal_catch_events = getSharedPreferences("btn_Events", Activity.MODE_PRIVATE);
        endjudge = new int[Integer.parseInt(event[i][1])];

        for (int d = 0; d < Integer.parseInt(event[i][1]); d++) {
            String JG = "jg" + String.valueOf(d);
            endjudge[d] = cal_catch_events.getInt(JG, 0);
        }

        cal_to_events = getSharedPreferences("cal_Events", Activity.MODE_PRIVATE);
        SharedPreferences.Editor row = cal_to_events.edit();
        for (int d = 0; d < Integer.parseInt(event[i][1]); d++) {
            String AD = "ad" + String.valueOf(d);
            row.putInt(AD, endjudge[d]).commit();
        }


        try {
            IS事件日期 = new Date_Event(event[i][0], Integer.parseInt(event[i][1]), Integer.parseInt(event[i][2]) /*,dd*/).dayday;
            IS日期長串顯示 = new Date_Event(event[2][0], Integer.parseInt(event[2][1]), Integer.parseInt(event[2][2]) /*,dd*/).DEE;
            IS確認吃藥 = new int[Integer.parseInt(event[i][1])];
            IS確認吃藥 = endjudge;
        } catch (Exception e) {
            e.printStackTrace();
        }

//-------------------------------------------------------在行事曆上顯示顏色

        for (day_1 = 0; day_1 < IS事件日期.length; day_1++) {
            date = new toUnix(IS事件日期[day_1]).epoch;
            if (endjudge[day_1] == 1) {
                Event ev1 = new Event(Color.GREEN, date, "已吃完藥");
                compactCalendarView.addEvent(ev1);
            } else {
                Event ev1 = new Event(Color.RED, date, "未吃完藥");
                compactCalendarView.addEvent(ev1);
            }

        }
             //-----------
    }
//-----------------------------------------------------點擊有事件日期顯示Calendar_Button.class
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
               // Context context = getApplicationContext();
                for (day_2 = 0; day_2 < IS日期長串顯示.length;day_2++) {
                    if (dateClicked.toString().compareTo(IS日期長串顯示[day_2]) == 0) {
                        btn_catch_events =getSharedPreferences("cal_Events", Activity.MODE_PRIVATE);
                            judge = new int[Integer.parseInt(event[2][1])];
                            for (int d = 0; d < Integer.parseInt(event[2][1]); d++) {
                                String AD = "ad" + String.valueOf(d);
                                judge[d] = btn_catch_events.getInt(AD, 0);
                            }
                        altDlgBuilder.show();
                        //Intent intent = new Intent(calendarMain.this, Calendar_Button.class);
                        //startActivity(intent);
                        day_3=day_2;
                    }
                }
            }
//----------------------------------------------------------------------------
            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                getSupportActionBar().setTitle("    "+dateFormatMonth.format(firstDayOfNewMonth));

            }
        });
    }

//----------------------------------------------------------------------------
    public void TimeStart() {
        btn_timepicker_1 = (Button) findViewById(R.id.btn_timePicker_1);
        btn_timepicker_1.setOnClickListener(btn_timepicker_1_Click);
        btn_timepicker_2 = (Button) findViewById(R.id.btn_timePicker_2);
        btn_timepicker_2.setOnClickListener( btn_timepicker_2_Click );
        btn_timepicker_3 = (Button) findViewById(R.id.btn_timePicker_3);
        btn_timepicker_3.setOnClickListener( btn_timepicker_3_Click );
        btn_timepicker_4 = (Button) findViewById(R.id.btn_timePicker_4);
        btn_timepicker_4.setOnClickListener( btn_timepicker_4_Click );

        table_1=getSharedPreferences("timePicker_1", Activity.MODE_PRIVATE);
        int judge_1=table_1.getInt("jg_1",1);
        if(judge_1 == 0)
            btn_timepicker_1.setTextColor(Color.RED);
        else
            btn_timepicker_1.setTextColor(Color.BLACK);

        table_2=getSharedPreferences("timePicker_2", Activity.MODE_PRIVATE);
        int judge_2=table_2.getInt("jg_2",1);
        if(judge_2 == 0)
            btn_timepicker_2.setTextColor(Color.RED);
        else
            btn_timepicker_2.setTextColor(Color.BLACK);

        table_3=getSharedPreferences("timePicker_3", Activity.MODE_PRIVATE);
        int judge_3=table_3.getInt("jg_3",1);
        if(judge_3 == 0)
            btn_timepicker_3.setTextColor(Color.RED);
        else
            btn_timepicker_3.setTextColor(Color.BLACK);

        table_4=getSharedPreferences("timePicker_4", Activity.MODE_PRIVATE);
        int judge_4=table_4.getInt("jg_4",1);
        if(judge_4 == 0)
            btn_timepicker_4.setTextColor(Color.RED);
        else
            btn_timepicker_4.setTextColor(Color.BLACK);

    }

//-------------------------------------------------------------------------------------------------------------------------
    /*public void testJSON() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, showUri, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        event = new String[1][3];
                        try {
                            JSONArray data = response.getJSONArray("data");
                            for (int i = 0; i < 1; i++) {
                                JSONObject jasondata = data.getJSONObject(i);
                               // String MB_ID = "藥袋_ID:" + jasondata.getString("MB_ID") + "\r\n";
                               // String MB_days = "藥袋_天數:" + jasondata.getString("MB_days") + "\r\n";
                               // String MB_date = "藥袋_日期:" + jasondata.getString("MB_date") + "\r\n";
                               // result +=  MB_days + MB_date;
                                // TV.setText(result);
                                event[i][0] = jasondata.getString("MB_date");
                                event[i][1] = jasondata.getString("MB_days");
                                event[i][2] ="1";
                               btn_timepicker_1.setText(event[i][0]);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("de","nonResponse");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.append(error.getMessage());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }*/

//------------------------------------------------------------------------------------------------------


    //------------------------------------------------------------------
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
    public SharedPreferences table_1,table_2,table_3,table_4;
    public int confirm_day[];
    public Button btn_timepicker_1, btn_timepicker_2, btn_timepicker_3, btn_timepicker_4;

    public SharedPreferences cal_to_events;
    public SharedPreferences cal_catch_events;
    public static String event [][];
    public int endjudge[];

    private NotificationManagerCompat notificationManager;
    public static MediaPlayer media_song;

    public SharedPreferences btn_catch_events;
    public SharedPreferences btn_to_events;
    public int judge[];
    //public AlertDialog.Builder altDlgBuilder;
    public AlertDialog.Builder altDlgBuilder;

}
