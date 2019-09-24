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
public class Hoja extends Nodo {

    private int num;
    private Set<Integer> siguientepos;

    public Hoja(String dato, int num) {
        super(dato);
  
        this.num = num;
        siguientepos = new HashSet<>();
    }
    public Hoja(String dato){
        super(dato);
        siguientepos = new HashSet<>();
    }
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public void siguientepos(int number){
        siguientepos.add(number);
    }
    public Set<Integer> getsiguientepos() {
        return siguientepos;
    }
    public void setsiguientepos(Set<Integer> followPos) {
        this.siguientepos = followPos;
    }
    public String getDato() {
        return dato;
    }
}