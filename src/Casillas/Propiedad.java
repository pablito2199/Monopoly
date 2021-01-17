package Casillas;

import Edificios.Casa;
import Edificios.Edificio;
import Edificios.Hotel;
import Edificios.Piscina;
import Excepciones.CasillaExcepcion.Compra.CompraExcepcion;
import Excepciones.CasillaExcepcion.Compra.NoPropiedad;
import Excepciones.CasillaExcepcion.Compra.SinDinero;
import Excepciones.CasillaExcepcion.Compra.YaComprada;
import Jugadores.Avatar;
import Jugadores.Coche;
import Jugadores.Jugador;
import Principal.Juego;
import Principal.Valores;

public abstract class Propiedad extends Casilla {
    private Jugador propietario;
    private double alquiler;
    private boolean hipotecada;
    private int ContadorNoCobrarTrato;

    public Propiedad() {
        this.propietario = null;
        this.alquiler = 0;
        this.hipotecada = false;
        this.ContadorNoCobrarTrato = 0;

    }

    public Propiedad(String nombre, double coste, int posicion, String color, double alquiler) {
        super(nombre, coste, color, posicion);
        this.propietario = null;
        this.alquiler = alquiler;
        this.hipotecada = false;
        this.ContadorNoCobrarTrato = 0;
    }

    public Jugador getPropietario() {
        return propietario;
    }

    public void setPropietario(Jugador propietario) {
        this.propietario = propietario;
    }

    public double getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(double alquiler) {
        this.alquiler = alquiler;
    }

    public boolean isHipotecada() {
        return hipotecada;
    }

    public void setHipotecada(boolean hipotecada) {
        this.hipotecada = hipotecada;
    }

    public int getContadorNoCobrarTrato() {
        return ContadorNoCobrarTrato;
    }

    public void setContadorNoCobrarTrato(int contadorNoCobrarTrato) {
        ContadorNoCobrarTrato = contadorNoCobrarTrato;
    }

    public void impCasilla() {
        Juego.consola.imprimirln("tipo: " + this.getClass().getName().split("Casillas.")[1] + ",");
        Juego.consola.imprimirln("propietario: ");
        if (getPropietario() != null) {
            Juego.consola.imprimirln("\t" + getPropietario().getNombre());
        }
    }

    public final boolean perteneceAJugador(Jugador jugador) {
        boolean pertenece = false;
        if (jugador.getNombre().equals(this.getPropietario().getNombre())) {
            pertenece = true;
        }
        return pertenece;
    }

    public final double valor(Propiedad prp) {
        double total = 0;
        total += prp.getCoste();
        if (prp instanceof Solar) {
            if (((Solar) prp).getEdificios().size() > 0) {
                for (Edificio e : ((Solar) prp).getEdificios()) {
                    if (prp.getGrupo().getNombre().equals("Negro")) {
                        if (e instanceof Casa)
                            total += Valores.getCasaGrupoNegro();
                        else if (e instanceof Hotel)
                            total += Valores.getHotelGrupoNegro();
                        else if (e instanceof Piscina)
                            total += Valores.getPiscinaGrupoNegro();
                        else
                            total += Valores.getPistaDeDeporteGrupoNegro();
                    } else if (prp.getGrupo().getNombre().equals("Cian")) {
                        if (e instanceof Casa)
                            total += Valores.getCasaGrupoCian();
                        else if (e instanceof Hotel)
                            total += Valores.getHotelGrupoCian();
                        else if (e instanceof Piscina)
                            total += Valores.getPiscinaGrupoCian();
                        else
                            total += Valores.getPistaDeDeporteGrupoCian();
                    } else if (prp.getGrupo().getNombre().equals("Violeta")) {
                        if (e instanceof Casa)
                            total += Valores.getCasaGrupoVioleta();
                        else if (e instanceof Hotel)
                            total += Valores.getHotelGrupoVioleta();
                        else if (e instanceof Piscina)
                            total += Valores.getPiscinaGrupoVioleta();
                        else
                            total += Valores.getPistaDeDeporteGrupoVioleta();
                    } else if (prp.getGrupo().getNombre().equals("Amarillo")) {
                        if (e instanceof Casa)
                            total += Valores.getCasaGrupoAmarillo();
                        else if (e instanceof Hotel)
                            total += Valores.getHotelGrupoAmarillo();
                        else if (e instanceof Piscina)
                            total += Valores.getPiscinaGrupoAmarillo();
                        else
                            total += Valores.getPistaDeDeporteGrupoAmarillo();
                    } else if (prp.getGrupo().getNombre().equals("Rojo")) {
                        if (e instanceof Casa)
                            total += Valores.getCasaGrupoRojo();
                        else if (e instanceof Hotel)
                            total += Valores.getHotelGrupoRojo();
                        else if (e instanceof Piscina)
                            total += Valores.getPiscinaGrupoRojo();
                        else
                            total += Valores.getPistaDeDeporteGrupoRojo();
                    } else if (prp.getGrupo().getNombre().equals("Gris")) {
                        if (e instanceof Casa)
                            total += Valores.getCasaGrupoGris();
                        else if (e instanceof Hotel)
                            total += Valores.getHotelGrupoGris();
                        else if (e instanceof Piscina)
                            total += Valores.getPiscinaGrupoGris();
                        else
                            total += Valores.getPistaDeDeporteGrupoGris();
                    } else if (prp.getGrupo().getNombre().equals("Verde")) {
                        if (e instanceof Casa)
                            total += Valores.getCasaGrupoVerde();
                        else if (e instanceof Hotel)
                            total += Valores.getHotelGrupoVerde();
                        else if (e instanceof Piscina)
                            total += Valores.getPiscinaGrupoVerde();
                        else
                            total += Valores.getPistaDeDeporteGrupoVerde();
                    } else {
                        if (e instanceof Casa)
                            total += Valores.getCasaGrupoAzul();
                        else if (e instanceof Hotel)
                            total += Valores.getHotelGrupoAzul();
                        else if (e instanceof Piscina)
                            total += Valores.getPiscinaGrupoAzul();
                        else
                            total += Valores.getPistaDeDeporteGrupoAzul();
                    }
                }
            }
        }
        return total;
    }

