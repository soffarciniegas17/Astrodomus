package com.example.sophie.astrodomus.activities;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.sophie.astrodomus.R;
import com.example.sophie.astrodomus.activities.MenuMain;
import com.example.sophie.astrodomus.controllers.UsuarioControl;
import com.example.sophie.astrodomus.models.Usuario;
import com.example.sophie.astrodomus.utils.Validations;

public class Login extends AppCompatActivity {


    private ViewFlipper viewFlipper;
    private EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       init();


    }


    public void init(){
        int images [] = {R.drawable.backslide01, R.drawable.backslide11, R.drawable.backslide3};
        viewFlipper = findViewById(R.id.slide);

        for(int img: images){
            slideImage(img);
        }
        username = findViewById(R.id.user_login);
        password = findViewById(R.id.pass_login);


    }

    public void slideImage(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }

    public void login(View view) {
        String user = username.getText().toString();
        String pass = password.getText().toString();
        if(user.equals("") && pass.equals("")){
            username.setError("Campo vacio");
            password.setError("Campo vacio");
        }else if(user.equals("")){
            username.setError("Campo vacio");
        }else if(pass.equals("")){
            password.setError("Campo vacio");
        }else{
            UsuarioControl usuarioControl = new UsuarioControl();
            usuarioControl.find(username.getText().toString(), password.getText().toString(), 0,  this);

        }


    }

    public void email(View view) {
        if(username.getText().toString().equals("")){
            username.setError("Debe ingresar un usuario.");
        }else{
            UsuarioControl usuarioC = new UsuarioControl();
            usuarioC.find(username.getText().toString(), "", 1, this);
        }

    }
}
