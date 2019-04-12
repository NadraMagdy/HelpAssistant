package com.example.helpassistant;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class SmsHelper {
    String API_KEY = "textbelt";
    public void sendSms(String phoneNumber,String massage)
    {
        final NameValuePair[] data = {
                new BasicNameValuePair("phone", phoneNumber),
                new BasicNameValuePair("message", "Hello world"),
                new BasicNameValuePair("key", API_KEY)
        };
        HttpClient httpClient = HttpClients.createMinimal();
        HttpPost httpPost = new HttpPost("https://textbelt.com/text");
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(Arrays.asList(data)));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String responseString = null;
        try {
            responseString = EntityUtils.toString(httpResponse.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            JSONObject response = new JSONObject(responseString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
