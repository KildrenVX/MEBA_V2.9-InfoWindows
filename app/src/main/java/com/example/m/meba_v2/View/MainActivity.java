package com.example.m.meba_v2.View;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.m.meba_v2.R;
import com.example.m.meba_v2.model.Marcadores;
import com.example.m.meba_v2.model.local;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener ,GoogleMap.OnInfoWindowClickListener {
    //  AlertDialog.Builder alerta = new AlertDialog.Builder(this);

    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        checkLocationPermission();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        final FloatingActionButton p = (FloatingActionButton) findViewById(R.id.Perfil);
        p.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(MainActivity.this,Login.class);
                    startActivity(intent);
                }catch(Exception e)
                {
                    Log.e("ERROR del INTENT",e.toString());
                }
            }
        });

        final FloatingActionButton p1 = (FloatingActionButton) findViewById(R.id.Ajustes);
        p1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
               try{
                   Intent intent = new Intent(MainActivity.this,Ajustes.class);
                   startActivity(intent);
               }catch(Exception e)
               {
                   Log.e("ERROR del INTENT",e.toString());
               }
            }
        });

        final FloatingActionButton p2 = (FloatingActionButton) findViewById(R.id.Lugares);
        p2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LugaresFavoritos.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        local l= new local();

        String localhost = l.getLocalhost();

        Log.e("IP DEL EQUIPO= ",localhost);
        mMap=googleMap;
        mMap.setMapType(googleMap.MAP_TYPE_HYBRID);
        mMap.setMyLocationEnabled(true); //activar la localizacion actual del usuario
        // ____________________________CARGAR_PI________________________________________________________________________________________

        for (int i =1;i<=50;i++) {
            Marcadores m = new Marcadores();
            m.CargarPI(i, mMap);
        }

        mMap.setOnInfoWindowClickListener(this);
        /*LatLng marquer = new LatLng(-33.353105, -70.735807);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title("Marcador");
        markerOptions.position(marquer);
        mMap.addMarker(new MarkerOptions().position(marquer).title("Marcador").snippet("descripcion"));
        mMap.setOnInfoWindowClickListener(this);*/

//__________________________________________________________________________________________________________________________________________

        CameraUpdate zoom= CameraUpdateFactory.zoomTo(15);
        mMap.animateCamera(zoom);
        //---------permiso de localizacion--------------------------------------------------
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        //-----------------------------------------------------------------------------------

        /* mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener(){
             @Override
             public void onMapClick(LatLng latLng) {
             //muestra coordenadas de posicion tocada dentro del mapa
              Toast.makeText(getApplicationContext(), latLng.toString(), Toast.LENGTH_LONG).show();
             }
         });
         */

        //----Agregar Marcador----------------------------------------------------------------------------------
        Toast.makeText(this, "Mantén presionado en un lugar del mapa para agregar marcador", Toast.LENGTH_LONG).show();
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener(){
            AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);//dialogo de alerta
            @Override
            public void onMapLongClick(final LatLng latLng) {
                //muestra coordenadas de posicion tocada dentro del mapa
                //Toast.makeText(getApplicationContext(), latLng.toString(), Toast.LENGTH_LONG).show();
                alerta.setTitle("Nuevo marcador");
                alerta.setMessage("¿Crear nuevo marcador aquí? ");
                alerta.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                        Intent i = new Intent(MainActivity.this,Agregar_PI.class);
                        Log.e("coordenadas: ",latLng.toString());
                        String a = latLng.toString();
                        String sCadena = a.substring(a.indexOf("(") + 1, a.indexOf(")"));
                        String[] array = sCadena.split(",");
                        Log.e("valor de Latitud",array[0]);
                        Log.e("Valor Longitud  ",array[1]);
                        String la = array[0];
                        String ln = array[1];
                        i.putExtra("latitud",la);
                        i.putExtra("longitud",ln);
                        startActivity(i);

                    }
                })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .show();


            }
        });


        //------------------------------------------------------------------------------------------------------

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);// manipulacion de localizacion
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 1, this);

       // mMap.setInfoWindowAdapter(this);//adaptar las ventanas de informacion
       // mMap.setOnInfoWindowClickListener(this);//infowindows click
    }


    //---------------------TIEMPO:DE_ESPERA_DE_ACTIVAR_EL_GPS-----------------------------------------------
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        try {
            switch (requestCode) {
                case MY_PERMISSIONS_REQUEST_LOCATION: {
                    // Si la consulta es cancelada los arreglos estaran vacios
                    if (grantResults.length > 0
                            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                        // permiso concedido
                        if (ContextCompat.checkSelfPermission(this,
                                Manifest.permission.ACCESS_FINE_LOCATION)
                                == PackageManager.PERMISSION_GRANTED) {
                            mMap.setMyLocationEnabled(true);
                        }
                    } else {
                        // Permiso denegado
                        Toast.makeText(this, "Permiso denegado", Toast.LENGTH_LONG).show();
                    }
                    return;
                }
                // agregar mas case para mas permisos(wifi,contactos,etc)

            }
        }catch (Exception e){
            e.printStackTrace();

        }
    }
//-------------------------------------------------------------------------------------------------------






    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //preguntar si el usuario necesita explicacion
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Mostrar explicacion del permiso
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            } else {
                // pedir el permiso
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }
    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    //---------------------CENTRA_A_LA_LOCALIZACION_ACTUAL----------------------------------------------
    @Override
    public void onLocationChanged(Location location) {

        //mMap.clear();
      /*  for (int i =1;i<=3;i++)
        {
            Marcadores m = new Marcadores();
            m.CargarPI(i,mMap);
        }*/

        LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
        //abrir en la posicion del marcador con zoom
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17.0f));


    }
//-------------------------------------------------------------------------------------------------

     /*public void BotonMarcador(Location location){
         mMap.clear();

         LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());

        MarkerOptions markerOptions = new MarkerOptions();
         markerOptions.position(latLng);
         markerOptions.title("Marcador en mi posicion actual");

         mMap.addMarker(markerOptions);
         mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17.0f));

     }*/

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, "Info window clicked",
                Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this,Ver_PuntoDeInteres.class);
        String titulo,descripcion;
        titulo=marker.getTitle().toString();
        descripcion=marker.getSnippet().toString();
        i.putExtra("Titulo", titulo);
        i.putExtra("Descripcion", descripcion);
        startActivity(i);
    }


}

