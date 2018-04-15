package com.kandirmaz.tolunay.tavhackaathon;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by tolunay on 15.04.2018.
 */

public class JSONDownloader {
    public static String downloadJson(String url) throws IOException {
        String jsonData = "";
        InputStream inputStream = null;
        try {
            URL jsonUrl = new URL(url);

            HttpURLConnection httpURLConnection = (HttpURLConnection) jsonUrl.openConnection();
            httpURLConnection.connect();

            inputStream = httpURLConnection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();

            String veriLine = "";
            while ((veriLine = bufferedReader.readLine()) != null) {
                stringBuffer.append(veriLine);
            }
            jsonData = stringBuffer.toString();

            bufferedReader.close();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }

        return jsonData;

    }
}