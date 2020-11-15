package com.example.ftl;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dvor.SmartDvorDatabaseHelper;


public class RegisterFragment extends Fragment {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextFirstName;
    private EditText editTextLastName;
//    private EditText editTextApartment;

    private Button btnRegister;

    private SmartDvorDatabaseHelper smartDvorDatabaseHelper;
    private SQLiteDatabase db;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);
//        smartDvorDatabaseHelper = new SmartDvorDatabaseHelper(this.requireContext());

        editTextUsername = rootView.findViewById(R.id.et_username);
        editTextPassword = rootView.findViewById(R.id.et_password);
        editTextFirstName = rootView.findViewById(R.id.et_firstname);
        editTextLastName = rootView.findViewById(R.id.et_lastname);
//        editTextApartment = rootView.findViewById(R.id.et_apartment);

        btnRegister = rootView.findViewById(R.id.btn_singup);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Firstname = editTextFirstName.getText().toString();
                String LastName = editTextLastName.getText().toString();
                String Username = editTextUsername.getText().toString();
                String Password = editTextPassword.getText().toString();

                Intent intent = new Intent(getActivity(), GeneralActivity.class);
                startActivity(intent);
                //                String Apartment = editTextApartment.getText().toString();

//                db = smartDvorDatabaseHelper.getWritableDatabase();

//                boolean checkUser = smartDvorDatabaseHelper.checkCorrectUsersAuthentificationData(db, PhoneNumber, Password);
//
//                if (checkUser) {
//                    Toast.makeText(RegisterFragment.this.requireContext(), "You are already sign up", Toast.LENGTH_LONG).show();
//                }
//                else {
////                    Toast.makeText(RegisterFragment.this.requireContext(), "You do not have an account yet", Toast.LENGTH_LONG).show();
//
//                    boolean insertData = smartDvorDatabaseHelper.insertClientsData(db, PhoneNumber, Password, Street, HouseNumber, "");
//
//                    if (insertData) {
//                        Toast.makeText(RegisterFragment.this.requireContext(), "Success sign up", Toast.LENGTH_LONG).show();
//
//                        Intent intent = new Intent(getActivity(), ProfileActivity.class);
//                        startActivity(intent);
//                    }
//                    else {
//                        Toast.makeText(RegisterFragment.this.requireContext(), "Wrong sign up", Toast.LENGTH_LONG).show();
//                    }
//                }
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }

}