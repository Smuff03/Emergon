package com.example.emergon.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.emergon.R;
import com.example.emergon.databinding.FragmentGalleryBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class GalleryFragment extends AppCompatActivity {
    EditText phone, otp;
    Button btngenOTP, btnverify;
    FirebaseAuth mAuth;
    String verificationID;
    ProgressBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phone = findViewById(R.id.phone);
        otp = findViewById(R.id.otp);
        btngenOTP = findViewById(R.id.btngenerateOTP);
        btnverify =findViewById(R.id.btnverifyOTP);
        mAuth = FirebaseAuth.getInstance();
        bar = findViewById(R.id.bar);
        btngenOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(TextUtils.isEmpty(phone.getText().toString()))
                {
                    Toast.makeText(GalleryFragment.this, "Enter Valid Phone No.", Toast.LENGTH_SHORT).show();
                }
                else {
                    String number = phone.getText().toString();
                    bar.setVisibility(View.VISIBLE);
                    sendverificationcode(number);
                }
            }
        });
        btnverify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(TextUtils.isEmpty(otp.getText().toString()))
                {
                    Toast.makeText(GalleryFragment.this, "Wrong OTP Entered", Toast.LENGTH_SHORT).show();
                }
                else
                    verifycode(otp.getText().toString());
            }
        });
    }

    private void sendverificationcode(String phoneNumber)
    {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91"+phoneNumber)  // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential credential)
        {
            final String code = credential.getSmsCode();
            if(code!=null)
            {
                verifycode(code);
            }
        }
        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(GalleryFragment.this, "Verification Failed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCodeSent(@NonNull String s,
                               @NonNull PhoneAuthProvider.ForceResendingToken token)
        {
            super.onCodeSent(s, token);
            verificationID = s;
            Toast.makeText(GalleryFragment.this, "Code sent", Toast.LENGTH_SHORT).show();
            btnverify.setEnabled(true);
            bar.setVisibility(View.INVISIBLE);
        }};
    private void verifycode(String Code)
    {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID,Code);
        signinbyCredentials(credential);
    }

    private void signinbyCredentials(PhoneAuthCredential credential)
    {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task)
                    {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(GalleryFragment.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(GalleryFragment.this ,GalleryFragment.class));
                        }

                    }
                });}

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser!=null)
        {
            startActivity(new Intent(GalleryFragment.this, GalleryFragment.class));
            finish();
        }}}