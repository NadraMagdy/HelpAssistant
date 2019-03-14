package com.example.helpassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;


public class CNA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cn);
    }

     public void SU(View view) {
         // Get the Edit Text of the firstName object
         EditText txtFirstName = findViewById(R.id.txtFirstName);
         // Get the value entered by the user
         String FirstName = txtFirstName.getText().toString();
         // Get the Edit Text of the lastName object
         EditText txtLastName = findViewById(R.id.txtLastName);
         // Get the value entered by the user
         String LastName = txtLastName.getText().toString();
         // Get the Edit Text of the txtPhoneNumber object
         EditText txtPhoneNumber = findViewById(R.id.txtPhoneNumber);
         // Get the value entered by the user
         String PhoneNumber = txtPhoneNumber.getText().toString();
         // Get the Edit Text of the userName object
         EditText txtUserName = findViewById(R.id.txtUserName);
         // Get the value entered by the user
         String UserName = txtUserName.getText().toString();
         // Get the Edit Text of the email object
         EditText txtEmail = findViewById(R.id.txtEmail);
         // Get the value entered by the user
         String Email = txtEmail.getText().toString();
         // Get the Edit Text of the email object
         EditText txtPassword = findViewById(R.id.txtPassword);
         // Get the value entered by the user
         String Password = txtPassword.getText().toString();

         // Define the Request Params object to call the REST API
         StringEntity jsonObject = null;
         JSONObject paramsJson = new JSONObject();
         try {
             paramsJson.put("FirstName", FirstName);
             paramsJson.put("LastName", LastName);
             paramsJson.put("PhoneNumber", PhoneNumber);
             paramsJson.put("UserName", UserName);
             paramsJson.put("Email", Email);
             paramsJson.put("Password", Password);
         } catch (JSONException e) {
             e.printStackTrace();
         }
         try {
             jsonObject = new StringEntity(paramsJson.toString());
         } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
         }

         // Call the HttpUtil Class

         HttpUtils.post("api/user/register", jsonObject, "application/json", new JsonHttpResponseHandler() {
             @Override
             public void onSuccess(int statueCode, Header[] headers, JSONObject response) {
                 Log.d("asd", "---------------- this is response : " + response);
                 try {
                     //JSONObject serverResp = new JSONObject(response.toString());
                     Toast.makeText(getApplicationContext(), "User Created Successfully, Please check your email to activate account.", Toast.LENGTH_LONG).show();
                     Intent i = new Intent(CNA.this , MainActivity.class);
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
}
