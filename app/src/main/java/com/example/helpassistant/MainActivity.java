package com.example.helpassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Login(View view) {
        Intent i = new Intent(MainActivity.this , Home.class);
        startActivity(i);
    }

    public void ForgetPass(View view) {
        Intent M = new Intent ( MainActivity.this , ForgetPassword.class );
        startActivity(M);
    }

    public void CNA(View view) {
        Intent s = new Intent ( MainActivity.this , CNA.class );
        startActivity(s);
    }
}
