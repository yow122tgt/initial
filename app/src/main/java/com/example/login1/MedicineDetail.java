package com.example.login1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MedicineDetail extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicine_detail);


        InitialiComponent();
        txt服藥天數.setText("28");
        txt用法用量.setText("口服 (每天2次)早晚飯後服用");
        txt藥品名稱.setText("RIVOTRIL 0.5MG 福利全");
        txt用途.setText("緩和焦慮相關症狀、控制癲癇發作");
        txt副作用.setText("疲倦、肌肉無力等等");
        detailTitle.setText("聖安健保");
        img藥袋外觀.setImageResource(R.drawable.mb4);

      
    }

    private void InitialiComponent() {
        txt服藥天數=findViewById(R.id.txt服藥天數);
        txt用法用量=findViewById(R.id.txt用法用量);
        txt藥品名稱=findViewById(R.id.txt藥品名稱)   ;
        txt用途=findViewById(R.id.txt用途);
        txt副作用=findViewById(R.id.txt副作用);
        img藥袋外觀=findViewById(R.id.img藥袋外觀);
        detailTitle=findViewById(R.id.detailTitle);

    }
    TextView txt服藥天數;
    TextView txt用法用量;
    TextView txt藥品名稱;
    TextView txt用途;
    TextView txt副作用;
    ImageView img藥袋外觀;
    TextView detailTitle;
}
