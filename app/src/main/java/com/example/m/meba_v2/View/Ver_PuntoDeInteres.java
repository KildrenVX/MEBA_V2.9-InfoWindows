package com.example.m.meba_v2.View;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.m.meba_v2.R;

public class Ver_PuntoDeInteres extends AppCompatActivity {


    Button btnAnec,btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver__punto_de_interes);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        TextView Titu,Desc;
        Titu = (TextView)findViewById(R.id.TitlePI);
        Desc = (TextView)findViewById(R.id.DescrpPI);
        String Titulo = getIntent().getStringExtra("Titulo");
        String Descrip = getIntent().getStringExtra("Descripcion");

        Titu.setText(Titulo);
        Desc.setText(Descrip);

    }
}
