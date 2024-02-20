package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;

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

public class SignUpActivity extends AppCompatActivity {

    EditText nameedt, emailedt, passedt, cnfpassedt;
    MaterialButton Signupbtn;
    TextView loginbtn;

    MyDBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nameedt = findViewById(R.id.name_input);
        emailedt = findViewById(R.id.email_input);
        passedt = findViewById(R.id.password_input);
        cnfpassedt = findViewById(R.id.confirm_password_input);
        Signupbtn = findViewById(R.id.sign_up_button);
        loginbtn = findViewById(R.id.login_button);

        db  = new MyDBHelper(SignUpActivity.this);

        Signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameedt.getText().toString();
                String email= emailedt.getText().toString();
                String password = passedt.getText().toString();
                String confirmPass = cnfpassedt.getText().toString();

                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPass)) {
                    Toast.makeText(SignUpActivity.this, "All fields required !", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkemail = db.checkEmail(email);
                        if(checkemail == false){
                            if(password.equals(confirmPass)){
                                Boolean insert = db.insertData(name,email,password);
                                if(insert == true){
                                    Cursor res = db.fetchname(email);
                                    res.moveToFirst();
                                    String email_name = res.getString(0);
                                    String email_id = res.getString(1);
                                    SharedPreferences pref = getSharedPreferences("login",MODE_PRIVATE);
                                    SharedPreferences.Editor editor = pref.edit();
                                    editor.putBoolean("flag",true);
                                    editor.putString("NAME",email_name);
                                    editor.putString("EMAIL",email_id);
                                    editor.apply();
                                    Toast.makeText(SignUpActivity.this,"Sign up Successfull !", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else{
                                    Toast.makeText(SignUpActivity.this, "SignUp Failed!", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(SignUpActivity.this, "Passwords does not match !", Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            Toast.makeText(SignUpActivity.this, "email already exists !", Toast.LENGTH_SHORT).show();
                        }

                }

            }
        });



        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}