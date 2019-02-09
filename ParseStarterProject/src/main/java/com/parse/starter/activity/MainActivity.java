/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.parse.starter.R;
import com.parse.starter.adapter.TabsAdapter;
import com.parse.starter.useful.SlidingTabLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private SlidingTabLayout slidingTabLayout;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

      toolbar = (Toolbar) findViewById(R.id.tb_main);
      toolbar.setLogo(R.drawable.instagramlogo);
      setSupportActionBar(toolbar);

      viewPager  = (ViewPager) findViewById(R.id.vp_main);
      slidingTabLayout = (SlidingTabLayout) findViewById(R.id.st_main);

      TabsAdapter tabsAdapter = new TabsAdapter(getSupportFragmentManager(), this);
      viewPager.setAdapter(tabsAdapter);
      slidingTabLayout.setCustomTabView(R.layout.tab_view, R.id.tv_tab);
      slidingTabLayout.setDistributeEvenly(true);
      slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.darkGrey));
      slidingTabLayout.setViewPager(viewPager);




  }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_logout:
                logoutUser();
                return true;
            case R.id.action_settings:
                return true;
            case R.id.action_share:
                sharePhotos();
                return true;
            default:
                return super.onOptionsItemSelected(item);



        }
    }
    private void sharePhotos(){

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //retorno dos dados selecionados
        if(requestCode == 1 && resultCode == RESULT_OK && data != null ){

            //recuperar local dos arquivos
            Uri localImageSelected = data.getData();

            //recuperar a imagem do local selecionado
            try {
                Bitmap image = MediaStore.Images.Media.getBitmap(getContentResolver(),localImageSelected);

                //comprimir a imagem selecionada no formato PNG
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.PNG, 75, stream);

                //criar array de bytes da imagem
                byte[] byteArray = stream.toByteArray();

                //criar um arquivo no formato proprio do parse
                SimpleDateFormat dateFormat = new SimpleDateFormat("ddmmaaaahhmmss");
                String imageName = dateFormat.format(new Date());
                ParseFile parseFile = new ParseFile( imageName +"image.png", byteArray);

                ParseObject parseObject = new ParseObject("image");
                parseObject.put("username", ParseUser.getCurrentUser().getUsername());
                parseObject.put("image", parseFile);

                //salvar dados
                parseObject.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e==null){
                            Toast.makeText(MainActivity.this,"Your image is shared", Toast.LENGTH_LONG).show();

                        }else {
                            Toast.makeText(MainActivity.this,"Error in share image - try again", Toast.LENGTH_LONG).show();
                        }

                    }
                });

            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }

    private void logoutUser(){
        ParseUser.logOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
