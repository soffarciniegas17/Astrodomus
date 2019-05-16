package com.example.sophie.astrodomus.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sophie.astrodomus.R;

/**
 * Created by Sophie on 5/05/2019.
 */

public class Validations {


    public static final String ERROR_LOGIN2="ERROR CONTRASEÑA";
    public static final String ERROR_LOGIN3="ERROR DATOS";
    public static final String ERROR_SERVER="ERROR SERVIDOR 500";
    private Dialog dialog;


    public void init(String ERROR, Context context){
        dialog= new Dialog(context);
        dialog.setContentView(R.layout.dialog_error);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        TextView desc= dialog.findViewById(R.id.id_desc_error),
                title=dialog.findViewById(R.id.id_titulo_error);

        Button exit = dialog.findViewById(R.id.id_button_error);

        switch (ERROR){

            case ERROR_LOGIN2:
                title.setText(ERROR_LOGIN2);
                desc.setText("La contraseña es incorrecta");
                break;
            case ERROR_LOGIN3:
                title.setText(ERROR_LOGIN3);
                desc.setText("No se pudo encontrar el usuario. Intente de nuevo.");
                break;
            case ERROR_SERVER:
                title.setText(ERROR_SERVER);
                desc.setText("Lo sentimos, hubo un problema con los servidores. Intenté más tarde.");
                break;
        }

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
}