    public abstract void Alquiler(Avatar avatartirada) throws SinDinero;

    public final void comprar(Jugador jugadorturno, Propiedad casilla) throws CompraExcepcion {
        if (casilla instanceof Solar) {
            if (casilla.getPropietario() == null) {
                if (jugadorturno.getFortuna() >= jugadorturno.getAvatar().getCasillaAvatar().getCoste()) {
                    jugadorturno.anadirPropiedad(casilla);
                    casilla.setPropietario(jugadorturno);
                    jugadorturno.setFortuna(jugadorturno.getFortuna() - casilla.getCoste());
                    jugadorturno.setDineroGastado(jugadorturno.getDineroGastado() + casilla.getCoste());
                    Juego.consola.imprimir("El jugador " + jugadorturno.getNombre() + " compra la casilla " + jugadorturno.getAvatar().getCasillaAvatar().getNombre() + " por " + jugadorturno.getAvatar().getCasillaAvatar().getCoste() + ". Su fortuna actual es " + jugadorturno.getFortuna());
                    if (jugadorturno.getAvatar() instanceof Coche && ((Coche) jugadorturno.getAvatar()).isMovimientoespecialcoche()) {
                        ((Coche) jugadorturno.getAvatar()).setPuedocomprarcoche(false);
                    }
                } else
                    throw new SinDinero("No tienes dinero suficiente para comprar " + casilla.getNombre() + ".");
            } else if (casilla.getPropietario() != null && casilla.getPropietario().getNombre().equals(jugadorturno.getNombre())) {
                throw new YaComprada("Ya tienes la casilla " + casilla.getNombre() + " en propiedad.");
            } else {
                throw new NoPropiedad("La casilla " + jugadorturno.getAvatar().getCasillaAvatar().getNombre() + " ya tiene propietario. No puedes comprarla.");
            }
        } else if (casilla instanceof Transporte) {
            if (casilla.getPropietario() == null) {
                if (jugadorturno.getFortuna() >= jugadorturno.getAvatar().getCasillaAvatar().getCoste()) {
                    jugadorturno.anadirPropiedad(casilla);
                    casilla.setPropietario(jugadorturno);
                    jugadorturno.setFortuna(jugadorturno.getFortuna() - casilla.getCoste());
                    jugadorturno.setDineroGastado(jugadorturno.getDineroGastado() + casilla.getCoste());
                    Juego.consola.imprimir("El jugador " + jugadorturno.getNombre() + " compra la casilla " + jugadorturno.getAvatar().getCasillaAvatar().getNombre() + " por " + jugadorturno.getAvatar().getCasillaAvatar().getCoste() + ". Su fortuna actual es " + jugadorturno.getFortuna());
                    if (jugadorturno.getAvatar() instanceof Coche && ((Coche) jugadorturno.getAvatar()).isMovimientoespecialcoche()) {
                        ((Coche) jugadorturno.getAvatar()).setPuedocomprarcoche(false);
                    }
                } else
                    throw new SinDinero("No tienes dinero suficiente para comprar " + casilla.getNombre() + ".");
            } else if (casilla.getPropietario() != null && casilla.getPropietario().getNombre().equals(jugadorturno.getNombre())) {
                throw new YaComprada("Ya tienes la casilla " + casilla.getNombre() + " en propiedad.");
            } else {
                throw new NoPropiedad("La casilla " + jugadorturno.getAvatar().getCasillaAvatar().getNombre() + " ya tiene propietario. No puedes comprarla.");
            }
        } else if (casilla instanceof Servicio) {
            if (casilla.getPropietario() == null) {
                if (jugadorturno.getFortuna() > jugadorturno.getAvatar().getCasillaAvatar().getCoste()) {
                    jugadorturno.anadirPropiedad(casilla);
                    casilla.setPropietario(jugadorturno);
                    jugadorturno.setFortuna(jugadorturno.getFortuna() - casilla.getCoste());
                    jugadorturno.setDineroGastado(jugadorturno.getDineroGastado() + casilla.getCoste());
                    Juego.consola.imprimir("El jugador " + jugadorturno.getNombre() + " compra la casilla " + jugadorturno.getAvatar().getCasillaAvatar().getNombre() + " por " + jugadorturno.getAvatar().getCasillaAvatar().getCoste() + ". Su fortuna actual es " + jugadorturno.getFortuna());
                    if (jugadorturno.getAvatar() instanceof Coche && ((Coche) jugadorturno.getAvatar()).isMovimientoespecialcoche()) {
                        ((Coche) jugadorturno.getAvatar()).setPuedocomprarcoche(false);
                    }
                } else
                    throw new SinDinero("No tienes dinero suficiente para comprar " + casilla.getNombre() + ".");
            } else if (casilla.getPropietario() != null && casilla.getPropietario().getNombre().equals(jugadorturno.getNombre())) {
                throw new YaComprada("Ya tienes la casilla " + casilla.getNombre() + " en propiedad.");
            } else {
                throw new NoPropiedad("La casilla " + jugadorturno.getAvatar().getCasillaAvatar().getNombre() + " ya tiene propietario. No puedes comprarla.");
            }
        }
    }
}
