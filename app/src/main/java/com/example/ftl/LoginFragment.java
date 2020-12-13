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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginFragment extends Fragment {

    private TextView txtv1;
    private TextView txtv2;

    private EditText editTextUsername;
    private EditText editTextPassword;

    private Button btnLogin;

    private Connection connection;
    private Statement statement;

//    private SQLiteDatabase db;
//    private SmartDvorDatabaseHelper smartDvorDatabaseHelper;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
//        smartDvorDatabaseHelper = new SmartDvorDatabaseHelper(this.requireContext());

        editTextUsername = rootView.findViewById(R.id.et_signin_username);
        editTextPassword = rootView.findViewById(R.id.et_signin_password);

        btnLogin = rootView.findViewById(R.id.btn_signin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new loginUser().execute("");

//                String Username = editTextUsername.getText().toString();
//                String Password = editTextPassword.getText().toString();

//                Intent intent = new Intent(getActivity(), GeneralActivity.class);
//                startActivity(intent);

                //                db = smartDvorDatabaseHelper.getReadableDatabase();
//
//                boolean checkUser = smartDvorDatabaseHelper.checkCorrectUsersAuthentificationData(db, PhoneNumber, Password);
//
//                if (checkUser) {
//                    Intent intent = new Intent(getActivity(), ProfileActivity.class);
//                    startActivity(intent);
//                }
//                else {
//                    Toast.makeText(LoginFragment.this.requireContext(), "You do not have an account yet", Toast.LENGTH_LONG).show();
//                }
            }
        });

//        showData(rootView);

        return rootView;
    }

    public class loginUser extends AsyncTask<String, String, String> {

        String z = null;
        Boolean isSuccess = false;

        @Override
        protected void onPreExecute() {
//            textViewReport.setText("Sending data to database.");
        }

        @Override
        protected void onPostExecute(String s) {
//            if (isSuccess)
//                textViewReport.setText("SUCCESS");
//            else
//                textViewReport.setText("NOT SUCCESS");
        }

        @Override
        protected String doInBackground(String... strings) {
            connection = connectionClass(ConnectionCustom.username.toString(), ConnectionCustom.password.toString(), ConnectionCustom.database.toString(), ConnectionCustom.ip.toString());
            if (connection == null) {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "Check your internet connection.", Toast.LENGTH_LONG).show();
                    }
                });
                z = "On Internet Connection.";
            } else {
                try {
//                    connection = connectionClass(ConnectionCustom.username.toString(), ConnectionCustom.password.toString(), ConnectionCustom.database.toString(), ConnectionCustom.ip.toString());

//                    id++;
                    String sql = "SELECT * FROM flt WHERE username = '"+editTextUsername.getText()+"' AND password = '"+editTextPassword.getText()+"' ";
                    statement = connection.createStatement();
//                    statement.executeUpdate(sql);
                    ResultSet resultSet = statement.executeQuery(sql);

                    if (resultSet.next()) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), "Login SUCCESS", Toast.LENGTH_LONG).show();
                            }
                        });
                        z = "Success";
//                        Toast.makeText(getActivity(), "Login SUCCESS", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getActivity(), GeneralActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    } else {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), "Login is NOT SUCCESS", Toast.LENGTH_LONG).show();
                            }
                        });
//                        Toast.makeText(getActivity(), "Login is NOT SUCCESS", Toast.LENGTH_LONG).show();
                    }

                    isSuccess = true;
                } catch (SQLException sqlException) {
                        sqlException.printStackTrace();
                        Log.e("SQL Exception : ", sqlException.getMessage());
                        isSuccess = false;
//                        z = sqlException.getMessage();
                }

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

//    private void showData(@NonNull View view) {
//
//        Button btnShow = view.findViewById(R.id.btn_show);
//
//        txtv1 = view.findViewById(R.id.txtPhoneNumber);
//        txtv2 = view.findViewById(R.id.txtPassword);
//
//        btnShow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    //Код чтения данных из базы
//                    SQLiteDatabase db = smartDvorDatabaseHelper.getReadableDatabase();
//                    String product = "";
//                    String product1 = "";
//                    Cursor cursor = db.query("CLIENTS", new String[]{"_id", "phoneNumber", "password", "street", "houseNumber", "apartNumber"}, "_id = ?", new String[]{Integer.toString(1)}, null, null, null);
//                    cursor.moveToFirst();
//                    while (!cursor.isAfterLast()) {
//                        product += cursor.getString(1) + " | ";
//                        product1 += cursor.getString(2) + " | ";
//                        txtv1.setText(product);
//                        txtv2.setText(product1);
//                        cursor.moveToNext();
//                    }
//                    cursor.close();
//                } catch (SQLiteException e) {
//                    Toast.makeText(LoginFragment.this.requireContext(), "Database unavailable", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }


//    public boolean validate() {
//        boolean valid = false;
//
//        String phoneNumber = editTextPhoneNumber.getText().toString();
//        String password = editTextPassword.getText().toString();
//
//        if (!Patterns.PHONE.matcher(phoneNumber).matches()) {
//            valid = false;
//            textInputLayoutPhoneNumber.setError("Please enter valid email.");
//        } else {
//            valid = true;
//            textInputLayoutPhoneNumber.setError(null);
//        }
//
//        if (password.isEmpty()) {
//            valid = false;
//            textInputLayoutPassword.setError("Enter valid password");
//        } else {
//            if (password.length() > 5) {
//                valid = true;
//                textInputLayoutPassword.setError(null);
//            } else {
//                valid = false;
//                textInputLayoutPassword.setError("Password should be more then ");
//            }
//        }
//
//        return valid;
//    }

}