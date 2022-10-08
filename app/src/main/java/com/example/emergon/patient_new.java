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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class patient_new extends AppCompatActivity {
    private EditText un,pass,cpass;
    private CheckBox cb;
    private Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_new);
        un= findViewById(R.id.editTextTextPersonName3);
        pass = findViewById(R.id.editTextTextPassword2);
        cpass = findViewById(R.id.editTextTextPassword3);
        cb = findViewById(R.id.cb1);
        b = findViewById(R.id.button5);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = un.getText().toString();
                String ps = pass.getText().toString();
                String cps = cpass.getText().toString();

                if(uname.isEmpty() || ps.isEmpty()){
                    Toast.makeText(patient_new.this,"please enter all data",Toast.LENGTH_SHORT).show();
                }else if(ps.matches(cps)){
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("patient_name",uname);
                    editor.putString("patient_pass",ps);
                    editor.putString("login","patient");
                    dataholder obj=new dataholder(uname,ps,"","","","","","","","","");

                    FirebaseDatabase db=FirebaseDatabase.getInstance();
                    DatabaseReference node= db.getReference(uname);

                    node.child(uname).setValue(obj);
                    editor.apply();
                    Intent intent = new Intent(patient_new.this, patient.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(patient_new.this,"password does not match",Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(patient_new.this,"Checked",Toast.LENGTH_SHORT).show();

                }else if(!compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("b","false");
                    editor.apply();
                    Toast.makeText(patient_new.this,"UnChecked",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

}
