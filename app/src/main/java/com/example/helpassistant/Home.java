package com.example.helpassistant;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Instant;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent i = getIntent();
        UserModel user = (UserModel)i.getSerializableExtra("UserModelObject");
        // Set the Label to the user.getFirstName()
        // "Welcome: " + user.getFirstName();

        // Handle Long Click Event
        Button helpButton = findViewById(R.id.btnHelpMe);
        helpButton.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v){
                Toast.makeText(getApplicationContext(), "Help Help Ya Lahwyyyyyy", Toast.LENGTH_LONG).show();
                return  true;
            }
        });
        TextView textView = findViewById(R.id.txtWelcom);

        textView.setText("Hi, " + user.getFirstName());

    }

    public void Setting(View view) {
        Intent k = new Intent(Home.this,Setting.class);
        startActivity(k);
    }

    public void SC(View view) {
        Intent K = new Intent(Home.this,SetupContact.class);
        startActivity(K);
    }

    public void Browesers(View view) {
        Intent BroweserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps"));
        startActivity(BroweserIntent);
    }
}
