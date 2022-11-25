package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    EditText name, surname, email, phone, province, city, password, confirm_password;
    Button registerBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        DBConnection database;

        name = (EditText) findViewById(R.id.textPersonName);
        surname = (EditText) findViewById(R.id.textPersonSurname);
        email = (EditText) findViewById(R.id.textEmail);
        phone = (EditText) findViewById(R.id.textPhone);
        province = (EditText) findViewById(R.id.textProvince);
        city = (EditText) findViewById(R.id.textCity);
        password = (EditText) findViewById(R.id.textPassword);
        confirm_password = (EditText) findViewById(R.id.textConfirmPassword);

        registerBtn = (Button) findViewById(R.id.registrationButton);

        database = new DBConnection(this);

        registerBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                String nam = name.getText().toString();
                String surnam = surname.getText().toString();
                String mail = email.getText().toString();
                String phon = phone.getText().toString();
                String provinc = province.getText().toString();
                String cit = city.getText().toString();
                String pass = password.getText().toString();
                String conf_pass = confirm_password.getText().toString();

                if (nam.equals("") || surnam.equals("") || mail.equals("") ||
                        phon.equals("") || provinc.equals("") || cit.equals("") ||
                        pass.equals("") || conf_pass.equals("")) {
                    Toast.makeText(RegistrationActivity.this, "Enter all fields.", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pass.equals(conf_pass)){
                        boolean usernam = database.checkUsername(mail);
                        if(usernam == false){
                            boolean data = database.insertData(nam, surnam, mail, phon, provinc, cit, pass);
                            if(data == true){
                                Toast.makeText(RegistrationActivity.this, "Registration succeed", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegistrationActivity.this, HomePage.class);
                                startActivity(intent); //translates to home page

                                name.getText().clear();
                                surname.getText().clear();
                                email.getText().clear();
                                phone.getText().clear();
                                province.getText().clear();
                                city.getText().clear();
                                password.getText().clear();
                                confirm_password.getText().clear();

                            }
                            else{
                                Toast.makeText(RegistrationActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(RegistrationActivity.this, "User already exist", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegistrationActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

    }
}
