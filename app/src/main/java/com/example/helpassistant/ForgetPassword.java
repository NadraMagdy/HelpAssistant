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

public class ForgetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
    }

    public void btnsend(View view) {

        EditText txtEmail = findViewById((R.id.forgetpasswordtxt));
        String email = txtEmail.getText().toString();

        StringEntity jsonObject = null;
        JSONObject paramsJson = new JSONObject();
        try {
            paramsJson.put("Email", email);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jsonObject = new StringEntity(paramsJson.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HttpUtils.post("api/user/ForgetPassword", jsonObject, "application/json", new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        Log.d("asd", "---------------- this is response : " + response);
                        try {
                            //JSONObject serverResp = new JSONObject(response.toString());
                            Toast.makeText(getApplicationContext(), "Please check your email to activate your account.", Toast.LENGTH_LONG).show();

                        } catch (Exception e) {
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

        Intent i = new Intent(ForgetPassword.this , EnterSecurityCode.class);
        startActivity(i);
      }
}
