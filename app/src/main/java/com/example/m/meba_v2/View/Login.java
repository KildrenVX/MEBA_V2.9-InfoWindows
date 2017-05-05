package com.example.m.meba_v2.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.m.meba_v2.R;
import com.example.m.meba_v2.View.MainActivity;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InterfaceAddress;
import java.net.URL;

public class Login extends AppCompatActivity {

    public void json(String Correo, String Pass){
        //conexion a http
        AsyncHttpClient client = new AsyncHttpClient();
        String url="http://meba.esy.es/meba_connect/login.php?Correo="+Correo+"&Pass="+Pass;

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
                        envio(ID);

                    } catch (Exception e) {
                        Toast.makeText(Login.this, "Usuario no existe.", Toast.LENGTH_SHORT).show();
                        txtUso.setText("");
                        txtPas.setText("");
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    Button btnIngresar;
    EditText txtUso,txtPas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUso = (EditText)findViewById(R.id.txtusu);
        txtPas = (EditText)findViewById(R.id.txtpass);
        btnIngresar = (Button)findViewById(R.id.btningresas);
        //btnIngresar.setOnClickListener(this);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                json(txtUso.getText().toString(),txtPas.getText().toString());
            }
        });

    }

    public void envio(int ID){
        Intent perfil = new Intent(this, Perfil.class);
        perfil.putExtra("datos", ID);
        startActivity(perfil);

    }


    public void Registroclick (View view)
    {
        Intent i = new Intent(this, Registro_User.class);
        startActivity(i);
    }

}
