package Casillas;

import Principal.Juego;

public final class Impuesto extends Casilla {
    public Impuesto() {
    }

    public Impuesto(String nombre, double coste, int posicion, String color) {
        super(nombre, coste, color, posicion);
    }

    public void impCasilla() {
        Juego.consola.imprimirln("tipo: " + this.getClass().getName() + ",");
        Juego.consola.imprimirln("apagar: " + getCoste());
    }
}