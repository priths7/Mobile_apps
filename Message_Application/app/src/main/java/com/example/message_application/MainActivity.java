package com.example.message_application;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText Mobileno,Message;
    private Button Submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Mobileno = (EditText) findViewById(R.id.edittext);
        Message = (EditText) findViewById(R.id.edittext1);
        Submit = (Button) findViewById(R.id.Submit);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String no = Mobileno.getText().toString();
                String msg = Message.getText().toString();

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                PendingIntent pi = PendingIntent.getActivity(getApplicationContext(),0,intent,0);

                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(no, null,msg,pi,null);
                Toast.makeText(getApplicationContext(),"Message Sent Successfully",Toast.LENGTH_LONG).show();
            }
        });
    }
}
