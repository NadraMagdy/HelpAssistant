package com.example.helpassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ApplyNewPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_new_password);
    }

    public void Login(View view) {
        Intent i = new Intent(ApplyNewPassword.this , Home.class);
        startActivity(i);
    }
}
