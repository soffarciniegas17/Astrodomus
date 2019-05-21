package com.example.sophie.astrodomus.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import com.example.sophie.astrodomus.R;
import com.example.sophie.astrodomus.controllers.ReporteControl;

public class MenuMain extends AppCompatActivity {

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);

        gridView = findViewById(R.id.grid_main);
        ReporteControl control = new ReporteControl();
        control.index(this, gridView);
    }


    public void logout(View view) {
        startActivity(new Intent(this, Login.class));
        finish();
    }
}
