package com.umaribnuzm.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView welcome;
    AppConfig appConfig;
    Button btnLogout,btnProfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        welcome = findViewById(R.id.welcome);
        btnLogout = findViewById(R.id.btnlogout);
        btnProfil = findViewById(R.id.btnprofil);
        appConfig = new AppConfig(this);
        String name = getIntent().getStringExtra("name");
        welcome.setText("Selamat Datang "+name);

        btnProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getApplicationContext(),EditProfil.class);
                //intent.putExtra("name", name);
                //startActivity(intent);
                //finish();
                Toast.makeText(getApplicationContext(),"Fitur belum tersedia, masih dalam tahap pengembangan",Toast.LENGTH_SHORT).show();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appConfig.UpdateUserLoginStatus(false);
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });
    }


}