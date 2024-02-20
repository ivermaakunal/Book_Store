package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {

    EditText emailedt, passedt;
    MaterialButton loginbtn;
    TextView signupbtn;
    MyDBHelper db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new MyDBHelper(LoginActivity.this);

        emailedt = findViewById(R.id.email_input);
        passedt = findViewById(R.id.password_input);
        loginbtn = findViewById(R.id.login_button);
        signupbtn = findViewById(R.id.sign_up_button);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailedt.getText().toString();
                String password = passedt.getText().toString();

                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "All fields required !", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkemailpassword =  db.checkEmailPassword(email,password);

                    if(checkemailpassword == true) {
                        Toast.makeText(LoginActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                        Cursor res = db.fetchname(email);
                        res.moveToFirst();
                        String email_name = res.getString(0);
                        String email_id = res.getString(1);

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        SharedPreferences pref = getSharedPreferences("login",MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putBoolean("flag",true);
                        editor.putString("NAME",email_name);
                        editor.putString("EMAIL",email_id);
                        editor.apply();
                        startActivity(intent);
                        finish();

                }else{
                        Toast.makeText(LoginActivity.this, "Login failed !", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });


        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });


    }
}