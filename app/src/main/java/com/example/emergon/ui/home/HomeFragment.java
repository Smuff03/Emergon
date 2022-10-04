package com.example.emergon.ui.home;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.emergon.R;
import com.example.emergon.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    EditText pt_phone;
    Button homebut;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Activity activity = getActivity();
        pt_phone = root.findViewById(R.id.pat_phoneno);
        homebut = root.findViewById(R.id.homeconfirm);
        homebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = String.valueOf(pt_phone);
                if(TextUtils.isEmpty(phone)){
                    Toast.makeText(activity, "Enter Valid Phone No.", Toast.LENGTH_SHORT).show();
                }
                else{
                    SharedPreferences preferences = activity.getSharedPreferences("checkbox",activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("pat_phone",phone);
                    editor.apply();
                }
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}