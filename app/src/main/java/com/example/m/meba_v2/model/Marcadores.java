package com.example.m.meba_v2.model;

import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;

/**
 * Created by m on 18/05/2017.
 */

public class Marcadores {
    public Marcadores() {
    }

    public int CargarPI(int ID,  final GoogleMap mMap)
    {

        //conexion a http Conexion/ve_persona.php?ID=
        AsyncHttpClient client = new AsyncHttpClient();
        String url="http://meba.esy.es/meba_connect/Buscar_punto_interes_por_id.php?ID="+ID;
        client.post(url, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String resul = new String(responseBody);
                    try {

                        JSONArray jsonArray = new JSONArray(resul);
                        int ids = jsonArray.getJSONObject(0).getInt("PunId");
                        double lat = jsonArray.getJSONObject(0).getDouble("PunLatitud");
                        double log = jsonArray.getJSONObject(0).getDouble("PunLongitud");
                        String Titulo = jsonArray.getJSONObject(0).getString("PunTitulo");
                        String descrip = jsonArray.getJSONObject(0).getString("PunDescripcion");
                        String valor = jsonArray.getJSONObject(0).getString("PunValorizacion");
                        //agregar marcadores
                        String Slat, title, Slog;
                        Slat = String.valueOf(lat);
                        Slog = String.valueOf(log);

                        Log.e("VAlor de de Tiulo: ", Titulo);
                        Log.e("VAlor de de lat: ", Slog);
                        Log.e("VAlor de de log: ", Slat);
                        LatLng marquer = new LatLng(lat, log);
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.title(Titulo);
                        markerOptions.position(marquer);
                        mMap.addMarker(new MarkerOptions().position(marquer).title(Titulo)
                        .snippet(descrip).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
                        /*
                         ArrayList<String> Punto = new ArrayList<String>();
                         Punto.add(ID);
                         Punto.add(lat);
                         Punto.add(log);
                         Punto.add(Titulo);
                         Punto.add(descrip);
                         Punto.add(valor);*/

                    } catch (Exception e) {
                        Log.e("error aqui ->", e.toString());
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

        return ID;
    }

}
