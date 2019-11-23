package medicineinfo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.login1.R;

public class MedicineDetail4 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicine_detail);


        InitialiComponent();
        txt服藥天數.setText("30");
        txt用法用量.setText("口服\n" +
                "每一天兩次，遵照醫師指示，" +
                "早飯後1粒，午飯後1粒");
        txt藥品名稱.setText("Parkryl(Selegiline)[5mg/tube]\n" +
                "中文名:巴可癒錠");
        txt用途.setText("巴金森氏症及其他相關症狀");
        txt副作用.setText("腸胃不適");
        detailTitle.setText("腦神經內科");
        img藥袋外觀.setImageResource(R.drawable.pa2);


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
