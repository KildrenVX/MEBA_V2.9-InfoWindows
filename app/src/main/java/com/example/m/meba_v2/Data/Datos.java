package com.example.m.meba_v2.Data;

import android.graphics.drawable.Drawable;

/**
 * Created by m on 25/04/2017.
 */

public class Datos {

    protected Drawable foto;
    protected String nombre;
    protected String info;
    protected long id;

    public Datos(Drawable foto, String nombre, String info) {
        this.foto = foto;
        this.nombre = nombre;
        this.info = info;
        //this.id = id;
    }

    public Drawable getFoto() {
        return foto;
    }

    public void setFoto(Drawable foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInfo() {
        return info;
    }

    public long getId() {
        return id;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setId(long id) {
        this.id = id;
    }
}
