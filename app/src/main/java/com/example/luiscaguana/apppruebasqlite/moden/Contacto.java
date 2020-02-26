package com.example.luiscaguana.apppruebasqlite.moden;

import java.io.Serializable;

public class Contacto  implements Serializable {
    private long id;
    private String nombre;
    private String email;


    public Contacto(String nombre, String email) {
        id = -1;
        this.nombre = nombre;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }
    public boolean hasBeenSaved() {
        if (id != -1) return true;
        else return false;
    }



}
