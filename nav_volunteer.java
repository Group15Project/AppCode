package com.example.project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;


public class nav_volunteer extends Fragment {

    EditText fname, email, phone, address;
    Button sign;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nav_volunteer, container, false);

        fname = (EditText) view.findViewById(R.id.fullPersonName_volunteer);
        email = (EditText) view.findViewById(R.id.textEmail_volunteer);
        phone = (EditText) view.findViewById(R.id.textPhone_volunteer);
        address = (EditText) view.findViewById(R.id.textAddress_volunteer);

        sign = (Button) view.findViewById(R.id.signInButton);

        DBConnection VolunteerDatabase;
        VolunteerDatabase = new DBConnection(this.getActivity());

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fnam = fname.getText().toString();
                String mail = email.getText().toString();
                String phon = phone.getText().toString();
                String addres = address.getText().toString();

                VolunteerDatabase.insertDataVolunteer(fnam, mail,
                        phon, addres);


                if (fnam.equals("") || mail.equals("") || phon.equals("") ||addres.equals(""))
                {
                    Toast.makeText(getContext(), "Fields empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();

                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.host_fragments, new volunteeredFragment());
                        fragmentTransaction.commit();

                        fname.getText().clear();
                        email.getText().clear();
                        phone.getText().clear();
                        address.getText().clear();

                    }
            }
        });

        return view;

    }
}
