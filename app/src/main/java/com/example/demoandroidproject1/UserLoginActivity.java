package com.example.demoandroidproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class UserLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void hideHorizontalDivider(View view)
    {
        EditText editText=(EditText)view;
        String s= editText.getHint().toString();
        if(s.equals(R.string.email_hint)){
            View v1=findViewById(R.id.dv_email);
            v1.setVisibility(View.INVISIBLE);
        }
    }
}