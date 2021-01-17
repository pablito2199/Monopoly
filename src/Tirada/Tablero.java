package Tirada;

import Casillas.*;
import Edificios.*;
import Excepciones.CasillaExcepcion.Compra.SinDinero;
import Excepciones.CasillaExcepcion.Hipotecas.ConEdificios;
import Excepciones.CasillaExcepcion.Hipotecas.VenderExcepcion;
import Excepciones.Excepcion;
import Jugadores.*;
import Principal.Juego;
import Principal.Valores;

import java.util.ArrayList;
import java.util.Scanner;

public class Tablero {
    private ArrayList<Casilla> casillas;
    private ArrayList<Grupo> grupos;
    private Casilla salida;
    private Dado dado;
    private Jugador banca;
    private Scanner lectura;
    private Avatar avatar;
    private ArrayList<Jugador> jugadores;
    private double boteparking;
    private int mov1, mov2, suma;
    private int turnoespecialcoche;
    private boolean puedolanzarcoche;
    private int turnoespecialpelota;
    private boolean puedolanzarpelota;
    private int turnoespecialesfinge;
    private boolean puedolanzaresfinge;
    private int turnoespecialsombrero;
    private boolean puedolanzarsombrero;
    private boolean jugadorescreados;
    private boolean noEdificios;

    public Tablero() {
        dado = new Dado();
        this.casillas = new ArrayList<>();
        this.grupos = new ArrayList<>();
        lectura = new Scanner(System.in);
        boteparking = 0;
        this.mov1 = 0;
        this.mov2 = 0;
        this.suma = 0;
        this.turnoespecialcoche = 0;
        this.puedolanzarcoche = true;
        this.turnoespecialesfinge = 0;
        this.puedolanzaresfinge = true;
        this.turnoespecialsombrero = 0;
        this.puedolanzarsombrero = true;
        this.jugadores = new ArrayList<>();
        this.jugadorescreados = false;

        Casillas_Grupos();
    }

    public ArrayList<Casilla> getCasillas() {
        return casillas;
    }

    public void setCasillas(ArrayList<Casilla> casillas) {
        this.casillas = casillas;
    }

    public ArrayList<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(ArrayList<Grupo> grupos) {
        this.grupos = grupos;
    }

    public Casilla getSalida() {
        return salida;
    }

    public void setSalida(Casilla salida) {
        this.salida = salida;
    }

    public Dado getDado() {
        return dado;
    }

    public void setDado(Dado dado) {
        this.dado = dado;
    }

    public Jugador getBanca() {
        return banca;
    }

    public void setBanca(Jugador banca) {
        this.banca = banca;
    }

    public Scanner getLectura() {
        return lectura;
    }

