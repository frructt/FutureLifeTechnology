package com.example.ftl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GeneralActivity extends AppCompatActivity {

    private Button btnEquipment;
    private Button btnLibrary;
    private Button btnReport;
    private Button btnFeedback;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);

        btnEquipment = findViewById(R.id.btn_equipment);
        btnLibrary = findViewById(R.id.btn_library);
        btnReport = findViewById(R.id.btn_report);
        btnFeedback = findViewById(R.id.btn_feedback);
        btnLogout = findViewById(R.id.btn_logout);

        btnEquipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(GeneralActivity.this, EquipmentActivity.class);
               startActivity(intent);
            }
        });

        btnLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GeneralActivity.this, LibraryActivity.class);
                startActivity(intent);
            }
        });

        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GeneralActivity.this, ReportActivity.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GeneralActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

//    public void setBtnLibrary(Button btnLibrary) {
//        this.btnLibrary = btnLibrary;
//        Intent intent = new Intent(GeneralActivity.this, LibraryActivity.class);
//        startActivity(intent);
//    }
//
//    public void setBtnEquipment(Button btnEquipment) {
//        this.btnEquipment = btnEquipment;
//        Intent intent = new Intent(GeneralActivity.this, EquipmentActivity.class);
//        startActivity(intent);
//    }
//
//    public void setBtnReport(View view) {
//        btnReport = findViewById(R.id.btn_report);
//        Intent intent = new Intent(GeneralActivity.this, ReportActivity.class);
//        startActivity(intent);
//    }
//
//    public void setBtnLogout(View view) {
//        Intent intent = new Intent(GeneralActivity.this, MainActivity.class);
//        startActivity(intent);
//    }
}