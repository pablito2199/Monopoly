package Jugadores;

import Casillas.Casilla;
import Principal.Juego;

import java.util.Random;

public abstract class Avatar {
    private String id;
    private Casilla casillaAvatar;
    private Jugador jugador;
    private int posicion;
    private boolean jugadorcarcel;
    private boolean noCobrarTrato;

    public Avatar() {
        this.id = null;
        this.jugador = null;
        this.posicion = 0;
        this.jugadorcarcel = false;
        this.noCobrarTrato = false;
    }

    public Avatar(Jugador jugador) {
        if (jugador == null) {
            Juego.consola.imprimirln("ERROR: jugador no inicializado");
            System.exit(1);
        }
        this.casillaAvatar = getCasillaAvatar();
        this.jugador = jugador;
        this.jugadorcarcel = false;
        this.noCobrarTrato = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Casilla getCasillaAvatar() {
        return casillaAvatar;
    }

    public void setCasillaAvatar(Casilla casillaAvatar) {
        this.casillaAvatar = casillaAvatar;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public boolean isJugadorcarcel() {
        return jugadorcarcel;
    }

    public void setJugadorcarcel(boolean jugadorcarcel) {
        this.jugadorcarcel = jugadorcarcel;
    }

    public boolean isNoCobrarTrato() {
        return noCobrarTrato;
    }

    public void setNoCobrarTrato(boolean noCobrarTrato) {
        this.noCobrarTrato = noCobrarTrato;
    }

    public final void generarId() {
        Random idAleatorio = new Random(System.currentTimeMillis());
        int numero = idAleatorio.nextInt(20) + 65;
        id = "" + (char) numero;
    }

    public abstract void impAvatar(String id);

    public final void moverEnBasico() {
        if (this instanceof Coche) {
            ((Coche) this).setMovimientoespecialcoche(false);
            Juego.consola.imprimir("A partir de ahora el avatar " + this.getId() + ", de tipo Coche, se moverá en modo básico.");
        } else if (this instanceof Pelota) {
            ((Pelota) this).setMovimientoespecialpelota(false);
            Juego.consola.imprimir("A partir de ahora el avatar " + this.getId() + ", de tipo Pelota, se moverá en modo básico.");
        } else if (this instanceof Sombrero) {
            ((Sombrero) this).setMovimientoespecialsombrero(false);
            Juego.consola.imprimir("A partir de ahora el avatar " + this.getId() + ", de tipo Sombrero, se moverá en modo básico.");
        } else if (this instanceof Esfinge) {
            ((Esfinge) this).setMovimientoespecialesfinge(false);
            Juego.consola.imprimir("A partir de ahora el avatar " + this.getId() + ", de tipo Esfinge, se moverá en modo básico.");
        }
    }

    public final void moverEnAvanzado() {
        if (this instanceof Coche) {
            ((Coche) this).setMovimientoespecialcoche(true);
            Juego.consola.imprimir("A partir de ahora el avatar " + this.getId() + ", de tipo Coche, se moverá en modo avanzado.");
        } else if (this instanceof Pelota) {
            ((Pelota) this).setMovimientoespecialpelota(true);
            Juego.consola.imprimir("A partir de ahora el avatar " + this.getId() + ", de tipo Pelota, se moverá en modo avanzado.");
        } else if (this instanceof Sombrero) {
            ((Sombrero) this).setMovimientoespecialsombrero(true);
            Juego.consola.imprimir("A partir de ahora el avatar " + this.getId() + ", de tipo Sombrero, se moverá en modo avanzado.");
        } else if (this instanceof Esfinge) {
            ((Esfinge) this).setMovimientoespecialesfinge(true);
            Juego.consola.imprimir("A partir de ahora el avatar " + this.getId() + ", de tipo Esfinge, se moverá en modo avanzado.");
        }
    }

    @Override
    public String toString() {
        return "\n{\n" +
                "id: " + id + ",\n" +
                "casilla: " + getCasillaAvatar().getNombre();
    }

    @Override
    public boolean equals(Object objeto) {
        if (objeto instanceof Avatar) {
            if (((Avatar) objeto).getId().equals(this.id)) {
                return true;
            }
        }
        return false;
    }
}