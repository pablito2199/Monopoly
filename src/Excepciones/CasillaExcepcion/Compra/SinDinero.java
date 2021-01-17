package Excepciones.CasillaExcepcion.Compra;

public final class SinDinero extends CompraExcepcion {
    public SinDinero() {
    }

    public SinDinero(String mensaje) {
        super(mensaje);
    }
}
