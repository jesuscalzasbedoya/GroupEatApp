package com.jcalzas.groupeat;

public class Rowitem_ciudad {
    private String nombre;

    public Rowitem_ciudad(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}
