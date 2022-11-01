package com.example.emergon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class patient_login extends AppCompatActivity {
    private EditText un,pass;
    private CheckBox cb;
    private Button b;
    @SuppressLint("MissingInflatedId")
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
                String upperCaseChars = "(.*[A-Z].*)";
                String lowerCaseChars = "(.*[a-z].*)";
                String numbers = "(.*[0-9].*)";
                String specialChars = "(.*[@,#,$,%].*$)";
                SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                String value = preferences.getString("temp","");
                if(uname.isEmpty() || ps.isEmpty()){
                    Toast.makeText(patient_login.this,"please enter all data",Toast.LENGTH_SHORT).show();

               }
                else{
                    FirebaseDatabase db=FirebaseDatabase.getInstance();
                    DatabaseReference node= db.getReference("/"+uname+"/ps");
                    DatabaseReference node1= db.getReference("/"+uname+"/gno");
                    node1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String value = snapshot.getValue(String.class);
                            SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("gno",value);
                            editor.apply();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    node.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String value = snapshot.getValue(String.class);
                            if(ps.equals(value)){

                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("patient_name",uname);
                                editor.putString("patient_pass",ps);
                                editor.putString("login","patient");
                                editor.apply();
                                Intent intent = new Intent(patient_login.this, patient.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(patient_login.this,"Incorrect Password",Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(patient_login.this,"Not Found",Toast.LENGTH_SHORT).show();
                        }
                    });
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
    Toast msg;
    private void makeToast(String s){
           if ( msg != null) msg.cancel();
            msg = Toast.makeText(getApplicationContext(),s, Toast.LENGTH_SHORT);
    }
    public boolean isValidPassword(String password)
    {
        boolean isValid = true;
        if (password.length() > 15 || password.length() < 8)
        {
            makeToast("Password must me within 8 - 15 char");
            isValid = false;
        }
        String upperCaseChars = "(.*[A-Z].*)";
        if (!password.matches(upperCaseChars ))
        {
            makeToast("Password must consist one capital letter");
            isValid = false;
        }
        String lowerCaseChars = "(.*[a-z].*)";
        if (!password.matches(lowerCaseChars ))
        {
            makeToast("Passwor must contain lower cases");
            isValid = false;
        }
        String numbers = "(.*[0-9].*)";
        if (!password.matches(numbers ))
        {
            makeToast("Password must consist some numerical");
            isValid = false;
        }
        String specialChars = "(.*[@,#,$,%].*$)";
        if (!password.matches(specialChars ))
        {
            makeToast("Password must contain some special character");
            isValid = false;
        }
        return isValid;
    }
}