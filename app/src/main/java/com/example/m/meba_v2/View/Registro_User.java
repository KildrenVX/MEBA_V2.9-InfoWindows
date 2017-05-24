package com.example.m.meba_v2.View;

import android.content.Intent;
import android.os.AsyncTask;
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

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

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
                      /*JsonRegistro(txtCorreo.getText().toString(), txtPass.getText().toString(),
                              txtEdad.getText().toString(), txtNombre.getText().toString(),
                              txtSexo.getText().toString());
                              */
                      guardar(v);
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

    public void guardar(View view){

        String correo,pass,edad,nombre,sex;

               correo= txtCorreo.getText().toString();
                pass=txtPass.getText().toString();
                edad=txtEdad.getText().toString();
                nombre=txtNombre.getText().toString();
                sex=txtSexo.getText().toString();
        BackgroundTask tareaAsincronica = new BackgroundTask();
        tareaAsincronica.execute(correo,pass,edad,nombre,sex);




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
                    Log.e("Conexion exitosa","");
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

    class BackgroundTask extends AsyncTask<String, Void, String> {

        String link;

        protected void onPreExecute(){
            link = "http://meba.esy.es/meba_connect/Crear_Usuario.php";
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }



        protected String doInBackground(String... params) {
            String Correo;
            String Pass;
            String Edad;
            String Nombre;
            String Sexo;

            //link = "http://meba.esy.es/meba_connect/Crear_Usuario.php";

            Correo = params[0];
            Pass = params[1];
            Edad = params[2];
            Nombre = params[3];
            Sexo = params[4];

            try {
                URL url = new URL(link);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream),8);
                String data = URLEncoder.encode("Correo","UTF-8")+"="+URLEncoder.encode(Correo,"UTF-8")+"&"+
                        URLEncoder.encode("Pass","UTF-8")+"="+URLEncoder.encode(Pass,"UTF-8")+"&"+
                        URLEncoder.encode("Edad","UTF-8")+"="+URLEncoder.encode(Edad,"UTF-8")+"&"+
                        URLEncoder.encode("Nombre","UTF-8")+"="+URLEncoder.encode(Nombre,"UTF-8")+"&"+
                        URLEncoder.encode("Sexo","UTF-8")+"="+URLEncoder.encode(Sexo,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();
                httpURLConnection.disconnect();

                return "datos ingresados ";



            }catch (MalformedURLException e ){
                e.fillInStackTrace();

            }catch(IOException e){
                e.fillInStackTrace();

            }

            return null;





        }
    }
    }
