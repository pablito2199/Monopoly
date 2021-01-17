package Casillas;

import Edificios.*;
import Excepciones.CasillaExcepcion.Compra.SinDinero;
import Excepciones.CasillaExcepcion.Edificar.ImposibilidadEdificar;
import Excepciones.Excepcion;
import Excepciones.Generales.ComandoInexistente;
import Jugadores.Avatar;
import Jugadores.Esfinge;
import Jugadores.Jugador;
import Jugadores.Sombrero;
import Principal.Juego;
import Principal.Valores;

import java.util.ArrayList;

public final class Solar extends Propiedad {
    private ArrayList<Edificio> edificios;
    private int numerocasas;
    private int numerohoteles;
    private int numeropiscinas;
    private int numeropistas;
    private double cobradoEnAlquiler;
    private boolean puedoconstruircasa;

    public Solar() {
        this.edificios = new ArrayList<>();
        this.numerocasas = 0;
        this.numerohoteles = 0;
        this.numeropiscinas = 0;
        this.numeropistas = 0;
        this.cobradoEnAlquiler = 0;
        this.puedoconstruircasa = false;
    }

    public Solar(String nombre, double coste, int posicion, String color, double alquiler) {
        super(nombre, coste, posicion, color, alquiler);
        this.edificios = new ArrayList<>();
        this.numerocasas = 0;
        this.numerohoteles = 0;
        this.numeropiscinas = 0;
        this.numeropistas = 0;
        this.puedoconstruircasa = false;
    }

    public ArrayList<Edificio> getEdificios() {
        return edificios;
    }

    public void setEdificios(ArrayList<Edificio> edificios) {
        this.edificios = edificios;
    }

    public int getNumerocasas() {
        return numerocasas;
    }

    public void setNumerocasas(int numerocasas) {
        this.numerocasas = numerocasas;
    }

    public int getNumerohoteles() {
        return numerohoteles;
    }

    public void setNumerohoteles(int numerohoteles) {
        this.numerohoteles = numerohoteles;
    }

    public int getNumeropiscinas() {
        return numeropiscinas;
    }

    public void setNumeropiscinas(int numeropiscinas) {
        this.numeropiscinas = numeropiscinas;
    }

    public int getNumeropistas() {
        return numeropistas;
    }

    public void setNumeropistas(int numeropistas) {
        this.numeropistas = numeropistas;
    }

    public double getCobradoEnAlquiler() {
        return cobradoEnAlquiler;
    }

    public void setCobradoEnAlquiler(double cobradoEnAlquiler) {
        this.cobradoEnAlquiler = cobradoEnAlquiler;
    }

    public boolean isPuedoconstruircasa() {
        return puedoconstruircasa;
    }

    public void setPuedoconstruircasa(boolean puedoconstruircasa) {
        this.puedoconstruircasa = puedoconstruircasa;
    }

    private void anadirEdificio(Edificio edificio) {
        this.edificios.add(edificio);
        if (edificio instanceof Casa) {
            setNumerocasas(getNumerocasas() + 1);
        }
        if (edificio instanceof Hotel) {
            setNumerohoteles(getNumerohoteles() + 1);
        }
        if (edificio instanceof Piscina) {
            setNumeropiscinas(getNumeropiscinas() + 1);
        }
        if (edificio instanceof PistaDeporte) {
            setNumeropistas(getNumeropistas() + 1);
        }
    }

    public void eliminarEdificio(Edificio edificio) {
        this.edificios.remove(edificio);
    }

