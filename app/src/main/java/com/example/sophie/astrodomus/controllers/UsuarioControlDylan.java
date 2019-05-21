package com.example.sophie.astrodomus.controllers;

import android.content.Context;
import android.content.Intent;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.sophie.astrodomus.activities.MenuMain;
import com.example.sophie.astrodomus.config.Constantes;
import com.example.sophie.astrodomus.models.Usuario;
import com.example.sophie.astrodomus.utils.SendMail;
import com.example.sophie.astrodomus.utils.Validations;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class UsuarioControlDylan implements Response.Listener<JSONObject>, Response.ErrorListener{

    RequestQueue request;
    JsonObjectRequest jsonrequest;
    private  Context context;
    private  String iduser;
    private Usuario user = new Usuario();

    public void find(String iduser, Usuario user, Context context){
        this.context = context;
        this.user = user;
        request= Volley.newRequestQueue(context);
        this.iduser = iduser;




        String url = "http://"+Constantes.ipdylan+"/ServiciosAdomus/Usuario/consultarUsuario.php?id="+ iduser;
        jsonrequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonrequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        System.out.println(error);
    }

    @Override
    public void onResponse(JSONObject response) {
        String status = response.optString("status");


       if(status.equals("1")){
            JSONArray json  = response.optJSONArray("usuario");
        try {
            for (int i = 0; i > json.length(); i++){
                user.setId_usuario(json.getJSONArray(i).getString(0));
                user.setNombre1(json.getJSONArray(i).getString(1));
                user.setNombre2(json.getJSONArray(i).getString(2));
                user.setApellido1(json.getJSONArray(i).getString(3));
                user.setApellido2(json.getJSONArray(i).getString(4));
                user.setUsuario(json.getJSONArray(i).getString(5));
                user.setCorreo(json.getJSONArray(i).getString(6));
                user.setClave(json.getJSONArray(i).getString(7));
                user.setRol(json.getJSONArray(i).getString(8));
                user.setEstado(json.getJSONArray(i).getString(9));
            }
        }catch (Exception e){

        }
        }


    }


}
