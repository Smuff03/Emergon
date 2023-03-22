package com.example.emergon;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.AggregateQuery;
import com.google.firebase.firestore.AggregateQuerySnapshot;
import com.google.firebase.firestore.AggregateSource;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class firestore {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String k;
    public void set(String un,String gn,String ph){
        
        Map<String, Object> phone = new HashMap<>();
        phone.put(gn, ph);
        db.collection("Guardian").document(un).set(phone);
        }
    public void update_guardian_name(String un,String gn,String old_name){
        DocumentReference docRef = db.collection("Guardian").document(un);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                       String ph_raw = ""+document.getData();
                       int oldlen = old_name.length();
                       int i = ph_raw.indexOf(old_name);
                       int len = ph_raw.length();
                        String ph = ph_raw.substring((i+oldlen+1),(i+oldlen+11));
                        ac(gn,ph,old_name,un);
//                        Map<String, Object> phone = new HashMap<>();
//                        phone.put(gn, ph);
//                        db.collection("Guardian").document(un).set(phone, SetOptions.merge());

                    }
                }
            }
        });

    }
    public void ac(String gn,String ph,String old_name,String un){
        DocumentReference docRef = db.collection("Guardian").document(un);
        Map<String, Object> phone = new HashMap<>();
        phone.put(old_name, FieldValue.delete());
        phone.put(gn, ph);
        docRef.update(phone);

    }

        public void cnt(){
            CollectionReference collection = db.collection("Guardian");
            Query query = collection.whereEqualTo(FieldPath.documentId(), "sam");
            AggregateQuery countQuery = query.count();
            countQuery.get(AggregateSource.SERVER).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    AggregateQuerySnapshot snapshot = task.getResult();
                    long c = snapshot.getCount();
                    String t = String.valueOf(c);
                    k = t;
                } else {
                }
            });
}
}
