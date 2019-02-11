package com.parse.starter.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.starter.R;
import com.parse.starter.adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private ListView listViewHome;
    private ArrayList<ParseObject> posts;
    private ArrayAdapter<ParseObject> adapter;
    private ParseQuery<ParseObject> parseQuery;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        posts = new ArrayList<>();
        listViewHome = (ListView) view.findViewById(R.id.lv_home);
        adapter = new HomeAdapter(getActivity(), posts);
        listViewHome.setAdapter(adapter);

        getPosts();



        return view;
    }
    private void getPosts(){
        parseQuery = ParseQuery.getQuery("image");
        parseQuery.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
        parseQuery.orderByDescending("createdAt");
        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e==null){
                    if (objects.size()> 0){
                        posts.clear();
                        for (ParseObject parseObject : objects){
                            posts.add(parseObject);
                        }
                        adapter.notifyDataSetChanged();
                    }
                }else {

                }
            }
        });

    }
    public void updatePost (){
        getPosts();
    }

}
