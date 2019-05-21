package com.example.sophie.astrodomus.controllers;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.sophie.astrodomus.activities.MenuMain;
import com.example.sophie.astrodomus.models.Usuario;
import com.example.sophie.astrodomus.utils.Constans;
import com.example.sophie.astrodomus.utils.SendMail;
import com.example.sophie.astrodomus.utils.Validations;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class UsuarioControl implements Response.Listener<JSONObject>, Response.ErrorListener{

    RequestQueue request;
    JsonObjectRequest jsonrequest;
    private  Context context;
    private String username, password;
    private int process;
    private ArrayList<String> user=null;

    public void find(String username, String password, int prccess, Context context){
        this.context = context;
        request= Volley.newRequestQueue(context);
        this.username = username;
        this.password = password;
        this.process = prccess;


        String url = Constans.URL+ "usuario/"+username;
        jsonrequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonrequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Validations validations =  new Validations();
        validations.init(Validations.ERROR_SERVER, context);
    }

    @Override
    public void onResponse(JSONObject response) {
        String status = response.optString("status");


       if(status.equals("1")){
            JSONArray json  = response.optJSONArray("usuario");
        try {
            user = new ArrayList<>();
            JSONObject jsonObject = json.getJSONObject(0);
            user.add(jsonObject.optString("id_usuario"));
            user.add(jsonObject.optString("nombre1"));
            user.add(jsonObject.optString("nombre2"));
            user.add(jsonObject.optString("apellido1"));
            user.add(jsonObject.optString("apellido2"));
            user.add(jsonObject.optString("correo"));
            user.add(jsonObject.optString("clave"));
            user.add(jsonObject.optString("id_rol"));
            user.add(jsonObject.optString("id_estado"));

            if(process==0){
                validation(user);
            }else{
                SendMail sendMail = new SendMail(user.get(5), user.get(0), context);
                sendMail.execute();
            }

        }catch (Exception e){
            Validations validations = new Validations();
            validations.init(Validations.ERROR_SERVER, context);
        }
        }else{
            Validations v = new Validations();
            v.init(Validations.ERROR_LOGIN3, context);

        }


    }

    private void validation(ArrayList<String> user ) {
        Validations validations= new Validations();
        if(user.get(0).equals(username) && user.get(6).equals(password)){
            Intent intent = new Intent(context, MenuMain.class);
            intent.putStringArrayListExtra("user", user);
            context.startActivity(intent);

        }else if(user.get(0).equals(username)){
            validations.init(Validations.ERROR_LOGIN2, context);
        }
    }



}
