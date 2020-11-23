package com.example.lawyerassistance;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    EditText edt_email,edt_pass;
    Button btn_login,btn_reg;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_email = findViewById(R.id.login_email);
        edt_pass = findViewById(R.id.login_pass);
        btn_login = findViewById(R.id.btn_login);
        btn_reg = findViewById(R.id.btn_logi_reg);
        firebaseAuth = FirebaseAuth.getInstance();

btn_login.setOnClickListener(v -> {
    String email = edt_email.getText().toString().trim();
    String pass = edt_pass.getText().toString().trim();

    if (TextUtils.isEmpty(email)) {
        Toast.makeText(login.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
        return;
    }

    if (TextUtils.isEmpty(pass)) {
        Toast.makeText(login.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
        return;

    }

    if (pass.length() < 6) {
        Toast.makeText(login.this, "Password too short", Toast.LENGTH_SHORT).show();
    }

    firebaseAuth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(login.this, task -> {
                if (task.isSuccessful())
                {
                    startActivity(new Intent(getApplicationContext(),Dashboard.class));
                }
                else
                {
                    Toast.makeText(login.this, "Invalid Email or Password!", Toast.LENGTH_SHORT).show();
                }
            });
});

        btn_reg.setOnClickListener(v -> {
            Intent next = new Intent(login.this,User_choice.class);
            startActivity(next);
        });
    }
}