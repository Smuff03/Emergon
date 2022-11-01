package com.example.emergon;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.emergon.R;
import com.example.emergon.databinding.FragmentCasefolderBinding;
import com.example.emergon.databinding.FragmentGeneBinding;
import com.example.emergon.databinding.FragmentSlideshowBinding;
import com.example.emergon.databinding.FragmentViewpresBinding;
import com.example.emergon.ui.gallery.GalleryFragment;
import com.example.emergon.ui.slideshow.SlideshowFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class viewpres extends Fragment {
    private FragmentViewpresBinding binding;
    EditText ed;
    TextView t1,t2;
    Button b;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding  = FragmentViewpresBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        Activity activity = getActivity();
        ed = root.findViewById(R.id.vprname);
        b = root.findViewById(R.id.vprbut);
        t1 = root.findViewById(R.id.vprt1);
        t2 = root.findViewById(R.id.vprt2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ed.getText().toString();
                Activity activity = getActivity();
                if(name.isEmpty()){
                    Toast.makeText(activity, "Enter Valid Doctor name", Toast.LENGTH_SHORT).show();
                }else{
                    FirebaseDatabase db=FirebaseDatabase.getInstance();
                    try {

                        SharedPreferences preferences = activity.getSharedPreferences("checkbox",activity.MODE_PRIVATE);
                        String uname = preferences.getString("patient_name","");
                        DatabaseReference node= db.getReference("/"+uname+"/"+name);

                        node.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String value = snapshot.getValue(String.class);
                                t1.setVisibility(View.VISIBLE);
                                t2.setVisibility(View.VISIBLE);
                                t2.setText(value);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(activity, "Turn On Internet", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }catch (Exception FirebaseError ){
                        Toast.makeText(activity, "Enter Correct ID", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return root;
    }
}