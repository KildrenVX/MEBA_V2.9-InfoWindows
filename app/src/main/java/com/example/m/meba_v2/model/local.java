package com.example.m.meba_v2.model;
import android.net.wifi.WifiManager;
import android.util.Log;
import java.net.InetAddress;



public class local {

    public String getLocalhost() {

        try
         {
             InetAddress inetAddress = InetAddress.getLocalHost();
             String localhost=inetAddress.getHostAddress();


             return localhost;

         }catch (Exception e)
          {
              Log.e("Algo paso:",e.toString());
              return "";
          }
    }




}
