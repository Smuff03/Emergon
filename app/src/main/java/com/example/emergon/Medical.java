package com.example.emergon;

import android.app.Activity;
import android.content.ComponentName;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.emergon.databinding.FragmentMedicalBinding;
public class Medical extends Fragment {
    private FragmentMedicalBinding binding;
    Button b;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding  = FragmentMedicalBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        Activity activity = getActivity();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        try {
            intent.setComponent(new ComponentName("health.care.com.healthcare", "health.care.com.healthcare.MainActivity"));
            startActivity(intent);
        }catch (Exception E){
            Toast.makeText(activity, "Please install Our healthcare scan", Toast.LENGTH_SHORT).show();
        }

        return root;
    }
}