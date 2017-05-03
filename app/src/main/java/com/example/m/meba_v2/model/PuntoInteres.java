package com.example.m.meba_v2.model;

/**
 * Created by LazaroZero on 02-05-2017.
 */

public class PuntoInteres {
    private int PunId = 0;
    private double PunLatitud = 0;
    private double PunLongitud = 0;
    private String PunTitulo = "";
    private String PunDescripcion = "";
    private String PunValorizacion = "";
    private String PunArchivo = "";

    public PuntoInteres() {

    }

    public int getPunId() {
        return PunId;
    }

    public void setPunId(int punId) {
        PunId = punId;
    }

    public double getPunLatitud() {
        return PunLatitud;
    }

    public void setPunLatitud(double punLatitud) {
        PunLatitud = punLatitud;
    }

    public double getPunLongitud() {
        return PunLongitud;
    }

    public void setPunLongitud(double punLongitud) {
        PunLongitud = punLongitud;
    }

    public String getPunTitulo() {
        return PunTitulo;
    }

    public void setPunTitulo(String punTitulo) {
        PunTitulo = punTitulo;
    }

    public String getPunDescripcion() {
        return PunDescripcion;
    }

    public void setPunDescripcion(String punDescripcion) {
        PunDescripcion = punDescripcion;
    }

    public String getPunValorizacion() {
        return PunValorizacion;
    }

    public void setPunValorizacion(String punValorizacion) {
        PunValorizacion = punValorizacion;
    }

    public String getPunArchivo() {
        return PunArchivo;
    }

    public void setPunArchivo(String punArchivo) {
        PunArchivo = punArchivo;
    }
}
