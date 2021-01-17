package Cartas;

import Casillas.Casilla;
import Excepciones.CasillaExcepcion.Compra.SinDinero;
import Jugadores.Avatar;
import Jugadores.Esfinge;
import Jugadores.Jugador;
import Jugadores.Sombrero;
import Principal.Juego;
import Principal.Valores;
import Tirada.Tablero;

import java.util.ArrayList;

public final class CajaComunidad extends Carta {

    public CajaComunidad(int numero_carta) {
        super(numero_carta);
    }

    public void accion(int numero_carta, Tablero tablero, Avatar avatartirada) throws SinDinero {
        switch (numero_carta) {
            case 1:
                Juego.consola.imprimir("Acción: Tu compañía de Internet obtiene beneficios. Recibe 2000000€");
                avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + 2000000);
                tablero.getCasillas().get(avatartirada.getPosicion()).anadirAvatar(avatartirada);
                avatartirada.getJugador().setPremiosInversionesOBote(avatartirada.getJugador().getPremiosInversionesOBote() + 2000000);
                if (avatartirada instanceof Esfinge)
                    ((Esfinge) avatartirada).setDineroUltimaCarta(2000000);
                else if (avatartirada instanceof Sombrero)
                    ((Sombrero) avatartirada).setDineroUltimaCarta(2000000);
                break;
            case 2:
                Juego.consola.imprimir("Acción: Devolución de Hacienda. Cobra 500000€.");
                avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + 500000);
                tablero.getCasillas().get(avatartirada.getPosicion()).anadirAvatar(avatartirada);
                avatartirada.getJugador().setPremiosInversionesOBote(avatartirada.getJugador().getPremiosInversionesOBote() + 500000);
                if (avatartirada instanceof Esfinge)
                    ((Esfinge) avatartirada).setDineroUltimaCarta(500000);
                else if (avatartirada instanceof Sombrero)
                    ((Sombrero) avatartirada).setDineroUltimaCarta(500000);
                break;
            case 3:
                Juego.consola.imprimir("Acción: Colócate en la casilla de Salida. Cobra " + Valores.getVueltaCompleta() + "€.");
                tablero.getCasillas().get(avatartirada.getPosicion()).eliminarAvatar(avatartirada);
                avatartirada.setCasillaAvatar(tablero.getCasillas().get(0));
                avatartirada.setPosicion(0);
                tablero.getCasillas().get(avatartirada.getPosicion()).anadirAvatar(avatartirada);
                avatartirada.getJugador().setCuentavueltas(avatartirada.getJugador().getCuentavueltas() + 1);
                avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getVueltaCompleta());
                avatartirada.getJugador().setPremiosInversionesOBote(avatartirada.getJugador().getPasarPorCasillaDeSalida() + Valores.getVueltaCompleta());
                if (avatartirada instanceof Esfinge)
                    ((Esfinge) avatartirada).setDineroUltimaCarta(Valores.getVueltaCompleta());
                else if (avatartirada instanceof Sombrero)
                    ((Sombrero) avatartirada).setDineroUltimaCarta(Valores.getVueltaCompleta());
                break;
            case 4:
                Juego.consola.imprimir("Acción: Recibe 1000000€ de beneficios por alquilar los servicios de tu jet privado.");
                avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + 1000000);
                tablero.getCasillas().get(avatartirada.getPosicion() - 1).anadirAvatar(avatartirada);
                avatartirada.getJugador().setPremiosInversionesOBote(avatartirada.getJugador().getPremiosInversionesOBote() + 1000000);
                if (avatartirada instanceof Esfinge)
                    ((Esfinge) avatartirada).setDineroUltimaCarta(1000000);
                else if (avatartirada instanceof Sombrero)
                    ((Sombrero) avatartirada).setDineroUltimaCarta(1000000);

                break;
            case 5:
                Juego.consola.imprimir("Acción: Paga 500000€ por un fin de semana en un balneario de 5 estrellas.");
                if (avatartirada.getJugador().getFortuna() >= 500000) {
                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() - 500000);
                    tablero.getCasillas().get(20).setCoste(tablero.getCasillas().get(20).getCoste() + 500000);
                    tablero.getCasillas().get(avatartirada.getPosicion()).anadirAvatar(avatartirada);
                    avatartirada.getJugador().setPagoTasasEImpuestos(avatartirada.getJugador().getPagoTasasEImpuestos() + 500000);
                    if (avatartirada instanceof Esfinge)
                        ((Esfinge) avatartirada).setDineroUltimaCarta(-500000);
                    else if (avatartirada instanceof Sombrero)
                        ((Sombrero) avatartirada).setDineroUltimaCarta(-500000);
                } else
                    throw new SinDinero("No tienes suficiente dinero para pagar a los jugadores.");
                break;
            case 6:
                int n = 0;
                Juego.consola.imprimir("Acción: Alquilas a tus compañeros una villa en Cannes durante una semana. Paga 200000€ a cada jugador.");
                ArrayList<Jugador> jugadores;
                jugadores = new ArrayList<>();
                for (Casilla c : tablero.getCasillas()) {
                    for (Avatar a : c.getAvatar()) {
                        if (c.getAvatar() != null) {
                            jugadores.add(a.getJugador());
                            n++;
                        }
                    }
                }
                if (avatartirada.getJugador().getFortuna() >= n * 200000) {
                    for (Jugador j : jugadores) {
                        if (avatartirada instanceof Esfinge)
                            ((Esfinge) avatartirada).setDineroUltimaCarta(((Esfinge) avatartirada).getDineroUltimaCarta() - 200000);
                        else if (avatartirada instanceof Sombrero)
                            ((Sombrero) avatartirada).setDineroUltimaCarta(((Sombrero) avatartirada).getDineroUltimaCarta() - 200000);
                        avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() - 200000);
                        tablero.getCasillas().get(20).setCoste(tablero.getCasillas().get(20).getCoste() + 200000);
                        avatartirada.getJugador().setPagoTasasEImpuestos(avatartirada.getJugador().getPagoTasasEImpuestos() + 200000);
                        j.setFortuna(j.getFortuna() + 200000);
                    }
                } else
                    throw new SinDinero("No tienes suficiente dinero para pagar a los jugadores.");
                tablero.getCasillas().get(avatartirada.getPosicion()).anadirAvatar(avatartirada);
                break;
        }
    }
}