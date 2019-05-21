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
import com.example.sophie.astrodomus.lists.ActivityAdapter;
import com.example.sophie.astrodomus.models.Reporte;
import com.example.sophie.astrodomus.utils.Constans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ReporteControl implements Response.Listener<JSONObject>, Response.ErrorListener {

    private RequestQueue request;
    private JsonObjectRequest jsonObject;
    private Context context;
    private GridView gridView;

    public void index(Context context, GridView grid){
        this.context = context;

        this.gridView = grid ;

        request = Volley.newRequestQueue(context);
        String url = Constans.URL+ "Reporte/consultarDesde.php";
        jsonObject = new JsonObjectRequest(Request.Method.GET,url, null, this, this );
        request.add(jsonObject);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(context, "Error en el servidor", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        ArrayList<Reporte> list = new ArrayList<>();
        JSONArray json = response.optJSONArray("reportes");
        for (int i =0; i < json.length(); i++){
            try {
                JSONObject jsonObject = json.getJSONObject(i);
                list.add(new Reporte(jsonObject.optString("id_reporte"),
                        jsonObject.optString("nombre1"),
                        jsonObject.optString("id_tipoReporte"),
                        jsonObject.optString("tipoReporte"),
                        jsonObject.optString("ambiente"),
                        jsonObject.optString("hora"),
                        jsonObject.optString("fecha"),
                        jsonObject.optString("detalle")));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        llenar(list);
    }

    public void llenar (ArrayList<Reporte> list){
        ActivityAdapter adapter = new ActivityAdapter(list, context, R.layout.item_activity);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
