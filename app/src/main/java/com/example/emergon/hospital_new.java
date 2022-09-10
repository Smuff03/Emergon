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

public class hospital_new extends AppCompatActivity {
    private EditText hname,hreg,pass,cpass;
    private CheckBox cb;
    private Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_new);
        hname= findViewById(R.id.editTextTextPersonName4);
        pass = findViewById(R.id.editTextTextPassword4);
        hreg = findViewById(R.id.editTextTextPersonName5);
        cpass = findViewById(R.id.editTextTextPassword5);
        cb = findViewById(R.id.cb4);
        b = findViewById(R.id.button6);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hname1 = hname.getText().toString();
                String ps = pass.getText().toString();
                String cps = cpass.getText().toString();
                String hr = hreg.getText().toString();
                if(hname1.isEmpty() || ps.isEmpty()|| hr.isEmpty()|| cps.isEmpty()){
                    Toast.makeText(hospital_new.this,"please enter all data",Toast.LENGTH_SHORT).show();
                }else if(ps.matches(cps)){
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("hospital_name",hname1);
                    editor.putString("hospital_password",ps);
                    editor.putString("hospital_registration",hr);
                    editor.putString("login","hospital");
                    editor.apply();
                    Intent intent = new Intent(hospital_new.this, hlogin.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(hospital_new.this,"password does not match",Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(hospital_new.this,"Checked",Toast.LENGTH_SHORT).show();

                }else if(!compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("b","false");
                    editor.apply();
                    Toast.makeText(hospital_new.this,"UnChecked",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

}
