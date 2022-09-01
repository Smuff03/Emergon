package com.example.emergon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.content.Context;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void openActivity(View view) {
        Toast.makeText(this, "opening Patient login", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, patient_login.class);
        startActivity(intent);
    }

    public void openhospital(View view) {
        Toast.makeText(this, "opening Hospital login", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, hospital_login.class);
        startActivity(intent);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data, Context t3, Intent t1, MainActivity t2) {
    }
}