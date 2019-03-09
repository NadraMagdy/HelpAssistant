package com.example.helpassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EnterSecurityCode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_security_code);
    }

    public void Next(View view) {
        Intent i = new Intent(EnterSecurityCode.this , ApplyNewPassword.class);
        startActivity(i);
    }
}
