package com.jcalzas.groupeat;

public class Rowitem_resultado {
    private String restaurante;
    private double valoracion;
    private String direccion;

    public Rowitem_resultado(String restaurante, double valoracion, String direccion) {
        this.restaurante = restaurante;
        this.valoracion = valoracion;
        this.direccion = direccion;
    }

    public String getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(String restaurante) {
        this.restaurante = restaurante;
    }

    public double getValoracion() {
        return valoracion;
    }

    public void setValoracion(double valoracion) {
        this.valoracion = valoracion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return this.restaurante + ": " + this.valoracion;
    }
}
