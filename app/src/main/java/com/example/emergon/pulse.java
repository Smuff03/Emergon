package com.example.emergon;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
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
import com.example.emergon.databinding.FragmentPulseBinding;
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

public class pulse extends Fragment {
    private FragmentPulseBinding binding;
    private ProgressBar progressBar;
    private TextView progressText;
    int i = 50;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding  = FragmentPulseBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        Activity activity = getActivity();
        progressBar = root.findViewById(R.id.progress_bar);
        progressText = root.findViewById(R.id.progress_text);
        progressText.setText("" + i);
        int k ;
        k = i - 30;
        progressBar.setProgress(k);
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("/pulsetest/p");

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Retrieve the value from the dataSnapshot
                Integer progressValue = dataSnapshot.getValue(Integer.class);
                // Set the progress bar value
                change(progressValue);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };

// Add the valueEventListener to the databaseRef
        databaseRef.addValueEventListener(valueEventListener);


        return root;
    }

    private void change(Integer progressValue) {
        String p = String.valueOf(progressValue);
        progressValue = progressValue - 30;
        i = 0;
        int  i1 = progressValue;
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // set the limitations for the numeric
                // text under the progress bar
                if (i <= i1) {
                    progressText.setText(p);
                    progressBar.setProgress(i);
                    i++;
                    handler.postDelayed(this, 20);
                } else {
                    handler.removeCallbacks(this);
                }
            }
        }, 20);

    }
}