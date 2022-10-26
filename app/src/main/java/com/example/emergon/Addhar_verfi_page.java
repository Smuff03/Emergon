package com.example.emergon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.emergon.ui.slideshow.SlideshowFragment;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Addhar_verfi_page extends AppCompatActivity {
    Button b;
    EditText t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addhar_verfi_page);
        b = findViewById(R.id.psub);
        t = findViewById(R.id.addhar_input_box);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String k = t.getText().toString();
                if(k.isEmpty()){
                    Toast.makeText(Addhar_verfi_page.this, "Enter ID", Toast.LENGTH_SHORT).show();
                }else{
                FirebaseDatabase db=FirebaseDatabase.getInstance();
                try {
                    DatabaseReference node= db.getReference("/Doctors/"+k);
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("did",k);
                    editor.apply();
                    node.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String value = snapshot.getValue(String.class);
                            editor.putString("dname",value);
                            editor.apply();
                            startActivity(new Intent(Addhar_verfi_page.this, crpres.class));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }catch (Exception FirebaseError ){
                    Toast.makeText(Addhar_verfi_page.this, "Enter Correct ID", Toast.LENGTH_SHORT).show();
                }
                }

            }
        });
    }
}