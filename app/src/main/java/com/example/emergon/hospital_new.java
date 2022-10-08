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

public class hospital_new extends AppCompatActivity {
    private EditText hos_name,hos_reg,hos_pass,cpass;
    private CheckBox cb;
    private Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_new);
        hos_name= findViewById(R.id.editTextTextPersonName4);
        hos_pass = findViewById(R.id.editTextTextPassword4);
        hos_reg = findViewById(R.id.editTextTextPersonName5);
        cpass = findViewById(R.id.editTextTextPassword5);
        cb = findViewById(R.id.cb4);
        b = findViewById(R.id.button6);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hos_name1 = hos_name.getText().toString();
                String ps = hos_pass.getText().toString();
                String cps = cpass.getText().toString();
                String hr = hos_reg.getText().toString();
                if(hos_name1.isEmpty() || ps.isEmpty()|| hr.isEmpty()|| cps.isEmpty()){
                    Toast.makeText(hospital_new.this,"please enter all data",Toast.LENGTH_SHORT).show();
                }else if(ps.matches(cps)){
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("hospital_name",hos_name1);
                    editor.putString("hospital_password",ps);
                    editor.putString("hospital_registration",hr);
                    editor.putString("login","hospital");

                    hospital_dataholder obj=new hospital_dataholder(hos_name1, ps, hr);
                    FirebaseDatabase db=FirebaseDatabase.getInstance();
                    DatabaseReference node= db.getReference(hos_name1);

                    node.child(hos_name1).setValue(obj);
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
