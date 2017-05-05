package com.example.m.meba_v2.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.m.meba_v2.Data.ObtenerJson;
import com.example.m.meba_v2.R;

public class Ver_PI extends AppCompatActivity {

    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_pi);

        //obteniendo la informacion de json
        ObtenerJson ObtenJson = new ObtenerJson();
        ObtenJson.ObjDatos();

        lista = (ListView) findViewById(R.id.listView);
    }
}
