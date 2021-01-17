package Edificios;

import Casillas.Casilla;
import Casillas.Solar;

public abstract class Edificio {
    private Solar casilla;

    public Edificio() {
        this.casilla = null;
    }

    public Edificio(Solar casilla) {
        this.casilla = casilla;
    }

    public Casilla getCasilla() {
        return casilla;
    }

    public void setCasilla(Solar casilla) {
        this.casilla = casilla;
    }
}