package com.project.MyMovie;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CommentWrite extends AppCompatActivity{

    Button save;
    Button cancel;

    @Override
    public void onCreate(Bundle savedInstatnce){
        super.onCreate(savedInstatnce);
        setContentView(R.layout.write_comment);

        save = findViewById(R.id.save);
        cancel = findViewById(R.id.cancel);

        save.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                doSave();
            }
        });
        cancel.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void doSave(){
        Toast.makeText(this.getApplicationContext(), "why", Toast.LENGTH_SHORT).show();
    }


}
