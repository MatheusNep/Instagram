package com.parse.starter.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.parse.starter.R;
import com.parse.starter.useful.ParseError;

public class RegisterActivity extends AppCompatActivity {
    private EditText textUser;
    private EditText textEmail;
    private EditText textPassword;
    private TextView userLogin;

    private Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textUser = (EditText) findViewById(R.id.et_signup_nameId);
        textEmail = (EditText) findViewById(R.id.et_signup_emailId);
        textPassword = (EditText) findViewById(R.id.et_signup_passwordId);
        signUp = (Button) findViewById(R.id.bt_signupId);
        userLogin = (TextView) findViewById(R.id.tv_signup_lloginId);
        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUserLogin();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpUser();
            }
        });
    }
    private void signUpUser(){
        //criar objeto usuário
        ParseUser user = new ParseUser();
        user.setUsername(textUser.getText().toString());
        user.setEmail(textEmail.getText().toString());
        user.setPassword(textPassword.getText().toString());
        //salvar dados do usuário
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){// sucesso ao salvar
                    Toast.makeText(RegisterActivity.this,"success in saving the user", Toast.LENGTH_LONG).show();
                    ParseUser.logOut();
                    openUserLogin();
                }else {//falha ao salvar usuário
                    ParseError parseError = new ParseError();

                    Toast.makeText(RegisterActivity.this,e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void openUserLogin(){
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }



}
