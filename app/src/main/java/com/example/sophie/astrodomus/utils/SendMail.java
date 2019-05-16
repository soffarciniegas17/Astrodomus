package com.example.sophie.astrodomus.utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sophie.astrodomus.R;
import com.example.sophie.astrodomus.controllers.PassChange;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class SendMail extends AsyncTask {

    private Context context;
    private Session session;
    private final String EMAIL ="adsi167adomus@gmail.com";
    private final String KEY ="SENA2019";
    private String email, message, code, usuario;

    public SendMail(String email, String usuario, Context context) {
        this.email = email;
        this.context = context;
        this.usuario = usuario;

        CodeRandom coderam = new CodeRandom();
        code = coderam.getCode();

        message = "<img src='https://i.pinimg.com/564x/4b/e4/38/4be4386bd1943f8180594573ae082f99.jpg' style='height: 80px;'> " +
                "<div style='background:#f3f3f3; width: 60%; padding: 20px'> " +
                "<h3>¡Hola!</h3>  <h3>Has solicitado el restablecimiento de contraseña. " +
                "Para ello deberás insertar el código que aparecerá en la pantalla.  </h3>" +
                "<div style='display: flex; flex-direction:column; align-items: center; " +
                "background: #0477BF;  padding: 5px; border: transparent; border-radius: 5px'> " +
                "<h1 style='color: #f3f3f3; font-size: 60px'>Código: "+code+"</h1> </div> " +
                "<h3>Si no has solicitado un restablecimiento de contraseña ignora este mensaje </h3>" +
                "<h4>Equipo ADSI 167- Astrodomus</h4></div>";
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();


       }

    @Override
    protected Object doInBackground(Object[] objects) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(props, new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL, KEY);
            }
        });

        try {
            MimeMessage mm = new MimeMessage(session);
            mm.setFrom(new InternetAddress(EMAIL));
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            mm.setSubject("Restablecimiento de contraseña");
            mm.setContent(message, "text/html");
            Transport.send(mm);
        }catch (Exception e){}

        return null;

    }


    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_rest);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button button = dialog.findViewById(R.id.id_button_rest);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                validationCode();
            }
        });
        dialog.show();

    }


    public void validationCode(){
        final Dialog validation = new Dialog(context);
        validation.setContentView(R.layout.dialog_confirm);
        validation.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        final EditText c1 = validation.findViewById(R.id.id_c1_confirm),
        c2= validation.findViewById(R.id.id_c2_confirm),
        c3= validation.findViewById(R.id.id_c3_confirm),
        c4= validation.findViewById(R.id.id_c4_confirm);

        Button buton = validation.findViewById(R.id.d_button_confirm);

        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = c1.getText().toString() +""+c2.getText().toString()+""+c3.getText().toString()+ ""+ c4.getText().toString();
                if(message.equals(code)){
                    validation.dismiss();
                    PassChange change = new PassChange(usuario, context);

                }else{
                    TextView textView = validation.findViewById(R.id.id_text_confirm);
                    textView.setText("El código es incorrecto");
                }

            }
        });

        validation.show();



    }
}
