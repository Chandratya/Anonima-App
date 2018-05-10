package com.krein.chandratya.anonimaapp;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Collections;

/***
 * Main Activity for the Material Me app, a mock sports news application
 */
public class Home extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()){
            case R.id.action_logout:

                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(this, login.class ));
                Toast.makeText(this, "Logout berhasil", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_chatroom:
                startActivity(new Intent(this, ChatListActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void nearby(View view) {
        Intent startActivity = new Intent(Home.this, nearby.class);
        startActivity(startActivity);
    }
    public void register(View view) {
        Intent startActivity = new Intent(Home.this, register.class);
        startActivity(startActivity);
    }
}
