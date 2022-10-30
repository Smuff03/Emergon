package com.example.emergon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class gaurdian_list_view extends AppCompatActivity {
    ListView gaurdian_list;
    ArrayList<String> names;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaurdian_list_view);

        gaurdian_list = findViewById(R.id.gaurdian_list_container);
        names = new ArrayList<>();
        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
        String gn = preferences.getString("gno","");
        String un = preferences.getString("patient_name","");
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        int gn1 = Integer.parseInt(gn);
        for(int i=0;i<gn1;i++){
            DatabaseReference node= db.getReference("/"+un+"/guard"+String.valueOf(i));
            node.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String value = snapshot.getValue(String.class);
                    names.add(value);
                    adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, names);
                    gaurdian_list.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        DatabaseReference node= db.getReference("/"+un);

        // he khalcha hyane modify kara list madhe items add karayla
//        names.add("chutiya");
//
//        names.add("bsdk");

        gaurdian_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String info = names.get(i);
                makeToast(info);
            }
        });

//        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, names);
//        gaurdian_list.setAdapter(adapter);
    }
    Toast msg;
    private void makeToast(String s){
        if ( msg != null) msg.cancel();
        msg = Toast.makeText(getApplicationContext(),s, Toast.LENGTH_SHORT);
        msg.show();
    }

}