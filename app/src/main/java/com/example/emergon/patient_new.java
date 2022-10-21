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

        String upperCaseChars = "(.*[A-Z].*)";
        String lowerCaseChars = "(.*[a-z].*)";
        String numbers = "(.*[0-9].*)";
        String specialChars = "(.*[@,#,$,%].*$)";

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = un.getText().toString();
                String ps = pass.getText().toString();
                String cps = cpass.getText().toString();

                if(uname.isEmpty() || ps.isEmpty()){
                    Toast.makeText(patient_new.this,"please enter all data",Toast.LENGTH_SHORT).show();
                }else if (ps.length() > 15 || ps.length() < 8)
                {
                    makeToast("Password must me within 8 - 15 char");
                }
                else if (!ps.matches(upperCaseChars ))
                {
                    makeToast("Password must consist one capital letter");
                } else if (!ps.matches(lowerCaseChars ))
                {
                    makeToast("Passwor must contain lower cases");
                } else if (!ps.matches(numbers ))
                {
                    makeToast("Password must consist some numerical");
                } else if (!ps.matches(specialChars ))
                {
                    makeToast("Password must contain some special character");
                }else if(ps.matches(cps)){
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("patient_name",uname);
                    editor.putString("patient_pass",ps);
                    editor.putString("gno","0");
                    editor.putString("login","patient");
                    dataholder obj=new dataholder(uname,ps,"","","","","","","","","","","0");
                    Image_dataholder obj1 = new Image_dataholder("","","","");
                    FirebaseDatabase db=FirebaseDatabase.getInstance();
                    DatabaseReference node= db.getReference(uname);
                    DatabaseReference inode= db.getReference("images"+uname);
                    node.setValue(obj);
                    inode.setValue(obj1);
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
    Toast msg;
    private void makeToast(String s) {
        if ( msg != null) msg.cancel();
        Toast.makeText(getApplicationContext(),s, Toast.LENGTH_SHORT).show();
    }

}
