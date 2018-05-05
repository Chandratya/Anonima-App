package com.krein.chandratya.anonimaapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {


    EditText loginEmail, loginPassword;
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // create new firebase instance

        // check if user already logged in
        if (FirebaseClass.auth.getCurrentUser() != null) {
            startActivity(new Intent(login.this, Home.class));
            finish();
        }

        loginEmail = (EditText) findViewById(R.id.username);
        loginPassword = (EditText) findViewById(R.id.password);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginFirebase();
            }
        });
    }

    private void loginFirebase() {
        String email = loginEmail.getText().toString();
        final String password = loginPassword.getText().toString();

        // checking fields
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Your email is still empty",
                    Toast.LENGTH_SHORT).show();
            return;

        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Your password is still empty",
                    Toast.LENGTH_SHORT).show();
            return;

        }

        // login user
        FirebaseClass.auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // if login not success
                        if (task.isSuccessful()) {
                            Toast.makeText(login.this,
                                    FirebaseClass.auth.getCurrentUser().getEmail().toString(),
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(login.this, Home.class);
                            startActivity(intent);

                        }else {
                            Toast.makeText(login.this,
                                    "Login Gagal! Username atau Password Salah!",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });
    }


    public void register(View view) {
        Intent startActivity = new Intent(login.this, register.class);
        startActivity(startActivity);
    }
}