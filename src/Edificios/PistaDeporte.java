package Edificios;

import Casillas.Solar;

public final class PistaDeporte extends Edificio {
    private String id;

    public PistaDeporte(String id) {
        this.id = id;
    }

    public PistaDeporte(String id, Solar casilla) {
        super(casilla);
        this.id = "pista-" + id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
