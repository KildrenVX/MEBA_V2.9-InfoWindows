package com.example.m.meba_v2.Data;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by LazaroZero on 02-05-2017.
 */

public class ObtenerJson {


    public void ObjDatos(){
        //conexion a http
        AsyncHttpClient client = new AsyncHttpClient();
        String url="http://meba.esy.es/meba_connect/ver_punto_interes.php";

        //envio de parametros
        //RequestParams parametros = new RequestParams();
        //parametros.put("Edad",18);

        client.post(url, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode==200){
                    cargarLista(obtDatosJSON(new String(responseBody)));
                    //obtDatosJSON(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }

    public  void cargarLista(ArrayList<String> datos){


    }

    public ArrayList<String> obtDatosJSON(String response){
        Log.e("response", response);
        ArrayList<String> listado = new ArrayList<String>();
        try{
            JSONArray jsonArray = new JSONArray(response);

            String texto = "";
            for (int i=0;i<jsonArray.length();i++){
                texto = jsonArray.getJSONObject(i).getString("PunTitulo")+" "+
                        jsonArray.getJSONObject(i).getString("PunDescripcion")+" "+
                        jsonArray.getJSONObject(i).getString("PunValorizacion");
                Log.e("mensaje", texto.toString());
                listado.add(texto);
            }
            Log.e("mensajeeeee", texto.toString());

            return listado;
        }catch(Exception e){
            Log.e("error",e.toString());
        }
        return listado;
    }
}
