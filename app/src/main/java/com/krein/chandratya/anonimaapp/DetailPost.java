package com.krein.chandratya.anonimaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class DetailPost extends AppCompatActivity {
    FirebaseAuth auth;
    private TextView postDetail, vKonten, vUserKonten;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);

        auth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        PostingModel detail = (PostingModel) intent.getSerializableExtra("ITEM_POSTING");

        postDetail = findViewById(R.id.isikonten);
        vKonten=(TextView)findViewById(R.id.isikonten);
        vUserKonten=(TextView)findViewById(R.id.user);

        vKonten.setText(""+detail.getPosting());
        vUserKonten.setText(""+detail.getUserName().split("@")[0]);


    }

    public void addComment(View view) {
        Intent startActivity = new Intent(DetailPost.this, Comment.class);
        startActivity(startActivity);
    }

}
