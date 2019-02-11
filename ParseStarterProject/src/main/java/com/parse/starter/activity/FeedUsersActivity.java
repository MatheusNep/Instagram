package com.parse.starter.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.starter.R;
import com.parse.starter.adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

public class FeedUsersActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView listView;
    private String userName;
    private ArrayAdapter<ParseObject> adapter;
    private ArrayList<ParseObject> posts;


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_users);
        Intent intent = getIntent();
        userName = intent.getStringExtra("username");

        toolbar = (Toolbar) findViewById(R.id.tb_feed);
        toolbar.setTitle(userName);
        toolbar.setTitleTextColor(getColor(R.color.black));
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left);
        setSupportActionBar(toolbar);

        posts = new ArrayList<>();
        listView = (ListView) findViewById(R.id.lv_feed);
        adapter = new HomeAdapter(getApplicationContext(),posts);
        listView.setAdapter(adapter);

        getPosts();


    }

    private void getPosts (){
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("image");
        query.whereEqualTo("username", userName);
        query.orderByDescending("createdAt");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e==null){

                    if (objects.size()>0){
                        posts.clear();
                        for (ParseObject parseObject: objects){
                            posts.add(parseObject);
                        }
                        adapter.notifyDataSetChanged();
                    }

                }else{
                    Toast.makeText(getApplicationContext(),"Error while showing feed", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
