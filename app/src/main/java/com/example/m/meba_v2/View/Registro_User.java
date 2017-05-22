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


        txtNombre = (EditText)findViewById(R.id.nameregist);
        txtCorreo = (EditText)findViewById(R.id.mailregis);
        txtPass = (EditText)findViewById(R.id.pass);
        txtConfirmPass = (EditText)findViewById(R.id.passr2);
        txtEdad = (EditText)findViewById(R.id.edadr);

        Log.i(" ",txtNombre.toString());

        btnRegistrar = (Button)findViewById(R.id.btnRegistrar);
        btnCancelar = (Button)findViewById(R.id.btnCancelar);


        btnRegistrar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                  try {
                      JsonRegistro(txtCorreo.getText().toString(), txtPass.getText().toString(),
                              txtEdad.getText().toString(), txtNombre.getText().toString(),
                              txtSexo.getText().toString());
                      Toast.makeText(null, "campos completos, json", Toast.LENGTH_SHORT).show();
                      Log.e("campos completos, json2", "");
                  }catch (Exception e ){
                      Log.e("error insert",e.toString());
                  }

            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(),Login.class);
                startActivity(I);
            }
        });
    }



    public void JsonRegistro (String Correo, String Pass,String Edad, String Nombre , String Sexo)
    {
        //conexion a http
        AsyncHttpClient client = new AsyncHttpClient();
        String url="http://meba.esy.es/meba_connect/Crear_Usuario.php";
        Log.i("probando ", url.toString());

       RequestParams parametros = new RequestParams();
        parametros.put("Nombre",Nombre);
        parametros.put("Pass",Pass);
        parametros.put("Edad",Edad);
        parametros.put("Correo",Correo);
        parametros.put("Sexo",Sexo);

        client.post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    try {
                    String resul = new String(responseBody);
                    Log.e("Conexion exitosa", resul.toString());
                        Intent I = new Intent(getApplicationContext(),Login.class);
                        startActivity(I);



                    } catch (Exception e) {
                        Log.e("Error conexion fallida ",e.toString());
                        txtNombre.setText("");
                        txtEdad.setText("");
                        txtPass.setText("");
                        txtConfirmPass.setText("");
                        txtCorreo.setText("");
                        txtSexo.setText("");
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }


}
