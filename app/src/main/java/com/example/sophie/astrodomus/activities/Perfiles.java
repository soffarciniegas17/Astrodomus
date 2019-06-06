package com.example.sophie.astrodomus.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.example.sophie.astrodomus.R;
import com.example.sophie.astrodomus.controllers.PerfilControl;
import com.example.sophie.astrodomus.models.Perfil;

public class Perfiles extends AppCompatActivity {

    PerfilControl control;
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfiles);
    }

    public void insertarPerfil(View v){
        Intent siguiente = new Intent(this, NewProfile.class);
        startActivity(siguiente);
    }

    public void cancelar(View v){

    }

    @Override
    protected void onResume() {
        super.onResume();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (true){
                    gridView = (GridView) findViewById(R.id.grid_perfil);
                    control = new PerfilControl();
                    control.find("05", Perfiles.this, gridView);

                }

            }
        });

    }
}
