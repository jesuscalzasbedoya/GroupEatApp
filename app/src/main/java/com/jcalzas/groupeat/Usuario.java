package com.jcalzas.groupeat;

import java.util.ArrayList;

public class Usuario {
    private String id;
    private String nombre;
    private ArrayList<String> amigos;

    public Usuario(){

    }

    public void inicializarAmigos(){
        this.amigos = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList getAmigos() {
        return amigos;
    }

    public void setAmigos(ArrayList amigos) {
        this.amigos = amigos;
    }

    public CharSequence amigos(){
        CharSequence amigosC = "";
        for(int i = 0; i<amigos.size(); i++){
            amigosC = amigosC + amigos.get(i) + ", ";
        }
        return amigosC;
    }

    public void aniadirAmigo(String amigo){
        this.amigos.add(amigo);
    }
}
