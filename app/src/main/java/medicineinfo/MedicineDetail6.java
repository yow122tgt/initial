package medicineinfo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.login1.R;

public class MedicineDetail6 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicine_detail);


        InitialiComponent();
        txt服藥天數.setText("7");
        txt用法用量.setText("口服 每次1粒 每天3次 飯後");
        txt藥品名稱.setText("0xethazine 5mg&polymigel 224mg");
        txt用途.setText("胃炎、食道炎、及伴隨之胃病等胃部不適相關症狀");
        txt副作用.setText("粉筆味");
        detailTitle.setText("消化內科");
        img藥袋外觀.setImageResource(R.drawable.mb5);


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
