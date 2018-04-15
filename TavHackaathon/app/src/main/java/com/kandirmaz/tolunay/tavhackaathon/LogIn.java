package com.kandirmaz.tolunay.tavhackaathon;

import android.content.Intent;

import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogIn extends AppCompatActivity  {
    private FirebaseAuth auth;
    EditText kullaniciAdi;
    EditText sifre;

    Location location;
    double latitude;
    double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();

        Boolean isLocc = chechLocation();

        if(isLocc) {

            if (auth.getCurrentUser() != null) {
                startActivity(new Intent(LogIn.this, ContestClass.class));
                finish();
            }

        }

        setContentView(R.layout.login_layout);

        auth = FirebaseAuth.getInstance();

        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        TextView textView = (TextView)findViewById(R.id.textView);
        TextView uyeOl = (TextView)findViewById(R.id.textView4);

        kullaniciAdi = (EditText)findViewById(R.id.editText);
        sifre = (EditText)findViewById(R.id.editText2);

        Button btnLogin = (Button)findViewById(R.id.button);

        Boolean isLoc = chechLocation();

        if(isLoc){
            imageView.setImageResource(R.drawable.okicon);
            textView.setText("Şu an havalimanındasınız");
        }else{
            imageView.setImageResource(R.drawable.crossicon);
            textView.setText("Şu an havalimanında değilsiniz");
            btnLogin.setClickable(false);
        }



        uyeOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogIn.this, SignUp.class));
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = kullaniciAdi.getText().toString();
                final String password = sifre.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Email adresi giriniz!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Şifre giriniz!", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LogIn.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.

                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        sifre.setError("Şifreniz çok kısa!");
                                    } else {
                                        Toast.makeText(LogIn.this, "Bir sorun oluştu", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(LogIn.this, ContestClass.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });



    }

    public Boolean chechLocation(){
        Boolean isLoc;


        LocationCompare locationCompare = new LocationCompare(getApplicationContext());

        location = locationCompare.getLocation();

        latitude =  location.getLatitude(); //enlem
        longitude = location.getLongitude(); //boylam

        Boolean inLatitude = (latitude<40.997997 && latitude>40.963090);
        Boolean inLongitude = (longitude<28.838373 && longitude>28.797647);



        if(inLatitude && inLongitude){
            isLoc = true;
        }else{
            isLoc = false;
        }

        return isLoc;


    }


}
