package com.example.emergon;

import androidx.annotation.NonNull;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class hospital_login extends AppCompatActivity {
    private EditText hname,hreg,pass;
    private CheckBox cb;
    private Button b,b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_login);
        hname= findViewById(R.id.editTextTextPersonName);
        pass = findViewById(R.id.editTextTextPassword);
        hreg = findViewById(R.id.editTextTextPersonName2);
        cb = findViewById(R.id.cb3);
        b = findViewById(R.id.button4);
        b1 = findViewById(R.id.buttongetosignup);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(hospital_login.this, "Opening Sign up for hospital", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(hospital_login.this, hospital_new.class);
                startActivity(intent);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hname1 = hname.getText().toString();
                String ps = pass.getText().toString();
                String hr = hreg.getText().toString();
                FirebaseDatabase db=FirebaseDatabase.getInstance();
                DatabaseReference node= db.getReference("/"+hname1+"/hos_pass");
                node.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String value = snapshot.getValue(String.class);
                        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("temp",value);
                        editor.apply();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(hospital_login.this,"Not Found",Toast.LENGTH_SHORT).show();
                    }
                });
                SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                String value = preferences.getString("temp","");
                if(hname1.isEmpty() || ps.isEmpty()|| hr.isEmpty()){
                    Toast.makeText(hospital_login.this,"please enter all data",Toast.LENGTH_SHORT).show();
                }else if(!ps.equals(value)){
                    Toast.makeText(hospital_login.this,"TRY AGAIN",Toast.LENGTH_SHORT).show();
                }
                else{
//                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("hospital_name",hname1);
                    editor.putString("hospital_password",ps);
                    editor.putString("hospital_registration",hr);
                    editor.putString("login","hospital");
                    editor.apply();
                    Intent intent = new Intent(hospital_login.this, hlogin.class);
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
                    Toast.makeText(hospital_login.this,"Checked",Toast.LENGTH_SHORT).show();

                }else if(!compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("b","false");
                    editor.apply();
                    Toast.makeText(hospital_login.this,"UnChecked",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void new_hospital(View view) {
        Toast.makeText(this, "Opening Sign up for hospital", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, hospital_new.class);
        startActivity(intent);
    }
}
