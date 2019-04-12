package com.example.helpassistant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.time.Instant;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Intent i = getIntent();
        //UserModel user = (UserModel)i.getSerializableExtra("UserModelObject");
        // Set the Label to the user.getFirstName()
        // "Welcome: " + user.getFirstName();
        SharedPreferences sp = this.getSharedPreferences("UserInfo", MODE_PRIVATE);
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
        if(sp.contains("FirstName")) {
            textView.setText("Hi, " + sp.getString("FirstName", null));
        }
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
    public void LO(View view) {
        SharedPreferences sp = getSharedPreferences("UserInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
        Intent i = new Intent(Home.this , MainActivity.class);
        startActivity(i);
    }

    public void helpMe(View view){

        SharedPreferences sp = getSharedPreferences("UserInfo", MODE_PRIVATE);
        String UserID = sp.getString("UserID", null);
        StringEntity jsonObject = null;
        JSONObject paramsJson = new JSONObject();
        try {
            paramsJson.put("UserID",UserID );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jsonObject = new StringEntity(paramsJson.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HttpUtils.post("api/user/smsHelper", jsonObject, "application/json", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                Log.d("asd", "---------------- this is response : " + response);
                try {
                    JSONObject serverResp = new JSONObject(response.toString());


                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                // Pull out the first event on the public timeline

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject error) {
                // Log error message
                // to help solve any problems
                Log.e("omg f android", statusCode + " " + throwable.getMessage());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String err, Throwable throwable) {

                // Log error message
                // to help solve any problems
                Log.e("omg ff android", statusCode + " " + throwable.getMessage());
            }


        });
    }

}

