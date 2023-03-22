package com.example.emergon;

import android.app.Activity;
import android.app.SearchManager;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.emergon.R;
import com.example.emergon.databinding.FragmentCasefolderBinding;
import com.example.emergon.databinding.FragmentGeneBinding;
import com.example.emergon.databinding.FragmentMapBinding;
import com.example.emergon.databinding.FragmentSlideshowBinding;
import com.example.emergon.databinding.FragmentViewpresBinding;
import com.example.emergon.ui.gallery.GalleryFragment;
import com.example.emergon.ui.slideshow.SlideshowFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class map extends Fragment {
    private FragmentMapBinding binding;
    private Button b,b1;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding  = FragmentMapBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        Activity activity = getActivity();
        b = root.findViewById(R.id.map1);
        b1 = root.findViewById(R.id.test);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = activity.getSharedPreferences("checkbox",activity.MODE_PRIVATE);
                String un = preferences.getString("patient_name","");
                FirebaseDatabase db=FirebaseDatabase.getInstance();
//                /Jayesh/users/test
                DatabaseReference node= db.getReference("/"+un+"/users/test/china");
                node.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                        String value = snapshot.getValue(String.class);
                        int comma = value.indexOf(",");
                        String lat = value.substring(0,comma);
                        int len = value.length();
                        String lon = value.substring(comma+1, len);
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:"+value+"?q="+value+"(Patient+Location)"));
                        startActivity(intent);

                        }
                        else{
//                            Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=hospitals"));
//                            mapIntent.setPackage("com.google.android.apps.maps");
//                            startActivity(mapIntent);
                            Toast.makeText(activity, "there is no Accident", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }

                });
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:19.07613608801284,72.99426826825649?q=19.07613608801284,72.99426826825649(Patient+Location)"));
//                startActivity(intent);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = "doctors near me";
                Activity activity = getActivity();
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, query); // query contains search string
                startActivity(intent);
//
//                startActivity(new Intent(activity, graph.class));
            }
        });

        return root;
    }
}