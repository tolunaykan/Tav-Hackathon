package com.kandirmaz.tolunay.tavhackaathon;

import android.app.ProgressDialog;
import android.content.Intent;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ContestClass extends AppCompatActivity  {
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    private List<HashMap<String,String>> veriler;
    private HashMap<String,String> item;
    LinearLayout sorguLayout, infoLayout;
    String flightNumber,url;
    TextView timeTv,gateTv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contest_layout);

        auth = FirebaseAuth.getInstance();


        sorguLayout = (LinearLayout)findViewById(R.id.linearLayout2);
        infoLayout = (LinearLayout)findViewById(R.id.infoLayout);

        url = "";

        timeTv = (TextView)findViewById(R.id.textView14);
        gateTv = (TextView)findViewById(R.id.textView13);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference userRef = database.getReference("users/" + auth.getUid());
        DatabaseReference data = userRef.child("flightNumber");

        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                flightNumber = dataSnapshot.getValue(String.class);

                if(flightNumber!=null){
                    sorguLayout.setVisibility(View.INVISIBLE);
                    infoLayout.setVisibility(View.VISIBLE);
                    String ilk2= flightNumber.substring(0,2);
                    String num = flightNumber.substring(2);
                    String url = "http://tolunaykan.xyz/tav/search.php?code=" + ilk2 +"&num=" + num;
                    new DownloadTask().execute(url);

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });







        Button btn = (Button)findViewById(R.id.button2);
        Button btnSorgula = (Button)findViewById(R.id.button11);
        final EditText veri = (EditText)findViewById(R.id.editText5);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();

            }
        });

        btnSorgula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();

                DatabaseReference userRef = database.getReference("users/" + auth.getUid());
                userRef.child("flightNumber").setValue(veri.getText().toString());

                DatabaseReference data = userRef.child("flightNumber");

                data.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        flightNumber = dataSnapshot.getValue(String.class);
                        if(flightNumber!=null){
                            sorguLayout.setVisibility(View.INVISIBLE);
                            infoLayout.setVisibility(View.VISIBLE);
                            String ilk2= flightNumber.substring(0,2);
                            String num = flightNumber.substring(2);
                            String url = "http://tolunaykan.xyz/tav/search.php?code=" + ilk2 +"&num=" + num;
                            new DownloadTask().execute(url);

                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });




            }
        });

        GridView gridview = (GridView)findViewById(R.id.gridview);

        veriler =new ArrayList<>();

        HashMap<String,String> item = new HashMap<>();
        item.put("name","Havacılık");
        item.put("aciklama","Bu kategoride uçak ve havalimanları hakkında genel kültür soruları bulunmaktadır.");

        veriler.add(0,item);

        HashMap<String,String> item2 = new HashMap<>();
        item2.put("name","Genel kültür");
        item2.put("aciklama","Bu kategoride cevaplayacağınız sorular genel kültürünüzü ölçmeye yöneliktir.");

        veriler.add(1,item2);

        HashMap<String,String> item3 = new HashMap<>();
        item3.put("name","Spor");
        item3.put("aciklama","Bu kategoride spor ile ilgili sorular bulunmaktadır.");

        veriler.add(2,item3);

        HashMap<String,String> item4 = new HashMap<>();
        item4.put("name","Sinema");
        item4.put("aciklama","Bu kategoride cevaplayacağınız sorular sinema bilginizi ölçmeye yöneliktir.");

        veriler.add(3,item4);




        gridview.setAdapter(new GridViewAdapter(getApplicationContext(),veriler));

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(ContestClass.this, LogIn.class));
                    finish();
                }
            }
        };



    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }

    private class DownloadTask extends AsyncTask<String, Void, String> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            if ( progressDialog!=null && progressDialog.isShowing() ){
                progressDialog.cancel();
            }

            progressDialog = new ProgressDialog(ContestClass.this);
            progressDialog.setMessage("Yükleniyor Lütfen Bekleyiniz");
            progressDialog.setIcon(R.mipmap.ic_launcher);
            progressDialog.setCancelable(false);
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String json="";
            try {
                json = JSONDownloader.downloadJson(params[0]);
                Log.d("hataa",params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }

            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(json);
            } catch (JSONException e) {
                Log.d("sss","ss");
            }
            item = JSONInfoReader.parse(jsonObject);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            gateTv.setText("Kapınız: "+item.get("gate"));
            timeTv.setText("Uçuş tarihiniz: "+item.get("date"));
            super.onPostExecute(result);
        }


    }



}
