package com.example.emergon;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QR extends MainActivity {
    public QR() {
        Bitmap bitmap;
        QRGEncoder qrgEncoder;
    }
    public void qrgen(ImageView ivqr,String s) {
        int dimen = ivqr.getWidth() < ivqr.getHeight() ? ivqr.getWidth() : ivqr.getHeight();
        dimen = dimen * 3 / 4;
        QRGEncoder qrgEncoder = new QRGEncoder(s, null, QRGContents.Type.TEXT, dimen);
        Bitmap bitmap = qrgEncoder.getBitmap();
        ivqr.setImageBitmap(bitmap);
    }
    public void sc(Intent t1, MainActivity t2, Context t3){
        scanCode(t1,t2,t3);
    }
    private void scanCode(Intent t1, MainActivity t2, Context t3){

        IntentIntegrator integrator = new IntentIntegrator(t2);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning code");
        integrator.initiateScan();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data, Context t3, Intent t1, MainActivity t2){
        IntentResult result  = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null){
            if(result.getContents() !=null){
                AlertDialog.Builder builder = new AlertDialog.Builder(t2);
                builder.setMessage(result.getContents());
                builder.setTitle("scanning result");
                builder.setPositiveButton("scan Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        scanCode(t1,t2,t3);
                    }
                }).setNegativeButton("finish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            else{
                Toast.makeText(this,"No Results",Toast.LENGTH_LONG).show();
            }
        }else {
            super.onActivityResult(requestCode,resultCode,data,t3,t1,t2);
        }
    }
}
