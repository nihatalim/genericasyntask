package com.nihatalim.genericasyntask.business;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thecower on 20.11.2017.
 */

public class ApiIntegrator {
    private String URL = "http://nihatalim.com/childtracker/api/postExample";

    public String ApiRequest(String JSON, String Method) throws Exception{
        if(Method == null) Method = "GET";

        // TODO: Bu metod get/post requestleri için parametrik olmalı
        StringBuilder str = new StringBuilder();
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(this.URL);
        HttpResponse response;
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
        nameValuePairs.add(new BasicNameValuePair("Request", JSON));
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        response = httpclient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        InputStream content = entity.getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
        String line;
        while ((line = reader.readLine()) != null) {
            str.append(line);
        }
        return str.toString();
    }
}
