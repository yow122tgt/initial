package com.example.login1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class confirmpassword extends AppCompatActivity {

    private Button confirm;
    private EditText reset1, reset2;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmpassword);
        setUIViews();





        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    String email = sharedPreferences.getString("email","");
                    String password0 = reset1.getText().toString();
                    setpassword(email,password0);
                }
            }
        });
    }

    private void setpassword(final String email1, final String password1){

        String URL_REGIST = "http://104.41.183.184/resetpassword.php";
        StringRequest request = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if(success.equals("1")){
                        Toast.makeText(confirmpassword.this,"更改成功",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(confirmpassword.this,MainActivity.class));
                        finish();
                    }else{
                        Toast.makeText(confirmpassword.this,success,Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(confirmpassword.this,"error"+e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(confirmpassword.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("email",email1);
                param.put("password",password1);
                return param;

            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(30000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(confirmpassword.this).addToRequestQueue(request);



    }

    private  void setUIViews(){
        confirm = (Button)findViewById(R.id.confirm);
        reset1 = (EditText)findViewById(R.id.reset1);
        reset2 = (EditText)findViewById(R.id.reset2);
        sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);

    }
    private  Boolean validate(){
        Boolean result = false;
        String reset01 = reset1.getText().toString();
        String reset02 = reset2.getText().toString();
        if(reset01.isEmpty() && reset02.isEmpty()){
            Toast.makeText(this,"請設定密碼", Toast.LENGTH_SHORT).show();
        }else if(reset01.isEmpty()){
            Toast.makeText(this,"請輸入新密碼", Toast.LENGTH_SHORT).show();
        }else  if(!reset02.equals(reset01) ){
            Toast.makeText(this,"請確認密碼輸入是否正確", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }
        return  result;
    }




}