    public void setLectura(Scanner lectura) {
        this.lectura = lectura;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public double getBoteparking() {
        return boteparking;
    }

    public void setBoteparking(double boteparking) {
        this.boteparking = boteparking;
    }

    public int getMov1() {
        return mov1;
    }

    public void setMov1(int mov1) {
        this.mov1 = mov1;
    }

    public int getMov2() {
        return mov2;
    }

    public void setMov2(int mov2) {
        this.mov2 = mov2;
    }

    public int getSuma() {
        return suma;
    }

    public void setSuma(int suma) {
        this.suma = suma;
    }

    public int getTurnoespecialcoche() {
        return turnoespecialcoche;
    }

    public void setTurnoespecialcoche(int turnoespecialcoche) {
        this.turnoespecialcoche = turnoespecialcoche;
    }

    public boolean isPuedolanzarcoche() {
        return puedolanzarcoche;
    }

    public void setPuedolanzarcoche(boolean puedolanzarcoche) {
        this.puedolanzarcoche = puedolanzarcoche;
    }

    public int getTurnoespecialpelota() {
        return turnoespecialpelota;
    }

    public void setTurnoespecialpelota(int turnoespecialpelota) {
        this.turnoespecialpelota = turnoespecialpelota;
    }

    public boolean isPuedolanzarpelota() {
        return puedolanzarpelota;
    }

    public void setPuedolanzarpelota(boolean puedolanzarpelota) {
        this.puedolanzarpelota = puedolanzarpelota;
    }

    public int getTurnoespecialesfinge() {
        return turnoespecialesfinge;
    }

    public void setTurnoespecialesfinge(int turnoespecialesfinge) {
        this.turnoespecialesfinge = turnoespecialesfinge;
    }

    public boolean isPuedolanzaresfinge() {
        return puedolanzaresfinge;
    }

    public void setPuedolanzaresfinge(boolean puedolanzaresfinge) {
        this.puedolanzaresfinge = puedolanzaresfinge;
    }

    public int getTurnoespecialsombrero() {
        return turnoespecialsombrero;
    }

    public void setTurnoespecialsombrero(int turnoespecialsombrero) {
        this.turnoespecialsombrero = turnoespecialsombrero;
    }

    public boolean isPuedolanzarsombrero() {
        return puedolanzarsombrero;
    }

    public void setPuedolanzarsombrero(boolean puedolanzarsombrero) {
        this.puedolanzarsombrero = puedolanzarsombrero;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public boolean isJugadorescreados() {
        return jugadorescreados;
    }

    public void setJugadorescreados(boolean jugadorescreados) {
        this.jugadorescreados = jugadorescreados;
    }

    public boolean isNoEdificios() {
        return noEdificios;
    }

    public void setNoEdificios(boolean noEdificios) {
        this.noEdificios = noEdificios;
    }

    public void Tirada(Jugador jugador) throws Excepcion {
        Avatar avatartirada = jugador.getAvatar();

        mov1 = dado.generarDado();
        mov2 = dado.generarDado();
        avatartirada.getJugador().setCuentadados(avatartirada.getJugador().getCuentadados() + 1);
        suma = mov1 + mov2;
        if (avatartirada instanceof Coche && ((Coche) avatartirada).isMovimientoespecialcoche())
            tiradaCoche(mov1, mov2, suma, avatartirada);
        else if (avatartirada instanceof Pelota && ((Pelota) avatartirada).isMovimientoespecialpelota())
            tiradaPelota(mov1, mov2, suma, avatartirada);
        else if (avatartirada instanceof Esfinge && ((Esfinge) avatartirada).isMovimientoespecialesfinge())
            tiradaEsfinge(mov1, mov2, suma, avatartirada);
        else if (avatartirada instanceof Sombrero && ((Sombrero) avatartirada).isMovimientoespecialsombrero())
            tiradaSombrero(mov1, mov2, suma, avatartirada);
        else tiradaNormal(mov1, mov2, suma, avatartirada);
    }

    private void tiradaNormal(int mov1, int mov2, int suma, Avatar avatartirada) throws SinDinero {
        int posicion;
        posicion = avatartirada.getPosicion();
        if (posicion == 10 && avatartirada.isJugadorcarcel()) {
            if (avatartirada.getJugador().getTurnocarcel() < 3) {
                if (avatartirada instanceof Coche && ((Coche) avatartirada).isMovimientoespecialcoche()) {
                    mov1 = dado.generarDado();
                    mov2 = dado.generarDado();
                    avatartirada.getJugador().setCuentadados(avatartirada.getJugador().getCuentadados() + 1);
                    Juego.consola.imprimirln("Dados: " + mov1 + " " + mov2);
                    suma = mov1 + mov2;
                    Juego.consola.imprimirln("Avanzas " + suma + " posiciones.");
                    if (dado.is_DobleDado(mov1, mov2) == 1) {
                        if (avatartirada.getJugador().getTurno() != 2)
                            tiradaCoche(mov1, mov2, suma, avatartirada);
                        else
                            doblesTresVeces(avatartirada);
                    } else Juego.consola.imprimirln("Continuas en la cárcel.");
                } else {
                    mov1 = dado.generarDado();
                    mov2 = dado.generarDado();
                    avatartirada.getJugador().setCuentadados(avatartirada.getJugador().getCuentadados() + 1);
                    Juego.consola.imprimirln("Dados: " + mov1 + " " + mov2);
                    suma = mov1 + mov2;
                    posicion = avatartirada.getPosicion();
                    if (dado.is_DobleDado(mov1, mov2) == 1) {
                        if (avatartirada.getJugador().getTurno() != 2) {
                            Juego.consola.imprimirln("Sales de la cárcel al sacar dobles.");
                            Juego.consola.imprimirln("Avanzas " + suma + " posiciones.");
                            this.casillas.get(avatartirada.getPosicion()).eliminarAvatar(avatartirada);
                            posicion += suma;
                            posicionEnRangoAvance(posicion, avatartirada);
                            AccionesCasillas(avatartirada);
                            if (avatartirada.getCasillaAvatar() instanceof Propiedad)
                                ((Propiedad) avatartirada.getCasillaAvatar()).Alquiler(avatartirada);
                            permiteConstruir(avatartirada);
                        } else
                            doblesTresVeces(avatartirada);
                    } else
                        Juego.consola.imprimirln("Continuas en la cárcel.");
                }
            } else {
                pagarTrasTresTurnosCarcel(avatartirada);
            }
        } else {
            Juego.consola.imprimirln("Dados: " + mov1 + " " + mov2);
            posicion = avatartirada.getPosicion();
            Juego.consola.imprimirln("Avanzas " + suma + " posiciones.");
            if (avatartirada.getJugador().getTurno() != 2 || (avatartirada.getJugador().getTurno() == 2 && getDado().is_DobleDado(mov1, mov2) == 0)) {
                this.casillas.get(avatartirada.getPosicion()).eliminarAvatar(avatartirada);
                posicion += suma;
                posicionEnRangoAvance(posicion, avatartirada);
                AccionesCasillas(avatartirada);
                if (avatartirada.getCasillaAvatar() instanceof Propiedad)
                    ((Propiedad) avatartirada.getCasillaAvatar()).Alquiler(avatartirada);
                permiteConstruir(avatartirada);
            } else
                doblesTresVeces(avatartirada);
        }
    }

    private void tiradaCoche(int mov1, int mov2, int suma, Avatar avatartirada) throws SinDinero {
        int posicion;
        if (avatartirada.getJugador().getTurnocoche() == 0) {
            if (suma > 4) {
                if (getTurnoespecialcoche() < 4) {
                    setPuedolanzarcoche(true);
                    tiradaNormal(mov1, mov2, suma, avatartirada);
                    getDado().setPuedolanzar(true);
                    comprobarGrupoEntero(avatartirada);
                    setTurnoespecialcoche(getTurnoespecialcoche() + 1);
                    if (getTurnoespecialcoche() == 4) {
                        comprobarGrupoEntero(avatartirada);
                        setPuedolanzarcoche(false);
                        ((Coche) avatartirada).setPuedocomprarcoche(true);
                        Juego.consola.imprimirln("No puedes volver a lanzar los dados.");
                        getDado().setPuedolanzar(false);
                        setTurnoespecialcoche(0);
                    }
                }
            } else {
                posicion = avatartirada.getPosicion();
                if (posicion == 10 && avatartirada.isJugadorcarcel()) {
                    if (avatartirada.getJugador().getTurnocarcel() < 3) {
                        if (avatartirada instanceof Coche && ((Coche) avatartirada).isMovimientoespecialcoche()) {
                            mov1 = dado.generarDado();
                            mov2 = dado.generarDado();
                            avatartirada.getJugador().setCuentadados(avatartirada.getJugador().getCuentadados() + 1);
                            Juego.consola.imprimirln("Dados: " + mov1 + " " + mov2);
                            suma = mov1 + mov2;
                            if (dado.is_DobleDado(mov1, mov2) == 1) {
                                if (avatartirada.getJugador().getTurno() != 2)
                                    tiradaCoche(mov1, mov2, suma, avatartirada);
                                else
                                    doblesTresVeces(avatartirada);
                            } else Juego.consola.imprimirln("Continuas en la cárcel.");
                        } else {
                            suma = mov1 + mov2;
                            if (dado.is_DobleDado(mov1, mov2) == 1) {
                                Juego.consola.imprimirln("Sales de la cárcel al sacar dobles.");
                                if (avatartirada.getJugador().getTurno() != 2) {
                                    Juego.consola.imprimirln("Dados: " + mov1 + " " + mov2);
                                    posicion = avatartirada.getPosicion();
                                    Juego.consola.imprimirln("Retrocedes " + suma + " posiciones.");
                                    if (avatartirada.getJugador().getTurno() != 2 || (avatartirada.getJugador().getTurno() == 2 && getDado().is_DobleDado(mov1, mov2) == 0)) {
                                        this.casillas.get(avatartirada.getPosicion()).eliminarAvatar(avatartirada);
                                        posicion -= suma;
                                        posicionEnRangoRetroceso(posicion, avatartirada);
                                        AccionesCasillas(avatartirada);
                                        if (avatartirada.getCasillaAvatar() instanceof Propiedad)
                                            ((Propiedad) avatartirada.getCasillaAvatar()).Alquiler(avatartirada);
                                        permiteConstruir(avatartirada);
                                    } else
                                        doblesTresVeces(avatartirada);
                                    avatartirada.getJugador().setTurnocoche(3);
                                    getDado().setPuedolanzar(false);
                                    if (avatartirada instanceof Coche)
                                        ((Coche) avatartirada).setPuedocomprarcoche(true);
                                    comprobarGrupoEntero(avatartirada);
                                }
                            } else Juego.consola.imprimirln("Continuas en la cárcel.");
                        }
                    } else {
                        pagarTrasTresTurnosCarcel(avatartirada);
                    }
                } else {
                    Juego.consola.imprimirln("Dados: " + mov1 + " " + mov2);
                    posicion = avatartirada.getPosicion();
                    Juego.consola.imprimirln("Retrocedes " + suma + " posiciones.");
                    if (avatartirada.getJugador().getTurno() != 2 || (avatartirada.getJugador().getTurno() == 2 && getDado().is_DobleDado(mov1, mov2) == 0)) {
                        this.casillas.get(avatartirada.getPosicion()).eliminarAvatar(avatartirada);
                        posicion -= suma;
                        posicionEnRangoRetroceso(posicion, avatartirada);
                        AccionesCasillas(avatartirada);
                        if (avatartirada.getCasillaAvatar() instanceof Propiedad)
                            ((Propiedad) avatartirada.getCasillaAvatar()).Alquiler(avatartirada);
                        permiteConstruir(avatartirada);
                    } else
                        doblesTresVeces(avatartirada);
                    avatartirada.getJugador().setTurnocoche(3);
                    ((Coche) avatartirada).setPuedocomprarcoche(true);
                    comprobarGrupoEntero(avatartirada);
                }
            }
        }
        if (avatartirada.getJugador().getTurnocoche() == 3) {
            Juego.consola.imprimirln("No podrás tirar los próximos dos turnos.");
            avatartirada.getJugador().setTurnocoche(avatartirada.getJugador().getTurnocoche() - 1);
            getDado().setPuedolanzar(false);
            getDado().setMovimiento(0);
        } else if (avatartirada.getJugador().getTurnocoche() == 2) {
            Juego.consola.imprimirln("No puedes tirar, te quedan " + avatartirada.getJugador().getTurnocoche() + " turnos.");
            avatartirada.getJugador().setTurnocoche(avatartirada.getJugador().getTurnocoche() - 1);
            getDado().setPuedolanzar(false);
            getDado().setMovimiento(0);
        } else if (avatartirada.getJugador().getTurnocoche() == 1) {
            Juego.consola.imprimirln("No puedes tirar, te queda " + avatartirada.getJugador().getTurnocoche() + " turno.");
            avatartirada.getJugador().setTurnocoche(avatartirada.getJugador().getTurnocoche() - 1);
            getDado().setPuedolanzar(false);
            getDado().setMovimiento(0);
        }
    }

    private void tiradaPelota(int mov1, int mov2, int suma, Avatar avatartirada) throws SinDinero {
        int posicion, posicionActual = avatartirada.getPosicion();
        if (suma > 4) {
            Juego.consola.imprimirln("Dados: " + mov1 + " " + mov2);
            if (posicionActual == 10 && avatartirada.isJugadorcarcel()) {
                if (avatartirada.getJugador().getTurnocarcel() < 3) {
                    if (dado.is_DobleDado(mov1, mov2) == 1) {
                        Juego.consola.imprimirln("Sales de la cárcel al sacar dobles.");
                        for (int j = 5; j <= suma; j += 2) {
                            if (!avatartirada.isJugadorcarcel()) {
                                avatartirada.getCasillaAvatar().eliminarAvatar(avatartirada);
                                posicion = posicionActual + j;
                                avatartirada.setPosicion(posicion);
                                if (avatartirada.getJugador().getTurno() != 2 || (avatartirada.getJugador().getTurno() == 2 && getDado().is_DobleDado(mov1, mov2) == 0)) {
                                    posicionEnRangoAvance(posicion, avatartirada);
                                    AccionesCasillas(avatartirada);
                                    propiedadesPelota(avatartirada);
                                    permiteConstruir(avatartirada);
                                } else
                                    doblesTresVeces(avatartirada);
                                Juego.consola.imprimir(this);
                            }
                        }
                    } else Juego.consola.imprimirln("Continuas en la cárcel.");
                } else {
                    pagarTrasTresTurnosCarcel(avatartirada);
                }
            } else {
                for (int j = 5; j <= suma; j += 2) {
                    if (!avatartirada.isJugadorcarcel()) {
                        avatartirada.getCasillaAvatar().eliminarAvatar(avatartirada);
                        posicion = posicionActual + j;
                        avatartirada.setPosicion(posicion);
                        if (avatartirada.getJugador().getTurno() != 2 || (avatartirada.getJugador().getTurno() == 2 && getDado().is_DobleDado(mov1, mov2) == 0)) {
                            posicionEnRangoAvance(posicion, avatartirada);
                            AccionesCasillas(avatartirada);
                            propiedadesPelota(avatartirada);
                            permiteConstruir(avatartirada);
                        } else
                            doblesTresVeces(avatartirada);
                        Juego.consola.imprimir(this);
                    }
                }
            }
        } else {
            Juego.consola.imprimirln("Dados: " + mov1 + " " + mov2);
            if (posicionActual == 10 && avatartirada.isJugadorcarcel()) {
                if (avatartirada.getJugador().getTurnocarcel() < 3) {
                    suma = mov1 + mov2;
                    if (dado.is_DobleDado(mov1, mov2) == 1) {
                        if (avatartirada.getJugador().getTurno() != 2) {
                            Juego.consola.imprimirln("Sales de la cárcel al sacar dobles.");
                            for (int j = 1; j <= suma; j += 2) {
                                if (!avatartirada.isJugadorcarcel()) {
                                    posicion = posicionActual - j;
                                    this.casillas.get(avatartirada.getPosicion()).eliminarAvatar(avatartirada);
                                    posicionEnRangoRetroceso(posicion, avatartirada);
                                    AccionesCasillas(avatartirada);
                                    propiedadesPelota(avatartirada);
                                    permiteConstruir(avatartirada);
                                    getDado().setPuedolanzar(false);
                                    comprobarGrupoEntero(avatartirada);
                                }
                                Juego.consola.imprimir(this);
                            }
                        }
                    } else
                        Juego.consola.imprimirln("Continuas en la cárcel.");
                } else {
                    pagarTrasTresTurnosCarcel(avatartirada);
                }
                Juego.consola.imprimir(this);
            } else {
                for (int j = 1; j <= suma; j += 2) {
                    if (!avatartirada.isJugadorcarcel()) {
                        avatartirada.getCasillaAvatar().eliminarAvatar(avatartirada);
                        posicion = posicionActual - j;
                        this.casillas.get(avatartirada.getPosicion()).eliminarAvatar(avatartirada);
                        posicionEnRangoRetroceso(posicion, avatartirada);
                        AccionesCasillas(avatartirada);
                        propiedadesPelota(avatartirada);
                        permiteConstruir(avatartirada);
                        getDado().setPuedolanzar(false);
                        comprobarGrupoEntero(avatartirada);
                    }
                    Juego.consola.imprimir(this);
                }
            }
        }
    }

    private void tiradaEsfinge(int mov1, int mov2, int suma, Avatar avatartirada) throws Excepcion {
        boolean vuelta = false;
        int posicionAntigua = avatartirada.getPosicion();
        int posicion = posicionAntigua;
        Juego.consola.imprimirln("Dados: " + mov1 + " " + mov2);
        if (suma > 4) {
            ((Esfinge) avatartirada).setDineroUltimoAlquiler(0);
            ((Esfinge) avatartirada).setCobraAlquiler(false);
            ((Esfinge) avatartirada).setDineroUltimoImpuesto(0);
            ((Esfinge) avatartirada).setCobraImpuesto(false);
            ((Esfinge) avatartirada).setDineroUltimoBote(0);
            ((Esfinge) avatartirada).setCobraBote(false);
            ((Esfinge) avatartirada).setDineroUltimaCarta(0);
            ((Esfinge) avatartirada).setCobraCarta(false);
            ((Esfinge) avatartirada).setVueltaUltimoTurno(false);
            ((Esfinge) avatartirada).setCompraUltimoTurno(false);
            ((Esfinge) avatartirada).setHipotecaUltimoTurno(false);
            ((Esfinge) avatartirada).setDeshipotecaUltimoTurno(false);
            ((Esfinge) avatartirada).setCasillaUltimoTurno(avatartirada.getCasillaAvatar());
            if (getTurnoespecialesfinge() < 3) {
                setPuedolanzaresfinge(true);
                if (posicionAntigua > 9 && posicionAntigua < 30)
                    vuelta = true;
                if (posicionAntigua >= 10 && posicionAntigua < 20) {
                    posicion = 21;
                } else if (posicionAntigua >= 30 && posicionAntigua < 40) {
                    posicion = 1;
                }
                for (int i = 0; i < suma; i++) {
                    if (posicionAntigua >= 0 && posicionAntigua < 11 && !vuelta) {
                        posicion = 29 - posicionAntigua;
                        if (posicion == 20) {
                            posicion = 30;
                        }
                    } else if (posicionAntigua >= 20 && posicionAntigua < 31 && !vuelta) {
                        posicion = 29 - posicionAntigua + 2;
                        if (posicion == 10) {
                            posicion = 0;
                        }
                    } else if (posicionAntigua >= 0 && posicionAntigua < 11) {
                        posicion = 29 - posicionAntigua + 2;
                        if (posicion == 30) {
                            posicion = 20;
                        }
                    } else if (posicionAntigua >= 20 && posicionAntigua < 31) {
                        posicion = 29 - posicionAntigua;
                        if (posicion == 0 || posicion == 40) {
                            avatartirada.getJugador().setPasarPorCasillaDeSalida(avatartirada.getJugador().getPasarPorCasillaDeSalida() + Valores.VUELTA_COMPLETA);
                            avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.VUELTA_COMPLETA);
                            vueltasAlTablero(avatartirada);
                            ((Esfinge) avatartirada).setVueltaUltimoTurno(true);
                            posicion = 10;
                        }
                    }
                    posicionAntigua = posicion;
                }
                Juego.consola.imprimirln("Avanzas " + suma + " posiciones.");
                if (avatartirada.getJugador().getTurno() != 2 || (avatartirada.getJugador().getTurno() == 2 && getDado().is_DobleDado(mov1, mov2) == 0)) {
                    casillas.get(avatartirada.getPosicion()).eliminarAvatar(avatartirada);
                    avatartirada.setPosicion(posicionAntigua);
                    avatartirada.setCasillaAvatar(getCasillas().get(posicionAntigua));
                    getDado().setPuedolanzar(true);
                    setTurnoespecialesfinge(getTurnoespecialesfinge() + 1);
                    comprobarGrupoEntero(avatartirada);
                    AccionesCasillas(avatartirada);
                    if (avatartirada.getCasillaAvatar() instanceof Propiedad) {
                        ((Propiedad) avatartirada.getCasillaAvatar()).Alquiler(avatartirada);
                        if (((Esfinge) avatartirada).isCobraAlquiler())
                            ((Esfinge) avatartirada).setDineroUltimoAlquiler(((Esfinge) avatartirada).getDineroUltimoAlquiler() + ((Propiedad) avatartirada.getCasillaAvatar()).getAlquiler());
                    }
                    permiteConstruir(avatartirada);
                } else
                    doblesTresVeces(avatartirada);
            }
            if (getTurnoespecialesfinge() == 3) {
                setPuedolanzaresfinge(false);
                comprobarGrupoEntero(avatartirada);
                Juego.consola.imprimirln("Esta era tu tercera tirada, no puedes volver a lanzar los dados.");
                getDado().setPuedolanzar(false);
                setTurnoespecialesfinge(0);
            }
        } else {
            setTurnoespecialesfinge(0);
            if (((Esfinge) avatartirada).getCasillaUltimoTurno() == null)
                ((Esfinge) avatartirada).setCasillaUltimoTurno(casillas.get(0));
            Juego.consola.imprimirln("¡Debes deshacer el último movimiento!");
            if (avatartirada.getCasillaAvatar() instanceof Propiedad) {
                if (((Propiedad) avatartirada.getCasillaAvatar()).getPropietario() != null && !(((Propiedad) avatartirada.getCasillaAvatar()).getPropietario().getNombre().equals(avatartirada.getJugador().getNombre())) && ((Esfinge) avatartirada).isCobraAlquiler()) {
                    Juego.consola.imprimir("El propietario de " + avatartirada.getCasillaAvatar().getNombre() + " te devuelve " + ((Propiedad) avatartirada.getCasillaAvatar()).getAlquiler() + "€.");
                    ((Propiedad) avatartirada.getCasillaAvatar()).getPropietario().setFortuna(((Propiedad) avatartirada.getCasillaAvatar()).getPropietario().getFortuna() - ((Propiedad) avatartirada.getCasillaAvatar()).getAlquiler());
                    avatartirada.getJugador().setPagoDeAlquileres(avatartirada.getJugador().getPagoDeAlquileres() - ((Propiedad) avatartirada.getCasillaAvatar()).getAlquiler());
                    ((Propiedad) avatartirada.getCasillaAvatar()).getPropietario().setCobroDeAlquileres(((Propiedad) avatartirada.getCasillaAvatar()).getPropietario().getCobroDeAlquileres() - ((Propiedad) avatartirada.getCasillaAvatar()).getAlquiler());
                }
            }
            if (((Esfinge) avatartirada).isCobraBote()) {
                Juego.consola.imprimirln("Debes devolver " + ((Esfinge) avatartirada).getDineroUltimoBote() + "€ al bote del Parking.");
                getCasillas().get(20).setCoste(((Esfinge) avatartirada).getDineroUltimoBote());
                avatartirada.getJugador().setPremiosInversionesOBote(avatartirada.getJugador().getPremiosInversionesOBote() - ((Esfinge) avatartirada).getDineroUltimoBote());
            }
            if (((Esfinge) avatartirada).isCobraImpuesto()) {
                Juego.consola.imprimirln("Se te devuelve " + ((Esfinge) avatartirada).getDineroUltimoImpuesto() + "€ de " + avatartirada.getCasillaAvatar().getNombre() + ".");
                getCasillas().get(20).setCoste(getCasillas().get(20).getCoste() - ((Esfinge) avatartirada).getDineroUltimoImpuesto());
                avatartirada.getJugador().setPagoTasasEImpuestos(avatartirada.getJugador().getPagoTasasEImpuestos() - ((Esfinge) avatartirada).getDineroUltimoImpuesto());
            }
            if (((Esfinge) avatartirada).isCobraCarta()) {
                if (((Esfinge) avatartirada).getDineroUltimaCarta() > 0) {
                    Juego.consola.imprimirln("Debes devolver " + ((Esfinge) avatartirada).getDineroUltimaCarta() + "€ de la acción de " + avatartirada.getCasillaAvatar().getNombre() + ".");
                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() - ((Esfinge) avatartirada).getDineroUltimaCarta());
                    avatartirada.getJugador().setPremiosInversionesOBote(avatartirada.getJugador().getPremiosInversionesOBote() - ((Esfinge) avatartirada).getDineroUltimaCarta());
                } else if (((Esfinge) avatartirada).getDineroUltimaCarta() < 0) {
                    Juego.consola.imprimirln("Se te devuelve " + -((Esfinge) avatartirada).getDineroUltimaCarta() + "€ de la acción de " + avatartirada.getCasillaAvatar().getNombre() + ".");
                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() - ((Esfinge) avatartirada).getDineroUltimaCarta());
                    avatartirada.getJugador().setPagoTasasEImpuestos(avatartirada.getJugador().getPagoTasasEImpuestos() + ((Esfinge) avatartirada).getDineroUltimaCarta());
                    getCasillas().get(20).setCoste(getCasillas().get(20).getCoste() + ((Esfinge) avatartirada).getDineroUltimaCarta());
                }
            }
            if (((Esfinge) avatartirada).isVueltaUltimoTurno()) {
                Juego.consola.imprimirln("Debes devolver " + Valores.getVueltaCompleta() + "€ de haber cobrado por pasar por la casilla de salida.");
                avatartirada.getJugador().setPasarPorCasillaDeSalida(avatartirada.getJugador().getPasarPorCasillaDeSalida() - Valores.getVueltaCompleta());
                avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() - Valores.getVueltaCompleta());
            }
            if (avatartirada.getCasillaAvatar() instanceof Propiedad) {
                if (((Esfinge) avatartirada).isCompraUltimoTurno()) {
                    if (avatartirada.getJugador().getPropiedades().size() > 0) {
                        for (Propiedad p : avatartirada.getJugador().getPropiedades()) {
                            if (p.getNombre().equals(avatartirada.getCasillaAvatar().getNombre())) {
                                if (p instanceof Solar) {
                                    if (((Solar) p).getEdificios().size() > 0) {
                                        for (Edificio e : ((Solar) p).getEdificios()) {
                                            if (p.getGrupo().getNombre().equals("Negro")) {
                                                if (e instanceof Casa) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getCasaGrupoNegro());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getCasaGrupoNegro());
                                                } else if (e instanceof Hotel) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getHotelGrupoNegro());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getHotelGrupoNegro());
                                                } else if (e instanceof Piscina) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getPiscinaGrupoNegro());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getPiscinaGrupoNegro());
                                                } else if (e instanceof PistaDeporte) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getPistaDeDeporteGrupoNegro());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getPistaDeDeporteGrupoNegro());
                                                }
                                            } else if (p.getGrupo().getNombre().equals("Cian")) {
                                                if (e instanceof Casa) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getCasaGrupoCian());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getCasaGrupoCian());
                                                } else if (e instanceof Hotel) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getHotelGrupoCian());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getHotelGrupoCian());
                                                } else if (e instanceof Piscina) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getPiscinaGrupoCian());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getPiscinaGrupoCian());
                                                } else if (e instanceof PistaDeporte) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getPistaDeDeporteGrupoCian());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getPistaDeDeporteGrupoCian());
                                                }
                                            } else if (p.getGrupo().getNombre().equals("Violeta")) {
                                                if (e instanceof Casa) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getCasaGrupoVioleta());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getCasaGrupoVioleta());
                                                } else if (e instanceof Hotel) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getHotelGrupoVioleta());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getHotelGrupoVioleta());
                                                } else if (e instanceof Piscina) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getPiscinaGrupoVioleta());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getPiscinaGrupoVioleta());
                                                } else if (e instanceof PistaDeporte) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getPistaDeDeporteGrupoVioleta());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getPistaDeDeporteGrupoVioleta());
                                                }
                                            } else if (p.getGrupo().getNombre().equals("Amarillo")) {
                                                if (e instanceof Casa) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getCasaGrupoAmarillo());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getCasaGrupoAmarillo());
                                                } else if (e instanceof Hotel) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getHotelGrupoAmarillo());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getHotelGrupoAmarillo());
                                                } else if (e instanceof Piscina) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getPiscinaGrupoAmarillo());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getPiscinaGrupoAmarillo());
                                                } else if (e instanceof PistaDeporte) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getPistaDeDeporteGrupoAmarillo());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getPistaDeDeporteGrupoAmarillo());
                                                }
                                            } else if (p.getGrupo().getNombre().equals("Rojo")) {
                                                if (e instanceof Casa) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getCasaGrupoRojo());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getCasaGrupoRojo());
                                                } else if (e instanceof Hotel) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getHotelGrupoRojo());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getHotelGrupoRojo());
                                                } else if (e instanceof Piscina) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getPiscinaGrupoRojo());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getPiscinaGrupoRojo());
                                                } else if (e instanceof PistaDeporte) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getPistaDeDeporteGrupoRojo());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getPistaDeDeporteGrupoRojo());
                                                }
                                            } else if (p.getGrupo().getNombre().equals("Gris")) {
                                                if (e instanceof Casa) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getCasaGrupoGris());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getCasaGrupoGris());
                                                } else if (e instanceof Hotel) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getHotelGrupoGris());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getHotelGrupoGris());
                                                } else if (e instanceof Piscina) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getPiscinaGrupoGris());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getPiscinaGrupoGris());
                                                } else if (e instanceof PistaDeporte) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getPistaDeDeporteGrupoGris());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getPistaDeDeporteGrupoGris());
                                                }
                                            } else if (p.getGrupo().getNombre().equals("Verde")) {
                                                if (e instanceof Casa) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getCasaGrupoVerde());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getCasaGrupoVerde());
                                                } else if (e instanceof Hotel) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getHotelGrupoVerde());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getHotelGrupoVerde());
                                                } else if (e instanceof Piscina) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getPiscinaGrupoVerde());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getPiscinaGrupoVerde());
                                                } else if (e instanceof PistaDeporte) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getPistaDeDeporteGrupoVerde());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getPistaDeDeporteGrupoVerde());
                                                }
                                            } else if (p.getGrupo().getNombre().equals("Azul")) {
                                                if (e instanceof Casa) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getCasaGrupoAzul());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getCasaGrupoAzul());
                                                } else if (e instanceof Hotel) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getHotelGrupoAzul());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getHotelGrupoAzul());
                                                } else if (e instanceof Piscina) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getPiscinaGrupoAzul());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getPiscinaGrupoAzul());
                                                } else if (e instanceof PistaDeporte) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getPistaDeDeporteGrupoAzul());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getPistaDeDeporteGrupoAzul());
                                                }
                                            }
                                            ((Solar) p).eliminarEdificio(e);
                                        }
                                        Juego.consola.imprimirln("Has vendido los edificios de " + p.getNombre());
                                    }
                                }
                            }
                        }
                    }
                }
                if (((Esfinge) avatartirada).isHipotecaUltimoTurno()) {
                    if (avatartirada.getJugador().getHipotecas().size() > 0) {
                        for (Propiedad h : avatartirada.getJugador().getHipotecas()) {
                            if (h.getNombre().equals(avatartirada.getCasillaAvatar().getNombre())) {
                                deshipotecar(avatartirada.getJugador(), h);
                                break;
                            }
                        }
                    }
                }
                if (((Esfinge) avatartirada).isDeshipotecaUltimoTurno() && !((Esfinge) avatartirada).isCompraUltimoTurno()) {
                    if (avatartirada.getJugador().getPropiedades().size() > 0) {
                        for (Propiedad h : avatartirada.getJugador().getPropiedades()) {
                            if (h.getNombre().equals(avatartirada.getCasillaAvatar().getNombre())) {
                                hipotecar(avatartirada.getJugador(), h);
                                break;
                            }
                        }
                    }
                }
                if (((Esfinge) avatartirada).isCompraUltimoTurno()) {
                    if (avatartirada.getJugador().getPropiedades().size() > 0) {
                        for (Propiedad p : avatartirada.getJugador().getPropiedades()) {
                            if (p.getNombre().equals(avatartirada.getCasillaAvatar().getNombre())) {
                                avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + p.getCoste());
                                Juego.consola.imprimirln("Has vendido " + p.getNombre() + " por " + p.getCoste() + "€.");
                                avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - p.getCoste());
                                avatartirada.getJugador().eliminarPropiedad(p);
                                p.setPropietario(null);
                                getBanca().anadirPropiedad(p);
                                break;
                            }
                        }
                    }
                }
            }
            avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + ((Esfinge) avatartirada).getDineroUltimoAlquiler() + ((Esfinge) avatartirada).getDineroUltimoImpuesto() - ((Esfinge) avatartirada).getDineroUltimoBote());
            if (((Esfinge) avatartirada).getCasillaUltimoTurno().getNombre().equals(avatartirada.getCasillaAvatar().getNombre()))
                Juego.consola.imprimirln("Te mantienes en " + avatartirada.getCasillaAvatar().getNombre() + ".");
            else {
                Juego.consola.imprimirln("Vuelves a  " + ((Esfinge) avatartirada).getCasillaUltimoTurno().getNombre() + ".");
                avatartirada.getCasillaAvatar().eliminarAvatar(avatartirada);
                avatartirada.setCasillaAvatar(((Esfinge) avatartirada).getCasillaUltimoTurno());
                avatartirada.setPosicion(avatartirada.getCasillaAvatar().getPosicion());
                avatartirada.getCasillaAvatar().anadirAvatar(avatartirada);
            }
            ((Esfinge) avatartirada).setDineroUltimoAlquiler(0);
            ((Esfinge) avatartirada).setCobraAlquiler(false);
            ((Esfinge) avatartirada).setDineroUltimoImpuesto(0);
            ((Esfinge) avatartirada).setCobraImpuesto(false);
            ((Esfinge) avatartirada).setDineroUltimoBote(0);
            ((Esfinge) avatartirada).setCobraBote(false);
            ((Esfinge) avatartirada).setDineroUltimaCarta(0);
            ((Esfinge) avatartirada).setCobraCarta(false);
            ((Esfinge) avatartirada).setVueltaUltimoTurno(false);
            ((Esfinge) avatartirada).setCompraUltimoTurno(false);
            ((Esfinge) avatartirada).setHipotecaUltimoTurno(false);
            ((Esfinge) avatartirada).setDeshipotecaUltimoTurno(false);
            ((Esfinge) avatartirada).setCasillaUltimoTurno(avatartirada.getCasillaAvatar());
        }
    }

    private void tiradaSombrero(int mov1, int mov2, int suma, Avatar avatartirada) throws Excepcion {
        boolean vuelta = false;
        int posicionAntigua = avatartirada.getPosicion();
        int posicion = posicionAntigua;
        Juego.consola.imprimirln("Dados: " + mov1 + " " + mov2);
        if (suma > 4) {
            ((Sombrero) avatartirada).setDineroUltimoAlquiler(0);
            ((Sombrero) avatartirada).setCobraAlquiler(false);
            ((Sombrero) avatartirada).setDineroUltimoImpuesto(0);
            ((Sombrero) avatartirada).setCobraImpuesto(false);
            ((Sombrero) avatartirada).setDineroUltimoBote(0);
            ((Sombrero) avatartirada).setCobraBote(false);
            ((Sombrero) avatartirada).setDineroUltimaCarta(0);
            ((Sombrero) avatartirada).setCobraCarta(false);
            ((Sombrero) avatartirada).setVueltaUltimoTurno(false);
            ((Sombrero) avatartirada).setCompraUltimoTurno(false);
            ((Sombrero) avatartirada).setHipotecaUltimoTurno(false);
            ((Sombrero) avatartirada).setDeshipotecaUltimoTurno(false);
            ((Sombrero) avatartirada).setCasillaUltimoTurno(avatartirada.getCasillaAvatar());
            if (getTurnoespecialsombrero() < 3) {
                setPuedolanzarsombrero(true);
                if (posicionAntigua >= 0 && posicionAntigua < 20)
                    vuelta = true;
                if (posicionAntigua >= 0 && posicionAntigua < 10) {
                    posicionAntigua = 11;
                } else if (posicionAntigua >= 20 && posicionAntigua < 30) {
                    posicionAntigua = 31;
                }
                for (int i = 0; i < suma; i++) {
                    if (posicionAntigua >= 10 && posicionAntigua < 21 && !vuelta) {
                        posicion = 49 - posicionAntigua;
                        if (posicion == 30) {
                            posicion = 0;
                        }
                    } else if (posicionAntigua >= 30 && posicionAntigua < 40 && !vuelta) {
                        posicion = 49 - posicionAntigua + 2;
                        if (posicion == 20) {
                            posicion = 10;
                        }
                    } else if (posicionAntigua >= 10 && posicionAntigua < 21) {
                        posicion = 49 - posicionAntigua + 2;
                        if (posicion == 0 || posicion == 40) {
                            avatartirada.getJugador().setPasarPorCasillaDeSalida(avatartirada.getJugador().getPasarPorCasillaDeSalida() + Valores.VUELTA_COMPLETA);
                            avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.VUELTA_COMPLETA);
                            vueltasAlTablero(avatartirada);
                            ((Sombrero) avatartirada).setVueltaUltimoTurno(true);
                            posicion = 30;
                        }
                    } else if (posicionAntigua >= 30 && posicionAntigua < 40) {
                        posicion = 49 - posicionAntigua;
                        if (posicion == 10) {
                            posicion = 20;
                        }
                    }
                    posicionAntigua = posicion;
                }
                Juego.consola.imprimirln("Avanzas " + suma + " posiciones.");
                if (avatartirada.getJugador().getTurno() != 2 || (avatartirada.getJugador().getTurno() == 2 && getDado().is_DobleDado(mov1, mov2) == 0)) {
                    casillas.get(avatartirada.getPosicion()).eliminarAvatar(avatartirada);
                    avatartirada.setPosicion(posicionAntigua);
                    avatartirada.setCasillaAvatar(getCasillas().get(posicionAntigua));
                    getDado().setPuedolanzar(true);
                    setTurnoespecialsombrero(getTurnoespecialsombrero() + 1);
                    comprobarGrupoEntero(avatartirada);
                    AccionesCasillas(avatartirada);
                    if (avatartirada.getCasillaAvatar() instanceof Propiedad) {
                        ((Propiedad) avatartirada.getCasillaAvatar()).Alquiler(avatartirada);
                        if (((Sombrero) avatartirada).isCobraAlquiler())
                            ((Sombrero) avatartirada).setDineroUltimoAlquiler(((Sombrero) avatartirada).getDineroUltimoAlquiler() + ((Propiedad) avatartirada.getCasillaAvatar()).getAlquiler());
                    }
                    permiteConstruir(avatartirada);
                } else
                    doblesTresVeces(avatartirada);
            }
            if (getTurnoespecialsombrero() == 3) {
                setPuedolanzarsombrero(false);
                comprobarGrupoEntero(avatartirada);
                Juego.consola.imprimirln("Esta era tu tercera tirada, no puedes volver a lanzar los dados.");
                getDado().setPuedolanzar(false);
                setTurnoespecialsombrero(0);
            }
        } else {
            setTurnoespecialsombrero(0);
            if (((Sombrero) avatartirada).getCasillaUltimoTurno() == null)
                ((Sombrero) avatartirada).setCasillaUltimoTurno(casillas.get(0));
            Juego.consola.imprimirln("¡Debes deshacer el último movimiento!");
            if (avatartirada.getCasillaAvatar() instanceof Propiedad) {
                if (((Propiedad) avatartirada.getCasillaAvatar()).getPropietario() != null && !(((Propiedad) avatartirada.getCasillaAvatar()).getPropietario().getNombre().equals(avatartirada.getJugador().getNombre())) && ((Sombrero) avatartirada).isCobraAlquiler()) {
                    Juego.consola.imprimir("El propietario de " + avatartirada.getCasillaAvatar().getNombre() + " te devuelve " + ((Propiedad) avatartirada.getCasillaAvatar()).getAlquiler() + "€.");
                    ((Propiedad) avatartirada.getCasillaAvatar()).getPropietario().setFortuna(((Propiedad) avatartirada.getCasillaAvatar()).getPropietario().getFortuna() - ((Propiedad) avatartirada.getCasillaAvatar()).getAlquiler());
                    avatartirada.getJugador().setPagoDeAlquileres(avatartirada.getJugador().getPagoDeAlquileres() - ((Propiedad) avatartirada.getCasillaAvatar()).getAlquiler());
                    ((Propiedad) avatartirada.getCasillaAvatar()).getPropietario().setCobroDeAlquileres(((Propiedad) avatartirada.getCasillaAvatar()).getPropietario().getCobroDeAlquileres() - ((Propiedad) avatartirada.getCasillaAvatar()).getAlquiler());
                }
            }
            if (((Sombrero) avatartirada).isCobraBote()) {
                Juego.consola.imprimirln("Debes devolver " + ((Sombrero) avatartirada).getDineroUltimoBote() + "€ al bote del Parking.");
                getCasillas().get(20).setCoste(((Sombrero) avatartirada).getDineroUltimoBote());
                avatartirada.getJugador().setPremiosInversionesOBote(avatartirada.getJugador().getPremiosInversionesOBote() - ((Sombrero) avatartirada).getDineroUltimoBote());
            }
            if (((Sombrero) avatartirada).isCobraImpuesto()) {
                Juego.consola.imprimirln("Se te devuelve " + ((Sombrero) avatartirada).getDineroUltimoImpuesto() + "€ de " + avatartirada.getCasillaAvatar().getNombre() + ".");
                getCasillas().get(20).setCoste(getCasillas().get(20).getCoste() - ((Sombrero) avatartirada).getDineroUltimoImpuesto());
                avatartirada.getJugador().setPagoTasasEImpuestos(avatartirada.getJugador().getPagoTasasEImpuestos() - ((Sombrero) avatartirada).getDineroUltimoImpuesto());
            }
            if (((Sombrero) avatartirada).isCobraCarta()) {
                if (((Sombrero) avatartirada).getDineroUltimaCarta() > 0) {
                    Juego.consola.imprimirln("Debes devolver " + ((Sombrero) avatartirada).getDineroUltimaCarta() + "€ de la acción de " + avatartirada.getCasillaAvatar().getNombre() + ".");
                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() - ((Sombrero) avatartirada).getDineroUltimaCarta());
                    avatartirada.getJugador().setPremiosInversionesOBote(avatartirada.getJugador().getPremiosInversionesOBote() - ((Sombrero) avatartirada).getDineroUltimaCarta());
                } else if (((Sombrero) avatartirada).getDineroUltimaCarta() < 0) {
                    Juego.consola.imprimirln("Se te devuelve " + -((Sombrero) avatartirada).getDineroUltimaCarta() + "€ de la acción de " + avatartirada.getCasillaAvatar().getNombre() + ".");
                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() - ((Sombrero) avatartirada).getDineroUltimaCarta());
                    avatartirada.getJugador().setPagoTasasEImpuestos(avatartirada.getJugador().getPagoTasasEImpuestos() + ((Sombrero) avatartirada).getDineroUltimaCarta());
                    getCasillas().get(20).setCoste(getCasillas().get(20).getCoste() + ((Sombrero) avatartirada).getDineroUltimaCarta());
                }
            }
            if (((Sombrero) avatartirada).isVueltaUltimoTurno()) {
                Juego.consola.imprimirln("Debes devolver " + Valores.getVueltaCompleta() + "€ de haber cobrado por pasar por la casilla de salida.");
                avatartirada.getJugador().setPasarPorCasillaDeSalida(avatartirada.getJugador().getPasarPorCasillaDeSalida() - Valores.getVueltaCompleta());
                avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() - Valores.getVueltaCompleta());
            }
            if (avatartirada.getCasillaAvatar() instanceof Propiedad) {
                if (((Sombrero) avatartirada).isCompraUltimoTurno()) {
                    if (avatartirada.getJugador().getPropiedades().size() > 0) {
                        for (Propiedad p : avatartirada.getJugador().getPropiedades()) {
                            if (p.getNombre().equals(avatartirada.getCasillaAvatar().getNombre())) {
                                if (p instanceof Solar) {
                                    if (((Solar) p).getEdificios().size() > 0) {
                                        for (Edificio e : ((Solar) p).getEdificios()) {
                                            if (p.getGrupo().getNombre().equals("Negro")) {
                                                if (e instanceof Casa) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getCasaGrupoNegro());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getCasaGrupoNegro());
                                                } else if (e instanceof Hotel) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getHotelGrupoNegro());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getHotelGrupoNegro());
                                                } else if (e instanceof Piscina) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getPiscinaGrupoNegro());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getPiscinaGrupoNegro());
                                                } else if (e instanceof PistaDeporte) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getPistaDeDeporteGrupoNegro());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getPistaDeDeporteGrupoNegro());
                                                }
                                            } else if (p.getGrupo().getNombre().equals("Cian")) {
                                                if (e instanceof Casa) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getCasaGrupoCian());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getCasaGrupoCian());
                                                } else if (e instanceof Hotel) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getHotelGrupoCian());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getHotelGrupoCian());
                                                } else if (e instanceof Piscina) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getPiscinaGrupoCian());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getPiscinaGrupoCian());
                                                } else if (e instanceof PistaDeporte) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getPistaDeDeporteGrupoCian());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getPistaDeDeporteGrupoCian());
                                                }
                                            } else if (p.getGrupo().getNombre().equals("Violeta")) {
                                                if (e instanceof Casa) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getCasaGrupoVioleta());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getCasaGrupoVioleta());
                                                } else if (e instanceof Hotel) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getHotelGrupoVioleta());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getHotelGrupoVioleta());
                                                } else if (e instanceof Piscina) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getPiscinaGrupoVioleta());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getPiscinaGrupoVioleta());
                                                } else if (e instanceof PistaDeporte) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getPistaDeDeporteGrupoVioleta());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getPistaDeDeporteGrupoVioleta());
                                                }
                                            } else if (p.getGrupo().getNombre().equals("Amarillo")) {
                                                if (e instanceof Casa) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getCasaGrupoAmarillo());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getCasaGrupoAmarillo());
                                                } else if (e instanceof Hotel) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getHotelGrupoAmarillo());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getHotelGrupoAmarillo());
                                                } else if (e instanceof Piscina) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getPiscinaGrupoAmarillo());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getPiscinaGrupoAmarillo());
                                                } else if (e instanceof PistaDeporte) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getPistaDeDeporteGrupoAmarillo());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getPistaDeDeporteGrupoAmarillo());
                                                }
                                            } else if (p.getGrupo().getNombre().equals("Rojo")) {
                                                if (e instanceof Casa) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getCasaGrupoRojo());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getCasaGrupoRojo());
                                                } else if (e instanceof Hotel) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getHotelGrupoRojo());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getHotelGrupoRojo());
                                                } else if (e instanceof Piscina) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getPiscinaGrupoRojo());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getPiscinaGrupoRojo());
                                                } else if (e instanceof PistaDeporte) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getPistaDeDeporteGrupoRojo());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getPistaDeDeporteGrupoRojo());
                                                }
                                            } else if (p.getGrupo().getNombre().equals("Gris")) {
                                                if (e instanceof Casa) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getCasaGrupoGris());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getCasaGrupoGris());
                                                } else if (e instanceof Hotel) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getHotelGrupoGris());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getHotelGrupoGris());
                                                } else if (e instanceof Piscina) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getPiscinaGrupoGris());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getPiscinaGrupoGris());
                                                } else if (e instanceof PistaDeporte) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getPistaDeDeporteGrupoGris());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getPistaDeDeporteGrupoGris());
                                                }
                                            } else if (p.getGrupo().getNombre().equals("Verde")) {
                                                if (e instanceof Casa) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getCasaGrupoVerde());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getCasaGrupoVerde());
                                                } else if (e instanceof Hotel) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getHotelGrupoVerde());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getHotelGrupoVerde());
                                                } else if (e instanceof Piscina) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getPiscinaGrupoVerde());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getPiscinaGrupoVerde());
                                                } else if (e instanceof PistaDeporte) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getPistaDeDeporteGrupoVerde());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getPistaDeDeporteGrupoVerde());
                                                }
                                            } else if (p.getGrupo().getNombre().equals("Azul")) {
                                                if (e instanceof Casa) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getCasaGrupoAzul());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getCasaGrupoAzul());
                                                } else if (e instanceof Hotel) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getHotelGrupoAzul());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getHotelGrupoAzul());
                                                } else if (e instanceof Piscina) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getPiscinaGrupoAzul());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getPiscinaGrupoAzul());
                                                } else if (e instanceof PistaDeporte) {
                                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.getPistaDeDeporteGrupoAzul());
                                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - Valores.getPistaDeDeporteGrupoAzul());
                                                }
                                            }
                                            ((Solar) p).eliminarEdificio(e);
                                        }
                                        Juego.consola.imprimirln("Has vendido los edificios de " + p.getNombre());
                                    }
                                }
                            }
                        }
                    }
                }
                if (((Sombrero) avatartirada).isHipotecaUltimoTurno()) {
                    if (avatartirada.getJugador().getHipotecas().size() > 0) {
                        for (Propiedad h : avatartirada.getJugador().getHipotecas()) {
                            if (h.getNombre().equals(avatartirada.getCasillaAvatar().getNombre())) {
                                deshipotecar(avatartirada.getJugador(), h);
                                break;
                            }
                        }
                    }
                }
                if (((Sombrero) avatartirada).isDeshipotecaUltimoTurno() && !((Sombrero) avatartirada).isCompraUltimoTurno()) {
                    if (avatartirada.getJugador().getPropiedades().size() > 0) {
                        for (Propiedad h : avatartirada.getJugador().getPropiedades()) {
                            if (h.getNombre().equals(avatartirada.getCasillaAvatar().getNombre())) {
                                hipotecar(avatartirada.getJugador(), h);
                                break;
                            }
                        }
                    }
                }
                if (((Sombrero) avatartirada).isCompraUltimoTurno()) {
                    if (avatartirada.getJugador().getPropiedades().size() > 0) {
                        for (Propiedad p : avatartirada.getJugador().getPropiedades()) {
                            if (p.getNombre().equals(avatartirada.getCasillaAvatar().getNombre())) {
                                avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + p.getCoste());
                                Juego.consola.imprimirln("Has vendido " + p.getNombre() + " por " + p.getCoste() + "€.");
                                avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() - p.getCoste());
                                avatartirada.getJugador().eliminarPropiedad(p);
                                p.setPropietario(null);
                                getBanca().anadirPropiedad(p);
                                break;
                            }
                        }
                    }
                }
            }
            avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + ((Sombrero) avatartirada).getDineroUltimoAlquiler() + ((Sombrero) avatartirada).getDineroUltimoImpuesto() - ((Sombrero) avatartirada).getDineroUltimoBote());
            if (((Sombrero) avatartirada).getCasillaUltimoTurno().getNombre().equals(avatartirada.getCasillaAvatar().getNombre()))
                Juego.consola.imprimirln("Te mantienes en " + avatartirada.getCasillaAvatar().getNombre() + ".");
            else {
                Juego.consola.imprimirln("Vuelves a  " + ((Sombrero) avatartirada).getCasillaUltimoTurno().getNombre() + ".");
                avatartirada.getCasillaAvatar().eliminarAvatar(avatartirada);
                avatartirada.setCasillaAvatar(((Sombrero) avatartirada).getCasillaUltimoTurno());
                avatartirada.setPosicion(avatartirada.getCasillaAvatar().getPosicion());
                avatartirada.getCasillaAvatar().anadirAvatar(avatartirada);
            }
            ((Sombrero) avatartirada).setDineroUltimoAlquiler(0);
            ((Sombrero) avatartirada).setCobraAlquiler(false);
            ((Sombrero) avatartirada).setDineroUltimoImpuesto(0);
            ((Sombrero) avatartirada).setCobraImpuesto(false);
            ((Sombrero) avatartirada).setDineroUltimoBote(0);
            ((Sombrero) avatartirada).setCobraBote(false);
            ((Sombrero) avatartirada).setDineroUltimaCarta(0);
            ((Sombrero) avatartirada).setCobraCarta(false);
            ((Sombrero) avatartirada).setVueltaUltimoTurno(false);
            ((Sombrero) avatartirada).setCompraUltimoTurno(false);
            ((Sombrero) avatartirada).setHipotecaUltimoTurno(false);
            ((Sombrero) avatartirada).setDeshipotecaUltimoTurno(false);
            ((Sombrero) avatartirada).setCasillaUltimoTurno(avatartirada.getCasillaAvatar());
        }
    }

    public void comprobarGrupoEntero(Avatar avatartirada) {
        if (avatartirada.getCasillaAvatar() instanceof Solar) {
            if (avatartirada.getJugador().getPropiedades().contains(getCasillas().get(1)) && avatartirada.getJugador().getPropiedades().contains(getCasillas().get(3))) {
                ((Solar) getCasillas().get(1)).setAlquiler(2 * Valores.getAlquilerGrupoNegro());
                ((Solar) getCasillas().get(3)).setAlquiler(2 * Valores.getAlquilerGrupoNegro());
                ((Solar) avatartirada.getCasillaAvatar()).setPuedoconstruircasa(true);
            }
            if (avatartirada.getJugador().getPropiedades().contains(getCasillas().get(6)) && avatartirada.getJugador().getPropiedades().contains(getCasillas().get(8)) && avatartirada.getJugador().getPropiedades().contains(getCasillas().get(9))) {
                ((Solar) getCasillas().get(6)).setAlquiler(2 * Valores.getAlquilerGrupoCian());
                ((Solar) getCasillas().get(8)).setAlquiler(2 * Valores.getAlquilerGrupoCian());
                ((Solar) getCasillas().get(9)).setAlquiler(2 * Valores.getAlquilerGrupoCian());
                ((Solar) avatartirada.getCasillaAvatar()).setPuedoconstruircasa(true);
            }
            if (avatartirada.getJugador().getPropiedades().contains(getCasillas().get(11)) && avatartirada.getJugador().getPropiedades().contains(getCasillas().get(13)) && avatartirada.getJugador().getPropiedades().contains(getCasillas().get(14))) {
                ((Solar) getCasillas().get(11)).setAlquiler(2 * Valores.getAlquilerGrupoVioleta());
                ((Solar) getCasillas().get(13)).setAlquiler(2 * Valores.getAlquilerGrupoVioleta());
                ((Solar) getCasillas().get(14)).setAlquiler(2 * Valores.getAlquilerGrupoVioleta());
                ((Solar) avatartirada.getCasillaAvatar()).setPuedoconstruircasa(true);
            }
            if (avatartirada.getJugador().getPropiedades().contains(getCasillas().get(16)) && avatartirada.getJugador().getPropiedades().contains(getCasillas().get(18)) && avatartirada.getJugador().getPropiedades().contains(getCasillas().get(19))) {
                ((Solar) getCasillas().get(16)).setAlquiler(2 * Valores.getAlquilerGrupoAmarillo());
                ((Solar) getCasillas().get(18)).setAlquiler(2 * Valores.getAlquilerGrupoAmarillo());
                ((Solar) getCasillas().get(19)).setAlquiler(2 * Valores.getAlquilerGrupoAmarillo());
                ((Solar) avatartirada.getCasillaAvatar()).setPuedoconstruircasa(true);
            }
            if (avatartirada.getJugador().getPropiedades().contains(getCasillas().get(21)) && avatartirada.getJugador().getPropiedades().contains(getCasillas().get(23)) && avatartirada.getJugador().getPropiedades().contains(getCasillas().get(24))) {
                ((Solar) getCasillas().get(21)).setAlquiler(2 * Valores.getAlquilerGrupoRojo());
                ((Solar) getCasillas().get(23)).setAlquiler(2 * Valores.getAlquilerGrupoRojo());
                ((Solar) getCasillas().get(24)).setAlquiler(2 * Valores.getAlquilerGrupoRojo());
                ((Solar) avatartirada.getCasillaAvatar()).setPuedoconstruircasa(true);
            }
            if (avatartirada.getJugador().getPropiedades().contains(getCasillas().get(26)) && avatartirada.getJugador().getPropiedades().contains(getCasillas().get(27)) && avatartirada.getJugador().getPropiedades().contains(getCasillas().get(29))) {
                ((Solar) getCasillas().get(26)).setAlquiler(2 * Valores.getAlquilerGrupoGris());
                ((Solar) getCasillas().get(27)).setAlquiler(2 * Valores.getAlquilerGrupoGris());
                ((Solar) getCasillas().get(29)).setAlquiler(2 * Valores.getAlquilerGrupoGris());
                ((Solar) avatartirada.getCasillaAvatar()).setPuedoconstruircasa(true);
            }
            if (avatartirada.getJugador().getPropiedades().contains(getCasillas().get(31)) && avatartirada.getJugador().getPropiedades().contains(getCasillas().get(32)) && avatartirada.getJugador().getPropiedades().contains(getCasillas().get(34))) {
                ((Solar) getCasillas().get(31)).setAlquiler(2 * Valores.getAlquilerGrupoVerde());
                ((Solar) getCasillas().get(32)).setAlquiler(2 * Valores.getAlquilerGrupoVerde());
                ((Solar) getCasillas().get(34)).setAlquiler(2 * Valores.getAlquilerGrupoVerde());
                ((Solar) avatartirada.getCasillaAvatar()).setPuedoconstruircasa(true);
            }
            if (avatartirada.getJugador().getPropiedades().contains(getCasillas().get(37)) && avatartirada.getJugador().getPropiedades().contains(getCasillas().get(39))) {
                ((Solar) getCasillas().get(37)).setAlquiler(2 * Valores.getAlquilerGrupoAzul());
                ((Solar) getCasillas().get(39)).setAlquiler(2 * Valores.getAlquilerGrupoAzul());
                ((Solar) avatartirada.getCasillaAvatar()).setPuedoconstruircasa(true);
            }
        }
    }

    public void hipotecar(Jugador jugadorturno, Propiedad casilla) throws ConEdificios {
        for (Propiedad p : jugadorturno.getPropiedades()) {
            if (p.getNombre().equals(casilla.getNombre())) {
                if (p instanceof Solar) {
                    if (jugadorturno.getEdificios().size() == 0) {
                        if (casilla.getGrupo().getNombre().equals("Negro")) {
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getHipotecaGrupoNegro());
                            Juego.consola.imprimirln(jugadorturno.getNombre() + " recibe " + Valores.getHipotecaGrupoNegro() + " por la hipoteca de " + casilla.getNombre() + ". No puede recibir alquileres ni edificar en el grupo " + casilla.getGrupo().getNombre() + ".");
                            anadirHipoteca(jugadorturno, casilla);
                        } else if (casilla.getGrupo().getNombre().equals("Cian")) {
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getHipotecaGrupoCian());
                            Juego.consola.imprimirln(jugadorturno.getNombre() + " recibe " + Valores.getHipotecaGrupoCian() + " por la hipoteca de " + casilla.getNombre() + ". No puede recibir alquileres ni edificar en el grupo " + casilla.getGrupo().getNombre() + ".");
                            anadirHipoteca(jugadorturno, casilla);
                        } else if (casilla.getGrupo().getNombre().equals("Violeta")) {
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getHipotecaGrupoVioleta());
                            Juego.consola.imprimirln(jugadorturno.getNombre() + " recibe " + Valores.getHipotecaGrupoVioleta() + " por la hipoteca de " + casilla.getNombre() + ". No puede recibir alquileres ni edificar en el grupo " + casilla.getGrupo().getNombre() + ".");
                            anadirHipoteca(jugadorturno, casilla);
                        } else if (casilla.getGrupo().getNombre().equals("Amarillo")) {
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getHipotecaGrupoAmarillo());
                            Juego.consola.imprimirln(jugadorturno.getNombre() + " recibe " + Valores.getHipotecaGrupoAmarillo() + " por la hipoteca de " + casilla.getNombre() + ". No puede recibir alquileres ni edificar en el grupo " + casilla.getGrupo().getNombre() + ".");
                            anadirHipoteca(jugadorturno, casilla);
                        } else if (casilla.getGrupo().getNombre().equals("Rojo")) {
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getHipotecaGrupoRojo());
                            Juego.consola.imprimirln(jugadorturno.getNombre() + " recibe " + Valores.getHipotecaGrupoRojo() + " por la hipoteca de " + casilla.getNombre() + ". No puede recibir alquileres ni edificar en el grupo " + casilla.getGrupo().getNombre() + ".");
                            anadirHipoteca(jugadorturno, casilla);
                        } else if (casilla.getGrupo().getNombre().equals("Gris")) {
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getHipotecaGrupoGris());
                            Juego.consola.imprimirln(jugadorturno.getNombre() + " recibe " + Valores.getHipotecaGrupoGris() + " por la hipoteca de " + casilla.getNombre() + ". No puede recibir alquileres ni edificar en el grupo " + casilla.getGrupo().getNombre() + ".");
                            anadirHipoteca(jugadorturno, casilla);
                        } else if (casilla.getGrupo().getNombre().equals("Verde")) {
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getHipotecaGrupoVerde());
                            Juego.consola.imprimirln(jugadorturno.getNombre() + " recibe " + Valores.getHipotecaGrupoVerde() + " por la hipoteca de " + casilla.getNombre() + ". No puede recibir alquileres ni edificar en el grupo " + casilla.getGrupo().getNombre() + ".");
                            anadirHipoteca(jugadorturno, casilla);
                        } else {
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getHipotecaGrupoAzul());
                            Juego.consola.imprimirln(jugadorturno.getNombre() + " recibe " + Valores.getHipotecaGrupoAzul() + " por la hipoteca de " + casilla.getNombre() + ". No puede recibir alquileres ni edificar en el grupo " + casilla.getGrupo().getNombre() + ".");
                            anadirHipoteca(jugadorturno, casilla);
                        }
                    } else {
                        throw new ConEdificios("Tienes edificios en la propiedad, antes de hipotecar debes venderlos.");
                    }
                } else if (p instanceof Transporte) {
                    jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getHipotecaTransporte());
                    Juego.consola.imprimirln(jugadorturno.getNombre() + " recibe " + Valores.getHipotecaTransporte() + " por la hipoteca de " + casilla.getNombre() + ".");
                    jugadorturno.getHipotecas().add(casilla);
                    jugadorturno.eliminarPropiedad(casilla);
                } else {
                    jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getHipotecaServicio());
                    Juego.consola.imprimirln(jugadorturno.getNombre() + " recibe " + Valores.getHipotecaServicio() + " por la hipoteca de " + casilla.getNombre() + ".");
                    jugadorturno.getHipotecas().add(casilla);
                    jugadorturno.eliminarPropiedad(casilla);
                }
                break;
            }
        }
    }

    private void anadirHipoteca(Jugador jugadorturno, Propiedad casilla) {
        jugadorturno.getHipotecas().add(casilla);
        jugadorturno.eliminarPropiedad(casilla);
        ((Solar) jugadorturno.getAvatar().getCasillaAvatar()).setPuedoconstruircasa(false);
    }

    public void deshipotecar(Jugador jugadorturno, Propiedad casilla) throws SinDinero {
        for (Propiedad h : jugadorturno.getHipotecas()) {
            if (h.getNombre().equals(casilla.getNombre())) {
                if (h instanceof Solar) {
                    if (casilla.getGrupo().getNombre().equals("Negro")) {
                        if (jugadorturno.getFortuna() >= Valores.getRecargoGrupoNegro()) {
                            jugadorturno.setFortuna(jugadorturno.getFortuna() - Valores.getRecargoGrupoNegro());
                            Juego.consola.imprimirln(jugadorturno.getNombre() + " paga " + Valores.getRecargoGrupoNegro() + " por deshipotecar " + casilla.getNombre() + ". Ahora puede recibir alquileres y edificar en el grupo " + casilla.getGrupo().getNombre() + ".");
                            anadirPropiedad(jugadorturno, casilla);
                        } else
                            throw new SinDinero("No tienes suficiente dinero para deshipotecar " + casilla.getNombre());
                    } else if (casilla.getGrupo().getNombre().equals("Cian")) {
                        if (jugadorturno.getFortuna() >= Valores.getRecargoGrupoCian()) {
                            jugadorturno.setFortuna(jugadorturno.getFortuna() - Valores.getRecargoGrupoCian());
                            Juego.consola.imprimirln(jugadorturno.getNombre() + " paga " + Valores.getRecargoGrupoCian() + " por deshipotecar " + casilla.getNombre() + ". Ahora puede recibir alquileres y edificar en el grupo " + casilla.getGrupo().getNombre() + ".");
                            anadirPropiedad(jugadorturno, casilla);
                        } else
                            throw new SinDinero("No tienes suficiente dinero para deshipotecar " + casilla.getNombre());
                    } else if (casilla.getGrupo().getNombre().equals("Violeta")) {
                        if (jugadorturno.getFortuna() >= Valores.getRecargoGrupoVioleta()) {
                            jugadorturno.setFortuna(jugadorturno.getFortuna() - Valores.getRecargoGrupoVioleta());
                            Juego.consola.imprimirln(jugadorturno.getNombre() + " paga " + Valores.getRecargoGrupoVioleta() + " por deshipotecar " + casilla.getNombre() + ". Ahora puede recibir alquileres y edificar en el grupo " + casilla.getGrupo().getNombre() + ".");
                            anadirPropiedad(jugadorturno, casilla);
                        } else
                            throw new SinDinero("No tienes suficiente dinero para deshipotecar " + casilla.getNombre());
                    } else if (casilla.getGrupo().getNombre().equals("Amarillo")) {
                        if (jugadorturno.getFortuna() >= Valores.getRecargoGrupoAmarillo()) {
                            jugadorturno.setFortuna(jugadorturno.getFortuna() - Valores.getRecargoGrupoAmarillo());
                            Juego.consola.imprimirln(jugadorturno.getNombre() + " paga " + Valores.getRecargoGrupoAmarillo() + " por deshipotecar " + casilla.getNombre() + ". Ahora puede recibir alquileres y edificar en el grupo " + casilla.getGrupo().getNombre() + ".");
                            anadirPropiedad(jugadorturno, casilla);
                        } else
                            throw new SinDinero("No tienes suficiente dinero para deshipotecar " + casilla.getNombre());
                    } else if (casilla.getGrupo().getNombre().equals("Rojo")) {
                        if (jugadorturno.getFortuna() >= Valores.getRecargoGrupoRojo()) {
                            jugadorturno.setFortuna(jugadorturno.getFortuna() - Valores.getRecargoGrupoRojo());
                            Juego.consola.imprimirln(jugadorturno.getNombre() + " paga " + Valores.getRecargoGrupoRojo() + " por deshipotecar " + casilla.getNombre() + ". Ahora puede recibir alquileres y edificar en el grupo " + casilla.getGrupo().getNombre() + ".");
                            anadirPropiedad(jugadorturno, casilla);
                        } else
                            throw new SinDinero("No tienes suficiente dinero para deshipotecar " + casilla.getNombre());
                    } else if (casilla.getGrupo().getNombre().equals("Gris")) {
                        if (jugadorturno.getFortuna() >= Valores.getRecargoGrupoGris()) {
                            jugadorturno.setFortuna(jugadorturno.getFortuna() - Valores.getRecargoGrupoGris());
                            Juego.consola.imprimirln(jugadorturno.getNombre() + " paga " + Valores.getRecargoGrupoGris() + " por deshipotecar " + casilla.getNombre() + ". Ahora puede recibir alquileres y edificar en el grupo " + casilla.getGrupo().getNombre() + ".");
                            anadirPropiedad(jugadorturno, casilla);
                        } else
                            throw new SinDinero("No tienes suficiente dinero para deshipotecar " + casilla.getNombre());
                    } else if (casilla.getGrupo().getNombre().equals("Verde")) {
                        if (jugadorturno.getFortuna() >= Valores.getRecargoGrupoVerde()) {
                            jugadorturno.setFortuna(jugadorturno.getFortuna() - Valores.getRecargoGrupoVerde());
                            Juego.consola.imprimirln(jugadorturno.getNombre() + " paga " + Valores.getRecargoGrupoVerde() + " por deshipotecar " + casilla.getNombre() + ". Ahora puede recibir alquileres y edificar en el grupo " + casilla.getGrupo().getNombre() + ".");
                            anadirPropiedad(jugadorturno, casilla);
                        } else
                            throw new SinDinero("No tienes suficiente dinero para deshipotecar " + casilla.getNombre());
                    } else {
                        if (jugadorturno.getFortuna() >= Valores.getRecargoGrupoAzul()) {
                            jugadorturno.setFortuna(jugadorturno.getFortuna() - Valores.getRecargoGrupoAzul());
                            Juego.consola.imprimirln(jugadorturno.getNombre() + " paga " + Valores.getRecargoGrupoAzul() + " por deshipotecar " + casilla.getNombre() + ". Ahora puede recibir alquileres y edificar en el grupo " + casilla.getGrupo().getNombre() + ".");
                            anadirPropiedad(jugadorturno, casilla);
                        } else
                            throw new SinDinero("No tienes suficiente dinero para deshipotecar " + casilla.getNombre());
                    }
                } else if (h instanceof Transporte) {
                    if (jugadorturno.getFortuna() >= Valores.getRecargoTransporte()) {
                        jugadorturno.setFortuna(jugadorturno.getFortuna() - Valores.getRecargoTransporte());
                        Juego.consola.imprimirln(jugadorturno.getNombre() + " paga " + Valores.getRecargoTransporte() + " por deshipotecar " + casilla.getNombre() + ". Ahora puede recibir alquileres.");
                        jugadorturno.getHipotecas().remove(casilla);
                        jugadorturno.anadirPropiedad(casilla);
                    } else
                        throw new SinDinero("No tienes suficiente dinero para deshipotecar " + casilla.getNombre());
                } else {
                    if (jugadorturno.getFortuna() >= Valores.getRecargoServicio()) {
                        jugadorturno.setFortuna(jugadorturno.getFortuna() - Valores.getRecargoServicio());
                        Juego.consola.imprimirln(jugadorturno.getNombre() + " paga " + Valores.getRecargoServicio() + " por deshipotecar " + casilla.getNombre() + ". Ahora puede recibir alquileres.");
                        jugadorturno.getHipotecas().remove(casilla);
                        jugadorturno.anadirPropiedad(casilla);
                    } else
                        throw new SinDinero("No tienes suficiente dinero para deshipotecar " + casilla.getNombre());
                }
                break;
            }
        }
    }

    private void anadirPropiedad(Jugador jugadorturno, Propiedad casilla) {
        jugadorturno.getHipotecas().remove(casilla);
        jugadorturno.anadirPropiedad(casilla);
        comprobarGrupoEntero(jugadorturno.getAvatar());
    }

    public void venderCasa(Jugador jugadorturno, Casilla casilla, int num_casas) throws VenderExcepcion {
        int numeroscasas = 0;
        ArrayList<Edificio> tipo;
        tipo = new ArrayList<>();
        for (Edificio e : ((Solar) casilla).getEdificios()) {
            if (e instanceof Casa) {
                tipo.add(e);
            }
        }
        if (casilla.getGrupo().getNombre().equals("Negro")) {
            if (tipo.size() >= num_casas && tipo.size() > 0) {
                while (numeroscasas < num_casas) {
                    for (Edificio e : ((Solar) casilla).getEdificios()) {
                        if (e instanceof Casa) {
                            ((Solar) casilla).eliminarEdificio(e);
                            numeroscasas++;
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getVenderCasaGrupoNegro());
                            break;
                        }
                    }
                }
                ((Solar) casilla).setNumerocasas(tipo.size() - num_casas);
                Juego.consola.imprimirln(jugadorturno.getNombre() + " ha vendido " + num_casas + " casa(s) en " + casilla.getNombre() + ", recibiendo " + (tipo.size() * Valores.getVenderCasaGrupoNegro()) + ". En la propiedad queda(n) " + ((Solar) casilla).getNumerocasas() + " casa(s).");
            } else if (tipo.size() < num_casas)
                throw new VenderExcepcion("Solamente se puede(n) vender " + tipo.size() + " casa(s), recibiendo " + (tipo.size() * Valores.getVenderCasaGrupoNegro()));
            else
                throw new VenderExcepcion("No hay casas construídas en esta propiedad.");
        } else if (casilla.getGrupo().getNombre().equals("Cian")) {
            if (tipo.size() >= num_casas && tipo.size() > 0) {
                while (numeroscasas < num_casas) {
                    for (Edificio e : ((Solar) casilla).getEdificios()) {
                        if (e instanceof Casa) {
                            ((Solar) casilla).eliminarEdificio(e);
                            numeroscasas++;
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getVenderCasaGrupoCian());
                            break;
                        }
                    }
                }
                ((Solar) casilla).setNumerocasas(tipo.size() - num_casas);
                Juego.consola.imprimirln(jugadorturno.getNombre() + " ha vendido " + num_casas + " casa(s) en " + casilla.getNombre() + ", recibiendo " + (tipo.size() * Valores.getVenderCasaGrupoCian()) + ". En la propiedad queda(n) " + ((Solar) casilla).getNumerocasas() + " casa(s).");
            } else if (tipo.size() < num_casas)
                throw new VenderExcepcion("Solamente se puede(n) vender " + tipo.size() + " casa(s), recibiendo " + (tipo.size() * Valores.getVenderCasaGrupoCian()));
            else
                throw new VenderExcepcion("No hay casas construídas en esta propiedad.");
        } else if (casilla.getGrupo().getNombre().equals("Violeta")) {
            if (tipo.size() >= num_casas && tipo.size() > 0) {
                while (numeroscasas < num_casas) {
                    for (Edificio e : ((Solar) casilla).getEdificios()) {
                        if (e instanceof Casa) {
                            ((Solar) casilla).eliminarEdificio(e);
                            numeroscasas++;
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getVenderCasaGrupoVioleta());
                            break;
                        }
                    }
                }
                ((Solar) casilla).setNumerocasas(tipo.size() - num_casas);
                Juego.consola.imprimirln(jugadorturno.getNombre() + " ha vendido " + num_casas + " casa(s) en " + casilla.getNombre() + ", recibiendo " + (tipo.size() * Valores.getVenderCasaGrupoVioleta()) + ". En la propiedad queda(n) " + ((Solar) casilla).getNumerocasas() + " casa(s).");
            } else if (tipo.size() < num_casas)
                throw new VenderExcepcion("Solamente se puede(n) vender " + tipo.size() + " casa(s), recibiendo " + (tipo.size() * Valores.getVenderCasaGrupoVioleta()));
            else
                throw new VenderExcepcion("No hay casas construídas en esta propiedad.");
        } else if (casilla.getGrupo().getNombre().equals("Amarillo")) {
            if (tipo.size() >= num_casas && tipo.size() > 0) {
                while (numeroscasas < num_casas) {
                    for (Edificio e : ((Solar) casilla).getEdificios()) {
                        if (e instanceof Casa) {
                            ((Solar) casilla).eliminarEdificio(e);
                            numeroscasas++;
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getVenderCasaGrupoAmarillo());
                            break;
                        }
                    }
                }
                ((Solar) casilla).setNumerocasas(tipo.size() - num_casas);
                Juego.consola.imprimirln(jugadorturno.getNombre() + " ha vendido " + num_casas + " casa(s) en " + casilla.getNombre() + ", recibiendo " + (tipo.size() * Valores.getVenderCasaGrupoAmarillo()) + ". En la propiedad queda(n) " + ((Solar) casilla).getNumerocasas() + " casa(s).");
            } else if (tipo.size() < num_casas)
                throw new VenderExcepcion("Solamente se puede(n) vender " + tipo.size() + " casa(s), recibiendo " + (tipo.size() * Valores.getVenderCasaGrupoAmarillo()));
            else
                throw new VenderExcepcion("No hay casas construídas en esta propiedad.");
        } else if (casilla.getGrupo().getNombre().equals("Rojo")) {
            if (tipo.size() >= num_casas && tipo.size() > 0) {
                while (numeroscasas < num_casas) {
                    for (Edificio e : ((Solar) casilla).getEdificios()) {
                        if (e instanceof Casa) {
                            ((Solar) casilla).eliminarEdificio(e);
                            numeroscasas++;
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getVenderCasaGrupoRojo());
                            break;
                        }
                    }
                }
                ((Solar) casilla).setNumerocasas(tipo.size() - num_casas);
                Juego.consola.imprimirln(jugadorturno.getNombre() + " ha vendido " + num_casas + " casa(s) en " + casilla.getNombre() + ", recibiendo " + (tipo.size() * Valores.getVenderCasaGrupoRojo()) + ". En la propiedad queda(n) " + ((Solar) casilla).getNumerocasas() + " casa(s).");
            } else if (tipo.size() < num_casas)
                throw new VenderExcepcion("Solamente se puede(n) vender " + tipo.size() + " casa(s), recibiendo " + (tipo.size() * Valores.getVenderCasaGrupoRojo()));
            else
                throw new VenderExcepcion("No hay casas construídas en esta propiedad.");
        } else if (casilla.getGrupo().getNombre().equals("Gris")) {
            if (tipo.size() >= num_casas && tipo.size() > 0) {
                while (numeroscasas < num_casas) {
                    for (Edificio e : ((Solar) casilla).getEdificios()) {
                        if (e instanceof Casa) {
                            ((Solar) casilla).eliminarEdificio(e);
                            numeroscasas++;
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getVenderCasaGrupoGris());
                            break;
                        }
                    }
                }
                ((Solar) casilla).setNumerocasas(tipo.size() - num_casas);
                Juego.consola.imprimirln(jugadorturno.getNombre() + " ha vendido " + num_casas + " casa(s) en " + casilla.getNombre() + ", recibiendo " + (tipo.size() * Valores.getVenderCasaGrupoGris()) + ". En la propiedad queda(n) " + ((Solar) casilla).getNumerocasas() + " casa(s).");
            } else if (tipo.size() < num_casas)
                throw new VenderExcepcion("Solamente se puede(n) vender " + tipo.size() + " casa(s), recibiendo " + (tipo.size() * Valores.getVenderCasaGrupoGris()));
            else
                throw new VenderExcepcion("No hay casas construídas en esta propiedad.");
        } else if (casilla.getGrupo().getNombre().equals("Verde")) {
            if (tipo.size() >= num_casas && tipo.size() > 0) {
                while (numeroscasas < num_casas) {
                    for (Edificio e : ((Solar) casilla).getEdificios()) {
                        if (e instanceof Casa) {
                            ((Solar) casilla).eliminarEdificio(e);
                            numeroscasas++;
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getVenderCasaGrupoVerde());
                            break;
                        }
                    }
                }
                ((Solar) casilla).setNumerocasas(tipo.size() - num_casas);
                Juego.consola.imprimirln(jugadorturno.getNombre() + " ha vendido " + num_casas + " casa(s) en " + casilla.getNombre() + ", recibiendo " + (tipo.size() * Valores.getVenderCasaGrupoVerde()) + ". En la propiedad queda(n) " + ((Solar) casilla).getNumerocasas() + " casa(s).");
            } else if (tipo.size() < num_casas)
                throw new VenderExcepcion("Solamente se puede(n) vender " + tipo.size() + " casa(s), recibiendo " + (tipo.size() * Valores.getVenderCasaGrupoVerde()));
            else
                throw new VenderExcepcion("No hay casas construídas en esta propiedad.");
        } else {
            if (tipo.size() >= num_casas && tipo.size() > 0) {
                while (numeroscasas < num_casas) {
                    for (Edificio e : ((Solar) casilla).getEdificios()) {
                        if (e instanceof Casa) {
                            ((Solar) casilla).eliminarEdificio(e);
                            numeroscasas++;
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getVenderCasaGrupoAzul());
                            break;
                        }
                    }
                }
                ((Solar) casilla).setNumerocasas(tipo.size() - num_casas);
                Juego.consola.imprimirln(jugadorturno.getNombre() + " ha vendido " + num_casas + " casa(s) en " + casilla.getNombre() + ", recibiendo " + (tipo.size() * Valores.getVenderCasaGrupoAzul()) + ". En la propiedad queda(n) " + ((Solar) casilla).getNumerocasas() + " casa(s).");
            } else if (tipo.size() < num_casas)
                throw new VenderExcepcion("Solamente se puede(n) vender " + tipo.size() + " casa(s), recibiendo " + (tipo.size() * Valores.getVenderCasaGrupoAzul()));
            else
                throw new VenderExcepcion("No hay casas construídas en esta propiedad.");
        }
    }

    public void venderHotel(Jugador jugadorturno, Casilla casilla, int num_hotels) throws VenderExcepcion {
        int numeroshotels = 0;
        ArrayList<Edificio> tipo;
        tipo = new ArrayList<>();
        for (Edificio e : ((Solar) casilla).getEdificios()) {
            if (e instanceof Hotel) {
                tipo.add(e);
            }
        }
        if (casilla.getGrupo().getNombre().equals("Negro")) {
            if (tipo.size() >= num_hotels && tipo.size() > 0) {
                while (numeroshotels < num_hotels) {
                    for (Edificio e : ((Solar) casilla).getEdificios()) {
                        if (e instanceof Hotel) {
                            ((Solar) casilla).eliminarEdificio(e);
                            numeroshotels++;
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getVenderHotelGrupoNegro());
                            break;
                        }
                    }
                }
                ((Solar) casilla).setNumerohoteles(tipo.size() - num_hotels);
                Juego.consola.imprimirln(jugadorturno.getNombre() + " ha vendido " + num_hotels + " hotel(es) en " + casilla.getNombre() + ", recibiendo " + (tipo.size() * Valores.getVenderHotelGrupoNegro()) + ". En la propiedad queda(n) " + ((Solar) casilla).getNumerohoteles() + " hotel(es).");
            } else if (tipo.size() < num_hotels)
                throw new VenderExcepcion("Solamente se puede(n) vender " + tipo.size() + " hotel(es), recibiendo " + (tipo.size() * Valores.getVenderHotelGrupoNegro()));
            else
                throw new VenderExcepcion("No hay hoteles construídos en esta propiedad.");
        } else if (casilla.getGrupo().getNombre().equals("Cian")) {
            if (tipo.size() >= num_hotels && tipo.size() > 0) {
                while (numeroshotels < num_hotels) {
                    for (Edificio e : ((Solar) casilla).getEdificios()) {
                        if (e instanceof Hotel) {
                            ((Solar) casilla).eliminarEdificio(e);
                            numeroshotels++;
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getVenderHotelGrupoCian());
                            break;
                        }
                    }
                }
                ((Solar) casilla).setNumerohoteles(tipo.size() - num_hotels);
                Juego.consola.imprimirln(jugadorturno.getNombre() + " ha vendido " + num_hotels + " hotel(es) en " + casilla.getNombre() + ", recibiendo " + (tipo.size() * Valores.getVenderHotelGrupoCian()) + ". En la propiedad queda(n) " + ((Solar) casilla).getNumerohoteles() + " hotel(es).");
            } else if (tipo.size() < num_hotels)
                throw new VenderExcepcion("Solamente se puede(n) vender " + tipo.size() + " hotel(es), recibiendo " + (tipo.size() * Valores.getVenderHotelGrupoCian()));
            else
                throw new VenderExcepcion("No hay hoteles construídos en esta propiedad.");
        } else if (casilla.getGrupo().getNombre().equals("Violeta")) {
            if (tipo.size() >= num_hotels && tipo.size() > 0) {
                while (numeroshotels < num_hotels) {
                    for (Edificio e : ((Solar) casilla).getEdificios()) {
                        if (e instanceof Hotel) {
                            ((Solar) casilla).eliminarEdificio(e);
                            numeroshotels++;
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getVenderHotelGrupoVioleta());
                            break;
                        }
                    }
                }
                ((Solar) casilla).setNumerohoteles(tipo.size() - num_hotels);
                Juego.consola.imprimirln(jugadorturno.getNombre() + " ha vendido " + num_hotels + " hotel(es) en " + casilla.getNombre() + ", recibiendo " + (tipo.size() * Valores.getVenderHotelGrupoVioleta()) + ". En la propiedad queda(n) " + ((Solar) casilla).getNumerohoteles() + " hotel(es).");
            } else if (tipo.size() < num_hotels)
                throw new VenderExcepcion("Solamente se puede(n) vender " + tipo.size() + " hotel(es), recibiendo " + (tipo.size() * Valores.getVenderHotelGrupoVioleta()));
            else
                throw new VenderExcepcion("No hay hoteles construídos en esta propiedad.");
        } else if (casilla.getGrupo().getNombre().equals("Amarillo")) {
            if (tipo.size() >= num_hotels && tipo.size() > 0) {
                while (numeroshotels < num_hotels) {
                    for (Edificio e : ((Solar) casilla).getEdificios()) {
                        if (e instanceof Hotel) {
                            ((Solar) casilla).eliminarEdificio(e);
                            numeroshotels++;
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getVenderHotelGrupoAmarillo());
                            break;
                        }
                    }
                }
                ((Solar) casilla).setNumerohoteles(tipo.size() - num_hotels);
                Juego.consola.imprimirln(jugadorturno.getNombre() + " ha vendido " + num_hotels + " hotel(es) en " + casilla.getNombre() + ", recibiendo " + (tipo.size() * Valores.getVenderHotelGrupoAmarillo()) + ". En la propiedad queda(n) " + ((Solar) casilla).getNumerohoteles() + " hotel(es).");
            } else if (tipo.size() < num_hotels)
                throw new VenderExcepcion("Solamente se puede(n) vender " + tipo.size() + " hotel(es), recibiendo " + (tipo.size() * Valores.getVenderHotelGrupoAmarillo()));
            else
                throw new VenderExcepcion("No hay hoteles construídos en esta propiedad.");
        } else if (casilla.getGrupo().getNombre().equals("Rojo")) {
            if (tipo.size() >= num_hotels && tipo.size() > 0) {
                while (numeroshotels < num_hotels) {
                    for (Edificio e : ((Solar) casilla).getEdificios()) {
                        if (e instanceof Hotel) {
                            ((Solar) casilla).eliminarEdificio(e);
                            numeroshotels++;
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getVenderHotelGrupoRojo());
                            break;
                        }
                    }
                }
                ((Solar) casilla).setNumerohoteles(tipo.size() - num_hotels);
                Juego.consola.imprimirln(jugadorturno.getNombre() + " ha vendido " + num_hotels + " hotel(es) en " + casilla.getNombre() + ", recibiendo " + (tipo.size() * Valores.getVenderHotelGrupoRojo()) + ". En la propiedad queda(n) " + ((Solar) casilla).getNumerohoteles() + " hotel(es).");
            } else if (tipo.size() < num_hotels)
                throw new VenderExcepcion("Solamente se puede(n) vender " + tipo.size() + " hotel(es), recibiendo " + (tipo.size() * Valores.getVenderHotelGrupoRojo()));
            else
                throw new VenderExcepcion("No hay hoteles construídos en esta propiedad.");
        } else if (casilla.getGrupo().getNombre().equals("Gris")) {
            if (tipo.size() >= num_hotels && tipo.size() > 0) {
                while (numeroshotels < num_hotels) {
                    for (Edificio e : ((Solar) casilla).getEdificios()) {
                        if (e instanceof Hotel) {
                            ((Solar) casilla).eliminarEdificio(e);
                            numeroshotels++;
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getVenderHotelGrupoGris());
                            break;
                        }
                    }
                }
                ((Solar) casilla).setNumerohoteles(tipo.size() - num_hotels);
                Juego.consola.imprimirln(jugadorturno.getNombre() + " ha vendido " + num_hotels + " hotel(es) en " + casilla.getNombre() + ", recibiendo " + (tipo.size() * Valores.getVenderHotelGrupoGris()) + ". En la propiedad queda(n) " + ((Solar) casilla).getNumerohoteles() + " hotel(es).");
            } else if (tipo.size() < num_hotels)
                throw new VenderExcepcion("Solamente se puede(n) vender " + tipo.size() + " hotel(es), recibiendo " + (tipo.size() * Valores.getVenderHotelGrupoGris()));
            else
                throw new VenderExcepcion("No hay hoteles construídos en esta propiedad.");
        } else if (casilla.getGrupo().getNombre().equals("Verde")) {
            if (tipo.size() >= num_hotels && tipo.size() > 0) {
                while (numeroshotels < num_hotels) {
                    for (Edificio e : ((Solar) casilla).getEdificios()) {
                        if (e instanceof Hotel) {
                            ((Solar) casilla).eliminarEdificio(e);
                            numeroshotels++;
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getVenderHotelGrupoVerde());
                            break;
                        }
                    }
                }
                ((Solar) casilla).setNumerohoteles(tipo.size() - num_hotels);
                Juego.consola.imprimirln(jugadorturno.getNombre() + " ha vendido " + num_hotels + " hotel(es) en " + casilla.getNombre() + ", recibiendo " + (tipo.size() * Valores.getVenderHotelGrupoVerde()) + ". En la propiedad queda(n) " + ((Solar) casilla).getNumerohoteles() + " hotel(es).");
            } else if (tipo.size() < num_hotels)
                throw new VenderExcepcion("Solamente se puede(n) vender " + tipo.size() + " hotel(es), recibiendo " + (tipo.size() * Valores.getVenderHotelGrupoVerde()));
            else
                throw new VenderExcepcion("No hay hoteles construídos en esta propiedad.");
        } else {
            if (tipo.size() >= num_hotels && tipo.size() > 0) {
                while (numeroshotels < num_hotels) {
                    for (Edificio e : ((Solar) casilla).getEdificios()) {
                        if (e instanceof Hotel) {
                            ((Solar) casilla).eliminarEdificio(e);
                            numeroshotels++;
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getVenderHotelGrupoAzul());
                            break;
                        }
                    }
                }
                ((Solar) casilla).setNumerohoteles(tipo.size() - num_hotels);
                Juego.consola.imprimirln(jugadorturno.getNombre() + " ha vendido " + num_hotels + " hotel(es) en " + casilla.getNombre() + ", recibiendo " + (tipo.size() * Valores.getVenderHotelGrupoAzul()) + ". En la propiedad queda(n) " + ((Solar) casilla).getNumerohoteles() + " hotel(es).");
            } else if (tipo.size() < num_hotels)
                throw new VenderExcepcion("Solamente se puede(n) vender " + tipo.size() + " hotel(es), recibiendo " + (tipo.size() * Valores.getVenderHotelGrupoAzul()));
            else
                throw new VenderExcepcion("No hay hoteles construídos en esta propiedad.");
        }
    }

    public void venderPiscina(Jugador jugadorturno, Casilla casilla, int num_Piscinas) throws VenderExcepcion {
        int numerosPiscinas = 0;
        ArrayList<Edificio> tipo;
        tipo = new ArrayList<>();
        for (Edificio e : ((Solar) casilla).getEdificios()) {
            if (e instanceof Piscina) {
                tipo.add(e);
            }
        }
        if (casilla.getGrupo().getNombre().equals("Negro")) {
            if (tipo.size() >= num_Piscinas && tipo.size() > 0) {
                while (numerosPiscinas < num_Piscinas) {
                    for (Edificio e : ((Solar) casilla).getEdificios()) {
                        if (e instanceof Piscina) {
                            ((Solar) casilla).eliminarEdificio(e);
                            numerosPiscinas++;
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getVenderPiscinaGrupoNegro());
                            break;
                        }
                    }
                }
                ((Solar) casilla).setNumeropiscinas(tipo.size() - num_Piscinas);
                Juego.consola.imprimirln(jugadorturno.getNombre() + " ha vendido " + num_Piscinas + " piscina(s) en " + casilla.getNombre() + ", recibiendo " + (tipo.size() * Valores.getVenderPiscinaGrupoNegro()) + ". En la propiedad queda(n) " + ((Solar) casilla).getNumeropiscinas() + " piscina(s).");
            } else if (tipo.size() < num_Piscinas)
                throw new VenderExcepcion("Solamente se puede(n) vender " + tipo.size() + " piscina(s), recibiendo " + (tipo.size() * Valores.getVenderPiscinaGrupoNegro()));
            else
                throw new VenderExcepcion("No hay piscinas construídas en esta propiedad.");
        } else if (casilla.getGrupo().getNombre().equals("Cian")) {
            if (tipo.size() >= num_Piscinas && tipo.size() > 0) {
                while (numerosPiscinas < num_Piscinas) {
                    for (Edificio e : ((Solar) casilla).getEdificios()) {
                        if (e instanceof Piscina) {
                            ((Solar) casilla).eliminarEdificio(e);
                            numerosPiscinas++;
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getVenderPiscinaGrupoCian());
                            break;
                        }
                    }
                }
                ((Solar) casilla).setNumeropiscinas(tipo.size() - num_Piscinas);
                Juego.consola.imprimirln(jugadorturno.getNombre() + " ha vendido " + num_Piscinas + " piscina(s) en " + casilla.getNombre() + ", recibiendo " + (tipo.size() * Valores.getVenderPiscinaGrupoCian()) + ". En la propiedad queda(n) " + ((Solar) casilla).getNumeropiscinas() + " piscina(s).");
            } else if (tipo.size() < num_Piscinas)
                throw new VenderExcepcion("Solamente se puede(n) vender " + tipo.size() + " piscina(s), recibiendo " + (tipo.size() * Valores.getVenderPiscinaGrupoCian()));
            else
                throw new VenderExcepcion("No hay piscinas construídas en esta propiedad.");
        } else if (casilla.getGrupo().getNombre().equals("Violeta")) {
            if (tipo.size() >= num_Piscinas && tipo.size() > 0) {
                while (numerosPiscinas < num_Piscinas) {
                    for (Edificio e : ((Solar) casilla).getEdificios()) {
                        if (e instanceof Piscina) {
                            ((Solar) casilla).eliminarEdificio(e);
                            numerosPiscinas++;
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getVenderPiscinaGrupoVioleta());
                            break;
                        }
                    }
                }
                ((Solar) casilla).setNumeropiscinas(tipo.size() - num_Piscinas);
                Juego.consola.imprimirln(jugadorturno.getNombre() + " ha vendido " + num_Piscinas + " piscina(s) en " + casilla.getNombre() + ", recibiendo " + (tipo.size() * Valores.getVenderPiscinaGrupoVioleta()) + ". En la propiedad queda(n) " + ((Solar) casilla).getNumeropiscinas() + " piscina(s).");
            } else if (tipo.size() < num_Piscinas)
                throw new VenderExcepcion("Solamente se puede(n) vender " + tipo.size() + " piscina(s), recibiendo " + (tipo.size() * Valores.getVenderPiscinaGrupoVioleta()));
            else
                throw new VenderExcepcion("No hay piscinas construídas en esta propiedad.");
        } else if (casilla.getGrupo().getNombre().equals("Amarillo")) {
            if (tipo.size() >= num_Piscinas && tipo.size() > 0) {
                while (numerosPiscinas < num_Piscinas) {
                    for (Edificio e : ((Solar) casilla).getEdificios()) {
                        if (e instanceof Piscina) {
                            ((Solar) casilla).eliminarEdificio(e);
                            numerosPiscinas++;
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getVenderPiscinaGrupoAmarillo());
                            break;
                        }
                    }
                }
                ((Solar) casilla).setNumeropiscinas(tipo.size() - num_Piscinas);
                Juego.consola.imprimirln(jugadorturno.getNombre() + " ha vendido " + num_Piscinas + " piscina(s) en " + casilla.getNombre() + ", recibiendo " + (tipo.size() * Valores.getVenderPiscinaGrupoAmarillo()) + ". En la propiedad queda(n) " + ((Solar) casilla).getNumeropiscinas() + " piscina(s).");
            } else if (tipo.size() < num_Piscinas)
                throw new VenderExcepcion("Solamente se puede(n) vender " + tipo.size() + " piscina(s), recibiendo " + (tipo.size() * Valores.getVenderPiscinaGrupoAmarillo()));
            else
                throw new VenderExcepcion("No hay piscinas construídas en esta propiedad.");
        } else if (casilla.getGrupo().getNombre().equals("Rojo")) {
            if (tipo.size() >= num_Piscinas && tipo.size() > 0) {
                while (numerosPiscinas < num_Piscinas) {
                    for (Edificio e : ((Solar) casilla).getEdificios()) {
                        if (e instanceof Piscina) {
                            ((Solar) casilla).eliminarEdificio(e);
                            numerosPiscinas++;
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getVenderPiscinaGrupoRojo());
                            break;
                        }
                    }
                }
                ((Solar) casilla).setNumeropiscinas(tipo.size() - num_Piscinas);
                Juego.consola.imprimirln(jugadorturno.getNombre() + " ha vendido " + num_Piscinas + " piscina(s) en " + casilla.getNombre() + ", recibiendo " + (tipo.size() * Valores.getVenderPiscinaGrupoRojo()) + ". En la propiedad queda(n) " + ((Solar) casilla).getNumeropiscinas() + " piscina(s).");
            } else if (tipo.size() < num_Piscinas)
                throw new VenderExcepcion("Solamente se puede(n) vender " + tipo.size() + " piscina(s), recibiendo " + (tipo.size() * Valores.getVenderPiscinaGrupoRojo()));
            else
                throw new VenderExcepcion("No hay piscinas construídas en esta propiedad.");
        } else if (casilla.getGrupo().getNombre().equals("Gris")) {
            if (tipo.size() >= num_Piscinas && tipo.size() > 0) {
                while (numerosPiscinas < num_Piscinas) {
                    for (Edificio e : ((Solar) casilla).getEdificios()) {
                        if (e instanceof Piscina) {
                            ((Solar) casilla).eliminarEdificio(e);
                            numerosPiscinas++;
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getVenderPiscinaGrupoGris());
                            break;
                        }
                    }
                }
                ((Solar) casilla).setNumeropiscinas(tipo.size() - num_Piscinas);
                Juego.consola.imprimirln(jugadorturno.getNombre() + " ha vendido " + num_Piscinas + " piscina(s) en " + casilla.getNombre() + ", recibiendo " + (tipo.size() * Valores.getVenderPiscinaGrupoGris()) + ". En la propiedad queda(n) " + ((Solar) casilla).getNumeropiscinas() + " piscina(s).");
            } else if (tipo.size() < num_Piscinas)
                throw new VenderExcepcion("Solamente se puede(n) vender " + tipo.size() + " piscina(s), recibiendo " + (tipo.size() * Valores.getVenderPiscinaGrupoGris()));
            else
                throw new VenderExcepcion("No hay piscinas construídas en esta propiedad.");
        } else if (casilla.getGrupo().getNombre().equals("Verde")) {
            if (tipo.size() >= num_Piscinas && tipo.size() > 0) {
                while (numerosPiscinas < num_Piscinas) {
                    for (Edificio e : ((Solar) casilla).getEdificios()) {
                        if (e instanceof Piscina) {
                            ((Solar) casilla).eliminarEdificio(e);
                            numerosPiscinas++;
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getVenderPiscinaGrupoVerde());
                            break;
                        }
                    }
                }
                ((Solar) casilla).setNumeropiscinas(tipo.size() - num_Piscinas);
                Juego.consola.imprimirln(jugadorturno.getNombre() + " ha vendido " + num_Piscinas + " piscina(s) en " + casilla.getNombre() + ", recibiendo " + (tipo.size() * Valores.getVenderPiscinaGrupoVerde()) + ". En la propiedad queda(n) " + ((Solar) casilla).getNumeropiscinas() + " piscina(s).");
            } else if (tipo.size() < num_Piscinas)
                throw new VenderExcepcion("Solamente se puede(n) vender " + tipo.size() + " piscina(s), recibiendo " + (tipo.size() * Valores.getVenderPiscinaGrupoVerde()));
            else
                throw new VenderExcepcion("No hay piscinas construídas en esta propiedad.");
        } else {
            if (tipo.size() >= num_Piscinas && tipo.size() > 0) {
                while (numerosPiscinas < num_Piscinas) {
                    for (Edificio e : ((Solar) casilla).getEdificios()) {
                        if (e instanceof Piscina) {
                            ((Solar) casilla).eliminarEdificio(e);
                            numerosPiscinas++;
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getVenderPiscinaGrupoAzul());
                            break;
                        }
                    }
                }
                ((Solar) casilla).setNumeropiscinas(tipo.size() - num_Piscinas);
                Juego.consola.imprimirln(jugadorturno.getNombre() + " ha vendido " + num_Piscinas + " piscina(s) en " + casilla.getNombre() + ", recibiendo " + (tipo.size() * Valores.getVenderPiscinaGrupoAzul()) + ". En la propiedad queda(n) " + ((Solar) casilla).getNumeropiscinas() + " piscina(s).");
            } else if (tipo.size() < num_Piscinas)
                throw new VenderExcepcion("Solamente se puede(n) vender " + tipo.size() + " piscina(s), recibiendo " + (tipo.size() * Valores.getVenderPiscinaGrupoAzul()));
            else
                throw new VenderExcepcion("No hay piscinas construídas en esta propiedad.");
        }
    }

    public void venderPista(Jugador jugadorturno, Casilla casilla, int num_Pistas) throws VenderExcepcion {
        int numerosPistas = 0;
        ArrayList<Edificio> tipo;
        tipo = new ArrayList<>();
        for (Edificio e : ((Solar) casilla).getEdificios()) {
            if (e instanceof PistaDeporte) {
                tipo.add(e);
            }
        }
        if (casilla.getGrupo().getNombre().equals("Negro")) {
            if (tipo.size() >= num_Pistas && tipo.size() > 0) {
                while (numerosPistas < num_Pistas) {
                    for (Edificio e : ((Solar) casilla).getEdificios()) {
                        if (e instanceof PistaDeporte) {
                            ((Solar) casilla).eliminarEdificio(e);
                            numerosPistas++;
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getVenderPistaDeDeporteGrupoNegro());
                            break;
                        }
                    }
                }
                ((Solar) casilla).setNumeropistas(tipo.size() - num_Pistas);
                Juego.consola.imprimirln(jugadorturno.getNombre() + " ha vendido " + num_Pistas + " pista(s) en " + casilla.getNombre() + ", recibiendo " + (tipo.size() * Valores.getVenderPistaDeDeporteGrupoNegro()) + ". En la propiedad queda(n) " + ((Solar) casilla).getNumeropistas() + " pista(s).");
            } else if (tipo.size() < num_Pistas)
                throw new VenderExcepcion("Solamente se puede(n) vender " + tipo.size() + " pista(s), recibiendo " + (tipo.size() * Valores.getVenderPistaDeDeporteGrupoNegro()));
            else
                throw new VenderExcepcion("No hay pistas construídas en esta propiedad.");
        } else if (casilla.getGrupo().getNombre().equals("Cian")) {
            if (tipo.size() >= num_Pistas && tipo.size() > 0) {
                while (numerosPistas < num_Pistas) {
                    for (Edificio e : ((Solar) casilla).getEdificios()) {
                        if (e instanceof PistaDeporte) {
                            ((Solar) casilla).eliminarEdificio(e);
                            numerosPistas++;
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getVenderPistaDeDeporteGrupoCian());
                            break;
                        }
                    }
                }
                ((Solar) casilla).setNumeropistas(tipo.size() - num_Pistas);
                Juego.consola.imprimirln(jugadorturno.getNombre() + " ha vendido " + num_Pistas + " pista(s) en " + casilla.getNombre() + ", recibiendo " + (tipo.size() * Valores.getVenderPistaDeDeporteGrupoCian()) + ". En la propiedad queda(n) " + ((Solar) casilla).getNumeropistas() + " pista(s).");
            } else if (tipo.size() < num_Pistas)
                throw new VenderExcepcion("Solamente se puede(n) vender " + tipo.size() + " pista(s), recibiendo " + (tipo.size() * Valores.getVenderPistaDeDeporteGrupoCian()));
            else
                throw new VenderExcepcion("No hay pistas construídas en esta propiedad.");
        } else if (casilla.getGrupo().getNombre().equals("Violeta")) {
            if (tipo.size() >= num_Pistas && tipo.size() > 0) {
                while (numerosPistas < num_Pistas) {
                    for (Edificio e : ((Solar) casilla).getEdificios()) {
                        if (e instanceof PistaDeporte) {
                            ((Solar) casilla).eliminarEdificio(e);
                            numerosPistas++;
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getVenderPistaDeDeporteGrupoVioleta());
                            break;
                        }
                    }
                }
                ((Solar) casilla).setNumeropistas(tipo.size() - num_Pistas);
                Juego.consola.imprimirln(jugadorturno.getNombre() + " ha vendido " + num_Pistas + " pista(s) en " + casilla.getNombre() + ", recibiendo " + (tipo.size() * Valores.getVenderPistaDeDeporteGrupoVioleta()) + ". En la propiedad queda(n) " + ((Solar) casilla).getNumeropistas() + " pista(s).");
            } else if (tipo.size() < num_Pistas)
                throw new VenderExcepcion("Solamente se puede(n) vender " + tipo.size() + " pista(s), recibiendo " + (tipo.size() * Valores.getVenderPistaDeDeporteGrupoVioleta()));
            else
                throw new VenderExcepcion("No hay pistas construídas en esta propiedad.");
        } else if (casilla.getGrupo().getNombre().equals("Amarillo")) {
            if (tipo.size() >= num_Pistas && tipo.size() > 0) {
                while (numerosPistas < num_Pistas) {
                    for (Edificio e : ((Solar) casilla).getEdificios()) {
                        if (e instanceof PistaDeporte) {
                            ((Solar) casilla).eliminarEdificio(e);
                            numerosPistas++;
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getVenderPistaDeDeporteGrupoAmarillo());
                            break;
                        }
                    }
                }
                ((Solar) casilla).setNumeropistas(tipo.size() - num_Pistas);
                Juego.consola.imprimirln(jugadorturno.getNombre() + " ha vendido " + num_Pistas + " pista(s) en " + casilla.getNombre() + ", recibiendo " + (tipo.size() * Valores.getVenderPistaDeDeporteGrupoAmarillo()) + ". En la propiedad queda(n) " + ((Solar) casilla).getNumeropistas() + " pista(s).");
            } else if (tipo.size() < num_Pistas)
                throw new VenderExcepcion("Solamente se puede(n) vender " + tipo.size() + " pista(s), recibiendo " + (tipo.size() * Valores.getVenderPistaDeDeporteGrupoAmarillo()));
            else
                throw new VenderExcepcion("No hay pistas construídas en esta propiedad.");
        } else if (casilla.getGrupo().getNombre().equals("Rojo")) {
            if (tipo.size() >= num_Pistas && tipo.size() > 0) {
                while (numerosPistas < num_Pistas) {
                    for (Edificio e : ((Solar) casilla).getEdificios()) {
                        if (e instanceof PistaDeporte) {
                            ((Solar) casilla).eliminarEdificio(e);
                            numerosPistas++;
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getVenderPistaDeDeporteGrupoRojo());
                            break;
                        }
                    }
                }
                ((Solar) casilla).setNumeropistas(tipo.size() - num_Pistas);
                Juego.consola.imprimirln(jugadorturno.getNombre() + " ha vendido " + num_Pistas + " pista(s) en " + casilla.getNombre() + ", recibiendo " + (tipo.size() * Valores.getVenderPistaDeDeporteGrupoRojo()) + ". En la propiedad queda(n) " + ((Solar) casilla).getNumeropistas() + " pista(s).");
            } else if (tipo.size() < num_Pistas)
                throw new VenderExcepcion("Solamente se puede(n) vender " + tipo.size() + " pista(s), recibiendo " + (tipo.size() * Valores.getVenderPistaDeDeporteGrupoRojo()));
            else
                throw new VenderExcepcion("No hay pistas construídas en esta propiedad.");
        } else if (casilla.getGrupo().getNombre().equals("Gris")) {
            if (tipo.size() >= num_Pistas && tipo.size() > 0) {
                while (numerosPistas < num_Pistas) {
                    for (Edificio e : ((Solar) casilla).getEdificios()) {
                        if (e instanceof PistaDeporte) {
                            ((Solar) casilla).eliminarEdificio(e);
                            numerosPistas++;
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getVenderPistaDeDeporteGrupoGris());
                            break;
                        }
                    }
                }
                ((Solar) casilla).setNumeropistas(tipo.size() - num_Pistas);
                Juego.consola.imprimirln(jugadorturno.getNombre() + " ha vendido " + num_Pistas + " pista(s) en " + casilla.getNombre() + ", recibiendo " + (tipo.size() * Valores.getVenderPistaDeDeporteGrupoGris()) + ". En la propiedad queda(n) " + ((Solar) casilla).getNumeropistas() + " pista(s).");
            } else if (tipo.size() < num_Pistas)
                throw new VenderExcepcion("Solamente se puede(n) vender " + tipo.size() + " pista(s), recibiendo " + (tipo.size() * Valores.getVenderPistaDeDeporteGrupoGris()));
            else
                throw new VenderExcepcion("No hay pistas construídas en esta propiedad.");
        } else if (casilla.getGrupo().getNombre().equals("Verde")) {
            if (tipo.size() >= num_Pistas && tipo.size() > 0) {
                while (numerosPistas < num_Pistas) {
                    for (Edificio e : ((Solar) casilla).getEdificios()) {
                        if (e instanceof PistaDeporte) {
                            ((Solar) casilla).eliminarEdificio(e);
                            numerosPistas++;
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getVenderPistaDeDeporteGrupoVerde());
                            break;
                        }
                    }
                }
                ((Solar) casilla).setNumeropistas(tipo.size() - num_Pistas);
                Juego.consola.imprimirln(jugadorturno.getNombre() + " ha vendido " + num_Pistas + " pista(s) en " + casilla.getNombre() + ", recibiendo " + (tipo.size() * Valores.getVenderPistaDeDeporteGrupoVerde()) + ". En la propiedad queda(n) " + ((Solar) casilla).getNumeropistas() + " pista(s).");
            } else if (tipo.size() < num_Pistas)
                throw new VenderExcepcion("Solamente se puede(n) vender " + tipo.size() + " pista(s), recibiendo " + (tipo.size() * Valores.getVenderPistaDeDeporteGrupoVerde()));
            else
                throw new VenderExcepcion("No hay pistas construídas en esta propiedad.");
        } else {
            if (tipo.size() >= num_Pistas && tipo.size() > 0) {
                while (numerosPistas < num_Pistas) {
                    for (Edificio e : ((Solar) casilla).getEdificios()) {
                        if (e instanceof PistaDeporte) {
                            ((Solar) casilla).eliminarEdificio(e);
                            numerosPistas++;
                            jugadorturno.setFortuna(jugadorturno.getFortuna() + Valores.getVenderPistaDeDeporteGrupoAzul());
                            break;
                        }
                    }
                }
                ((Solar) casilla).setNumeropistas(tipo.size() - num_Pistas);
                Juego.consola.imprimirln(jugadorturno.getNombre() + " ha vendido " + num_Pistas + " pista(s) en " + casilla.getNombre() + ", recibiendo " + (tipo.size() * Valores.getVenderPistaDeDeporteGrupoAzul()) + ". En la propiedad queda(n) " + ((Solar) casilla).getNumeropistas() + " pista(s).");
            } else if (tipo.size() < num_Pistas)
                throw new VenderExcepcion("Solamente se puede(n) vender " + tipo.size() + " pista(s), recibiendo " + (tipo.size() * Valores.getVenderPistaDeDeporteGrupoAzul()));
            else
                throw new VenderExcepcion("No hay pistas construídas en esta propiedad.");
        }
    }

    private void Casillas_Grupos() {
        Casilla salida = new Especial("Salida", 0, "RojoN", 0);
        casillas.add(salida);
        Casilla c2 = new Solar("Soria", Valores.VALOR_GRUPO_NEGRO, 1, "Negro", Valores.ALQUILER_GRUPO_NEGRO);
        casillas.add(c2);
        Casilla c3 = new AccionComunidad("Caja", 0, "CianN", 2);
        casillas.add(c3);
        Casilla c4 = new Solar("Palencia", Valores.VALOR_GRUPO_NEGRO, 3, "Negro", Valores.ALQUILER_GRUPO_NEGRO);
        casillas.add(c4);
        Casilla c5 = new Impuesto("ITV", Valores.getValorImpuesto1(), 4, "GrisN");
        casillas.add(c5);
        Casilla c6 = new Transporte("Atocha", Valores.VALOR_TRANSPORTE, 5, "NegroS", Valores.VALOR_TRANSPORTE);
        casillas.add(c6);
        Casilla c7 = new Solar("León", Valores.VALOR_GRUPO_CIAN, 6, "Cian", Valores.ALQUILER_GRUPO_CIAN);
        casillas.add(c7);
        Casilla c8 = new AccionSuerte("Suerte", 0, "VerdeN", 7);
        casillas.add(c8);
        Casilla c9 = new Solar("Ferrol", Valores.VALOR_GRUPO_CIAN, 8, "Cian", Valores.ALQUILER_GRUPO_CIAN);
        casillas.add(c9);
        Casilla c10 = new Solar("ACoruña", Valores.VALOR_GRUPO_CIAN, 9, "Cian", Valores.ALQUILER_GRUPO_CIAN);
        casillas.add(c10);
        Casilla carcel = new Especial("Cárcel", 0, "AmarilloN", 10);
        casillas.add(carcel);
        Casilla c12 = new Solar("Ourense", Valores.VALOR_GRUPO_VIOLETA, 11, "Violeta", Valores.ALQUILER_GRUPO_VIOLETA);
        casillas.add(c12);
        Casilla c13 = new Servicio("Luz", Valores.VALOR_SERVICIO, 12, "Negro", Valores.FACTOR_SERVICIO);
        casillas.add(c13);
        Casilla c14 = new Solar("Pontevedra", Valores.VALOR_GRUPO_VIOLETA, 13, "Violeta", Valores.ALQUILER_GRUPO_VIOLETA);
        casillas.add(c14);
        Casilla c15 = new Solar("Lugo", Valores.VALOR_GRUPO_VIOLETA, 14, "Violeta", Valores.ALQUILER_GRUPO_VIOLETA);
        casillas.add(c15);
        Casilla c16 = new Transporte("Peinador", Valores.VALOR_TRANSPORTE, 15, "NegroS", Valores.VALOR_TRANSPORTE);
        casillas.add(c16);
        Casilla c17 = new Solar("Vigo", Valores.VALOR_GRUPO_AMARILLO, 16, "Amarillo", Valores.ALQUILER_GRUPO_AMARILLO);
        casillas.add(c17);
        Casilla c18 = new AccionComunidad("Caja", 0, "CianN", 17);
        casillas.add(c18);
        Casilla c19 = new Solar("Santiago", Valores.VALOR_GRUPO_AMARILLO, 18, "Amarillo", Valores.ALQUILER_GRUPO_AMARILLO);
        casillas.add(c19);
        Casilla c20 = new Solar("Valencia", Valores.VALOR_GRUPO_AMARILLO, 19, "Amarillo", Valores.ALQUILER_GRUPO_AMARILLO);
        casillas.add(c20);
        Casilla parking = new Especial("Parking", 0, "VioletaN", 20);
        casillas.add(parking);
        Casilla c22 = new Solar("Barcelona", Valores.VALOR_GRUPO_ROJO, 21, "Rojo", Valores.ALQUILER_GRUPO_ROJO);
        casillas.add(c22);
        Casilla c23 = new AccionSuerte("Suerte", 0, "VerdeN", 22);
        casillas.add(c23);
        Casilla c24 = new Solar("Oviedo", Valores.VALOR_GRUPO_ROJO, 23, "Rojo", Valores.ALQUILER_GRUPO_ROJO);
        casillas.add(c24);
        Casilla c25 = new Solar("Ponferrada", Valores.VALOR_GRUPO_ROJO, 24, "Rojo", Valores.ALQUILER_GRUPO_ROJO);
        casillas.add(c25);
        Casilla c26 = new Transporte("Celeiro", Valores.VALOR_TRANSPORTE, 25, "NegroS", Valores.VALOR_TRANSPORTE);
        casillas.add(c26);
        Casilla c27 = new Solar("Valladolid", Valores.VALOR_GRUPO_GRIS, 26, "Gris", Valores.ALQUILER_GRUPO_GRIS);
        casillas.add(c27);
        Casilla c28 = new Solar("Castellón", Valores.VALOR_GRUPO_GRIS, 27, "Gris", Valores.ALQUILER_GRUPO_GRIS);
        casillas.add(c28);
        Casilla c29 = new Servicio("Agua", Valores.VALOR_SERVICIO, 28, "Negro", Valores.FACTOR_SERVICIO);
        casillas.add(c29);
        Casilla c30 = new Solar("Madrid", Valores.VALOR_GRUPO_GRIS, 29, "Gris", Valores.ALQUILER_GRUPO_GRIS);
        casillas.add(c30);
        Casilla ir_carcel = new Especial("IrACárcel", 0, "AzulN", 30);
        casillas.add(ir_carcel);
        Casilla c32 = new Solar("Sevilla", Valores.VALOR_GRUPO_VERDE, 31, "Verde", Valores.ALQUILER_GRUPO_VERDE);
        casillas.add(c32);
        Casilla c33 = new Solar("Málaga", Valores.VALOR_GRUPO_VERDE, 32, "Verde", Valores.ALQUILER_GRUPO_VERDE);
        casillas.add(c33);
        Casilla c34 = new AccionComunidad("Caja", 0, "CianN", 33);
        casillas.add(c34);
        Casilla c35 = new Solar("Mallorca", Valores.VALOR_GRUPO_VERDE, 34, "Verde", Valores.ALQUILER_GRUPO_VERDE);
        casillas.add(c35);
        Casilla c36 = new Transporte("Monbus", Valores.VALOR_TRANSPORTE, 35, "NegroS", Valores.VALOR_TRANSPORTE);
        casillas.add(c36);
        Casilla c37 = new AccionSuerte("Suerte", 0, "VerdeN", 36);
        casillas.add(c37);
        Casilla c38 = new Solar("Tenerife", Valores.VALOR_GRUPO_AZUL, 37, "Azul", Valores.ALQUILER_GRUPO_AZUL);
        casillas.add(c38);
        Casilla c39 = new Impuesto("IVA", Valores.getValorImpuesto2(), 38, "GrisN");
        casillas.add(c39);
        Casilla c40 = new Solar("Albacete", Valores.VALOR_GRUPO_AZUL, 39, "Azul", Valores.ALQUILER_GRUPO_AZUL);
        casillas.add(c40);

        banca = new Jugador();
        for (Casilla c : casillas) {
            if (c instanceof Propiedad) {
                banca.anadirPropiedad((Propiedad) c);
            }
        }

        Grupo grupoNegro = new Grupo("Negro", "Negro");
        grupoNegro.anadirGrupo(c2);
        grupoNegro.anadirGrupo(c4);
        casillas.get(1).setGrupo(grupoNegro);
        casillas.get(3).setGrupo(grupoNegro);
        Grupo grupoCian = new Grupo("Cian", "Cian");
        grupoCian.anadirGrupo(c7);
        grupoCian.anadirGrupo(c9);
        grupoCian.anadirGrupo(c10);
        casillas.get(6).setGrupo(grupoCian);
        casillas.get(8).setGrupo(grupoCian);
        casillas.get(9).setGrupo(grupoCian);
        Grupo grupoVioleta = new Grupo("Violeta", "Violeta");
        grupoVioleta.anadirGrupo(c12);
        grupoVioleta.anadirGrupo(c14);
        grupoVioleta.anadirGrupo(c15);
        casillas.get(11).setGrupo(grupoVioleta);
        casillas.get(13).setGrupo(grupoVioleta);
        casillas.get(14).setGrupo(grupoVioleta);
        Grupo grupoAmarillo = new Grupo("Amarillo", "Amarillo");
        grupoAmarillo.anadirGrupo(c17);
        grupoAmarillo.anadirGrupo(c19);
        grupoAmarillo.anadirGrupo(c20);
        casillas.get(16).setGrupo(grupoAmarillo);
        casillas.get(18).setGrupo(grupoAmarillo);
        casillas.get(19).setGrupo(grupoAmarillo);
        Grupo grupoRojo = new Grupo("Rojo", "Rojo");
        grupoRojo.anadirGrupo(c22);
        grupoRojo.anadirGrupo(c24);
        grupoRojo.anadirGrupo(c25);
        casillas.get(21).setGrupo(grupoRojo);
        casillas.get(23).setGrupo(grupoRojo);
        casillas.get(24).setGrupo(grupoRojo);
        Grupo grupoGris = new Grupo("Gris", "Gris");
        grupoGris.anadirGrupo(c27);
        grupoGris.anadirGrupo(c28);
        grupoGris.anadirGrupo(c30);
        casillas.get(26).setGrupo(grupoGris);
        casillas.get(27).setGrupo(grupoGris);
        casillas.get(29).setGrupo(grupoGris);
        Grupo grupoVerde = new Grupo("Verde", "Verde");
        grupoVerde.anadirGrupo(c32);
        grupoVerde.anadirGrupo(c33);
        grupoVerde.anadirGrupo(c35);
        casillas.get(31).setGrupo(grupoVerde);
        casillas.get(32).setGrupo(grupoVerde);
        casillas.get(34).setGrupo(grupoVerde);
        Grupo grupoAzul = new Grupo("Azul", "Azul");
        grupoAzul.anadirGrupo(c38);
        grupoAzul.anadirGrupo(c40);
        casillas.get(37).setGrupo(grupoAzul);
        casillas.get(39).setGrupo(grupoAzul);

        grupos.add(grupoNegro);
        grupos.add(grupoCian);
        grupos.add(grupoVioleta);
        grupos.add(grupoAmarillo);
        grupos.add(grupoRojo);
        grupos.add(grupoGris);
        grupos.add(grupoVerde);
        grupos.add(grupoAzul);
    }

    public void salir_carcel(Jugador jugador) throws SinDinero {
        if (jugador.getFortuna() >= Valores.SALIR_CARCEL) {
            Juego.consola.imprimirln("Has salido de la cárcel pagando " + Valores.SALIR_CARCEL);
            jugador.getAvatar().setJugadorcarcel(false);
            jugador.setFortuna(jugador.getFortuna() - Valores.SALIR_CARCEL);
        } else
            throw new SinDinero("No tienes dinero suficiente para salir de la cárcel. Acompañas tus amigos políticos otro turno.");
    }

    private void pagarTrasTresTurnosCarcel(Avatar avatartirada) throws SinDinero {
        double fortunaactual;
        Juego.consola.imprimirln("Ya han pasado tres turnos. Deberás pagar " + Valores.getSalirCarcel() + " por salir de la cárcel.");
        if (avatartirada.getJugador().getFortuna() >= Valores.SALIR_CARCEL) {
            fortunaactual = avatartirada.getJugador().getFortuna() - Valores.SALIR_CARCEL;
            avatartirada.getJugador().setFortuna(fortunaactual);
            avatartirada.setJugadorcarcel(false);
        } else
            throw new SinDinero("No tienes dinero suficiente para salir de la cárcel. Acompañas tus amigos políticos otro turno.");
    }

    private void doblesTresVeces(Avatar avatartirada) {
        Juego.consola.imprimirln("Has sacado dobles tres veces. Vas a la cárcel.");
        avatartirada.getJugador().setVecesEnLaCarcel(avatartirada.getJugador().getVecesEnLaCarcel() + 1);
        this.casillas.get(avatartirada.getPosicion()).eliminarAvatar(avatartirada);
        avatartirada.setPosicion(10);
        casillas.get(10).anadirAvatar(avatartirada);
        getDado().setPuedolanzar(false);
        avatartirada.setJugadorcarcel(true);
        avatartirada.getCasillaAvatar().setVisitacasillas(avatartirada.getCasillaAvatar().getVisitacasillas() + 1);
    }

    public void crear_array_jugadores() {
        for (Casilla c : casillas) {
            if (c.getAvatar() != null) {
                for (Avatar a : c.getAvatar()) {
                    jugadores.add(a.getJugador());
                }
            }
        }
        setJugadorescreados(true);
    }

    private void AccionesCasillas(Avatar avatartirada) throws SinDinero {
        if (casillas.get(30).getNombre().equals(avatartirada.getCasillaAvatar().getNombre())) {
            Juego.consola.imprimirln("Has caído en ir a cárcel.");
            avatartirada.getJugador().setVecesEnLaCarcel(avatartirada.getJugador().getVecesEnLaCarcel() + 1);
            avatartirada.setPosicion(10);
            casillas.get(10).anadirAvatar(avatartirada);
            avatartirada.setJugadorcarcel(true);
            this.casillas.get(30).eliminarAvatar(avatartirada);
            getDado().setPuedolanzar(false);
            Juego.consola.imprimirln("Vas a la cárcel.");
            avatartirada.getCasillaAvatar().setVisitacasillas(avatartirada.getCasillaAvatar().getVisitacasillas() + 1);
        } else if (casillas.get(20).getNombre().equals(avatartirada.getCasillaAvatar().getNombre())) {
            avatartirada.getJugador().setPremiosInversionesOBote(avatartirada.getJugador().getPremiosInversionesOBote() + getCasillas().get(20).getCoste());
            avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + getCasillas().get(20).getCoste());
            if (getCasillas().get(20).getCoste() != 0) {
                Juego.consola.imprimirln("Has caído en el Párking. Recibes del bote " + getCasillas().get(20).getCoste());
                if (avatartirada instanceof Esfinge && ((Esfinge) avatartirada).isMovimientoespecialesfinge()) {
                    ((Esfinge) avatartirada).setCobraBote(true);
                    ((Esfinge) avatartirada).setDineroUltimoBote(getCasillas().get(20).getCoste());
                }
                if (avatartirada instanceof Sombrero && ((Sombrero) avatartirada).isMovimientoespecialsombrero()) {
                    ((Sombrero) avatartirada).setCobraBote(true);
                    ((Sombrero) avatartirada).setDineroUltimoBote(getCasillas().get(20).getCoste());
                }
            } else
                Juego.consola.imprimirln("Has caído en " + getCasillas().get(20).getNombre() + ".");
            getCasillas().get(20).setCoste(0);
            casillas.get(20).anadirAvatar(avatartirada);
            avatartirada.getCasillaAvatar().setVisitacasillas(avatartirada.getCasillaAvatar().getVisitacasillas() + 1);
        } else if (casillas.get(4).getNombre().equals(avatartirada.getCasillaAvatar().getNombre())) {
            Juego.consola.imprimirln("Has caído en " + getCasillas().get(4).getNombre() + " Paga la cantidad de " + getCasillas().get(4).getCoste());
            if (avatartirada.getJugador().getFortuna() >= getCasillas().get(4).getCoste()) {
                avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() - getCasillas().get(4).getCoste());
                avatartirada.getJugador().setPagoTasasEImpuestos(avatartirada.getJugador().getPagoTasasEImpuestos() + getCasillas().get(4).getCoste());
                getCasillas().get(20).setCoste(getCasillas().get(20).getCoste() + getCasillas().get(4).getCoste());
                casillas.get(4).anadirAvatar(avatartirada);
                avatartirada.getCasillaAvatar().setVisitacasillas(avatartirada.getCasillaAvatar().getVisitacasillas() + 1);
                if (avatartirada instanceof Esfinge && ((Esfinge) avatartirada).isMovimientoespecialesfinge()) {
                    ((Esfinge) avatartirada).setCobraImpuesto(true);
                    ((Esfinge) avatartirada).setDineroUltimoImpuesto(getCasillas().get(4).getCoste());
                }
                if (avatartirada instanceof Sombrero && ((Sombrero) avatartirada).isMovimientoespecialsombrero()) {
                    ((Sombrero) avatartirada).setCobraImpuesto(true);
                    ((Sombrero) avatartirada).setDineroUltimoImpuesto(getCasillas().get(4).getCoste());
                }
            } else
                throw new SinDinero("No tienes dinero suficiente para pagar " + casillas.get(4).getNombre() + ". Rajoy se ha enfadado.");
        } else if (casillas.get(38).getNombre().equals(avatartirada.getCasillaAvatar().getNombre())) {
            Juego.consola.imprimirln("Has caído en " + getCasillas().get(38).getNombre() + " Paga la cantidad de " + getCasillas().get(38).getCoste());
            if (avatartirada.getJugador().getFortuna() >= getCasillas().get(38).getCoste()) {
                avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() - getCasillas().get(38).getCoste());
                avatartirada.getJugador().setPagoTasasEImpuestos(avatartirada.getJugador().getPagoTasasEImpuestos() + getCasillas().get(38).getCoste());
                getCasillas().get(20).setCoste(getCasillas().get(20).getCoste() + getCasillas().get(38).getCoste());
                casillas.get(38).anadirAvatar(avatartirada);
                avatartirada.getCasillaAvatar().setVisitacasillas(avatartirada.getCasillaAvatar().getVisitacasillas() + 1);
                if (avatartirada instanceof Esfinge && ((Esfinge) avatartirada).isMovimientoespecialesfinge()) {
                    ((Esfinge) avatartirada).setCobraImpuesto(true);
                    ((Esfinge) avatartirada).setDineroUltimoImpuesto(getCasillas().get(38).getCoste());
                }
                if (avatartirada instanceof Sombrero && ((Sombrero) avatartirada).isMovimientoespecialsombrero()) {
                    ((Sombrero) avatartirada).setCobraImpuesto(true);
                    ((Sombrero) avatartirada).setDineroUltimoImpuesto(getCasillas().get(38).getCoste());
                }
            } else
                throw new SinDinero("No tienes dinero suficiente para pagar " + casillas.get(38).getNombre() + ". Rajoy se ha enfadado.");
        } else if (casillas.get(2).getNombre().equals(avatartirada.getCasillaAvatar().getNombre()) || casillas.get(17).getNombre().equals(avatartirada.getCasillaAvatar().getNombre()) || casillas.get(33).getNombre().equals(avatartirada.getCasillaAvatar().getNombre()) && avatartirada.getCasillaAvatar() instanceof AccionComunidad) {
            ((AccionComunidad) avatartirada.getCasillaAvatar()).eleccioncomunidad(this, avatartirada);
            if (avatartirada instanceof Esfinge)
                ((Esfinge) avatartirada).setCobraCarta(true);
            else if (avatartirada instanceof Sombrero)
                ((Sombrero) avatartirada).setCobraCarta(true);
        } else if (casillas.get(7).getNombre().equals(avatartirada.getCasillaAvatar().getNombre()) || casillas.get(22).getNombre().equals(avatartirada.getCasillaAvatar().getNombre()) || casillas.get(36).getNombre().equals(avatartirada.getCasillaAvatar().getNombre()) && avatartirada.getCasillaAvatar() instanceof AccionSuerte) {
            ((AccionSuerte) avatartirada.getCasillaAvatar()).eleccionsuerte(this, avatartirada);
            if (avatartirada instanceof Esfinge)
                ((Esfinge) avatartirada).setCobraCarta(true);
            else if (avatartirada instanceof Sombrero)
                ((Sombrero) avatartirada).setCobraCarta(true);
        } else {
            Juego.consola.imprimirln("Has caído en " + avatartirada.getCasillaAvatar().getNombre());
            casillas.get(avatartirada.getPosicion()).anadirAvatar(avatartirada);
            avatartirada.getCasillaAvatar().setVisitacasillas(avatartirada.getCasillaAvatar().getVisitacasillas() + 1);
        }
        comprobarGrupoEntero(avatartirada);
    }

    private void posicionEnRangoAvance(int posicion, Avatar avatartirada) {
        if (posicion > 39) {
            avatartirada.getJugador().setPasarPorCasillaDeSalida(avatartirada.getJugador().getPasarPorCasillaDeSalida() + Valores.VUELTA_COMPLETA);
            avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() + Valores.VUELTA_COMPLETA);
            avatartirada.setPosicion(posicion - 40);
            avatartirada.setCasillaAvatar(casillas.get(avatartirada.getPosicion()));
            vueltasAlTablero(avatartirada);
        } else {
            avatartirada.setPosicion(posicion);
            avatartirada.setCasillaAvatar(casillas.get(avatartirada.getPosicion()));
        }
    }

    private void vueltasAlTablero(Avatar avatartirada) {
        Juego.consola.imprimirln("Has dado una vuelta al tablero. Recibes " + Valores.getVueltaCompleta());
        if (avatartirada.getJugador().getCuentavueltas() == 4) {
            for (Casilla c : casillas) {
                if (c instanceof Solar) {
                    if (c.getGrupo().getNombre().equals("Negro")) {
                        c.setCoste(Valores.VALOR_GRUPO_NEGRO * 1.05);
                    } else if (c.getGrupo().getNombre().equals("Cian")) {
                        c.setCoste(Valores.VALOR_GRUPO_CIAN * 1.05);
                    } else if (c.getGrupo().getNombre().equals("Violeta")) {
                        c.setCoste(Valores.VALOR_GRUPO_CIAN * 1.05);
                    } else if (c.getGrupo().getNombre().equals("Amarillo")) {
                        c.setCoste(Valores.VALOR_GRUPO_CIAN * 1.05);
                    } else if (c.getGrupo().getNombre().equals("Rojo")) {
                        c.setCoste(Valores.VALOR_GRUPO_CIAN * 1.05);
                    } else if (c.getGrupo().getNombre().equals("Gris")) {
                        c.setCoste(Valores.VALOR_GRUPO_CIAN * 1.05);
                    } else if (c.getGrupo().getNombre().equals("Verde")) {
                        c.setCoste(Valores.VALOR_GRUPO_CIAN * 1.05);
                    } else if (c.getGrupo().getNombre().equals("Azul")) {
                        c.setCoste(Valores.VALOR_GRUPO_CIAN * 1.05);
                    }
                }
            }
            avatartirada.getJugador().setCuentavueltas(0);
        } else
            avatartirada.getJugador().setCuentavueltas(avatartirada.getJugador().getCuentavueltas() + 1);
    }

    private void posicionEnRangoRetroceso(int posicion, Avatar avatartirada) {
        if (posicion < 0) {
            posicion += 40;
            avatartirada.getJugador().setCuentavueltas(avatartirada.getJugador().getCuentavueltas() - 1);
        }
        avatartirada.setCasillaAvatar(casillas.get(posicion));
        avatartirada.setPosicion(posicion);
    }

    private void permiteConstruir(Avatar avatartirada) {
        for (int i = 0; i < jugadores.size(); i++) {
            if (avatartirada.getJugador().getNombre().equals(jugadores.get(i).getNombre())) {
                avatartirada.getCasillaAvatar().frecuenciaVisita(i);
                if (avatartirada.getCasillaAvatar() instanceof Solar) {
                    if (((Solar) avatartirada.getCasillaAvatar()).getPropietario() != null) {
                        if (avatartirada.getCasillaAvatar().getVecesencasilla().get(i) > 2 && ((Solar) avatartirada.getCasillaAvatar()).getPropietario().getNombre().equals(avatartirada.getJugador().getNombre()) && ((Solar) avatartirada.getCasillaAvatar()).getPropietario() != null) {
                            ((Solar) avatartirada.getCasillaAvatar()).setPuedoconstruircasa(true);
                        }
                    }
                }
            }
        }
    }

    private void propiedadesPelota(Avatar avatartirada) throws SinDinero {
        if (avatartirada.getCasillaAvatar() instanceof Propiedad) {
            if (((Propiedad) getCasillas().get(avatartirada.getCasillaAvatar().getPosicion())).getPropietario() != null && !((Propiedad) getCasillas().get(avatartirada.getCasillaAvatar().getPosicion())).getPropietario().getNombre().equals(avatartirada.getJugador().getNombre()))
                ((Propiedad) avatartirada.getCasillaAvatar()).Alquiler(avatartirada);
            else {
                if (getCasillas().get(avatartirada.getCasillaAvatar().getPosicion()) instanceof Solar && ((Solar) getCasillas().get(avatartirada.getCasillaAvatar().getPosicion())).getPropietario() != null && ((Solar) getCasillas().get(avatartirada.getCasillaAvatar().getPosicion())).getPropietario().getNombre().equals(avatartirada.getJugador().getNombre())) {
                    Juego.consola.imprimirln("Ya tienes la casilla " + avatartirada.getCasillaAvatar().getNombre() + " en propiedad.");
                } else if (getCasillas().get(avatartirada.getCasillaAvatar().getPosicion()) instanceof Transporte && ((Transporte) getCasillas().get(avatartirada.getCasillaAvatar().getPosicion())).getPropietario() != null && ((Transporte) getCasillas().get(avatartirada.getCasillaAvatar().getPosicion())).getPropietario().getNombre().equals(avatartirada.getJugador().getNombre())) {
                    Juego.consola.imprimirln("Ya tienes la casilla " + avatartirada.getCasillaAvatar().getNombre() + " en propiedad.");
                } else if (getCasillas().get(avatartirada.getCasillaAvatar().getPosicion()) instanceof Servicio && ((Servicio) getCasillas().get(avatartirada.getCasillaAvatar().getPosicion())).getPropietario() != null && ((Servicio) getCasillas().get(avatartirada.getCasillaAvatar().getPosicion())).getPropietario().getNombre().equals(avatartirada.getJugador().getNombre())) {
                    Juego.consola.imprimirln("Ya tienes la casilla " + avatartirada.getCasillaAvatar().getNombre() + " en propiedad.");
                } else if (getCasillas().get(avatartirada.getCasillaAvatar().getPosicion()) instanceof Solar && ((Solar) getCasillas().get(avatartirada.getCasillaAvatar().getPosicion())).getPropietario() == null) {
                    String opcion;
                    do {
                        opcion = Juego.consola.leer("Deseas comprar la casilla " + avatartirada.getCasillaAvatar().getNombre() + "? (s/n) ");
                        switch (opcion) {
                            case "s":
                                if (avatartirada.getJugador().getFortuna() >= avatartirada.getCasillaAvatar().getCoste()) {
                                    avatartirada.getJugador().anadirPropiedad(((Propiedad) avatartirada.getCasillaAvatar()));
                                    ((Solar) avatartirada.getCasillaAvatar()).setPropietario(avatartirada.getJugador());
                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() - avatartirada.getCasillaAvatar().getCoste());
                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() + avatartirada.getCasillaAvatar().getCoste());
                                    getBanca().eliminarPropiedad((Solar) avatartirada.getCasillaAvatar());
                                    Juego.consola.imprimirln("El jugador " + avatartirada.getJugador().getNombre() + " compra la casilla " + avatartirada.getCasillaAvatar().getNombre() + " por " + avatartirada.getCasillaAvatar().getCoste() + ". Su fortuna actual es " + avatartirada.getJugador().getFortuna());
                                    comprobarGrupoEntero(avatartirada);
                                } else
                                    throw new SinDinero("No tienes dinero suficiente para comprar la casilla.");
                                break;
                            case "n":
                                Juego.consola.imprimirln("No has comprado " + avatartirada.getCasillaAvatar().getNombre());
                                break;
                            default:
                                Juego.consola.imprimirln("No es una opción correcta. Intentálo de nuevo.");
                                break;
                        }
                    } while (!opcion.equals("s") && !opcion.equals("n"));
                } else if (getCasillas().get(avatartirada.getCasillaAvatar().getPosicion()) instanceof Transporte && ((Transporte) getCasillas().get(avatartirada.getCasillaAvatar().getPosicion())).getPropietario() == null) {
                    String opcion;
                    do {
                        opcion = Juego.consola.leer("Deseas comprar la casilla " + avatartirada.getCasillaAvatar().getNombre() + "? (s/n) ");
                        switch (opcion) {
                            case "s":
                                if (avatartirada.getJugador().getFortuna() >= avatartirada.getCasillaAvatar().getCoste()) {
                                    avatartirada.getJugador().anadirPropiedad(((Propiedad) avatartirada.getCasillaAvatar()));
                                    ((Transporte) avatartirada.getCasillaAvatar()).setPropietario(avatartirada.getJugador());
                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() - avatartirada.getCasillaAvatar().getCoste());
                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() + avatartirada.getCasillaAvatar().getCoste());
                                    getBanca().eliminarPropiedad((Transporte) avatartirada.getCasillaAvatar());
                                    Juego.consola.imprimirln("El jugador " + avatartirada.getJugador().getNombre() + " compra la casilla " + avatartirada.getCasillaAvatar().getNombre() + " por " + avatartirada.getCasillaAvatar().getCoste() + ". Su fortuna actual es " + avatartirada.getJugador().getFortuna());
                                    comprobarGrupoEntero(avatartirada);
                                } else
                                    throw new SinDinero("No tienes dinero suficiente para comprar la casilla.");
                                break;
                            case "n":
                                Juego.consola.imprimirln("No has comprado " + avatartirada.getCasillaAvatar().getNombre());
                                break;
                            default:
                                Juego.consola.imprimirln("No es una opción correcta. Intentálo de nuevo.");
                                break;
                        }
                    } while (!opcion.equals("s") && !opcion.equals("n"));
                } else if (getCasillas().get(avatartirada.getCasillaAvatar().getPosicion()) instanceof Servicio && ((Servicio) getCasillas().get(avatartirada.getCasillaAvatar().getPosicion())).getPropietario() == null) {
                    String opcion;
                    do {
                        opcion = Juego.consola.leer("Deseas comprar la casilla " + avatartirada.getCasillaAvatar().getNombre() + "? (s/n) ");
                        switch (opcion) {
                            case "s":
                                if (avatartirada.getJugador().getFortuna() >= avatartirada.getCasillaAvatar().getCoste()) {
                                    avatartirada.getJugador().anadirPropiedad(((Propiedad) avatartirada.getCasillaAvatar()));
                                    ((Servicio) avatartirada.getCasillaAvatar()).setPropietario(avatartirada.getJugador());
                                    avatartirada.getJugador().setFortuna(avatartirada.getJugador().getFortuna() - avatartirada.getCasillaAvatar().getCoste());
                                    avatartirada.getJugador().setDineroGastado(avatartirada.getJugador().getDineroGastado() + avatartirada.getCasillaAvatar().getCoste());
                                    getBanca().eliminarPropiedad((Servicio) avatartirada.getCasillaAvatar());
                                    Juego.consola.imprimirln("El jugador " + avatartirada.getJugador().getNombre() + " compra la casilla " + avatartirada.getCasillaAvatar().getNombre() + " por " + avatartirada.getCasillaAvatar().getCoste() + ". Su fortuna actual es " + avatartirada.getJugador().getFortuna());
                                    comprobarGrupoEntero(avatartirada);
                                } else
                                    throw new SinDinero("No tienes dinero suficiente para comprar la casilla.");
                                break;
                            case "n":
                                Juego.consola.imprimirln("No has comprado " + avatartirada.getCasillaAvatar().getNombre());
                                break;
                            default:
                                Juego.consola.imprimirln("No es una opción correcta. Intentálo de nuevo.");
                                break;
                        }
                    } while (!opcion.equals("s") && !opcion.equals("n"));
                }
            }
        }
    }

    @Override
    public String toString() {
        int i;
        String tablero = "";
        tablero += "|";
        for (i = 20; i <= 30; i++) {
            tablero += casillas.get(i);
        }
        tablero += "\n|" + casillas.get(19) + "     \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|" + casillas.get(31);
        tablero += "\n|" + casillas.get(18) + "     \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|" + casillas.get(32);
        tablero += "\n|" + casillas.get(17) + "     \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|" + casillas.get(33);
        tablero += "\n|" + casillas.get(16) + "     \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|" + casillas.get(34);
        tablero += "\n|" + casillas.get(15) + "     \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|" + casillas.get(35);
        tablero += "\n|" + casillas.get(14) + "     \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|" + casillas.get(36);
        tablero += "\n|" + casillas.get(13) + "     \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|" + casillas.get(37);
        tablero += "\n|" + casillas.get(12) + "     \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|" + casillas.get(38);
        tablero += "\n|" + casillas.get(11) + "     \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|" + casillas.get(39);
        tablero += "\n|";
        for (i = 10; i >= 0; i--) {
            tablero += casillas.get(i);
        }
        return tablero;
    }
}