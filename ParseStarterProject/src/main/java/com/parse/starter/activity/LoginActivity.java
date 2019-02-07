package com.parse.starter.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.starter.R;

public class LoginActivity extends AppCompatActivity {
    private EditText userName;
    private EditText password;
    private Button login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        userName = (EditText) findViewById(R.id.et_login_nameId);
        password = (EditText) findViewById(R.id.et_login_passwordId);
        login = (Button) findViewById(R.id.bt_loginId);

        checkLoggedUser();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = userName.getText().toString();
                String passworde = password.getText().toString();
                 valitedLogin(user, passworde);

            }
        });
    }
    public void openUserRegistration(View view){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
    private void checkLoggedUser (){
        if (ParseUser.getCurrentUser()!= null){//está logado
            openMainActivity();

        }
    }
    private void valitedLogin(String user, String password){
        ParseUser.logInInBackground(user, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e==null){
                    Toast.makeText(LoginActivity.this,"success in user login", Toast.LENGTH_LONG).show();
                    openMainActivity();
                }else {
                    Toast.makeText(LoginActivity.this,"Error in user login - "+e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void openMainActivity(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
