package com.project.MyMovie.comment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.project.MyMovie.R;

public class AllCommentList extends LinearLayout{

    ImageView reviewUserImage;

    TextView reviewUserId;
    TextView reviewTime;
    TextView reviewCommentText;
    TextView reviewRecommendCount;

    RatingBar reviewRatingBar;

    public AllCommentList(Context context) {
        super(context);

        init(context);
    }

    public AllCommentList(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.comment_item, this, true);

        reviewUserImage = findViewById(R.id.reviewUserImage);
        reviewUserId = findViewById(R.id.reviewUserId);
        reviewTime = findViewById(R.id.reviewTime);
        reviewCommentText = findViewById(R.id.reviewCommentText);
        reviewRecommendCount = findViewById(R.id.reviewRecommendCount);
        reviewRatingBar = findViewById(R.id.reviewRatingBar);

    }

    public void setReviewUserId(String name){
        reviewUserId.setText(name);
    }

    public void setReviewTime(String time){
        reviewTime.setText(time);
    }

    public void setReviewCommentText(String comment){
        reviewCommentText.setText(comment);
    }

    public void setRecommendCount(int recommendCnt){
        reviewRecommendCount.setText(String.valueOf(recommendCnt));
    }

    public void setRating(float reviewRating){
        reviewRatingBar.setRating(reviewRating);
    }

}
