package com.example.lawyerassistance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class User_choice extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton rdb_lawyer,rdb_client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_choice);

        radioGroup = findViewById(R.id.radiogroup);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            rdb_lawyer = User_choice.this.findViewById(R.id.rdb_lawyer);
            rdb_client = User_choice.this.findViewById(R.id.rdb_client);

            if (rdb_client.isChecked()) {
                Intent move_client = new Intent(User_choice.this, Client_signup.class);
                User_choice.this.startActivity(move_client);
            } else {
                Intent move_lawyer = new Intent(User_choice.this, Lawyer_signup.class);
                User_choice.this.startActivity(move_lawyer);
            }
        });
    }
}