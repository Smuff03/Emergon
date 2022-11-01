package com.example.emergon.ui.home;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.emergon.R;
import com.example.emergon.databinding.FragmentHomeBinding;
import com.example.emergon.dataholder;
import com.example.emergon.hos_scan_res;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    Button homebut;
    EditText ad,dob,email,ph,age,hg,wg,mc,n,bg,add;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Activity activity = getActivity();
        String N1,Ad1,Db1,Em1,Ph1,Ag1,Hg1,Wg1,Mc1,Bg1,Add1;
        SharedPreferences preferences = activity.getSharedPreferences("checkbox",activity.MODE_PRIVATE);
        String uname = preferences.getString("patient_name","");
        String ps = preferences.getString("patient_pass","");
        String g = preferences.getString("gno","");
        ph = root.findViewById(R.id.eph);
        homebut = root.findViewById(R.id.homeconfirm);
        n =  root.findViewById(R.id.ename);
        ad = root.findViewById(R.id.eadh);
        dob = root.findViewById(R.id.edob);
        email = root.findViewById(R.id.eemail);
        ph = root.findViewById(R.id.eph);
        age = root.findViewById(R.id.eage);
        hg = root.findViewById(R.id.ehg);;
        wg = root.findViewById(R.id.ewg);
        mc = root.findViewById(R.id.emc);
        bg = root.findViewById(R.id.ebg);
        add = root.findViewById(R.id.ead);
        N1 = res(uname,"n");
        Db1 = res(uname,"dod");
        Em1 = res(uname,"email");
        Ph1 = res(uname,"ph");
        Ag1 = res(uname,"age");
        Hg1 = res(uname,"hg");
        Wg1 = res(uname,"wg");
        Bg1 = res(uname,"bg");
        Mc1 = res(uname,"mc");
        Ad1 = res(uname,"ad");
        Add1 = res(uname,"add");

        homebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity activity = getActivity();
                String N = n.getText().toString();
                String Ad = ad.getText().toString();
                String Dob = dob.getText().toString();
                String Email = email.getText().toString();
                String Ph = ph.getText().toString();
                String Age = age.getText().toString();
                String Hg = hg.getText().toString();
                String Wg = wg.getText().toString();
                String Mc = mc.getText().toString();
                String Bg = bg.getText().toString();
                String Add = add.getText().toString();
                if(N.isEmpty()||Ad.isEmpty()||Dob.isEmpty()||Email.isEmpty()||Ph.isEmpty()||Age.isEmpty()||Hg.isEmpty()||
                Wg.isEmpty()||Mc.isEmpty()||Bg.isEmpty()||Add.isEmpty()){
                    Toast.makeText(activity,"please enter all data",Toast.LENGTH_SHORT).show();
                }else{
                    dataholder obj=new dataholder(uname,ps,n.getText().toString(),ad.getText().toString(),dob.getText().toString(),email.getText().toString(),ph.getText().toString(),age.getText().toString(),hg.getText().toString(),wg.getText().toString(),mc.getText().toString(),bg.getText().toString(),g,add.getText().toString());
                    FirebaseDatabase db=FirebaseDatabase.getInstance();
                    DatabaseReference node= db.getReference(uname);
                    node.setValue(obj);
                    Toast.makeText(activity,"Data saved",Toast.LENGTH_SHORT).show();
                    SharedPreferences preferences = activity.getSharedPreferences("checkbox",activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();

                    editor.putString("ph",Ph);
                    editor.putString("n",N);
                    editor.putString("ad",Ad);
                    editor.putString("dob",Dob);
                    editor.putString("email",Email);
                    editor.putString("age",Age);
                    editor.putString("hg",Hg);
                    editor.putString("wg",Wg);
                    editor.putString("mc",Mc);
                    editor.putString("bg",Bg);
                    editor.putString("add",Add);
                    editor.apply();
                }
            }
        });

        return root;
    }
    public String res(String u,String p){
        String result;
        Activity activity = getActivity();
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        DatabaseReference node= db.getReference("/"+u+"/"+p);
        SharedPreferences preferences = activity.getSharedPreferences("checkbox",activity.MODE_PRIVATE);
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
                    case "bg":
                        bg.setText(value, TextView.BufferType.EDITABLE);
                        break;
                    case "add":
                        add.setText(value, TextView.BufferType.EDITABLE);
                        break;

                    default:
                        ad.setText("this is default", TextView.BufferType.EDITABLE);
                        break;

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(activity,"Not Found",Toast.LENGTH_SHORT).show();
            }
        });
        result = preferences.getString("temp","");
        return result;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
