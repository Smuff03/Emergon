
package com.example.emergon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class hlogin extends AppCompatActivity {
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hlogin);

        Button Scanner_btn = findViewById(R.id.Scanner_btn);
        Scanner_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goTOScannerPage = new Intent(hlogin.this,Scanner_page.class);
                startActivity(goTOScannerPage);
            }
        });
        Button addhar_btn = findViewById(R.id.Adhhar_btn);
        addhar_btn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToAddharVerifiPage = new Intent(hlogin.this,Addhar_verfi_page.class);
                startActivity(goToAddharVerifiPage);
            }
        }));
    }
}
