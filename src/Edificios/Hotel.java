package Edificios;

import Casillas.Solar;

public final class Hotel extends Edificio {
    private String id;

    public Hotel() {
        this.id = null;
    }

    public Hotel(String id, Solar casilla) {
        super(casilla);
        this.id = "hotel-" + id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
