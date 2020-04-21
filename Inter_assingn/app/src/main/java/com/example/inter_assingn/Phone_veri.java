package com.example.inter_assingn;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Phone_veri extends AppCompatActivity {

    private Button sub;
    private EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_number);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.Phone_page);
        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        phone = (EditText) findViewById(R.id.Phone);
        sub = (Button) findViewById(R.id.send);
        Intent intent = getIntent();
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = phone.getText().toString();

                if(mobile.isEmpty() || mobile.length()<10)
                {
                    phone.setError("Enter a valid number");
                    phone.requestFocus();
                    return;
                }

                Intent intent = new Intent(Phone_veri.this,verify_otp.class);
                intent.putExtra("mobile",mobile);


                startActivity(intent);
                finish();
            }
        });


    }
}
