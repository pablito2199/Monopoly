package Excepciones;

public abstract class Excepcion extends Exception {
    public Excepcion() {
    }

    public Excepcion(String mensaje) {
        super(mensaje);
    }
}
