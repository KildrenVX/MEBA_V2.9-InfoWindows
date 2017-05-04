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

    /*@Override
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
    public String enviarDatosGET(String Correo,String password)
    {
        URL url = null;
        String linea="";
        int respuesta=0;
        StringBuilder resul=null;//recive la data
        try {
            url = new URL("http://meba.esy.es/meba_connect/login.php?Correo="+Correo+"&Pass="+password);//dirrecion url del servidor
            //http://meba.esy.es/meba_connect/login.php?Nombre="+usuario+"&Pass="+password
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();//al momento que la data sale, puede retornar una respuesta
            respuesta = connection.getResponseCode(); // si devuelve una respues lo guardamos en una variable

            resul=new StringBuilder();//Consumiendo el JSON en la respuesta
            Log.e("json", resul.toString() );
            if(respuesta==200)
            {
                //tengo la duda de si esto funciona o no xD
                //claro pero de aqui para abajo no tengo la mas minima idea por que incluso despues que recibimos el json
                //esta listo no tenemos ue hacer nada mas
                InputStream in = new BufferedInputStream(connection.getInputStream());//TOMAMOS LA RESPUESTA
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));//leemos la respuesta

                //LLENAMOS LA VARIABLE "resul" con el dato que hemos traido
                while ((linea=reader.readLine())!=null)
                {
                    resul.append(linea);//agregamos cada linea que retorne

                }

            }

        }catch (Exception e){
            Log.e("laweea y que saen xD",e.toString());
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
    */
    //pero si lo que esta arriba de esto el seba no cacho xD
    //cuando vino el andy, recuerdas
    //yo te explico es que vino el andy y nos obligo a comentar todo tu codigo
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

}
