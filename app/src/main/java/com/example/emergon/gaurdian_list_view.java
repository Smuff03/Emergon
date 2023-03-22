package com.example.emergon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class gaurdian_list_view extends AppCompatActivity {
    ListView gaurdian_list;
    ArrayList<String> names;
    ArrayAdapter<String> adapter;
    final Context context = this;
    FirebaseDatabase db=FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaurdian_list_view);

        gaurdian_list = findViewById(R.id.gaurdian_list_container);
        names = new ArrayList<>();
        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
        String gn = preferences.getString("gno","");
        String un = preferences.getString("patient_name","");

        int gn1 = Integer.parseInt(gn);
//        DatabaseReference node1 = db.getReference("/"+un+"/gno");
//        node1.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
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
//        DatabaseReference node= db.getReference("/"+un);

        // he khalcha hyane modify kara list madhe items add karayla
//        names.add("chutiya");
//
//        names.add("bsdk");

        gaurdian_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                View db = layoutInflater.inflate(R.layout.dialog_box, null);
                Toast.makeText(gaurdian_list_view.this, adapter.getItem(i), Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(gaurdian_list_view.this);
                builder.setView(db);
                builder.setCancelable(false);
                final EditText input = (EditText) db.findViewById(R.id.userInput);
                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                    String s = input.getText().toString();
                    String oldname = adapter.getItem(i);
                    change(i,s,oldname);
                    Toast.makeText(gaurdian_list_view.this, s, Toast.LENGTH_SHORT).show();
                });
                builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                    String s = input.getText().toString();
                    dialog.cancel();
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

//        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, names);
//        gaurdian_list.setAdapter(adapter);
    }
    Toast msg;
    private void change(int i,String s,String old_name){
        names.add(i,s);
        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
        String un = preferences.getString("patient_name","");
        names.remove(i+1);
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, names);
        gaurdian_list.setAdapter(adapter);
        DatabaseReference node= db.getReference("/"+un);
        Map<String, Object> updates = new HashMap<String,Object>();
        updates.put("guard"+i, s);
        firestore f = new firestore();
        f.update_guardian_name(un,s,old_name);
        node.updateChildren(updates);
    }
    private void makeToast(String s){
        if ( msg != null) msg.cancel();
        msg = Toast.makeText(getApplicationContext(),s, Toast.LENGTH_SHORT);
        msg.show();
    }

}