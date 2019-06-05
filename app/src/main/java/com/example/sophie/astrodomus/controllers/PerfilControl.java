package com.example.sophie.astrodomus.controllers;

import android.content.Context;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.sophie.astrodomus.R;
import com.example.sophie.astrodomus.config.Constantes;
import com.example.sophie.astrodomus.lists.PerfilAdapter;
import com.example.sophie.astrodomus.models.Perfil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class PerfilControl implements Response.Listener<JSONObject>, Response.ErrorListener {

    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    private Context context;
    private String id_user;
    GridView gridView;

    public void find(String id_user, Context context, GridView gridView){
        this.id_user = id_user;
        this.context = context;
        this.gridView = gridView;
        requestQueue = Volley.newRequestQueue(context);

        String url = "http://"+ Constantes.ipdylan2 +"/ServiciosAdomus/Perfil/consultarPerfiles.php?id="+ id_user;

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        requestQueue.add(jsonObjectRequest);
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        System.out.println(error);
        Toast.makeText(context, "Error en el servidor", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        ArrayList<Perfil> list = new ArrayList<>();
        JSONArray json = response.optJSONArray("perfiles");
        for (int i = 0; i < json.length(); i++) {
            try {
                JSONObject jsonObject = json.getJSONObject(i);
                list.add(new Perfil(jsonObject.optString("id_perfil"),
                        jsonObject.optString("nombre1"),
                        jsonObject.optString("ambiente"),
                        jsonObject.optString("tag")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        llenar(list);
    }

    public void llenar(ArrayList<Perfil> list){
        PerfilAdapter adapter = new PerfilAdapter(list, context, R.layout.item_perfil);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}

