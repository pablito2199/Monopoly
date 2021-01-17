package Excepciones.CasillaExcepcion.Compra;

import Excepciones.CasillaExcepcion.CasillaExcepcion;

public abstract class CompraExcepcion extends CasillaExcepcion {
    public CompraExcepcion() {
    }

    public CompraExcepcion(String mensaje) {
        super(mensaje);
    }
}
