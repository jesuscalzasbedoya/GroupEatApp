package com.jcalzas.groupeat;

public class Rowitem_Amigos {
    private String nombre;
    private String id;

    public Rowitem_Amigos(String nombre, String id){
        this.nombre = nombre;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString(){
        return this.nombre;
    }
}
