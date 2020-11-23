package com.example.lawyerassistance;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Client_signup extends AppCompatActivity {

    EditText client_name, edt_email, txt_pass, ed_cnf, text_ph, cl_city;
    Button btn_register;

    DatabaseReference databaseReference;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_signup);
        getSupportActionBar().setTitle("Signup Form");

        client_name = findViewById(R.id.cli_name);
        edt_email = findViewById(R.id.cli_email);
        txt_pass = findViewById(R.id.cli_pass);
        ed_cnf = findViewById(R.id.cli_cnf);
        text_ph = findViewById(R.id.cli_phone);
        cl_city = findViewById(R.id.cli_city);
        btn_register = findViewById(R.id.btn_cli_reg);

        databaseReference = FirebaseDatabase.getInstance().getReference("Client");
        mAuth = FirebaseAuth.getInstance();

        btn_register.setOnClickListener(v -> {
            String name = client_name.getText().toString().trim();
            String Email = edt_email.getText().toString().trim();
            String password = txt_pass.getText().toString().trim();
            String cnf_pass = ed_cnf.getText().toString().trim();
            String phone = text_ph.getText().toString().trim();
            String city = cl_city.getText().toString().trim();

            if (TextUtils.isEmpty(name)) {
                Toast.makeText(Client_signup.this, "Please Enter Full name", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(Email)) {
                Toast.makeText(Client_signup.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(Client_signup.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(cnf_pass)) {
                Toast.makeText(Client_signup.this, "Please Enter Confirm Password", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(phone)) {
                Toast.makeText(Client_signup.this, "Please Enter Phone no", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(city)) {
                Toast.makeText(Client_signup.this, "Please Enter City", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(cnf_pass)) {
                Toast.makeText(this, "Please Enter Correct Password", Toast.LENGTH_SHORT).show();
            }

            if (password.length() < 6) {
                Toast.makeText(this, "Password too short", Toast.LENGTH_SHORT).show();
            }

            if (password.equals(cnf_pass)) {

                mAuth.createUserWithEmailAndPassword(Email, password)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful())
                            {
                                client information = new client(
                                        name,
                                        Email,
                                        password,
                                        phone,
                                        city
                                );

                                FirebaseDatabase.getInstance().getReference("Client")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(information).addOnCompleteListener(task1 -> {
                                            Toast.makeText(Client_signup.this, "Registration Completed", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(),login.class));
                                        });

                            }
                            else
                                {
                                    Toast.makeText(Client_signup.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                }

                            // ...
                        });
            }
        });
    }
}

