package com.example.emergon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.InputStream;

public class medicine extends AppCompatActivity {
    Button selectDocumentToUploadBtn,uploadSelectedImageBtn;
    ImageView viewSelectedDocument;
    Uri Filepath;
    Bitmap bitmap;
    EditText input;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);


        input=(EditText)findViewById(R.id.document_name_edit_txt1);
        viewSelectedDocument=(ImageView)findViewById(R.id.view_selected_document_window1);

        selectDocumentToUploadBtn=(Button)findViewById(R.id.document_selection_btn1);
        selectDocumentToUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withActivity(medicine.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        //hya image/* cha folder creations shi kahi sambandh nahi to fakta specify karto device madhun images typeach uchla
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent,"Select Document"),1);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
            }
        });

        uploadSelectedImageBtn=(Button)findViewById(R.id.document_upload_btn1);
        uploadSelectedImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadtofirebase();
            }
        });

    }

    private void uploadtofirebase() {
        //upload percent wagera hya khalcha dialogbox madhe distat
        ProgressDialog dialog = new ProgressDialog(this);
//        String name=input.getText().toString();
        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
        String uname = preferences.getString("patient_name","");
        dialog.setTitle("File Upload Progress");
        dialog.show();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference uploader = storage.getReference().child(uname+"_medicine");
        FirebaseDatabase db = FirebaseDatabase.getInstance();
//        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
//        String uname = preferences.getString("patient_name","");
        DatabaseReference node = db.getReference("images"+uname);

        uploader.putFile(Filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                dialog.dismiss();
                node.child("ig1").setValue("gs://emergon-e0f67.appspot.com/"+uname+"_medicine");
                Toast.makeText(getApplicationContext(),"File uploaded ",Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                float percent =(100*snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                dialog.setMessage("Progress: "+(int)percent+" %");

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==1 && resultCode == RESULT_OK){
            Filepath=data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(Filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                viewSelectedDocument.setImageBitmap(bitmap);
            }catch (Exception ex){

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}