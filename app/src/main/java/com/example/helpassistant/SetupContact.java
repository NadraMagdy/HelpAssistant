package com.example.helpassistant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class SetupContact extends AppCompatActivity {
    ListView ListView ;
    ArrayList<String> List;
    Button butSave;
    EditText editText;
    EditText editText2;
    EditText editText3;

    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_contact);
        ListView = (ListView) findViewById(R.id.ListView);
        butSave=(Button)findViewById(R.id.butSave);
        editText=(EditText)findViewById(R.id.txtFirstNum);
        editText2=(EditText)findViewById(R.id.txtSecondNum);
        editText3=(EditText)findViewById(R.id.txtThirdNum);


        List = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,List);


        butSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String names=editText.getText().toString();
                String names2=editText2.getText().toString();
                String names3=editText3.getText().toString();
                List.add(names+","+names2+","+names3);
                ListView.setAdapter(arrayAdapter);
                arrayAdapter.notifyDataSetChanged();

            }
        });

    }

    public void save(View view)
    {

        //We have to write the UserId instead of 15  in the default value but it just for test
        SharedPreferences sp = getSharedPreferences("UserInfo", MODE_PRIVATE);
        // Get User ID form SP
        String userID = sp.getString("UserID", null);
        // Get the Edit Text of the firstNumber object
        EditText txtFirstNumber = findViewById(R.id.txtFirstNum);
        // Get the value entered by the user
        String FirstNumber = txtFirstNumber.getText().toString();
        // Get the Edit Text of the SecondNumber object
        EditText txtSecondNumber = findViewById(R.id.txtSecondNum);
        // Get the value entered by the user
        String SecondNumber = txtSecondNumber.getText().toString();
        // Get the Edit Text of the thirdNumber object
        EditText txtThirdNumber = findViewById(R.id.txtThirdNum);
        // Get the value entered by the user
        String ThirdNumber = txtThirdNumber.getText().toString();

        String Numbers = FirstNumber +","+ SecondNumber +"," + ThirdNumber ;
        // Get the Edit Text of the Message object
        EditText txtMessage = findViewById(R.id.txtmessage);
        // Get the value entered by the user
        String Message = txtMessage.getText().toString();

        // Define the Request Params object to call the REST API
        StringEntity jsonObject = null;
        JSONObject paramsJson = new JSONObject();
        try {

            paramsJson.put("UserID",userID);
            paramsJson.put("Numbers",Numbers );
            paramsJson.put("Message", Message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jsonObject = new StringEntity(paramsJson.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // Call the HttpUtil Class
        HttpUtils.post("api/user/Emergency", jsonObject, "application/json", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statueCode, Header[] headers, JSONObject response) {
                Log.d("asd", "---------------- this is response : " + response);
                try {
                    Intent i = new Intent(SetupContact.this , MainActivity.class);
                    startActivity(i);

                    //JSONObject serverResp = new JSONObject(response.toString());
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
