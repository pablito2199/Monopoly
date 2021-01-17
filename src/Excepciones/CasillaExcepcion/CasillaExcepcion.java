package Excepciones.CasillaExcepcion;

import Excepciones.Excepcion;

public abstract class CasillaExcepcion extends Excepcion {
    public CasillaExcepcion() {
    }

    public CasillaExcepcion(String mensaje) {
        super(mensaje);
    }
}
