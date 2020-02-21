package com.project.MyMovie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.project.MyMovie.R;
import com.project.MyMovie.comment.AllCommentList;
import com.project.MyMovie.data.CommentData;
import com.project.MyMovie.data.CommentList;
import com.project.MyMovie.data.MovieDetailData;
import com.project.MyMovie.data.MovieDetailList;
import com.project.MyMovie.data.MovieInformation;
import com.project.MyMovie.data.ResponseInfo;
import com.project.MyMovie.util.AppHelper;

import java.util.ArrayList;
import java.util.Collections;

public class MovieDetailFragment extends Fragment{

    NavigationDrawerActivity activity;

    private static final int WRITE_COMMENT_CODE = 101;
    private static final int SEE_ALL_COMMENT_CODE = 102;
    private static final int SUCCESS_CODE = 200;

    ImageView movieImage;
    TextView movieName;
    ImageView movieGrade;
    TextView runningDate;
    TextView genre;
    TextView duration;
    TextView thumbUpText;
    TextView thumbDownText;
    TextView reservationRank;
    TextView reservationRate;
    TextView ratingScore;
    RatingBar ratingBar;
    TextView audience;
    TextView synopsis;
    TextView director;
    TextView actor;

    Button thumbUp;
    Button thumbDown;

    boolean thumbUpState = true;
    boolean thumbDownState = true;

    int upCount = 0;
    int downCount = 0;

    ArrayList<CommentData> commentList;
    ArrayList<MovieDetailData> movieList;

    int movieId;
    String movieTitle;
    int grade;

