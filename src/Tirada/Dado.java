package Tirada;

import java.util.Random;

public class Dado {
    private int movimiento;
    private boolean puedolanzar;

    public Dado() {
        this.movimiento = 0;
        this.puedolanzar = true;
    }

    public int getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(int movimiento) {
        this.movimiento = movimiento;
    }

    public boolean isPuedolanzar() {
        return puedolanzar;
    }

    public void setPuedolanzar(boolean puedolanzar) {
        this.puedolanzar = puedolanzar;
    }

    public int generarDado() {
        Random moverse = new Random();
        setMovimiento(moverse.nextInt(6) + 1);
        return getMovimiento();
    }


    public int is_DobleDado(int movimiento1, int movimiento2) {
        if (movimiento1 == movimiento2) {
            puedolanzar = true;
            return 1;
        } else {
            puedolanzar = false;
            return 0;
        }
    }
}