package Casillas;

import Jugadores.Avatar;
import Principal.Juego;
import Principal.Valores;

public final class Especial extends Casilla {
    public Especial() {
    }

    public Especial(String nombre, double coste, String color, int posicion) {
        super(nombre, coste, color, posicion);
    }

    public void impCasilla() {
        if (getNombre().equals("Cárcel") || getNombre().equals("Carcel") || getNombre().equals("cárcel") || getNombre().equals("carcel")) {
            Juego.consola.imprimirln("salir: " + Valores.SALIR_CARCEL + ",");
            Juego.consola.imprimirln("jugadores: ");
            for (Avatar a : super.getAvatar()) {
                Juego.consola.imprimirln("\t[" + a.getJugador().getNombre() + "," + a.getJugador().getVecesEnLaCarcel() + "],");
            }
        } else if (getNombre().equals("Párking") || getNombre().equals("Parking") || getNombre().equals("párking") || getNombre().equals("parking")) {
            Juego.consola.imprimirln("bote: " + getCoste() + ",");
            Juego.consola.imprimirln("jugadores: ");
            for (Avatar a : super.getAvatar()) {
                Juego.consola.imprimirln("\t" + a.getJugador().getNombre() + ",");
            }
        } else if (getNombre().equals("IrCárcel") || getNombre().equals("IrACárcel")) {
            Juego.consola.imprimirln("jugadores: ");
            for (Avatar a : super.getAvatar()) {
                Juego.consola.imprimirln("\t" + a.getJugador().getNombre() + ",");
            }
        } else if (getNombre().equals("Salida") || getNombre().equals("salida")) {
            Juego.consola.imprimirln("jugadores: ");
            for (Avatar a : super.getAvatar()) {
                Juego.consola.imprimirln("\t" + a.getJugador().getNombre() + ",");
            }
        }
    }
}