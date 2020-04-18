package com.example.background_color_changer;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ScrollView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        backcolorchanger();
    }

    private CheckBox c1,c2,c3,c4;
    private Button b1;
    private ScrollView lay;
    public void backcolorchanger(){
        c1 = (CheckBox) findViewById(R.id.red);
        c2 = (CheckBox) findViewById(R.id.green);
        c3 = (CheckBox) findViewById(R.id.blue);
        c4 = (CheckBox) findViewById(R.id.white);
        b1 = (Button) findViewById(R.id.button);
        lay = (ScrollView) findViewById(R.id.layout1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer s = new StringBuffer();
                boolean s1 = c1.isChecked();
                boolean s2 = c2.isChecked();
                boolean s3 = c3.isChecked();
                boolean s4 = c4.isChecked();
                int a[]={0,0,0,0};
                lay.setBackgroundColor(Color.WHITE);
                if(s1)
                {
                    lay.setBackgroundColor(Color.RED);
                    a[0]=1;
                }
                if(s2)
                {
                    lay.setBackgroundColor(Color.GREEN);
                    a[1]=1;
                }
                if(s3)
                {
                    lay.setBackgroundColor(Color.BLUE);
                    a[2]=1;
                }
                if(s4)
                {
                    lay.setBackgroundColor(Color.WHITE);
                    a[3]=1;
                }
                if((a[0]+a[1]+a[2]+a[3])>1) {
                    lay.setBackgroundColor(Color.WHITE);
                    s.append("Multiple Options selected");
                }
                    Toast.makeText(MainActivity.this, s.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
