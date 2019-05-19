package com.example.sophie.astrodomus.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.sophie.astrodomus.R;

public class MenuMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);
    }


    public void logout(View view) {
        startActivity(new Intent(this, Login.class));
        finish();
    }
}
