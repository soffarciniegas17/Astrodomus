package com.example.sophie.astrodomus.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.sophie.astrodomus.R;
import com.example.sophie.astrodomus.controllers.UsuarioControlDylan;
import com.example.sophie.astrodomus.models.Usuario;

public class UsuarioInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_info);

        Usuario user = new Usuario();
        UsuarioControlDylan service = new UsuarioControlDylan();
        service.find("06", user, this);

        EditText nombre1 = (EditText) findViewById(R.id.txt_nombre1);

        nombre1.setText(user.getNombre1());

    }
}
