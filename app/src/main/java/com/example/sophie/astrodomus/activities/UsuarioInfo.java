package com.example.sophie.astrodomus.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.sophie.astrodomus.R;
import com.example.sophie.astrodomus.controllers.SelectInfo;
import com.example.sophie.astrodomus.controllers.UpdateInfo;
import com.example.sophie.astrodomus.models.Usuario;

public class UsuarioInfo extends AppCompatActivity {

    int i = 0;
    Usuario user = new Usuario();
    boolean status = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_info);

        final SelectInfo service = new SelectInfo(this);
        final EditText nombre1 = (EditText) findViewById(R.id.txt_nombre1);
        final EditText nombre2 = (EditText) findViewById(R.id.txt_nombre2);
        final EditText apellido1 = (EditText) findViewById(R.id.txt_apellido1);
        final EditText apellido2 = (EditText) findViewById(R.id.txt_apellido2);
        final EditText usuario = (EditText) findViewById(R.id.txt_usuario);
        final EditText correo = (EditText) findViewById(R.id.txt_correo);
        final EditText rol = (EditText) findViewById(R.id.txt_rol);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    service.find("06", user, UsuarioInfo.this);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            nombre1.setText(user.getNombre1());
                            nombre2.setText(user.getNombre2());
                            apellido1.setText(user.getApellido1());
                            apellido2.setText(user.getApellido2());
                            usuario.setText(user.getUsuario());
                            correo.setText(user.getCorreo());
                            rol.setText(user.getRol());
                        }
                    });
                    if (status) {
                        i = 0;
                        break;
                    } else {
                        System.out.println("Espere " + i);
                    }

                    i++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void guardarCambios(View v){
        EditText nombre1 = (EditText) findViewById(R.id.txt_nombre1);
        EditText nombre2 = (EditText) findViewById(R.id.txt_nombre2);
        EditText apellido1 = (EditText) findViewById(R.id.txt_apellido1);
        EditText apellido2 = (EditText) findViewById(R.id.txt_apellido2);
        EditText correo = (EditText) findViewById(R.id.txt_correo);

        user.setNombre1(nombre1.getText().toString());
        user.setNombre2(nombre2.getText().toString());
        user.setApellido1(apellido1.getText().toString());
        user.setApellido2(apellido2.getText().toString());
        user.setCorreo(correo.getText().toString());

        System.out.println("nombre" + user.getNombre1());

        UpdateInfo serviceUpdate = new UpdateInfo();
        serviceUpdate.update(user, this);

    }

    public void cancelar(View v){

    }

    public void response(Usuario user, boolean status){
        this.user = user;
        this.status = status;
    }
}
