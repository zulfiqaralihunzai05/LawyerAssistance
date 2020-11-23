package com.example.lawyerassistance;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Lawyer_signup extends AppCompatActivity {
    EditText lawyer_name, gmail, pass_lawyer, cnfrmedpass, ph_lawyer, lawyer_city;
    Button btn_lawyerreg;
    RadioButton Adv_high,Adv_supreme;

    DatabaseReference databaseReference;
    String lawyer_category;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lawyer_signup);
        getSupportActionBar().setTitle("Signup Form");

        lawyer_name = findViewById(R.id.lawyer_name);
        gmail = findViewById(R.id.lawyer_email);
        pass_lawyer = findViewById(R.id.lawyer_pass);
        cnfrmedpass = findViewById(R.id.lawyer_cnf);
        ph_lawyer = findViewById(R.id.law_phone);
        lawyer_city = findViewById(R.id.law_city);
        btn_lawyerreg = findViewById(R.id.btn_lawyer_reg);
        Adv_high = findViewById(R.id.rdb_cat1);
        Adv_supreme = findViewById(R.id.rdb_cat2);

        databaseReference = FirebaseDatabase.getInstance().getReference("Lawyer");
        firebaseAuth = FirebaseAuth.getInstance();

        btn_lawyerreg.setOnClickListener(v -> {
            String name = lawyer_name.getText().toString().trim();
            String Email = gmail.getText().toString().trim();
            String password = pass_lawyer.getText().toString().trim();
            String cnf_pass = cnfrmedpass.getText().toString().trim();
            String phone = ph_lawyer.getText().toString().trim();
            String city = lawyer_city.getText().toString().trim();

            if (Adv_high.isChecked()){
                lawyer_category = "Advocate High Court";
            }

            if (Adv_supreme.isChecked()){
                lawyer_category = "Advocate Supreme Court";
            }

            if (TextUtils.isEmpty(name)) {
                Toast.makeText(Lawyer_signup.this, "Please Enter Full name", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(Email)) {
                Toast.makeText(Lawyer_signup.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(Lawyer_signup.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(cnf_pass)) {
                Toast.makeText(Lawyer_signup.this, "Please Enter Confirm Password", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(phone)) {
                Toast.makeText(Lawyer_signup.this, "Please Enter Phone no", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(city)) {
                Toast.makeText(Lawyer_signup.this, "Please Enter City", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(cnf_pass)) {
                Toast.makeText(Lawyer_signup.this, "Please Enter Correct Password", Toast.LENGTH_SHORT).show();
            }

            if (password.length() < 6) {
                Toast.makeText(Lawyer_signup.this, "Password too short", Toast.LENGTH_SHORT).show();
            }

            if (password.equals(cnf_pass)){
                firebaseAuth.createUserWithEmailAndPassword(Email, password)
                        .addOnCompleteListener(Lawyer_signup.this, task -> {
                            if (task.isSuccessful())
                            {
                               Lawyer information = new Lawyer(
                                       name,
                                       Email,
                                       password,
                                       phone,
                                       city,
                                       lawyer_category
                               );


                               FirebaseDatabase.getInstance().getReference("Lawyer")
                                       .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                                       .setValue(information).addOnCompleteListener(task1 -> {
                                           Toast.makeText(Lawyer_signup.this, "Registration Completed", Toast.LENGTH_SHORT).show();
                                           startActivity(new Intent(getApplicationContext(),login.class));
                                       });
                            }
                            else
                                {
                                    Toast.makeText(Lawyer_signup.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        });
            }



        });
    }
}