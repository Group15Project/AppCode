package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityLogin extends AppCompatActivity {

    EditText email, password;
    TextView register;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.textUsername);
        password = (EditText) findViewById(R.id.textPassword);

        DBConnection database;

        login = (Button) findViewById(R.id.loginButton);
        register = (TextView) findViewById(R.id.register);

        database = new DBConnection(this);

        getSupportActionBar().hide();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String mail = email.getText().toString();
                String pass = password.getText().toString();

                if(email.equals("")|| pass.equals("")){
                    Toast.makeText(MainActivityLogin.this, "Enter all fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkPassword = database.checkPassword(mail,pass);
                    if(checkPassword == true){
                        Toast.makeText(MainActivityLogin.this, "Login successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivityLogin.this, HomePage.class);
                        startActivity(intent); //translates to home page

                        email.getText().clear();
                        password.getText().clear();
                    }
                    else{
                        Toast.makeText(MainActivityLogin.this, "Invalid login details", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityLogin.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });


    }
}
