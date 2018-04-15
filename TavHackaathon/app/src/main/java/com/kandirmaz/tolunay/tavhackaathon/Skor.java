package com.kandirmaz.tolunay.tavhackaathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Skor extends AppCompatActivity  {

    private List<HashMap<String,String>> veriler;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skor_layout);

        auth = FirebaseAuth.getInstance();

        String mail = auth.getCurrentUser().getEmail();

        String[] isim = mail.split("@");


        Button btn = (Button)findViewById(R.id.button10);

        String skor = getIntent().getStringExtra("skor");

        ListView listView = (ListView)findViewById(R.id.listView);

        veriler =new ArrayList<>();

        HashMap<String,String> item = new HashMap<>();
        item.put("isim","Metehan");
        item.put("skor","1000");
        item.put("id","0");
        veriler.add(0,item);

        HashMap<String,String> item2 = new HashMap<>();
        item2.put("isim","Tolunay");
        item2.put("skor","856");
        item2.put("id","0");
        veriler.add(1,item2);

        HashMap<String,String> item3 = new HashMap<>();
        item3.put("isim","Mehmet");
        item3.put("skor","546");
        item3.put("id","0");
        veriler.add(2,item3);

        HashMap<String,String> item4 = new HashMap<>();
        item4.put("isim","Fatih");
        item4.put("skor","456");
        item4.put("id","0");
        veriler.add(3,item4);

        HashMap<String,String> item5 = new HashMap<>();
        item5.put("isim","Ahmet");
        item5.put("skor","312");
        item5.put("id","0");
        veriler.add(4,item5);

        HashMap<String,String> item6 = new HashMap<>();
        item6.put("isim","Burak");
        item6.put("skor","186");
        item6.put("id","0");
        veriler.add(5,item6);

        HashMap<String,String> item7 = new HashMap<>();
        item7.put("isim",isim[0]);
        item7.put("skor",skor);
        item7.put("id","1");
        veriler.add(6,item7);




        for(int i=0; i<veriler.size(); i++){

           for(int a=0; a<veriler.size()-1; a++){

               if(Integer.parseInt(veriler.get(a).get("skor"))<Integer.parseInt(veriler.get(a+1).get("skor"))){


                   HashMap<String,String> temp;
                   temp = veriler.get(a+1);
                   veriler.set(a+1,veriler.get(a));
                   veriler.set(a,temp);


               }


           }


            if(veriler.get(0).get("isim").equals(isim[0])){
                btn.setVisibility(View.VISIBLE);
            }




        }

        final Intent intent = new Intent(Skor.this,Kupon.class);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                finish();
            }
        });

        listView.setAdapter(new ListAdapter(getApplicationContext(),veriler));


    }
}
