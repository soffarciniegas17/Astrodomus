package com.example.sophie.astrodomus.controllers;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.example.sophie.astrodomus.R;
import com.example.sophie.astrodomus.utils.Validations;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.FileNameMap;


public class PassChange implements Response.Listener<JSONObject>, Response.ErrorListener {

    Context context;
    JsonObjectRequest jsonrequest;
    RequestQueue request;
    private String usuario;
    private Dialog dialog;

    public PassChange(String usuario, Context context) {
        this.context = context;
        this.usuario = usuario;

        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_password);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button button = dialog.findViewById(R.id.id_button_password);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edit1= dialog.findViewById(R.id.id_pass_password);
                EditText edit2 = dialog.findViewById(R.id.id_confirm_password);

                if(edit1.getText().toString().equals(edit2.getText().toString())){
                    rest(edit1.getText().toString());
                }else{
                    edit2.setError("Las contraseñas no coinciden");
                }
            }
        });

        dialog.show();



    }

    public void rest(String password){
        String url = "http://192.168.43.129:8000/api/usuario/"+ usuario+ "?clave="+password;
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
            dialog.dismiss();
            final Dialog finish = new Dialog(context);
            finish.setContentView(R.layout.dialog_rest);

            TextView title = finish.findViewById(R.id.id_title_rest),
            text = finish.findViewById(R.id.id_text_rest);

            title.setText("Contraseña restablecida");
            text.setText("La contraseña ha sido cambiada, ingresa con los nuevos datos");

            Button button= finish.findViewById(R.id.id_button_rest);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish.dismiss();
                }
            });

            finish.show();
        }else{
            Validations validations =  new Validations();
            validations.init(Validations.ERROR_SERVER, context);
        }

    }
}
