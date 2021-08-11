package com.example.carapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Login extends AppCompatActivity{
    private Button btn_login;
    private EditText  username=null;
    private EditText  password=null;
    //test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login = (Button) findViewById(R.id.btn_login);
        username = (EditText) findViewById(R.id.account);
        password = (EditText) findViewById(R.id.pwd);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    public void login(){
        if (username.getText().toString().equals("123") &&
                password.getText().toString().equals("123")) {
            Toast.makeText(getApplicationContext(), "登入成功",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Login.this,MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "登入失敗",
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void save(String user,String password) throws Exception {
        FileOutputStream output = this.openFileOutput("user.txt", Context.MODE_PRIVATE);
        String fileContent = user+" "+password;
        output.write(fileContent.getBytes());
        output.close();
    }
    public String read() throws IOException {
//開啟檔案輸入流
        FileInputStream input = this.openFileInput("user.txt");
        byte[] temp = new byte[1024];
        StringBuffer stringBuffer = new StringBuffer("");
        int len = 0;
        while ((len = input.read(temp)) > 0) {
            stringBuffer.append(new String(temp, 0, len));
        }
//關閉輸入流
        input.close();
        return stringBuffer.toString();
    }

}