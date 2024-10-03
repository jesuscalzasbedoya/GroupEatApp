package com.jcalzas.groupeat;

public class Rowitem_Resenia {
    private double valoracion;
    private String restaurante;

    public void setValoracion(double valoracion) {
        this.valoracion = valoracion;
    }

    public void setRestaurante(String restaurante) {
        this.restaurante = restaurante;
    }

    public Rowitem_Resenia(double valoracion, String restaurante) {
        this.valoracion = valoracion;
        this.restaurante = restaurante;
    }

    public double getValoracion() {
        return valoracion;
    }

    public String getRestaurante() {
        return restaurante;
    }
}
