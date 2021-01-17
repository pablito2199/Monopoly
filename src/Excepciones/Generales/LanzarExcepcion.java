package Excepciones.Generales;

import Excepciones.Excepcion;

public final class LanzarExcepcion extends Excepcion {
    public LanzarExcepcion() {
    }

    public LanzarExcepcion(String mensaje) {
        super(mensaje);
    }
}
