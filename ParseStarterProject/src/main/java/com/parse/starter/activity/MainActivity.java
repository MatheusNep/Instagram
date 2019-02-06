/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.parse.starter.R;

import java.util.List;


public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

      /*/cadastro do usuario
      ParseUser usuario = new ParseUser();
      usuario.setUsername("matheussaboia");
      usuario.setEmail("matheussaboia@hotmail.com");
      usuario.setPassword("qweqwe");

      //cadastrar usuario

      usuario.signUpInBackground(new SignUpCallback() {
          @Override
          public void done(ParseException e) {
              if (e == null){
                  Log.i("cadastroUsuario", "Sucesso ao cadastrar usuario - ");
              }else {
                  Log.i("cadastroUsuario", "Erro ao cadastrar usuario - "+ e.getMessage());
              }
          }
      });*/

      ParseUser.logOut();

     /*if (ParseUser.getCurrentUser()!= null){//usuario logado
         Log.i("cadastroUsuario", "Usuário logado");
     }else{
         Log.i("cadastroUsuario", "Usuário não esta logado ");
     }*/

      /*ParseUser.logInInBackground("matheussaboia", "qweqwe", new LogInCallback() {
          @Override
          public void done(ParseUser user, ParseException e) {
              if (e == null){//deu certo
                  Log.i("LoginUsuario", "Login realizado com sucesso!!");
              }else{
                  Log.i("LoginUsuario", "Erro ao efetuar login - "+ e.getMessage());
              }
          }
      });*/

  }

}
