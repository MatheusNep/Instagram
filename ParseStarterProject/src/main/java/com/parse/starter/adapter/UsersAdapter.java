package com.parse.starter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.starter.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends ArrayAdapter<ParseUser> {
    private Context context;
    private ArrayList<ParseUser> users;

    public UsersAdapter(Context c,  ArrayList<ParseUser> objects) {
        super(c, 0, objects);
        this.context = c;
        this.users = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.users_list,parent, false);
        }

        TextView username = (TextView) view.findViewById(R.id.tv_user_name);

        ParseUser parseUser = users.get(position);
        username.setText(parseUser.getUsername());
        /*if (posts.size()>0){
            ImageView imageView = (ImageView) view.findViewById(R.id.im_post_list);
            ParseObject parseObject = posts.get(position);

            //parseObject.getParseFile("image");
            Picasso.with(context).load(parseObject.getParseFile("image").getUrl()).fit().into(imageView);
        }*/
        return view;
    }
}