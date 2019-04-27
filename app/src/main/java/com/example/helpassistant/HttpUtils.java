package com.example.helpassistant;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.HttpEntity;

public class HttpUtils {
      private static  final String BASE_URL = "http://10.0.2.2:26694/";
    //private static  final String BASE_URL = "http://23.99.197.150:5000/";
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {

        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, HttpEntity entity, String contentType, AsyncHttpResponseHandler responseHandler){
        client.addHeader("Content-Type", "application/json");
        String aUrl = getAbsoluteUrl(url);
        client.post(null, getAbsoluteUrl(url), entity, contentType, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.addHeader("Content-Type", "application/json");
        client.post(getAbsoluteUrl(url), params, responseHandler);
        //client.post
    }


     private static String getAbsoluteUrl(String relativeUrl){
        return BASE_URL + relativeUrl;
    }
}
