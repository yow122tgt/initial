package medicineinfo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableRow;

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
            startActivity(new Intent (MedicineInfoAct.this, com.example.calendar_test01.calendarMain.class));
        }
    };


    //--------------------------------------------------------------------------------------
    private View.OnClickListener btnx_click=new View.OnClickListener(){


        private DialogInterface.OnClickListener btnNO_click=new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        };

        private DialogInterface.OnClickListener btnOk_click=new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                btnx.setVisibility(View.GONE);
                btnInfo.setVisibility(View.GONE);
                btnplus.setVisibility(View.GONE);
                btnInfo.setText("尚無藥單資料");

            }
        };

        @Override
        public void onClick(View v) {

            AlertDialog.Builder x = new AlertDialog.Builder(MedicineInfoAct.this);
            x.setTitle("是否刪除藥單");
            x.setMessage("刪除後的藥單資料可以在會員資訊中查看");
            x.setPositiveButton("刪除",btnOk_click);
            x.setNegativeButton("取消",btnNO_click);

            Dialog message = x.create();
            message.show();


        }
    };
    private View.OnClickListener btnx2_click =new View.OnClickListener(){
        private DialogInterface.OnClickListener btnNO_click=new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        };
        private DialogInterface.OnClickListener btnOk_click=new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {

                btnx2.setVisibility(View.GONE);
                btnInfo2.setVisibility(View.GONE);
                btnplus2.setVisibility(View.GONE);
            }
        };
        @Override
        public void onClick(View v) {
            AlertDialog.Builder x = new AlertDialog.Builder(MedicineInfoAct.this);
            x.setTitle("是否刪除藥單");
            x.setMessage("刪除後的藥單資料可以在會員資訊中查看");
            x.setPositiveButton("刪除",btnOk_click);
            x.setNegativeButton("取消",btnNO_click);

            Dialog message = x.create();
            message.show();

        }
    };
    private View.OnClickListener btnx3_click=new View.OnClickListener(){
        private DialogInterface.OnClickListener btnNO_click=new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        };
        private DialogInterface.OnClickListener btnOk_click=new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {

                btnx3.setVisibility(View.GONE);
                btnInfo3.setVisibility(View.GONE);
                btnplus3.setVisibility(View.GONE);
            }
        };
        @Override
        public void onClick(View v) {


            AlertDialog.Builder x = new AlertDialog.Builder(MedicineInfoAct.this);
            x.setTitle("是否刪除藥單");
            x.setMessage("刪除後的藥單資料可以在會員資訊中查看");
            x.setPositiveButton("刪除",btnOk_click);
            x.setNegativeButton("取消",btnNO_click);

            Dialog message = x.create();
            message.show();
        }
    };
    private View.OnClickListener btnx4_click=new View.OnClickListener(){
        private DialogInterface.OnClickListener btnNO_click=new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        };
        private DialogInterface.OnClickListener btnOk_click=new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {

                btnx4.setVisibility(View.GONE);
                btnInfo4.setVisibility(View.GONE);
                btnplus4.setVisibility(View.GONE);
            }
        };

        @Override
        public void onClick(View v) {



            AlertDialog.Builder x = new AlertDialog.Builder(MedicineInfoAct.this);
            x.setTitle("是否刪除藥單");
            x.setMessage("刪除後的藥單資料可以在會員資訊中查看");
            x.setPositiveButton("刪除",btnOk_click);
            x.setNegativeButton("取消",btnNO_click);

            Dialog message = x.create();
            message.show();


        }
    };
    private View.OnClickListener btnx5_click=new View.OnClickListener(){
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
          startActivity(new Intent(MedicineInfoAct.this,MedicineDetail2.class));
        }
    };
    private View.OnClickListener btnInfo3_click=new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MedicineInfoAct.this,MedicineDetail3.class));
        }
    };
    private View.OnClickListener btnInfo4_click=new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MedicineInfoAct.this,MedicineDetail4.class));
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
        btnInfo.setText("聖安健保");
        btnInfo2.setText("拉傷");
        btnInfo3.setText("高血壓");
        btnInfo4.setText("巴金森氏症");

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

        btnx.setOnClickListener(btnx_click);
        btnx2.setOnClickListener(btnx2_click);
        btnx3.setOnClickListener(btnx3_click);
        btnx4.setOnClickListener(btnx4_click);
        btnx5.setOnClickListener(btnx5_click);



        btnInfo=findViewById(R.id.btnInfo);
        btnInfo2=findViewById(R.id.btnInfo2);
        btnInfo3=findViewById(R.id.btnInfo3);
        btnInfo4=findViewById(R.id.btnInfo4);
        btnInfo5=findViewById(R.id.btnInfo5);

        btnInfo.setOnClickListener(btnInfo_click);
        btnInfo2.setOnClickListener(btnInfo2_click);
        btnInfo3.setOnClickListener(btnInfo3_click);
        btnInfo4.setOnClickListener(btnInfo4_click);
        btnInfo5.setOnClickListener(btnInfo5_click);


        btnplus=findViewById(R.id.btnplus);
        btnplus2=findViewById(R.id.btnplus2);
        btnplus3=findViewById(R.id.btnplus3);
        btnplus4=findViewById(R.id.btnplus4);
        btnplus5=findViewById(R.id.btnplus5);


        btnplus.setOnClickListener(btnplus_click);
        btnplus2.setOnClickListener(btnplus2_click);
        btnplus3.setOnClickListener(btnplus3_click);
        btnplus4.setOnClickListener(btnplus4_click);
        btnplus5.setOnClickListener(btnplus5_click);

    }

    ImageButton btnx;
    ImageButton btnx2;
    ImageButton btnx3;
    ImageButton btnx4;
    ImageButton btnx5;

    Button btnInfo;
    Button btnInfo2;
    Button btnInfo3;
    Button btnInfo4;
    Button btnInfo5;

    ImageButton btnplus;
    ImageButton btnplus2;
    ImageButton btnplus3;
    ImageButton btnplus4;
    ImageButton btnplus5;




    Button btnM首頁;
    Button btnM藥單;
    Button btnM行事曆;
    Button btnM會員;
    Button btnMNews;


}
