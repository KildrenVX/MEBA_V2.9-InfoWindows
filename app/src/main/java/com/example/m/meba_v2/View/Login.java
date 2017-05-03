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

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button btnIngresar;
    EditText txtUso,txtPas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUso = (EditText)findViewById(R.id.txtusu);
        txtPas = (EditText)findViewById(R.id.txtpass);
        btnIngresar = (Button)findViewById(R.id.btningresas);


        btnIngresar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Thread tr = new Thread()
        {
            @Override
            public void run() {
                final String resultado = enviarDatosGET(txtUso.getText().toString(),txtPas.getText().toString());
                // accesedamos al UI so cuando estamos en un hilo
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int r = obtDatosjson(resultado);
                        if(r>0)
                        {
                            Intent i = new Intent(getApplicationContext(),MainActivity.class);
                            i.putExtra("cod",txtUso.getText().toString());
                            startActivity(i);
                        }else
                        {
                            Toast.makeText(getApplicationContext(),"ERROR DE VALIDACION ",Toast.LENGTH_LONG).show();
                            Log.e("ERROR",resultado);
                        }
                    }
                });
            }
        };
        tr.start();

    }


    public String enviarDatosGET(String usu,String pas)
    {
        URL url = null;
        String linea="";
        int respuesta=0;
        StringBuilder resul=null;//recive la data
        try {
            url = new URL("http://192.168.0.14:80/Login.php?usu="+usu+"&pas="+pas);//la IP es mi PC , se requiere cambiarla
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();//al momento que la data sale, puede retornar una respuesta
            respuesta = connection.getResponseCode(); // si devuelve una respues lo guardamos en una variable

            resul=new StringBuilder();//Consumiendo el JSON en la respuesta
            if(respuesta==200)
            {
                InputStream in = new BufferedInputStream(connection.getInputStream());//TOMAMOS LA RESPUESTA
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));//leemos la respuesta

                //LLENAMOS LA VARIABLE "resul" con el dato que emos traido
                while ((linea=reader.readLine())!=null)
                {
                    resul.append(linea);//agregamos casa linia q retorne

                }

            }

        }catch (Exception e){
            Log.e("laweea",e.toString());
        }
        return resul.toString();
    }

    //atraves de este metodo, vemos si este JSON tiene Datps
    public int obtDatosjson(String response)
    {
        int res = 0;
        try {
            JSONArray json =  new JSONArray(response);
            if(json.length()>0)
            {
                res=1;
            }
        }catch (Exception e){}
        return  res;
    }
}
