package com.example.emergon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class patient_login extends AppCompatActivity {
    private EditText un,pass;
    private CheckBox cb;
    private Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_login);
        un= findViewById(R.id.Username);
        pass = findViewById(R.id.Password);
        cb = findViewById(R.id.cb2);
        b = findViewById(R.id.button3);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = un.getText().toString();
                String ps = pass.getText().toString();

                if(uname.isEmpty() || ps.isEmpty()){
                    Toast.makeText(patient_login.this,"please enter all data",Toast.LENGTH_SHORT).show();
                }else{
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("patient_name",uname);
                    editor.putString("patient_password",ps);
                    editor.putString("login","patient");
                    editor.apply();
                    Intent intent = new Intent(patient_login.this, patient.class);
                    startActivity(intent);

                }

            }
        });
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("b","true");
                    editor.apply();
                    Toast.makeText(patient_login.this,"Checked",Toast.LENGTH_SHORT).show();

                }else if(!compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("b","false");
                    editor.apply();
                    Toast.makeText(patient_login.this,"UnChecked",Toast.LENGTH_SHORT).show();

                }
            }
        });
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
