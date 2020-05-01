package com.example.youtubedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.youtubedemo.Activities.ClassActivity;

public class LoginActivity extends AppCompatActivity {

    private TextView textView_welcome, textView_login, textView_password;
    private EditText editText_login, editText_password;
    private Button button_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        textView_welcome = findViewById(R.id.textView_welcome);
        textView_login = findViewById(R.id.textView_login);
        textView_password = findViewById(R.id.textView_pssword);
        editText_login = findViewById(R.id.editText_login);
        editText_password = findViewById(R.id.editText_password);
        button_login = findViewById(R.id.button_login);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ClassActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
