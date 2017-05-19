package com.example.m.meba_v2.View;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.m.meba_v2.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;

public class Detalle_punto_de_interes extends AppCompatActivity implements View.OnClickListener {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_punto_de_interes);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //btn=(Button)findViewById(R.id.veranec);

        btn.setOnClickListener(this);
    }

    public void JsonPI(String ID){
        //conexion a http
        AsyncHttpClient client = new AsyncHttpClient();
        String url="http://meba.esy.es/meba_connect/Buscar_punto_interes_por_id.php?ID="+ID;

        Log.e("probando y que saen", url.toString());
        //envio de parametros
        //RequestParams parametros = new RequestParams();
        //parametros.put("PunId",ID);

        client.post(url, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String resul = new String(responseBody);
                    Log.e("estoy", "aqui");
                    try {

                        JSONArray jsonArray = new JSONArray(resul);

                        String texto = "";
                        for (int i=0;i<jsonArray.length();i++){
                            texto = jsonArray.getJSONObject(i).getString("PunTitulo")+" "+
                                    jsonArray.getJSONObject(i).getString("PunDescripcion")+" "+
                                    jsonArray.getJSONObject(i).getString("PunValorizacion");
                            Log.e("mensaje", texto.toString());

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        //Intent i = new Intent(Detalle_punto_de_interes.this,Ver_anecdotas.class);
        //startActivity(i);
    }
}
