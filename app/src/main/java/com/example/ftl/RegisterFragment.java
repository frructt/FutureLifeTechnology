package com.example.ftl;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Connection.ConnectionCustom;
import com.example.dvor.SmartDvorDatabaseHelper;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class RegisterFragment extends Fragment {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextFirstName;
    private EditText editTextLastName;
//    private EditText editTextApartment;
    private TextView textViewReport;
    private static int id = 0;

    private Button btnRegister;

    private SmartDvorDatabaseHelper smartDvorDatabaseHelper;
    private SQLiteDatabase db;

    Connection connection;
    Statement statement;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_register, container, false);
//        smartDvorDatabaseHelper = new SmartDvorDatabaseHelper(this.requireContext());

        editTextUsername = rootView.findViewById(R.id.et_username);
        editTextPassword = rootView.findViewById(R.id.et_password);
        editTextFirstName = rootView.findViewById(R.id.et_firstname);
        editTextLastName = rootView.findViewById(R.id.et_lastname);
//        editTextApartment = rootView.findViewById(R.id.et_apartment);
        textViewReport = rootView.findViewById(R.id.textViewReport);

        btnRegister = rootView.findViewById(R.id.btn_singup);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String Firstname = editTextFirstName.getText().toString();
//                String LastName = editTextLastName.getText().toString();
//                String Username = editTextUsername.getText().toString();
//                String Password = editTextPassword.getText().toString();

                new registerUser().execute("");

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

    public class registerUser extends AsyncTask<String, String, String> {

        String z = "";
        Boolean isSuccess = false;

        @Override
        protected void onPreExecute() {
            textViewReport.setText("Sending data to database.");
        }

        @Override
        protected void onPostExecute(String s) {
            if (isSuccess)
                textViewReport.setText("SUCCESS");
            else
                textViewReport.setText("NOT SUCCESS");
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                connection = connectionClass(ConnectionCustom.username.toString(), ConnectionCustom.password.toString(), ConnectionCustom.database.toString(), ConnectionCustom.ip.toString());
                if (connection == null) {
                    z = "Check your internet connection.";
                } else {
                    id=3;
                    String sql = "INSERT INTO flt (firstname, lastname, username, password) VALUES ('"+id+"', '"+editTextFirstName.getText()+"', '"+editTextLastName.getText()+"', '"+editTextUsername.getText()+"', '"+editTextPassword.getText()+"');";
                    statement = connection.createStatement();
                    statement.executeUpdate(sql);
                    isSuccess = true;
                }

            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                Log.e("SQL Exception : ", sqlException.getMessage());
                isSuccess = false;
                z = sqlException.getMessage();
            }
            return z;
        }
    }

    @SuppressLint("NewApi")
    public Connection connectionClass(String username, String password, String database, String server) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        Connection connection = null;
        String connectionURL = null;

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connectionURL = "jdbc:jtds:sqlserver://" + server + '/' + database + ";user=" + username + ";password=" + password + ";";
            connection = DriverManager.getConnection(connectionURL);

//            textView.setText("SUCCESS");
        } catch (ClassNotFoundException classNotFoundException) {
//            classNotFoundException.printStackTrace();
//            textView.setText("CLASS NOT FOUND EXCEPTION");
            Log.e("CLASS ERROR : ", classNotFoundException.getMessage());
        } catch (SQLException sqlException) {
//            sqlException.printStackTrace();
//            textView.setText("SQL EXCEPTION");
            Log.e("SQL ERROR", sqlException.getMessage());
        }

        return connection;
    }

}