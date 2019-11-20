package com.example.login1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.jd3z.userinfo.userinfo;

import medicineinfo.MedicineInfoAct;


public class MenuActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnOut=findViewById(R.id.btnOut);

        InitialiComponent();
        final SharedPreferences sharedPreferences = getSharedPreferences("UserInfo",MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");

        btnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(getResources().getString(R.string.prefLoginstate),"登出");
                editor.apply();
                startActivity(new Intent(MenuActivity.this,MainActivity.class));
                finish();

            }
        });

    }


    private void InitialiComponent() {
        btnNews=findViewById(R.id.btnNews);
        btnNews.setOnClickListener(btnNews_click);
        btn藥袋掃描=findViewById(R.id.btn藥袋掃描);
        btn藥袋掃描.setOnClickListener(btn藥袋掃描_click);
        btn我的資訊=findViewById(R.id.btn我的資訊);
        btn我的資訊.setOnClickListener(btn我的資訊_click);
        btn藥單資料=findViewById(R.id.btn藥單資料);
        btn藥單資料.setOnClickListener(btn藥單資料_click);
        btn服藥行事曆=findViewById(R.id.btn服藥行事曆);
        btn服藥行事曆.setOnClickListener(btn服藥行事曆_click);



    }




    private View.OnClickListener btnNews_click =new View.OnClickListener(){
        @Override
        public void onClick(View v) {

        }
    };
    private View.OnClickListener btn藥袋掃描_click =new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            startActivity(new Intent (MenuActivity.this, com.app.camera.scan1.class));
        }
    };
    private View.OnClickListener btn我的資訊_click =new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            startActivity(new Intent (MenuActivity.this, userinfo.class));
        }
    };
    private View.OnClickListener btn藥單資料_click =new View.OnClickListener(){
        @Override
        public void onClick(View v) {

            startActivity(new Intent (MenuActivity.this, MedicineInfoAct.class));


        }
    };
    private View.OnClickListener btn服藥行事曆_click =new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            startActivity(new Intent (MenuActivity.this, com.example.calendar_test01.calendarMain.class));
        }
    };
    ImageButton btn我的資訊;
    ImageButton btn藥袋掃描;
    ImageButton btn藥單資料;
    ImageButton btnNews;
    ImageButton btn服藥行事曆;
    Button btnOut;

}
