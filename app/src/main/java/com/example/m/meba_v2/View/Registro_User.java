package com.example.m.meba_v2.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.m.meba_v2.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;

public class Registro_User extends AppCompatActivity  {

    public void json(String Correo, String Pass,int Edad, String Nombre , String Sex){
        //conexion a http
        AsyncHttpClient client = new AsyncHttpClient();
        String url="http://meba.esy.es/meba_connect/create_User.php?Nombre="+Nombre+"&Pass="+Pass+"&Edad="+Edad+"&Correo="+Correo+"&Sexo="+Sex;

        Log.e("probando y que saen", url.toString());
        //envio de parametros
        //RequestParams parametros = new RequestParams();
        //parametros.put("Edad",18);

        client.post(url, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String resul = new String(responseBody);
                    Log.e("estoy", "aqui");
                    try {
                        Log.e("estoy", "aqui2");
                        JSONArray jsonArray = new JSONArray(resul);
                        int ID = Integer.parseInt(jsonArray.getJSONObject(0).getString("usuID"));
                        //envio(ID);

                    } catch (Exception e) {
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro__user);
    }
}
