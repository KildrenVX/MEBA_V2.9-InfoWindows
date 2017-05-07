package com.example.m.meba_v2.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.m.meba_v2.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

public class Registro_User extends AppCompatActivity  {

    Button btnRegistrar;// crear botones y campos de texto
    EditText txtNombre,txtSexo,txtCorreo,txtPass,txtConfirmPass,txtEdad;

    public void json(String Correo, String Pass,int Edad, String Nombre , String Sexo){
        //conexion a http
        AsyncHttpClient client = new AsyncHttpClient();
        String url="http://meba.esy.es/meba_connect/create_User.php?Nombre="+Nombre+"&Pass="+Pass+
                "&Edad="+Edad+"&Correo="+Correo+"&Sexo="+Sexo; // envia parametros po la URL
        Log.e("probando y que saen", url.toString());

      /*  RequestParams parametros = new RequestParams();
        parametros.put("Nombre",Nombre);
        parametros.put("Pass",Pass);
        parametros.put("Edad",Edad);
        parametros.put("Correo",Correo);
        parametros.put("Sexo",Sexo);
*/

        client.post(url, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String resul = new String(responseBody);
                    Log.e("Conexion exitosa", "");
                    //si le envie los parametros por la url entonces se creo el usuario ???
                    try {
                        Log.e("estoy", "aqui2");
                        //JSONArray jsonArray = new JSONArray(resul);
                        //int ID = Integer.parseInt(jsonArray.getJSONObject(0).getString("usuID"));
                        //envio(ID);

                    } catch (Exception e) {
                        Log.e("Error",e.toString());
                       // Toast.makeText(Login.this, "Usuario no existe.", Toast.LENGTH_SHORT).show();
                       // txtUso.setText("");
                        //txtPas.setText("");
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        txtNombre = (EditText)findViewById(R.id.nameregist);
        btnRegistrar = (Button)findViewById(R.id.btnregistrar);
        txtCorreo = (EditText)findViewById(R.id.mailregis);
        txtPass = (EditText)findViewById(R.id.pass);
        txtConfirmPass = (EditText)findViewById(R.id.passr2);
        txtEdad = (EditText)findViewById(R.id.edadr);
       final int Edad = Integer.parseInt(txtEdad.getText().toString());


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                json(txtCorreo.getText().toString(),txtPass.getText().toString(),
                        Edad,txtNombre.getText().toString(),
                        txtSexo.getText().toString());
            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro__user);


    }
}
