package Excepciones.Generales;

import Excepciones.Excepcion;

public final class ComandoInexistente extends Excepcion {
    public ComandoInexistente() {
    }

    public ComandoInexistente(String mensaje) {
        super(mensaje);
    }
}
