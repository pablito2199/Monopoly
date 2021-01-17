package Excepciones.CasillaExcepcion.Compra;

public final class NoComprarCoche extends CompraExcepcion {
    public NoComprarCoche() {
    }

    public NoComprarCoche(String mensaje) {
        super(mensaje);
    }
}