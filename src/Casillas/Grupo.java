package Casillas;

import java.util.ArrayList;

public class Grupo {
    private String nombre;
    private String color;
    private ArrayList<Casilla> casillas;

    public Grupo() {
        this.nombre = null;
        this.color = null;
        this.casillas = new ArrayList<>();
    }

    public Grupo(String nombre, String color) {
        this.nombre = nombre;
        this.color = color;
        this.casillas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ArrayList<Casilla> getCasillas() {
        return casillas;
    }

    public void setCasillas(ArrayList<Casilla> casillas) {
        this.casillas = casillas;
    }

    public void anadirGrupo(Casilla cas) {
        this.casillas.add(cas);
    }

    @Override
    public boolean equals(Object objeto) {
        if (objeto instanceof Grupo) {
            if (((Grupo) objeto).getCasillas().equals(this.casillas)) {
                return true;
            }
        }
        return false;
    }
}