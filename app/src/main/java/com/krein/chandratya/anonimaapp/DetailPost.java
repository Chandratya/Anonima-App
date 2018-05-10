package com.krein.chandratya.anonimaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class DetailPost extends AppCompatActivity {
    FirebaseAuth auth;
    private TextView postDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);

        auth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        final String detail = intent.getStringExtra("DETAIL_POST");

        postDetail = findViewById(R.id.isikonten);


        postDetail.setText(detail);

    }

    public void addComment(View view) {
        Intent startActivity = new Intent(DetailPost.this, Comment.class);
        startActivity(startActivity);
    }

}
