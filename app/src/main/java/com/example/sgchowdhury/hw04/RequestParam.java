package com.example.sgchowdhury.hw04;

/***
 * InClass05
 * Recipe Puppy
 * Gana Ramesan, Shrirupa Chowdhury
 */

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by ganar on 9/25/2017.
 */

public class RequestParam {
    String method,baseUrl;
    HashMap<String,String> params = new HashMap<String, String>();

    public RequestParam(String method, String baseUrl) {
        this.method = method;
        this.baseUrl = baseUrl;
    }

    public String getEncodedParams() throws UnsupportedEncodingException {
        //loop over key/value params
        //appent to stringbuilder key = value

        StringBuilder sb = new StringBuilder();
        for(String key : params.keySet()){
            String value = URLEncoder.encode(params.get(key),"UTF-8");
            if (sb.length() >0)
                sb.append("&");
            sb.append(key+"="+value);
        }
//        Log.d("test", "sb: "+sb.toString());
        return sb.toString();
    }

    public String Encodedurl() throws UnsupportedEncodingException {
        return this.baseUrl +"?"+getEncodedParams();
    }

    public HttpURLConnection setUpCOnnectio() throws IOException {
        if(method.equals("GET")){
            URL url = new URL(Encodedurl());

            HttpURLConnection con = null;
            try {
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return con;

        }else{ //POST
            URL url = new URL(this.baseUrl);
            HttpURLConnection con = null;
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.getOutputStream());
            outputStreamWriter.write(getEncodedParams());
            outputStreamWriter.flush();
            return con;


        }

    }

    public void addParam(String key, String value){
        params.put(key,value);
    }
}