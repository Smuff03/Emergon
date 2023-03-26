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
import com.example.emergon.databinding.FragmentReminderBinding;
public class Reminder extends Fragment {
    private FragmentReminderBinding binding;
    Button b;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding  = FragmentReminderBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        Activity activity = getActivity();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        try {
            intent.setComponent(new ComponentName("com.pratik.medicinereminder", "com.pratik.medicinereminder.activity.MainActivity"));
            startActivity(intent);
        }catch (Exception E){
            Toast.makeText(activity, "Please install med reminder", Toast.LENGTH_SHORT).show();
        }

        return root;
    }
}