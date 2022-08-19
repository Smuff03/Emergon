package com.example.emergon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class patient_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_login);
    }

    public void openActivity(View view) {
        Toast.makeText(this, "opening patient Activity", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, patient.class);
        startActivity(intent);
    }
    public void openlogin(View view) {
        Toast.makeText(this, "opening physical Activity", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, patient_new.class);
        startActivity(intent);
    }
}