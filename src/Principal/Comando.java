package Principal;

import Excepciones.Excepcion;

public interface Comando {
    void crear(String[] partes) throws Excepcion;

    void jugador(String[] partes) throws Excepcion;

    void listar(String[] partes) throws Excepcion;

    void lanzar(String[] partes) throws Excepcion;

    void comprar(String[] partes) throws Excepcion;

    void acabar(String[] partes) throws Excepcion;

    void describir(String[] partes) throws Excepcion;

    void ver(String[] partes) throws Excepcion;

    void edificar(String[] partes) throws Excepcion;

    void hipotecar(String[] partes) throws Excepcion;

    void deshipotecar(String[] partes) throws Excepcion;

    void vender(String[] partes) throws Excepcion;

    void aceptar(String[] partes) throws Excepcion;

    void trato(String[] partes) throws Excepcion;

    void tratos(String[] partes) throws Excepcion;

    void eliminar(String[] partes) throws Excepcion;

    void estadisticas(String[] partes) throws Excepcion;

    void cambiar(String[] partes) throws Excepcion;

    void salir(String[] partes) throws Excepcion;

    void stop(String[] partes) throws Excepcion;
}
