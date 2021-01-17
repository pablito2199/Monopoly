package Jugadores;

import Casillas.Casilla;
import Casillas.Propiedad;
import Casillas.Solar;
import Edificios.*;
import Principal.Juego;
import Principal.Valores;

import java.util.ArrayList;

public class Jugador {
    private String nombre;
    private Avatar avatar;
    private double fortuna;
    private double dineroGastado;
    private double pagoTasasEImpuestos;
    private double pagoDeAlquileres;
    private double cobroDeAlquileres;
    private double pasarPorCasillaDeSalida;
    private double premiosInversionesOBote;
    private int vecesEnLaCarcel;
    private ArrayList<Propiedad> propiedades;
    private ArrayList<Casilla> edificios;
    private ArrayList<Propiedad> hipotecas;
    private int turno;
    private int turnocarcel;
    private int cuentavueltas;
    private int cuentadados;
    private int turnocoche;

    public Jugador() {
        this.nombre = "Banca";
        this.avatar = null;
        this.fortuna = 999999999;
        this.dineroGastado = 0;
        this.pagoTasasEImpuestos = 0;
        this.pagoDeAlquileres = 0;
        this.cobroDeAlquileres = 0;
        this.pasarPorCasillaDeSalida = 0;
        this.premiosInversionesOBote = 0;
        this.vecesEnLaCarcel = 0;
        this.turno = 0;
        this.turnocoche = 0;
        this.turnocarcel = 0;
        this.cuentadados = 0;
        this.hipotecas = new ArrayList<>();
        this.propiedades = new ArrayList<>();
        this.edificios = new ArrayList<>();
    }

