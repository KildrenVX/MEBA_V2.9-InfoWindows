package com.example.m.meba_v2.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.m.meba_v2.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Agregar_PI extends AppCompatActivity {

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> ListaDatHaper;
    private HashMap<String,List<String>> listHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar__pi);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        listView =(ExpandableListView)findViewById(R.id.Listas);
        initData();
        listAdapter = new ExpandableListAdapter(this,ListaDatHaper,listHash);
        listView.setAdapter(listAdapter);

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


    public void Click (View view)
    {
        Intent intent = new Intent(Agregar_PI.this,Detalle_punto_de_interes.class);
        startActivity(intent);
    }

}
