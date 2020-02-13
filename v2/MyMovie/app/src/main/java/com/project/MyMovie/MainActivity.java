package com.project.MyMovie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.project.MyMovie.data.CommentData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    boolean btnState1 = true;
    boolean btnState2 = true;

    TextView txt10;
    TextView txt11;
    TextView txt_write;

    Button btn2;
    Button btn3;
    Button btn_acl;

    ListView lv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt10 = findViewById(R.id.textView10);
        txt11 = findViewById(R.id.textView11);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn_acl = findViewById(R.id.AllCommentList);
        txt_write = findViewById(R.id.writeCommentTextView);

        btn2.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                ThumbsUpOnClick();
            }
        });
        btn3.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                ThumbsDownOnclick();
            }
        });
        btn_acl.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                ShowCommentList();
            }
        });
        txt_write.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                WriteComment();
            }
        });

        //      ListView area
        lv = findViewById(R.id.MyListView);

        final CommentAdapter adapter = new CommentAdapter();
        adapter.addItem(new CommentData("Fire_Pistol","영화 재밌네요"));
        adapter.addItem(new CommentData("Black_Beard","영화 재밌네요"));
        adapter.addItem(new CommentData("White_Beard","영화 재밌네요"));
        adapter.addItem(new CommentData("StrawHat","영화 재밌네요"));

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CommentData item = (CommentData) adapter.getItem(position);
                Toast.makeText(MainActivity.this.getApplicationContext(), "good", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void ThumbsUpOnClick(){
        if(btnState1) {
            if (!btnState2) {
                txt11.setText("33");
                btn3.setBackgroundResource(R.drawable.thumbs_down_selector);
                btnState2 = !btnState2;
            }
            txt10.setText("154");
            btn2.setBackgroundResource(R.drawable.ic_thumb_up_selected);
        }else{
            txt10.setText("153");
            btn2.setBackgroundResource(R.drawable.thumbs_up_selector);
        }
        btnState1 = !btnState1;
    }

    public void ThumbsDownOnclick(){
        if(btnState2){
            if(!btnState1){
                txt10.setText("153");
                btn2.setBackgroundResource(R.drawable.thumbs_up_selector);
                btnState1 = !btnState1;
            }
            txt11.setText("32");
            btn3.setBackgroundResource(R.drawable.ic_thumb_down_selected);
        }else{
            txt11.setText("33");
            btn3.setBackgroundResource(R.drawable.thumbs_down_selector);

        }
        btnState2 = !btnState2;
    }

    public void ShowCommentList(){
        Intent intent = new Intent(getApplicationContext(), ShowCommentList.class);
        startActivityForResult(intent, 101);

    }

    public void WriteComment(){
        Intent intent = new Intent(getApplicationContext(), CommentWrite.class);
        startActivityForResult(intent, 101);
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
