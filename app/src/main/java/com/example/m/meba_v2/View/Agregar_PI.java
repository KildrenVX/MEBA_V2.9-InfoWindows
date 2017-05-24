package com.example.m.meba_v2.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.m.meba_v2.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Agregar_PI extends AppCompatActivity /*implements View.OnClickListener*/{

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> ListaDatHaper;
    private HashMap<String,List<String>> listHash;
    Button btnAgregarPI,BtnCancelar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar__pi);

        Agregar();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        BtnCancelar = (Button)findViewById(R.id.CANCELARPI);
        btnAgregarPI=(Button)findViewById(R.id.AGREGARPI);

        listView =(ExpandableListView)findViewById(R.id.Listas);
        initData();
        //listAdapter = new ExpandableListAdapter(this,ListaDatHaper,listHash);
        listView.setAdapter(listAdapter);

       // btnAgregarPI.setOnClickListener(this);
    }

    public void Agregar()
    {

        String la[] = new String[0];
        String lo[] = new String[0];
//         la[0] = String.valueOf(getIntent().getStringArrayExtra("latitud"));
  //       lo[0] = String.valueOf(getIntent().getStringArrayExtra("longitud"));
        Log.e("LAT",la.toString());
        Log.e("LOG",lo.toString());

    }

    private void initData() {
        ListaDatHaper = new ArrayList<>();
        listHash = new HashMap<>();

        ListaDatHaper.add("Categoria");

        List<String> categorias = new ArrayList<>();
        categorias.add("Aire Libre");
        categorias.add("Estructura");

        listHash.put(ListaDatHaper.get(0),categorias);

    }

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
