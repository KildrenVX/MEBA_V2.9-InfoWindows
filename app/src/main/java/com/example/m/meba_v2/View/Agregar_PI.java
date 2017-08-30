package com.example.m.meba_v2.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.m.meba_v2.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Agregar_PI extends AppCompatActivity /*implements View.OnClickListener*/{

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> ListaDatHaper;
    private HashMap<String,List<String>> listHash;
    ImageButton addPI;
    EditText titulos,descrip;
    String file;
    double lat,lng,val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar__pi);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        String Lat = getIntent().getStringExtra("latitud");
        String Lng = getIntent().getStringExtra("longitud");
        lat = Double.parseDouble(Lat);
        lng = Double.parseDouble(Lng);

        Log.e("Latitud:",Lat);
        Log.e("Longitud:",Lng);

        addPI = (ImageButton)findViewById(R.id.agrepi);
        titulos = (EditText)findViewById(R.id.Titulo);
        descrip = (EditText)findViewById(R.id.descripcion);
        val=3;


        addPI.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    AgregarJson();
                   // Intent intent = new Intent(Agregar_PI.this,MainActivity.class);
                    //startActivity(intent);
                } catch (Exception e) {
                    Log.e("error json", e.toString());
                }

            }
        });




        //listView =(ExpandableListView)findViewById(R.id.Listas);
       // initData();
        //listAdapter = new ExpandableListAdapter(this,ListaDatHaper,listHash);
        //listView.setAdapter(listAdapter);

       // btnAgregarPI.setOnClickListener(this);
    }

    public void AgregarJson()
    {

        AsyncHttpClient client = new AsyncHttpClient();
        //String url ="http://meba.esy.es/meba_connect/Crear_punto_interes.php?";
        String url ="http://192.168.43.104/meba_connect/Crear_punto_interes.php?";
        //192.168.0.12
        RequestParams params = new RequestParams();
        params.put("Titulo",titulos.getText().toString());
        params.put("Descripcion",descrip.getText().toString());
        params.put("Valorizacion",val);
        params.put("Archivo",file);
        params.put("Latitud",lat);
        params.put("Longitud",lng);


        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                if(statusCode==200){
                    Log.i("json", new String(responseBody));
                    Log.i("status", Integer.toString(statusCode));
                    Toast.makeText(Agregar_PI.this, "Punto de interés agregado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                Log.i("status",Integer.toString(statusCode));
               // Log.i("Responsebody",responseBody.toString());
                Log.i("error paso algo D:",error.toString());
            }
        });

        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);

    }

 /*   private void initData() {
        ListaDatHaper = new ArrayList<>();
        listHash = new HashMap<>();

        ListaDatHaper.add("Categoria");

        List<String> categorias = new ArrayList<>();
        categorias.add("Aire Libre");
        categorias.add("Estructura");

        listHash.put(ListaDatHaper.get(0),categorias);

    }*/

/*
    @Override
    public void onClick(View v) {
        GoogleMap mMap;
        LatLng latLng;
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title("marcador con dialogo de alerta");
        markerOptions.position(latLng);
        mMap.addMarker(markerOptions);
        Log.e("coordenadas: ",latLng.toString());
    }*/
}
