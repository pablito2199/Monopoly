package Jugadores;

import Casillas.Propiedad;
import Principal.Juego;

public class Trato {
    private String id;
    private String mensaje;
    private Jugador jugador1;
    private Jugador jugador2;
    private Propiedad propiedad1;
    private Propiedad propiedad2;
    private Propiedad propiedad3;
    private int dinero1;
    private int dinero2;
    private int numero_turnos;

    public Trato() {
        this.id = null;
        this.mensaje = null;
        this.jugador1 = null;
        this.jugador2 = null;
        this.propiedad1 = null;
        this.propiedad2 = null;
        this.propiedad3 = null;
        this.dinero1 = 0;
        this.dinero2 = 0;
        this.numero_turnos = 0;
    }

    public Trato(int n, Jugador jugador1, Propiedad propiedad1, Jugador jugador2, Propiedad propiedad2) {
        this.id = "trato" + n;
        this.mensaje = "cambiar (" + propiedad1.getNombre() + ", " + propiedad2.getNombre() + ")";
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.propiedad1 = propiedad1;
        this.propiedad2 = propiedad2;
        this.propiedad3 = null;
        this.dinero1 = 0;
        this.dinero2 = 0;
        this.numero_turnos = 0;
        Juego.consola.imprimirln(jugador2.getNombre() + ", ¿te doy " + propiedad1.getNombre() + " y tú me das " + propiedad2.getNombre() + "?");
    }

    public Trato(int n, Jugador jugador1, Propiedad propiedad1, Jugador jugador2, int dinero2) {
        this.id = "trato" + n;
        this.mensaje = "cambiar (" + propiedad1.getNombre() + ", " + dinero2 + "€)";
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.propiedad1 = propiedad1;
        this.propiedad2 = null;
        this.propiedad3 = null;
        this.dinero1 = 0;
        this.dinero2 = dinero2;
        this.numero_turnos = 0;
        Juego.consola.imprimirln(jugador2.getNombre() + ", ¿te doy " + propiedad1.getNombre() + " y tú me das " + dinero2 + "€?");
    }

    public Trato(int n, Jugador jugador1, int dinero1, Jugador jugador2, Propiedad propiedad1) {
        this.id = "trato" + n;
        this.mensaje = "cambiar (" + dinero1 + "€, " + propiedad1.getNombre() + ")";
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.propiedad1 = propiedad1;
        this.propiedad2 = null;
        this.propiedad3 = null;
        this.dinero1 = dinero1;
        this.dinero2 = 0;
        this.numero_turnos = 0;
        Juego.consola.imprimirln(jugador2.getNombre() + ", ¿te doy " + dinero1 + "€ y tú me das " + propiedad1.getNombre() + "?");
    }

    public Trato(int n, Jugador jugador1, Propiedad propiedad1, Jugador jugador2, Propiedad propiedad2, int dinero2) {
        this.id = "trato" + n;
        this.mensaje = "cambiar (" + propiedad1.getNombre() + ", " + propiedad2.getNombre() + " y " + dinero2 + "€)";
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.propiedad1 = propiedad1;
        this.propiedad2 = propiedad2;
        this.propiedad3 = null;
        this.dinero1 = 0;
        this.dinero2 = dinero2;
        this.numero_turnos = 0;
        Juego.consola.imprimirln(jugador2.getNombre() + ", ¿te doy " + propiedad1.getNombre() + " y tú me das " + propiedad2.getNombre() + " y " + dinero2 + "€?");
    }

    public Trato(int n, Jugador jugador1, Propiedad propiedad1, int dinero1, Jugador jugador2, Propiedad propiedad2) {
        this.id = "trato" + n;
        this.mensaje = "cambiar (" + propiedad1.getNombre() + " y " + dinero1 + "€, " + propiedad2.getNombre() + ")";
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.propiedad1 = propiedad1;
        this.propiedad2 = propiedad2;
        this.propiedad3 = null;
        this.dinero1 = dinero1;
        this.dinero2 = 0;
        this.numero_turnos = 0;
        Juego.consola.imprimirln(jugador2.getNombre() + ", ¿te doy " + propiedad1.getNombre() + " y " + dinero1 + "€ y tú me das " + propiedad2.getNombre() + "?");
    }

    public Trato(int n, Jugador jugador1, Propiedad propiedad1, Jugador jugador2, Propiedad propiedad2, Propiedad propiedad3, int numero_turnos) {
        this.id = "trato" + n;
        this.mensaje = "cambiar (" + propiedad1.getNombre() + ", " + propiedad2.getNombre() + " y noalquiler(" + propiedad3.getNombre() + ", " + numero_turnos + ")";
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.propiedad1 = propiedad1;
        this.propiedad2 = propiedad2;
        this.propiedad3 = propiedad3;
        this.dinero1 = 0;
        this.dinero2 = 0;
        this.numero_turnos = numero_turnos;
        Juego.consola.imprimirln(jugador2.getNombre() + ", ¿te doy " + propiedad1.getNombre() + " y tú me das " + propiedad2.getNombre() + " y no pago el alquiler en " + propiedad3.getNombre() + " durante " + numero_turnos + " turno(s)?");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public void setJugador1(Jugador jugador1) {
        this.jugador1 = jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public void setJugador2(Jugador jugador2) {
        this.jugador2 = jugador2;
    }

    public Propiedad getPropiedad1() {
        return propiedad1;
    }

    public void setPropiedad1(Propiedad propiedad1) {
        this.propiedad1 = propiedad1;
    }

    public Propiedad getPropiedad2() {
        return propiedad2;
    }

    public void setPropiedad2(Propiedad propiedad2) {
        this.propiedad2 = propiedad2;
    }

    public Propiedad getPropiedad3() {
        return propiedad3;
    }

    public void setPropiedad3(Propiedad propiedad3) {
        this.propiedad3 = propiedad3;
    }

    public int getDinero1() {
        return dinero1;
    }

    public void setDinero1(int dinero1) {
        this.dinero1 = dinero1;
    }

    public int getDinero2() {
        return dinero2;
    }

    public void setDinero2(int dinero2) {
        this.dinero2 = dinero2;
    }

    public int getNumero_turnos() {
        return numero_turnos;
    }

    public void setNumero_turnos(int numero_turnos) {
        this.numero_turnos = numero_turnos;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}

