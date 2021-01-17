package Casillas;

import Excepciones.CasillaExcepcion.Compra.SinDinero;
import Jugadores.Avatar;
import Jugadores.Esfinge;
import Jugadores.Sombrero;
import Principal.Juego;

public final class Servicio extends Propiedad {
    private double cobradoEnAlquiler;

    public Servicio() {
        this.cobradoEnAlquiler = 0;
    }

    public Servicio(String nombre, double coste, int posicion, String color, double alquiler) {
        super(nombre, coste, posicion, color, alquiler);
        this.cobradoEnAlquiler = 0;
    }

    public double getCobradoEnAlquiler() {
        return cobradoEnAlquiler;
    }

    public void setCobradoEnAlquiler(double cobradoEnAlquiler) {
        this.cobradoEnAlquiler = cobradoEnAlquiler;
    }

    public void Alquiler(Avatar avatartirada) throws SinDinero {
        if (avatartirada.getCasillaAvatar() instanceof Servicio) {
            if (((Servicio) avatartirada.getCasillaAvatar()).getPropietario() != null && !((Servicio) avatartirada.getCasillaAvatar()).perteneceAJugador(avatartirada.getJugador()) && !isHipotecada()) {
                if (((Servicio) avatartirada.getCasillaAvatar()).getContadorNoCobrarTrato() == 0 && !avatartirada.isNoCobrarTrato()) {
                    if (avatartirada.getJugador().getFortuna() >= ((Servicio) avatartirada.getCasillaAvatar()).getAlquiler()) {
                        Juego.consola.imprimir("La casilla " + avatartirada.getCasillaAvatar().getNombre() + " ya tiene propietario. Deberá pagar " + ((Servicio) avatartirada.getCasillaAvatar()).getAlquiler());
                        avatartirada.getJugador().setPagoDeAlquileres(avatartirada.getJugador().getPagoDeAlquileres() + ((Servicio) avatartirada.getCasillaAvatar()).getAlquiler());
                        ((Servicio) avatartirada.getCasillaAvatar()).getPropietario().setCobroDeAlquileres(((Servicio) avatartirada.getCasillaAvatar()).getPropietario().getCobroDeAlquileres() + ((Servicio) avatartirada.getCasillaAvatar()).getAlquiler());
                        double fortunaactual = avatartirada.getJugador().getFortuna() - ((Servicio) avatartirada.getCasillaAvatar()).getAlquiler();
                        avatartirada.getJugador().setFortuna(fortunaactual);
                        ((Servicio) avatartirada.getCasillaAvatar()).setCobradoEnAlquiler(((Servicio) avatartirada.getCasillaAvatar()).getCobradoEnAlquiler() + ((Servicio) avatartirada.getCasillaAvatar()).getAlquiler());
                        if (avatartirada instanceof Esfinge && ((Esfinge) avatartirada).isMovimientoespecialesfinge())
                            ((Esfinge) avatartirada).setCobraAlquiler(true);
                        else if (avatartirada instanceof Sombrero && ((Sombrero) avatartirada).isMovimientoespecialsombrero())
                            ((Sombrero) avatartirada).setCobraAlquiler(true);
                    } else
                        throw new SinDinero("No tienes suficiente dinero para pagar a " + ((Solar) avatartirada.getCasillaAvatar()).getPropietario() + ". Huye, quiere matarte.");
                } else {
                    ((Servicio) avatartirada.getCasillaAvatar()).setContadorNoCobrarTrato(((Servicio) avatartirada.getCasillaAvatar()).getContadorNoCobrarTrato() - 1);
                    if (((Servicio) avatartirada.getCasillaAvatar()).getContadorNoCobrarTrato() == 0)
                        avatartirada.setNoCobrarTrato(false);
                    Juego.consola.imprimirln("No se cobra el alquiler de " + avatartirada.getCasillaAvatar().getNombre() + " por el trato realizado anteriormente. Quedan " + ((Servicio) avatartirada.getCasillaAvatar()).getContadorNoCobrarTrato() + " turno(s) para finalizar el trato.");
                }
            }
        }
    }
}