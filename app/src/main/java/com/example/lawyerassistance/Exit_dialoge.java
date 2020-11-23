package com.example.lawyerassistance;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;

public class Exit_dialoge extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit_dialoge);
    }

   @Override
    public void onBackPressed(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(Exit_dialoge.this);
        builder.setMessage("Are You Sure Want to Exit!");
        builder.setCancelable(true);
        builder.setNegativeButton("Yes", (dialog, which) -> dialog.cancel());
        builder.setPositiveButton("No", (dialog, which) -> finish());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
   }
}