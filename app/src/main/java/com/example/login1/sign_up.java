package com.example.login1;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class sign_up extends AppCompatActivity {

    private EditText username, password,email,confirmpassword,name0;
    private Button register2, forgot;
    private TextView relogin;
    private ProgressBar loading;
    private  static String URL_REGIST = "http://104.41.183.184/test.php";
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();

        register2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    String name =  username.getText().toString();
                    String password0 = password.getText().toString();
                    String email0 = email.getText().toString();
                    String sname1 = name0.getText().toString();
                    registerNewAccount(name,password0,email0,sname1);


                }
            }
        });

        relogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(sign_up.this,MainActivity.class));
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(sign_up.this,confirmemail.class));
            }
        });


    }

    private  void setupUIViews(){
        name0 = (EditText)findViewById(R.id.editTextname);
        username = (EditText)findViewById(R.id.editusername);
        password = (EditText)findViewById(R.id.editpassword);
        email = (EditText)findViewById(R.id.editemail);
        register2 = (Button)findViewById(R.id.editregister2);
        forgot = (Button)findViewById(R.id.editforgot);
        relogin = (TextView)findViewById(R.id.sighed);
        loading = findViewById(R.id.loading);
        confirmpassword = (EditText)findViewById(R.id.editpassword2);
    }
    private void registerNewAccount(final String username1, final String password1, final String email1, final String name1){
        loading.setVisibility(View.VISIBLE);
        register2.setVisibility(View.GONE);
        String URL_REGIST = "http://104.41.183.184/register.php";
        StringRequest request = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if(success.equals("1")){
                        String user_email = email.getText().toString().trim();
                        String user_password = password.getText().toString().trim();

                        firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){

                                  //  Toast.makeText(sign_up.this, "註冊成功", Toast.LENGTH_SHORT).show();
                                   // startActivity(new Intent(sign_up.this, MainActivity.class));
                                 //   finish();
                                }else{
                                 //   Toast.makeText(sign_up.this, "註冊失敗!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                             loading.setVisibility(View.GONE);
                             register2.setVisibility(View.VISIBLE);
                             Toast.makeText(sign_up.this,"註冊成功",Toast.LENGTH_LONG).show();
                             startActivity(new Intent(sign_up.this,MainActivity.class));
                             finish();
                    }else{
                        loading.setVisibility(View.GONE);
                        register2.setVisibility(View.VISIBLE);
                        Toast.makeText(sign_up.this,success,Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    loading.setVisibility(View.GONE);
                    register2.setVisibility(View.VISIBLE);
                    Toast.makeText(sign_up.this,"error"+e.getMessage(),Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.setVisibility(View.GONE);
                register2.setVisibility(View.VISIBLE);
                Toast.makeText(sign_up.this,error.toString(),Toast.LENGTH_LONG).show();

            }
        }


        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("username",username1);
                params.put("password",password1);
                params.put("email",email1);
                params.put("lname",name1);

                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(30000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(sign_up.this).addToRequestQueue(request);
    }

    private  Boolean validate(){
        Boolean result = false;
        String name =  username.getText().toString();
        String password0 = password.getText().toString();
        String email0 = email.getText().toString();
        String sname1 = name0.getText().toString();
        String confirmpassword2 = confirmpassword.getText().toString();
        if(name.isEmpty() || password0.isEmpty() || email0.isEmpty() || sname1.isEmpty()){
            Toast.makeText(this, "請輸入資料", Toast.LENGTH_SHORT).show();
        }else if (!password0.equals(confirmpassword2)){
            Toast.makeText(this, "請確認密碼是否正確", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }


        return  result;
    }


}