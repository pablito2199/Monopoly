package Jugadores;

import Principal.Juego;

public final class Coche extends Avatar {
    private boolean puedocomprarcoche;
    private boolean movimientoespecialcoche;

    public Coche() {
        this.puedocomprarcoche = true;
        this.movimientoespecialcoche = false;
    }

    public Coche(Jugador jugador) {
        super(jugador);
        this.puedocomprarcoche = true;
        this.movimientoespecialcoche = false;
    }

    public boolean isPuedocomprarcoche() {
        return puedocomprarcoche;
    }

    public void setPuedocomprarcoche(boolean puedocomprarcoche) {
        this.puedocomprarcoche = puedocomprarcoche;
    }

    public boolean isMovimientoespecialcoche() {
        return movimientoespecialcoche;
    }

    public void setMovimientoespecialcoche(boolean movimientoespecialcoche) {
        this.movimientoespecialcoche = movimientoespecialcoche;
    }

    public void impAvatar(String id) {
        Juego.consola.imprimirln("{");
        Juego.consola.imprimirln("id: " + this.getId() + ",");
        Juego.consola.imprimirln("tipo: Coche,");
        Juego.consola.imprimirln("casilla: " + this.getCasillaAvatar().getNombre() + ",");
        Juego.consola.imprimirln("jugador: " + this.getJugador().getNombre());
        Juego.consola.imprimirln("}");
    }

    @Override
    public String toString() {
        return "{\n" +
                "id: " + this.getId() + ",\n" +
                "figura: Coche,\n" +
                "casilla: " + this.getCasillaAvatar().getNombre();
    }
}
