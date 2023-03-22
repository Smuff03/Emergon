package com.example.emergon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.content.Context;

import com.google.firebase.firestore.AggregateQuery;
import com.google.firebase.firestore.AggregateQuerySnapshot;
import com.google.firebase.firestore.AggregateSource;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
        String checkbox = preferences.getString("b","");
        String log = preferences.getString("login","");
        if(checkbox.equals("true")){
            if(log.equals("patient")){
            Intent intent = new Intent(MainActivity.this,bio.class);
            startActivity(intent);}
            else if(log.equals("hospital")){
                Intent intent = new Intent(MainActivity.this,bio.class);
                startActivity(intent);
            }
        }else if(checkbox.equals("false")){
            Toast.makeText(this,"please sign in",Toast.LENGTH_SHORT).show();
        }

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
