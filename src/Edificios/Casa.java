package Edificios;

import Casillas.Solar;

public final class Casa extends Edificio {
    private String id;

    public Casa() {
        this.id = null;
    }

    public Casa(String id, Solar casilla) {
        super(casilla);
        this.id = "casa-" + id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
