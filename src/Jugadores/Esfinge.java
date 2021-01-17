package Jugadores;

import Casillas.Casilla;
import Principal.Juego;

public final class Esfinge extends Avatar {
    private boolean movimientoespecialesfinge;
    private double dineroUltimoAlquiler;
    private boolean cobraAlquiler;
    private double dineroUltimoImpuesto;
    private boolean cobraImpuesto;
    private double dineroUltimoBote;
    private boolean cobraBote;
    private double dineroUltimaCarta;
    private boolean cobraCarta;
    private boolean vueltaUltimoTurno;
    private boolean compraUltimoTurno;
    private boolean hipotecaUltimoTurno;
    private boolean deshipotecaUltimoTurno;
    private Casilla casillaUltimoTurno;

    public Esfinge() {
        this.movimientoespecialesfinge = false;
        this.dineroUltimoAlquiler = 0;
        this.cobraAlquiler = false;
        this.dineroUltimoImpuesto = 0;
        this.cobraImpuesto = false;
        this.dineroUltimoBote = 0;
        this.cobraBote = false;
        this.dineroUltimaCarta = 0;
        this.cobraCarta = false;
        this.vueltaUltimoTurno = false;
        this.compraUltimoTurno = false;
        this.hipotecaUltimoTurno = false;
        this.deshipotecaUltimoTurno = false;
        this.casillaUltimoTurno = null;
    }

    public Esfinge(Jugador jugador) {
        super(jugador);
        this.movimientoespecialesfinge = false;
        this.dineroUltimoAlquiler = 0;
        this.cobraAlquiler = false;
        this.dineroUltimoImpuesto = 0;
        this.cobraImpuesto = false;
        this.dineroUltimoBote = 0;
        this.cobraBote = false;
        this.dineroUltimaCarta = 0;
        this.cobraCarta = false;
        this.vueltaUltimoTurno = false;
        this.compraUltimoTurno = false;
        this.hipotecaUltimoTurno = false;
        this.deshipotecaUltimoTurno = false;
        this.casillaUltimoTurno = null;
    }

    public boolean isMovimientoespecialesfinge() {
        return movimientoespecialesfinge;
    }

    public void setMovimientoespecialesfinge(boolean movimientoespecialesfinge) {
        this.movimientoespecialesfinge = movimientoespecialesfinge;
    }

    public double getDineroUltimoAlquiler() {
        return dineroUltimoAlquiler;
    }

    public void setDineroUltimoAlquiler(double dineroUltimoAlquiler) {
        this.dineroUltimoAlquiler = dineroUltimoAlquiler;
    }

    public boolean isCobraAlquiler() {
        return cobraAlquiler;
    }

    public void setCobraAlquiler(boolean cobraAlquiler) {
        this.cobraAlquiler = cobraAlquiler;
    }

    public double getDineroUltimoImpuesto() {
        return dineroUltimoImpuesto;
    }

    public void setDineroUltimoImpuesto(double dineroUltimoImpuesto) {
        this.dineroUltimoImpuesto = dineroUltimoImpuesto;
    }

    public boolean isCobraImpuesto() {
        return cobraImpuesto;
    }

    public void setCobraImpuesto(boolean cobraImpuesto) {
        this.cobraImpuesto = cobraImpuesto;
    }

    public double getDineroUltimoBote() {
        return dineroUltimoBote;
    }

    public void setDineroUltimoBote(double dineroUltimoBote) {
        this.dineroUltimoBote = dineroUltimoBote;
    }

    public boolean isCobraBote() {
        return cobraBote;
    }

    public void setCobraBote(boolean cobraBote) {
        this.cobraBote = cobraBote;
    }

    public double getDineroUltimaCarta() {
        return dineroUltimaCarta;
    }

    public void setDineroUltimaCarta(double dineroUltimaCarta) {
        this.dineroUltimaCarta = dineroUltimaCarta;
    }

    public boolean isCobraCarta() {
        return cobraCarta;
    }

    public void setCobraCarta(boolean cobraCarta) {
        this.cobraCarta = cobraCarta;
    }

    public boolean isVueltaUltimoTurno() {
        return vueltaUltimoTurno;
    }

    public void setVueltaUltimoTurno(boolean vueltaUltimoTurno) {
        this.vueltaUltimoTurno = vueltaUltimoTurno;
    }

    public boolean isCompraUltimoTurno() {
        return compraUltimoTurno;
    }

    public void setCompraUltimoTurno(boolean compraUltimoTurno) {
        this.compraUltimoTurno = compraUltimoTurno;
    }

    public boolean isHipotecaUltimoTurno() {
        return hipotecaUltimoTurno;
    }

    public void setHipotecaUltimoTurno(boolean hipotecaUltimoTurno) {
        this.hipotecaUltimoTurno = hipotecaUltimoTurno;
    }

    public boolean isDeshipotecaUltimoTurno() {
        return deshipotecaUltimoTurno;
    }

    public void setDeshipotecaUltimoTurno(boolean deshipotecaUltimoTurno) {
        this.deshipotecaUltimoTurno = deshipotecaUltimoTurno;
    }

    public Casilla getCasillaUltimoTurno() {
        return casillaUltimoTurno;
    }

    public void setCasillaUltimoTurno(Casilla casillaUltimoTurno) {
        this.casillaUltimoTurno = casillaUltimoTurno;
    }

    public void impAvatar(String id) {
        Juego.consola.imprimirln("{");
        Juego.consola.imprimirln("id: " + this.getId() + ",");
        Juego.consola.imprimirln("tipo: Esfinge,");
        Juego.consola.imprimirln("casilla: " + this.getCasillaAvatar().getNombre() + ",");
        Juego.consola.imprimirln("jugador: " + this.getJugador().getNombre());
        Juego.consola.imprimirln("}");
    }

    @Override
    public String toString() {
        return "{\n" +
                "id: " + this.getId() + ",\n" +
                "figura: Esfinge,\n" +
                "casilla: " + this.getCasillaAvatar().getNombre();
    }

}
