package Jugadores;

import Principal.Juego;

public final class Pelota extends Avatar {
    private boolean movimientoespecialpelota;

    public Pelota() {
        this.movimientoespecialpelota = false;
    }

    public Pelota(Jugador jugador) {
        super(jugador);
        this.movimientoespecialpelota = false;
    }

    public boolean isMovimientoespecialpelota() {
        return movimientoespecialpelota;
    }

    public void setMovimientoespecialpelota(boolean movimientoespecialpelota) {
        this.movimientoespecialpelota = movimientoespecialpelota;
    }

    public void impAvatar(String id) {
        Juego.consola.imprimirln("{");
        Juego.consola.imprimirln("id: " + this.getId() + ",");
        Juego.consola.imprimirln("tipo: Pelota,");
        Juego.consola.imprimirln("casilla: " + this.getCasillaAvatar().getNombre() + ",");
        Juego.consola.imprimirln("jugador: " + this.getJugador().getNombre());
        Juego.consola.imprimirln("}");
    }

    @Override
    public String toString() {
        return "{\n" +
                "id: " + this.getId() + ",\n" +
                "figura: Pelota,\n" +
                "casilla: " + this.getCasillaAvatar().getNombre();
    }
}
