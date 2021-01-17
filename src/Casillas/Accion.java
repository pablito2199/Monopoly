package Casillas;

import Cartas.CajaComunidad;
import Cartas.Suerte;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Accion extends Casilla {
    private ArrayList<Suerte> suerte;
    private ArrayList<CajaComunidad> comunidad;

    public Accion(ArrayList<Suerte> suerte) {
        this.suerte = suerte;
    }

    public Accion(String nombre, double coste, String color, int posicion) {
        super(nombre, coste, color, posicion);
        this.suerte = new ArrayList<>();
        this.comunidad = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            comunidad.add(new CajaComunidad(i));
            suerte.add(new Suerte(i));
        }
    }

    public ArrayList<Suerte> getSuerte() {
        return suerte;
    }

    public void setSuerte(ArrayList<Suerte> suerte) {
        this.suerte = suerte;
    }

    public ArrayList<CajaComunidad> getComunidad() {
        return comunidad;
    }

    public void setComunidad(ArrayList<CajaComunidad> comunidad) {
        this.comunidad = comunidad;
    }

    public final void BarajarSuerte() {
        Collections.shuffle(suerte);
    }

    public final void BarajarComunidad() {
        Collections.shuffle(comunidad);
    }
}
