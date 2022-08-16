package com.example.emergon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class hospital_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_login);
    }

    public void new_hospital(View view) {
        Toast.makeText(this, "Opening Sign up for hospital", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, hospital_new.class);
        startActivity(intent);
    }
}