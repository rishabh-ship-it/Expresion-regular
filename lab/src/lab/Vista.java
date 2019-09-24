/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rafael Lastra
 */
public class Vista extends javax.swing.JFrame {

    /**
     * Creates new form Vistas
     */
    private static Set<Integer>[] SiguientePos;
    private static Set<Character> input = new HashSet<Character>();
    private static ArrayList<String> f = new ArrayList<String>();
    DefaultTableModel pk = new DefaultTableModel();
    Nodo raiz;
    ArrayList<Hoja> nodos = new ArrayList<Hoja>();
    String t2 = "", l = "";

    public Vista() {
        initComponents();
        // TODO code application logic here        
        String hp[] = {"Nodo", "PrimeraPos", "UltimaPos", "SiguientePos"};
        pk.setColumnIdentifiers(hp);
        tabla.setModel(pk);
        this.setLocationRelativeTo(null);
    }

    private void ayudantePreorden(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        String q = "";
        if (nodo instanceof Hoja) {
            if (!(nodo.getdato().equals("&"))) {
                q = q + SiguientePos[((Hoja) nodo).getNum() - 1];
                //System.out.println("nodo --> " + ((Hoja) nodo).getdato() + " posicion --> " + ((Hoja) nodo).getNum());
                //((Hoja)nodo).setsiguientepos();
                nodos.add((Hoja) nodo);
                //System.out.println(((Hoja) nodo).getsiguientepos());
            }
        }
        pk.addRow(new Object[]{nodo.getdato(), nodo.getPrimeraPos(), nodo.getUltimaPos(), q});

        ayudantePreorden(nodo.getizquierdo());
        ayudantePreorden(nodo.getderecho());
    }

    public boolean verificar(ArrayList<Estado> pa) {
        boolean pe = false;
        for (Estado ve : pa) {
            if (ve.isMarcado() == false) {
                pe = true;
            }
        }
        return pe;
    }

    public Hoja buscarNodo(Integer j) {
        for (Hoja pi : nodos) {
            if (pi.getNum() == j) {
                return pi;
            }

        }
        return null;
    }

    public ArrayList Unir(ArrayList<Integer> pa, Set<Integer> pe) {
        Iterator iter = pe.iterator();
        while (iter.hasNext()) {
            Integer w = (Integer) iter.next();
            if (!pa.contains(w)) {
                pa.add(w);
            }
        }
        return pa;
    }

    public boolean verificar2(ArrayList<Integer> u, ArrayList<Estado> Dstates) {
        ArrayList<Integer> posiciones;
        for (Estado pa : Dstates) {
            posiciones = new ArrayList<Integer>();
            Iterator iter = pa.getPosicion().iterator();

            while (iter.hasNext()) {
                Integer w = (Integer) iter.next();
                posiciones.add(w);
            }
            if (posiciones.equals(u)) {
                return false;
            }
        }
        return true;
    }

    private void DFA(String Alfabeto) {
        String letra[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
        DefaultTableModel kiki = new DefaultTableModel();
        kiki.addColumn("Estados/Transiciones");
        ArrayList<Estado> estados = new ArrayList<Estado>();
        ArrayList<Integer> u;
        String[] vector5 = Alfabeto.split(",");
        String[] vector6 = new String[vector5.length];
        for (int i = 0; i < vector6.length; i++) {
                vector6[i] = vector5[i];
            kiki.addColumn(vector6[i]);
            //System.out.println(vector6[i]);
        }
        System.out.println(vector6.length);
        String[][] trand = new String[100][vector6.length];
        ArrayList<Integer> raizppos = new ArrayList<Integer>();
        Iterator iter2 = raiz.PrimeraPos.iterator();
        while (iter2.hasNext()) {
            Integer w = (Integer) iter2.next();
            raizppos.add(w);
        }
        int cont = 0;
        Estado A = new Estado(raizppos, letra[cont]);
        estados.add(A);
        int i = 0;
        while (verificar(estados)) {
            estados.get(i).setMarcado(true);
            for (int j = 0; j < vector6.length; j++) {
                u = new ArrayList<Integer>();
                Iterator iter = estados.get(i).getPosicion().iterator();
                while (iter.hasNext()) {
                    Integer w = (Integer) iter.next();
                    if (vector6[j].equals(buscarNodo(w).getDato())) {
                        u = Unir(u, SiguientePos[buscarNodo(w).getNum() - 1]);
                    }
                }
                if (!u.isEmpty() && verificar2(u, estados)) {
                    cont++;
                    A = new Estado(u, letra[cont]);
                    estados.add(A);
                    trand[i][j] = A.getLetra();
                } else if (!u.isEmpty()) {
                    for (Estado v : estados) {
                        if (v.getPosicion().equals(u)) {
                            trand[i][j] = v.getLetra();
                        }
                    }
                }
            }
            i++;
        }        
        kiki.setRowCount(estados.size());
        tabla2.setModel(kiki);
        int ñ=0;
        for (Estado v : estados) {
            System.out.println("Estado --> " + v.getLetra() + " Posicion --> " + v.getPosicion());
            kiki.setValueAt(v.getLetra(), ñ, 0);
            ñ++;
        }
        for (int j = 0; j < estados.size(); j++) {
            for (int k = 0; k < vector6.length; k++) {
                System.out.println(trand[j][k]);
                kiki.setValueAt(trand[j][k], j, k+1);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        texto = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        label = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla2 = new javax.swing.JTable();
        label2 = new javax.swing.JLabel();
        texto2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tabla);

        jButton1.setText("Generar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Digite E.R -->");

        label.setText("Alfabeto");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 314, Short.MAX_VALUE)
        );

        tabla2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla2);

