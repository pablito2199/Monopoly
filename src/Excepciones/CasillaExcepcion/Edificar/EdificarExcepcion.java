package Excepciones.CasillaExcepcion.Edificar;

import Excepciones.CasillaExcepcion.CasillaExcepcion;

public abstract class EdificarExcepcion extends CasillaExcepcion {
    public EdificarExcepcion() {
    }

    public EdificarExcepcion(String mensaje) {
        super(mensaje);
    }
}
