package com.project.MyMovie.comment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.project.MyMovie.R;
import com.project.MyMovie.data.CommentData;
import com.project.MyMovie.data.CommentList;
import com.project.MyMovie.data.ResponseInfo;
import com.project.MyMovie.util.AppHelper;

import org.w3c.dom.Comment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class ShowCommentList extends AppCompatActivity{
    private static final int successCode = 200;

    TextView showName;
    ImageView showGrade;
    RatingBar showRatingBar;
    TextView showScore;

    TextView write;

    ListView showCommentList;

    ArrayList<CommentData> commentList;

    int grade;
    int id;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_comment_list);

        showName = findViewById(R.id.showName);
        showGrade = findViewById(R.id.showGrade);
        showRatingBar = findViewById(R.id.showRatingBar);
        showScore = findViewById(R.id.showScore);

        showCommentList = findViewById(R.id.showCommentList);

        write = findViewById(R.id.write);

        Intent passedIntent = getIntent();
        if(passedIntent != null){
            id = passedIntent.getIntExtra(getString(R.string.movieID), 0);
            title = passedIntent.getStringExtra(getString(R.string.movieTitle));
            grade = passedIntent.getIntExtra(getString(R.string.movieGrade),0);

            if(grade == 12)
                showGrade.setImageResource(R.drawable.ic_12);
            else if(grade == 15)
                showGrade.setImageResource(R.drawable.ic_15);
            else if(grade == 19)
                showGrade.setImageResource(R.drawable.ic_19);

            showName.setText(title);

            requestCommentList();
        }

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickMethod();
            }
        });
    }

    class MovieAdapter extends BaseAdapter {
        ArrayList<CommentData> items = new ArrayList<CommentData>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(CommentData item){
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            AllCommentList view = new AllCommentList(getApplicationContext());

            CommentData item = items.get(position);
            view.setReviewUserId(item.getWriter());
            view.setReviewTime(item.getTime());
            view.setReviewCommentText(item.getContents());
            view.setRecommendCount(item.getRecommend());

            return view;
        }
    }

    public void requestCommentList(){

        String url = "http://" + AppHelper.host + ":" + AppHelper.port + "/movie/readCommentList";
        url += "?" + "id=" + id;

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        requestCommentListResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("showCommentList Error",error.getMessage());
                    }
                }
        );

        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);
    }

    public void requestCommentListResponse(String response){
        commentList = new ArrayList<>();

        Gson gson = new Gson();

        ResponseInfo info = gson.fromJson(response, ResponseInfo.class);
        if(info.code == successCode){
            CommentList list = gson.fromJson(response, CommentList.class);
            for(CommentData item : list.result){
                Collections.addAll(commentList, item);
            }

            final MovieAdapter adapter = new MovieAdapter();
            for(CommentData item : commentList){
                adapter.addItem(item);
            }
            showCommentList.setAdapter(adapter);
        }
    }


    public void onClickMethod(){
        String movieNameText = showName.getText().toString();
        Intent clickIntent = new Intent(getApplicationContext(), CommentWrite.class);
        clickIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        clickIntent.putExtra(getString(R.string.movieGrade), grade);
        clickIntent.putExtra(getString(R.string.movieID), movieNameText);
        startActivityForResult(clickIntent, successCode);
    }



}
