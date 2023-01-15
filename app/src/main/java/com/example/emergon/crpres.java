package com.example.emergon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class crpres extends AppCompatActivity {
    EditText Name,Date,Pre;
    Button B;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crpres);
        t  = findViewById(R.id.crpresdname);
        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
        String dn = preferences.getString("dname","");
        if(dn==""){
            startActivity(new Intent(crpres.this, Addhar_verfi_page.class));
            Toast.makeText(this, "Enter correct ID", Toast.LENGTH_SHORT).show();
        }
        t.setText("Welcome Dr."+dn);
        B = findViewById(R.id.crpresbut);
        Name = findViewById(R.id.ptname);
        Date = findViewById(R.id.ptdate);
        Pre = findViewById(R.id.ptpre);
        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name,pre,date;
                name =Name.getText().toString();
                date = Date.getText().toString();
                pre = Pre.getText().toString();
                if(name.isEmpty()||date.isEmpty()||pre.isEmpty()){
                    Toast.makeText(crpres.this, "Fill all!!", Toast.LENGTH_SHORT).show();
                }else{
                    FirebaseDatabase db=FirebaseDatabase.getInstance();
                    try {
                        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                        String dname = preferences.getString("dname","");
//                        DatabaseReference node= db.getReference(name);
//                        DatabaseReference node1= db.getReference("/Doctors/"+k);
                        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("/"+name);
                        Map<String, Object> updates = new HashMap<String,Object>();
                        updates.put(dname, pre);
                        ref.updateChildren(updates);
                        Toast.makeText(crpres.this, "Prescription Uploaded", Toast.LENGTH_SHORT).show();

                    }catch (Exception FirebaseError ){
                        Toast.makeText(crpres.this, "Enter Correct ID", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}