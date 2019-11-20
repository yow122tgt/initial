package com.example.login1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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


public class MainActivity extends AppCompatActivity {

    private EditText username, password;
    private Button login, registered, forgot;
    private CheckBox checkBox;
    SharedPreferences sharedPreferences;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUIViews();
        username.setText(sharedPreferences.getString("username", ""));

        registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, sign_up.class));
            }
        });

        String loginStatus = sharedPreferences.getString(getResources().getString(R.string.prefLoginstate),"");
        if(loginStatus.equals("loggedin")){
            startActivity(new Intent(MainActivity.this, MenuActivity.class));
        }

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,confirmemail.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    String name =  username.getText().toString();
                    String password0 = password.getText().toString();
                    login(name,password0);
                }


            }
        });

    }


    private  void setupUIViews(){
        username = (EditText)findViewById(R.id.varification);
        password = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.confirm);
        registered= (Button)findViewById(R.id.registed);
        forgot = (Button)findViewById(R.id.forgot);
        checkBox = findViewById(R.id.checkBox);
        sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);


    }



    private boolean validate(){
        Boolean result = false;
        String name = username.getText().toString();
        String password2 = password.getText().toString();
        if(name.isEmpty() || password2.isEmpty()){
            Toast.makeText(this, "請輸入帳號密碼", Toast.LENGTH_SHORT).show();
        }else {
            result=true;
        }

        return  result;

    }


private void login(final String username1,final String password1){
    String URL_REGIST = "http://104.41.183.184/login.php";
    StringRequest request = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                JSONObject jsonObject = new JSONObject(response);
                String success = jsonObject.getString("success");
                if(success.equals("1")){

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    sharedPreferences.edit().putString("username",username1).commit();

                    if(checkBox.isChecked()){
                        editor.putString(getResources().getString(R.string.prefLoginstate),"loggedin");
                    }else{
                        editor.putString(getResources().getString(R.string.prefLoginstate),"loggedout");
                    }

                    editor.apply();

                    startActivity(new Intent(MainActivity.this, MenuActivity.class));

                }else{
                    Toast.makeText(MainActivity.this,success,Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this,"error"+e.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
    },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                }
            })
    {
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String,String> params = new HashMap<>();
            params.put("username",username1);
            params.put("password",password1);



            return params;
        }
    };
    request.setRetryPolicy(new DefaultRetryPolicy(30000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    MySingleton.getInstance(MainActivity.this).addToRequestQueue(request);
}

}