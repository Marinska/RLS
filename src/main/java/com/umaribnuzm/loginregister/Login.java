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

public class Login extends AppCompatActivity {
    EditText textinputusername;
    EditText textinputpassword;
    Button buttonlogin;
    TextView redirectdaftar;
    ProgressBar progressBar;
    AppConfig appConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textinputusername = findViewById(R.id.textusername);
        textinputpassword = findViewById(R.id.textpassword);
        buttonlogin = findViewById(R.id.btnlogin);
        progressBar = findViewById(R.id.progress);
        redirectdaftar = findViewById(R.id.redirectdaftar);

        appConfig = new AppConfig(this);
        if(appConfig.UserLogin())
        {
            String name = appConfig.getNameofuser();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("name", name);
            startActivity(intent);
            finish();
        }

        redirectdaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
                finish();
            }
        });

        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username, password;
                username = textinputusername.getText().toString();
                password = textinputpassword.getText().toString();
                appConfig.UpdateUserLoginStatus(true);
                appConfig.SavedUserName(username);

                if(!username.equals("") && !password.equals("")) {
                    progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[2];
                            field[0] = "username";
                            field[1] = "password";

                            String[] data = new String[2];
                            data[0] = username;
                            data[1] = password;

                            PutData putData = new PutData("https://marinska.site/LoginRegisterApp/login.php", "POST", field, data);
                            if (putData.startPut()){
                                if (putData.onComplete()){
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if(result.equals("Login Success")){
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        String name = textinputusername.getText().toString();
                                        intent.putExtra("name", name);
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