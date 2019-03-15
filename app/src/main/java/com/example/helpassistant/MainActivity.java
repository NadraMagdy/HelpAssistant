package com.example.helpassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Login(View view) {




        // Get the Edit Text of the email object
        EditText txtEmail = findViewById(R.id.txtEmail);
        // Get the value entered by the user
        String email = txtEmail.getText().toString();
        // Get the Edit Text of the password object
        EditText txtPassword = findViewById((R.id.txtPassword));
        String password = txtPassword.getText().toString();
        // Define the Request Params object to call the REST API

        StringEntity jsonObject = null;
        JSONObject paramsJson = new JSONObject();
        try {
            paramsJson.put("userName", email);
            paramsJson.put("UserPassword", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jsonObject = new StringEntity(paramsJson.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        // Call the HttpUtil Class
        HttpUtils.post("api/user/signIn", jsonObject, "application/json", new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("asd", "---------------- this is response : " + response);
                try {
                    JSONObject serverResp = new JSONObject(response.toString());
                    UserModel user = UserModel.fromJson(serverResp);
                    Intent j = new Intent(MainActivity.this , Home.class);
                    j.putExtra("UserModelObject", user);
                    startActivity(j);

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
                Toast.makeText(getApplicationContext(), err, Toast.LENGTH_LONG).show();
                Log.e("omg ff android", statusCode + " " + throwable.getMessage());
            }
        });

    }

    public void ForgetPass(View view) {
        Intent M = new Intent ( MainActivity.this , ForgetPassword.class );
        startActivity(M);
    }

    public void CNA(View view) {
        Intent s = new Intent ( MainActivity.this , CreateNewAcc.class );
        startActivity(s);
    }
}
