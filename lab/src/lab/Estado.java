/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab;

/**
 *
 * @author Rafael Lastra
 */
import java.util.ArrayList;
import java.util.Set;

public class Estado {

    private  ArrayList<Integer> Posicion;
    private boolean marcado;
    private  String letra;

    public ArrayList<Integer> getPosicion() {
        return Posicion;
    }

    public void setPosicion(ArrayList<Integer> Posicion) {
        this.Posicion = Posicion;
    }

    public boolean isMarcado() {
        return marcado;
    }

    public void setMarcado(boolean marcado) {
        this.marcado = marcado;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public Estado(ArrayList pos, String l) {
        this.Posicion = pos;
        this.marcado=false;
        this.letra=l;
    }

  
    

}
