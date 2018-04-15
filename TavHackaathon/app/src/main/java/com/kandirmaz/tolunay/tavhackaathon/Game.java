package com.kandirmaz.tolunay.tavhackaathon;

import android.app.ProgressDialog;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Game extends AppCompatActivity  {

    List<HashMap<String,String>> veriler;
    TextView soru,sure;
    Button btn1,btn2,btn3,btn4;
    int skor = 0;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        soru = (TextView)findViewById(R.id.textView7);
        sure = (TextView)findViewById(R.id.textView8);
        btn1 = (Button)findViewById(R.id.button3);
        btn2 = (Button)findViewById(R.id.button4);
        btn3 = (Button)findViewById(R.id.button5);
        btn4 = (Button)findViewById(R.id.button6);

        new DownloadTask().execute();







    }

    private class DownloadTask extends AsyncTask<Void, Void, Void>{
        private ProgressDialog progressDialogg;

        @Override
        protected void onPreExecute() {
            if ( progressDialogg!=null && progressDialogg.isShowing() ){
                progressDialogg.cancel();
            }

            progressDialogg = new ProgressDialog(Game.this);
            progressDialogg.setMessage("Yükleniyor Lütfen Bekleyiniz");
            progressDialogg.setIcon(R.mipmap.ic_launcher);
            progressDialogg.setCancelable(false);
//            progressDialogg.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            String json="";
            try {
                json = JSONDownloader.downloadJson("http://tavv.herokuapp.com/soru/listele");
            } catch (IOException e) {
                e.printStackTrace();
            }
            JSONReader jsonReader = new JSONReader();
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(json);
            } catch (JSONException e) {
                Log.d("sss","ss");
            }
            veriler = jsonReader.parse(jsonObject);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (progressDialogg.isShowing()) {
                progressDialogg.dismiss();
            }
            soruGetir(0);
            super.onPostExecute(result);
        }

    }

    public void soruGetir(final int sayac){

        if (sayac > 9) {

            if(timer!=null){
                timer.cancel();

            }

            Intent intent = new Intent(Game.this,Skor.class);
            intent.putExtra("skor",String.valueOf(skor));
            startActivity(intent);
            finish();


        }else{

            soru.setText(veriler.get(sayac).get("soru"));
            btn1.setText(veriler.get(sayac).get("cevap1"));
            btn2.setText(veriler.get(sayac).get("cevap2"));
            btn3.setText(veriler.get(sayac).get("cevap3"));
            btn4.setText(veriler.get(sayac).get("cevap4"));

            reverseTimer(30,sayac);

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if(veriler.get(sayac).get("cevap1Truth").equals("1")){
                        skor += (sayac+1)*Integer.valueOf(sure.getText().toString());

                    }

                    soruGetir(sayac+1);


                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if(veriler.get(sayac).get("cevap2Truth").equals("1")){
                        skor += (sayac+1)*Integer.valueOf(sure.getText().toString());

                    }

                    soruGetir(sayac+1);

                }
            });
            btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if(veriler.get(sayac).get("cevap3Truth").equals("1")){
                        skor += (sayac+1)*Integer.valueOf(sure.getText().toString());
                    }

                    soruGetir(sayac+1);

                }
            });
            btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if(veriler.get(sayac).get("cevap4Truth").equals("1")){
                        skor += (sayac+1)*Integer.valueOf(sure.getText().toString());
                    }

                    soruGetir(sayac+1);

                }
            });

        }

    }

    public void reverseTimer(int Seconds, final int sayacc){

        if(timer!=null){
            timer.cancel();

        }

        timer = new CountDownTimer(Seconds* 1000+1000, 1000) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                sure.setText(String.valueOf(seconds));
            }

            public void onFinish() {
                sure.setText("0");
                soruGetir(sayacc+1);
            }
        };

        timer.start();
    }
}
