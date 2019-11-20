package medicineinfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

import com.example.login1.MedicineDetail;
import com.example.login1.MenuActivity;
import com.example.login1.R;
import com.jd3z.userinfo.userinfo;

public class MedicineInfoAct extends Activity {

    private View.OnClickListener btnM首頁_click=new  View.OnClickListener(){
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MedicineInfoAct.this, MenuActivity.class));

        }
    };
    private View.OnClickListener btnM藥單_click=new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MedicineInfoAct.this, MedicineInfoAct.class));
        }
    };
    private View.OnClickListener btnM會員_click=new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            startActivity(new Intent (MedicineInfoAct.this, userinfo.class));
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


    //--------------------------------------------------------------------------------------
    private View.OnClickListener btnx_click=new View.OnClickListener(){
        @Override
        public void onClick(View v) {

        }
    };
    private View.OnClickListener btnx2_click =new View.OnClickListener(){
        @Override
        public void onClick(View v) {

        }
    };
    private View.OnClickListener btnx3_click=new View.OnClickListener(){
        @Override
        public void onClick(View v) {

        }
    };
    private View.OnClickListener btnx4_click=new View.OnClickListener(){
        @Override
        public void onClick(View v) {

        }
    };
    private View.OnClickListener btnx5_click=new View.OnClickListener(){
        @Override
        public void onClick(View v) {

        }
    };
    private View.OnClickListener btnx6_click=new View.OnClickListener(){
        @Override
        public void onClick(View v) {

        }
    };
    //----------------------------------------------------------------------
    private View.OnClickListener btnInfo_click=new View.OnClickListener(){
        @Override
        public void onClick(View v) {

            startActivity(new Intent(MedicineInfoAct.this, MedicineDetail.class));

        }
    };
    private View.OnClickListener btnInfo2_click=new View.OnClickListener(){
        @Override
        public void onClick(View v) {

        }
    };
    private View.OnClickListener btnInfo3_click=new View.OnClickListener(){
        @Override
        public void onClick(View v) {

        }
    };
    private View.OnClickListener btnInfo4_click=new View.OnClickListener(){
        @Override
        public void onClick(View v) {

        }
    };
    private View.OnClickListener btnInfo5_click=new View.OnClickListener(){
        @Override
        public void onClick(View v) {

        }
    };
    private View.OnClickListener btnInfo6_click=new View.OnClickListener(){
        @Override
        public void onClick(View v) {

        }
    };
    private View.OnClickListener btnplus_click=new View.OnClickListener(){
        @Override
        public void onClick(View v) {

        }
    };
    private View.OnClickListener btnplus2_click=new View.OnClickListener(){
        @Override
        public void onClick(View v) {

        }
    };
    private View.OnClickListener btnplus3_click=new View.OnClickListener(){
        @Override
        public void onClick(View v) {

        }
    };
    private View.OnClickListener btnplus4_click=new View.OnClickListener(){
        @Override
        public void onClick(View v) {

        }
    };
    private View.OnClickListener btnplus5_click=new View.OnClickListener(){
        @Override
        public void onClick(View v) {

        }
    };
    private View.OnClickListener btnplus6_click=new View.OnClickListener(){
        @Override
        public void onClick(View v) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        InitialiComponent();
        MenuComponent();

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


    private void InitialiComponent() {
        btnx=findViewById(R.id.btnx);
        btnx2=findViewById(R.id.btnx2);
        btnx3=findViewById(R.id.btnx3);
        btnx4=findViewById(R.id.btnx4);
        btnx5=findViewById(R.id.btnx5);
        btnx6=findViewById(R.id.btnx6);
        btnx.setOnClickListener(btnx_click);
        btnx2.setOnClickListener(btnx2_click);
        btnx3.setOnClickListener(btnx3_click);
        btnx4.setOnClickListener(btnx4_click);
        btnx5.setOnClickListener(btnx5_click);
        btnx6.setOnClickListener(btnx6_click);


        btnInfo=findViewById(R.id.btnInfo);
        btnInfo2=findViewById(R.id.btnInfo2);
        btnInfo3=findViewById(R.id.btnInfo3);
        btnInfo4=findViewById(R.id.btnInfo4);
        btnInfo5=findViewById(R.id.btnInfo5);
        btnInfo6=findViewById(R.id.btnInfo6);
        btnInfo.setOnClickListener(btnInfo_click);
        btnInfo2.setOnClickListener(btnInfo2_click);
        btnInfo3.setOnClickListener(btnInfo3_click);
        btnInfo4.setOnClickListener(btnInfo4_click);
        btnInfo5.setOnClickListener(btnInfo5_click);
        btnInfo6.setOnClickListener(btnInfo6_click);

        btnplus=findViewById(R.id.btnplus);
        btnplus2=findViewById(R.id.btnplus2);
        btnplus3=findViewById(R.id.btnplus3);
        btnplus4=findViewById(R.id.btnplus4);
        btnplus5=findViewById(R.id.btnplus5);
        btnplus6=findViewById(R.id.btnplus6);

        btnplus.setOnClickListener(btnplus_click);
        btnplus2.setOnClickListener(btnplus2_click);
        btnplus3.setOnClickListener(btnplus3_click);
        btnplus4.setOnClickListener(btnplus4_click);
        btnplus5.setOnClickListener(btnplus5_click);
        btnplus6.setOnClickListener(btnplus6_click);
    }

    ImageButton btnx;
    ImageButton btnx2;
    ImageButton btnx3;
    ImageButton btnx4;
    ImageButton btnx5;
    ImageButton btnx6;
    Button btnInfo;
    Button btnInfo2;
    Button btnInfo3;
    Button btnInfo4;
    Button btnInfo5;
    Button btnInfo6;
    ImageButton btnplus;
    ImageButton btnplus2;
    ImageButton btnplus3;
    ImageButton btnplus4;
    ImageButton btnplus5;
    ImageButton btnplus6;




    Button btnM首頁;
    Button btnM藥單;
    Button btnM行事曆;
    Button btnM會員;
    Button btnMNews;


}
