package com.example.helpassistant;

import android.content.Intent;
import android.content.SharedPreferences;
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



public class Edit extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
    }

    public void save(View view) {
        SharedPreferences sp = getSharedPreferences("UserInfo", MODE_PRIVATE);


        EditText txtUpdateFirstName = findViewById(R.id.txtUpdateFirstName);
        String FirstName = txtUpdateFirstName.getText().toString();

        EditText txtUpdateLastName = findViewById(R.id.txtUpdateLastName);
        String LastName = txtUpdateLastName.getText().toString();

        EditText txtUpdatePhoneNum =findViewById(R.id.txtUpdatePhoneNum);
        String PhoneNumber =txtUpdatePhoneNum.getText().toString();

        EditText txtUpdatePassword = findViewById(R.id.txtUpdatePassword);
        String Password = txtUpdatePassword.getText().toString();

        // Define the Request Params object to call the REST API
        StringEntity jsonObject = null;
        JSONObject paramsJson = new JSONObject();
        try {
            paramsJson.put("FirstName", FirstName);
            paramsJson.put("LastName", LastName);
            paramsJson.put("PhoneNumber",PhoneNumber);
            paramsJson.put("Password",Password);
            paramsJson.put("UserID",sp);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jsonObject = new StringEntity(paramsJson.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // Call the HttpUtil Class

        HttpUtils.post("api/user/Update", jsonObject, "application/json", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statueCode, Header[] headers, JSONObject response) {
                Log.d("asd", "---------------- this is response : " + response);
                try {
                    //JSONObject serverResp = new JSONObject(response.toString());
                    Toast.makeText(getApplicationContext(), "User Updated Successfully", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(Edit.this , Home.class);
                    startActivity(i);

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

                Log.e("omg android", statusCode + " " + throwable.getMessage());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String err, Throwable throwable) {
                // Log error message
                // to help solve any problems
                Toast.makeText(getApplicationContext(), err, Toast.LENGTH_LONG).show();
                Log.e("omg android", statusCode + " " + throwable.getMessage());
            }
        });

    }

    public void Del(View view) {


        SharedPreferences sp = getSharedPreferences("UserInfo", MODE_PRIVATE);
        StringEntity jsonObject = null;
        JSONObject paramsJson = new JSONObject();
        try {
            paramsJson.put("UserID",sp.contains("UserID") );
            } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jsonObject = new StringEntity(paramsJson.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HttpUtils.post("api/user/deleteUser", jsonObject, "application/json", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                Log.d("asd", "---------------- this is response : " + response);
                try {
                    JSONObject serverResp = new JSONObject(response.toString());
                    UserModel user = UserModel.fromJson(serverResp);
                    // Set user information in the shared preference
                    SharedPreferences sp = getSharedPreferences("UserInfo", MODE_PRIVATE);
                    SharedPreferences.Editor ed = sp.edit();
                    ed.putString("UserID", user.getUserID());
                    ed.commit();

                    Intent j = new Intent(Edit.this, MainActivity.class);
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
                Log.e("omg ff android", statusCode + " " + throwable.getMessage());
            }


        });
    }

}