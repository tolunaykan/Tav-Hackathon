package com.kandirmaz.tolunay.tavhackaathon;

import android.os.Environment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JSONInfoReader {

    public static HashMap<String,String> parse(JSONObject jsonObject){

        HashMap<String,String> item = new HashMap<>();

        try{

            String date = jsonObject.getString("time").toString();
            String gate = jsonObject.getString("gate").toString();

            if(gate.equals("null")){
                gate = "Kapınız belli değildir";
            }

            item.put("date",date);
            item.put("gate",gate);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return item;
    }

}
