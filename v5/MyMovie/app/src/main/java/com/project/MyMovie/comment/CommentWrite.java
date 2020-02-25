package com.project.MyMovie.comment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.project.MyMovie.R;
import com.project.MyMovie.util.AppHelper;

import java.util.HashMap;
import java.util.Map;

public class CommentWrite extends AppCompatActivity{

    TextView writeName;
    ImageView writeGrade;
    RatingBar writeRatingBar;
    EditText input;

    Button save;
    Button cancel;

    String movieName;
    int movieId;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_comment);

        writeName = findViewById(R.id.writeName);
        writeGrade = findViewById(R.id.writeGrade);
        writeRatingBar = findViewById(R.id.writeRatingBar);
        input = findViewById(R.id.input);

        save = findViewById(R.id.save);
        cancel = findViewById(R.id.cancel);

        Intent passedIntent = getIntent();
        movieName = passedIntent.getStringExtra(getString(R.string.movieTitle));
        movieId = passedIntent.getIntExtra(getString(R.string.movieID),0);
        writeName.setText(movieName);

        int grade = passedIntent.getIntExtra(getString(R.string.movieGrade),0);

        if(grade == 12)
            writeGrade.setImageResource(R.drawable.ic_12);
        else if(grade == 15)
            writeGrade.setImageResource(R.drawable.ic_15);
        else if(grade == 19)
            writeGrade.setImageResource(R.drawable.ic_19);


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
        String url = "http://" + AppHelper.host + ":" + AppHelper.port + "/movie/createComment";

        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(CommentWrite.this, response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CommentWrite.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            ){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(movieId));
                params.put("writer", "firePistol");
                params.put("rating", String.valueOf(writeRatingBar.getRating()));
                params.put("contents", input.getText().toString());

                return params;
            }
        };

        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

}
