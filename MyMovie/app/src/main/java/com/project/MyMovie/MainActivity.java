package com.project.MyMovie;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean btnState1 = true;
    boolean btnState2 = true;

    TextView txt10;
    TextView txt11;
    Button btn2;
    Button btn3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt10 = findViewById(R.id.textView10);
        txt11 = findViewById(R.id.textView11);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);

        btn2.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                ThumbsUpOnClick();
            }
        });
        btn3.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                ThumbsDownOnclick();
            }
        });

    }

    public void ThumbsUpOnClick(){
        if(btnState1) {
            if (!btnState2) {
                txt11.setText("33");
                btn3.setBackgroundResource(R.drawable.thumbs_down_selector);
                btnState2 = !btnState2;
            }
            txt10.setText("154");
            btn2.setBackgroundResource(R.drawable.ic_thumb_up_selected);
        }else{
            txt10.setText("153");
            btn2.setBackgroundResource(R.drawable.thumbs_up_selector);
        }
        btnState1 = !btnState1;
    }

    public void ThumbsDownOnclick(){
        if(btnState2){
            if(!btnState1){
                txt10.setText("153");
                btn2.setBackgroundResource(R.drawable.thumbs_up_selector);
                btnState1 = !btnState1;
            }
            txt11.setText("32");
            btn3.setBackgroundResource(R.drawable.ic_thumb_down_selected);
        }else{
            txt11.setText("33");
            btn3.setBackgroundResource(R.drawable.thumbs_down_selector);

        }
        btnState2 = !btnState2;
    }


}
