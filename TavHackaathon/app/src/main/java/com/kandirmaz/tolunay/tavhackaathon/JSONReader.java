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

public class JSONReader {

    public List<HashMap<String,String>> parse(JSONObject jsonObject){

        JSONArray jsonArray = null;

        try{

            jsonArray = jsonObject.getJSONArray("sorular");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return getItems(jsonArray);
    }

    private List<HashMap<String,String>> getItems(JSONArray jsonArray){

        int itemSayisi = jsonArray.length();
        List<HashMap<String,String>> itemList = new ArrayList<>();
        HashMap<String,String> item = null;

        for(int i=0; i<itemSayisi; i++){
            try {
                JSONObject firstObj = jsonArray.getJSONObject(i);
                item = getItem(firstObj);
                itemList.add(item);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return itemList;

    }

    private HashMap<String,String> getItem(JSONObject jsonObject){

        HashMap<String,String> item = new HashMap<>();
        String itemSoru = "";
        String itemCevap1 = "";
        String itemCevap2 = "";
        String itemCevap3 = "";
        String itemCevap4 = "";
        String itemCevap1Truth = "";
        String itemCevap2Truth = "";
        String itemCevap3Truth = "";
        String itemCevap4Truth = "";
        String itemTur = "";

        try{
            itemSoru = jsonObject.getString("soru").toString();
            itemCevap1 = jsonObject.getJSONArray("cevaplar").getJSONObject(0).getString("data").toString();
            itemCevap2 = jsonObject.getJSONArray("cevaplar").getJSONObject(1).getString("data").toString();
            itemCevap3 = jsonObject.getJSONArray("cevaplar").getJSONObject(2).getString("data").toString();
            itemCevap4 = jsonObject.getJSONArray("cevaplar").getJSONObject(3).getString("data").toString();



            itemCevap1Truth = jsonObject.getJSONArray("cevaplar").getJSONObject(0).getString("state").toString();
            itemCevap2Truth = jsonObject.getJSONArray("cevaplar").getJSONObject(1).getString("state").toString();
            itemCevap3Truth = jsonObject.getJSONArray("cevaplar").getJSONObject(2).getString("state").toString();
            itemCevap4Truth = jsonObject.getJSONArray("cevaplar").getJSONObject(3).getString("state").toString();
            itemTur = jsonObject.getString("tur").toString();


            item.put("soru",itemSoru);
            item.put("cevap1",itemCevap1);
            item.put("cevap2",itemCevap2);
            item.put("cevap3",itemCevap3);
            item.put("cevap4",itemCevap4);



            item.put("cevap1Truth",itemCevap1Truth);
            item.put("cevap2Truth",itemCevap2Truth);
            item.put("cevap3Truth",itemCevap3Truth);
            item.put("cevap4Truth",itemCevap4Truth);
            item.put("tur",itemTur);



        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("Hata","Hata");
        }
        return item;
    }

}
