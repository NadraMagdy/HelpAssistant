package com.example.helpassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SetupContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_contact);
    }

    public void save(View view) {
        Intent j = new Intent(SetupContact.this , Home.class);
        startActivity(j);
    }
}
