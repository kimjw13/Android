package com.project.MyMovie.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.project.MyMovie.R;

public class MovieFragment extends Fragment {
    NavigationDrawerActivity activity;

    ImageView moviePosterImageView;
    TextView movieNameTextView;
    TextView reservationRateTextView;
    TextView gradeTextView;
    Button detailButton;

    int id;
    String image;
    String movieName;
    float reservation_rate;
    int grade;

    public MovieFragment(int id, String image, String movieName, float reservation_rate, int grade) {
        this.id = id;
        this.image = image;
        this.movieName = movieName;
        this.reservation_rate = reservation_rate;
        this.grade = grade;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.movie_fragment, container, false);

        moviePosterImageView = rootView.findViewById(R.id.moviePosterImageView);
        movieNameTextView = rootView.findViewById(R.id.movieNameTextView);
        reservationRateTextView = rootView.findViewById(R.id.reservationRateTextView);
        gradeTextView = rootView.findViewById(R.id.gradeTextView);
        detailButton = rootView.findViewById(R.id.detailButton);

        Glide.with(this).load(image).into(moviePosterImageView);

        movieNameTextView.setText(movieName);
        reservationRateTextView.setText(String.valueOf(reservation_rate) + " %");
        gradeTextView.setText(String.valueOf(grade));

        detailButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                detailButtonOnClick();
            }
        });

        return rootView;

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (NavigationDrawerActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        activity = null;
    }

    public void detailButtonOnClick(){
        activity.onChangeFragment(id, movieName, grade);
        Log.e("detailbutton", "선택된 id:" + id + " 선택된 movieName:" + movieName + "선택된 grade" + grade);
    }



}
