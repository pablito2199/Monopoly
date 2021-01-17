package Excepciones.Generales;

import Excepciones.Excepcion;

public final class JuegoNoComenzado extends Excepcion {
    public JuegoNoComenzado() {
    }

    public JuegoNoComenzado(String mensaje) {
        super(mensaje);
    }
}
