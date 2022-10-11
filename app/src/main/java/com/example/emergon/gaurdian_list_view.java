package com.example.emergon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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

        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, names);
        gaurdian_list.setAdapter(adapter);
    }
    Toast msg;
    private void makeToast(String s){
        if ( msg != null) msg.cancel();
        msg = Toast.makeText(getApplicationContext(),s, Toast.LENGTH_SHORT);
        msg.show();
    }

}