package com.example.firebasetest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MainActivity extends AppCompatActivity {

    // creating a variable for text view.
    TextView updatedTV;
    Button updatedButton;

    // initializing the variable for firebase firestore
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        updatedTV = findViewById(R.id.idTVUpdate);

        DocumentReference docRef = db.collection("MyData").document("Data");

        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
           @Override
           public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
               if (error != null) {
                   Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                   return;
               }
               if (value != null && value.exists()) {
                   updatedTV.setText(value.getData().get("updatedValue").toString());
               }
           }
       });

        updatedButton = findViewById(R.id.buttonUpdate);
        updatedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("database", "button clicked.");
                db.collection("MyData").document("Data").update("updatedValue", "somevalue");
            }
        });


//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }
}