    public Jugador(String nombre, String tipo) {
        this.nombre = nombre;
        Avatar ava;
        if (tipo.equals("Coche") || tipo.equals("coche")) {
            ava = new Coche(this);
        } else if (tipo.equals("Esfinge") || tipo.equals("esfinge")) {
            ava = new Esfinge(this);
        } else if (tipo.equals("Pelota") || tipo.equals("pelota")) {
            ava = new Pelota(this);
        } else if (tipo.equals("Sombrero") || tipo.equals("sombrero")) {
            ava = new Sombrero(this);
        } else {
            Juego.consola.imprimirln("Esa figura no existe, utilizará el Sombrero.\n");
            ava = new Sombrero(this);
        }
        this.avatar = ava;
        this.fortuna = Valores.FORTUNA_INICIAL;
        this.dineroGastado = 0;
        this.pagoTasasEImpuestos = 0;
        this.pagoDeAlquileres = 0;
        this.cobroDeAlquileres = 0;
        this.pasarPorCasillaDeSalida = 0;
        this.premiosInversionesOBote = 0;
        this.vecesEnLaCarcel = 0;
        this.hipotecas = new ArrayList<>();
        this.propiedades = new ArrayList<>();
        this.edificios = new ArrayList<>();
        this.turno = 0;
        this.turnocoche = 0;
        this.turnocarcel = 0;
        this.cuentavueltas = 0;
        this.cuentadados = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public double getFortuna() {
        return fortuna;
    }

    public void setFortuna(double fortuna) {
        this.fortuna = fortuna;
    }

    public double getDineroGastado() {
        return dineroGastado;
    }

    public void setDineroGastado(double dineroGastado) {
        this.dineroGastado = dineroGastado;
    }

    public double getPagoTasasEImpuestos() {
        return pagoTasasEImpuestos;
    }

    public void setPagoTasasEImpuestos(double pagoTasasEImpuestos) {
        this.pagoTasasEImpuestos = pagoTasasEImpuestos;
    }

    public double getPagoDeAlquileres() {
        return pagoDeAlquileres;
    }

    public void setPagoDeAlquileres(double pagoDeAlquileres) {
        this.pagoDeAlquileres = pagoDeAlquileres;
    }

    public double getCobroDeAlquileres() {
        return cobroDeAlquileres;
    }

    public void setCobroDeAlquileres(double cobroDeAlquileres) {
        this.cobroDeAlquileres = cobroDeAlquileres;
    }

    public double getPasarPorCasillaDeSalida() {
        return pasarPorCasillaDeSalida;
    }

    public void setPasarPorCasillaDeSalida(double pasarPorCasillaDeSalida) {
        this.pasarPorCasillaDeSalida = pasarPorCasillaDeSalida;
    }

    public double getPremiosInversionesOBote() {
        return premiosInversionesOBote;
    }

    public void setPremiosInversionesOBote(double premiosInversionesOBote) {
        this.premiosInversionesOBote = premiosInversionesOBote;
    }

    public int getVecesEnLaCarcel() {
        return vecesEnLaCarcel;
    }

    public void setVecesEnLaCarcel(int vecesEnLaCarcel) {
        this.vecesEnLaCarcel = vecesEnLaCarcel;
    }

    public ArrayList<Casilla> getEdificios() {
        return edificios;
    }

    public void setEdificios(ArrayList<Casilla> edificios) {
        this.edificios = edificios;
    }

    public ArrayList<Propiedad> getHipotecas() {
        return hipotecas;
    }

    public void setHipotecas(ArrayList<Propiedad> hipotecas) {
        this.hipotecas = hipotecas;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public int getTurnocarcel() {
        return turnocarcel;
    }

    public void setTurnocarcel(int turnocarcel) {
        this.turnocarcel = turnocarcel;
    }

    public int getCuentavueltas() {
        return cuentavueltas;
    }

    public void setCuentavueltas(int cuentavueltas) {
        this.cuentavueltas = cuentavueltas;
    }

    public int getCuentadados() {
        return cuentadados;
    }

    public void setCuentadados(int cuentadados) {
        this.cuentadados = cuentadados;
    }

    public int getTurnocoche() {
        return turnocoche;
    }

    public void setTurnocoche(int turnocoche) {
        this.turnocoche = turnocoche;
    }

    public ArrayList<Propiedad> getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(ArrayList<Propiedad> propiedades) {
        if (propiedades == null) {
            Juego.consola.imprimirln("Propiedades no inicializadas");
            return;
        }
        for (Casilla casilla : propiedades) {
            if (casilla == null) {
                Juego.consola.imprimirln("Casilla no inicializada.");
                return;
            }
        }
        this.propiedades = propiedades;
    }

    public void anadirPropiedad(Propiedad casilla) {
        if (casilla != null)
            this.propiedades.add(casilla);
    }

    public void eliminarPropiedad(Propiedad casilla) {
        if (casilla != null)
            this.propiedades.remove(casilla);
    }

    public void impJugador() {
        Juego.consola.imprimirln("nombre: " + this.getNombre() + ",");
        Juego.consola.imprimirln("avatar: " + this.getAvatar().getId() + ",");
        Juego.consola.imprimirln("fortuna: " + this.getFortuna() + ",");
        Juego.consola.imprimirln("propiedades:");
        for (int i = 0; i < propiedades.size(); i++) {
            Juego.consola.imprimirln("\t" + this.getPropiedades().get(i).getNombre() + ",");
        }
        Juego.consola.imprimirln("hipotecas:");
        for (int i = 0; i < this.getHipotecas().size(); i++) {
            Juego.consola.imprimirln("\t" + this.getHipotecas().get(i).getNombre() + ",");
        }
        Juego.consola.imprimirln("edificios:");
        for (int i = 0; i < propiedades.size(); i++) {
            if (propiedades.get(i) instanceof Solar) {
                if (((Solar) propiedades.get(i)).getEdificios().size() != 0) {
                    for (int j = 0; j < ((Solar) propiedades.get(i)).getEdificios().size(); j++) {
                        if (((Solar) propiedades.get(i)).getEdificios().get(j) instanceof Casa)
                            Juego.consola.imprimirln("\t" + ((Casa) ((Solar) this.getPropiedades().get(i)).getEdificios().get(j)).getId() + "(" + this.getPropiedades().get(i).getNombre() + "), ");
                        else if (((Solar) propiedades.get(i)).getEdificios().get(j) instanceof Hotel)
                            Juego.consola.imprimirln("\t" + ((Hotel) ((Solar) this.getPropiedades().get(i)).getEdificios().get(j)).getId() + "(" + this.getPropiedades().get(i).getNombre() + "), ");
                        else if (((Solar) propiedades.get(i)).getEdificios().get(j) instanceof Piscina)
                            Juego.consola.imprimirln("\t" + ((Piscina) ((Solar) this.getPropiedades().get(i)).getEdificios().get(j)).getId() + "(" + this.getPropiedades().get(i).getNombre() + "), ");
                        else
                            Juego.consola.imprimirln("\t" + ((PistaDeporte) ((Solar) this.getPropiedades().get(i)).getEdificios().get(j)).getId() + "(" + this.getPropiedades().get(i).getNombre() + "), ");
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        String datos;
        String properties = "";
        String buildings = "";
        String hipotecs = "";
        if (propiedades.size() != 0) {
            for (Casilla prp : propiedades) {
                properties += "\n\t" + prp.getNombre() + ",";
                if (prp instanceof Solar) {
                    for (Edificio edif : ((Solar) prp).getEdificios()) {
                        if (edif instanceof Casa)
                            buildings += "\n\t" + ((Casa) edif).getId() + "(" + prp.getNombre() + "),";
                        else if (edif instanceof Hotel)
                            buildings += "\n\t" + ((Hotel) edif).getId() + "(" + prp.getNombre() + "),";
                        else if (edif instanceof Piscina)
                            buildings += "\n\t" + ((Piscina) edif).getId() + "(" + prp.getNombre() + "),";
                        else
                            buildings += "\n\t" + ((PistaDeporte) edif).getId() + "(" + prp.getNombre() + "),";
                    }
                }
            }
        }
        if (hipotecas.size() != 0) {
            for (Casilla hipo : hipotecas) {
                hipotecs += "\n\t" + hipo.getNombre() + ",";
            }
        }
        if (this.avatar != null) {
            datos = "\n{\nnombre: " + this.nombre
                    + "\navatar: " + this.avatar.getId()
                    + "\nfortuna: " + this.fortuna
                    + "\npropiedades: " + properties
                    + "\nhipotecas: " + hipotecs
                    + "\nedificios: " + buildings
                    + "\n}";
        } else {
            datos = "\n{\nnombre: " + this.nombre +
                    "\nfortuna: " + this.fortuna
                    + "\ngastos: " + this.dineroGastado
                    + "\npropiedades:" + properties
                    + "\nhipotecas: " + hipotecs
                    + "\nedificios: " + buildings
                    + "\n}";
        }
        return datos;
    }

    @Override
    public boolean equals(Object objeto) {
        if (objeto instanceof Jugador) {
            if (((Jugador) objeto).getNombre().equals(this.nombre)) {
                return true;
            }
        }
        return false;
    }
}