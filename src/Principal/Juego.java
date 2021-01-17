package Principal;

import Casillas.*;
import Consola.ConsolaNormal;
import Edificios.*;
import Excepciones.CasillaExcepcion.Compra.NoComprarCoche;
import Excepciones.CasillaExcepcion.Compra.NoEstarEnCasilla;
import Excepciones.CasillaExcepcion.Compra.NoPropiedad;
import Excepciones.CasillaExcepcion.Compra.SinDinero;
import Excepciones.CasillaExcepcion.Edificar.ImposibilidadEdificar;
import Excepciones.CasillaExcepcion.Edificar.NoPropietario;
import Excepciones.CasillaExcepcion.Edificar.NoSolar;
import Excepciones.CasillaExcepcion.Hipotecas.ConEdificios;
import Excepciones.CasillaExcepcion.Hipotecas.DeshipotecarExcepcion;
import Excepciones.CasillaExcepcion.Hipotecas.ImposibilidadHipotecar;
import Excepciones.CasillaExcepcion.Hipotecas.VenderExcepcion;
import Excepciones.Excepcion;
import Excepciones.Generales.*;
import Jugadores.*;
import Tirada.Tablero;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Juego implements Comando {
    public static final ConsolaNormal consola = new ConsolaNormal();
    private ArrayList<Jugador> jugadores;
    private Jugador jugadorturno;
    private Tablero tablero;
    private ArrayList<Avatar> avatar;
    private int turno;
    private int dollar;
    private boolean nocrearjugador;
    private ArrayList<Jugador> jugadorestrato;
    private ArrayList<Trato> trato;
    private int trato_creado;

    public Juego() {
        tablero = new Tablero();
        jugadores = new ArrayList<>();
        avatar = new ArrayList<>();
        turno = 0;
        dollar = 0;
        jugadorestrato = new ArrayList<>();
        trato = new ArrayList<>();
        this.trato_creado = 0;
        BufferedReader buffRead = null;
        try {
            String directorio = "D:\\Pablo\\Trabajos GrEI\\Programacion Orientada a Objetos\\Monopoly\\";
            FileReader fileRead = new FileReader(directorio + "comandos.txt");
            buffRead = new BufferedReader(fileRead);
        } catch (FileNotFoundException notFound) {
            consola.imprimir(notFound.getMessage());
            System.exit(0);
        }

        String opcion = consola.leer("¿Desea ejecutar en modo manual o automático (m/a)?\n$> ");

        while (true) {
            switch (opcion) {
                case "m":
                    if (dollar == 0)
                        consola.imprimir("$> ");
                    else
                        consola.imprimir("\n$> ");
                    dollar++;
                    String orden = consola.leer("");
                    String[] partes = orden.split(" ");
                    opcionesMenu(partes);
                    break;
                case "a":
                    orden = "";
                    try {
                        orden = buffRead.readLine();
                        consola.imprimir("\n$> ");
                        consola.imprimir(orden + "\n");

                    } catch (IOException io) {
                        consola.imprimir(io.getMessage());
                    }
                    partes = orden.split(" ");
                    opcionesMenu(partes);
                    break;
            }
        }
    }

    private static boolean SePuedeCambiar(String entrada) {
        try {
            Integer.parseInt(entrada);
            return true;
        } catch (NumberFormatException excepcion) {
            return false;
        }
    }

    private void opcionesMenu(String[] partes) {
        String comando0 = partes[0];
        try {
            switch (comando0) {
                case "crear":
                    crear(partes);
                    break;
                case "jugador":
                    jugador(partes);
                    break;
                case "listar":
                    listar(partes);
                    break;
                case "lanzar":
                    lanzar(partes);
                    break;
                case "comprar":
                    comprar(partes);
                    break;
                case "acabar":
                    acabar(partes);
                    break;
                case "describir":
                    describir(partes);
                    break;
                case "ver":
                    ver(partes);
                    break;
                case "edificar":
                    edificar(partes);
                    break;
                case "hipotecar":
                    hipotecar(partes);
                    break;
                case "deshipotecar":
                    deshipotecar(partes);
                    break;
                case "vender":
                    vender(partes);
                    break;
                case "trato":
                    trato(partes);
                    break;
                case "aceptar":
                    aceptar(partes);
                    break;
                case "tratos":
                    tratos(partes);
                    break;
                case "eliminar":
                    eliminar(partes);
                    break;
                case "estadisticas":
                    estadisticas(partes);
                    break;
                case "cambiar":
                    cambiar(partes);
                    break;
                case "salir":
                    salir(partes);
                    break;
                case "stop":
                    stop(partes);
                    break;
                default:
                    consola.imprimir("\nComando incorrecto.");
                    break;
            }
        } catch (Excepcion excepcion) {
            consola.imprimir(excepcion.getMessage());
        }
    }

    public void crear(String[] partes) throws Excepcion {
        if (partes.length == 4) {
            if (partes[1].equals("jugador") || partes[1].equals("jugadora")) {
                if (!nocrearjugador) {
                    if (jugadores.size() < 6) {
                        creacionJugadores(partes[2], partes[3]);
                    } else
                        throw new CreacionExcepcion("No puedes crear más jugadores. Ya has creado el máximo.");
                } else
                    throw new CreacionExcepcion("Ya no puedes crear jugadores, pues has comenzado el juego.");
            } else
                throw new ComandoInexistente("Comando incorrecto.");
        } else
            throw new ComandoInexistente("Comando incorrecto.");
    }

    public void jugador(String[] partes) throws Excepcion {
        if (partes.length == 1) {
            if (jugadorturno != null) {
                consola.imprimir("{\nnombre: " + jugadorturno.getNombre() + "\navatar: " + jugadorturno.getAvatar().getId() + "\n}");
            } else
                throw new CreacionExcepcion("Primero debes crear algún jugador.");
        } else
            throw new ComandoInexistente("Comando incorrecto.");
    }

    public void listar(String[] partes) throws Excepcion {
        if (partes.length == 2) {
            if (partes[1].equals("jugadores")) {
                consola.imprimir(jugadores);
            } else if (partes[1].equals("avatares")) {
                for (Jugador j : jugadores) {
                    consola.imprimir(j.getAvatar() + ("\njugador: ") + j.getNombre() + "\n}");
                }
            } else if (partes[1].equals("enventa")) {
                for (Casilla p : tablero.getBanca().getPropiedades()) {
                    consola.imprimirln("{");
                    consola.imprimirln("nombre: " + p.getNombre() + ",");
                    consola.imprimirln("tipo: " + p.getClass().getName().split("Casillas.")[1] + ",");
                    if (p instanceof Solar)
                        consola.imprimirln("grupo: " + p.getGrupo().getNombre() + ",");
                    consola.imprimirln("valor: " + p.getCoste());
                    consola.imprimirln("}");
                }
            } else if (partes[1].equals("edificios")) {
                for (Casilla c : tablero.getCasillas()) {
                    if (c instanceof Solar) {
                        if (((Solar) c).getEdificios().size() != 0) {
                            for (Edificio e : ((Solar) c).getEdificios()) {
                                consola.imprimir("{");
                                if (e instanceof Casa)
                                    consola.imprimir("id: " + ((Casa) e).getId());
                                else if (e instanceof Hotel)
                                    consola.imprimir("id: " + ((Hotel) e).getId());
                                else if (e instanceof Piscina)
                                    consola.imprimir("id: " + ((Piscina) e).getId());
                                else
                                    consola.imprimir("id: " + ((PistaDeporte) e).getId());
                                consola.imprimir("propietario: " + ((Solar) c).getPropietario().getNombre());
                                consola.imprimir("casilla: " + c.getNombre());
                                consola.imprimir("grupo: " + c.getGrupo().getNombre());
                                if (e instanceof Casa)
                                    consola.imprimir("coste: " + costeEdificio(c.getGrupo().getNombre(), "Casa"));
                                else if (e instanceof Hotel)
                                    consola.imprimir("coste: " + costeEdificio(c.getGrupo().getNombre(), "Hotel"));
                                else if (e instanceof Piscina)
                                    consola.imprimir("coste: " + costeEdificio(c.getGrupo().getNombre(), "Piscina"));
                                else
                                    consola.imprimir("coste: " + costeEdificio(c.getGrupo().getNombre(), "Pista"));
                                consola.imprimir("},");
                            }
                        }
                    }
                }
            } else
                throw new ComandoInexistente("Comando incorrecto.");
        } else if (partes.length == 3) {
            if (partes[1].equals("edificios")) {
                for (int i = 0; i < tablero.getGrupos().size(); i++) {
                    if (tablero.getGrupos().get(i).getNombre().equals(partes[2])) {
                        for (int j = 0; j < tablero.getGrupos().get(i).getCasillas().size(); j++) {
                            consola.imprimir("{");
                            consola.imprimir("propiedad: " + tablero.getGrupos().get(i).getCasillas().get(j).getNombre());
                            int w = 0, x = 0, y = 0, z = 0;
                            if (((Solar) tablero.getGrupos().get(i).getCasillas().get(j)).getEdificios().size() != 0) {
                                for (Edificio e : ((Solar) tablero.getGrupos().get(i).getCasillas().get(j)).getEdificios()) {
                                    if (e instanceof Casa) {
                                        if (w == 0) {
                                            consola.imprimir("casas: ");
                                            w++;
                                        }
                                        consola.imprimir("\t" + ((Casa) e).getId());
                                    } else if (e instanceof Hotel) {
                                        if (x == 0) {
                                            consola.imprimir("hoteles: ");
                                            x++;
                                        }
                                        consola.imprimir("\t" + ((Hotel) e).getId());
                                    } else if (e instanceof Piscina) {
                                        if (y == 0) {
                                            consola.imprimir("piscinas: ");
                                            y++;
                                        }
                                        consola.imprimir("\t" + ((Piscina) e).getId());
                                    } else {
                                        if (z == 0) {
                                            consola.imprimir("pistasDeDeporte: ");
                                            z++;
                                        }
                                        consola.imprimir("\t" + ((PistaDeporte) e).getId());
                                    }
                                }
                                imprimirEdificiosPorConstruir(i, j);
                            } else {
                                if (tablero.getGrupos().get(i).getNombre().equals("Negro") || tablero.getGrupos().get(i).getNombre().equals("Azul"))
                                    consola.imprimir("Aún puedes edificar cuatro casas, dos hoteles, dos piscinas y dos pistas de deporte.");
                                else
                                    consola.imprimir("Aún puedes edificar cuatro casas, tres hoteles, tres piscinas y tres pistas de deporte.");
                            }
                            consola.imprimir("alquiler: " + ((Solar) tablero.getGrupos().get(i).getCasillas().get(j)).getAlquiler());
                            consola.imprimir("},");
                        }
                    }
                }
            } else
                throw new ComandoInexistente("Comando incorrecto.");
        } else
            throw new ComandoInexistente("Comando incorrecto.");
    }

    public void lanzar(String[] partes) throws Excepcion {
        if (partes.length == 2) {
            if (partes[1].equals("dados")) {
                if (jugadores.size() > 1 && jugadores.size() < 7) {
                    if (tablero.getDado().isPuedolanzar()) {
                        if (!tablero.isJugadorescreados()) {
                            tablero.crear_array_jugadores();
                        }
                        nocrearjugador = true;
                        tablero.Tirada(jugadorturno);
                        if (jugadorturno.getAvatar() instanceof Pelota) {
                            if (!((Pelota) jugadorturno.getAvatar()).isMovimientoespecialpelota())
                                consola.imprimir(tablero);
                        } else
                            consola.imprimir(tablero);
                        if (jugadorturno.getAvatar() instanceof Coche) {
                            if (tablero.getDado().is_DobleDado(tablero.getMov1(), tablero.getMov2()) == 0 && !((Coche) jugadorturno.getAvatar()).isMovimientoespecialcoche()) {
                                tablero.getDado().setPuedolanzar(false);
                                jugadorturno.setTurno(0);
                            } else if (((Coche) jugadorturno.getAvatar()).isMovimientoespecialcoche() && tablero.getSuma() < 5 && !jugadorturno.getAvatar().isJugadorcarcel()) {
                                tablero.getDado().setPuedolanzar(false);
                                jugadorturno.setTurno(0);
                            } else if (((Coche) jugadorturno.getAvatar()).isMovimientoespecialcoche() && tablero.getSuma() > 4 && !jugadorturno.getAvatar().isJugadorcarcel()) {
                                if (tablero.getTurnoespecialcoche() < 4 && tablero.isPuedolanzarcoche()) {
                                    tablero.getDado().setPuedolanzar(true);
                                } else tablero.getDado().setPuedolanzar(false);
                                jugadorturno.setTurno(0);
                            } else if (((Coche) jugadorturno.getAvatar()).isMovimientoespecialcoche() && tablero.getSuma() < 5) {
                                jugadorturno.setTurno(0);
                            }
                        } else if (jugadorturno.getAvatar() instanceof Esfinge) {
                            if (tablero.getDado().is_DobleDado(tablero.getMov1(), tablero.getMov2()) == 0 && !((Esfinge) jugadorturno.getAvatar()).isMovimientoespecialesfinge()) {
                                tablero.getDado().setPuedolanzar(false);
                                jugadorturno.setTurno(0);
                            } else if (((Esfinge) jugadorturno.getAvatar()).isMovimientoespecialesfinge() && tablero.getSuma() < 5 && !jugadorturno.getAvatar().isJugadorcarcel()) {
                                tablero.getDado().setPuedolanzar(false);
                                jugadorturno.setTurno(0);
                            } else if (((Esfinge) jugadorturno.getAvatar()).isMovimientoespecialesfinge() && tablero.getSuma() > 4 && !jugadorturno.getAvatar().isJugadorcarcel()) {
                                if (tablero.getTurnoespecialcoche() < 4 && tablero.isPuedolanzaresfinge()) {
                                    tablero.getDado().setPuedolanzar(true);
                                } else tablero.getDado().setPuedolanzar(false);
                                jugadorturno.setTurno(0);
                            } else if (((Esfinge) jugadorturno.getAvatar()).isMovimientoespecialesfinge() && tablero.getSuma() < 5) {
                                jugadorturno.setTurno(0);
                            }
                        } else if (jugadorturno.getAvatar() instanceof Sombrero) {
                            if (tablero.getDado().is_DobleDado(tablero.getMov1(), tablero.getMov2()) == 0 && !((Sombrero) jugadorturno.getAvatar()).isMovimientoespecialsombrero()) {
                                tablero.getDado().setPuedolanzar(false);
                                jugadorturno.setTurno(0);
                            } else if (((Sombrero) jugadorturno.getAvatar()).isMovimientoespecialsombrero() && tablero.getSuma() < 5 && !jugadorturno.getAvatar().isJugadorcarcel()) {
                                tablero.getDado().setPuedolanzar(false);
                                jugadorturno.setTurno(0);
                            } else if (((Sombrero) jugadorturno.getAvatar()).isMovimientoespecialsombrero() && tablero.getSuma() > 4 && !jugadorturno.getAvatar().isJugadorcarcel()) {
                                if (tablero.getTurnoespecialcoche() < 4 && tablero.isPuedolanzarsombrero()) {
                                    tablero.getDado().setPuedolanzar(true);
                                } else tablero.getDado().setPuedolanzar(false);
                                jugadorturno.setTurno(0);
                            } else if (((Sombrero) jugadorturno.getAvatar()).isMovimientoespecialsombrero() && tablero.getSuma() < 5) {
                                jugadorturno.setTurno(0);
                            }
                        } else if (jugadorturno.getAvatar() instanceof Pelota && ((Pelota) jugadorturno.getAvatar()).isMovimientoespecialpelota() && tablero.getSuma() < 5 && !jugadorturno.getAvatar().isJugadorcarcel()) {
                            tablero.getDado().setPuedolanzar(false);
                            jugadorturno.setTurno(0);
                        } else if (jugadorturno.getAvatar().getPosicion() == 10 && jugadorturno.getAvatar().isJugadorcarcel()) {
                            tablero.getDado().setPuedolanzar(false);
                            jugadorturno.setTurno(0);
                        } else if (tablero.getDado().is_DobleDado(tablero.getMov1(), tablero.getMov2()) == 0 && !(jugadorturno.getAvatar() instanceof Coche)) {
                            tablero.getDado().setPuedolanzar(false);
                            jugadorturno.setTurno(0);
                        } else
                            jugadorturno.setTurno(jugadorturno.getTurno() + 1);
                    } else
                        throw new LanzarExcepcion("No puedes volver a lanzar los dados.");
                } else
                    throw new LanzarExcepcion("Es necesario que el número de jugadores esté entre 2 y 6.");
            } else
                throw new ComandoInexistente("Comando incorrecto.");
        } else
            throw new ComandoInexistente("Comando incorrecto.");
    }

    public void comprar(String[] partes) throws Excepcion {
        if (partes.length == 2) {
            if (nocrearjugador) {
                if (jugadorturno.getAvatar() instanceof Coche) {
                    if (((Coche) jugadorturno.getAvatar()).isPuedocomprarcoche()) {
                        for (Casilla c : tablero.getCasillas()) {
                            if (c.getNombre().equals(partes[1])) {
                                if (c.estaAvatar(jugadorturno.getAvatar())) {
                                    if (c instanceof Propiedad) {
                                        ((Propiedad) c).comprar(jugadorturno, (Propiedad) c);
                                        tablero.getBanca().getPropiedades().remove(c);
                                        tablero.comprobarGrupoEntero(jugadorturno.getAvatar());
                                    } else
                                        throw new NoPropiedad("No puedes comprar la casilla, no es una propiedad.");
                                } else
                                    throw new NoEstarEnCasilla("El jugador no está en dicha casilla.");
                            }
                        }
                    } else
                        throw new NoComprarCoche("No puedes comprar propiedades todavía.");
                } else {
                    for (Casilla c : tablero.getCasillas()) {
                        if (c.getNombre().equals(partes[1])) {
                            if (jugadorturno.getAvatar().getCasillaAvatar().getNombre().equals(partes[1])) {
                                if (c instanceof Propiedad) {
                                    ((Propiedad) c).comprar(jugadorturno, (Propiedad) c);
                                    tablero.getBanca().getPropiedades().remove(c);
                                    tablero.comprobarGrupoEntero(jugadorturno.getAvatar());
                                    if (jugadorturno.getAvatar() instanceof Esfinge && ((Esfinge) jugadorturno.getAvatar()).isMovimientoespecialesfinge())
                                        ((Esfinge) jugadorturno.getAvatar()).setCompraUltimoTurno(true);
                                    else if (jugadorturno.getAvatar() instanceof Sombrero && ((Sombrero) jugadorturno.getAvatar()).isMovimientoespecialsombrero())
                                        ((Sombrero) jugadorturno.getAvatar()).setCompraUltimoTurno(true);
                                } else
                                    throw new NoPropiedad("No puedes comprar la casilla, no es una propiedad.");
                            } else
                                throw new NoEstarEnCasilla("El jugador no está en dicha casilla.");
                        }
                    }
                }
            } else
                throw new JuegoNoComenzado("El juego aún no ha comenzado.");
        } else
            throw new ComandoInexistente("Comando incorrecto.");
    }

    public void acabar(String[] partes) throws Excepcion {
        if (partes.length == 2) {
            if (partes[1].equals("turno")) {
                if (nocrearjugador) {
                    if (!tablero.getDado().isPuedolanzar()) {
                        acabarTurno();
                        tablero.getDado().setPuedolanzar(true);
                        consola.imprimirln("Le toca jugar a " + jugadorturno.getNombre() + ".\n");
                        if (jugadorestrato.contains(jugadorturno)) {
                            for (Trato t : trato) {
                                if (!t.getJugador1().getNombre().equals(jugadorturno.getNombre())) {
                                    if (t.getJugador2().getNombre().equals(jugadorturno.getNombre())) {
                                        consola.imprimirln("{\n\tjugadorPropone: " + t.getJugador1().getNombre() + ",\n\t" + t.getId() + ": " + t.getMensaje());
                                        consola.imprimirln("}");
                                    }
                                }
                            }
                        }
                    } else
                        throw new LanzarExcepcion("Primero debes lanzar los dados.");
                } else
                    throw new JuegoNoComenzado("El juego aún no ha comenzado.");
            } else
                throw new ComandoInexistente("Comando incorrecto.");
        } else
            throw new ComandoInexistente("Comando incorrecto.");
    }

    public void describir(String[] partes) throws Excepcion {
        if (partes.length == 2) {
            for (Casilla c : tablero.getCasillas()) {
                if (partes[1].equals(c.getNombre())) {
                    if (c instanceof Solar) {
                        ((Solar) c).impCasilla();
                    } else if (c instanceof Transporte) {
                        ((Transporte) c).impCasilla();
                    } else if (c instanceof Impuesto) {
                        ((Impuesto) c).impCasilla();
                    } else if (c instanceof Especial) {
                        ((Especial) c).impCasilla();
                    } else if (c instanceof Servicio) {
                        ((Servicio) c).impCasilla();
                    }
                }
            }
        } else if (partes.length == 3) {
            if (partes[1].equals("jugador")) {
                for (Jugador j : jugadores) {
                    if (partes[2].equals(j.getNombre())) {
                        j.impJugador();
                    }
                }
            } else if (partes[1].equals("avatar")) {
                for (Jugador j : jugadores) {
                    if (partes[2].equals(j.getAvatar().getId())) {
                        j.getAvatar().impAvatar(j.getAvatar().getId());
                    }
                }
            } else
                throw new ComandoInexistente("Comando incorrecto.");
        }
    }

    public void ver(String[] partes) throws Excepcion {
        if (partes.length == 2) {
            if (partes[1].equals("tablero")) {
                consola.imprimir(tablero.toString());
            } else
                throw new ComandoInexistente("Comando incorrecto.");
        } else
            throw new ComandoInexistente("Comando incorrecto.");
    }

    public void edificar(String[] partes) throws Excepcion {
        if (nocrearjugador) {
            if (jugadorturno.getAvatar().getCasillaAvatar() instanceof Solar) {
                if (((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getPropietario() != null) {
                    if (jugadorturno.getNombre().equals(((Solar) jugadorturno.getAvatar().getCasillaAvatar()).getPropietario().getNombre())) {
                        if (((Solar) jugadorturno.getAvatar().getCasillaAvatar()).isPuedoconstruircasa()) {
                            if (partes.length == 2)
                                ((Solar) jugadorturno.getAvatar().getCasillaAvatar()).Edificar(jugadorturno, partes[1]);
                            else
                                throw new ComandoInexistente("Comando incorrecto.");
                        } else
                            throw new ImposibilidadEdificar("Todavía no puedes edificar en esta propiedad.");
                    } else
                        throw new NoPropietario("No eres el propietario de esta casilla.");
                } else
                    throw new NoPropietario("Esta casilla no tiene propietario.");
            } else
                throw new NoSolar("No puedes edificar en esta casilla, no es un solar.");
        } else
            throw new JuegoNoComenzado("El juego aún no ha comenzado.");
    }

    public void hipotecar(String[] partes) throws Excepcion {
        boolean hipotecado = false;
        if (nocrearjugador) {
            if (partes.length == 2) {
                for (Casilla c : tablero.getCasillas()) {
                    if (c.getNombre().equals(partes[1])) {
                        if (jugadorturno.getHipotecas().size() > 0) {
                            for (Propiedad h : jugadorturno.getHipotecas()) {
                                if (h.getNombre().equals(partes[1])) {
                                    consola.imprimir(jugadorturno.getNombre() + " no puede hipotecar " + h.getNombre() + ". Ya está hipotecada.");
                                    hipotecado = true;
                                }
                            }
                        }
                        if (!hipotecado) {
                            if (c instanceof Solar) {
                                if (((Solar) c).getEdificios().size() == 0) {
                                    if (((Solar) c).getPropietario() != null) {
                                        if (jugadorturno.getNombre().equals(((Solar) c).getPropietario().getNombre())) {
                                            if (jugadorturno.getAvatar() instanceof Esfinge && ((Esfinge) jugadorturno.getAvatar()).isMovimientoespecialesfinge())
                                                ((Esfinge) jugadorturno.getAvatar()).setHipotecaUltimoTurno(true);
                                            else if (jugadorturno.getAvatar() instanceof Sombrero && ((Sombrero) jugadorturno.getAvatar()).isMovimientoespecialsombrero())
                                                ((Sombrero) jugadorturno.getAvatar()).setHipotecaUltimoTurno(true);
                                            tablero.hipotecar(jugadorturno, ((Propiedad) c));
                                            ((Propiedad) c).setHipotecada(true);
                                        } else
                                            throw new ImposibilidadHipotecar(jugadorturno.getNombre() + " no puede hipotecar " + c.getNombre() + ". No es una propiedad que le pertenece.");
                                    } else
                                        throw new ImposibilidadHipotecar(jugadorturno.getNombre() + " no puede hipotecar " + c.getNombre() + ". No es una propiedad que le pertenece.");
                                } else
                                    throw new ConEdificios("Para poder hipotecar " + c.getNombre() + " debes vender sus edificios.");
                            } else if (c instanceof Transporte) {
                                if (((Transporte) c).getPropietario() != null) {
                                    if (jugadorturno.getAvatar() instanceof Esfinge && ((Esfinge) jugadorturno.getAvatar()).isMovimientoespecialesfinge())
                                        ((Esfinge) jugadorturno.getAvatar()).setHipotecaUltimoTurno(true);
                                    else if (jugadorturno.getAvatar() instanceof Sombrero && ((Sombrero) jugadorturno.getAvatar()).isMovimientoespecialsombrero())
                                        ((Sombrero) jugadorturno.getAvatar()).setHipotecaUltimoTurno(true);
                                    if (jugadorturno.getNombre().equals(((Transporte) c).getPropietario().getNombre())) {
                                        tablero.hipotecar(jugadorturno, ((Propiedad) c));
                                        ((Propiedad) c).setHipotecada(true);
                                    } else
                                        throw new ImposibilidadHipotecar(jugadorturno.getNombre() + " no puede hipotecar " + c.getNombre() + ". No es una propiedad que le pertenece.");
                                } else
                                    throw new ImposibilidadHipotecar(jugadorturno.getNombre() + " no puede hipotecar " + c.getNombre() + ". No es una propiedad que le pertenece.");

                            } else if (c instanceof Servicio) {
                                if (((Servicio) c).getPropietario() != null) {
                                    if (jugadorturno.getAvatar() instanceof Esfinge && ((Esfinge) jugadorturno.getAvatar()).isMovimientoespecialesfinge())
                                        ((Esfinge) jugadorturno.getAvatar()).setHipotecaUltimoTurno(true);
                                    else if (jugadorturno.getAvatar() instanceof Sombrero && ((Sombrero) jugadorturno.getAvatar()).isMovimientoespecialsombrero())
                                        ((Sombrero) jugadorturno.getAvatar()).setHipotecaUltimoTurno(true);
                                    if (jugadorturno.getNombre().equals(((Servicio) c).getPropietario().getNombre())) {
                                        tablero.hipotecar(jugadorturno, ((Propiedad) c));
                                        ((Propiedad) c).setHipotecada(true);
                                    } else
                                        throw new ImposibilidadHipotecar(jugadorturno.getNombre() + " no puede hipotecar " + c.getNombre() + ". No es una propiedad que le pertenece.");
                                } else
                                    throw new ImposibilidadHipotecar(jugadorturno.getNombre() + " no puede hipotecar " + c.getNombre() + ". No es una propiedad que le pertenece.");

                            } else
                                throw new ImposibilidadHipotecar("Solo puedes hipotecar propiedades.");
                        }
                        hipotecado = false;
                    }
                }
            } else
                throw new ComandoInexistente("Comando incorrecto.");
        } else
            throw new JuegoNoComenzado("El juego aún no ha comenzado.");
    }

    public void deshipotecar(String[] partes) throws Excepcion {
        boolean hipotecado = false;
        if (nocrearjugador) {
            if (partes.length == 2) {
                for (Casilla c : tablero.getCasillas()) {
                    if (c.getNombre().equals(partes[1])) {
                        if (c instanceof Solar) {
                            if (((Solar) c).getPropietario() != null) {
                                if (((Solar) c).getPropietario().getNombre().equals(jugadorturno.getNombre())) {
                                    if (jugadorturno.getHipotecas().size() > 0) {
                                        for (Propiedad h : jugadorturno.getHipotecas()) {
                                            if (h.getNombre().equals(partes[1])) {
                                                hipotecado = true;
                                                break;
                                            }
                                        }
                                        if (hipotecado) {
                                            if (jugadorturno.getAvatar() instanceof Esfinge && ((Esfinge) jugadorturno.getAvatar()).isMovimientoespecialesfinge())
                                                ((Esfinge) jugadorturno.getAvatar()).setDeshipotecaUltimoTurno(true);
                                            else if (jugadorturno.getAvatar() instanceof Sombrero && ((Sombrero) jugadorturno.getAvatar()).isMovimientoespecialsombrero())
                                                ((Sombrero) jugadorturno.getAvatar()).setDeshipotecaUltimoTurno(true);
                                            tablero.deshipotecar(jugadorturno, ((Propiedad) c));
                                            ((Propiedad) c).setHipotecada(false);
                                        } else
                                            throw new DeshipotecarExcepcion(jugadorturno.getNombre() + " no puede deshipotecar " + c.getNombre() + ". No está hipotecada.");
                                    } else
                                        throw new DeshipotecarExcepcion(jugadorturno.getNombre() + " no puede deshipotecar " + c.getNombre() + ". No está hipotecada.");
                                } else
                                    throw new DeshipotecarExcepcion(jugadorturno.getNombre() + " no puede deshipotecar " + c.getNombre() + ". No es una propiedad que le pertenece.");
                            } else
                                throw new DeshipotecarExcepcion(jugadorturno.getNombre() + " no puede deshipotecar " + c.getNombre() + ". No es una propiedad que le pertenece.");
                        } else if (c instanceof Transporte) {
                            if (((Transporte) c).getPropietario() != null) {
                                if (((Transporte) c).getPropietario().getNombre().equals(jugadorturno.getNombre())) {
                                    if (jugadorturno.getHipotecas().size() > 0) {
                                        for (Propiedad h : jugadorturno.getHipotecas()) {
                                            if (h.getNombre().equals(partes[1])) {
                                                hipotecado = true;
                                                break;
                                            }
                                        }
                                        if (hipotecado) {
                                            if (jugadorturno.getAvatar() instanceof Esfinge && ((Esfinge) jugadorturno.getAvatar()).isMovimientoespecialesfinge())
                                                ((Esfinge) jugadorturno.getAvatar()).setDeshipotecaUltimoTurno(true);
                                            else if (jugadorturno.getAvatar() instanceof Sombrero && ((Sombrero) jugadorturno.getAvatar()).isMovimientoespecialsombrero())
                                                ((Sombrero) jugadorturno.getAvatar()).setDeshipotecaUltimoTurno(true);
                                            tablero.deshipotecar(jugadorturno, ((Propiedad) c));
                                            ((Propiedad) c).setHipotecada(false);
                                        } else
                                            throw new DeshipotecarExcepcion(jugadorturno.getNombre() + " no puede deshipotecar " + c.getNombre() + ". No está hipotecada.");
                                    } else
                                        throw new DeshipotecarExcepcion(jugadorturno.getNombre() + " no puede deshipotecar " + c.getNombre() + ". No está hipotecada.");
                                } else
                                    throw new DeshipotecarExcepcion(jugadorturno.getNombre() + " no puede deshipotecar " + c.getNombre() + ". No es una propiedad que le pertenece.");
                            } else
                                throw new DeshipotecarExcepcion(jugadorturno.getNombre() + " no puede deshipotecar " + c.getNombre() + ". No es una propiedad que le pertenece.");
                        } else if (c instanceof Servicio) {
                            if (((Servicio) c).getPropietario() != null) {
                                if (((Servicio) c).getPropietario().getNombre().equals(jugadorturno.getNombre())) {
                                    if (jugadorturno.getHipotecas().size() > 0) {
                                        for (Propiedad h : jugadorturno.getHipotecas()) {
                                            if (h.getNombre().equals(partes[1])) {
                                                hipotecado = true;
                                                break;
                                            }
                                        }
                                        if (hipotecado) {
                                            if (jugadorturno.getAvatar() instanceof Esfinge && ((Esfinge) jugadorturno.getAvatar()).isMovimientoespecialesfinge())
                                                ((Esfinge) jugadorturno.getAvatar()).setDeshipotecaUltimoTurno(true);
                                            else if (jugadorturno.getAvatar() instanceof Sombrero && ((Sombrero) jugadorturno.getAvatar()).isMovimientoespecialsombrero())
                                                ((Sombrero) jugadorturno.getAvatar()).setDeshipotecaUltimoTurno(true);
                                            tablero.deshipotecar(jugadorturno, ((Propiedad) c));
                                            ((Propiedad) c).setHipotecada(false);
                                        } else
                                            throw new DeshipotecarExcepcion(jugadorturno.getNombre() + " no puede deshipotecar " + c.getNombre() + ". No está hipotecada.");
                                    } else
                                        throw new DeshipotecarExcepcion(jugadorturno.getNombre() + " no puede deshipotecar " + c.getNombre() + ". No está hipotecada.");
                                } else
                                    throw new DeshipotecarExcepcion(jugadorturno.getNombre() + " no puede deshipotecar " + c.getNombre() + ". No es una propiedad que le pertenece.");
                            } else
                                throw new DeshipotecarExcepcion(jugadorturno.getNombre() + " no puede deshipotecar " + c.getNombre() + ". No es una propiedad que le pertenece.");
                        } else
                            throw new DeshipotecarExcepcion("Solo puedes deshipotecar propiedades.");
                    }
                }
            } else
                throw new ComandoInexistente("Comando incorrecto.");
        } else
            throw new JuegoNoComenzado("El juego aún no ha comenzado.");
    }

    public void vender(String[] partes) throws Excepcion {
        if (partes.length == 4) {
            if (nocrearjugador) {
                for (Casilla c : tablero.getCasillas()) {
                    if (c.getNombre().equals(partes[2])) {
                        if (c instanceof Solar) {
                            if (((Solar) c).getPropietario() != null) {
                                if (((Solar) c).getPropietario().getNombre().equals(jugadorturno.getNombre())) {
                                    if (jugadorturno.getPropiedades().size() > 0) {
                                        if (partes[1].equals("casa") || partes[1].equals("casas")) {
                                            for (Propiedad p : jugadorturno.getPropiedades()) {
                                                if (p.getNombre().equals(partes[2])) {
                                                    if (SePuedeCambiar(partes[3]))
                                                        tablero.venderCasa(jugadorturno, p, Integer.parseInt(partes[3]));
                                                    else
                                                        throw new ComandoInexistente("Comando Incorrecto.");
                                                }
                                            }
                                        } else if (partes[1].equals("hotel") || partes[1].equals("hoteles")) {
                                            for (Propiedad p : jugadorturno.getPropiedades()) {
                                                if (p.getNombre().equals(partes[2]))
                                                    if (SePuedeCambiar(partes[3]))
                                                        tablero.venderHotel(jugadorturno, p, Integer.parseInt(partes[3]));
                                                    else
                                                        throw new ComandoInexistente("Comando Incorrecto.");
                                            }
                                        } else if (partes[1].equals("piscina") || partes[1].equals("piscinas")) {
                                            for (Propiedad p : jugadorturno.getPropiedades()) {
                                                if (p.getNombre().equals(partes[2]))
                                                    if (SePuedeCambiar(partes[3]))
                                                        tablero.venderPiscina(jugadorturno, p, Integer.parseInt(partes[3]));
                                                    else
                                                        throw new ComandoInexistente("Comando Incorrecto.");
                                            }
                                        } else if (partes[1].equals("pista") || partes[1].equals("pistas")) {
                                            for (Propiedad p : jugadorturno.getPropiedades()) {
                                                if (p.getNombre().equals(partes[2]))
                                                    if (SePuedeCambiar(partes[3]))
                                                        tablero.venderPista(jugadorturno, p, Integer.parseInt(partes[3]));
                                                    else
                                                        throw new ComandoInexistente("Comando Incorrecto.");
                                            }
                                        } else
                                            throw new ComandoInexistente("Comando incorrecto.");
                                    } else
                                        throw new VenderExcepcion("No se pueden vender " + partes[1] + " en " + partes[2] + ". Esta propiedad no pertenece a " + jugadorturno.getNombre() + ".");
                                } else
                                    throw new VenderExcepcion("No se pueden vender " + partes[1] + " en " + partes[2] + ". Esta propiedad no pertenece a " + jugadorturno.getNombre() + ".");
                            } else
                                throw new VenderExcepcion("No se pueden vender " + partes[1] + " en " + partes[2] + ". Esta propiedad no pertenece a " + jugadorturno.getNombre() + ".");
                        } else
                            throw new NoSolar("No puedes edificar en esta casilla, no es un solar.");
                    }
                }
            } else
                throw new JuegoNoComenzado("El juego aún no ha comenzado.");
        } else
            throw new ComandoInexistente("Comando incorrecto.");
    }

    public void trato(String[] partes) throws Excepcion {
        boolean espropiedad1 = false;
        boolean espropiedad2 = false;
        boolean espropiedad3 = false;
        Propiedad prop1 = null;
        Propiedad prop2 = null;
        Propiedad prop3 = null;
        Jugador jugadorRecibe = null;

        for (Propiedad p : jugadorturno.getPropiedades()) {
            if (partes[3].equals(p.getNombre())) {
                prop1 = p;
            }
        }

        for (Jugador jug : tablero.getJugadores()) {
            if (partes[1].equals(jug.getNombre())) {
                jugadorRecibe = jug;
                jugadorestrato.add(jugadorRecibe);
            }
        }

        if (partes.length == 5) {
            for (Casilla i : tablero.getCasillas()) {
                if (i.getNombre().equals(partes[3])) {
                    if (i instanceof Propiedad) {
                        espropiedad1 = true;
                    } else
                        throw new NoPropiedad(i.getNombre() + " no es una propiedad.");
                }
                if (i.getNombre().equals(partes[4])) {
                    if (i instanceof Propiedad) {
                        espropiedad2 = true;
                    } else
                        throw new NoPropiedad(i.getNombre() + " no es una propiedad.");
                }
            }

            if (espropiedad1 && espropiedad2) {
                if (jugadorRecibe != null) {
                    if (jugadorRecibe.getPropiedades().size() > 0) {
                        for (Propiedad p : jugadorRecibe.getPropiedades()) {
                            if (partes[4].equals(p.getNombre())) {
                                prop2 = p;
                            }
                        }
                    } else
                        throw new TratoExcepcion("El jugador no posee la propiedad " + partes[4] + " o está hipotecada.");
                } else
                    throw new TratoExcepcion("No puedes proponer un trato a " + partes[1] + ". Salvo que sea Tipo de Incógnito no existe.");

                if (prop1 != null && prop2 != null) {
                    if (!jugadorturno.getNombre().equals(jugadorRecibe.getNombre())) {
                        if (!prop1.getNombre().equals(prop2.getNombre())) {
                            trato_creado++;
                            trato.add(new Trato(trato_creado, jugadorturno, prop1, jugadorRecibe, prop2));
                        } else
                            throw new TratoExcepcion("No se puede proponer un trato entre las mismas casillas.");
                    } else
                        throw new TratoExcepcion("No se puede proponer un trato a sí mismo.");
                } else
                    throw new TratoExcepcion("No existe una de las dos propiedades o está hipotecada.");

            } else if (espropiedad1 || espropiedad2) {
                if (espropiedad1 && SePuedeCambiar(partes[4])) {
                    if (prop1 != null) {
                        if (jugadorRecibe != null) {
                            if (!jugadorturno.getNombre().equals(jugadorRecibe.getNombre())) {
                                if (SePuedeCambiar(partes[4])) {
                                    trato_creado++;
                                    trato.add(new Trato(trato_creado, jugadorturno, prop1, jugadorRecibe, Integer.parseInt(partes[4])));
                                } else
                                    throw new TratoExcepcion("No es una cantidad de dinero posible.");
                            } else
                                throw new TratoExcepcion("No se puede proponer un trato a sí mismo.");
                        } else
                            throw new TratoExcepcion("No puedes proponer un trato a " + partes[1] + ". Salvo que sea Tipo de Incógnito no existe.");
                    } else
                        throw new TratoExcepcion("No existe la propiedad " + partes[3] + " o está hipotecada.");

                } else if (espropiedad2 && SePuedeCambiar(partes[3])) {
                    if (jugadorRecibe != null) {
                        if (jugadorRecibe.getPropiedades().size() > 0) {
                            for (Propiedad p : jugadorRecibe.getPropiedades()) {
                                if (partes[4].equals(p.getNombre())) {
                                    prop2 = p;
                                }
                            }
                        } else
                            throw new TratoExcepcion("El jugador no posee la propiedad " + partes[4] + " o está hipotecada.");
                    } else
                        throw new TratoExcepcion("No puedes proponer un trato a " + partes[1] + ". Salvo que sea Tipo de Incógnito no existe.");

                    if (prop2 != null) {
                        if (!jugadorturno.getNombre().equals(jugadorRecibe.getNombre())) {
                            if (SePuedeCambiar(partes[3])) {
                                trato_creado++;
                                trato.add(new Trato(trato_creado, jugadorturno, Integer.parseInt(partes[3]), jugadorRecibe, prop2));
                            } else
                                throw new TratoExcepcion("No es una cantidad de dinero posible.");
                        } else
                            throw new TratoExcepcion("No se puede proponer un trato a sí mismo.");
                    } else
                        throw new TratoExcepcion("No existe la propiedad " + partes[3] + " o está hipotecada.");
                }
            } else
                throw new TratoExcepcion("No existe una de las dos propiedades o está hipotecada.");

        } else if (partes.length == 6) {
            for (Casilla i : tablero.getCasillas()) {
                if (i.getNombre().equals(partes[3])) {
                    if (i instanceof Propiedad) {
                        espropiedad1 = true;
                    } else
                        throw new NoPropiedad(i.getNombre() + " no es una propiedad.");
                }
                if (i.getNombre().equals(partes[4]) || i.getNombre().equals(partes[5])) {
                    if (i instanceof Propiedad) {
                        espropiedad2 = true;
                    } else
                        throw new NoPropiedad(i.getNombre() + " no es una propiedad.");
                }
            }

            if (espropiedad1) {
                if (espropiedad2) {
                    if (jugadorRecibe != null) {
                        if (jugadorRecibe.getPropiedades().size() > 0) {
                            for (Propiedad p : jugadorRecibe.getPropiedades()) {
                                if (partes[5].equals(p.getNombre())) {
                                    prop2 = p;
                                }
                                if (partes[4].equals(p.getNombre())) {
                                    prop2 = p;
                                }
                            }
                        } else
                            throw new TratoExcepcion("El jugador no posee la propiedad o está hipotecada.");
                    } else
                        throw new TratoExcepcion("No puedes proponer un trato a " + partes[1] + ". Salvo que sea Tipo de Incógnito no existe.");

                    if (prop1 != null && prop2 != null) {
                        if (!jugadorturno.getNombre().equals(jugadorRecibe.getNombre())) {
                            if (!prop1.getNombre().equals(prop2.getNombre())) {
                                if (SePuedeCambiar(partes[5])) {
                                    trato_creado++;
                                    trato.add(new Trato(trato_creado, jugadorturno, prop1, jugadorRecibe, prop2, Integer.parseInt(partes[5])));
                                } else if (SePuedeCambiar(partes[4])) {
                                    trato_creado++;
                                    trato.add(new Trato(trato_creado, jugadorturno, prop1, Integer.parseInt(partes[4]), jugadorRecibe, prop2));
                                } else
                                    throw new TratoExcepcion("No es una cantidad de dinero posible.");
                            } else
                                throw new TratoExcepcion("No se puede proponer un trato entre las mismas casillas.");
                        } else
                            throw new TratoExcepcion("No se puede proponer un trato a sí mismo.");
                    } else
                        throw new TratoExcepcion("No existe una de las dos propiedades o está hipotecada.");
                } else
                    throw new TratoExcepcion("No existe una de las dos propiedades o está hipotecada.");
            } else
                throw new TratoExcepcion("No existe una de las dos propiedades o está hipotecada.");
        } else if (partes.length == 7) {
            for (Casilla i : tablero.getCasillas()) {
                if (i.getNombre().equals(partes[3])) {
                    if (i instanceof Propiedad) {
                        espropiedad1 = true;
                    } else
                        throw new NoPropiedad(i.getNombre() + " no es una propiedad.");
                }
                if (i.getNombre().equals(partes[4])) {
                    if (i instanceof Propiedad) {
                        espropiedad2 = true;
                    } else
                        throw new NoPropiedad(i.getNombre() + " no es una propiedad.");
                }
                if (i.getNombre().equals(partes[5])) {
                    if (i instanceof Propiedad) {
                        espropiedad3 = true;
                    } else
                        throw new NoPropiedad(i.getNombre() + " no es una propiedad.");
                }
            }

            if (espropiedad1 && espropiedad2 && espropiedad3) {
                if (jugadorRecibe != null) {
                    if (jugadorRecibe.getPropiedades().size() > 0) {
                        for (Propiedad p : jugadorRecibe.getPropiedades()) {
                            if (partes[4].equals(p.getNombre())) {
                                prop2 = p;
                            }
                            if (partes[5].equals(p.getNombre())) {
                                prop3 = p;
                            }
                        }
                    } else
                        throw new TratoExcepcion("El jugador no posee una de las dos propiedades o está hipotecada.");
                } else
                    throw new TratoExcepcion("No puedes proponer un trato a " + partes[1] + ". Salvo que sea Tipo de Incógnito no existe.");

                if (prop1 != null && prop2 != null && prop3 != null) {
                    if (!jugadorturno.getNombre().equals(jugadorRecibe.getNombre())) {
                        if (!prop1.getNombre().equals(prop2.getNombre())) {
                            if (SePuedeCambiar(partes[6])) {
                                trato_creado++;
                                trato.add(new Trato(trato_creado, jugadorturno, prop1, jugadorRecibe, prop2, prop3, Integer.parseInt(partes[6])));
                            } else
                                throw new TratoExcepcion("No es un número de turnos posible.");
                        } else
                            throw new TratoExcepcion("No se puede proponer un trato entre las mismas casillas.");
                    } else
                        throw new TratoExcepcion("No se puede proponer un trato a sí mismo.");
                } else
                    throw new TratoExcepcion("No existe alguna de las tres propiedades o está hipotecada.");
            } else
                throw new TratoExcepcion("No existe alguna de las tres propiedades o está hipotecada.");
        } else
            throw new ComandoInexistente("Comando incorrecto");
    }

    public void aceptar(String[] partes) throws Excepcion {
        if (partes.length == 2) {
            if (nocrearjugador) {
                if (jugadorestrato.contains(jugadorturno)) {
                    if (trato.size() > 0) {
                        for (Trato t : trato) {
                            if (!t.getJugador1().getNombre().equals(jugadorturno.getNombre())) {
                                if (t.getJugador2().getNombre().equals(jugadorturno.getNombre())) {
                                    if (partes[1].equals(t.getId())) {
                                        if (t.getPropiedad1() != null && t.getPropiedad2() == null && t.getPropiedad3() == null) {
                                            if (t.getDinero1() != 0) {
                                                if (t.getPropiedad1().getPropietario().getNombre().equals(t.getJugador2().getNombre())) {
                                                    if (t.getJugador1().getFortuna() >= t.getDinero1()) {
                                                        if (t.getPropiedad1() instanceof Solar) {
                                                            if (((Solar) t.getPropiedad1()).getEdificios().size() > 0)
                                                                throw new TratoExcepcion("El trato no puede ser aceptado: " + t.getJugador2().getNombre() + " debe vender antes los edificios de " + t.getPropiedad1().getNombre() + ".");
                                                        }
                                                        Propiedad propiedad = null;
                                                        for (Propiedad p : t.getJugador1().getPropiedades()) {
                                                            if (p.getNombre().equals(t.getPropiedad1().getNombre()))
                                                                propiedad = p;
                                                        }
                                                        if (propiedad != null) {
                                                            t.getJugador1().anadirPropiedad(t.getPropiedad1());
                                                            t.getJugador2().eliminarPropiedad(t.getPropiedad1());
                                                        } else {
                                                            t.getJugador1().getHipotecas().add(t.getPropiedad1());
                                                            t.getJugador2().getHipotecas().remove(t.getPropiedad1());
                                                        }
                                                        t.getPropiedad1().setPropietario(t.getJugador1());
                                                        t.getJugador1().setFortuna(t.getJugador1().getFortuna() - t.getDinero1());
                                                        t.getJugador2().setFortuna(t.getJugador2().getFortuna() + t.getDinero1());
                                                        Juego.consola.imprimirln("Se ha aceptado el siguiente trato con " + t.getJugador1().getNombre() + ": le doy " + t.getPropiedad1().getNombre() + " y " + t.getJugador1().getNombre() + " me da " + t.getDinero1() + "€.");
                                                    } else
                                                        throw new SinDinero("El trato no puede ser aceptado: " + t.getJugador1().getNombre() + " no dispone de " + t.getDinero1() + "€.");
                                                } else
                                                    throw new NoPropiedad("El trato no puede ser aceptado: " + t.getPropiedad1().getNombre() + " no pertenece a " + t.getJugador1().getNombre() + ".");
                                            } else if (t.getDinero2() != 0) {
                                                if (t.getPropiedad1().getPropietario().getNombre().equals(t.getJugador1().getNombre())) {
                                                    if (t.getJugador2().getFortuna() >= t.getDinero2()) {
                                                        if (t.getPropiedad1() instanceof Solar) {
                                                            if (((Solar) t.getPropiedad1()).getEdificios().size() > 0)
                                                                throw new TratoExcepcion("El trato no puede ser aceptado: " + t.getJugador1().getNombre() + " debe vender antes los edificios de " + t.getPropiedad1().getNombre() + ".");
                                                        }
                                                        Propiedad propiedad = null;
                                                        for (Propiedad p : t.getJugador1().getPropiedades()) {
                                                            if (p.getNombre().equals(t.getPropiedad1().getNombre()))
                                                                propiedad = p;
                                                        }
                                                        if (propiedad != null) {
                                                            t.getJugador2().anadirPropiedad(t.getPropiedad1());
                                                            t.getJugador1().eliminarPropiedad(t.getPropiedad1());
                                                        } else {
                                                            t.getJugador2().getHipotecas().add(t.getPropiedad1());
                                                            t.getJugador1().getHipotecas().remove(t.getPropiedad1());
                                                        }
                                                        t.getPropiedad1().setPropietario(t.getJugador2());
                                                        t.getJugador1().setFortuna(t.getJugador1().getFortuna() + t.getDinero2());
                                                        t.getJugador2().setFortuna(t.getJugador2().getFortuna() - t.getDinero2());
                                                        Juego.consola.imprimirln("Se ha aceptado el siguiente trato con " + t.getJugador1().getNombre() + ": le doy " + t.getDinero2() + "€ y " + t.getJugador1().getNombre() + " me da " + t.getPropiedad1().getNombre() + ".");
                                                    } else
                                                        throw new SinDinero("El trato no puede ser aceptado: " + t.getJugador1().getNombre() + " no dispone de " + t.getDinero1() + "€.");
                                                } else
                                                    throw new NoPropiedad("El trato no puede ser aceptado: " + t.getPropiedad1().getNombre() + " no pertenece a " + t.getJugador1().getNombre() + ".");
                                            }
                                        } else if (t.getPropiedad1() != null && t.getPropiedad2() != null && t.getPropiedad3() == null) {
                                            if (t.getDinero1() == 0 && t.getDinero2() == 0) {
                                                if (t.getPropiedad1().getPropietario().getNombre().equals(t.getJugador1().getNombre())) {
                                                    if (t.getPropiedad2().getPropietario().getNombre().equals(t.getJugador2().getNombre())) {
                                                        if (t.getPropiedad1() instanceof Solar) {
                                                            if (((Solar) t.getPropiedad1()).getEdificios().size() > 0)
                                                                throw new TratoExcepcion("El trato no puede ser aceptado: " + t.getJugador1().getNombre() + " debe vender antes los edificios de " + t.getPropiedad1().getNombre() + ".");
                                                        }
                                                        if (t.getPropiedad2() instanceof Solar) {
                                                            if (((Solar) t.getPropiedad2()).getEdificios().size() > 0)
                                                                throw new TratoExcepcion("El trato no puede ser aceptado: " + t.getJugador2().getNombre() + " debe vender antes los edificios de " + t.getPropiedad2().getNombre() + ".");
                                                        }
                                                        Propiedad propiedad1 = null, propiedad2 = null;
                                                        for (Propiedad p : t.getJugador1().getPropiedades()) {
                                                            if (p.getNombre().equals(t.getPropiedad1().getNombre()))
                                                                propiedad1 = p;
                                                        }
                                                        for (Propiedad p : t.getJugador2().getPropiedades()) {
                                                            if (p.getNombre().equals(t.getPropiedad2().getNombre()))
                                                                propiedad2 = p;
                                                        }
                                                        if (propiedad1 != null) {
                                                            t.getJugador1().anadirPropiedad(t.getPropiedad2());
                                                            t.getJugador2().eliminarPropiedad(t.getPropiedad2());
                                                        } else {
                                                            t.getJugador1().getHipotecas().add(t.getPropiedad2());
                                                            t.getJugador2().getHipotecas().remove(t.getPropiedad2());
                                                        }
                                                        if (propiedad2 != null) {
                                                            t.getJugador2().anadirPropiedad(t.getPropiedad1());
                                                            t.getJugador1().eliminarPropiedad(t.getPropiedad1());
                                                        } else {
                                                            t.getJugador2().getHipotecas().add(t.getPropiedad1());
                                                            t.getJugador1().getHipotecas().remove(t.getPropiedad1());
                                                        }
                                                        t.getPropiedad2().setPropietario(t.getJugador1());
                                                        t.getPropiedad1().setPropietario(t.getJugador2());
                                                        Juego.consola.imprimirln("Se ha aceptado el siguiente trato con " + t.getJugador1().getNombre() + ": le doy " + t.getPropiedad2().getNombre() + " y " + t.getJugador1().getNombre() + " me da " + t.getPropiedad1().getNombre() + ".");
                                                    } else
                                                        throw new NoPropiedad("El trato no puede ser aceptado: " + t.getPropiedad2().getNombre() + " no pertenece a " + t.getJugador2().getNombre() + ".");
                                                } else
                                                    throw new NoPropiedad("El trato no puede ser aceptado: " + t.getPropiedad1().getNombre() + " no pertenece a " + t.getJugador1().getNombre() + ".");
                                            } else if (t.getDinero1() != 0) {
                                                if (t.getPropiedad1().getPropietario().getNombre().equals(t.getJugador1().getNombre())) {
                                                    if (t.getPropiedad2().getPropietario().getNombre().equals(t.getJugador2().getNombre())) {
                                                        if (t.getJugador1().getFortuna() >= t.getDinero1()) {
                                                            if (t.getPropiedad1() instanceof Solar) {
                                                                if (((Solar) t.getPropiedad1()).getEdificios().size() > 0)
                                                                    throw new TratoExcepcion("El trato no puede ser aceptado: " + t.getJugador1().getNombre() + " debe vender antes los edificios de " + t.getPropiedad1().getNombre() + ".");
                                                            }
                                                            if (t.getPropiedad2() instanceof Solar) {
                                                                if (((Solar) t.getPropiedad2()).getEdificios().size() > 0)
                                                                    throw new TratoExcepcion("El trato no puede ser aceptado: " + t.getJugador2().getNombre() + " debe vender antes los edificios de " + t.getPropiedad2().getNombre() + ".");
                                                            }
                                                            Propiedad propiedad1 = null, propiedad2 = null;
                                                            for (Propiedad p : t.getJugador1().getPropiedades()) {
                                                                if (p.getNombre().equals(t.getPropiedad1().getNombre()))
                                                                    propiedad1 = p;
                                                            }
                                                            for (Propiedad p : t.getJugador2().getPropiedades()) {
                                                                if (p.getNombre().equals(t.getPropiedad2().getNombre()))
                                                                    propiedad2 = p;
                                                            }
                                                            if (propiedad1 != null) {
                                                                t.getJugador1().anadirPropiedad(t.getPropiedad2());
                                                                t.getJugador2().eliminarPropiedad(t.getPropiedad2());
                                                            } else {
                                                                t.getJugador1().getHipotecas().add(t.getPropiedad2());
                                                                t.getJugador2().getHipotecas().remove(t.getPropiedad2());
                                                            }
                                                            if (propiedad2 != null) {
                                                                t.getJugador2().anadirPropiedad(t.getPropiedad1());
                                                                t.getJugador1().eliminarPropiedad(t.getPropiedad1());
                                                            } else {
                                                                t.getJugador2().getHipotecas().add(t.getPropiedad1());
                                                                t.getJugador1().getHipotecas().remove(t.getPropiedad1());
                                                            }
                                                            t.getPropiedad2().setPropietario(t.getJugador1());
                                                            t.getPropiedad1().setPropietario(t.getJugador2());
                                                            t.getJugador1().setFortuna(t.getJugador1().getFortuna() - t.getDinero1());
                                                            t.getJugador2().setFortuna(t.getJugador2().getFortuna() + t.getDinero1());
                                                            Juego.consola.imprimirln("Se ha aceptado el siguiente trato con " + t.getJugador1().getNombre() + ": le doy " + t.getPropiedad2().getNombre() + " y " + t.getJugador1().getNombre() + " me da " + t.getPropiedad1().getNombre() + " y " + t.getDinero1() + "€.");
                                                        } else
                                                            throw new SinDinero("El trato no puede ser aceptado: " + t.getJugador1().getNombre() + " no dispone de " + t.getDinero1() + "€.");
                                                    } else
                                                        throw new NoPropiedad("El trato no puede ser aceptado: " + t.getPropiedad2().getNombre() + " no pertenece a " + t.getJugador2().getNombre() + ".");
                                                } else
                                                    throw new NoPropiedad("El trato no puede ser aceptado: " + t.getPropiedad1().getNombre() + " no pertenece a " + t.getJugador1().getNombre() + ".");
                                            } else if (t.getDinero2() != 0) {
                                                if (t.getPropiedad1().getPropietario().getNombre().equals(t.getJugador1().getNombre())) {
                                                    if (t.getPropiedad2().getPropietario().getNombre().equals(t.getJugador2().getNombre())) {
                                                        if (t.getJugador2().getFortuna() >= t.getDinero2()) {
                                                            if (t.getPropiedad1() instanceof Solar) {
                                                                if (((Solar) t.getPropiedad1()).getEdificios().size() > 0)
                                                                    throw new TratoExcepcion("El trato no puede ser aceptado: " + t.getJugador1().getNombre() + " debe vender antes los edificios de " + t.getPropiedad1().getNombre() + ".");
                                                            }
                                                            if (t.getPropiedad2() instanceof Solar) {
                                                                if (((Solar) t.getPropiedad2()).getEdificios().size() > 0)
                                                                    throw new TratoExcepcion("El trato no puede ser aceptado: " + t.getJugador2().getNombre() + " debe vender antes los edificios de " + t.getPropiedad2().getNombre() + ".");
                                                            }
                                                            Propiedad propiedad1 = null, propiedad2 = null;
                                                            for (Propiedad p : t.getJugador1().getPropiedades()) {
                                                                if (p.getNombre().equals(t.getPropiedad1().getNombre()))
                                                                    propiedad1 = p;
                                                            }
                                                            for (Propiedad p : t.getJugador2().getPropiedades()) {
                                                                if (p.getNombre().equals(t.getPropiedad2().getNombre()))
                                                                    propiedad2 = p;
                                                            }
                                                            if (propiedad1 != null) {
                                                                t.getJugador1().anadirPropiedad(t.getPropiedad2());
                                                                t.getJugador2().eliminarPropiedad(t.getPropiedad2());
                                                            } else {
                                                                t.getJugador1().getHipotecas().add(t.getPropiedad2());
                                                                t.getJugador2().getHipotecas().remove(t.getPropiedad2());
                                                            }
                                                            if (propiedad2 != null) {
                                                                t.getJugador2().anadirPropiedad(t.getPropiedad1());
                                                                t.getJugador1().eliminarPropiedad(t.getPropiedad1());
                                                            } else {
                                                                t.getJugador2().getHipotecas().add(t.getPropiedad1());
                                                                t.getJugador1().getHipotecas().remove(t.getPropiedad1());
                                                            }
                                                            t.getPropiedad2().setPropietario(t.getJugador1());
                                                            t.getPropiedad1().setPropietario(t.getJugador2());
                                                            t.getJugador1().setFortuna(t.getJugador1().getFortuna() + t.getDinero2());
                                                            t.getJugador2().setFortuna(t.getJugador2().getFortuna() - t.getDinero2());
                                                            Juego.consola.imprimirln("Se ha aceptado el siguiente trato con " + t.getJugador1().getNombre() + ": le doy " + t.getPropiedad2().getNombre() + " y " + t.getDinero2() + "€ y " + t.getJugador1().getNombre() + " me da " + t.getPropiedad1().getNombre() + ".");
                                                        } else
                                                            throw new SinDinero("El trato no puede ser aceptado: " + t.getJugador1().getNombre() + " no dispone de " + t.getDinero1() + "€.");
                                                    } else
                                                        throw new NoPropiedad("El trato no puede ser aceptado: " + t.getPropiedad2().getNombre() + " no pertenece a " + t.getJugador2().getNombre() + ".");
                                                } else
                                                    throw new NoPropiedad("El trato no puede ser aceptado: " + t.getPropiedad1().getNombre() + " no pertenece a " + t.getJugador1().getNombre() + ".");
                                            }
                                        } else if (t.getPropiedad1() != null && t.getPropiedad2() != null && t.getPropiedad3() != null) {
                                            if (t.getPropiedad1().getPropietario().getNombre().equals(t.getJugador1().getNombre())) {
                                                if (t.getPropiedad2().getPropietario().getNombre().equals(t.getJugador2().getNombre())) {
                                                    if (t.getPropiedad3().getPropietario().getNombre().equals(t.getJugador2().getNombre())) {
                                                        if (t.getPropiedad1() instanceof Solar) {
                                                            if (((Solar) t.getPropiedad1()).getEdificios().size() > 0)
                                                                throw new TratoExcepcion("El trato no puede ser aceptado: " + t.getJugador1().getNombre() + " debe vender antes los edificios de " + t.getPropiedad1().getNombre() + ".");
                                                        }
                                                        if (t.getPropiedad2() instanceof Solar) {
                                                            if (((Solar) t.getPropiedad2()).getEdificios().size() > 0)
                                                                throw new TratoExcepcion("El trato no puede ser aceptado: " + t.getJugador2().getNombre() + " debe vender antes los edificios de " + t.getPropiedad2().getNombre() + ".");
                                                        }
                                                        Propiedad propiedad1 = null, propiedad2 = null;
                                                        for (Propiedad p : t.getJugador1().getPropiedades()) {
                                                            if (p.getNombre().equals(t.getPropiedad1().getNombre()))
                                                                propiedad1 = p;
                                                        }
                                                        for (Propiedad p : t.getJugador2().getPropiedades()) {
                                                            if (p.getNombre().equals(t.getPropiedad2().getNombre()))
                                                                propiedad2 = p;
                                                        }
                                                        if (propiedad1 != null) {
                                                            t.getJugador1().anadirPropiedad(t.getPropiedad2());
                                                            t.getJugador2().eliminarPropiedad(t.getPropiedad2());
                                                        } else {
                                                            t.getJugador1().getHipotecas().add(t.getPropiedad2());
                                                            t.getJugador2().getHipotecas().remove(t.getPropiedad2());
                                                        }
                                                        if (propiedad2 != null) {
                                                            t.getJugador2().anadirPropiedad(t.getPropiedad1());
                                                            t.getJugador1().eliminarPropiedad(t.getPropiedad1());
                                                        } else {
                                                            t.getJugador2().getHipotecas().add(t.getPropiedad1());
                                                            t.getJugador1().getHipotecas().remove(t.getPropiedad1());
                                                        }
                                                        t.getPropiedad2().setPropietario(t.getJugador1());
                                                        t.getPropiedad1().setPropietario(t.getJugador2());
                                                        t.getPropiedad3().setContadorNoCobrarTrato(t.getNumero_turnos());
                                                        t.getJugador1().getAvatar().setNoCobrarTrato(true);
                                                        Juego.consola.imprimirln("Se ha aceptado el siguiente trato con " + t.getJugador1().getNombre() + ": le doy " + t.getPropiedad2().getNombre() + " y no le cobro el alquiler en " + t.getPropiedad3().getNombre() + " durante " + t.getNumero_turnos() + " turno(s) y " + t.getJugador1().getNombre() + " me da " + t.getPropiedad1().getNombre() + ".");
                                                    } else
                                                        throw new NoPropiedad("El trato no puede ser aceptado: " + t.getPropiedad3().getNombre() + " no pertenece a " + t.getJugador2().getNombre() + ".");
                                                } else
                                                    throw new NoPropiedad("El trato no puede ser aceptado: " + t.getPropiedad2().getNombre() + " no pertenece a " + t.getJugador2().getNombre() + ".");
                                            } else
                                                throw new NoPropiedad("El trato no puede ser aceptado: " + t.getPropiedad1().getNombre() + " no pertenece a " + t.getJugador1().getNombre() + ".");
                                        }
                                        trato.remove(t);
                                        break;
                                    } else
                                        throw new TratoExcepcion("No existe el trato indicado.");
                                } else
                                    throw new TratoExcepcion("No puedes eliminar un trato que no va dirigido a ti");
                            }
                        }
                    } else
                        throw new TratoExcepcion("Aún no te han propuesto ningún trato, " + jugadorturno.getNombre() + ".");
                } else
                    throw new TratoExcepcion("Aún no te han propuesto ningún trato, " + jugadorturno.getNombre() + ".");
            } else
                throw new JuegoNoComenzado("El juego aún no ha comenzado.");
        } else
            throw new ComandoInexistente("Comando Incorrecto");
    }

    public void tratos(String[] partes) throws Excepcion {
        int n = 0;
        if (partes.length == 1) {
            if (nocrearjugador) {
                if (jugadorestrato.contains(jugadorturno)) {
                    for (Trato t : trato) {
                        if (!t.getJugador1().getNombre().equals(jugadorturno.getNombre())) {
                            if (t.getJugador2().getNombre().equals(jugadorturno.getNombre())) {
                                consola.imprimirln("{\n\tjugadorPropone: " + t.getJugador1().getNombre() + ",\n\t" + t.getId() + ": " + t.getMensaje());
                                consola.imprimirln("}");
                                n++;
                            }
                        }
                    }
                    if (n == 0)
                        throw new TratoExcepcion("Aún no te han propuesto ningún trato, " + jugadorturno.getNombre() + ".");
                } else
                    throw new TratoExcepcion("Aún no te han propuesto ningún trato, " + jugadorturno.getNombre() + ".");
            } else
                throw new JuegoNoComenzado("El juego aún no ha comenzado.");
        } else
            throw new ComandoInexistente("Comando Incorrecto");
    }

    public void eliminar(String[] partes) throws Excepcion {
        if (partes.length == 2) {
            if (nocrearjugador) {
                if (trato.size() > 0) {
                    if (jugadorestrato.contains(jugadorturno)) {
                        for (Trato t : trato) {
                            if (partes[1].equals(t.getId())) {
                                if (!t.getJugador1().getNombre().equals(jugadorturno.getNombre())) {
                                    if (t.getJugador2().getNombre().equals(jugadorturno.getNombre())) {
                                        consola.imprimirln("Se ha eliminado el " + t.getId() + ".");
                                        trato.remove(t);
                                        break;
                                    } else
                                        throw new TratoExcepcion("No puedes eliminar un trato que no va dirigido a ti");
                                } else
                                    throw new TratoExcepcion("No puedes eliminar un trato que has propuesto.");
                            }
                        }
                    } else
                        throw new TratoExcepcion("Aún no te han propuesto ningún trato, " + jugadorturno.getNombre() + ".");
                } else
                    throw new TratoExcepcion("Aún no te han propuesto ningún trato, " + jugadorturno.getNombre() + ".");
            } else
                throw new JuegoNoComenzado("El juego aún no ha comenzado.");
        } else
            throw new ComandoInexistente("Comando Incorrecto");
    }

    public void estadisticas(String[] partes) throws Excepcion {
        if (partes.length == 2) {
            for (Jugador j : jugadores) {
                if (jugadores != null && !jugadores.isEmpty()) {
                    if (partes[1].equals(j.getNombre())) {
                        consola.imprimir("{");
                        consola.imprimir("dineroInvertido: " + j.getDineroGastado() + ",");
                        consola.imprimir("pagoTasasEImpuestos: " + j.getPagoTasasEImpuestos() + ",");
                        consola.imprimir("pagoDeAlquileres: " + j.getPagoDeAlquileres() + ",");
                        consola.imprimir("cobroDeAlquileres: " + j.getCobroDeAlquileres() + ",");
                        consola.imprimir("pasarPorCasillaDeSalida: " + j.getPasarPorCasillaDeSalida() + ",");
                        consola.imprimir("premiosInversionesOBote: " + j.getPremiosInversionesOBote() + ",");
                        consola.imprimir("vecesEnLaCarcel: " + j.getVecesEnLaCarcel());
                        consola.imprimir("}");
                    }
                }
            }
        } else if (partes.length == 1) {
            if (jugadores.size() > 1 && jugadores.size() < 7) {
                double dineroEnCabeza, dineroJugador, cobradoGrupos = 0, cobradoGrupoMasRentable = 0;
                Jugador jugadorMasVueltas = jugadores.get(0);
                Jugador jugadorMasVecesDados = jugadores.get(0);
                Jugador jugadorEnCabeza = jugadores.get(0);
                Casilla casillaMasRentable = tablero.getCasillas().get(1);
                Grupo grupoMasRentable = tablero.getGrupos().get(0);
                for (Casilla c : tablero.getGrupos().get(0).getCasillas()) {
                    if (c instanceof Solar)
                        cobradoGrupoMasRentable += ((Solar) c).getCobradoEnAlquiler();
                    else if (c instanceof Transporte)
                        cobradoGrupoMasRentable += ((Transporte) c).getCobradoEnAlquiler();
                    else if (c instanceof Servicio)
                        cobradoGrupoMasRentable += ((Servicio) c).getCobradoEnAlquiler();
                }
                Casilla casillaMasFrecuentada = tablero.getCasillas().get(0);
                consola.imprimir("{");
                for (Casilla c : tablero.getCasillas()) {
                    if (casillaMasRentable instanceof Solar && c instanceof Solar) {
                        if (((Solar) casillaMasRentable).getCobradoEnAlquiler() < ((Solar) c).getCobradoEnAlquiler()) {
                            casillaMasRentable = c;
                        }
                    } else if (casillaMasRentable instanceof Solar && c instanceof Transporte) {
                        if (((Solar) casillaMasRentable).getCobradoEnAlquiler() < ((Transporte) c).getCobradoEnAlquiler()) {
                            casillaMasRentable = c;
                        }
                    } else if (casillaMasRentable instanceof Solar && c instanceof Servicio) {
                        if (((Solar) casillaMasRentable).getCobradoEnAlquiler() < ((Servicio) c).getCobradoEnAlquiler()) {
                            casillaMasRentable = c;
                        }
                    } else if (casillaMasRentable instanceof Transporte && c instanceof Solar) {
                        if (((Transporte) casillaMasRentable).getCobradoEnAlquiler() < ((Solar) c).getCobradoEnAlquiler()) {
                            casillaMasRentable = c;
                        }
                    } else if (casillaMasRentable instanceof Transporte && c instanceof Transporte) {
                        if (((Transporte) casillaMasRentable).getCobradoEnAlquiler() < ((Transporte) c).getCobradoEnAlquiler()) {
                            casillaMasRentable = c;
                        }
                    } else if (casillaMasRentable instanceof Transporte && c instanceof Servicio) {
                        if (((Transporte) casillaMasRentable).getCobradoEnAlquiler() < ((Servicio) c).getCobradoEnAlquiler()) {
                            casillaMasRentable = c;
                        }
                    } else if (casillaMasRentable instanceof Servicio && c instanceof Solar) {
                        if (((Servicio) casillaMasRentable).getCobradoEnAlquiler() < ((Solar) c).getCobradoEnAlquiler()) {
                            casillaMasRentable = c;
                        }
                    } else if (casillaMasRentable instanceof Servicio && c instanceof Transporte) {
                        if (((Servicio) casillaMasRentable).getCobradoEnAlquiler() < ((Transporte) c).getCobradoEnAlquiler()) {
                            casillaMasRentable = c;
                        }
                    } else if (casillaMasRentable instanceof Servicio && c instanceof Servicio) {
                        if (((Servicio) casillaMasRentable).getCobradoEnAlquiler() < ((Servicio) c).getCobradoEnAlquiler()) {
                            casillaMasRentable = c;
                        }
                    }
                }
                consola.imprimir("casillaMasRentable: " + casillaMasRentable.getNombre() + ",");
                for (int i = 1; i < tablero.getGrupos().size(); i++) {
                    for (Casilla c : tablero.getGrupos().get(i).getCasillas()) {
                        if (c instanceof Solar)
                            cobradoGrupos += ((Solar) c).getCobradoEnAlquiler();
                        else if (c instanceof Transporte)
                            cobradoGrupos += ((Transporte) c).getCobradoEnAlquiler();
                        else if (c instanceof Servicio)
                            cobradoGrupos += ((Servicio) c).getCobradoEnAlquiler();
                    }
                    if (cobradoGrupoMasRentable < cobradoGrupos) {
                        grupoMasRentable = tablero.getGrupos().get(i);
                    }
                    cobradoGrupos = 0;
                }
                consola.imprimir("grupoMasRentable: " + grupoMasRentable.getNombre() + ",");
                for (int i = 1; i < tablero.getCasillas().size(); i++) {
                    if (casillaMasFrecuentada.getVisitacasillas() < tablero.getCasillas().get(i).getVisitacasillas()) {
                        casillaMasFrecuentada = tablero.getCasillas().get(i);
                    }
                }
                consola.imprimir("casillaMasFrecuentada: " + casillaMasFrecuentada.getNombre() + ",");
                for (Jugador j : jugadores) {
                    if (jugadorMasVueltas.getCuentavueltas() < j.getCuentavueltas()) {
                        jugadorMasVueltas = j;
                    } else if (jugadorMasVueltas.getCuentavueltas() == j.getCuentavueltas() && jugadorMasVueltas.getAvatar().getPosicion() < j.getAvatar().getPosicion()) {
                        jugadorMasVueltas = j;
                    }
                }
                consola.imprimir("jugadorMasVueltas: " + jugadorMasVueltas.getNombre() + ",");
                for (Jugador j : jugadores) {
                    if (jugadorMasVecesDados.getCuentadados() < j.getCuentadados()) {
                        jugadorMasVecesDados = j;
                    }
                }
                consola.imprimir("jugadorMasVecesDados: " + jugadorMasVecesDados.getNombre() + ",");
                for (Jugador j : jugadores) {
                    dineroEnCabeza = jugadorEnCabeza.getFortuna();
                    for (Propiedad pc : jugadorEnCabeza.getPropiedades()) {
                        dineroEnCabeza += pc.getCoste();
                        if (((Solar) pc).getEdificios().size() != 0) {
                            for (int k = 0; k < ((Solar) pc).getEdificios().size(); k++) {
                                if (((Solar) pc).getEdificios().get(k) instanceof Casa) {
                                    if (pc.getGrupo().getNombre().equals("Negro"))
                                        dineroEnCabeza += Valores.getCasaGrupoNegro();
                                    else if (pc.getGrupo().getNombre().equals("Cian"))
                                        dineroEnCabeza += Valores.getCasaGrupoCian();
                                    else if (pc.getGrupo().getNombre().equals("Violeta"))
                                        dineroEnCabeza += Valores.getCasaGrupoVioleta();
                                    else if (pc.getGrupo().getNombre().equals("Amarillo"))
                                        dineroEnCabeza += Valores.getCasaGrupoAmarillo();
                                    else if (pc.getGrupo().getNombre().equals("Rojo"))
                                        dineroEnCabeza += Valores.getCasaGrupoRojo();
                                    else if (pc.getGrupo().getNombre().equals("Gris"))
                                        dineroEnCabeza += Valores.getCasaGrupoGris();
                                    else if (pc.getGrupo().getNombre().equals("Verde"))
                                        dineroEnCabeza += Valores.getCasaGrupoVerde();
                                    else if (pc.getGrupo().getNombre().equals("Azul"))
                                        dineroEnCabeza += Valores.getCasaGrupoAzul();
                                } else if (((Solar) pc).getEdificios().get(k) instanceof Hotel) {
                                    if (pc.getGrupo().getNombre().equals("Negro"))
                                        dineroEnCabeza += Valores.getHotelGrupoNegro();
                                    else if (pc.getGrupo().getNombre().equals("Cian"))
                                        dineroEnCabeza += Valores.getHotelGrupoCian();
                                    else if (pc.getGrupo().getNombre().equals("Violeta"))
                                        dineroEnCabeza += Valores.getHotelGrupoVioleta();
                                    else if (pc.getGrupo().getNombre().equals("Amarillo"))
                                        dineroEnCabeza += Valores.getHotelGrupoAmarillo();
                                    else if (pc.getGrupo().getNombre().equals("Rojo"))
                                        dineroEnCabeza += Valores.getHotelGrupoRojo();
                                    else if (pc.getGrupo().getNombre().equals("Gris"))
                                        dineroEnCabeza += Valores.getHotelGrupoGris();
                                    else if (pc.getGrupo().getNombre().equals("Verde"))
                                        dineroEnCabeza += Valores.getHotelGrupoVerde();
                                    else if (pc.getGrupo().getNombre().equals("Azul"))
                                        dineroEnCabeza += Valores.getHotelGrupoAzul();
                                } else if (((Solar) pc).getEdificios().get(k) instanceof Piscina) {
                                    if (pc.getGrupo().getNombre().equals("Negro"))
                                        dineroEnCabeza += Valores.getPiscinaGrupoNegro();
                                    else if (pc.getGrupo().getNombre().equals("Cian"))
                                        dineroEnCabeza += Valores.getPiscinaGrupoCian();
                                    else if (pc.getGrupo().getNombre().equals("Violeta"))
                                        dineroEnCabeza += Valores.getPiscinaGrupoVioleta();
                                    else if (pc.getGrupo().getNombre().equals("Amarillo"))
                                        dineroEnCabeza += Valores.getPiscinaGrupoAmarillo();
                                    else if (pc.getGrupo().getNombre().equals("Rojo"))
                                        dineroEnCabeza += Valores.getPiscinaGrupoRojo();
                                    else if (pc.getGrupo().getNombre().equals("Gris"))
                                        dineroEnCabeza += Valores.getPiscinaGrupoGris();
                                    else if (pc.getGrupo().getNombre().equals("Verde"))
                                        dineroEnCabeza += Valores.getPiscinaGrupoVerde();
                                    else if (pc.getGrupo().getNombre().equals("Azul"))
                                        dineroEnCabeza += Valores.getPiscinaGrupoAzul();
                                } else {
                                    if (pc.getGrupo().getNombre().equals("Negro"))
                                        dineroEnCabeza += Valores.getPistaDeDeporteGrupoNegro();
                                    else if (pc.getGrupo().getNombre().equals("Cian"))
                                        dineroEnCabeza += Valores.getPistaDeDeporteGrupoCian();
                                    else if (pc.getGrupo().getNombre().equals("Violeta"))
                                        dineroEnCabeza += Valores.getPistaDeDeporteGrupoVioleta();
                                    else if (pc.getGrupo().getNombre().equals("Amarillo"))
                                        dineroEnCabeza += Valores.getPistaDeDeporteGrupoAmarillo();
                                    else if (pc.getGrupo().getNombre().equals("Rojo"))
                                        dineroEnCabeza += Valores.getPistaDeDeporteGrupoRojo();
                                    else if (pc.getGrupo().getNombre().equals("Gris"))
                                        dineroEnCabeza += Valores.getPistaDeDeporteGrupoGris();
                                    else if (pc.getGrupo().getNombre().equals("Verde"))
                                        dineroEnCabeza += Valores.getPistaDeDeporteGrupoVerde();
                                    else if (pc.getGrupo().getNombre().equals("Azul"))
                                        dineroEnCabeza += Valores.getPistaDeDeporteGrupoAzul();
                                }
                            }
                        }
                    }
                    dineroJugador = j.getFortuna();
                    for (Propiedad p : j.getPropiedades()) {
                        dineroJugador += p.getCoste();
                        if (((Solar) p).getEdificios().size() != 0) {
                            for (int k = 0; k < ((Solar) p).getEdificios().size(); k++) {
                                if (((Solar) p).getEdificios().get(k) instanceof Casa) {
                                    if (p.getGrupo().getNombre().equals("Negro"))
                                        dineroJugador += Valores.getCasaGrupoNegro();
                                    else if (p.getGrupo().getNombre().equals("Cian"))
                                        dineroJugador += Valores.getCasaGrupoCian();
                                    else if (p.getGrupo().getNombre().equals("Violeta"))
                                        dineroJugador += Valores.getCasaGrupoVioleta();
                                    else if (p.getGrupo().getNombre().equals("Amarillo"))
                                        dineroJugador += Valores.getCasaGrupoAmarillo();
                                    else if (p.getGrupo().getNombre().equals("Rojo"))
                                        dineroJugador += Valores.getCasaGrupoRojo();
                                    else if (p.getGrupo().getNombre().equals("Gris"))
                                        dineroJugador += Valores.getCasaGrupoGris();
                                    else if (p.getGrupo().getNombre().equals("Verde"))
                                        dineroJugador += Valores.getCasaGrupoVerde();
                                    else if (p.getGrupo().getNombre().equals("Azul"))
                                        dineroJugador += Valores.getCasaGrupoAzul();
                                } else if (((Solar) p).getEdificios().get(k) instanceof Hotel) {
                                    if (p.getGrupo().getNombre().equals("Negro"))
                                        dineroJugador += Valores.getHotelGrupoNegro();
                                    else if (p.getGrupo().getNombre().equals("Cian"))
                                        dineroJugador += Valores.getHotelGrupoCian();
                                    else if (p.getGrupo().getNombre().equals("Violeta"))
                                        dineroJugador += Valores.getHotelGrupoVioleta();
                                    else if (p.getGrupo().getNombre().equals("Amarillo"))
                                        dineroJugador += Valores.getHotelGrupoAmarillo();
                                    else if (p.getGrupo().getNombre().equals("Rojo"))
                                        dineroJugador += Valores.getHotelGrupoRojo();
                                    else if (p.getGrupo().getNombre().equals("Gris"))
                                        dineroJugador += Valores.getHotelGrupoGris();
                                    else if (p.getGrupo().getNombre().equals("Verde"))
                                        dineroJugador += Valores.getHotelGrupoVerde();
                                    else if (p.getGrupo().getNombre().equals("Azul"))
                                        dineroJugador += Valores.getHotelGrupoAzul();
                                } else if (((Solar) p).getEdificios().get(k) instanceof Piscina) {
                                    if (p.getGrupo().getNombre().equals("Negro"))
                                        dineroJugador += Valores.getPiscinaGrupoNegro();
                                    else if (p.getGrupo().getNombre().equals("Cian"))
                                        dineroJugador += Valores.getPiscinaGrupoCian();
                                    else if (p.getGrupo().getNombre().equals("Violeta"))
                                        dineroJugador += Valores.getPiscinaGrupoVioleta();
                                    else if (p.getGrupo().getNombre().equals("Amarillo"))
                                        dineroJugador += Valores.getPiscinaGrupoAmarillo();
                                    else if (p.getGrupo().getNombre().equals("Rojo"))
                                        dineroJugador += Valores.getPiscinaGrupoRojo();
                                    else if (p.getGrupo().getNombre().equals("Gris"))
                                        dineroJugador += Valores.getPiscinaGrupoGris();
                                    else if (p.getGrupo().getNombre().equals("Verde"))
                                        dineroJugador += Valores.getPiscinaGrupoVerde();
                                    else if (p.getGrupo().getNombre().equals("Azul"))
                                        dineroJugador += Valores.getPiscinaGrupoAzul();
                                } else {
                                    if (p.getGrupo().getNombre().equals("Negro"))
                                        dineroJugador += Valores.getPistaDeDeporteGrupoNegro();
                                    else if (p.getGrupo().getNombre().equals("Cian"))
                                        dineroJugador += Valores.getPistaDeDeporteGrupoCian();
                                    else if (p.getGrupo().getNombre().equals("Violeta"))
                                        dineroJugador += Valores.getPistaDeDeporteGrupoVioleta();
                                    else if (p.getGrupo().getNombre().equals("Amarillo"))
                                        dineroJugador += Valores.getPistaDeDeporteGrupoAmarillo();
                                    else if (p.getGrupo().getNombre().equals("Rojo"))
                                        dineroJugador += Valores.getPistaDeDeporteGrupoRojo();
                                    else if (p.getGrupo().getNombre().equals("Gris"))
                                        dineroJugador += Valores.getPistaDeDeporteGrupoGris();
                                    else if (p.getGrupo().getNombre().equals("Verde"))
                                        dineroJugador += Valores.getPistaDeDeporteGrupoVerde();
                                    else if (p.getGrupo().getNombre().equals("Azul"))
                                        dineroJugador += Valores.getPistaDeDeporteGrupoAzul();
                                }
                            }
                        }
                    }
                    if (dineroEnCabeza < dineroJugador) {
                        jugadorEnCabeza = j;
                    }
                }
                consola.imprimir("jugadorEnCabeza: " + jugadorEnCabeza.getNombre());
                consola.imprimir("}");
            } else
                throw new JuegoNoComenzado("Es necesario que el número de jugadores esté entre 2 y 6.");
        } else
            throw new ComandoInexistente("Comando incorrecto.");
    }

    public void cambiar(String[] partes) throws Excepcion {
        if (partes.length == 2) {
            if (jugadores.size() > 1 && jugadores.size() < 7) {
                if (tablero.getDado().isPuedolanzar()) {
                    if (jugadorturno.getAvatar() instanceof Coche) {
                        if (!((Coche) jugadorturno.getAvatar()).isMovimientoespecialcoche())
                            jugadorturno.getAvatar().moverEnAvanzado();
                        else
                            jugadorturno.getAvatar().moverEnBasico();
                    } else if (jugadorturno.getAvatar() instanceof Pelota) {
                        if (!((Pelota) jugadorturno.getAvatar()).isMovimientoespecialpelota())
                            jugadorturno.getAvatar().moverEnAvanzado();
                        else
                            jugadorturno.getAvatar().moverEnBasico();
                    } else if (jugadorturno.getAvatar() instanceof Sombrero) {
                        if (!((Sombrero) jugadorturno.getAvatar()).isMovimientoespecialsombrero())
                            jugadorturno.getAvatar().moverEnAvanzado();
                        else
                            jugadorturno.getAvatar().moverEnBasico();
                    } else if (jugadorturno.getAvatar() instanceof Esfinge) {
                        if (!((Esfinge) jugadorturno.getAvatar()).isMovimientoespecialesfinge())
                            jugadorturno.getAvatar().moverEnAvanzado();
                        else
                            jugadorturno.getAvatar().moverEnBasico();
                    }
                } else
                    throw new LanzarExcepcion("No es tu turno.");
            } else
                throw new JuegoNoComenzado("El juego aún no ha comenzado.");
        } else
            throw new ComandoInexistente("Comando incorrecto.");
    }

    public void salir(String[] partes) throws Excepcion {
        if (partes.length == 2) {
            if (partes[1].equals("carcel")) {
                if (nocrearjugador) {
                    if (tablero.getDado().isPuedolanzar()) {
                        if (jugadorturno.getAvatar().isJugadorcarcel()) {
                            tablero.salir_carcel(jugadorturno);
                        } else
                            throw new LanzarExcepcion("El jugador no está en la cárcel.");
                    } else
                        throw new LanzarExcepcion("No es tu turno.");
                } else
                    throw new JuegoNoComenzado("El juego aún no ha comenzado.");
            } else
                throw new ComandoInexistente("Comando incorrecto.");
        } else if (partes.length == 1) {
            consola.imprimir("\nGracias por jugar. ¡Hasta la próxima!");
            System.exit(0);
        } else
            throw new ComandoInexistente("Comando incorrecto.");
    }

    public void stop(String[] partes) throws Excepcion {
        if (partes.length == 1) {
            consola.leer("");
        } else
            throw new ComandoInexistente("Comando incorrecto.");
    }

    private double costeEdificio(String grupo, String tipo) {
        double coste = 0;
        switch (grupo) {
            case "Negro":
                switch (tipo) {
                    case "Casa":
                        coste = Valores.getCasaGrupoNegro();
                        break;
                    case "Hotel":
                        coste = Valores.getHotelGrupoNegro();
                        break;
                    case "Piscina":
                        coste = Valores.getPiscinaGrupoNegro();
                        break;
                    case "Pista":
                        coste = Valores.getPistaDeDeporteGrupoNegro();
                        break;
                }
                break;
            case "Cian":
                switch (tipo) {
                    case "Casa":
                        coste = Valores.getCasaGrupoCian();
                        break;
                    case "Hotel":
                        coste = Valores.getHotelGrupoCian();
                        break;
                    case "Piscina":
                        coste = Valores.getPiscinaGrupoCian();
                        break;
                    case "Pista":
                        coste = Valores.getPistaDeDeporteGrupoCian();
                        break;
                }
                break;
            case "Violeta":
                switch (tipo) {
                    case "Casa":
                        coste = Valores.getCasaGrupoVioleta();
                        break;
                    case "Hotel":
                        coste = Valores.getHotelGrupoVioleta();
                        break;
                    case "Piscina":
                        coste = Valores.getPiscinaGrupoVioleta();
                        break;
                    case "Pista":
                        coste = Valores.getPistaDeDeporteGrupoVioleta();
                        break;
                }
                break;
            case "Amarillo":
                switch (tipo) {
                    case "Casa":
                        coste = Valores.getCasaGrupoAmarillo();
                        break;
                    case "Hotel":
                        coste = Valores.getHotelGrupoAmarillo();
                        break;
                    case "Piscina":
                        coste = Valores.getPiscinaGrupoAmarillo();
                        break;
                    case "Pista":
                        coste = Valores.getPistaDeDeporteGrupoAmarillo();
                        break;
                }
                break;
            case "Rojo":
                switch (tipo) {
                    case "Casa":
                        coste = Valores.getCasaGrupoRojo();
                        break;
                    case "Hotel":
                        coste = Valores.getHotelGrupoRojo();
                        break;
                    case "Piscina":
                        coste = Valores.getPiscinaGrupoRojo();
                        break;
                    case "Pista":
                        coste = Valores.getPistaDeDeporteGrupoRojo();
                        break;
                }
                break;
            case "Gris":
                switch (tipo) {
                    case "Casa":
                        coste = Valores.getCasaGrupoGris();
                        break;
                    case "Hotel":
                        coste = Valores.getHotelGrupoGris();
                        break;
                    case "Piscina":
                        coste = Valores.getPiscinaGrupoGris();
                        break;
                    case "Pista":
                        coste = Valores.getPistaDeDeporteGrupoGris();
                        break;
                }
                break;
            case "Verde":
                switch (tipo) {
                    case "Casa":
                        coste = Valores.getCasaGrupoVerde();
                        break;
                    case "Hotel":
                        coste = Valores.getHotelGrupoVerde();
                        break;
                    case "Piscina":
                        coste = Valores.getPiscinaGrupoVerde();
                        break;
                    case "Pista":
                        coste = Valores.getPistaDeDeporteGrupoVerde();
                        break;
                }
                break;
            case "Azul":
                switch (tipo) {
                    case "Casa":
                        coste = Valores.getCasaGrupoAzul();
                        break;
                    case "Hotel":
                        coste = Valores.getHotelGrupoAzul();
                        break;
                    case "Piscina":
                        coste = Valores.getPiscinaGrupoAzul();
                        break;
                    case "Pista":
                        coste = Valores.getPistaDeDeporteGrupoAzul();
                        break;
                }
                break;
        }
        return coste;
    }

    private void imprimirEdificiosPorConstruir(int i, int j) {
        Casilla c = tablero.getGrupos().get(i).getCasillas().get(j);
        if (((Solar) c).getNumerocasas() == 0 && ((Solar) c).getNumerohoteles() == 0 && (tablero.getGrupos().get(i).getNombre().equals("Negro") || tablero.getGrupos().get(i).getNombre().equals("Azul"))) {
            consola.imprimir("Aún se pueden edificar cuatro casas, dos hoteles, dos piscinas y dos pistas de deporte.");
        } else if (((Solar) c).getNumerocasas() == 1 && ((Solar) c).getNumerohoteles() == 0 && (tablero.getGrupos().get(i).getNombre().equals("Negro") || tablero.getGrupos().get(i).getNombre().equals("Azul"))) {
            consola.imprimir("Aún se pueden edificar tres casas, dos hoteles, dos piscinas y dos pistas de deporte.");
        } else if (((Solar) c).getNumerocasas() == 2 && ((Solar) c).getNumerohoteles() == 0 && (tablero.getGrupos().get(i).getNombre().equals("Negro") || tablero.getGrupos().get(i).getNombre().equals("Azul"))) {
            consola.imprimir("Aún se pueden edificar dos casas, dos hoteles, dos piscinas y dos pistas de deporte.");
        } else if (((Solar) c).getNumerocasas() == 3 && ((Solar) c).getNumerohoteles() == 0 && (tablero.getGrupos().get(i).getNombre().equals("Negro") || tablero.getGrupos().get(i).getNombre().equals("Azul"))) {
            consola.imprimir("Aún se pueden edificar una casa, dos hoteles, dos piscinas y dos pistas de deporte.");
        } else if (((Solar) c).getNumerocasas() == 4 && ((Solar) c).getNumerohoteles() == 0 && (tablero.getGrupos().get(i).getNombre().equals("Negro") || tablero.getGrupos().get(i).getNombre().equals("Azul"))) {
            consola.imprimir("Aún se pueden edificar dos hoteles, dos piscinas y dos pistas de deporte. Ya no se pueden construír casas.");
        } else if (((Solar) c).getNumerocasas() == 0 && ((Solar) c).getNumerohoteles() == 0 && (tablero.getGrupos().get(i).getNombre().equals("Cian") || tablero.getGrupos().get(i).getNombre().equals("Violeta") || tablero.getGrupos().get(i).getNombre().equals("Amarillo") || tablero.getGrupos().get(i).getNombre().equals("Rojo") || tablero.getGrupos().get(i).getNombre().equals("Gris") || tablero.getGrupos().get(i).getNombre().equals("Verde"))) {
            consola.imprimir("Aún se pueden edificar cuatro casas, tres hoteles, tres piscinas y tres pistas de deporte.");
        } else if (((Solar) c).getNumerocasas() == 1 && ((Solar) c).getNumerohoteles() == 0 && (tablero.getGrupos().get(i).getNombre().equals("Cian") || tablero.getGrupos().get(i).getNombre().equals("Violeta") || tablero.getGrupos().get(i).getNombre().equals("Amarillo") || tablero.getGrupos().get(i).getNombre().equals("Rojo") || tablero.getGrupos().get(i).getNombre().equals("Gris") || tablero.getGrupos().get(i).getNombre().equals("Verde"))) {
            consola.imprimir("Aún se pueden edificar tres casas, tres hoteles, tres piscinas y tres pistas de deporte.");
        } else if (((Solar) c).getNumerocasas() == 2 && ((Solar) c).getNumerohoteles() == 0 && (tablero.getGrupos().get(i).getNombre().equals("Cian") || tablero.getGrupos().get(i).getNombre().equals("Violeta") || tablero.getGrupos().get(i).getNombre().equals("Amarillo") || tablero.getGrupos().get(i).getNombre().equals("Rojo") || tablero.getGrupos().get(i).getNombre().equals("Gris") || tablero.getGrupos().get(i).getNombre().equals("Verde"))) {
            consola.imprimir("Aún se pueden edificar dos casas, tres hoteles, tres piscinas y tres pistas de deporte.");
        } else if (((Solar) c).getNumerocasas() == 3 && ((Solar) c).getNumerohoteles() == 0 && (tablero.getGrupos().get(i).getNombre().equals("Cian") || tablero.getGrupos().get(i).getNombre().equals("Violeta") || tablero.getGrupos().get(i).getNombre().equals("Amarillo") || tablero.getGrupos().get(i).getNombre().equals("Rojo") || tablero.getGrupos().get(i).getNombre().equals("Gris") || tablero.getGrupos().get(i).getNombre().equals("Verde"))) {
            consola.imprimir("Aún se pueden edificar una casa, tres hoteles, tres piscinas y tres pistas de deporte.");
        } else if (((Solar) c).getNumerocasas() == 4 && ((Solar) c).getNumerohoteles() == 0 && (tablero.getGrupos().get(i).getNombre().equals("Cian") || tablero.getGrupos().get(i).getNombre().equals("Violeta") || tablero.getGrupos().get(i).getNombre().equals("Amarillo") || tablero.getGrupos().get(i).getNombre().equals("Rojo") || tablero.getGrupos().get(i).getNombre().equals("Gris") || tablero.getGrupos().get(i).getNombre().equals("Verde"))) {
            consola.imprimir("Aún se pueden edificar tres hoteles, tres piscinas y tres pistas de deporte. Ya no se pueden construír casas.");
        } else if (((Solar) c).getNumerocasas() == 0 && ((Solar) c).getNumerohoteles() == 1 && (tablero.getGrupos().get(i).getNombre().equals("Negro") || tablero.getGrupos().get(i).getNombre().equals("Azul"))) {
            consola.imprimir("Aún se pueden edificar cuatro casas, un hotel, dos piscinas y dos pistas de deporte.");
        } else if (((Solar) c).getNumerocasas() == 1 && ((Solar) c).getNumerohoteles() == 1 && (tablero.getGrupos().get(i).getNombre().equals("Negro") || tablero.getGrupos().get(i).getNombre().equals("Azul"))) {
            consola.imprimir("Aún se pueden edificar tres casas, un hotel, dos piscinas y dos pistas de deporte.");
        } else if (((Solar) c).getNumerocasas() == 2 && ((Solar) c).getNumerohoteles() == 1 && (tablero.getGrupos().get(i).getNombre().equals("Negro") || tablero.getGrupos().get(i).getNombre().equals("Azul"))) {
            if (((Solar) c).getNumeropiscinas() == 0) {
                consola.imprimir("Aún se pueden edificar dos casas, un hotel, dos piscinas y dos pistas de deporte.");
            } else if (((Solar) c).getNumeropiscinas() == 1) {
                consola.imprimir("Aún se pueden edificar dos casas, un hotel, una piscina y dos pistas de deporte.");
            } else {
                consola.imprimir("Aún se pueden edificar dos casas, un hotel y dos pistas de deporte. Ya no se pueden construír piscinas.");
            }
        } else if (((Solar) c).getNumerocasas() == 3 && ((Solar) c).getNumerohoteles() == 1 && (tablero.getGrupos().get(i).getNombre().equals("Negro") || tablero.getGrupos().get(i).getNombre().equals("Azul"))) {
            if (((Solar) c).getNumeropiscinas() == 0) {
                consola.imprimir("Aún se pueden edificar una casa, un hotel, dos piscinas y dos pistas de deporte.");
            } else if (((Solar) c).getNumeropiscinas() == 1) {
                consola.imprimir("Aún se pueden edificar una casa, un hotel, una piscina y dos pistas de deporte.");
            } else {
                consola.imprimir("Aún se pueden edificar una casa, un hotel y dos pistas de deporte. Ya no se pueden construír piscinas.");
            }
        } else if (((Solar) c).getNumerocasas() == 4 && ((Solar) c).getNumerohoteles() == 1 && (tablero.getGrupos().get(i).getNombre().equals("Negro") || tablero.getGrupos().get(i).getNombre().equals("Azul"))) {
            if (((Solar) c).getNumeropiscinas() == 0) {
                consola.imprimir("Aún se pueden edificar un hotel, dos piscinas y dos pistas de deporte. Ya no se pueden construír casas.");
            } else if (((Solar) c).getNumeropiscinas() == 1) {
                consola.imprimir("Aún se pueden edificar un hotel, una piscina y dos pistas de deporte. Ya no se pueden construír casas.");
            } else {
                consola.imprimir("Aún se pueden edificar un hotel y dos pistas de deporte. Ya no se pueden construír casas y piscinas.");
            }
        } else if (((Solar) c).getNumerocasas() == 0 && ((Solar) c).getNumerohoteles() == 1 && (tablero.getGrupos().get(i).getNombre().equals("Cian") || tablero.getGrupos().get(i).getNombre().equals("Violeta") || tablero.getGrupos().get(i).getNombre().equals("Amarillo") || tablero.getGrupos().get(i).getNombre().equals("Rojo") || tablero.getGrupos().get(i).getNombre().equals("Gris") || tablero.getGrupos().get(i).getNombre().equals("Verde"))) {
            if (((Solar) c).getNumeropiscinas() == 0) {
                consola.imprimir("Aún se pueden edificar cuatro casas, dos hoteles, tres piscinas y tres pistas de deporte.");
            } else if (((Solar) c).getNumeropiscinas() == 1) {
                consola.imprimir("Aún se pueden edificar cuatro casas, dos hoteles, dos piscinas y tres pistas de deporte.");
            } else if (((Solar) c).getNumeropiscinas() == 2) {
                consola.imprimir("Aún se pueden edificar cuatro casas, dos hoteles, una piscina y tres pistas de deporte.");
            } else {
                consola.imprimir("Aún se pueden edificar cuatro casas, dos hoteles y tres pistas de deporte. Ya no se pueden construír piscinas.");
            }
        } else if (((Solar) c).getNumerocasas() == 1 && ((Solar) c).getNumerohoteles() == 1 && (tablero.getGrupos().get(i).getNombre().equals("Cian") || tablero.getGrupos().get(i).getNombre().equals("Violeta") || tablero.getGrupos().get(i).getNombre().equals("Amarillo") || tablero.getGrupos().get(i).getNombre().equals("Rojo") || tablero.getGrupos().get(i).getNombre().equals("Gris") || tablero.getGrupos().get(i).getNombre().equals("Verde"))) {
            if (((Solar) c).getNumeropiscinas() == 0) {
                consola.imprimir("Aún se pueden edificar tres casas, dos hoteles, tres piscinas y tres pistas de deporte.");
            } else if (((Solar) c).getNumeropiscinas() == 1) {
                consola.imprimir("Aún se pueden edificar tres casas, dos hoteles, dos piscinas y tres pistas de deporte.");
            } else if (((Solar) c).getNumeropiscinas() == 2) {
                consola.imprimir("Aún se pueden edificar tres casas, dos hoteles, una piscina y tres pistas de deporte.");
            } else {
                consola.imprimir("Aún se pueden edificar tres casas, dos hoteles y tres pistas de deporte. Ya no se pueden construír piscinas.");
            }
        } else if (((Solar) c).getNumerocasas() == 2 && ((Solar) c).getNumerohoteles() == 1 && (tablero.getGrupos().get(i).getNombre().equals("Cian") || tablero.getGrupos().get(i).getNombre().equals("Violeta") || tablero.getGrupos().get(i).getNombre().equals("Amarillo") || tablero.getGrupos().get(i).getNombre().equals("Rojo") || tablero.getGrupos().get(i).getNombre().equals("Gris") || tablero.getGrupos().get(i).getNombre().equals("Verde"))) {
            if (((Solar) c).getNumeropiscinas() == 0) {
                consola.imprimir("Aún se pueden edificar dos casas, dos hoteles, tres piscinas y tres pistas de deporte.");
            } else if (((Solar) c).getNumeropiscinas() == 1) {
                consola.imprimir("Aún se pueden edificar dos casas, dos hoteles, dos piscinas y tres pistas de deporte.");
            } else if (((Solar) c).getNumeropiscinas() == 2) {
                consola.imprimir("Aún se pueden edificar dos casas, dos hoteles, una piscina y tres pistas de deporte.");
            } else {
                consola.imprimir("Aún se pueden edificar dos casas, dos hoteles y dos pistas tres deporte. Ya no se pueden construír piscinas.");
            }
        } else if (((Solar) c).getNumerocasas() == 3 && ((Solar) c).getNumerohoteles() == 1 && (tablero.getGrupos().get(i).getNombre().equals("Cian") || tablero.getGrupos().get(i).getNombre().equals("Violeta") || tablero.getGrupos().get(i).getNombre().equals("Amarillo") || tablero.getGrupos().get(i).getNombre().equals("Rojo") || tablero.getGrupos().get(i).getNombre().equals("Gris") || tablero.getGrupos().get(i).getNombre().equals("Verde"))) {
            if (((Solar) c).getNumeropiscinas() == 0) {
                consola.imprimir("Aún se pueden edificar una casa, dos hoteles, tres piscinas y tres pistas de deporte.");
            } else if (((Solar) c).getNumeropiscinas() == 1) {
                consola.imprimir("Aún se pueden edificar una casa, dos hoteles, dos piscinas y tres pistas de deporte.");
            } else if (((Solar) c).getNumeropiscinas() == 2) {
                consola.imprimir("Aún se pueden edificar una casa, dos hoteles, una piscina y tres pistas de deporte.");
            } else {
                consola.imprimir("Aún se pueden edificar una casa, dos hoteles y tres pistas de deporte. Ya no se pueden construír piscinas.");
            }
        } else if (((Solar) c).getNumerocasas() == 4 && ((Solar) c).getNumerohoteles() == 1 && (tablero.getGrupos().get(i).getNombre().equals("Cian") || tablero.getGrupos().get(i).getNombre().equals("Violeta") || tablero.getGrupos().get(i).getNombre().equals("Amarillo") || tablero.getGrupos().get(i).getNombre().equals("Rojo") || tablero.getGrupos().get(i).getNombre().equals("Gris") || tablero.getGrupos().get(i).getNombre().equals("Verde"))) {
            if (((Solar) c).getNumeropiscinas() == 0) {
                consola.imprimir("Aún se pueden edificar dos hoteles, tres piscinas y tres pistas de deporte. Ya no se pueden construír casas.");
            } else if (((Solar) c).getNumeropiscinas() == 1) {
                consola.imprimir("Aún se pueden edificar dos hoteles, dos piscinas y tres pistas de deporte. Ya no se pueden construír casas.");
            } else if (((Solar) c).getNumeropiscinas() == 2) {
                consola.imprimir("Aún se pueden edificar dos hoteles, una piscina y tres pistas de deporte. Ya no se pueden construír casas.");
            } else {
                consola.imprimir("Aún se pueden edificar dos hoteles y tres pistas de deporte. Ya no se pueden construír casas y piscinas.");
            }
        } else if (((Solar) c).getNumerocasas() == 0 && ((Solar) c).getNumerohoteles() == 2 && (tablero.getGrupos().get(i).getNombre().equals("Negro") || tablero.getGrupos().get(i).getNombre().equals("Azul"))) {
            if (((Solar) c).getNumeropiscinas() == 0) {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar dos casas, dos piscinas y dos pistas de deporte. Ya no se pueden construír hoteles.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar dos casas, dos piscinas y una pista de deporte. Ya no se pueden construír hoteles.");
                } else {
                    consola.imprimir("Aún se pueden edificar dos casas y dos piscinas. Ya no se pueden construír hoteles y pistas de deporte.");
                }
            } else if (((Solar) c).getNumeropiscinas() == 1) {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar dos casas, una piscina y dos pistas de deporte. Ya no se pueden construír hoteles.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar dos casas, una piscina y una pista de deporte. Ya no se pueden construír hoteles.");
                } else {
                    consola.imprimir("Aún se pueden edificar dos casas y una piscina. Ya no se pueden construír hoteles y pistas de deporte.");
                }
            } else {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar dos casas y dos pistas de deporte. Ya no se pueden construír hoteles y piscinas.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar dos casas y una pista de deporte. Ya no se pueden construír hoteles y piscinas.");
                } else {
                    consola.imprimir("Aún se pueden edificar dos casas. Ya no se pueden construír hoteles, piscinas y pistas de deporte.");
                }
            }
        } else if (((Solar) c).getNumerocasas() == 1 && ((Solar) c).getNumerohoteles() == 2 && (tablero.getGrupos().get(i).getNombre().equals("Negro") || tablero.getGrupos().get(i).getNombre().equals("Azul"))) {
            if (((Solar) c).getNumeropiscinas() == 0) {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar una casa, dos piscinas y dos pistas de deporte. Ya no se pueden construír hoteles.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar una casa, dos piscinas y una pista de deporte. Ya no se pueden construír hoteles.");
                } else {
                    consola.imprimir("Aún se pueden edificar una casa y dos piscinas. Ya no se pueden construír hoteles y pistas de deporte.");
                }
            } else if (((Solar) c).getNumeropiscinas() == 1) {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar una casa, una piscina y dos pistas de deporte. Ya no se pueden construír hoteles.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar una casa, una piscina y una pista de deporte. Ya no se pueden construír hoteles.");
                } else {
                    consola.imprimir("Aún se pueden edificar una casa y una piscina. Ya no se pueden construír hoteles y pistas de deporte.");
                }
            } else {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar una casa y dos pistas de deporte. Ya no se pueden construír hoteles y piscinas.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar una casa y una pista de deporte. Ya no se pueden construír hoteles y piscinas.");
                } else {
                    consola.imprimir("Aún se puede edificar una casa. Ya no se pueden construír hoteles, piscinas y pistas de deporte.");
                }
            }
        } else if (((Solar) c).getNumerocasas() == 2 && ((Solar) c).getNumerohoteles() == 2 && (tablero.getGrupos().get(i).getNombre().equals("Negro") || tablero.getGrupos().get(i).getNombre().equals("Azul"))) {
            if (((Solar) c).getNumeropiscinas() == 0) {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar dos piscinas y dos pistas de deporte. Ya no se pueden construír casas y hoteles.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar dos piscinas y una pista de deporte. Ya no se pueden construír casas y hoteles.");
                } else {
                    consola.imprimir("Aún se pueden edificar dos piscinas. Ya no se pueden construír casas, hoteles y pistas de deporte.");
                }
            } else if (((Solar) c).getNumeropiscinas() == 1) {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar una piscina y dos pistas de deporte. Ya no se pueden construír casas y hoteles.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar una piscina y una pista de deporte. Ya no se pueden construír casas y hoteles.");
                } else {
                    consola.imprimir("Aún se puede edificar una piscina. Ya no se pueden construír casas, hoteles y pistas de deporte.");
                }
            } else {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar dos pistas de deporte. Ya no se pueden construír casas, hoteles y piscinas.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se puede edificar una pista de deporte. Ya no se pueden construír casas, hoteles y piscinas.");
                } else {
                    consola.imprimir("Ya no se puede edificar.");
                }
            }
        } else if (((Solar) c).getNumerocasas() == 0 && ((Solar) c).getNumerohoteles() == 2 && (tablero.getGrupos().get(i).getNombre().equals("Cian") || tablero.getGrupos().get(i).getNombre().equals("Violeta") || tablero.getGrupos().get(i).getNombre().equals("Amarillo") || tablero.getGrupos().get(i).getNombre().equals("Rojo") || tablero.getGrupos().get(i).getNombre().equals("Gris") || tablero.getGrupos().get(i).getNombre().equals("Verde"))) {
            if (((Solar) c).getNumeropiscinas() == 0) {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar cuatro casas, un hotel, tres piscinas y tres pistas de deporte.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar cuatro casas, un hotel, tres piscinas y dos pistas de deporte.");
                } else if (((Solar) c).getNumeropistas() == 2) {
                    consola.imprimir("Aún se pueden edificar cuatro casas, un hotel, tres piscinas y una pista de deporte.");
                } else {
                    consola.imprimir("Aún se pueden edificar cuatro casas, un hotel, tres piscinas. Ya no se pueden construír pistas de deporte.");
                }
            } else if (((Solar) c).getNumeropiscinas() == 1) {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar cuatro casas, un hotel, dos piscinas y tres pistas de deporte.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar cuatro casas, un hotel, dos piscinas y dos pistas de deporte.");
                } else if (((Solar) c).getNumeropistas() == 2) {
                    consola.imprimir("Aún se pueden edificar cuatro casas, un hotel, dos piscinas y una pista de deporte.");
                } else {
                    consola.imprimir("Aún se pueden edificar cuatro casas, un hotel, dos piscinas. Ya no se pueden construír pistas de deporte.");
                }
            } else if (((Solar) c).getNumeropiscinas() == 2) {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar cuatro casas, un hotel, una piscina y tres pistas de deporte.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar cuatro casas, un hotel, una piscina y dos pistas de deporte.");
                } else if (((Solar) c).getNumeropistas() == 2) {
                    consola.imprimir("Aún se pueden edificar cuatro casas, un hotel, una piscina y una pista de deporte.");
                } else {
                    consola.imprimir("Aún se pueden edificar cuatro casas, un hotel, una piscina. Ya no se pueden construír pistas de deporte.");
                }
            } else {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar cuatro casas, un hotel y tres pistas de deporte. Ya no se pueden construír piscinas");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar cuatro casas, un hotel y dos pistas de deporte. Ya no se pueden construír piscinas.");
                } else if (((Solar) c).getNumeropistas() == 2) {
                    consola.imprimir("Aún se pueden edificar cuatro casas, un hotel y una pista de deporte. Ya no se pueden construír piscinas.");
                } else {
                    consola.imprimir("Aún se pueden edificar cuatro casas y un hotel. Ya no se pueden construír piscinas y pistas de deporte.");
                }
            }
        } else if (((Solar) c).getNumerocasas() == 1 && ((Solar) c).getNumerohoteles() == 2 && (tablero.getGrupos().get(i).getNombre().equals("Cian") || tablero.getGrupos().get(i).getNombre().equals("Violeta") || tablero.getGrupos().get(i).getNombre().equals("Amarillo") || tablero.getGrupos().get(i).getNombre().equals("Rojo") || tablero.getGrupos().get(i).getNombre().equals("Gris") || tablero.getGrupos().get(i).getNombre().equals("Verde"))) {
            if (((Solar) c).getNumeropiscinas() == 0) {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar tres casas, un hotel, tres piscinas y tres pistas de deporte.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar tres casas, un hotel, tres piscinas y dos pistas de deporte.");
                } else if (((Solar) c).getNumeropistas() == 2) {
                    consola.imprimir("Aún se pueden edificar tres casas, un hotel, tres piscinas y una pista de deporte.");
                } else {
                    consola.imprimir("Aún se pueden edificar tres casas, un hotel, tres piscinas. Ya no se pueden construír pistas de deporte.");
                }
            } else if (((Solar) c).getNumeropiscinas() == 1) {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar tres casas, un hotel, dos piscinas y tres pistas de deporte.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar tres casas, un hotel, dos piscinas y dos pistas de deporte.");
                } else if (((Solar) c).getNumeropistas() == 2) {
                    consola.imprimir("Aún se pueden edificar tres casas, un hotel, dos piscinas y una pista de deporte.");
                } else {
                    consola.imprimir("Aún se pueden edificar tres casas, un hotel, dos piscinas. Ya no se pueden construír pistas de deporte.");
                }
            } else if (((Solar) c).getNumeropiscinas() == 2) {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar tres casas, un hotel, una piscina y tres pistas de deporte.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar tres casas, un hotel, una piscina y dos pistas de deporte.");
                } else if (((Solar) c).getNumeropistas() == 2) {
                    consola.imprimir("Aún se pueden edificar tres casas, un hotel, una piscina y una pista de deporte.");
                } else {
                    consola.imprimir("Aún se pueden edificar tres casas, un hotel, una piscina. Ya no se pueden construír pistas de deporte.");
                }
            } else {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar tres casas, un hotel y tres pistas de deporte. Ya no se pueden construír piscinas");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar tres casas, un hotel y dos pistas de deporte. Ya no se pueden construír piscinas.");
                } else if (((Solar) c).getNumeropistas() == 2) {
                    consola.imprimir("Aún se pueden edificar tres casas, un hotel y una pista de deporte. Ya no se pueden construír piscinas.");
                } else {
                    consola.imprimir("Aún se pueden edificar tres casas y un hotel. Ya no se pueden construír piscinas y pistas de deporte.");
                }
            }
        } else if (((Solar) c).getNumerocasas() == 2 && ((Solar) c).getNumerohoteles() == 2 && (tablero.getGrupos().get(i).getNombre().equals("Cian") || tablero.getGrupos().get(i).getNombre().equals("Violeta") || tablero.getGrupos().get(i).getNombre().equals("Amarillo") || tablero.getGrupos().get(i).getNombre().equals("Rojo") || tablero.getGrupos().get(i).getNombre().equals("Gris") || tablero.getGrupos().get(i).getNombre().equals("Verde"))) {
            if (((Solar) c).getNumeropiscinas() == 0) {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar dos casas, un hotel, tres piscinas y tres pistas de deporte.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar dos casas, un hotel, tres piscinas y dos pistas de deporte.");
                } else if (((Solar) c).getNumeropistas() == 2) {
                    consola.imprimir("Aún se pueden edificar dos casas, un hotel, tres piscinas y una pista de deporte.");
                } else {
                    consola.imprimir("Aún se pueden edificar dos casas, un hotel, tres piscinas. Ya no se pueden construír pistas de deporte.");
                }
            } else if (((Solar) c).getNumeropiscinas() == 1) {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar dos casas, un hotel, dos piscinas y tres pistas de deporte.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar dos casas, un hotel, dos piscinas y dos pistas de deporte.");
                } else if (((Solar) c).getNumeropistas() == 2) {
                    consola.imprimir("Aún se pueden edificar dos casas, un hotel, dos piscinas y una pista de deporte.");
                } else {
                    consola.imprimir("Aún se pueden edificar dos casas, un hotel, dos piscinas. Ya no se pueden construír pistas de deporte.");
                }
            } else if (((Solar) c).getNumeropiscinas() == 2) {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar dos casas, un hotel, una piscina y tres pistas de deporte.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar dos casas, un hotel, una piscina y dos pistas de deporte.");
                } else if (((Solar) c).getNumeropistas() == 2) {
                    consola.imprimir("Aún se pueden edificar dos casas, un hotel, una piscina y una pista de deporte.");
                } else {
                    consola.imprimir("Aún se pueden edificar dos casas, un hotel, una piscina. Ya no se pueden construír pistas de deporte.");
                }
            } else {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar dos casas, un hotel y tres pistas de deporte. Ya no se pueden construír piscinas");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar dos casas, un hotel y dos pistas de deporte. Ya no se pueden construír piscinas.");
                } else if (((Solar) c).getNumeropistas() == 2) {
                    consola.imprimir("Aún se pueden edificar dos casas, un hotel y una pista de deporte. Ya no se pueden construír piscinas.");
                } else {
                    consola.imprimir("Aún se pueden edificar dos casas y un hotel. Ya no se pueden construír piscinas y pistas de deporte.");
                }
            }
        } else if (((Solar) c).getNumerocasas() == 3 && ((Solar) c).getNumerohoteles() == 2 && (tablero.getGrupos().get(i).getNombre().equals("Cian") || tablero.getGrupos().get(i).getNombre().equals("Violeta") || tablero.getGrupos().get(i).getNombre().equals("Amarillo") || tablero.getGrupos().get(i).getNombre().equals("Rojo") || tablero.getGrupos().get(i).getNombre().equals("Gris") || tablero.getGrupos().get(i).getNombre().equals("Verde"))) {
            if (((Solar) c).getNumeropiscinas() == 0) {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar una casa, un hotel, tres piscinas y tres pistas de deporte.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar una casa, un hotel, tres piscinas y dos pistas de deporte.");
                } else if (((Solar) c).getNumeropistas() == 2) {
                    consola.imprimir("Aún se pueden edificar una casa, un hotel, tres piscinas y una pista de deporte.");
                } else {
                    consola.imprimir("Aún se pueden edificar una casa, un hotel, tres piscinas. Ya no se pueden construír pistas de deporte.");
                }
            } else if (((Solar) c).getNumeropiscinas() == 1) {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar una casa, un hotel, dos piscinas y tres pistas de deporte.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar una casa, un hotel, dos piscinas y dos pistas de deporte.");
                } else if (((Solar) c).getNumeropistas() == 2) {
                    consola.imprimir("Aún se pueden edificar una casa, un hotel, dos piscinas y una pista de deporte.");
                } else {
                    consola.imprimir("Aún se pueden edificar una casa, un hotel, dos piscinas. Ya no se pueden construír pistas de deporte.");
                }
            } else if (((Solar) c).getNumeropiscinas() == 2) {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar una casa, un hotel, una piscina y tres pistas de deporte.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar una casa, un hotel, una piscina y dos pistas de deporte.");
                } else if (((Solar) c).getNumeropistas() == 2) {
                    consola.imprimir("Aún se pueden edificar una casa, un hotel, una piscina y una pista de deporte.");
                } else {
                    consola.imprimir("Aún se pueden edificar una casa, un hotel, una piscina. Ya no se pueden construír pistas de deporte.");
                }
            } else {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar una casa, un hotel y tres pistas de deporte. Ya no se pueden construír piscinas");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar una casa, un hotel y dos pistas de deporte. Ya no se pueden construír piscinas.");
                } else if (((Solar) c).getNumeropistas() == 2) {
                    consola.imprimir("Aún se pueden edificar una casa, un hotel y una pista de deporte. Ya no se pueden construír piscinas.");
                } else {
                    consola.imprimir("Aún se pueden edificar una casa y un hotel. Ya no se pueden construír piscinas y pistas de deporte.");
                }
            }
        } else if (((Solar) c).getNumerocasas() == 4 && ((Solar) c).getNumerohoteles() == 2 && (tablero.getGrupos().get(i).getNombre().equals("Cian") || tablero.getGrupos().get(i).getNombre().equals("Violeta") || tablero.getGrupos().get(i).getNombre().equals("Amarillo") || tablero.getGrupos().get(i).getNombre().equals("Rojo") || tablero.getGrupos().get(i).getNombre().equals("Gris") || tablero.getGrupos().get(i).getNombre().equals("Verde"))) {
            if (((Solar) c).getNumeropiscinas() == 0) {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar un hotel, tres piscinas y tres pistas de deporte. Ya no se pueden construír casas.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar un hotel, tres piscinas y dos pistas de deporte. Ya no se pueden construír casas.");
                } else if (((Solar) c).getNumeropistas() == 2) {
                    consola.imprimir("Aún se pueden edificar un hotel, tres piscinas y una pista de deporte. Ya no se pueden construír casas.");
                } else {
                    consola.imprimir("Aún se pueden edificar un hotel y tres piscinas. Ya no se pueden construír casas y pistas de deporte.");
                }
            } else if (((Solar) c).getNumeropiscinas() == 1) {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar un hotel, dos piscinas y tres pistas de deporte. Ya no se pueden construír casas.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar un hotel, dos piscinas y dos pistas de deporte. Ya no se pueden construír casas.");
                } else if (((Solar) c).getNumeropistas() == 2) {
                    consola.imprimir("Aún se pueden edificar un hotel, dos piscinas y una pista de deporte. Ya no se pueden construír casas.");
                } else {
                    consola.imprimir("Aún se pueden edificar un hotel y dos piscinas. Ya no se pueden construír casas y pistas de deporte.");
                }
            } else if (((Solar) c).getNumeropiscinas() == 2) {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar un hotel, una piscina y tres pistas de deporte. Ya no se pueden construír casas.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar un hotel, una piscina y dos pistas de deporte. Ya no se pueden construír casas.");
                } else if (((Solar) c).getNumeropistas() == 2) {
                    consola.imprimir("Aún se pueden edificar un hotel, una piscina y una pista de deporte. Ya no se pueden construír casas.");
                } else {
                    consola.imprimir("Aún se pueden edificar un hotel y una piscina. Ya no se pueden construír casas y pistas de deporte.");
                }
            } else {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar un hotel y tres pistas de deporte. Ya no se pueden construír casas y piscinas.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar un hotel y dos pistas de deporte. Ya no se pueden construír casas y piscinas.");
                } else if (((Solar) c).getNumeropistas() == 2) {
                    consola.imprimir("Aún se pueden edificar un hotel y una pista de deporte. Ya no se pueden construír casas y piscinas.");
                } else {
                    consola.imprimir("Aún se pueden edificar un hotel. Ya no se pueden construír casas, piscinas y pistas de deporte.");
                }
            }
        } else if (((Solar) c).getNumerocasas() == 0 && ((Solar) c).getNumerohoteles() == 3 && (tablero.getGrupos().get(i).getNombre().equals("Cian") || tablero.getGrupos().get(i).getNombre().equals("Violeta") || tablero.getGrupos().get(i).getNombre().equals("Amarillo") || tablero.getGrupos().get(i).getNombre().equals("Rojo") || tablero.getGrupos().get(i).getNombre().equals("Gris") || tablero.getGrupos().get(i).getNombre().equals("Verde"))) {
            if (((Solar) c).getNumeropiscinas() == 0) {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar tres casas, tres piscinas y tres pistas de deporte. Ya no se pueden construír hoteles.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar tres casas, tres piscinas y dos pistas de deporte. Ya no se pueden construír hoteles.");
                } else if (((Solar) c).getNumeropistas() == 2) {
                    consola.imprimir("Aún se pueden edificar tres casas, tres piscinas y una pista de deporte. Ya no se pueden construír hoteles.");
                } else {
                    consola.imprimir("Aún se pueden edificar tres casas y tres piscinas. Ya no se pueden construír hoteles y pistas de deporte.");
                }
            } else if (((Solar) c).getNumeropiscinas() == 1) {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar tres casas, dos piscina y tres pistas de deporte. Ya no se pueden construír hoteles.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar tres casas, dos piscinas y dos pistas de deporte. Ya no se pueden construír hoteles.");
                } else if (((Solar) c).getNumeropistas() == 2) {
                    consola.imprimir("Aún se pueden edificar tres casas, dos piscinas y una pista de deporte. Ya no se pueden construír hoteles.");
                } else {
                    consola.imprimir("Aún se pueden edificar tres casas y dos piscinas. Ya no se pueden construír hoteles y pistas de deporte.");
                }
            } else if (((Solar) c).getNumeropiscinas() == 2) {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar tres casas, una piscina y tres pistas de deporte. Ya no se pueden construír hoteles.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar tres casas, una piscina y dos pistas de deporte. Ya no se pueden construír hoteles.");
                } else if (((Solar) c).getNumeropistas() == 2) {
                    consola.imprimir("Aún se pueden edificar tres casas, una piscina y una pista de deporte. Ya no se pueden construír hoteles.");
                } else {
                    consola.imprimir("Aún se pueden edificar tres casas y una piscina. Ya no se pueden construír hoteles y pistas de deporte.");
                }
            } else {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar tres casas y dos pistas de deporte. Ya no se pueden construír hoteles y piscinas.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar tres casas y dos pistas de deporte. Ya no se pueden construír hoteles y piscinas.");
                } else if (((Solar) c).getNumeropistas() == 2) {
                    consola.imprimir("Aún se pueden edificar tres casas y una pista de deporte. Ya no se pueden construír hoteles y piscinas.");
                } else {
                    consola.imprimir("Aún se pueden edificar tres casas. Ya no se pueden construír hoteles, piscinas y pistas de deporte.");
                }
            }
        } else if (((Solar) c).getNumerocasas() == 1 && ((Solar) c).getNumerohoteles() == 3 && (tablero.getGrupos().get(i).getNombre().equals("Cian") || tablero.getGrupos().get(i).getNombre().equals("Violeta") || tablero.getGrupos().get(i).getNombre().equals("Amarillo") || tablero.getGrupos().get(i).getNombre().equals("Rojo") || tablero.getGrupos().get(i).getNombre().equals("Gris") || tablero.getGrupos().get(i).getNombre().equals("Verde"))) {
            if (((Solar) c).getNumeropiscinas() == 0) {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar dos casas, tres piscinas y tres pistas de deporte. Ya no se pueden construír hoteles.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar dos casas, tres piscinas y dos pistas de deporte. Ya no se pueden construír hoteles.");
                } else if (((Solar) c).getNumeropistas() == 2) {
                    consola.imprimir("Aún se pueden edificar dos casas, tres piscinas y una pista de deporte. Ya no se pueden construír hoteles.");
                } else {
                    consola.imprimir("Aún se pueden edificar dos casas y tres piscinas. Ya no se pueden construír hoteles y pistas de deporte.");
                }
            } else if (((Solar) c).getNumeropiscinas() == 1) {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar dos casas, dos piscina y tres pistas de deporte. Ya no se pueden construír hoteles.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar dos casas, dos piscinas y dos pistas de deporte. Ya no se pueden construír hoteles.");
                } else if (((Solar) c).getNumeropistas() == 2) {
                    consola.imprimir("Aún se pueden edificar dos casas, dos piscinas y una pista de deporte. Ya no se pueden construír hoteles.");
                } else {
                    consola.imprimir("Aún se pueden edificar dos casas y dos piscinas. Ya no se pueden construír hoteles y pistas de deporte.");
                }
            } else if (((Solar) c).getNumeropiscinas() == 2) {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar dos casas, una piscina y tres pistas de deporte. Ya no se pueden construír hoteles.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar dos casas, una piscina y dos pistas de deporte. Ya no se pueden construír hoteles.");
                } else if (((Solar) c).getNumeropistas() == 2) {
                    consola.imprimir("Aún se pueden edificar dos casas, una piscina y una pista de deporte. Ya no se pueden construír hoteles.");
                } else {
                    consola.imprimir("Aún se pueden edificar dos casas y una piscina. Ya no se pueden construír hoteles y pistas de deporte.");
                }
            } else {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar dos casas y dos pistas de deporte. Ya no se pueden construír hoteles y piscinas.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar dos casas y dos pistas de deporte. Ya no se pueden construír hoteles y piscinas.");
                } else if (((Solar) c).getNumeropistas() == 2) {
                    consola.imprimir("Aún se pueden edificar dos casas y una pista de deporte. Ya no se pueden construír hoteles y piscinas.");
                } else {
                    consola.imprimir("Aún se pueden edificar dos casas. Ya no se pueden construír hoteles, piscinas y pistas de deporte.");
                }
            }
        } else if (((Solar) c).getNumerocasas() == 2 && ((Solar) c).getNumerohoteles() == 3 && (tablero.getGrupos().get(i).getNombre().equals("Cian") || tablero.getGrupos().get(i).getNombre().equals("Violeta") || tablero.getGrupos().get(i).getNombre().equals("Amarillo") || tablero.getGrupos().get(i).getNombre().equals("Rojo") || tablero.getGrupos().get(i).getNombre().equals("Gris") || tablero.getGrupos().get(i).getNombre().equals("Verde"))) {
            if (((Solar) c).getNumeropiscinas() == 0) {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar una casa, tres piscinas y tres pistas de deporte. Ya no se pueden construír hoteles.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar una casa, tres piscinas y dos pistas de deporte. Ya no se pueden construír hoteles.");
                } else if (((Solar) c).getNumeropistas() == 2) {
                    consola.imprimir("Aún se pueden edificar una casa, tres piscinas y una pista de deporte. Ya no se pueden construír hoteles.");
                } else {
                    consola.imprimir("Aún se pueden edificar una casa y tres piscinas. Ya no se pueden construír hoteles y pistas de deporte.");
                }
            } else if (((Solar) c).getNumeropiscinas() == 1) {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar una casa, dos piscina y tres pistas de deporte. Ya no se pueden construír hoteles.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar una casa, dos piscinas y dos pistas de deporte. Ya no se pueden construír hoteles.");
                } else if (((Solar) c).getNumeropistas() == 2) {
                    consola.imprimir("Aún se pueden edificar una casa, dos piscinas y una pista de deporte. Ya no se pueden construír hoteles.");
                } else {
                    consola.imprimir("Aún se pueden edificar una casa y dos piscinas. Ya no se pueden construír hoteles y pistas de deporte.");
                }
            } else if (((Solar) c).getNumeropiscinas() == 2) {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar una casa, una piscina y tres pistas de deporte. Ya no se pueden construír hoteles.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar una casa, una piscina y dos pistas de deporte. Ya no se pueden construír hoteles.");
                } else if (((Solar) c).getNumeropistas() == 2) {
                    consola.imprimir("Aún se pueden edificar una casa, una piscina y una pista de deporte. Ya no se pueden construír hoteles.");
                } else {
                    consola.imprimir("Aún se pueden edificar una casa y una piscina. Ya no se pueden construír hoteles y pistas de deporte.");
                }
            } else {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar una casa y dos pistas de deporte. Ya no se pueden construír hoteles y piscinas.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar una casa y dos pistas de deporte. Ya no se pueden construír hoteles y piscinas.");
                } else if (((Solar) c).getNumeropistas() == 2) {
                    consola.imprimir("Aún se pueden edificar una casa y una pista de deporte. Ya no se pueden construír hoteles y piscinas.");
                } else {
                    consola.imprimir("Aún se puede edificar una casa. Ya no se pueden construír hoteles, piscinas y pistas de deporte.");
                }
            }
        } else if (((Solar) c).getNumerocasas() == 3 && ((Solar) c).getNumerohoteles() == 3 && (tablero.getGrupos().get(i).getNombre().equals("Cian") || tablero.getGrupos().get(i).getNombre().equals("Violeta") || tablero.getGrupos().get(i).getNombre().equals("Amarillo") || tablero.getGrupos().get(i).getNombre().equals("Rojo") || tablero.getGrupos().get(i).getNombre().equals("Gris") || tablero.getGrupos().get(i).getNombre().equals("Verde"))) {
            if (((Solar) c).getNumeropiscinas() == 0) {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar tres piscinas y tres pistas de deporte. Ya no se pueden construír casas y hoteles.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar tres piscinas y dos pistas de deporte. Ya no se pueden construír casas y hoteles.");
                } else if (((Solar) c).getNumeropistas() == 2) {
                    consola.imprimir("Aún se pueden edificar tres piscinas y una pista de deporte. Ya no se pueden construír casas y hoteles.");
                } else {
                    consola.imprimir("Aún se pueden edificar tres piscinas. Ya no se pueden construír casas, hoteles y pistas de deporte.");
                }
            } else if (((Solar) c).getNumeropiscinas() == 1) {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar dos piscinas y tres pistas de deporte. Ya no se pueden construír casas y hoteles.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar dos piscinas y dos pistas de deporte. Ya no se pueden construír casas y hoteles.");
                } else if (((Solar) c).getNumeropistas() == 2) {
                    consola.imprimir("Aún se pueden edificar dos piscinas y una pista de deporte. Ya no se pueden construír casas y hoteles.");
                } else {
                    consola.imprimir("Aún se pueden edificar dos piscinas. Ya no se pueden construír casas, hoteles y pistas de deporte.");
                }
            } else if (((Solar) c).getNumeropiscinas() == 2) {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar una piscina y tres pistas de deporte. Ya no se pueden construír casas y hoteles.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar una piscina y dos pistas de deporte. Ya no se pueden construír casas y hoteles.");
                } else if (((Solar) c).getNumeropistas() == 2) {
                    consola.imprimir("Aún se pueden edificar una piscina y una pista de deporte. Ya no se pueden construír casas y hoteles.");
                } else {
                    consola.imprimir("Aún se puede edificar una piscina. Ya no se pueden construír casas, hoteles y pistas de deporte.");
                }
            } else {
                if (((Solar) c).getNumeropistas() == 0) {
                    consola.imprimir("Aún se pueden edificar tres pistas de deporte. Ya no se pueden construír casas, hoteles y piscinas.");
                } else if (((Solar) c).getNumeropistas() == 1) {
                    consola.imprimir("Aún se pueden edificar dos pistas de deporte. Ya no se pueden construír casas, hoteles y piscinas.");
                } else if (((Solar) c).getNumeropistas() == 2) {
                    consola.imprimir("Aún se puede edificar una pista de deporte. Ya no se pueden construír casas, hoteles y piscinas.");
                } else {
                    consola.imprimir("Ya no se puede edificar.");
                }
            }
        }
    }

    private void creacionJugadores(String nombre, String figura) throws CreacionExcepcion {
        if (jugadores.size() == 0) {
            crearJugador(nombre, figura);
        } else if (jugadores.size() == 1) {
            if (jugadores.get(0).getNombre().equals(nombre)) {
                throw new CreacionExcepcion("El jugador ya existe.");
            } else crearJugador(nombre, figura);
        } else if (jugadores.size() == 2) {
            if (jugadores.get(0).getNombre().equals(nombre)) {
                throw new CreacionExcepcion("El jugador ya existe.");
            } else if (jugadores.get(1).getNombre().equals(nombre)) {
                throw new CreacionExcepcion("El jugador ya existe.");
            } else crearJugador(nombre, figura);
        } else if (jugadores.size() == 3) {
            if (jugadores.get(0).getNombre().equals(nombre)) {
                throw new CreacionExcepcion("El jugador ya existe.");
            } else if (jugadores.get(1).getNombre().equals(nombre)) {
                throw new CreacionExcepcion("El jugador ya existe.");
            } else if (jugadores.get(2).getNombre().equals(nombre)) {
                throw new CreacionExcepcion("El jugador ya existe.");
            } else crearJugador(nombre, figura);
        } else if (jugadores.size() == 4) {
            if (jugadores.get(0).getNombre().equals(nombre)) {
                throw new CreacionExcepcion("El jugador ya existe.");
            } else if (jugadores.get(1).getNombre().equals(nombre)) {
                throw new CreacionExcepcion("El jugador ya existe.");
            } else if (jugadores.get(2).getNombre().equals(nombre)) {
                throw new CreacionExcepcion("El jugador ya existe.");
            } else if (jugadores.get(3).getNombre().equals(nombre)) {
                throw new CreacionExcepcion("El jugador ya existe.");
            } else crearJugador(nombre, figura);
        } else if (jugadores.size() == 5) {
            if (jugadores.get(0).getNombre().equals(nombre)) {
                throw new CreacionExcepcion("El jugador ya existe.");
            } else if (jugadores.get(1).getNombre().equals(nombre)) {
                throw new CreacionExcepcion("El jugador ya existe.");
            } else if (jugadores.get(2).getNombre().equals(nombre)) {
                throw new CreacionExcepcion("El jugador ya existe.");
            } else if (jugadores.get(3).getNombre().equals(nombre)) {
                throw new CreacionExcepcion("El jugador ya existe.");
            } else if (jugadores.get(4).getNombre().equals(nombre)) {
                throw new CreacionExcepcion("El jugador ya existe.");
            } else crearJugador(nombre, figura);
        }
    }

    private void crearJugador(String nombre, String figura) {
        Jugador jug = new Jugador(nombre, figura);
        jug.getAvatar().generarId();
        for (int i = 0; i < jugadores.size(); i++) {
            while (jugadores.get(i).getAvatar().getId().equals(jug.getAvatar().getId())) {
                jug.getAvatar().generarId();
                i = 0;
            }
        }
        jugadores.add(jug);
        Iniciar();
        consola.imprimir("{\n" + "nombre: " + jug.getNombre() + ",\n" + "avatar: " + jug.getAvatar().getId() + "\n}");
        consola.imprimir("\n" + tablero.toString());
        jugadorturno = jugadores.get(turno);
    }

    private void Iniciar() {
        for (Jugador j : jugadores) {
            if (!this.avatar.contains((j.getAvatar()))) {
                this.avatar.add(j.getAvatar());
                j.getAvatar().setCasillaAvatar(tablero.getCasillas().get(0));
                tablero.getCasillas().get(0).anadirAvatar(j.getAvatar());
            }
        }
    }

    private void acabarTurno() {
        if (!jugadorturno.getAvatar().isJugadorcarcel() && jugadorturno.getTurnocarcel() == 3) {
            jugadorturno.setTurnocarcel(0);
        } else if (jugadorturno.getAvatar().isJugadorcarcel() && jugadorturno.getTurnocarcel() == 0) {
            jugadorturno.setTurnocarcel(1);
        } else if (jugadorturno.getAvatar().isJugadorcarcel() && jugadorturno.getTurnocarcel() == 1) {
            jugadorturno.setTurnocarcel(2);
        } else if (jugadorturno.getAvatar().isJugadorcarcel() && jugadorturno.getTurnocarcel() == 2) {
            jugadorturno.setTurnocarcel(3);
        }
        turno++;
        if (turno >= jugadores.size()) {
            turno = 0;
        }
        jugadorturno = jugadores.get(turno);
        tablero.getDado().setPuedolanzar(true);
    }
}