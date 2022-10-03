
package com.example.emergon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class hlogin extends AppCompatActivity {
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hlogin);
        tv = findViewById(R.id.textView3);

        tv.setText(String.format("welcome to hospital "));
    }
}
