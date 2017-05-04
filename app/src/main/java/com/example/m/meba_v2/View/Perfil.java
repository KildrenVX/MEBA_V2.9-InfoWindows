package com.example.m.meba_v2.View;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.m.meba_v2.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;

import java.util.ArrayList;

public class Perfil extends AppCompatActivity {

    TextView asd;
    Button btnLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        ImafPerCir();

        final Intent login = new Intent(this, Login.class);
        asd = (TextView) findViewById(R.id.textView2);
        btnLog=(Button)findViewById(R.id.btnlog);
        Bundle datos = this.getIntent().getExtras();
        int asd = datos.getInt("datos");
        Log.e("ID",Integer.toString(asd));
        if (asd >= 0){
            ObjDatos(asd);
        }else{
            Toast.makeText(this, "no me acuerdo para que hice esto xD", Toast.LENGTH_SHORT).show();
        }


        btnLog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(login);
                //seba no invente xD
            }
        });
     }


     public void ObjDatos(int ID){
         //conexion a http
         AsyncHttpClient client = new AsyncHttpClient();
         String url="http://meba.esy.es/meba_connect/get_User_Id.php?ID="+ID;
            Log.e("url",url.toString());
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




     public void obtDatosJSON(String response){
         Log.e("response", response.toString());
         try{
             JSONArray jsonArray = new JSONArray(response);
             asd.setText(jsonArray.getJSONObject(0).getString("usuNombre"));
         }catch(Exception e){

             Log.e("error json", e.toString());
         }
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




    public void Logclick(View view)
    {
        Intent i = new Intent(getApplicationContext(),Login.class);
        startActivity(i);
    }
}