    ListView MyListView;
    TextView writeCommentTextView;
    Button allCommentList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.movie_detail_fragment, container, false);

        Bundle bundle = getArguments();
        if(bundle != null) {
            MovieInformation data = bundle.getParcelable(getString(R.string.movieHeader));
            movieId = data.id;
            movieTitle = data.title;
            grade = data.grade;
        }

        if(movieList == null) {
            movieList = new ArrayList<>();
        }

        movieImage = rootView.findViewById(R.id.movieImage);
        movieName = rootView.findViewById(R.id.movieName);
        movieGrade = rootView.findViewById(R.id.movieGrade);
        runningDate = rootView.findViewById(R.id.runningDate);
        genre = rootView.findViewById(R.id.genre);
        duration = rootView.findViewById(R.id.duration);
        thumbUpText = rootView.findViewById(R.id.thumbUpText);
        thumbDownText = rootView.findViewById(R.id.thumbDownText);
        reservationRank = rootView.findViewById(R.id.reservationRank);
        reservationRate = rootView.findViewById(R.id.reservationRate);
        ratingScore = rootView.findViewById(R.id.ratingScore);
        ratingBar = rootView.findViewById(R.id.ratingBar);
        audience = rootView.findViewById(R.id.audience);
        synopsis = rootView.findViewById(R.id.synopsis);
        director = rootView.findViewById(R.id.director);
        actor = rootView.findViewById(R.id.actor);

        thumbUp = rootView.findViewById(R.id.thumbUp);
        thumbDown = rootView.findViewById(R.id.thumbDown);

        writeCommentTextView = rootView.findViewById(R.id.writeCommentTextView);
        allCommentList = rootView.findViewById(R.id.AllCommentList);

        requestSelectedMovieInformation();
        requestCommentList();

        writeCommentTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                WriteComment();
            }
        });
        allCommentList.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                ShowCommentList();
            }
        });
        thumbUp.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                ThumbsUpOnClick();
            }
        });
        thumbDown.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                ThumbsDownOnclick();
            }
        });



        return rootView;
    }

    public void requestSelectedMovieInformation(){

        String url = "http://" + AppHelper.host + ":" + AppHelper.port + "/movie/readMovie";
        movieTitle = movieTitle.replace(" ", "%20");
        url += "?" + "id=" + movieId + "&name=" + movieTitle;


        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        requestSelectedMovieInfoResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(getString(R.string.movieDetailFragment),error.getMessage());
                    }
                }
        );

        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);
    }

    public void requestSelectedMovieInfoResponse(String response){
        Gson gson = new Gson();

        ResponseInfo info = gson.fromJson(response, ResponseInfo.class);
        if(info.code == SUCCESS_CODE){
            MovieDetailList list = gson.fromJson(response, MovieDetailList.class);
            for(MovieDetailData item : list.result){
                Collections.addAll(movieList,item);
            }
        }

        Glide.with(this).load(movieList.get(0).image).into(movieImage);
        if(movieList.get(0).grade == 12)
            movieGrade.setImageResource(R.drawable.ic_12);
        else if(movieList.get(0).grade == 15)
            movieGrade.setImageResource(R.drawable.ic_15);
        else if(movieList.get(0).grade == 19)
            movieGrade.setImageResource(R.drawable.ic_19);

        upCount = movieList.get(0).like;
        downCount = movieList.get(0).dislike;

        movieName.setText(movieList.get(0).title);
        thumbUpText.setText(String.valueOf(movieList.get(0).like));
        thumbDownText.setText(String.valueOf(movieList.get(0).dislike));
        runningDate.setText(movieList.get(0).date);
        genre.setText(movieList.get(0).genre);
        duration.setText(String.valueOf(movieList.get(0).duration));
        reservationRank.setText(String.valueOf(movieList.get(0).reservation_grade));
        reservationRate.setText(String.valueOf(movieList.get(0).reservation_rate));
        ratingScore.setText(String.valueOf(movieList.get(0).audience_rating));
        ratingBar.setRating(movieList.get(0).audience_rating);
        audience.setText(String.valueOf(movieList.get(0).audience));
        synopsis.setText(movieList.get(0).synopsis);
        director.setText(movieList.get(0).director);
        actor.setText(movieList.get(0).actor);
    }

    public void requestCommentList(){

        String url = "http://" + AppHelper.host + ":" + AppHelper.port + "/movie/readCommentList";
        movieTitle = movieTitle.replace(" ", "%20");
        url += "?" + "id=" + movieId + "&limit=" + 2;


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
                        Log.e(getString(R.string.movieDetailFragment),error.getMessage());
                    }
                }
        );

        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);
    }

    public void requestCommentListResponse(String response){
        Gson gson = new Gson();

        ResponseInfo info = gson.fromJson(response, ResponseInfo.class);
        if(info.code == SUCCESS_CODE){
            CommentList list = gson.fromJson(response, CommentList.class);
            for(CommentData item : list.result){
                commentList.add(item);
            }

            final MovieAdapter adapter = new MovieAdapter();
            for(CommentData item : commentList){
                adapter.addItem(item);
            }
            MyListView.setAdapter(adapter);
        }
    }

    class MovieAdapter extends BaseAdapter {
        ArrayList<CommentData> items = new ArrayList<>();

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
            AllCommentList view = new AllCommentList(activity);

            CommentData item = items.get(position);
            view.setReviewUserId(item.getWriter());
            view.setReviewTime(item.getTime());
            view.setRecommendCount(item.getRecommend());
            view.setReviewCommentText(item.getContents());

            return view;
        }
    }



    public void WriteComment(){
        String movieNameText = movieName.getText().toString();

        Intent intent = new Intent(activity, com.project.MyMovie.comment.CommentWrite.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra(getString(R.string.movieGrade), grade);
        intent.putExtra(getString(R.string.movieID), movieNameText);
        startActivityForResult(intent, WRITE_COMMENT_CODE);

    }

    public void ShowCommentList(){
        Intent intent = new Intent(activity, com.project.MyMovie.comment.ShowCommentList.class);
        intent.putExtra(getString(R.string.movieID), movieId);
        movieTitle = movieTitle.replace("%20", " ");
        intent.putExtra(getString(R.string.movieTitle), movieTitle);
        intent.putExtra(getString(R.string.movieGrade), grade);
        startActivityForResult(intent, SEE_ALL_COMMENT_CODE);
    }




    public void ThumbsUpOnClick(){
        if(thumbUpState) {
            if (!thumbDownState) {
                downCount++;
                thumbDownText.setText(String.valueOf(downCount));
                thumbDown.setBackgroundResource(R.drawable.thumbs_down_selector);
                thumbDownState = !thumbDownState;
            }
            upCount++;
            thumbUpText.setText(String.valueOf(upCount));
            thumbUp.setBackgroundResource(R.drawable.ic_thumb_up_selected);
        }else{
            upCount--;
            thumbUpText.setText(String.valueOf(upCount));
            thumbUp.setBackgroundResource(R.drawable.thumbs_up_selector);
        }
        thumbUpState = !thumbUpState;

    }

    public void ThumbsDownOnclick(){
        if(thumbDownState){
            if(!thumbUpState){
                upCount--;
                thumbUpText.setText(String.valueOf(upCount));
                thumbUp.setBackgroundResource(R.drawable.thumbs_up_selector);
                thumbUpState = !thumbUpState;
            }
            downCount--;
            thumbDownText.setText(String.valueOf(downCount));
            thumbDown.setBackgroundResource(R.drawable.ic_thumb_down_selected);
        }else{
            downCount++;
            thumbDownText.setText(String.valueOf(downCount));
            thumbDown.setBackgroundResource(R.drawable.thumbs_down_selector);

        }
        thumbDownState = !thumbDownState;

    }



}
