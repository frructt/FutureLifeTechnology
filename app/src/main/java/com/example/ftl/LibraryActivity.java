package com.example.ftl;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Connection.ConnectionCustom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class LibraryActivity extends AppCompatActivity {

    private EditText editTextDeviceName;
    private EditText editTextModel;
    private EditText editTextSerialNumber;
    private EditText editTextFLTNumber;

    private Button btnLogout;
    private Button btnSave;

    Connection connection;
    Statement statement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        editTextDeviceName = findViewById(R.id.et_name);
        editTextModel = findViewById(R.id.et_model);
        editTextSerialNumber = findViewById(R.id.et_serial_number);
        editTextFLTNumber = findViewById(R.id.et_ftl_number);

        btnLogout = findViewById(R.id.btn_logout);
        btnSave = findViewById(R.id.btn_save);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LibraryActivity.this, GeneralActivity.class);
                startActivity(intent);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new userDevices().execute("");
                Intent intent = new Intent(LibraryActivity.this, GeneralActivity.class);

                editTextDeviceName.setText("");
                editTextModel.setText("");
                editTextSerialNumber.setText("");
                editTextFLTNumber.setText("");

                startActivity(intent);
            }
        });
    }

    public class userDevices extends AsyncTask<String, String, String> {

        String z = "";
        Boolean isSuccess = false;

        @Override
        protected void onPreExecute() {
            Toast.makeText(LibraryActivity.this, "Sending data to database.", Toast.LENGTH_LONG).show();
//            textViewReport.setText("Sending data to database.");
        }

        @Override
        protected void onPostExecute(String s) {
            if (isSuccess) {
                Toast.makeText(LibraryActivity.this, "SUCCESS", Toast.LENGTH_LONG).show();
//                textViewReport.setText("SUCCESS");
            }
            else {
                Toast.makeText(LibraryActivity.this, "SUCCESS", Toast.LENGTH_LONG).show();
//                textViewReport.setText("NOT SUCCESS");
            }
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                connection = connectionClass(ConnectionCustom.username.toString(), ConnectionCustom.password.toString(), ConnectionCustom.database.toString(), ConnectionCustom.ip.toString());
                if (connection == null) {
                    z = "Check your internet connection.";
                } else {
                    int id = 4;
                    String sql = "INSERT INTO flt (id, devicename, model, serialnumber, fltnumber) VALUES ('"+id+"', '"+editTextDeviceName.getText()+"', '"+editTextModel.getText()+"', '"+editTextSerialNumber.getText()+"', '"+editTextFLTNumber.getText()+"');";
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