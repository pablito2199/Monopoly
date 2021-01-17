package Consola;

import Jugadores.Jugador;
import Tirada.Tablero;

import java.util.ArrayList;

public interface Consola {
    void imprimir(String mensaje);

    void imprimirln(String mensaje);

    void imprimir(ArrayList<Jugador> jugador);

    void imprimir(Tablero tablero);

    String leer(String descripcion);
}
