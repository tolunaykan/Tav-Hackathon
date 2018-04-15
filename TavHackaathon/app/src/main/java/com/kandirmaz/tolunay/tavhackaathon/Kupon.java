package com.kandirmaz.tolunay.tavhackaathon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Kupon extends AppCompatActivity {

    Boolean isSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kupon_layout);

        final TextView kuponResult = (TextView)findViewById(R.id.textView12);
        Button btn1 = (Button)findViewById(R.id.button7);
        Button btn2 = (Button)findViewById(R.id.button8);
        Button btn3 = (Button)findViewById(R.id.button9);



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSelected){
                    Toast.makeText(getApplicationContext(),"Zaten ödül seçtiniz",Toast.LENGTH_SHORT).show();
                }else{
                    kuponResult.setText("30 Nisan 2018 tarihine kadar geçerli TAV Havalimanlarında ücretsiz bir kerelik Business Check-in kazandınız\nKupon kodunuz: M6yt9FE3");
                    isSelected = true;
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSelected){
                    Toast.makeText(getApplicationContext(),"Zaten ödül seçtiniz",Toast.LENGTH_SHORT).show();
                }else {
                    kuponResult.setText("30 Nisan 2018 tarihine kadar geçerli TAV Havalimanlarında ücretsiz bir kerelik Hızlı Geçiş hakkı kazandınız\nKupon kodunuz: D5tYz9AD");
                    isSelected = true;
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSelected){
                    Toast.makeText(getApplicationContext(),"Zaten ödül seçtiniz",Toast.LENGTH_SHORT).show();
                }else {
                    kuponResult.setText("30 Nisan 2018 tarihine kadar geçerli TAV Havalimanlarında ücretsiz bir kerelik Ring Hizmeti kazandınız\nKupon kodunuz: H7Qo5Ty6I");
                    isSelected = true;
                }
            }
        });





    }
}
