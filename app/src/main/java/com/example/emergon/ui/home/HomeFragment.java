package com.example.emergon.ui.home;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.emergon.R;
import com.example.emergon.databinding.FragmentHomeBinding;
import com.example.emergon.dataholder;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    Button homebut;
    EditText ad,dob,email,ph,age,hg,wg,mc,n,bg;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Activity activity = getActivity();

        SharedPreferences preferences = activity.getSharedPreferences("checkbox",activity.MODE_PRIVATE);
        String uname = preferences.getString("patient_name","");
        String ps = preferences.getString("patient_pass","");
        ph = root.findViewById(R.id.pat_phoneno);
        homebut = root.findViewById(R.id.homeconfirm);
        n =  root.findViewById(R.id.editTextTextPersonName7);
        ad = root.findViewById(R.id.editTextNumber5);
        dob = root.findViewById(R.id.editTextDate);
        email = root.findViewById(R.id.editTextTextEmailAddress);
        ph = root.findViewById(R.id.pat_phoneno);
        age = root.findViewById(R.id.editTextNumber2);
        hg = root.findViewById(R.id.editTextNumber3);;
        wg = root.findViewById(R.id.editTextNumber4);
        mc = root.findViewById(R.id.editTextTextPersonName9);
        bg = root.findViewById(R.id.editTextTextPersonName8);

        homebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataholder obj=new dataholder(uname,ps,n.getText().toString(),ad.getText().toString(),dob.getText().toString(),email.getText().toString(),ph.getText().toString(),age.getText().toString(),hg.getText().toString(),wg.getText().toString(),mc.getText().toString());
                FirebaseDatabase db=FirebaseDatabase.getInstance();
                DatabaseReference node= db.getReference(uname);

                node.child(uname).setValue(obj);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}