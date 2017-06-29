package com.example.m.meba_v2.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.m.meba_v2.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;


public class Registro_User extends AppCompatActivity  {

    Button btnRegistrar,btnCancelar;// crear botones y campos de texto
    EditText txtNombre,txtSexo,txtCorreo,txtPass,txtConfirmPass,txtEdad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro__user);

        txtNombre = (EditText) findViewById(R.id.nameregist);
        txtCorreo = (EditText) findViewById(R.id.mailregis);
        txtPass = (EditText) findViewById(R.id.pass);
        txtConfirmPass = (EditText) findViewById(R.id.passr2);
        txtEdad = (EditText) findViewById(R.id.edadr);
        txtSexo = (EditText) findViewById(R.id.sexo);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    json();
                } catch (Exception e) {
                    Log.e("error json", e.toString());
                }

            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), Login.class);
                startActivity(I);
            }
        });
    }

    public void json(){
        AsyncHttpClient client = new AsyncHttpClient();
        //String url="http://meba.esy.es/meba_connect/Crear_Usuario.php?";
        String url="http://192.168.0.14/meba_connect/Crear_Usuario.php?";

        //192.168.0.12
        RequestParams parametros = new RequestParams();//enviar parametros a la URL
        parametros.put("Nombre", txtNombre.getText().toString());
        parametros.put("Pass", txtPass.getText().toString());
        parametros.put("Edad", Integer.parseInt(txtEdad.getText().toString()));
        parametros.put("Correo", txtCorreo.getText().toString());
        parametros.put("Sexo", txtSexo.getText().toString());

        client.post(url, parametros, new AsyncHttpResponseHandler() { //
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200){
                    Log.i("json", new String(responseBody));
                    Log.i("status", Integer.toString(statusCode));
                    Toast.makeText(Registro_User.this, "Usuario Creado Exitosamente", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.i("status",Integer.toString(statusCode));
                Log.i("Responsebody",responseBody.toString());
                Log.i("error",error.toString());
            }
        });


    }
}
