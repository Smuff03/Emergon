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

public class gene extends Fragment {
    private FragmentGeneBinding binding;
    ImageView iv;
    Button b,gq;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding  = FragmentGeneBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        Activity activity = getActivity();
        iv = root.findViewById(R.id.ivp);
        b = root.findViewById(R.id.gene);
        gq = root.findViewById(R.id.gqr);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QR q = new QR();
                SharedPreferences preferences = activity.getSharedPreferences("checkbox",activity.MODE_PRIVATE);
                String s = preferences.getString("patient_name","");
                q.qrgen(iv,s);
            }
        });
        gq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QR q = new QR();
                SharedPreferences preferences = activity.getSharedPreferences("checkbox",activity.MODE_PRIVATE);
                String s = preferences.getString("patient_name","");
                FirebaseDatabase db=FirebaseDatabase.getInstance();
                DatabaseReference node= db.getReference("/"+s+"/ph");
                node.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String value = snapshot.getValue(String.class);
                        q.qrgen(iv,value);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
        return root;
    }
}