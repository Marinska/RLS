package com.umaribnuzm.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class SignUp extends AppCompatActivity {

    EditText textinputfullname;
    EditText textinputusername;
    EditText textinputpassword;
    EditText textinputemail;
    Button buttondaftar;
    TextView redirectlogin;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        textinputfullname = findViewById(R.id.textfullname);
        textinputusername = findViewById(R.id.textusername);
        textinputpassword = findViewById(R.id.textpassword);
        textinputemail = findViewById(R.id.textemail);
        buttondaftar = findViewById(R.id.btndaftar);
        progressBar = findViewById(R.id.progress);
        redirectlogin = findViewById(R.id.redirectlogin);

        redirectlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        buttondaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname, username, password, email;
                fullname = textinputfullname.getText().toString();
                username = textinputusername.getText().toString();
                password = textinputpassword.getText().toString();
                email = textinputemail.getText().toString();

                if(!fullname.equals("") && !username.equals("") && !password.equals("") && !email.equals("")) {
                    progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[4];
                            field[0] = "fullname";
                            field[1] = "username";
                            field[2] = "password";
                            field[3] = "email";

                            String[] data = new String[4];
                            data[0] = fullname;
                            data[1] = username;
                            data[2] = password;
                            data[3] = email;
                            PutData putData = new PutData("https://marinska.site/LoginRegisterApp/signup.php", "POST", field, data);
                            if (putData.startPut()){
                                if (putData.onComplete()){
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if(result.equals("Sign Up Success")){
                                        Intent intent = new Intent(getApplicationContext(), Login.class);
                                        Toast.makeText(getApplicationContext(),"Berhasil mendaftar, silahkan login", Toast.LENGTH_SHORT).show();
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(),"Mohon isi semua data dengan lengkap", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}