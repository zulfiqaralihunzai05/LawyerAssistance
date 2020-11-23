package com.example.lawyerassistance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {
Button profile,lawyers,laws,recommend,case_study;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        profile = findViewById(R.id.btn_profile);
        lawyers = findViewById(R.id.btn_lawyer);
        laws = findViewById(R.id.btn_laws);
        recommend = findViewById(R.id.btn_recommemd);
        case_study = findViewById(R.id.btn_case_study);

        profile.setOnClickListener(v -> Toast.makeText(Dashboard.this, "Profile Clicked", Toast.LENGTH_SHORT).show());

        lawyers.setOnClickListener(v -> Toast.makeText(Dashboard.this, "Lawyers Clicked", Toast.LENGTH_SHORT).show());

    }
}