package com.example.m.meba_v2.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.m.meba_v2.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

public class Registro_anecdota extends AppCompatActivity {

    Spinner opcion;
    EditText title,descrip;
    String files;
    int IDuse,IDCaT,val;
    ImageButton add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_anecdota);

        opcion = (Spinner)findViewById(R.id.Categoria);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.opciones, android.R.layout.simple_spinner_item);
        opcion.setAdapter(adapter);

        title = (EditText)findViewById(R.id.TituloAnec);
        descrip = (EditText)findViewById(R.id.descripcionAne);
        add = (ImageButton)findViewById(R.id.add_amec);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                   jsonAnec();
                    // Intent intent = new Intent(Agregar_PI.this,MainActivity.class);
                    //startActivity(intent);
                } catch (Exception e) {
                    Log.e("error json", e.toString());
                }
            }
        });

        files="ejemplo";
        IDCaT=2;
        IDuse=1;
        val=2;

    }

    public void jsonAnec()
    {

        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://192.168.0.14/meba_connect/Crear_publicacion.php?";

        RequestParams params = new RequestParams();
        params.put("Titulo",title.getText().toString());
        params.put("Descrip",descrip.getText().toString());
        params.put("Val",val);
        params.put("File",files);
        params.put("UsuID",IDuse);
        params.put("CatID",IDCaT);

        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode==200)
                {
                    Toast.makeText(Registro_anecdota.this,"Anecdota Creada",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                Log.i("status",Integer.toString(statusCode));
                Log.i("Responsebody",responseBody.toString());
                Log.i("error",error.toString());
            }
        });

        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);


    }
}
