
package com.example.emergon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class hlogin extends AppCompatActivity {
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hlogin);
        tv = findViewById(R.id.textView3);

        tv.setText(String.format("welcome to hospital "));
        Button Scanner = findViewById(R.id.Scanner);
        Scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Scanneropen = new Intent(hlogin.this,Scanner_page.class);
                startActivity(Scanneropen);
                Toast.makeText(getApplicationContext(), "Opening Scanner",Toast.LENGTH_LONG).show();
            }
        });
        //adhar ke button ko define kiya aur uska activity bhi
        Button Addhar_btn = findViewById(R.id.Adhhar);
        Addhar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //iske wajah se object create hota hai
                Intent Addhar_page_open = new Intent(hlogin.this,Adhar_verification_page.class);
                //niche wale se page ko jata hai
                startActivity(Addhar_page_open);
                //ye niche wala message deta hai
                Toast.makeText(getApplicationContext(),"Opening Adhar verification page",Toast.LENGTH_LONG).show();
            }
        });
    }
}
