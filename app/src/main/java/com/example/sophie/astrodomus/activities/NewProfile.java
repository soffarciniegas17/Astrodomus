package com.example.sophie.astrodomus.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.sophie.astrodomus.R;
import com.example.sophie.astrodomus.config.Constantes;
import com.example.sophie.astrodomus.models.Perfil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewProfile extends AppCompatActivity {

    Spinner ambientes;
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_profile);

        llenarAmbiente();
    }

    public void llenarAmbiente(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        ambientes = (Spinner) findViewById(R.id.spinner_ambiente);
        String url = "http://"+ Constantes.ipdylan2 +"/ServiciosAdomus/Control/BusquedaAmbientes.php";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ArrayList<String> list = new ArrayList<>();
                JSONArray json = response.optJSONArray("ambientes");
                for (int i = 0; i < json.length(); i++) {
                    try {
                        JSONObject jsonObject = json.getJSONObject(i);
                        list.add(jsonObject.optString("ambiente"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(NewProfile.this, "Error en el servidor", Toast.LENGTH_SHORT).show();
            }
        });

        ambientes.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list));

    }
}