    @Override
    public void impCasilla() {
        super.impCasilla();
        if (getGrupo().getNombre().equals("Negro")) {
            Juego.consola.imprimirln("grupo: " + super.getGrupo().getNombre() + ",");
            Juego.consola.imprimirln("valor: " + getCoste() + ",");
            Juego.consola.imprimirln("alquiler: " + getAlquiler() + ",");
            Juego.consola.imprimirln("valor hotel: " + Valores.getHotelGrupoNegro() + ",");
            Juego.consola.imprimirln("valor casa: " + Valores.getCasaGrupoNegro() + ",");
            Juego.consola.imprimirln("valor piscina: " + Valores.getPiscinaGrupoNegro() + ",");
            Juego.consola.imprimirln("valor pista deporte: " + Valores.getPistaDeDeporteGrupoNegro() + ",");
            Juego.consola.imprimirln("alquiler casa: " + 0.05 * Valores.getAlquilerGrupoNegro() + ",");
            Juego.consola.imprimirln("alquiler dos casas: " + 0.15 * Valores.getAlquilerGrupoNegro() + ",");
            Juego.consola.imprimirln("alquiler tres casas: " + 0.35 * Valores.getAlquilerGrupoNegro() + ",");
            Juego.consola.imprimirln("alquiler cuatro casas: " + 0.5 * Valores.getAlquilerGrupoNegro() + ",");
            Juego.consola.imprimirln("alquiler hotel: " + 0.7 * Valores.getAlquilerGrupoNegro() + ",");
            Juego.consola.imprimirln("alquiler piscina: " + 0.25 * Valores.getAlquilerGrupoNegro() + ",");
            Juego.consola.imprimirln("alquiler pista de deporte: " + 0.25 * Valores.getAlquilerGrupoNegro() + ",");
            if (getEdificios().size() != 0) {
                Juego.consola.imprimirln("edificios construídos: ");
                for (Edificio e : getEdificios()) {
                    if (e instanceof Casa)
                        Juego.consola.imprimirln("\t" + ((Casa) e).getId() + ", ");
                    else if (e instanceof Hotel)
                        Juego.consola.imprimirln("\t" + ((Hotel) e).getId() + ", ");
                    else if (e instanceof Piscina)
                        Juego.consola.imprimirln("\t" + ((Piscina) e).getId() + ", ");
                    else
                        Juego.consola.imprimirln("\t" + ((PistaDeporte) e).getId() + ", ");
                }
            }
        } else if (getGrupo().getNombre().equals("Cian")) {
            Juego.consola.imprimirln("grupo: " + getGrupo().getNombre() + ",");
            Juego.consola.imprimirln("valor: " + getCoste() + ",");
            Juego.consola.imprimirln("alquiler: " + getAlquiler() + ",");
            Juego.consola.imprimirln("valor hotel: " + Valores.getHotelGrupoCian() + ",");
            Juego.consola.imprimirln("valor casa: " + Valores.getCasaGrupoCian() + ",");
            Juego.consola.imprimirln("valor piscina: " + Valores.getPiscinaGrupoCian() + ",");
            Juego.consola.imprimirln("valor pista deporte: " + Valores.getPistaDeDeporteGrupoCian() + ",");
            Juego.consola.imprimirln("alquiler casa: " + 0.05 * Valores.getAlquilerGrupoCian() + ",");
            Juego.consola.imprimirln("alquiler dos casas: " + 0.15 * Valores.getAlquilerGrupoCian() + ",");
            Juego.consola.imprimirln("alquiler tres casas: " + 0.35 * Valores.getAlquilerGrupoCian() + ",");
            Juego.consola.imprimirln("alquiler cuatro casas: " + 0.5 * Valores.getAlquilerGrupoCian() + ",");
            Juego.consola.imprimirln("alquiler hotel: " + 0.7 * Valores.getAlquilerGrupoCian() + ",");
            Juego.consola.imprimirln("alquiler piscina: " + 0.25 * Valores.getAlquilerGrupoCian() + ",");
            Juego.consola.imprimirln("alquiler pista de deporte: " + 0.25 * Valores.getAlquilerGrupoCian() + ",");
            if (getEdificios().size() != 0) {
                Juego.consola.imprimirln("edificios construídos: ");
                for (Edificio e : getEdificios()) {
                    if (e instanceof Casa)
                        Juego.consola.imprimirln("\t" + ((Casa) e).getId() + ", ");
                    else if (e instanceof Hotel)
                        Juego.consola.imprimirln("\t" + ((Hotel) e).getId() + ", ");
                    else if (e instanceof Piscina)
                        Juego.consola.imprimirln("\t" + ((Piscina) e).getId() + ", ");
                    else
                        Juego.consola.imprimirln("\t" + ((PistaDeporte) e).getId() + ", ");
                }
            }
        } else if (getGrupo().getNombre().equals("Violeta")) {
            Juego.consola.imprimirln("grupo: " + getGrupo().getNombre() + ",");
            Juego.consola.imprimirln("propietario: ");
            Juego.consola.imprimirln("valor: " + getCoste() + ",");
            Juego.consola.imprimirln("alquiler: " + getAlquiler() + ",");
            Juego.consola.imprimirln("valor hotel: " + Valores.getHotelGrupoVioleta() + ",");
            Juego.consola.imprimirln("valor casa: " + Valores.getCasaGrupoVioleta() + ",");
            Juego.consola.imprimirln("valor piscina: " + Valores.getPiscinaGrupoVioleta() + ",");
            Juego.consola.imprimirln("valor pista deporte: " + Valores.getPistaDeDeporteGrupoVioleta() + ",");
            Juego.consola.imprimirln("alquiler casa: " + 0.05 * Valores.getAlquilerGrupoVioleta() + ",");
            Juego.consola.imprimirln("alquiler dos casas: " + 0.15 * Valores.getAlquilerGrupoVioleta() + ",");
            Juego.consola.imprimirln("alquiler tres casas: " + 0.35 * Valores.getAlquilerGrupoVioleta() + ",");
            Juego.consola.imprimirln("alquiler cuatro casas: " + 0.5 * Valores.getAlquilerGrupoVioleta() + ",");
            Juego.consola.imprimirln("alquiler hotel: " + 0.7 * Valores.getAlquilerGrupoVioleta() + ",");
            Juego.consola.imprimirln("alquiler piscina: " + 0.25 * Valores.getAlquilerGrupoVioleta() + ",");
            Juego.consola.imprimirln("alquiler pista de deporte: " + 0.25 * Valores.getAlquilerGrupoVioleta() + ",");
            if (getEdificios().size() != 0) {
                Juego.consola.imprimirln("edificios construídos: ");
                for (Edificio e : getEdificios()) {
                    if (e instanceof Casa)
                        Juego.consola.imprimirln("\t" + ((Casa) e).getId() + ", ");
                    else if (e instanceof Hotel)
                        Juego.consola.imprimirln("\t" + ((Hotel) e).getId() + ", ");
                    else if (e instanceof Piscina)
                        Juego.consola.imprimirln("\t" + ((Piscina) e).getId() + ", ");
                    else
                        Juego.consola.imprimirln("\t" + ((PistaDeporte) e).getId() + ", ");
                }
            }
        } else if (getGrupo().getNombre().equals("Amarillo")) {
            Juego.consola.imprimirln("grupo: " + getGrupo().getNombre() + ",");
            Juego.consola.imprimirln("valor: " + getCoste() + ",");
            Juego.consola.imprimirln("alquiler: " + getAlquiler() + ",");
            Juego.consola.imprimirln("valor hotel: " + Valores.getHotelGrupoAmarillo() + ",");
            Juego.consola.imprimirln("valor casa: " + Valores.getCasaGrupoAmarillo() + ",");
            Juego.consola.imprimirln("valor piscina: " + Valores.getPiscinaGrupoAmarillo() + ",");
            Juego.consola.imprimirln("valor pista deporte: " + Valores.getPistaDeDeporteGrupoAmarillo() + ",");
            Juego.consola.imprimirln("alquiler casa: " + 0.05 * Valores.getAlquilerGrupoAmarillo() + ",");
            Juego.consola.imprimirln("alquiler dos casas: " + 0.15 * Valores.getAlquilerGrupoAmarillo() + ",");
            Juego.consola.imprimirln("alquiler tres casas: " + 0.35 * Valores.getAlquilerGrupoAmarillo() + ",");
            Juego.consola.imprimirln("alquiler cuatro casas: " + 0.5 * Valores.getAlquilerGrupoAmarillo() + ",");
            Juego.consola.imprimirln("alquiler hotel: " + 0.7 * Valores.getAlquilerGrupoAmarillo() + ",");
            Juego.consola.imprimirln("alquiler piscina: " + 0.25 * Valores.getAlquilerGrupoAmarillo() + ",");
            Juego.consola.imprimirln("alquiler pista de deporte: " + 0.25 * Valores.getAlquilerGrupoAmarillo() + ",");
            if (getEdificios().size() != 0) {
                Juego.consola.imprimirln("edificios construídos: ");
                for (Edificio e : getEdificios()) {
                    if (e instanceof Casa)
                        Juego.consola.imprimirln("\t" + ((Casa) e).getId() + ", ");
                    else if (e instanceof Hotel)
                        Juego.consola.imprimirln("\t" + ((Hotel) e).getId() + ", ");
                    else if (e instanceof Piscina)
                        Juego.consola.imprimirln("\t" + ((Piscina) e).getId() + ", ");
                    else
                        Juego.consola.imprimirln("\t" + ((PistaDeporte) e).getId() + ", ");
                }
            }
        } else if (getGrupo().getNombre().equals("Rojo")) {
            Juego.consola.imprimirln("grupo: " + getGrupo().getNombre() + ",");
            Juego.consola.imprimirln("valor: " + getCoste() + ",");
            Juego.consola.imprimirln("alquiler: " + getAlquiler() + ",");
            Juego.consola.imprimirln("valor hotel: " + Valores.getHotelGrupoRojo() + ",");
            Juego.consola.imprimirln("valor casa: " + Valores.getCasaGrupoRojo() + ",");
            Juego.consola.imprimirln("valor piscina: " + Valores.getPiscinaGrupoRojo() + ",");
            Juego.consola.imprimirln("valor pista deporte: " + Valores.getPistaDeDeporteGrupoRojo() + ",");
            Juego.consola.imprimirln("alquiler casa: " + 0.05 * Valores.getAlquilerGrupoRojo() + ",");
            Juego.consola.imprimirln("alquiler dos casas: " + 0.15 * Valores.getAlquilerGrupoRojo() + ",");
            Juego.consola.imprimirln("alquiler tres casas: " + 0.35 * Valores.getAlquilerGrupoRojo() + ",");
            Juego.consola.imprimirln("alquiler cuatro casas: " + 0.5 * Valores.getAlquilerGrupoRojo() + ",");
            Juego.consola.imprimirln("alquiler hotel: " + 0.7 * Valores.getAlquilerGrupoRojo() + ",");
            Juego.consola.imprimirln("alquiler piscina: " + 0.25 * Valores.getAlquilerGrupoRojo() + ",");
            Juego.consola.imprimirln("alquiler pista de deporte: " + 0.25 * Valores.getAlquilerGrupoRojo() + ",");
            if (getEdificios().size() != 0) {
                Juego.consola.imprimirln("edificios construídos: ");
                for (Edificio e : getEdificios()) {
                    if (e instanceof Casa)
                        Juego.consola.imprimirln("\t" + ((Casa) e).getId() + ", ");
                    else if (e instanceof Hotel)
                        Juego.consola.imprimirln("\t" + ((Hotel) e).getId() + ", ");
                    else if (e instanceof Piscina)
                        Juego.consola.imprimirln("\t" + ((Piscina) e).getId() + ", ");
                    else
                        Juego.consola.imprimirln("\t" + ((PistaDeporte) e).getId() + ", ");
                }
            }
        } else if (getGrupo().getNombre().equals("Gris")) {
            Juego.consola.imprimirln("grupo: " + getGrupo().getNombre() + ",");
            Juego.consola.imprimirln("valor: " + getCoste() + ",");
            Juego.consola.imprimirln("alquiler: " + getAlquiler() + ",");
            Juego.consola.imprimirln("valor hotel: " + Valores.getHotelGrupoGris() + ",");
            Juego.consola.imprimirln("valor casa: " + Valores.getCasaGrupoGris() + ",");
            Juego.consola.imprimirln("valor piscina: " + Valores.getPiscinaGrupoGris() + ",");
            Juego.consola.imprimirln("valor pista deporte: " + Valores.getPistaDeDeporteGrupoGris() + ",");
            Juego.consola.imprimirln("alquiler casa: " + 0.05 * Valores.getAlquilerGrupoGris() + ",");
            Juego.consola.imprimirln("alquiler dos casas: " + 0.15 * Valores.getAlquilerGrupoGris() + ",");
            Juego.consola.imprimirln("alquiler tres casas: " + 0.35 * Valores.getAlquilerGrupoGris() + ",");
            Juego.consola.imprimirln("alquiler cuatro casas: " + 0.5 * Valores.getAlquilerGrupoGris() + ",");
            Juego.consola.imprimirln("alquiler hotel: " + 0.7 * Valores.getAlquilerGrupoGris() + ",");
            Juego.consola.imprimirln("alquiler piscina: " + 0.25 * Valores.getAlquilerGrupoGris() + ",");
            Juego.consola.imprimirln("alquiler pista de deporte: " + 0.25 * Valores.getAlquilerGrupoGris() + ",");
            if (getEdificios().size() != 0) {
                Juego.consola.imprimirln("edificios construídos: ");
                for (Edificio e : getEdificios()) {
                    if (e instanceof Casa)
                        Juego.consola.imprimirln("\t" + ((Casa) e).getId() + ", ");
                    else if (e instanceof Hotel)
                        Juego.consola.imprimirln("\t" + ((Hotel) e).getId() + ", ");
                    else if (e instanceof Piscina)
                        Juego.consola.imprimirln("\t" + ((Piscina) e).getId() + ", ");
                    else
                        Juego.consola.imprimirln("\t" + ((PistaDeporte) e).getId() + ", ");
                }
            }
        } else if (getGrupo().getNombre().equals("Verde")) {
            Juego.consola.imprimirln("grupo: " + getGrupo().getNombre() + ",");
            Juego.consola.imprimirln("valor: " + getCoste() + ",");
            Juego.consola.imprimirln("alquiler: " + getAlquiler() + ",");
            Juego.consola.imprimirln("valor hotel: " + Valores.getHotelGrupoVerde() + ",");
            Juego.consola.imprimirln("valor casa: " + Valores.getCasaGrupoVerde() + ",");
            Juego.consola.imprimirln("valor piscina: " + Valores.getPiscinaGrupoVerde() + ",");
            Juego.consola.imprimirln("valor pista deporte: " + Valores.getPistaDeDeporteGrupoVerde() + ",");
            Juego.consola.imprimirln("alquiler casa: " + 0.05 * Valores.getAlquilerGrupoVerde() + ",");
            Juego.consola.imprimirln("alquiler dos casas: " + 0.15 * Valores.getAlquilerGrupoVerde() + ",");
            Juego.consola.imprimirln("alquiler tres casas: " + 0.35 * Valores.getAlquilerGrupoVerde() + ",");
            Juego.consola.imprimirln("alquiler cuatro casas: " + 0.5 * Valores.getAlquilerGrupoVerde() + ",");
            Juego.consola.imprimirln("alquiler hotel: " + 0.7 * Valores.getAlquilerGrupoVerde() + ",");
            Juego.consola.imprimirln("alquiler piscina: " + 0.25 * Valores.getAlquilerGrupoVerde() + ",");
            Juego.consola.imprimirln("alquiler pista de deporte: " + 0.25 * Valores.getAlquilerGrupoVerde() + ",");
            if (getEdificios().size() != 0) {
                Juego.consola.imprimirln("edificios construídos: ");
                for (Edificio e : getEdificios()) {
                    if (e instanceof Casa)
                        Juego.consola.imprimirln("\t" + ((Casa) e).getId() + ", ");
                    else if (e instanceof Hotel)
                        Juego.consola.imprimirln("\t" + ((Hotel) e).getId() + ", ");
                    else if (e instanceof Piscina)
                        Juego.consola.imprimirln("\t" + ((Piscina) e).getId() + ", ");
                    else
                        Juego.consola.imprimirln("\t" + ((PistaDeporte) e).getId() + ", ");
                }
            }
        } else {
            Juego.consola.imprimirln("grupo: " + getGrupo().getNombre() + ",");
            Juego.consola.imprimirln("valor: " + getCoste() + ",");
            Juego.consola.imprimirln("alquiler: " + getAlquiler() + ",");
            Juego.consola.imprimirln("valor hotel: " + Valores.getHotelGrupoAzul() + ",");
            Juego.consola.imprimirln("valor casa: " + Valores.getCasaGrupoAzul() + ",");
            Juego.consola.imprimirln("valor piscina: " + Valores.getPiscinaGrupoAzul() + ",");
            Juego.consola.imprimirln("valor pista deporte: " + Valores.getPistaDeDeporteGrupoAzul() + ",");
            Juego.consola.imprimirln("alquiler casa: " + 0.05 * Valores.getAlquilerGrupoAzul() + ",");
            Juego.consola.imprimirln("alquiler dos casas: " + 0.15 * Valores.getAlquilerGrupoAzul() + ",");
            Juego.consola.imprimirln("alquiler tres casas: " + 0.35 * Valores.getAlquilerGrupoAzul() + ",");
            Juego.consola.imprimirln("alquiler cuatro casas: " + 0.5 * Valores.getAlquilerGrupoAzul() + ",");
            Juego.consola.imprimirln("alquiler hotel: " + 0.7 * Valores.getAlquilerGrupoAzul() + ",");
            Juego.consola.imprimirln("alquiler piscina: " + 0.25 * Valores.getAlquilerGrupoAzul() + ",");
            Juego.consola.imprimirln("alquiler pista de deporte: " + 0.25 * Valores.getAlquilerGrupoAzul() + ",");
            if (getEdificios().size() != 0) {
                Juego.consola.imprimirln("edificios construídos: ");
                for (Edificio e : getEdificios()) {
                    if (e instanceof Casa)
                        Juego.consola.imprimirln("\t" + ((Casa) e).getId() + ", ");
                    else if (e instanceof Hotel)
                        Juego.consola.imprimirln("\t" + ((Hotel) e).getId() + ", ");
                    else if (e instanceof Piscina)
                        Juego.consola.imprimirln("\t" + ((Piscina) e).getId() + ", ");
                    else
                        Juego.consola.imprimirln("\t" + ((PistaDeporte) e).getId() + ", ");
                }
            }
        }
    }

