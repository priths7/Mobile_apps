package com.example.inter_assingn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class profile extends AppCompatActivity {

    private TextView useri,uidi;
    private Button sigout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.profile_page);
        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
        useri = (TextView) findViewById(R.id.email_id);
        uidi = (TextView) findViewById(R.id.UID_data);
        SharedPreferences sh = getApplicationContext().getSharedPreferences("shared_pref",MODE_PRIVATE);
        String name = sh.getString("User_name","");
        String uid = sh.getString("uid","");
        useri.setText(name);
        uidi.setText(uid);
        sigout = (Button) findViewById(R.id.signout);
        sigout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(profile.this,logout.class));
            }
        });


    }
}
