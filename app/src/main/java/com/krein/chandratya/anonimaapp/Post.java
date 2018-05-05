package com.krein.chandratya.anonimaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class Post extends AppCompatActivity {
    private EditText content;
    private Button btnPostNearby;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        content = (EditText)findViewById(R.id.content);
        btnPostNearby = (Button)findViewById(R.id.btnPostNearby);
        btnPostNearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("postnearby").child(FirebaseClass.auth.getCurrentUser().getEmail().split("@")[0]);
                ref.child("user").setValue(FirebaseClass.auth.getCurrentUser().getEmail().split("@")[0]);
                String key = ref.push().getKey().split("-")[1];
                DatabaseReference refChild = ref.child(key);
                refChild.child("keyComment").setValue(key);
                refChild.child("posting").setValue(content.getText().toString());
            }
        });
    }
}
