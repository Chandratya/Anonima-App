package com.krein.chandratya.anonimaapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity implements View.OnClickListener {
    private Button buttonRegis;
    private EditText txtEmail;
    private EditText txtPass;
    private TextView txtRegis;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        buttonRegis = (Button) findViewById(R.id.buttonRegis);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPass = (EditText) findViewById(R.id.txtPass);

        buttonRegis.setOnClickListener(this);
    }

public void regisUser(){
        String email = txtEmail.getText().toString().trim();
        String pass = txtPass.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Isi email terlebih dahulu", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this, "Isi password terlebih dahulu", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Mendaftarkan...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.hide();
                    Handler mHandler = new Handler();
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run(){
                            Intent start = new Intent(register.this, login.class);
                            startActivity(start);
                            finish();
                            Toast.makeText(register.this, "Registrasi Berhasil!", Toast.LENGTH_SHORT).show();
                        }
                    }, 1000L);
                }else{
                    Toast.makeText(register.this, "GAGAL, Coba lagi!", Toast.LENGTH_SHORT).show();
                    progressDialog.hide();
                }
            }
        });

}

    @Override
    public void onClick(View view) {
        if(view == buttonRegis){
            regisUser();
        }
    }
}

