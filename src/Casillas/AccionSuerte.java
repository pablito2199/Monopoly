package Casillas;

import Cartas.Carta;
import Cartas.Suerte;
import Excepciones.CasillaExcepcion.Compra.SinDinero;
import Jugadores.Avatar;
import Principal.Juego;
import Tirada.Tablero;

import java.util.ArrayList;

public final class AccionSuerte extends Accion {
    public AccionSuerte(ArrayList<Suerte> suerte) {
        super(suerte);
    }

    public AccionSuerte(String nombre, double coste, String color, int posicion) {
        super(nombre, coste, color, posicion);
    }

    private static boolean SePuedeCambiar(String entrada) {
        try {
            Integer.parseInt(entrada);
            return true;
        } catch (NumberFormatException excepcion) {
            return false;
        }
    }

    public void eleccionsuerte(Tablero tablero, Avatar avatartirada) throws SinDinero {
        String numero;
        do {
            Juego.consola.imprimirln(avatartirada.getJugador().getNombre() + ", elige una carta: ");
            do {
                numero = Juego.consola.leer("$> ");
                if (SePuedeCambiar(numero)) {
                    if (Integer.parseInt(numero) < 1 || Integer.parseInt(numero) > 6)
                        Juego.consola.imprimirln("El número debe estar entre 1 y 6.");
                } else
                    Juego.consola.imprimirln("Debes escribir un número.");
            } while (!SePuedeCambiar(numero));
        } while (Integer.parseInt(numero) < 1 || Integer.parseInt(numero) > 6);
        super.BarajarSuerte();
        Carta carta = getSuerte().get(Integer.parseInt(numero) - 1);
        carta.accion(Integer.parseInt(numero), tablero, avatartirada);
    }
}
