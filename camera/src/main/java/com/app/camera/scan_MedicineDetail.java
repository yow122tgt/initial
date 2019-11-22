package com.app.camera;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;

public class scan_MedicineDetail extends Activity {
    private RequestQueue mQueue;
    private final static String mUrl = "http://192.168.56.1/get_json.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_medicine_detail);

        MedicineDetailComponent();
    }

    private void MedicineDetailComponent() {
        detailTitle=findViewById(R.id.detailTitle);
        txt調劑日期=findViewById(R.id.txt調劑日期);
        txt用法用量=findViewById(R.id.txt用法用量);
        txt用途=findViewById(R.id.txt用途);
        txt副作用=findViewById(R.id.txt副作用);
        txt藥品外觀=findViewById(R.id.txt藥品外觀);
        img藥袋外觀=findViewById(R.id.img藥袋外觀);
        txt藥品名稱=findViewById(R.id.txt藥品名稱);
    }
    TextView detailTitle;
    TextView txt調劑日期;
    TextView txt用法用量;
    TextView txt藥品名稱;
    TextView txt用途;
    TextView txt副作用;
    TextView txt藥品外觀;
    ImageView img藥袋外觀;
}