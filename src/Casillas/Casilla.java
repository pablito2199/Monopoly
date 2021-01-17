package Casillas;

import Jugadores.Avatar;
import Principal.Valores;

import java.util.ArrayList;

public abstract class Casilla {
    private int posicion;
    private String nombre;
    private String color;
    private ArrayList<Avatar> avatar;
    private Grupo grupo;
    private double coste;
    private int visitacasillas;
    private ArrayList<Integer> vecesencasilla;

    public Casilla() {
        this.nombre = null;
        this.grupo = null;
        this.posicion = 0;
        this.coste = 0;
        this.color = null;
        this.avatar = new ArrayList<>();
        this.visitacasillas = 0;
        this.vecesencasilla = new ArrayList<>();
    }

    public Casilla(String nombre, double coste, String color, int posicion) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.color = color;
        this.avatar = new ArrayList<>();
        this.coste = coste;
        this.visitacasillas = 0;
        this.vecesencasilla = new ArrayList<>();
        vecesencasilla.add(0);
        vecesencasilla.add(0);
        vecesencasilla.add(0);
        vecesencasilla.add(0);
        vecesencasilla.add(0);
        vecesencasilla.add(0);
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ArrayList<Avatar> getAvatar() {
        return avatar;
    }

    public void setAvatar(ArrayList<Avatar> avatar) {
        this.avatar = avatar;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public double getCoste() {
        return coste;
    }

    public void setCoste(double coste) {
        this.coste = coste;
    }

    public int getVisitacasillas() {
        return visitacasillas;
    }

    public void setVisitacasillas(int visitacasillas) {
        this.visitacasillas = visitacasillas;
    }

    public ArrayList<Integer> getVecesencasilla() {
        return vecesencasilla;
    }

    public void setVecesencasilla(ArrayList<Integer> vecesencasilla) {
        this.vecesencasilla = vecesencasilla;
    }

    public final void anadirAvatar(Avatar ava) {
        this.avatar.add(ava);
    }

    public final void eliminarAvatar(Avatar ava) {
        this.avatar.remove(ava);
    }

    public final boolean estaAvatar(Avatar avatartirada) {
        boolean avatarencasilla = false;
        for (Avatar a : this.avatar) {
            if (avatartirada.getId().equals(a.getId())) {
                avatarencasilla = true;
                break;
            }
        }
        return avatarencasilla;
    }

    public final void frecuenciaVisita(int turnojugador) {
        vecesencasilla.set(turnojugador, vecesencasilla.get(turnojugador) + 1);
    }

    @Override
    public String toString() {
        String impresion = "";
        if (getColor().equals("Negro")) {
            impresion += Valores.NEGRO;
        }
        if (getColor().equals("Cian")) {
            impresion += Valores.CIAN;
        }
        if (getColor().equals("Rojo")) {
            impresion += Valores.ROJO;
        }
        if (getColor().equals("Amarillo")) {
            impresion += Valores.AMARILLO;
        }
        if (getColor().equals("Gris")) {
            impresion += Valores.GRIS;
        }
        if (getColor().equals("Verde")) {
            impresion += Valores.VERDE;
        }
        if (getColor().equals("Azul")) {
            impresion += Valores.AZUL;
        }
        if (getColor().equals("Violeta")) {
            impresion += Valores.VIOLETA;
        }
        if (getColor().equals("RojoN")) {
            impresion += Valores.ROJO_NEGRITA;
        }
        if (getColor().equals("AzulN")) {
            impresion += Valores.AZUL_NEGRITA;
        }
        if (getColor().equals("CianN")) {
            impresion += Valores.CIAN_NEGRITA;
        }
        if (getColor().equals("VerdeN")) {
            impresion += Valores.VERDE_NEGRITA;
        }
        if (getColor().equals("NegroS")) {
            impresion += Valores.NEGRO_NEGRITA;
        }
        if (getColor().equals("AmarilloN")) {
            impresion += Valores.AMARILLO_NEGRITA;
        }
        if (getColor().equals("GrisN")) {
            impresion += Valores.GRIS_NEGRITA;
        }
        if (getColor().equals("VioletaN")) {
            impresion += Valores.VIOLETA_NEGRITA;
        }
        String avatares = "";
        if (avatar != null && !avatar.isEmpty()) {
            avatares += "&";
            for (Avatar a : avatar) {
                avatares += a.getId();
            }
        }
        impresion += String.format("%-15s", this.nombre + " " + avatares) + Valores.RESET + "|";
        return impresion;
    }

    @Override
    public boolean equals(Object objeto) {
        if (objeto instanceof Casilla) {
            if (((Casilla) objeto).getNombre().equals(this.nombre)) {
                return true;
            }
        }
        return false;
    }
}