package Casillas;

import Excepciones.CasillaExcepcion.Compra.SinDinero;
import Jugadores.Avatar;
import Jugadores.Esfinge;
import Jugadores.Sombrero;
import Principal.Juego;

public final class Transporte extends Propiedad {
    private double cobradoEnAlquiler;

    public Transporte() {
        this.cobradoEnAlquiler = 0;
    }

    public Transporte(String nombre, double coste, int posicion, String color, double alquiler) {
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
        if (avatartirada.getCasillaAvatar() instanceof Transporte) {
            if (((Transporte) avatartirada.getCasillaAvatar()).getPropietario() != null && !((Transporte) avatartirada.getCasillaAvatar()).perteneceAJugador(avatartirada.getJugador()) && !isHipotecada()) {
                if (((Transporte) avatartirada.getCasillaAvatar()).getContadorNoCobrarTrato() == 0 && !avatartirada.isNoCobrarTrato()) {
                    if (avatartirada.getJugador().getFortuna() >= ((Transporte) avatartirada.getCasillaAvatar()).getAlquiler()) {
                        Juego.consola.imprimir("La casilla " + avatartirada.getCasillaAvatar().getNombre() + " ya tiene propietario. Deberá pagar " + ((Transporte) avatartirada.getCasillaAvatar()).getAlquiler());
                        avatartirada.getJugador().setPagoDeAlquileres(avatartirada.getJugador().getPagoDeAlquileres() + ((Transporte) avatartirada.getCasillaAvatar()).getAlquiler());
                        ((Transporte) avatartirada.getCasillaAvatar()).getPropietario().setCobroDeAlquileres(((Transporte) avatartirada.getCasillaAvatar()).getPropietario().getCobroDeAlquileres() + ((Transporte) avatartirada.getCasillaAvatar()).getAlquiler());
                        double fortunaactual = avatartirada.getJugador().getFortuna() - ((Transporte) avatartirada.getCasillaAvatar()).getAlquiler();
                        avatartirada.getJugador().setFortuna(fortunaactual);
                        ((Transporte) avatartirada.getCasillaAvatar()).setCobradoEnAlquiler(((Transporte) avatartirada.getCasillaAvatar()).getCobradoEnAlquiler() + ((Transporte) avatartirada.getCasillaAvatar()).getAlquiler());
                        if (avatartirada instanceof Esfinge && ((Esfinge) avatartirada).isMovimientoespecialesfinge())
                            ((Esfinge) avatartirada).setCobraAlquiler(true);
                        else if (avatartirada instanceof Sombrero && ((Sombrero) avatartirada).isMovimientoespecialsombrero())
                            ((Sombrero) avatartirada).setCobraAlquiler(true);
                    } else
                        throw new SinDinero("No tienes suficiente dinero para pagar a " + ((Transporte) avatartirada.getCasillaAvatar()).getPropietario() + ". Huye, quiere matarte.");
                } else {
                    ((Transporte) avatartirada.getCasillaAvatar()).setContadorNoCobrarTrato(((Transporte) avatartirada.getCasillaAvatar()).getContadorNoCobrarTrato() - 1);
                    if (((Transporte) avatartirada.getCasillaAvatar()).getContadorNoCobrarTrato() == 0)
                        avatartirada.setNoCobrarTrato(false);
                    Juego.consola.imprimirln("No se cobra el alquiler de " + avatartirada.getCasillaAvatar().getNombre() + " por el trato realizado anteriormente. Quedan " + ((Transporte) avatartirada.getCasillaAvatar()).getContadorNoCobrarTrato() + " turno(s) para finalizar el trato.");
                }
            }
        }
    }
}