package com.example.sophie.astrodomus.controllers;

import android.app.DownloadManager;
import android.content.Context;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sophie.astrodomus.activities.UsuarioInfo;
import com.example.sophie.astrodomus.config.Constantes;
import com.example.sophie.astrodomus.models.Usuario;
import com.sun.mail.imap.protocol.BODY;

public class UpdateInfo implements Response.Listener<String>, Response.ErrorListener {

    RequestQueue request;
    StringRequest stringRequest;
    private Context context;
    private Usuario user;
    private UsuarioInfo usuarioInfo;

    public void update(Usuario user, Context context){
        this.context = context;
        this.user = user;
        request = Volley.newRequestQueue(context);

        String url = "http://"+ Constantes.ipdylan +"/ServiciosAdomus/Usuario/modificarInfo.php?id="+ user.getId_usuario()
                +"&nombre1="+ user.getNombre1() +"&nombre2="+ user.getNombre2()
                +"&apellido1="+ user.getApellido1() +"&apellido2="+ user.getApellido2()
                +"&correo="+ user.getCorreo();

        stringRequest = new StringRequest(Request.Method.GET, url,this, this);
        request.add(stringRequest);
        System.out.println(url);
    }

    @Override
    public void onResponse(String response) {
        System.out.println(response.toString());
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        System.out.println("Error Volley: " + error);
    }
}
