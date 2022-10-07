package com.example.emergon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emergon.ui.slideshow.SlideshowFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.concurrent.TimeUnit;

public class Scanner_page extends AppCompatActivity implements View.OnClickListener{
    private EditText edt;
    private Button sb,vp;
    FirebaseAuth mAuth;
    String verificationID;
    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner_page);
        edt = findViewById(R.id.scanuser);
        sb = findViewById(R.id.scanbtt);
        vp = findViewById(R.id.btnverifyOTP1);
        mAuth = FirebaseAuth.getInstance();
        sb.setOnClickListener(this);
        vp.setOnClickListener(this);

        bar = findViewById(R.id.bar1);
    }

//    private void check(View view) {
//        Toast.makeText(this,"No Results",Toast.LENGTH_LONG).show();
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.scanbtt:
                scanCode();
                break;
            case R.id.btnverifyOTP1:
                if(TextUtils.isEmpty(vp.getText().toString()))
                {
                    Toast.makeText(Scanner_page.this, "Wrong OTP Entered", Toast.LENGTH_SHORT).show();
                }
                else
                    verifycode(vp.getText().toString());

                break;
            default:
                break;
        }
      // scanCode();
    }

    private void scanCode(){

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning code");
        integrator.initiateScan();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result  = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null){
            if(result.getContents() !=null){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                String s = result.getContents();
                Toast.makeText(this,s,Toast.LENGTH_LONG).show();
                builder.setMessage(result.getContents());
                builder.setTitle("scanning result");
                builder.setPositiveButton("scan Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        scanCode();
                    }
                }).setNegativeButton("finish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        edt.setText(s, TextView.BufferType.EDITABLE);
                        if(TextUtils.isEmpty(s))
                        {
                            Toast.makeText(Scanner_page.this, "Enter Valid Phone No.", Toast.LENGTH_SHORT).show();
                        }
                        else {
//                            edt.setText(s, TextView.BufferType.EDITABLE);
                            String number = s;
                            bar.setVisibility(View.VISIBLE);
                            sendverificationcode(number);
                        }

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            else{
                Toast.makeText(this,"No Results",Toast.LENGTH_LONG).show();
            }
        }else {
            super.onActivityResult(requestCode,resultCode,data);
        }

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
            Toast.makeText(Scanner_page.this, "Verification Failed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCodeSent(@NonNull String s,
                               @NonNull PhoneAuthProvider.ForceResendingToken token)
        {
            super.onCodeSent(s, token);
            verificationID = s;
            Toast.makeText(Scanner_page.this, "Code sent", Toast.LENGTH_SHORT).show();
            vp.setEnabled(true);
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
                            Toast.makeText(Scanner_page.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Scanner_page.this ,Scanner_page.class));
                        }

                    }
                });}

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser!=null)
        {
            startActivity(new Intent(Scanner_page.this, Scanner_page
                    .class));
            finish();
        }}

}