package com.project.MyMovie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.project.MyMovie.data.CommentData;

import java.util.ArrayList;

public class ShowCommentList extends AppCompatActivity{

    TextView write;

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_comment_list);

        lv = findViewById(R.id.CommentList);

        final CommentAdapter adapter = new CommentAdapter();
        adapter.addItem(new CommentData("Fire_Pistol","영화 재밌네요"));
        adapter.addItem(new CommentData("Black_Beard","영화 재밌네요"));
        adapter.addItem(new CommentData("White_Beard","영화 재밌네요"));
        adapter.addItem(new CommentData("StrawHat","영화 재밌네요"));

        lv.setAdapter(adapter);

        write = findViewById(R.id.write);
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CommentWrite.class);
                startActivityForResult(intent, 101);
            }
        });
    }

    class CommentAdapter extends BaseAdapter {

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
        public View getView(int position, View view, ViewGroup viewGroup) {
            AllCommentList temp = new AllCommentList(getApplicationContext());

            CommentData item = items.get(position);

            temp.setName(item.getName());
            temp.setComment(item.getMention());

            return temp;
        }
    }

}
