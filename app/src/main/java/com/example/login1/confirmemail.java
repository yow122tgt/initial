package com.example.login1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class confirmemail extends AppCompatActivity {

    private Button confirm ,confirm2;
    private EditText editemail;
    FirebaseUser user;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmemail);
        setUIViews();

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    final String email = editemail.getText().toString();
                    setInfo();

                    confirm.setEnabled(false);
                    FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            confirm.setEnabled(true);
                            if(task.isSuccessful()){
                                Toast.makeText(confirmemail.this,"已經寄到"+ email +",請認證",Toast.LENGTH_SHORT).show();

                            }else{
                                Toast.makeText(confirmemail.this,"Email錯誤",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });



                }



            }
        });
        confirm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().getCurrentUser().reload().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        final String email = editemail.getText().toString();
                        setInfo();
                        if(user.isEmailVerified() ==true){
                            sharedPreferences.edit().putString("email",email).commit();

                            startActivity(new Intent(confirmemail.this,confirmpassword.class));
                        }
                    }
                });
            }
        });

    }
    private  void setInfo(){
        user = FirebaseAuth.getInstance().getCurrentUser();
        System.out.println(user.isEmailVerified());
    }

    private void setUIViews(){
        editemail = (EditText)findViewById(R.id.editemail);
        confirm = (Button)findViewById(R.id.confirm);
        confirm2 = findViewById(R.id.confirm2);
        sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
    }

    private Boolean validate(){
        Boolean result = false;
        String phone = editemail.getText().toString();
        if(phone.isEmpty()){
            Toast.makeText(this, "請輸入Email", Toast.LENGTH_SHORT).show();
        }else{
            result=true;
        }
        return result;
    }


}