        label2.setText(".");

        texto2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                texto2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Epresion regular a graficar");

        jLabel3.setText("Digite cadena a verificar -->");

        jButton2.setText("Verificar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(texto, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(texto2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))))
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(123, Short.MAX_VALUE))
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(texto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(texto2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label2)
                                .addGap(41, 41, 41)))
                        .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        String expresion = texto.getText();
        String alfabeto = "";
        int contador = 0;
        String[] vector = expresion.split("");

        int[] careverga = new int[vector.length];
        for (int i = 0; i < vector.length; i++) {
            if (vector[i].equals("(")) {
                contador++;
                careverga[i] = contador;
            } else if (vector[i].equals(")")) {

                careverga[i] = contador;
                contador--;
            }
            if (vector[i].compareTo("(") != 0 && vector[i].compareTo(")") != 0 && vector[i].compareTo("+") != 0 && vector[i].compareTo("*") != 0 && vector[i].compareTo("?") != 0 && vector[i].compareTo("|") != 0) {
                if (alfabeto.contains(vector[i]) == false) {
                    if (alfabeto.compareTo("")==0) {
                    alfabeto = alfabeto + vector[i];
                    }else{
                    alfabeto = alfabeto + "," + vector[i];
                    }
                    
                }
            }
        }
        
        texto2.setText(alfabeto);
        for (int i = 0; i < vector.length; i++) {
            if (vector[i].equals("?")) {
                if (vector[i - 1].equals("*")) {
                    if (vector[i - 2].equals(")")) {
                        for (int j = 0; j < i - 2; j++) {
                            if (careverga[j] == careverga[i - 2]) {
                                vector[j] = "(" + vector[j];
                                vector[i] = "|&)";
                                break;
                            }
                        }
                    } else {
                        vector[i] = "";
                    }
                } else if (vector[i - 1].equals("+")) {
                    vector[i - 1] = "*";
                    vector[i] = "";
                } else if (vector[i - 1].equals(")")) {
                    for (int j = 0; j < i - 1; j++) {
                        if (careverga[j] == careverga[i - 1]) {
                            vector[j] = "(" + vector[j];
                            vector[i] = "|&)";
                            break;
                        }
                    }
                } else {
                    vector[i] = "|&)";
                    vector[i - 1] = "(" + vector[i - 1];
                }
            }
        }

        for (String i : vector) {
            l = l + i;
        }
        //reemplazando ? por epsilon
        //System.out.println(l);
        Arbol arbol = new Arbol();
        raiz = arbol.generateTree(l + "#");

        l = l + "#";
        for (int i = 65; i <= 90; i++) {
            f.add(String.valueOf((char) i));
            f.add(String.valueOf((char) (i + 32)));
        }
        for (int i = 0; i < 10; i++) {
            f.add(i + "");
        }

        String[] p = l.split("");
        int[] w = new int[p.length];
        for (int i = 0; i < w.length; i++) {
            w[i] = 0;
        }
        int k = 0;
        for (int i = 0; i < p.length; i++) {
            if (f.contains(p[i])) {
                k++;
                w[i] = k;
            }
        }

        for (int i : w) {
            if (i == 0) {
                t2 = t2 + " ";
            } else {
                t2 = t2 + (i + "");
            }
        }

        //System.out.println(l);
        //System.out.println(t2);
        SiguientePos = arbol.getSiguientePos();
        ayudantePreorden(raiz);
        Graficar jl = new Graficar();
        jl.setObjArbol(raiz, l, t2);
        jl.paint(panel.getGraphics());
        panel.add(jl);
        label2.setText(l);
        //label3.setText(t2);
        DFA(alfabeto);
        //JFrame ventana = new JFrame();
        //ventana.getContentPane().add(jl);
        //ventana.setDefaultCloseOperation(3);
        //ventana.setSize(1500, 600);
        //ventana.setVisible(true);
        // ventana.setResizable(true);


    }//GEN-LAST:event_jButton1ActionPerformed

    private void texto2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_texto2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_texto2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vista().setVisible(true);
            }
        });

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel label;
    private javax.swing.JLabel label2;
    private javax.swing.JPanel panel;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tabla2;
    private javax.swing.JTextField texto;
    private javax.swing.JTextField texto2;
    // End of variables declaration//GEN-END:variables
}
