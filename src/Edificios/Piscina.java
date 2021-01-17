package Edificios;

import Casillas.Solar;

public final class Piscina extends Edificio {
    private String id;

    public Piscina(String id) {
        this.id = id;
    }

    public Piscina(String id, Solar casilla) {
        super(casilla);
        this.id = "piscina-" + id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
