package com.example.m.meba_v2.View;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.m.meba_v2.R;

public class Detalle_punto_de_interes extends AppCompatActivity implements View.OnClickListener {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_punto_de_interes);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        btn=(Button)findViewById(R.id.veranec);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //Intent i = new Intent(Detalle_punto_de_interes.this,Ver_anecdotas.class);
        //startActivity(i);
    }
}
