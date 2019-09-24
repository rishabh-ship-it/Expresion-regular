/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Rafael Lastra
 */
public class Nodo {

    public String dato;
    private Nodo izq;
    private Nodo der;
    private Nodo padre;
    public Set<Integer> PrimeraPos;
    public Set<Integer> UltimaPos;
    public boolean esanulable;
      
    
    public Nodo(String dato) {
        this.dato = dato;
        padre = null;
        izq = null;
        der = null;
        
        PrimeraPos = new HashSet<>();
        UltimaPos = new HashSet<>();
        if(dato.equals("&")){
            esanulable= true;
        }else{
            esanulable = false;
        }
        
    }


    public Nodo getizquierdo() {
        return izq;
    }

    public Nodo getderecho() {
        return der;
    }

    public Nodo getpadre() {
        return padre;
    }

    public String getdato() {
        return dato;
    }
    public void setdato(String nodo) {
        this.dato=nodo;
    }
    public void setpadre(Nodo nodo) {
        this.padre=nodo;
    }

    public void setderecho(Nodo nodo) {
        this.der = nodo;
    }
    
    public void setizquierdo(Nodo nodo) {
        this.izq = nodo;
    }
    public int nodosCompletos(Nodo n) {
        if (n == null)
            return 0;
        else {
            if (n.getizquierdo() != null && n.getderecho() != null)
                return nodosCompletos(n.getizquierdo()) + nodosCompletos(n.getderecho()) + 1;
            return nodosCompletos(n.getizquierdo()) + nodosCompletos(n.getderecho());
        }
    }
    public void añadirPrimeraPos(int num){
        PrimeraPos.add(num);
    }
    
    public void añadirtodoPrimeraPos(Set set){
        PrimeraPos.addAll(set);
    }
    public void añadirUltimaPos(int num){
        UltimaPos.add(num);
    }
    
    public void añadirtodoUltimaPos(Set set){
        UltimaPos.addAll(set);
    }

    public Set<Integer> getPrimeraPos() {
        return PrimeraPos;
    }

    public Set<Integer> getUltimaPos() {
        return UltimaPos;
    }

    public boolean isEsanulable() {
        return esanulable;
    }

    public void setEsanulable(boolean esanulable) {
        this.esanulable = esanulable;
    }
    
}
