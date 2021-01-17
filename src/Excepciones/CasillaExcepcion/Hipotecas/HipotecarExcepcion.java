package Excepciones.CasillaExcepcion.Hipotecas;

import Excepciones.CasillaExcepcion.CasillaExcepcion;

public abstract class HipotecarExcepcion extends CasillaExcepcion {
    public HipotecarExcepcion() {
    }

    public HipotecarExcepcion(String mensaje) {
        super(mensaje);
    }
}
