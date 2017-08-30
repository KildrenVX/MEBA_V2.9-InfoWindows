package com.example.m.meba_v2.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.m.meba_v2.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;

public class Anecdota extends AppCompatActivity {
    TextView txtTitulo,txtDescripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anecdota);






        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://192.168.43.104/meba_connect/Buscar_publicacion_por_id.php?ID=13";
        client.post(url, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String resul = new String(responseBody);
                    try {

                        JSONArray jsonArray = new JSONArray(resul);
                        //int ids = jsonArray.getJSONObject(0).getInt("PubID");
                        String Titulo = jsonArray.getJSONObject(0).getString("PubTitulo");
                        String descrip = jsonArray.getJSONObject(0).getString("PubDescripcion");
                        String valor = jsonArray.getJSONObject(0).getString("PubValorizacion");
                     //   String files = jsonArray.getJSONObject(0).getString("PubArchivo");
                      //  int idUsu = jsonArray.getJSONObject(0).getInt("Usuario_usuID");
                       // int idCat = jsonArray.getJSONObject(0).getInt("Categorias_CatId");
                        Log.e("Titulo: ",Titulo);
                        Log.e("Descripcion: ",descrip);

                        txtTitulo = (TextView)findViewById(R.id.Titleanec);
                        txtDescripcion = (TextView)findViewById(R.id.Descrpanecnec);

                        txtTitulo.setText(Titulo);
                        txtDescripcion.setText(descrip);




                    } catch (Exception e) {
                        Log.e("error aqui ->", e.toString());
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });


    }
}
