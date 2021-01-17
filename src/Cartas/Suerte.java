package Cartas;

import Casillas.Casilla;
import Casillas.Propiedad;
import Casillas.Solar;
import Edificios.Casa;
import Edificios.Edificio;
import Edificios.Hotel;
import Edificios.Piscina;
import Excepciones.CasillaExcepcion.Compra.SinDinero;
import Jugadores.Avatar;
import Jugadores.Esfinge;
import Jugadores.Jugador;
import Jugadores.Sombrero;
import Principal.Juego;
import Tirada.Tablero;

import java.util.ArrayList;

public final class Suerte extends Carta {

    public Suerte(int numero_carta) {
        super(numero_carta);
    }

    public void accion(int numero_carta, Tablero tablero, Avatar avatartirada) throws SinDinero {
        switch (numero_carta) {
            case 1:
                Juego.consola.imprimir("Acción: ¡Has ganado el bote de la lotería! Recibe 1000000€.");
                avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + 1000000);
                tablero.getCasillas().get(avatartirada.getPosicion()).anadirAvatar(avatartirada);
                avatartirada.getJugador().setPremiosInversionesOBote(avatartirada.getJugador().getPremiosInversionesOBote() + 1000000);
                if (avatartirada instanceof Esfinge)
                    ((Esfinge) avatartirada).setDineroUltimaCarta(1000000);
                else if (avatartirada instanceof Sombrero)
                    ((Sombrero) avatartirada).setDineroUltimaCarta(1000000);
                break;
            case 2:
                Juego.consola.imprimir("Acción: Vendes tu billete de avión para Terrassa en una subasta por Internet. Cobra 500000€.");
                avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + 500000);
                tablero.getCasillas().get(avatartirada.getPosicion()).anadirAvatar(avatartirada);
                avatartirada.getJugador().setPremiosInversionesOBote(avatartirada.getJugador().getPremiosInversionesOBote() + 500000);
                if (avatartirada instanceof Esfinge)
                    ((Esfinge) avatartirada).setDineroUltimaCarta(500000);
                else if (avatartirada instanceof Sombrero)
                    ((Sombrero) avatartirada).setDineroUltimaCarta(500000);
                break;
            case 3:
                Juego.consola.imprimir("Acción: Te multan por usar el móvil mientras conduces. Paga 150000€");
                if (avatartirada.getJugador().getFortuna() >= 150000) {
                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() - 150000);
                    tablero.getCasillas().get(20).setCoste(tablero.getCasillas().get(20).getCoste() + 150000);
                    tablero.getCasillas().get(avatartirada.getPosicion()).anadirAvatar(avatartirada);
                    avatartirada.getJugador().setPagoTasasEImpuestos(avatartirada.getJugador().getPagoTasasEImpuestos() + 150000);
                    if (avatartirada instanceof Esfinge)
                        ((Esfinge) avatartirada).setDineroUltimaCarta(-150000);
                    else if (avatartirada instanceof Sombrero)
                        ((Sombrero) avatartirada).setDineroUltimaCarta(-150000);
                } else
                    throw new SinDinero("No tienes suficiente dinero para pagar a los jugadores.");
                break;
            case 4:
                Juego.consola.imprimir("Acción: Beneficio por la venta de tus acciones. Recibe 1500000€.");
                avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + 1500000);
                tablero.getCasillas().get(avatartirada.getPosicion()).anadirAvatar(avatartirada);
                avatartirada.getJugador().setPremiosInversionesOBote(avatartirada.getJugador().getPremiosInversionesOBote() + 1500000);
                if (avatartirada instanceof Esfinge)
                    ((Esfinge) avatartirada).setDineroUltimaCarta(1500000);
                else if (avatartirada instanceof Sombrero)
                    ((Sombrero) avatartirada).setDineroUltimaCarta(1500000);
                break;
            case 5:
                Juego.consola.imprimir("Acción: El aumento del impuesto sobre bienes inmuebles afecta a todas tus propiedades. Paga 400000€ por casa, 115000€ por hotel, 200.000€ por piscina y 750000€ por pista de deportes.");
                int dineroCobrado = 0;
                for (Propiedad p : avatartirada.getJugador().getPropiedades()) {
                    if (p instanceof Solar) {
                        for (Edificio e : ((Solar) p).getEdificios()) {
                            if (e instanceof Casa) {
                                dineroCobrado += 400000;
                            } else if (e instanceof Hotel) {
                                dineroCobrado += 115000;
                            } else if (e instanceof Piscina) {
                                dineroCobrado += 200000;
                            } else {
                                dineroCobrado += 750000;
                            }
                        }
                    }
                }
                if (avatartirada.getJugador().getFortuna() >= dineroCobrado) {
                    if (avatartirada instanceof Esfinge)
                        ((Esfinge) avatartirada).setDineroUltimaCarta(dineroCobrado);
                    else if (avatartirada instanceof Sombrero)
                        ((Sombrero) avatartirada).setDineroUltimaCarta(dineroCobrado);
                    tablero.getCasillas().get(20).setCoste(tablero.getCasillas().get(20).getCoste() + dineroCobrado);
                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() - dineroCobrado);
                    avatartirada.getJugador().setPagoTasasEImpuestos(avatartirada.getJugador().getPagoTasasEImpuestos() + dineroCobrado);
                } else
                    throw new SinDinero("No tienes suficiente dinero para pagar a los jugadores.");
                tablero.getCasillas().get(avatartirada.getPosicion()).anadirAvatar(avatartirada);
                break;
            case 6:
                int n = 0;
                Juego.consola.imprimir("Acción: Has sido elegido presidente de la junta directiva. Paga a cada jugador 250000€.");
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
                if (avatartirada.getJugador().getFortuna() >= n * 250000) {
                    for (Jugador j : jugadores) {
                        if (avatartirada instanceof Esfinge)
                            ((Esfinge) avatartirada).setDineroUltimaCarta(((Esfinge) avatartirada).getDineroUltimaCarta() - 250000);
                        else if (avatartirada instanceof Sombrero)
                            ((Sombrero) avatartirada).setDineroUltimaCarta(((Sombrero) avatartirada).getDineroUltimaCarta() - 250000);
                        tablero.getCasillas().get(20).setCoste(tablero.getCasillas().get(20).getCoste() + 250000);
                        avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() - 250000);
                        j.setFortuna(j.getFortuna() + 250000);
                        avatartirada.getJugador().setPagoTasasEImpuestos(avatartirada.getJugador().getPagoTasasEImpuestos() + 250000);
                    }
                } else
                    throw new SinDinero("No tienes suficiente dinero para pagar a los jugadores.");
                tablero.getCasillas().get(avatartirada.getPosicion()).anadirAvatar(avatartirada);
                break;
        }
    }
}
