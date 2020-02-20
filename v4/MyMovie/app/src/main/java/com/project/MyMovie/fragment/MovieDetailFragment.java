package com.project.MyMovie.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.project.MyMovie.R;
import com.project.MyMovie.data.CommentData;
import com.project.MyMovie.data.MovieDetailData;
import com.project.MyMovie.data.MovieInformation;

import java.util.ArrayList;

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
    Button thumbDown

    boolean thumbUpState1 = true;
    boolean thumbDownState2 = true;

    int upCount = 0;
    int downCount = 0;

    ArrayList<CommentData> commentlist;
    ArrayList<MovieInformation> movielist;

    int movieId;
    String movieTitle;

    ListView MyListView;
    TextView WriteCommentTextView;
    Button AllCommentList

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.movie_detail_fragment, container, false);

        Bundle bundle = getArguments();

        if(bundle != null){
            MovieInformation data = bundle.getParcelable();
        }
    }




    public void ThumbsUpOnClick(){

    }

    public void ThumbsDownOnclick(){

    }



}
