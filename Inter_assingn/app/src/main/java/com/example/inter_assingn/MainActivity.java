package com.example.inter_assingn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button logi;
    private TextView sigup;
    private EditText email, pass;
    private FirebaseAuth mAuth;
    private String emailid,password,name,uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.Login_page);
        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
        mAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);
        sigup = (TextView) findViewById(R.id.signup);
        logi = (Button) findViewById(R.id.login);
        sigup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,signup.class));
                finish();
            }
        });

        logi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailid = email.getText().toString();
                password = pass.getText().toString();
                mAuth.signInWithEmailAndPassword(emailid,password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    assert user != null;
                                    name = user.getEmail();
                                    uid = user.getUid();
                                    SharedPreferences sh = getSharedPreferences("shared_pref",MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sh.edit();
                                    editor.putString("User_name",name);
                                    editor.putString("uid",uid);
                                    editor.apply();
                                    startActivity(new Intent(MainActivity.this,Phone_veri.class));
                                    finish();
                                }
                                else
                                {
                                    Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });


    }
}
