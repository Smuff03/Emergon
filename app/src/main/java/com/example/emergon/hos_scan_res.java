package com.example.emergon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class hos_scan_res extends AppCompatActivity {
    String N,Db,Em,Ph,Ag,Hg,Wg,Bg,Mc,Ad;
    EditText ad,dob,email,ph,age,hg,wg,mc,n,bg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hos_scan_res);
        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
        String uname = preferences.getString("hu","");
        N = res(uname,"n");
        Db = res(uname,"dod");
        Em = res(uname,"email");
        Ph = res(uname,"ph");
        Ag = res(uname,"age");
        Hg = res(uname,"hg");
        Wg = res(uname,"wg");
//        bg = res(uname,"");
        Mc = res(uname,"mc");
        Ad = res(uname,"ad");
        ph = findViewById(R.id.epho);
        n =  findViewById(R.id.en);
        ad = findViewById(R.id.eadhh);
        dob = findViewById(R.id.edate);
        email = findViewById(R.id.eem);
        age = findViewById(R.id.eag);
        hg = findViewById(R.id.eh);;
        wg = findViewById(R.id.ew);
        mc = findViewById(R.id.emcm);
        bg = findViewById(R.id.eb);
    }
    public String res(String u,String p){
        String result;
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        DatabaseReference node= db.getReference("/"+u+"/"+p);
        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
        node.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                switch (p){
                    case "n":
                        n.setText(value, TextView.BufferType.EDITABLE);
                        break;
                    case "dod":
                        dob.setText(value, TextView.BufferType.EDITABLE);
                        break;
                    case "email":
                        email.setText(value, TextView.BufferType.EDITABLE);
                        break;
                    case "ph":
                        ph.setText(value, TextView.BufferType.EDITABLE);
                        break;
                    case "age":
                        age.setText(value, TextView.BufferType.EDITABLE);
                        break;
                    case "hg":
                        hg.setText(value, TextView.BufferType.EDITABLE);
                        break;
                    case "wg":
                        wg.setText(value, TextView.BufferType.EDITABLE);
                        break;
                    case "mc":
                        mc.setText(value, TextView.BufferType.EDITABLE);
                        break;
                    case "ad":
                        ad.setText(value, TextView.BufferType.EDITABLE);
                        break;
                    default:
                        ad.setText("this is default", TextView.BufferType.EDITABLE);
                        break;

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(hos_scan_res.this,"Not Found",Toast.LENGTH_SHORT).show();
            }
        });
        result = preferences.getString("temp","");
        return result;
    }
}