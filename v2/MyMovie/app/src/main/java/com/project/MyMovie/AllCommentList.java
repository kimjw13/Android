package com.project.MyMovie;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class AllCommentList extends LinearLayout {

    TextView txt_v1;
    TextView txt_v2;

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

        txt_v1 = findViewById(R.id.userid);
        txt_v2 = findViewById(R.id.comment);

    }
    public void setName(String name){
        txt_v1.setText(name);

    }

    public void setComment(String comment){
        txt_v2.setText(comment);

    }

}