    private void compraCasa(Jugador jugador, Casilla casilla) throws SinDinero {
        if (casilla.getGrupo().getNombre().equals("Negro")) {
            if (jugador.getFortuna() >= Valores.getCasaGrupoNegro()) {
                if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerocasas() == 0) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Casa("1", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerocasas() == 1) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Casa("2", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerocasas() == 2) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Casa("3", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Casa("4", (Solar) jugador.getAvatar().getCasillaAvatar()));
                }
                jugador.setFortuna(jugador.getFortuna() - Valores.getCasaGrupoNegro());
                Juego.consola.imprimirln("Se ha edificado una casa en " + jugador.getAvatar().getCasillaAvatar().getNombre() + ". La fortuna de " + jugador.getNombre() + " se reduce en " + Valores.getCasaGrupoNegro());
                jugador.setDineroGastado(jugador.getDineroGastado() + Valores.getCasaGrupoNegro());
                if (((Solar) casilla).getNumerohoteles() == 0) {
                    if (((Solar) casilla).getNumerocasas() == 1)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoNegro() + 0.05 * Valores.getAlquilerGrupoNegro());
                    else if (((Solar) casilla).getNumerocasas() == 2)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoNegro() + 0.15 * Valores.getAlquilerGrupoNegro());
                    else if (((Solar) casilla).getNumerocasas() == 3)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoNegro() + 0.35 * Valores.getAlquilerGrupoNegro());
                    else if (((Solar) casilla).getNumerocasas() == 4)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoNegro() + 0.5 * Valores.getAlquilerGrupoNegro());
                } else if (((Solar) casilla).getNumerohoteles() == 1) {
                    if (((Solar) casilla).getNumerocasas() == 1)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoNegro() + 0.75 * Valores.getAlquilerGrupoNegro());
                    else if (((Solar) casilla).getNumerocasas() == 2)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoNegro() + 0.85 * Valores.getAlquilerGrupoNegro());
                    else if (((Solar) casilla).getNumerocasas() == 3)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoNegro() + 1.05 * Valores.getAlquilerGrupoNegro());
                    else if (((Solar) casilla).getNumerocasas() == 4)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoNegro() + 1.2 * Valores.getAlquilerGrupoNegro());
                } else {
                    if (((Solar) casilla).getNumerocasas() == 1)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoNegro() + 1.45 * Valores.getAlquilerGrupoNegro());
                    else
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoNegro() + 1.55 * Valores.getAlquilerGrupoNegro());
                }
            } else
                throw new SinDinero("No tienes suficiente dinero para edificar otra casa en esta casilla.");
        } else if (casilla.getGrupo().getNombre().equals("Cian")) {
            if (jugador.getFortuna() >= Valores.getCasaGrupoCian()) {
                if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerocasas() == 0) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Casa("1", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerocasas() == 1) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Casa("2", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerocasas() == 2) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Casa("3", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Casa("4", (Solar) jugador.getAvatar().getCasillaAvatar()));
                }
                jugador.setFortuna(jugador.getFortuna() - Valores.getCasaGrupoCian());
                Juego.consola.imprimirln("Se ha edificado una casa en " + jugador.getAvatar().getCasillaAvatar().getNombre() + ". La fortuna de " + jugador.getNombre() + " se reduce en " + Valores.getCasaGrupoCian());
                jugador.setDineroGastado(jugador.getDineroGastado() + Valores.getCasaGrupoCian());
                if (((Solar) casilla).getNumerohoteles() == 0) {
                    if (((Solar) casilla).getNumerocasas() == 1)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoCian() + 0.05 * Valores.getAlquilerGrupoCian());
                    else if (((Solar) casilla).getNumerocasas() == 2)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoCian() + 0.15 * Valores.getAlquilerGrupoCian());
                    else if (((Solar) casilla).getNumerocasas() == 3)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoCian() + 0.35 * Valores.getAlquilerGrupoCian());
                    else if (((Solar) casilla).getNumerocasas() == 4)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoCian() + 0.5 * Valores.getAlquilerGrupoCian());
                } else if (((Solar) casilla).getNumerohoteles() == 1) {
                    if (((Solar) casilla).getNumerocasas() == 1)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoCian() + 0.75 * Valores.getAlquilerGrupoCian());
                    else if (((Solar) casilla).getNumerocasas() == 2)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoCian() + 0.85 * Valores.getAlquilerGrupoCian());
                    else if (((Solar) casilla).getNumerocasas() == 3)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoCian() + 1.05 * Valores.getAlquilerGrupoCian());
                    else if (((Solar) casilla).getNumerocasas() == 4)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoCian() + 1.2 * Valores.getAlquilerGrupoCian());
                } else if (((Solar) casilla).getNumerohoteles() == 2) {
                    if (((Solar) casilla).getNumerocasas() == 1)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoCian() + 1.45 * Valores.getAlquilerGrupoCian());
                    else if (((Solar) casilla).getNumerocasas() == 2)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoCian() + 1.55 * Valores.getAlquilerGrupoCian());
                    else if (((Solar) casilla).getNumerocasas() == 3)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoCian() + 1.75 * Valores.getAlquilerGrupoCian());
                    else if (((Solar) casilla).getNumerocasas() == 4)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoCian() + 1.9 * Valores.getAlquilerGrupoCian());
                } else {
                    if (((Solar) casilla).getNumerocasas() == 1)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoCian() + 2.15 * Valores.getAlquilerGrupoCian());
                    else if (((Solar) casilla).getNumerocasas() == 2)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoCian() + 2.25 * Valores.getAlquilerGrupoCian());
                    else if (((Solar) casilla).getNumerocasas() == 3)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoCian() + 2.45 * Valores.getAlquilerGrupoCian());
                }
            } else
                throw new SinDinero("No tienes suficiente dinero para edificar otra casa en esta casilla.");
        } else if (casilla.getGrupo().getNombre().equals("Violeta")) {
            if (jugador.getFortuna() >= Valores.getCasaGrupoVioleta()) {
                if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerocasas() == 0) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Casa("1", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerocasas() == 1) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Casa("2", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerocasas() == 2) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Casa("3", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Casa("4", (Solar) jugador.getAvatar().getCasillaAvatar()));
                }
                jugador.setFortuna(jugador.getFortuna() - Valores.getCasaGrupoVioleta());
                Juego.consola.imprimirln("Se ha edificado una casa en " + jugador.getAvatar().getCasillaAvatar().getNombre() + ". La fortuna de " + jugador.getNombre() + " se reduce en " + Valores.getCasaGrupoVioleta());
                jugador.setDineroGastado(jugador.getDineroGastado() + Valores.getCasaGrupoVioleta());
                if (((Solar) casilla).getNumerohoteles() == 0) {
                    if (((Solar) casilla).getNumerocasas() == 1)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVioleta() + 0.05 * Valores.getAlquilerGrupoVioleta());
                    else if (((Solar) casilla).getNumerocasas() == 2)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVioleta() + 0.15 * Valores.getAlquilerGrupoVioleta());
                    else if (((Solar) casilla).getNumerocasas() == 3)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVioleta() + 0.35 * Valores.getAlquilerGrupoVioleta());
                    else if (((Solar) casilla).getNumerocasas() == 4)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVioleta() + 0.5 * Valores.getAlquilerGrupoVioleta());
                } else if (((Solar) casilla).getNumerohoteles() == 1) {
                    if (((Solar) casilla).getNumerocasas() == 1)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVioleta() + 0.75 * Valores.getAlquilerGrupoVioleta());
                    else if (((Solar) casilla).getNumerocasas() == 2)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVioleta() + 0.85 * Valores.getAlquilerGrupoVioleta());
                    else if (((Solar) casilla).getNumerocasas() == 3)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVioleta() + 1.05 * Valores.getAlquilerGrupoVioleta());
                    else if (((Solar) casilla).getNumerocasas() == 4)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVioleta() + 1.2 * Valores.getAlquilerGrupoVioleta());
                } else if (((Solar) casilla).getNumerohoteles() == 2) {
                    if (((Solar) casilla).getNumerocasas() == 1)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVioleta() + 1.45 * Valores.getAlquilerGrupoVioleta());
                    else if (((Solar) casilla).getNumerocasas() == 2)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVioleta() + 1.55 * Valores.getAlquilerGrupoVioleta());
                    else if (((Solar) casilla).getNumerocasas() == 3)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVioleta() + 1.75 * Valores.getAlquilerGrupoVioleta());
                    else if (((Solar) casilla).getNumerocasas() == 4)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVioleta() + 1.9 * Valores.getAlquilerGrupoVioleta());
                } else {
                    if (((Solar) casilla).getNumerocasas() == 1)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVioleta() + 2.15 * Valores.getAlquilerGrupoVioleta());
                    else if (((Solar) casilla).getNumerocasas() == 2)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVioleta() + 2.25 * Valores.getAlquilerGrupoVioleta());
                    else if (((Solar) casilla).getNumerocasas() == 3)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVioleta() + 2.45 * Valores.getAlquilerGrupoVioleta());
                }
            } else
                throw new SinDinero("No tienes suficiente dinero para edificar otra casa en esta casilla.");
        } else if (casilla.getGrupo().getNombre().equals("Amarillo")) {
            if (jugador.getFortuna() >= Valores.getCasaGrupoAmarillo()) {
                if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerocasas() == 0) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Casa("1", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerocasas() == 1) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Casa("2", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerocasas() == 2) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Casa("3", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Casa("4", (Solar) jugador.getAvatar().getCasillaAvatar()));
                }
                jugador.setFortuna(jugador.getFortuna() - Valores.getCasaGrupoAmarillo());
                Juego.consola.imprimirln("Se ha edificado una casa en " + jugador.getAvatar().getCasillaAvatar().getNombre() + ". La fortuna de " + jugador.getNombre() + " se reduce en " + Valores.getCasaGrupoAmarillo());
                jugador.setDineroGastado(jugador.getDineroGastado() + Valores.getCasaGrupoAmarillo());
                if (((Solar) casilla).getNumerohoteles() == 0) {
                    if (((Solar) casilla).getNumerocasas() == 1)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAmarillo() + 0.05 * Valores.getAlquilerGrupoAmarillo());
                    else if (((Solar) casilla).getNumerocasas() == 2)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAmarillo() + 0.15 * Valores.getAlquilerGrupoAmarillo());
                    else if (((Solar) casilla).getNumerocasas() == 3)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAmarillo() + 0.35 * Valores.getAlquilerGrupoAmarillo());
                    else if (((Solar) casilla).getNumerocasas() == 4)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAmarillo() + 0.5 * Valores.getAlquilerGrupoAmarillo());
                } else if (((Solar) casilla).getNumerohoteles() == 1) {
                    if (((Solar) casilla).getNumerocasas() == 1)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAmarillo() + 0.75 * Valores.getAlquilerGrupoAmarillo());
                    else if (((Solar) casilla).getNumerocasas() == 2)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAmarillo() + 0.85 * Valores.getAlquilerGrupoAmarillo());
                    else if (((Solar) casilla).getNumerocasas() == 3)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAmarillo() + 1.05 * Valores.getAlquilerGrupoAmarillo());
                    else if (((Solar) casilla).getNumerocasas() == 4)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAmarillo() + 1.2 * Valores.getAlquilerGrupoAmarillo());
                } else if (((Solar) casilla).getNumerohoteles() == 2) {
                    if (((Solar) casilla).getNumerocasas() == 1)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAmarillo() + 1.45 * Valores.getAlquilerGrupoAmarillo());
                    else if (((Solar) casilla).getNumerocasas() == 2)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAmarillo() + 1.55 * Valores.getAlquilerGrupoAmarillo());
                    else if (((Solar) casilla).getNumerocasas() == 3)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAmarillo() + 1.75 * Valores.getAlquilerGrupoAmarillo());
                    else if (((Solar) casilla).getNumerocasas() == 4)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAmarillo() + 1.9 * Valores.getAlquilerGrupoAmarillo());
                } else {
                    if (((Solar) casilla).getNumerocasas() == 1)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAmarillo() + 2.15 * Valores.getAlquilerGrupoAmarillo());
                    else if (((Solar) casilla).getNumerocasas() == 2)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAmarillo() + 2.25 * Valores.getAlquilerGrupoAmarillo());
                    else if (((Solar) casilla).getNumerocasas() == 3)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAmarillo() + 2.45 * Valores.getAlquilerGrupoAmarillo());
                }
            } else
                throw new SinDinero("No tienes suficiente dinero para edificar otra casa en esta casilla.");
        } else if (casilla.getGrupo().getNombre().equals("Rojo")) {
            if (jugador.getFortuna() >= Valores.getCasaGrupoRojo()) {
                if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerocasas() == 0) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Casa("1", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerocasas() == 1) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Casa("2", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerocasas() == 2) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Casa("3", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Casa("4", (Solar) jugador.getAvatar().getCasillaAvatar()));
                }
                jugador.setFortuna(jugador.getFortuna() - Valores.getCasaGrupoRojo());
                Juego.consola.imprimirln("Se ha edificado una casa en " + jugador.getAvatar().getCasillaAvatar().getNombre() + ". La fortuna de " + jugador.getNombre() + " se reduce en " + Valores.getCasaGrupoRojo());
                jugador.setDineroGastado(jugador.getDineroGastado() + Valores.getCasaGrupoRojo());
                if (((Solar) casilla).getNumerohoteles() == 0) {
                    if (((Solar) casilla).getNumerocasas() == 1)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoRojo() + 0.05 * Valores.getAlquilerGrupoRojo());
                    else if (((Solar) casilla).getNumerocasas() == 2)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoRojo() + 0.15 * Valores.getAlquilerGrupoRojo());
                    else if (((Solar) casilla).getNumerocasas() == 3)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoRojo() + 0.35 * Valores.getAlquilerGrupoRojo());
                    else if (((Solar) casilla).getNumerocasas() == 4)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoRojo() + 0.5 * Valores.getAlquilerGrupoRojo());
                } else if (((Solar) casilla).getNumerohoteles() == 1) {
                    if (((Solar) casilla).getNumerocasas() == 1)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoRojo() + 0.75 * Valores.getAlquilerGrupoRojo());
                    else if (((Solar) casilla).getNumerocasas() == 2)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoRojo() + 0.85 * Valores.getAlquilerGrupoRojo());
                    else if (((Solar) casilla).getNumerocasas() == 3)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoRojo() + 1.05 * Valores.getAlquilerGrupoRojo());
                    else if (((Solar) casilla).getNumerocasas() == 4)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoRojo() + 1.2 * Valores.getAlquilerGrupoRojo());
                } else if (((Solar) casilla).getNumerohoteles() == 2) {
                    if (((Solar) casilla).getNumerocasas() == 1)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoRojo() + 1.45 * Valores.getAlquilerGrupoRojo());
                    else if (((Solar) casilla).getNumerocasas() == 2)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoRojo() + 1.55 * Valores.getAlquilerGrupoRojo());
                    else if (((Solar) casilla).getNumerocasas() == 3)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoRojo() + 1.75 * Valores.getAlquilerGrupoRojo());
                    else if (((Solar) casilla).getNumerocasas() == 4)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoRojo() + 1.9 * Valores.getAlquilerGrupoRojo());
                } else {
                    if (((Solar) casilla).getNumerocasas() == 1)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoRojo() + 2.15 * Valores.getAlquilerGrupoRojo());
                    else if (((Solar) casilla).getNumerocasas() == 2)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoRojo() + 2.25 * Valores.getAlquilerGrupoRojo());
                    else if (((Solar) casilla).getNumerocasas() == 3)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoRojo() + 2.45 * Valores.getAlquilerGrupoRojo());
                }
            } else
                throw new SinDinero("No tienes suficiente dinero para edificar otra casa en esta casilla.");
        } else if (casilla.getGrupo().getNombre().equals("Gris")) {
            if (jugador.getFortuna() >= Valores.getCasaGrupoGris()) {
                if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerocasas() == 0) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Casa("1", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerocasas() == 1) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Casa("2", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerocasas() == 2) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Casa("3", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Casa("4", (Solar) jugador.getAvatar().getCasillaAvatar()));
                }
                jugador.setFortuna(jugador.getFortuna() - Valores.getCasaGrupoGris());
                Juego.consola.imprimirln("Se ha edificado una casa en " + jugador.getAvatar().getCasillaAvatar().getNombre() + ". La fortuna de " + jugador.getNombre() + " se reduce en " + Valores.getCasaGrupoGris());
                jugador.setDineroGastado(jugador.getDineroGastado() + Valores.getCasaGrupoGris());
                if (((Solar) casilla).getNumerohoteles() == 0) {
                    if (((Solar) casilla).getNumerocasas() == 1)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoGris() + 0.05 * Valores.getAlquilerGrupoGris());
                    else if (((Solar) casilla).getNumerocasas() == 2)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoGris() + 0.15 * Valores.getAlquilerGrupoGris());
                    else if (((Solar) casilla).getNumerocasas() == 3)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoGris() + 0.35 * Valores.getAlquilerGrupoGris());
                    else if (((Solar) casilla).getNumerocasas() == 4)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoGris() + 0.5 * Valores.getAlquilerGrupoGris());
                } else if (((Solar) casilla).getNumerohoteles() == 1) {
                    if (((Solar) casilla).getNumerocasas() == 1)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoGris() + 0.75 * Valores.getAlquilerGrupoGris());
                    else if (((Solar) casilla).getNumerocasas() == 2)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoGris() + 0.85 * Valores.getAlquilerGrupoGris());
                    else if (((Solar) casilla).getNumerocasas() == 3)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoGris() + 1.05 * Valores.getAlquilerGrupoGris());
                    else if (((Solar) casilla).getNumerocasas() == 4)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoGris() + 1.2 * Valores.getAlquilerGrupoGris());
                } else if (((Solar) casilla).getNumerohoteles() == 2) {
                    if (((Solar) casilla).getNumerocasas() == 1)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoGris() + 1.45 * Valores.getAlquilerGrupoGris());
                    else if (((Solar) casilla).getNumerocasas() == 2)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoGris() + 1.55 * Valores.getAlquilerGrupoGris());
                    else if (((Solar) casilla).getNumerocasas() == 3)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoGris() + 1.75 * Valores.getAlquilerGrupoGris());
                    else if (((Solar) casilla).getNumerocasas() == 4)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoGris() + 1.9 * Valores.getAlquilerGrupoGris());
                } else {
                    if (((Solar) casilla).getNumerocasas() == 1)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoGris() + 2.15 * Valores.getAlquilerGrupoGris());
                    else if (((Solar) casilla).getNumerocasas() == 2)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoGris() + 2.25 * Valores.getAlquilerGrupoGris());
                    else if (((Solar) casilla).getNumerocasas() == 3)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoGris() + 2.45 * Valores.getAlquilerGrupoGris());
                }
            } else
                throw new SinDinero("No tienes suficiente dinero para edificar otra casa en esta casilla.");
        } else if (casilla.getGrupo().getNombre().equals("Verde")) {
            if (jugador.getFortuna() >= Valores.getCasaGrupoVerde()) {
                if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerocasas() == 0) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Casa("1", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerocasas() == 1) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Casa("2", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerocasas() == 2) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Casa("3", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Casa("4", (Solar) jugador.getAvatar().getCasillaAvatar()));
                }
                jugador.setFortuna(jugador.getFortuna() - Valores.getCasaGrupoVerde());
                Juego.consola.imprimirln("Se ha edificado una casa en " + jugador.getAvatar().getCasillaAvatar().getNombre() + ". La fortuna de " + jugador.getNombre() + " se reduce en " + Valores.getCasaGrupoVerde());
                jugador.setDineroGastado(jugador.getDineroGastado() + Valores.getCasaGrupoVerde());
                if (((Solar) casilla).getNumerohoteles() == 0) {
                    if (((Solar) casilla).getNumerocasas() == 1)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVerde() + 0.05 * Valores.getAlquilerGrupoVerde());
                    else if (((Solar) casilla).getNumerocasas() == 2)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVerde() + 0.15 * Valores.getAlquilerGrupoVerde());
                    else if (((Solar) casilla).getNumerocasas() == 3)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVerde() + 0.35 * Valores.getAlquilerGrupoVerde());
                    else if (((Solar) casilla).getNumerocasas() == 4)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVerde() + 0.5 * Valores.getAlquilerGrupoVerde());
                } else if (((Solar) casilla).getNumerohoteles() == 1) {
                    if (((Solar) casilla).getNumerocasas() == 1)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVerde() + 0.75 * Valores.getAlquilerGrupoVerde());
                    else if (((Solar) casilla).getNumerocasas() == 2)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVerde() + 0.85 * Valores.getAlquilerGrupoVerde());
                    else if (((Solar) casilla).getNumerocasas() == 3)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVerde() + 1.05 * Valores.getAlquilerGrupoVerde());
                    else if (((Solar) casilla).getNumerocasas() == 4)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVerde() + 1.2 * Valores.getAlquilerGrupoVerde());
                } else if (((Solar) casilla).getNumerohoteles() == 2) {
                    if (((Solar) casilla).getNumerocasas() == 1)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVerde() + 1.45 * Valores.getAlquilerGrupoVerde());
                    else if (((Solar) casilla).getNumerocasas() == 2)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVerde() + 1.55 * Valores.getAlquilerGrupoVerde());
                    else if (((Solar) casilla).getNumerocasas() == 3)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVerde() + 1.75 * Valores.getAlquilerGrupoVerde());
                    else if (((Solar) casilla).getNumerocasas() == 4)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVerde() + 1.9 * Valores.getAlquilerGrupoVerde());
                } else {
                    if (((Solar) casilla).getNumerocasas() == 1)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVerde() + 2.15 * Valores.getAlquilerGrupoVerde());
                    else if (((Solar) casilla).getNumerocasas() == 2)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVerde() + 2.25 * Valores.getAlquilerGrupoVerde());
                    else if (((Solar) casilla).getNumerocasas() == 3)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVerde() + 2.45 * Valores.getAlquilerGrupoVerde());
                }
            } else
                throw new SinDinero("No tienes suficiente dinero para edificar otra casa en esta casilla.");
        } else {
            if (jugador.getFortuna() >= Valores.getCasaGrupoAzul()) {
                if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerocasas() == 0) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Casa("1", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerocasas() == 1) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Casa("2", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerocasas() == 2) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Casa("3", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Casa("4", (Solar) jugador.getAvatar().getCasillaAvatar()));
                }
                jugador.setFortuna(jugador.getFortuna() - Valores.getCasaGrupoAzul());
                Juego.consola.imprimirln("Se ha edificado una casa en " + jugador.getAvatar().getCasillaAvatar().getNombre() + ". La fortuna de " + jugador.getNombre() + " se reduce en " + Valores.getCasaGrupoAzul());
                jugador.setDineroGastado(jugador.getDineroGastado() + Valores.getCasaGrupoAzul());
                if (((Solar) casilla).getNumerohoteles() == 0) {
                    if (((Solar) casilla).getNumerocasas() == 1)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAzul() + 0.05 * Valores.getAlquilerGrupoAzul());
                    else if (((Solar) casilla).getNumerocasas() == 2)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAzul() + 0.15 * Valores.getAlquilerGrupoAzul());
                    else if (((Solar) casilla).getNumerocasas() == 3)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAzul() + 0.35 * Valores.getAlquilerGrupoAzul());
                    else if (((Solar) casilla).getNumerocasas() == 4)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAzul() + 0.5 * Valores.getAlquilerGrupoAzul());
                } else if (((Solar) casilla).getNumerohoteles() == 1) {
                    if (((Solar) casilla).getNumerocasas() == 1)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAzul() + 0.75 * Valores.getAlquilerGrupoAzul());
                    else if (((Solar) casilla).getNumerocasas() == 2)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAzul() + 0.85 * Valores.getAlquilerGrupoAzul());
                    else if (((Solar) casilla).getNumerocasas() == 3)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAzul() + 1.05 * Valores.getAlquilerGrupoAzul());
                    else if (((Solar) casilla).getNumerocasas() == 4)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAzul() + 1.2 * Valores.getAlquilerGrupoAzul());
                } else {
                    if (((Solar) casilla).getNumerocasas() == 1)
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAzul() + 1.45 * Valores.getAlquilerGrupoAzul());
                    else
                        ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAzul() + 1.55 * Valores.getAlquilerGrupoAzul());
                }
            } else
                throw new SinDinero("No tienes suficiente dinero para edificar otra casa en esta casilla.");
        }
    }

    private void compraHotel(Jugador jugador, Casilla casilla) throws SinDinero {
        if (casilla.getGrupo().getNombre().equals("Negro")) {
            if (jugador.getFortuna() >= Valores.getHotelGrupoNegro()) {
                if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerohoteles() == 0) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Hotel("1", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Hotel("2", (Solar) jugador.getAvatar().getCasillaAvatar()));
                }
                jugador.setFortuna(jugador.getFortuna() - Valores.getHotelGrupoNegro());
                Juego.consola.imprimirln("Se ha edificado un hotel en " + jugador.getAvatar().getCasillaAvatar().getNombre() + ". La fortuna de " + jugador.getNombre() + " se reduce en " + Valores.getHotelGrupoNegro());
                jugador.setDineroGastado(jugador.getDineroGastado() + Valores.getHotelGrupoNegro());
                if (((Solar) casilla).getNumerohoteles() == 1)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoNegro() + 0.7 * Valores.getAlquilerGrupoNegro());
                else
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoNegro() + 1.4 * Valores.getAlquilerGrupoNegro());
            } else
                throw new SinDinero("No tienes suficiente dinero para edificar otro hotel en esta casilla.");
        } else if (casilla.getGrupo().getNombre().equals("Cian")) {
            if (jugador.getFortuna() >= Valores.getHotelGrupoCian()) {
                if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerohoteles() == 0) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Hotel("1", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerohoteles() == 1) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Hotel("2", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Hotel("3", (Solar) jugador.getAvatar().getCasillaAvatar()));
                }
                jugador.setFortuna(jugador.getFortuna() - Valores.getHotelGrupoCian());
                Juego.consola.imprimirln("Se ha edificado un hotel en " + jugador.getAvatar().getCasillaAvatar().getNombre() + ". La fortuna de " + jugador.getNombre() + " se reduce en " + Valores.getHotelGrupoCian());
                jugador.setDineroGastado(jugador.getDineroGastado() + Valores.getHotelGrupoCian());
                if (((Solar) casilla).getNumerohoteles() == 1)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoCian() + 0.7 * Valores.getAlquilerGrupoCian());
                else if (((Solar) casilla).getNumerohoteles() == 2)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoCian() + 1.4 * Valores.getAlquilerGrupoCian());
                else
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoCian() + 2.1 * Valores.getAlquilerGrupoCian());
            } else
                throw new SinDinero("No tienes suficiente dinero para edificar otro hotel en esta casilla.");
        } else if (casilla.getGrupo().getNombre().equals("Violeta")) {
            if (jugador.getFortuna() >= Valores.getHotelGrupoVioleta()) {
                if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerohoteles() == 0) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Hotel("1", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerohoteles() == 1) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Hotel("2", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Hotel("3", (Solar) jugador.getAvatar().getCasillaAvatar()));
                }
                jugador.setFortuna(jugador.getFortuna() - Valores.getHotelGrupoVioleta());
                Juego.consola.imprimirln("Se ha edificado un hotel en " + jugador.getAvatar().getCasillaAvatar().getNombre() + ". La fortuna de " + jugador.getNombre() + " se reduce en " + Valores.getHotelGrupoVioleta());
                jugador.setDineroGastado(jugador.getDineroGastado() + Valores.getHotelGrupoVioleta());
                if (((Solar) casilla).getNumerohoteles() == 1)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVioleta() + 0.7 * Valores.getAlquilerGrupoVioleta());
                else if (((Solar) casilla).getNumerohoteles() == 2)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVioleta() + 1.4 * Valores.getAlquilerGrupoVioleta());
                else
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVioleta() + 2.1 * Valores.getAlquilerGrupoVioleta());
            } else
                throw new SinDinero("No tienes suficiente dinero para edificar otro hotel en esta casilla.");
        } else if (casilla.getGrupo().getNombre().equals("Amarillo")) {
            if (jugador.getFortuna() >= Valores.getHotelGrupoAmarillo()) {
                if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerohoteles() == 0) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Hotel("1", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerohoteles() == 1) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Hotel("2", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Hotel("3", (Solar) jugador.getAvatar().getCasillaAvatar()));
                }
                jugador.setFortuna(jugador.getFortuna() - Valores.getHotelGrupoAmarillo());
                Juego.consola.imprimirln("Se ha edificado un hotel en " + jugador.getAvatar().getCasillaAvatar().getNombre() + ". La fortuna de " + jugador.getNombre() + " se reduce en " + Valores.getHotelGrupoAmarillo());
                jugador.setDineroGastado(jugador.getDineroGastado() + Valores.getHotelGrupoAmarillo());
                if (((Solar) casilla).getNumerohoteles() == 1)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAmarillo() + 0.7 * Valores.getAlquilerGrupoAmarillo());
                else if (((Solar) casilla).getNumerohoteles() == 2)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAmarillo() + 1.4 * Valores.getAlquilerGrupoAmarillo());
                else
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAmarillo() + 2.1 * Valores.getAlquilerGrupoAmarillo());
            } else
                throw new SinDinero("No tienes suficiente dinero para edificar otro hotel en esta casilla.");
        } else if (casilla.getGrupo().getNombre().equals("Rojo")) {
            if (jugador.getFortuna() >= Valores.getHotelGrupoRojo()) {
                if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerohoteles() == 0) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Hotel("1", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerohoteles() == 1) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Hotel("2", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Hotel("3", (Solar) jugador.getAvatar().getCasillaAvatar()));
                }
                jugador.setFortuna(jugador.getFortuna() - Valores.getHotelGrupoRojo());
                Juego.consola.imprimirln("Se ha edificado un hotel en " + jugador.getAvatar().getCasillaAvatar().getNombre() + ". La fortuna de " + jugador.getNombre() + " se reduce en " + Valores.getHotelGrupoRojo());
                jugador.setDineroGastado(jugador.getDineroGastado() + Valores.getHotelGrupoRojo());
                if (((Solar) casilla).getNumerohoteles() == 1)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoRojo() + 0.7 * Valores.getAlquilerGrupoRojo());
                else if (((Solar) casilla).getNumerohoteles() == 2)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoRojo() + 1.4 * Valores.getAlquilerGrupoRojo());
                else
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoRojo() + 2.1 * Valores.getAlquilerGrupoRojo());
            } else
                throw new SinDinero("No tienes suficiente dinero para edificar otro hotel en esta casilla.");
        } else if (casilla.getGrupo().getNombre().equals("Gris")) {
            if (jugador.getFortuna() >= Valores.getHotelGrupoGris()) {
                if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerohoteles() == 0) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Hotel("1", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerohoteles() == 1) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Hotel("2", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Hotel("3", (Solar) jugador.getAvatar().getCasillaAvatar()));
                }
                jugador.setFortuna(jugador.getFortuna() - Valores.getHotelGrupoGris());
                Juego.consola.imprimirln("Se ha edificado un hotel en " + jugador.getAvatar().getCasillaAvatar().getNombre() + ". La fortuna de " + jugador.getNombre() + " se reduce en " + Valores.getHotelGrupoGris());
                jugador.setDineroGastado(jugador.getDineroGastado() + Valores.getHotelGrupoGris());
                if (((Solar) casilla).getNumerohoteles() == 1)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoGris() + 0.7 * Valores.getAlquilerGrupoGris());
                else if (((Solar) casilla).getNumerohoteles() == 2)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoGris() + 1.4 * Valores.getAlquilerGrupoGris());
                else
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoGris() + 2.1 * Valores.getAlquilerGrupoGris());
            } else
                throw new SinDinero("No tienes suficiente dinero para edificar otro hotel en esta casilla.");
        } else if (casilla.getGrupo().getNombre().equals("Verde")) {
            if (jugador.getFortuna() >= Valores.getHotelGrupoVerde()) {
                if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerohoteles() == 0) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Hotel("1", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerohoteles() == 1) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Hotel("2", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Hotel("3", (Solar) jugador.getAvatar().getCasillaAvatar()));
                }
                jugador.setFortuna(jugador.getFortuna() - Valores.getHotelGrupoVerde());
                Juego.consola.imprimirln("Se ha edificado un hotel en " + jugador.getAvatar().getCasillaAvatar().getNombre() + ". La fortuna de " + jugador.getNombre() + " se reduce en " + Valores.getHotelGrupoVerde());
                jugador.setDineroGastado(jugador.getDineroGastado() + Valores.getHotelGrupoVerde());
                if (((Solar) casilla).getNumerohoteles() == 1)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVerde() + 0.7 * Valores.getAlquilerGrupoVerde());
                else if (((Solar) casilla).getNumerohoteles() == 2)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVerde() + 1.4 * Valores.getAlquilerGrupoVerde());
                else
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVerde() + 2.1 * Valores.getAlquilerGrupoVerde());
            } else
                throw new SinDinero("No tienes suficiente dinero para edificar otro hotel en esta casilla.");
        } else {
            if (jugador.getFortuna() >= Valores.getHotelGrupoAzul()) {
                if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumerohoteles() == 0) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Hotel("1", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Hotel("2", (Solar) jugador.getAvatar().getCasillaAvatar()));
                }
                jugador.setFortuna(jugador.getFortuna() - Valores.getHotelGrupoAzul());
                Juego.consola.imprimirln("Se ha edificado un hotel en " + jugador.getAvatar().getCasillaAvatar().getNombre() + ". La fortuna de " + jugador.getNombre() + " se reduce en " + Valores.getHotelGrupoAzul());
                jugador.setDineroGastado(jugador.getDineroGastado() + Valores.getHotelGrupoAzul());
                if (((Solar) casilla).getNumerohoteles() == 1)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAzul() + 0.7 * Valores.getAlquilerGrupoAzul());
                else
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAzul() + 1.4 * Valores.getAlquilerGrupoAzul());
            } else
                throw new SinDinero("No tienes suficiente dinero para edificar otro hotel en esta casilla.");
        }
    }

    private void compraPiscina(Jugador jugador, Casilla casilla) throws SinDinero {
        if (casilla.getGrupo().getNombre().equals("Negro")) {
            if (jugador.getFortuna() >= Valores.getPiscinaGrupoNegro()) {
                if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumeropiscinas() == 0) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Piscina("1", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Piscina("2", (Solar) jugador.getAvatar().getCasillaAvatar()));
                }
                jugador.setFortuna(jugador.getFortuna() - Valores.getPiscinaGrupoNegro());
                Juego.consola.imprimirln("Se ha edificado una piscina en " + jugador.getAvatar().getCasillaAvatar().getNombre() + ". La fortuna de " + jugador.getNombre() + " se reduce en " + Valores.getPiscinaGrupoNegro());
                jugador.setDineroGastado(jugador.getDineroGastado() + Valores.getPiscinaGrupoNegro());
                if (((Solar) casilla).getNumeropiscinas() == 1)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoNegro() + 1.8 * Valores.getAlquilerGrupoNegro());
                else
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoNegro() + 2.05 * Valores.getAlquilerGrupoNegro());
            } else
                throw new SinDinero("No tienes suficiente dinero para edificar otra piscina en esta casilla.");
        } else if (casilla.getGrupo().getNombre().equals("Cian")) {
            if (jugador.getFortuna() >= Valores.getPiscinaGrupoCian()) {
                if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumeropiscinas() == 0) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Piscina("1", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumeropiscinas() == 1) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Piscina("2", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Piscina("3", (Solar) jugador.getAvatar().getCasillaAvatar()));
                }
                jugador.setFortuna(jugador.getFortuna() - Valores.getPiscinaGrupoCian());
                Juego.consola.imprimirln("Se ha edificado una piscina en " + jugador.getAvatar().getCasillaAvatar().getNombre() + ". La fortuna de " + jugador.getNombre() + " se reduce en " + Valores.getPiscinaGrupoCian());
                jugador.setDineroGastado(jugador.getDineroGastado() + Valores.getPiscinaGrupoCian());
                if (((Solar) casilla).getNumeropiscinas() == 1)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoCian() + 2.7 * Valores.getAlquilerGrupoCian());
                else if (((Solar) casilla).getNumeropiscinas() == 2)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoCian() + 2.95 * Valores.getAlquilerGrupoCian());
                else
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoCian() + 3.2 * Valores.getAlquilerGrupoCian());
            } else
                throw new SinDinero("No tienes suficiente dinero para edificar otra piscina en esta casilla.");
        } else if (casilla.getGrupo().getNombre().equals("Violeta")) {
            if (jugador.getFortuna() >= Valores.getPiscinaGrupoVioleta()) {
                if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumeropiscinas() == 0) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Piscina("1", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumeropiscinas() == 1) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Piscina("2", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Piscina("3", (Solar) jugador.getAvatar().getCasillaAvatar()));
                }
                jugador.setFortuna(jugador.getFortuna() - Valores.getPiscinaGrupoVioleta());
                Juego.consola.imprimirln("Se ha edificado una piscina en " + jugador.getAvatar().getCasillaAvatar().getNombre() + ". La fortuna de " + jugador.getNombre() + " se reduce en " + Valores.getPiscinaGrupoVioleta());
                jugador.setDineroGastado(jugador.getDineroGastado() + Valores.getPiscinaGrupoVioleta());
                if (((Solar) casilla).getNumeropiscinas() == 1)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVioleta() + 2.7 * Valores.getAlquilerGrupoVioleta());
                else if (((Solar) casilla).getNumeropiscinas() == 2)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVioleta() + 2.95 * Valores.getAlquilerGrupoVioleta());
                else
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVioleta() + 3.2 * Valores.getAlquilerGrupoVioleta());
            } else
                throw new SinDinero("No tienes suficiente dinero para edificar otra piscina en esta casilla.");
        } else if (casilla.getGrupo().getNombre().equals("Amarillo")) {
            if (jugador.getFortuna() >= Valores.getPiscinaGrupoAmarillo()) {
                if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumeropiscinas() == 0) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Piscina("1", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumeropiscinas() == 1) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Piscina("2", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Piscina("3", (Solar) jugador.getAvatar().getCasillaAvatar()));
                }
                jugador.setFortuna(jugador.getFortuna() - Valores.getPiscinaGrupoAmarillo());
                Juego.consola.imprimirln("Se ha edificado una piscina en " + jugador.getAvatar().getCasillaAvatar().getNombre() + ". La fortuna de " + jugador.getNombre() + " se reduce en " + Valores.getPiscinaGrupoAmarillo());
                jugador.setDineroGastado(jugador.getDineroGastado() + Valores.getPiscinaGrupoAmarillo());
                if (((Solar) casilla).getNumeropiscinas() == 1)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAmarillo() + 2.7 * Valores.getAlquilerGrupoAmarillo());
                else if (((Solar) casilla).getNumeropiscinas() == 2)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAmarillo() + 2.95 * Valores.getAlquilerGrupoAmarillo());
                else
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAmarillo() + 3.2 * Valores.getAlquilerGrupoAmarillo());
            } else
                throw new SinDinero("No tienes suficiente dinero para edificar otra piscina en esta casilla.");
        } else if (casilla.getGrupo().getNombre().equals("Rojo")) {
            if (jugador.getFortuna() >= Valores.getPiscinaGrupoRojo()) {
                if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumeropiscinas() == 0) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Piscina("1", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumeropiscinas() == 1) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Piscina("2", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Piscina("3", (Solar) jugador.getAvatar().getCasillaAvatar()));
                }
                jugador.setFortuna(jugador.getFortuna() - Valores.getPiscinaGrupoRojo());
                Juego.consola.imprimirln("Se ha edificado una piscina en " + jugador.getAvatar().getCasillaAvatar().getNombre() + ". La fortuna de " + jugador.getNombre() + " se reduce en " + Valores.getPiscinaGrupoRojo());
                jugador.setDineroGastado(jugador.getDineroGastado() + Valores.getPiscinaGrupoRojo());
                if (((Solar) casilla).getNumeropiscinas() == 1)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoRojo() + 2.7 * Valores.getAlquilerGrupoRojo());
                else if (((Solar) casilla).getNumeropiscinas() == 2)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoRojo() + 2.95 * Valores.getAlquilerGrupoRojo());
                else
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoRojo() + 3.2 * Valores.getAlquilerGrupoRojo());
            } else
                throw new SinDinero("No tienes suficiente dinero para edificar otra piscina en esta casilla.");
        } else if (casilla.getGrupo().getNombre().equals("Gris")) {
            if (jugador.getFortuna() >= Valores.getPiscinaGrupoGris()) {
                if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumeropiscinas() == 0) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Piscina("1", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumeropiscinas() == 1) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Piscina("2", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Piscina("3", (Solar) jugador.getAvatar().getCasillaAvatar()));
                }
                jugador.setFortuna(jugador.getFortuna() - Valores.getPiscinaGrupoGris());
                Juego.consola.imprimirln("Se ha edificado una piscina en " + jugador.getAvatar().getCasillaAvatar().getNombre() + ". La fortuna de " + jugador.getNombre() + " se reduce en " + Valores.getPiscinaGrupoGris());
                jugador.setDineroGastado(jugador.getDineroGastado() + Valores.getPiscinaGrupoGris());
                if (((Solar) casilla).getNumeropiscinas() == 1)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoGris() + 2.7 * Valores.getAlquilerGrupoGris());
                else if (((Solar) casilla).getNumeropiscinas() == 2)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoGris() + 2.95 * Valores.getAlquilerGrupoGris());
                else
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoGris() + 3.2 * Valores.getAlquilerGrupoGris());
            } else
                throw new SinDinero("No tienes suficiente dinero para edificar otra piscina en esta casilla.");
        } else if (casilla.getGrupo().getNombre().equals("Verde")) {
            if (jugador.getFortuna() >= Valores.getPiscinaGrupoVerde()) {
                if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumeropiscinas() == 0) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Piscina("1", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumeropiscinas() == 1) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Piscina("2", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Piscina("3", (Solar) jugador.getAvatar().getCasillaAvatar()));
                }
                jugador.setFortuna(jugador.getFortuna() - Valores.getPiscinaGrupoVerde());
                Juego.consola.imprimirln("Se ha edificado una piscina en " + jugador.getAvatar().getCasillaAvatar().getNombre() + ". La fortuna de " + jugador.getNombre() + " se reduce en " + Valores.getPiscinaGrupoVerde());
                jugador.setDineroGastado(jugador.getDineroGastado() + Valores.getPiscinaGrupoVerde());
                if (((Solar) casilla).getNumeropiscinas() == 1)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVerde() + 2.7 * Valores.getAlquilerGrupoVerde());
                else if (((Solar) casilla).getNumeropiscinas() == 2)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVerde() + 2.95 * Valores.getAlquilerGrupoVerde());
                else
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVerde() + 3.2 * Valores.getAlquilerGrupoVerde());
            } else
                throw new SinDinero("No tienes suficiente dinero para edificar otra piscina en esta casilla.");
        } else {
            if (jugador.getFortuna() >= Valores.getPiscinaGrupoAzul()) {
                if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumeropiscinas() == 0) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Piscina("1", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new Piscina("2", (Solar) jugador.getAvatar().getCasillaAvatar()));
                }
                jugador.setFortuna(jugador.getFortuna() - Valores.getPiscinaGrupoAzul());
                Juego.consola.imprimirln("Se ha edificado una piscina en " + jugador.getAvatar().getCasillaAvatar().getNombre() + ". La fortuna de " + jugador.getNombre() + " se reduce en " + Valores.getPiscinaGrupoAzul());
                jugador.setDineroGastado(jugador.getDineroGastado() + Valores.getPiscinaGrupoAzul());
                if (((Solar) casilla).getNumeropiscinas() == 1)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAzul() + 1.8 * Valores.getAlquilerGrupoAzul());
                else
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAzul() + 2.05 * Valores.getAlquilerGrupoAzul());
            } else
                throw new SinDinero("No tienes suficiente dinero para edificar otra piscina en esta casilla.");
        }
    }

    private void compraPista(Jugador jugador, Casilla casilla) throws SinDinero {
        if (casilla.getGrupo().getNombre().equals("Negro")) {
            if (jugador.getFortuna() >= Valores.getPistaDeDeporteGrupoNegro()) {
                if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumeropistas() == 0) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new PistaDeporte("1", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new PistaDeporte("2", (Solar) jugador.getAvatar().getCasillaAvatar()));
                }
                jugador.setFortuna(jugador.getFortuna() - Valores.getPistaDeDeporteGrupoNegro());
                Juego.consola.imprimirln("Se ha edificado una pista de deporte en " + jugador.getAvatar().getCasillaAvatar().getNombre() + ". La fortuna de " + jugador.getNombre() + " se reduce en " + Valores.getPistaDeDeporteGrupoNegro());
                jugador.setDineroGastado(jugador.getDineroGastado() + Valores.getPistaDeDeporteGrupoNegro());
                if (((Solar) casilla).getNumeropistas() == 1)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoNegro() + 2.3 * Valores.getAlquilerGrupoNegro());
                else
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoNegro() + 2.55 * Valores.getAlquilerGrupoNegro());
            } else
                throw new SinDinero("No tienes suficiente dinero para edificar otra pita de deporte en esta casilla.");
        } else if (casilla.getGrupo().getNombre().equals("Cian")) {
            if (jugador.getFortuna() >= Valores.getPistaDeDeporteGrupoCian()) {
                if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumeropistas() == 0) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new PistaDeporte("1", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumeropistas() == 1) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new PistaDeporte("2", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new PistaDeporte("3", (Solar) jugador.getAvatar().getCasillaAvatar()));
                }
                jugador.setFortuna(jugador.getFortuna() - Valores.getPistaDeDeporteGrupoCian());
                Juego.consola.imprimirln("Se ha edificado una pista de deporte en " + jugador.getAvatar().getCasillaAvatar().getNombre() + ". La fortuna de " + jugador.getNombre() + " se reduce en " + Valores.getPistaDeDeporteGrupoCian());
                jugador.setDineroGastado(jugador.getDineroGastado() + Valores.getPistaDeDeporteGrupoCian());
                if (((Solar) casilla).getNumeropistas() == 1)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoCian() + 3.55 * Valores.getAlquilerGrupoCian());
                else if (((Solar) casilla).getNumeropistas() == 2)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoCian() + 3.8 * Valores.getAlquilerGrupoCian());
                else
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoCian() + 4.05 * Valores.getAlquilerGrupoCian());
            } else
                throw new SinDinero("No tienes suficiente dinero para edificar otra pita de deporte en esta casilla.");
        } else if (casilla.getGrupo().getNombre().equals("Violeta")) {
            if (jugador.getFortuna() >= Valores.getPistaDeDeporteGrupoVioleta()) {
                if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumeropistas() == 0) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new PistaDeporte("1", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumeropistas() == 1) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new PistaDeporte("2", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new PistaDeporte("3", (Solar) jugador.getAvatar().getCasillaAvatar()));
                }
                jugador.setFortuna(jugador.getFortuna() - Valores.getPistaDeDeporteGrupoVioleta());
                Juego.consola.imprimirln("Se ha edificado una pista de deporte en " + jugador.getAvatar().getCasillaAvatar().getNombre() + ". La fortuna de " + jugador.getNombre() + " se reduce en " + Valores.getPistaDeDeporteGrupoVioleta());
                jugador.setDineroGastado(jugador.getDineroGastado() + Valores.getPistaDeDeporteGrupoVioleta());
                if (((Solar) casilla).getNumeropistas() == 1)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVioleta() + 3.55 * Valores.getAlquilerGrupoVioleta());
                else if (((Solar) casilla).getNumeropistas() == 2)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVioleta() + 3.8 * Valores.getAlquilerGrupoVioleta());
                else
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVioleta() + 4.05 * Valores.getAlquilerGrupoVioleta());
            } else
                throw new SinDinero("No tienes suficiente dinero para edificar otra pita de deporte en esta casilla.");
        } else if (casilla.getGrupo().getNombre().equals("Amarillo")) {
            if (jugador.getFortuna() >= Valores.getPistaDeDeporteGrupoAmarillo()) {
                if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumeropistas() == 0) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new PistaDeporte("1", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumeropistas() == 1) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new PistaDeporte("2", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new PistaDeporte("3", (Solar) jugador.getAvatar().getCasillaAvatar()));
                }
                jugador.setFortuna(jugador.getFortuna() - Valores.getPistaDeDeporteGrupoAmarillo());
                Juego.consola.imprimirln("Se ha edificado una pista de deporte en " + jugador.getAvatar().getCasillaAvatar().getNombre() + ". La fortuna de " + jugador.getNombre() + " se reduce en " + Valores.getPistaDeDeporteGrupoAmarillo());
                jugador.setDineroGastado(jugador.getDineroGastado() + Valores.getPistaDeDeporteGrupoAmarillo());
                if (((Solar) casilla).getNumeropistas() == 1)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAmarillo() + 3.55 * Valores.getAlquilerGrupoAmarillo());
                else if (((Solar) casilla).getNumeropistas() == 2)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAmarillo() + 3.8 * Valores.getAlquilerGrupoAmarillo());
                else
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAmarillo() + 4.05 * Valores.getAlquilerGrupoAmarillo());
            } else
                throw new SinDinero("No tienes suficiente dinero para edificar otra pita de deporte en esta casilla.");
        } else if (casilla.getGrupo().getNombre().equals("Rojo")) {
            if (jugador.getFortuna() >= Valores.getPistaDeDeporteGrupoRojo()) {
                if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumeropistas() == 0) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new PistaDeporte("1", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumeropistas() == 1) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new PistaDeporte("2", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new PistaDeporte("3", (Solar) jugador.getAvatar().getCasillaAvatar()));
                }
                jugador.setFortuna(jugador.getFortuna() - Valores.getPistaDeDeporteGrupoRojo());
                Juego.consola.imprimirln("Se ha edificado una pista de deporte en " + jugador.getAvatar().getCasillaAvatar().getNombre() + ". La fortuna de " + jugador.getNombre() + " se reduce en " + Valores.getPistaDeDeporteGrupoRojo());
                jugador.setDineroGastado(jugador.getDineroGastado() + Valores.getPistaDeDeporteGrupoRojo());
                if (((Solar) casilla).getNumeropistas() == 1)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoRojo() + 3.55 * Valores.getAlquilerGrupoRojo());
                else if (((Solar) casilla).getNumeropistas() == 2)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoRojo() + 3.8 * Valores.getAlquilerGrupoRojo());
                else
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoRojo() + 4.05 * Valores.getAlquilerGrupoRojo());
            } else
                throw new SinDinero("No tienes suficiente dinero para edificar otra pita de deporte en esta casilla.");
        } else if (casilla.getGrupo().getNombre().equals("Gris")) {
            if (jugador.getFortuna() >= Valores.getPistaDeDeporteGrupoGris()) {
                if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumeropistas() == 0) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new PistaDeporte("1", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumeropistas() == 1) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new PistaDeporte("2", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new PistaDeporte("3", (Solar) jugador.getAvatar().getCasillaAvatar()));
                }
                jugador.setFortuna(jugador.getFortuna() - Valores.getPistaDeDeporteGrupoGris());
                Juego.consola.imprimirln("Se ha edificado una pista de deporte en " + jugador.getAvatar().getCasillaAvatar().getNombre() + ". La fortuna de " + jugador.getNombre() + " se reduce en " + Valores.getPistaDeDeporteGrupoGris());
                jugador.setDineroGastado(jugador.getDineroGastado() + Valores.getPistaDeDeporteGrupoGris());
                if (((Solar) casilla).getNumeropistas() == 1)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoGris() + 3.55 * Valores.getAlquilerGrupoGris());
                else if (((Solar) casilla).getNumeropistas() == 2)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoGris() + 3.8 * Valores.getAlquilerGrupoGris());
                else
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoGris() + 4.05 * Valores.getAlquilerGrupoGris());
            } else
                throw new SinDinero("No tienes suficiente dinero para edificar otra pita de deporte en esta casilla.");
        } else if (casilla.getGrupo().getNombre().equals("Verde")) {
            if (jugador.getFortuna() >= Valores.getPistaDeDeporteGrupoVerde()) {
                if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumeropistas() == 0) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new PistaDeporte("1", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumeropistas() == 1) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new PistaDeporte("2", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new PistaDeporte("3", (Solar) jugador.getAvatar().getCasillaAvatar()));
                }
                jugador.setFortuna(jugador.getFortuna() - Valores.getPistaDeDeporteGrupoVerde());
                Juego.consola.imprimirln("Se ha edificado una pista de deporte en " + jugador.getAvatar().getCasillaAvatar().getNombre() + ". La fortuna de " + jugador.getNombre() + " se reduce en " + Valores.getPistaDeDeporteGrupoVerde());
                jugador.setDineroGastado(jugador.getDineroGastado() + Valores.getPistaDeDeporteGrupoVerde());
                if (((Solar) casilla).getNumeropistas() == 1)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVerde() + 3.55 * Valores.getAlquilerGrupoVerde());
                else if (((Solar) casilla).getNumeropistas() == 2)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVerde() + 3.8 * Valores.getAlquilerGrupoVerde());
                else
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoVerde() + 4.05 * Valores.getAlquilerGrupoVerde());
            } else
                throw new SinDinero("No tienes suficiente dinero para edificar otra pita de deporte en esta casilla.");
        } else {
            if (jugador.getFortuna() >= Valores.getPistaDeDeporteGrupoAzul()) {
                if (((Solar) jugador.getAvatar().getCasillaAvatar()).getNumeropistas() == 0) {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new PistaDeporte("1", (Solar) jugador.getAvatar().getCasillaAvatar()));
                } else {
                    ((Solar) jugador.getAvatar().getCasillaAvatar()).anadirEdificio(new PistaDeporte("2", (Solar) jugador.getAvatar().getCasillaAvatar()));
                }
                jugador.setFortuna(jugador.getFortuna() - Valores.getPistaDeDeporteGrupoAzul());
                Juego.consola.imprimirln("Se ha edificado una pista de deporte en " + jugador.getAvatar().getCasillaAvatar().getNombre() + ". La fortuna de " + jugador.getNombre() + " se reduce en " + Valores.getPistaDeDeporteGrupoAzul());
                jugador.setDineroGastado(jugador.getDineroGastado() + Valores.getPistaDeDeporteGrupoAzul());
                if (((Solar) casilla).getNumeropistas() == 1)
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAzul() + 2.3 * Valores.getAlquilerGrupoAzul());
                else
                    ((Solar) casilla).setAlquiler(Valores.getAlquilerGrupoAzul() + 2.55 * Valores.getAlquilerGrupoAzul());
            } else
                throw new SinDinero("No tienes suficiente dinero para edificar otra pita de deporte en esta casilla.");
        }
    }

    public void Alquiler(Avatar avatartirada) throws SinDinero {
        if (avatartirada.getCasillaAvatar() instanceof Solar) {
            if (((Solar) avatartirada.getCasillaAvatar()).getPropietario() != null && !((Solar) avatartirada.getCasillaAvatar()).perteneceAJugador(avatartirada.getJugador()) && !isHipotecada()) {
                if (((Solar) avatartirada.getCasillaAvatar()).getContadorNoCobrarTrato() == 0 && !avatartirada.isNoCobrarTrato()) {
                    if (avatartirada.getJugador().getFortuna() >= ((Solar) avatartirada.getCasillaAvatar()).getAlquiler()) {
                        Juego.consola.imprimirln("La casilla " + avatartirada.getCasillaAvatar().getNombre() + " ya tiene propietario. Deberá pagar " + ((Solar) avatartirada.getCasillaAvatar()).getAlquiler());
                        avatartirada.getJugador().setPagoDeAlquileres(avatartirada.getJugador().getPagoDeAlquileres() + ((Solar) avatartirada.getCasillaAvatar()).getAlquiler());
                        ((Solar) avatartirada.getCasillaAvatar()).getPropietario().setCobroDeAlquileres(((Solar) avatartirada.getCasillaAvatar()).getPropietario().getCobroDeAlquileres() + ((Solar) avatartirada.getCasillaAvatar()).getAlquiler());
                        double fortunaactual = avatartirada.getJugador().getFortuna() - ((Solar) avatartirada.getCasillaAvatar()).getAlquiler();
                        avatartirada.getJugador().setFortuna(fortunaactual);
                        ((Solar) avatartirada.getCasillaAvatar()).setCobradoEnAlquiler(((Solar) avatartirada.getCasillaAvatar()).getCobradoEnAlquiler() + ((Solar) avatartirada.getCasillaAvatar()).getAlquiler());
                        if (avatartirada instanceof Esfinge && ((Esfinge) avatartirada).isMovimientoespecialesfinge())
                            ((Esfinge) avatartirada).setCobraAlquiler(true);
                        else if (avatartirada instanceof Sombrero && ((Sombrero) avatartirada).isMovimientoespecialsombrero())
                            ((Sombrero) avatartirada).setCobraAlquiler(true);
                    } else
                        throw new SinDinero("No tienes suficiente dinero para pagar a " + ((Solar) avatartirada.getCasillaAvatar()).getPropietario() + ". Huye, quiere matarte.");
                } else {
                    ((Solar) avatartirada.getCasillaAvatar()).setContadorNoCobrarTrato(((Solar) avatartirada.getCasillaAvatar()).getContadorNoCobrarTrato() - 1);
                    if (((Solar) avatartirada.getCasillaAvatar()).getContadorNoCobrarTrato() == 0)
                        avatartirada.setNoCobrarTrato(false);
                    Juego.consola.imprimirln("No se cobra el alquiler de " + avatartirada.getCasillaAvatar().getNombre() + " por el trato realizado anteriormente. Quedan " + ((Solar) avatartirada.getCasillaAvatar()).getContadorNoCobrarTrato() + " turno(s) para finalizar el trato.");
                }
            }
        }
    }

    public void Edificar(Jugador jugadorturno, String partes) throws Excepcion {
        boolean hipotecado = false;
        for (int i = 0; i < jugadorturno.getHipotecas().size(); i++) {
            if (jugadorturno.getHipotecas().get(i).getNombre().equals(jugadorturno.getAvatar().getCasillaAvatar().getNombre())) {
                hipotecado = true;
                break;
            }
        }
        if (!hipotecado) {
            if (jugadorturno.getAvatar().getCasillaAvatar().getGrupo().getNombre().equals("Negro") || jugadorturno.getAvatar().getCasillaAvatar().getGrupo().getNombre().equals("Azul")) {
                if (partes.equals("casa")) {
                    if (((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getNumerocasas() < 4) {
                        if (((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getNumerohoteles() < 2) {
                            compraCasa(jugadorturno, jugadorturno.getAvatar().getCasillaAvatar());
                        } else if (((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getNumerohoteles() == 2 && ((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getNumerocasas() < 2) {
                            compraCasa(jugadorturno, jugadorturno.getAvatar().getCasillaAvatar());
                        } else
                            throw new ImposibilidadEdificar("No puedes construír más casas en esta propiedad.");
                    } else
                        throw new ImposibilidadEdificar("No puedes construír más casas en esta propiedad.");
                } else if (partes.equals("hotel")) {
                    if (((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getNumerohoteles() == 0) {
                        if (((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getNumerocasas() == 4) {
                            compraHotel(jugadorturno, jugadorturno.getAvatar().getCasillaAvatar());
                            for (int i = 0; i < ((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getEdificios().size(); i++) {
                                i = 0;
                                if (((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getEdificios().get(i) instanceof Casa) {
                                    ((Solar) jugadorturno.getAvatar().getCasillaAvatar()).eliminarEdificio(((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getEdificios().get(i));
                                }
                            }
                            ((Solar) jugadorturno.getAvatar().getCasillaAvatar()).setNumerocasas(0);
                        } else
                            throw new ImposibilidadEdificar("Todavía no puedes construír un hotel en esta casilla.");
                    } else if (((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getNumerohoteles() == 1) {
                        if (((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getNumerocasas() == 4) {
                            compraHotel(jugadorturno, jugadorturno.getAvatar().getCasillaAvatar());
                            for (int i = 0; i < ((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getEdificios().size(); i++) {
                                if (((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getEdificios().get(i) instanceof Casa) {
                                    ((Solar) jugadorturno.getAvatar().getCasillaAvatar()).eliminarEdificio(((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getEdificios().get(i));
                                    i = 0;
                                }
                            }
                            ((Solar) jugadorturno.getAvatar().getCasillaAvatar()).setNumerocasas(0);
                        } else
                            throw new ImposibilidadEdificar("Todavía no puedes construír un hotel en esta casilla.");
                    } else
                        throw new ImposibilidadEdificar("No puedes construír más hoteles en esta propiedad.");
                } else if (partes.equals("piscina")) {
                    if (((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getNumeropiscinas() < 2) {
                        if ((((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getNumerohoteles() > 0 && ((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getNumerocasas() > 1) || ((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getNumerohoteles() > 1) {
                            compraPiscina(jugadorturno, jugadorturno.getAvatar().getCasillaAvatar());
                        } else
                            throw new ImposibilidadEdificar("Todavía no puedes construír una piscina en esta casilla.");
                    } else
                        throw new ImposibilidadEdificar("No puedes construír más piscinas en esta propiedad.");
                } else if (partes.equals("pista")) {
                    if (((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getNumeropistas() < 2) {
                        if (((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getNumerohoteles() > 1) {
                            compraPista(jugadorturno, jugadorturno.getAvatar().getCasillaAvatar());
                        } else
                            throw new ImposibilidadEdificar("Todavía no puedes construír una pista de deporte en esta casilla.");
                    } else
                        throw new ImposibilidadEdificar("No puedes construír más pistas de deporte en esta propiedad.");
                } else throw new ComandoInexistente("Comando incorrecto.");
            } else {
                if (partes.equals("casa")) {
                    if (((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getNumerocasas() < 4) {
                        if (((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getNumerohoteles() < 3) {
                            compraCasa(jugadorturno, jugadorturno.getAvatar().getCasillaAvatar());
                        } else if (((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getNumerohoteles() == 3 && ((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getNumerocasas() < 3) {
                            compraCasa(jugadorturno, jugadorturno.getAvatar().getCasillaAvatar());
                        } else
                            throw new ImposibilidadEdificar("No puedes construír más casas en esta propiedad.");
                    } else
                        throw new ImposibilidadEdificar("No puedes construír más casas en esta propiedad.");
                } else if (partes.equals("hotel")) {
                    if (((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getNumerohoteles() == 0) {
                        if (((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getNumerocasas() == 4) {
                            compraHotel(jugadorturno, jugadorturno.getAvatar().getCasillaAvatar());
                            for (int i = 0; i < ((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getEdificios().size(); i++) {
                                i = 0;
                                if (((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getEdificios().get(i) instanceof Casa) {
                                    ((Solar) jugadorturno.getAvatar().getCasillaAvatar()).eliminarEdificio(((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getEdificios().get(i));
                                }
                            }
                            ((Solar) jugadorturno.getAvatar().getCasillaAvatar()).setNumerocasas(0);
                        } else
                            throw new ImposibilidadEdificar("Todavía no puedes construír un hotel en esta casilla.");
                    } else if (((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getNumerohoteles() == 1) {
                        if (((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getNumerocasas() == 4) {
                            compraHotel(jugadorturno, jugadorturno.getAvatar().getCasillaAvatar());
                            for (int i = 0; i < ((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getEdificios().size(); i++) {
                                if (((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getEdificios().get(i) instanceof Casa) {
                                    ((Solar) jugadorturno.getAvatar().getCasillaAvatar()).eliminarEdificio(((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getEdificios().get(i));
                                    i = 0;
                                }
                            }
                            ((Solar) jugadorturno.getAvatar().getCasillaAvatar()).setNumerocasas(0);
                        } else
                            throw new ImposibilidadEdificar("Todavía no puedes construír un hotel en esta casilla.");
                    } else if (((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getNumerohoteles() == 2) {
                        if (((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getNumerocasas() == 4) {
                            compraHotel(jugadorturno, jugadorturno.getAvatar().getCasillaAvatar());
                            for (int i = 0; i < ((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getEdificios().size(); i++) {
                                if (((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getEdificios().get(i) instanceof Casa) {
                                    ((Solar) jugadorturno.getAvatar().getCasillaAvatar()).eliminarEdificio(((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getEdificios().get(i));
                                    i = 0;
                                }
                            }
                            ((Solar) jugadorturno.getAvatar().getCasillaAvatar()).setNumerocasas(0);
                        } else
                            throw new ImposibilidadEdificar("Todavía no puedes construír un hotel en esta casilla.");
                    } else
                        throw new ImposibilidadEdificar("No puedes construír más hoteles en esta propiedad.");
                } else if (partes.equals("piscina")) {
                    if (((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getNumeropiscinas() < 3) {
                        if ((((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getNumerohoteles() > 0 && ((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getNumerocasas() > 1) || ((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getNumerohoteles() > 1) {
                            compraPiscina(jugadorturno, jugadorturno.getAvatar().getCasillaAvatar());
                        } else
                            throw new ImposibilidadEdificar("Todavía no puedes construír una piscina en esta casilla.");
                    } else
                        throw new ImposibilidadEdificar("No puedes construír más piscinas en esta propiedad.");
                } else if (partes.equals("pista")) {
                    if (((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getNumeropistas() < 3) {
                        if (((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getNumerohoteles() > 1) {
                            compraPista(jugadorturno, jugadorturno.getAvatar().getCasillaAvatar());
                        } else
                            throw new ImposibilidadEdificar("Todavía no puedes construír una pista de deporte en esta casilla.");
                    } else
                        throw new ImposibilidadEdificar("No puedes construír más pistas de deporte en esta propiedad.");
                } else throw new ComandoInexistente("Comando incorrecto.");
            }
        } else
            throw new ImposibilidadEdificar("No puedes edificar en " + jugadorturno.getAvatar().getCasillaAvatar().getNombre() + ", está hipotecada.");
    }
}