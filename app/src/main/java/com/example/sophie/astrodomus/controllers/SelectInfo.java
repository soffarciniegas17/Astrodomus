package com.example.sophie.astrodomus.controllers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.sophie.astrodomus.activities.MenuMain;
import com.example.sophie.astrodomus.activities.UsuarioInfo;
import com.example.sophie.astrodomus.config.Constantes;
import com.example.sophie.astrodomus.models.Usuario;
import com.example.sophie.astrodomus.utils.SendMail;
import com.example.sophie.astrodomus.utils.Validations;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class SelectInfo implements Response.Listener<JSONArray>, Response.ErrorListener{

    RequestQueue request;
    JsonArrayRequest jsonrequest;
    private  Context context;
    private  String iduser;
    private Usuario user;
    private UsuarioInfo usuarioInfo;
    public SelectInfo(UsuarioInfo usuarioInfo) {
        this.usuarioInfo = usuarioInfo;
    }

    public void find(String iduser, Usuario user, Context context){
        this.context = context;
        this.user = user;
        request= Volley.newRequestQueue(context);
        this.iduser = iduser;


        String url = "http://"+Constantes.ipdylan+"/ServiciosAdomus/Usuario/consultarUsuario.php?id=" + iduser;
        jsonrequest = new JsonArrayRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonrequest);

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(context, "Error en el servidor", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONArray response) {
        user = new Usuario();
        try {
            user.setId_usuario(response.optString(0));
            user.setNombre1(response.optString(1));
            user.setNombre2(response.optString(2));
            user.setApellido1(response.optString(3));
            user.setApellido2(response.optString(4));
            user.setUsuario(response.optString(5));
            user.setCorreo(response.optString(6));
            user.setClave(response.optString(7));
            user.setRol(response.optString(8));
            user.setEstado(response.optString(9));
            boolean status = true;

            usuarioInfo.response(user, status);

        }catch (Exception e){
            System.out.println("Error: " + e);
        }

    }

}
