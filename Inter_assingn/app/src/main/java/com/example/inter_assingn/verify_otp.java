package com.example.inter_assingn;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class verify_otp extends AppCompatActivity {

    private EditText otp;
    private Button sub;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_veri);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.otp_page);
        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
        otp = (EditText) findViewById(R.id.veric);
        sub = (Button) findViewById(R.id.submit);
        mAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        String phone = intent.getStringExtra("mobile");
        sendOtp(phone);

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = otp.getText().toString();
                if(code.isEmpty() || code.length() < 6)
                {
                    otp.setText("Enter valid code");
                    otp.requestFocus();
                    return;
                }

                veriyOtp(code);
            }
        });



    }
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();

            if(code != null)
            {
                otp.setText(code);
                veriyOtp(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(verify_otp.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken){
            super.onCodeSent(s, forceResendingToken);
            mVerificationId = s;
            mResendToken = forceResendingToken;
        }
    };

    private void sendOtp(String phon)
    {
        PhoneAuthProvider.getInstance().verifyPhoneNumber("+91"+phon,60, TimeUnit.SECONDS, TaskExecutors.MAIN_THREAD,mCallbacks);
    }

    private void veriyOtp(String otp)
    {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId,otp);
        signinwithphonecred(credential);
    }

    private void signinwithphonecred(PhoneAuthCredential credential) {
    mAuth.signInWithCredential(credential).addOnCompleteListener(verify_otp.this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if(task.isSuccessful()){
                Intent intent = new Intent(verify_otp.this,profile.class);
                startActivity(intent);
                finish();
            }
            else
            {
            String message = "Somthing is wrong, we will fix it soon...";

            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                message = "Invalid code entered...";
            }

            Snackbar snackbar = Snackbar.make(findViewById(R.id.parent), message, Snackbar.LENGTH_LONG);

            snackbar.setAction("Dismiss", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            snackbar.show();
        }
        }
    });

    }

}
