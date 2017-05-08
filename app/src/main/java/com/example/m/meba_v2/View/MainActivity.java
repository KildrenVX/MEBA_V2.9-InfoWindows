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
 import android.view.LayoutInflater;
 import android.view.View;
 import android.widget.Toast;

 import com.example.m.meba_v2.R;
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
         LocationListener, GoogleMap.InfoWindowAdapter, GoogleMap.OnInfoWindowClickListener {
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
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });

        final FloatingActionButton p1 = (FloatingActionButton) findViewById(R.id.Ajustes);
        p1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Ajustees.class);
                startActivity(intent);
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

        final FloatingActionButton p3 = (FloatingActionButton) findViewById(R.id.LugarNuevo);
        p3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(MainActivity.this, Ver_PI.class);
                //startActivity(intent);

             /*   mMap.clear();

                Location location = new Location("");

                LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title("Lugar creado con floating button");
                mMap.addMarker(markerOptions);
*/

                 //  Intent intent = new Intent(MainActivity.this,LugaresFavoritos.class);
               // startActivity(intent);
            }
        });

    }

     @Override
     public void onMapReady(final GoogleMap googleMap) {
         mMap=googleMap;
         mMap.setMapType(googleMap.MAP_TYPE_HYBRID);
         mMap.setMyLocationEnabled(true);

         LatLng MiCasa = new LatLng(-33.3267245, -70.74691519999999);
         mMap.addMarker(new MarkerOptions().position(MiCasa).title("Esta es mi casa"));
         mMap.moveCamera(CameraUpdateFactory.newLatLng(MiCasa));
         mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MiCasa,12));


         LatLng LaU = new LatLng(-33.405845, -70.682372);
         mMap.addMarker(new MarkerOptions().position(LaU).title("Esto es Inacap"));

         CameraUpdate zoom= CameraUpdateFactory.zoomTo(15);
         mMap.animateCamera(zoom);
         if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                 != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                 != PackageManager.PERMISSION_GRANTED) {
             return;
         }


        /* mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener(){
             @Override
             public void onMapClick(LatLng latLng) {
                 //muestra coordenadas de posicion tocada dentro del mapa
                 Toast.makeText(getApplicationContext(), latLng.toString(), Toast.LENGTH_LONG).show();

             }
         });
         */
         Toast.makeText(this, "Manten presionado en un lugar del mapa para agregar marcador", Toast.LENGTH_LONG).show();
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener(){
            AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
             @Override
             public void onMapLongClick(final LatLng latLng) {
                 //muestra coordenadas de posicion tocada dentro del mapa
                 //Toast.makeText(getApplicationContext(), latLng.toString(), Toast.LENGTH_LONG).show();
                 alerta.setTitle("Nuevo marcador");
                 alerta.setMessage("Crear nuevo marcador aqui? ");
                 alerta.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                     public void onClick(DialogInterface dialog, int which) {
                         MarkerOptions markerOptions = new MarkerOptions();
                         markerOptions.title("marcador con dialogo de alerta");
                         markerOptions.position(latLng);
                         mMap.addMarker(markerOptions);
                         Log.e("coordenadas: ",latLng.toString());
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


         LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
         locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 1, this);

         mMap.setInfoWindowAdapter(this);
         mMap.setOnInfoWindowClickListener(this);
     }


     public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
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
     @Override
     public void onConnected(@Nullable Bundle bundle) {

     }

     @Override
     public void onConnectionSuspended(int i) {

     }


     @Override
     public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

     }

     @Override
     public void onLocationChanged(Location location) {
        /* mMap.clear();

         LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());

       MarkerOptions markerOptions = new MarkerOptions();
         markerOptions.position(latLng);
         markerOptions.title("Mi lugar");
         //agregar marcador al mapa
         mMap.addMarker(markerOptions);

         //abrir en la posicion del marcador con zoom
         mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17.0f));
         */
     }

     public void BotonMarcador(Location location){
         mMap.clear();

         LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());

        MarkerOptions markerOptions = new MarkerOptions();
         markerOptions.position(latLng);
         markerOptions.title("Marcador en mi posicion actual");

         mMap.addMarker(markerOptions);


         mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17.0f));

     }

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
     public View getInfoWindow(Marker marker) {
         LayoutInflater inflater = LayoutInflater.from(this);
         View view = inflater.inflate(R.layout.info_windows,null,false);
         view.findViewById(R.id.txtTitulo);
         view.findViewById(R.id.txtDescripcion);
         view.findViewById(R.id.txtDireccion);
         view.findViewById(R.id.imagen);
         return  view;
     }

     @Override
     public View getInfoContents(Marker marker) {
         return null;
     }

     @Override
     public void onInfoWindowClick(Marker marker) {
         //accion al tocar el info windows
         Toast.makeText(this, "mostrar detalle del punto de interes ", Toast.LENGTH_LONG).show();

         Intent intent = new Intent(MainActivity.this, Detalle_punto_de_interes.class);
         startActivity(intent);

     }
 }
