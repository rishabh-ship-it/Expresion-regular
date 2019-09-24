/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab;

import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JPanel;


/**
 *
 * @author Rafael Lastra
 */
public class Graficar extends Canvas {
    private Nodo objArbol;
    public static final int DIAMETRO = 28;
    public static final int RADIO = DIAMETRO / 2  ;
    public static final int ANCHO = 30;
    private String s;
    private String s2;

    public void setObjArbol(Nodo objArbol,String s,String s2) {
        this.objArbol = objArbol;
        this.s=s;
        this.s2=s2;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); 
        pintar(g, (getWidth() / 2+ 425)+465, 20, objArbol);
       //g.drawString(s.substring(0, s.length()-1),(int) (getWidth() / 2) ,20 );
        //g.drawString(s2,(int) (getWidth() / 2) ,40 );
    }
    
  private void pintar(Graphics g, int x, int y, Nodo n) {
        if (n == null){
            
        }
        else {
            int EXTRA = n.nodosCompletos(n) * (ANCHO / 2);
            g.drawOval(x, y, DIAMETRO, DIAMETRO);
            g.drawString(n.getdato(), x + 12, y + 18);
            g.drawString("PP"+ n.getPrimeraPos(), x , y );
            g.drawString("UP"+ n.getUltimaPos(), x , y+45 );
            if (n.getizquierdo()!= null){
                g.drawLine(x+RADIO-10, y+RADIO+10, x-ANCHO-EXTRA+RADIO+15, y+ANCHO+RADIO);
            }
            if (n.getderecho()!=null) {
                 g.drawLine(x+RADIO+10, y+RADIO+5, x+ANCHO+EXTRA+RADIO-15, y+ANCHO+RADIO-5);
            }
            pintar(g,x-ANCHO-EXTRA,y+ANCHO,n.getizquierdo());
            pintar(g,x+ANCHO+EXTRA,y+ANCHO,n.getderecho());
        }
    }
}
