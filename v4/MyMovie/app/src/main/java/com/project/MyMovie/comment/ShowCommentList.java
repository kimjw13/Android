package com.project.MyMovie.comment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.project.MyMovie.R;
import com.project.MyMovie.data.CommentData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ShowCommentList extends AppCompatActivity{

    RatingBar show_rating_bar;

    TextView write;

    ListView lv;

    int selected_moive_id;
    String selected_movie_title;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_comment_list);

        lv = findViewById(R.id.CommentList);

        write = findViewById(R.id.write);
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CommentWrite.class);
                startActivityForResult(intent, 101);
            }
        });
    }



}
