package com.example.helpassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void Setting(View view) {
        Intent k = new Intent(Home.this,Setting.class);
        startActivity(k);
    }

    public void SC(View view) {
        Intent K = new Intent(Home.this,SetupContact.class);
        startActivity(K);
    }
}
