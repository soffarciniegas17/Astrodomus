package com.example.sophie.astrodomus.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.example.sophie.astrodomus.R;
import com.example.sophie.astrodomus.controllers.PerfilControl;

public class Perfiles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfiles);

        GridView gridView = (GridView) findViewById(R.id.grid_perfil);
        PerfilControl control = new PerfilControl();
        control.find("05", this, gridView);
    }

    public void insertarPerfil(View v){
        Intent siguiente = new Intent(this, NewProfile.class);
        startActivity(siguiente);
    }

    public void cancelar(View v){

    }


}
