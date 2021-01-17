package Excepciones.CasillaExcepcion.Compra;

public final class NoEstarEnCasilla extends CompraExcepcion {
    public NoEstarEnCasilla() {
    }

    public NoEstarEnCasilla(String mensaje) {
        super(mensaje);
    }
}
