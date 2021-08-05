package com.example.carapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;

public class Login extends AppCompatActivity {
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login =(Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(Login.this, "登入成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}