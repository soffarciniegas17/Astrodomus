package com.example.sophie.astrodomus.activities;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sophie.astrodomus.R;
import com.example.sophie.astrodomus.config.Constantes;
import com.example.sophie.astrodomus.models.Perfil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class NewProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_profile);

        llamarAmbientes();
    }

    public void llamarAmbientes(){

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://"+ Constantes.ipdylan +"/ServiciosAdomus/Control/BusquedaAmbientes.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ArrayList<String> listAmb = new ArrayList<String>();
                ArrayList<String> listId = new ArrayList<String>();
                JSONArray json = response.optJSONArray("ambientes");
                for (int i = 0; i < json.length(); i++) {
                    try {
                        JSONArray jsonArray = json.getJSONArray(i);
                        listAmb.add(jsonArray.optString(1));
                        listId.add(jsonArray.optString(0));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                llenarSpinner(listAmb, listId);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(NewProfile.this, "Error en el servidor", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
    }

    Spinner ambientes;
    String idposition;

    public void llenarSpinner(ArrayList<String> listAmb, final ArrayList<String> listId){
        ambientes = (Spinner) findViewById(R.id.spinner_ambiente);
        ambientes.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listAmb));
        ambientes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idposition = listId.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void guardarPerfil(View v){
        TextView tag = (TextView) findViewById(R.id.text_tag);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://"+ Constantes.ipdylan +"/ServiciosAdomus/Perfil/insertarPerfil.php?id_usuario=05&id_ambiente="+ idposition +"&tag="+ tag.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    AlertDialog.Builder ventana = new AlertDialog.Builder(NewProfile.this);
                if (response.equalsIgnoreCase("true")){
                    ventana.setIcon(android.R.drawable.ic_dialog_info)
                            .setTitle("Perfil guardado")
                            .setMessage("Se ha guardado el perfil satisfactoriamente")
                            .setPositiveButton("Aceptar", null)
                            .show();
                }else{
                    ventana.setIcon(android.R.drawable.ic_dialog_info)
                            .setTitle("Error al guardar")
                            .setMessage("Ha ocurrido un error al guardar")
                            .setPositiveButton("Aceptar", null)
                            .show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    Toast.makeText(NewProfile.this, "Error en servidor", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);
    }
}
