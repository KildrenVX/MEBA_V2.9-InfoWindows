package com.example.m.meba_v2.View;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.example.m.meba_v2.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;

import java.util.ArrayList;

public class Perfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        ImafPerCir();

        ObjDatos();
     }

     public void ObjDatos(){
         //conexion a http
         AsyncHttpClient client = new AsyncHttpClient();
         String url="http://meba.esy.es/meba_connect/get_all_Users.php";

         //envio de parametros
         //RequestParams parametros = new RequestParams();
         //parametros.put("Edad",18);

         client.post(url, null, new AsyncHttpResponseHandler() {
             @Override
             public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                 if (statusCode==200){
                     Log.e("respuesta", responseBody.toString());
                     obtDatosJSON(new String(responseBody));
                 }
             }

             @Override
             public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

             }
         });
     }



     public ArrayList<String> obtDatosJSON(String response){
         Log.e("response", response.toString());
         ArrayList<String> listado = new ArrayList<String>();
         try{

             JSONArray jsonArray = new JSONArray(response);
             String texto;
             for (int i=0;i<jsonArray.length();i++){
                 texto = jsonArray.getJSONObject(i).getString("usuNombre")+" "+
                         jsonArray.getJSONObject(i).getString("usuCorreo")+" "+
                         jsonArray.getJSONObject(i).getString("usuSexo");
                 Log.e("mensaje", texto.toString());
                 listado.add(texto);
             }
         }catch(Exception e){
             e.printStackTrace();
         }
         return listado;
     }








    public void ImafPerCir ()
    {
        //extraemos el drawable en un bitmap
        Drawable originalDrawable = getResources().getDrawable(R.drawable.perfil);
        Bitmap originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();

        //creamos el drawable redondeado
        RoundedBitmapDrawable roundedDrawable =
                RoundedBitmapDrawableFactory.create(getResources(), originalBitmap);

        //asignamos el CornerRadius
        roundedDrawable.setCornerRadius(originalBitmap.getHeight());

        ImageView imageView = (ImageView) findViewById(R.id.PERFILIMA);

        imageView.setImageDrawable(roundedDrawable);

    }
}
