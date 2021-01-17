package Cartas;

import Excepciones.CasillaExcepcion.Compra.SinDinero;
import Jugadores.Avatar;
import Tirada.Tablero;

public abstract class Carta {
    private int numero_carta;

    public Carta(int numero_carta) {
        this.numero_carta = numero_carta;
    }

    public abstract void accion(int numero_carta, Tablero tablero, Avatar avatar) throws SinDinero;

    public int getNumero_carta() {
        return numero_carta;
    }

    public void setNumero_carta(int numero_carta) {
        this.numero_carta = numero_carta;
    }
}