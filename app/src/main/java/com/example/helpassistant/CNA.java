package com.example.helpassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CNA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cn);
    }

    public void SU(View view) {
        Intent i = new Intent(CNA.this , Home.class);
        startActivity(i);
    }
}
