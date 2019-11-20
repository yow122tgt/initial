package com.jd3z.userinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.login1.MenuActivity;
import com.example.login1.R;

import medicineinfo.MedicineInfoAct;

public class userinfo extends Activity {


    private View.OnClickListener btnM首頁_click=new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            startActivity(new Intent(userinfo.this, MenuActivity.class));
        }
    };
    private View.OnClickListener btnM藥單_click=new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            startActivity(new Intent(userinfo.this, MedicineInfoAct.class));
        }
    };
    private View.OnClickListener btnM會員_click=new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            startActivity(new Intent (userinfo.this, userinfo.class));
        }
    };
    private View.OnClickListener btnMNews_click=new View.OnClickListener(){
        @Override
        public void onClick(View v) {

        }
    };
    private View.OnClickListener btnM行事曆_click=new View.OnClickListener(){
        @Override
        public void onClick(View v) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        MenuComponent();
        InitialiComponent();
    }

    private void InitialiComponent() {
    }

    private void MenuComponent() {

        btnM首頁=findViewById(R.id.btnM首頁);
        btnM藥單=findViewById(R.id.btnM藥單);
        btnM行事曆=findViewById(R.id.btnM行事曆);
        btnM會員=findViewById(R.id.btnM會員);
        btnMNews=findViewById(R.id.btnMNews);

        btnM首頁.setOnClickListener(btnM首頁_click);
        btnM藥單.setOnClickListener(btnM藥單_click);
        btnM會員.setOnClickListener(btnM會員_click);
        btnMNews.setOnClickListener(btnMNews_click);
        btnM行事曆.setOnClickListener(btnM行事曆_click);


    }
    Button btnM首頁;
    Button btnM藥單;
    Button btnM行事曆;
    Button btnM會員;
    Button btnMNews;
}
