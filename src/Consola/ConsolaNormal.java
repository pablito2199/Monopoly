package Consola;

import Jugadores.Jugador;
import Tirada.Tablero;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsolaNormal implements Consola {
    public ConsolaNormal() {
    }

    public void imprimir(String mensaje) {
        System.out.print(mensaje);
    }

    public void imprimirln(String mensaje) {
        System.out.println(mensaje);
    }

    public void imprimir(ArrayList<Jugador> jugadores) {
        System.out.println("\n" + jugadores);
    }

    public void imprimir(Tablero tablero) {
        System.out.println("\n" + tablero);
    }

    public String leer(String descripcion) {
        imprimir(descripcion);
        Scanner lectura = new Scanner(System.in);
        return lectura.nextLine();
    }
}
