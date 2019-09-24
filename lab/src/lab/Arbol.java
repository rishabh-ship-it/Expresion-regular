/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author jbo
 */
public class Arbol {
        
    private int leafNodeID = 0;

    // Stacks for symbol nodes and operators
    private Stack<Nodo> stackNode = new Stack<>();
    private Stack<Character> operator = new Stack<Character>();

    // Set of inputs
    private Set<Character> input = new HashSet<Character>();
    private ArrayList<Character> op = new ArrayList<>();
    
    public Set<Integer> SiguientePos[];
    // Generates tree using the regular expression and returns it's root

    public Nodo generateTree(String regular) {

        Character[] ops = {'*', '|', '.','+'};
        op.addAll(Arrays.asList(ops));

        // Only inputs available
        Character ch[] = new Character[26 + 26];
        for (int i = 65; i <= 90; i++) {
            ch[i - 65] = (char) i;
            ch[i - 65 + 26] = (char) (i + 32);
        }
        Character integer[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9','&'};
        Character others[] = {'#', '\\', '=', '_', '.', '*', '/', '+', '-', ' ', '(', ')'};
        input.addAll(Arrays.asList(ch));
        input.addAll(Arrays.asList(integer));
        input.addAll(Arrays.asList(others));

        // Generate regular expression with the concatenation
        regular = AddConcat(regular);

        // Cleaning stacks
        stackNode.clear();
        operator.clear();

        // Flag which is true when there is something like: \( or \* or etc
        boolean isSymbol = false;

        for (int i = 0; i < regular.length(); i++) {
            if (regular.charAt(i) == '\\') {
                isSymbol = true;
                continue;
            }
            if (isSymbol || isInputCharacter(regular.charAt(i))) {
                if (isSymbol) {
                    //create a node with "\{symbol}" symbol 
                    pushStack("\\" + Character.toString(regular.charAt(i)));
                } else {
                    pushStack(Character.toString(regular.charAt(i)));
                }
                isSymbol = false;
            } else if (operator.isEmpty()) {
                operator.push(regular.charAt(i));

            } else if (regular.charAt(i) == '(') {
                operator.push(regular.charAt(i));

            } else if (regular.charAt(i) == ')') {
                while (operator.get(operator.size() - 1) != '(') {
                    doOperation();
                }

                // Pop the '(' left parenthesis
                operator.pop();

            } else {
                while (!operator.isEmpty()
                        && Priority(regular.charAt(i), operator.get(operator.size() - 1))) {
                    doOperation();
                }
                operator.push(regular.charAt(i));
            }
        }

        // Clean the remaining elements in the stack
        while (!operator.isEmpty()) {
            doOperation();
        }

        // Get the complete Tree
        Nodo completeTree = stackNode.pop();
        //System.out.println("Recorrido Preorden --> ");

//        System.out.println(" raiz "+ completeTree.getdato());
        //System.out.println(leafNodeID);
        SiguientePos = new Set[leafNodeID];
        for (int i = 0; i < leafNodeID; i++) {
            SiguientePos[i] = new HashSet<>();
        }
        generarAnulable(completeTree); 
        generarPrimeraUltimaPos(completeTree);
        generarsiguientePos(completeTree);
        //ayudantePreorden(completeTree);
        return completeTree;
    }

    private void ayudantePreorden(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        String f ="Nodo "+nodo.getdato() + "  Primera posicion: " + nodo.getPrimeraPos()+ "  Segunda posicion: " + nodo.getUltimaPos();
        if (nodo instanceof Hoja) {
            if (!(nodo.getdato().equals("&"))) {
                f=f+" Siguiente posicion: " + SiguientePos[((Hoja) nodo).getNum()-1]+" Posicion: " + ((Hoja) nodo).getNum(); //((Hoja) nodo).getsiguientepos();
            }
        }
        System.out.println(f);
        //System.out.print(nodo.getdato() + " " + nodo.getPrimeraPos()+ " " + nodo.getUltimaPos() +" "+ nodo.isEsanulable());     //mostrar datos del nodo
        ayudantePreorden(nodo.getizquierdo());   //recorre subarbol izquierdo
        ayudantePreorden(nodo.getderecho());     //recorre subarbol derecho
    }

    private boolean Priority(char first, Character second) {
        if (first == second) {
            return true;
        }
        if (first == '*') {
            return false;
        }
        if (second == '*') {
            return true;
        }
        if (first == '+') {
            return false;
        }
        if (second == '+') {
            return true;
        }
        if (first == '.') {
            return false;
        }
        if (second == '.') {
            return true;
        }
        if (first == '|') {
            return false;
        }
        return true;
    }

    // Do the desired operation based on the top of stackNode
    private void doOperation() {
        if (this.operator.size() > 0) {
            char charAt = operator.pop();

            switch (charAt) {
                case ('|'):
                    union();
                    break;

                case ('.'):
                    concatenation();
                    break;

                case ('*'):
                    star();
                    break;
                case ('+'):
                    mas();
                    break;

                default:
                    System.out.println(">>" + charAt);
                    System.out.println("Unkown Symbol !");
                    System.exit(1);
                    break;
            }
        }
    }

    // Do the star operation
    private void star() {
        // Retrieve top Node from Stack
        Nodo node = stackNode.pop();

        Nodo root = new Nodo("*");
        root.setizquierdo(node);
        root.setderecho(null);
        node.setpadre(root);

        // Put node back in the stackNode
        stackNode.push(root);
    }
    
     private void mas() {
        // Retrieve top Node from Stack
        Nodo node = stackNode.pop();

        Nodo root = new Nodo("+");
        root.setizquierdo(node);
        root.setderecho(null);
        node.setpadre(root);

        // Put node back in the stackNode
        stackNode.push(root);
    }

    // Do the concatenation operation
    private void concatenation() {
        // retrieve node 1 and 2 from stackNode
        Nodo node2 = stackNode.pop();
        Nodo node1 = stackNode.pop();

        Nodo root = new Nodo(".");
        root.setizquierdo(node1);
        root.setderecho(node2);
        node1.setpadre(root);
        node2.setpadre(root);

        // Put node back to stackNode
        stackNode.push(root);
    }

    // Makes union of sub Node 1 with sub Node 2
    private void union() {
        // Load two Node in stack into variables
        Nodo node2 = stackNode.pop();
        Nodo node1 = stackNode.pop();

        Nodo root = new Nodo("|");
        root.setizquierdo(node1);
        root.setderecho(node2);
        node1.setpadre(root);
        node2.setpadre(root);

        // Put Node back to stack
        stackNode.push(root);
    }

    // Push input symbol into stackNode
    private void pushStack(String symbol) {
        if(!(symbol.equals("&"))){
            Nodo node = new Hoja(symbol, ++leafNodeID);
            node.setizquierdo(null);
            node.setderecho(null);

            // Put Node back to stackNode
            stackNode.push(node);
        }else{
            Nodo node = new Hoja(symbol);
            node.setizquierdo(null);
            node.setderecho(null);

            // Put Node back to stackNode
            stackNode.push(node);
        }
        
//        if (node.getdato().equals("&")) {
//            node.setEsanulable(true);
//        }

    }

    // add "." when is concatenation between to symbols that: "." -> "."
    // concatenates to each other
    private String AddConcat(String regular) {
        String newRegular = new String("");

        for (int i = 0; i < regular.length() - 1; i++) {
            if (regular.charAt(i) == '\\' && isInputCharacter(regular.charAt(i + 1))) {
                newRegular += regular.charAt(i);
            } else if (regular.charAt(i) == '\\' && regular.charAt(i + 1) == '(') {
                newRegular += regular.charAt(i);
            } else if ((isInputCharacter(regular.charAt(i)) || (regular.charAt(i) == '(' && i > 0 && regular.charAt(i - 1) == '\\')) && isInputCharacter(regular.charAt(i + 1))) {
                newRegular += regular.charAt(i) + ".";

            } else if ((isInputCharacter(regular.charAt(i)) || (regular.charAt(i) == '(' && i > 0 && regular.charAt(i - 1) == '\\')) && regular.charAt(i + 1) == '(') {
                newRegular += regular.charAt(i) + ".";

            } else if (regular.charAt(i) == ')' && isInputCharacter(regular.charAt(i + 1))) {
                newRegular += regular.charAt(i) + ".";

            } else if (regular.charAt(i) == '*' && isInputCharacter(regular.charAt(i + 1))) {
                newRegular += regular.charAt(i) + ".";

            } else if (regular.charAt(i) == '*' && regular.charAt(i + 1) == '(') {
                newRegular += regular.charAt(i) + ".";

            }  else if (regular.charAt(i) == '+' && isInputCharacter(regular.charAt(i + 1))) {
                newRegular += regular.charAt(i) + ".";

            } else if (regular.charAt(i) == '+' && regular.charAt(i + 1) == '(') {
                newRegular += regular.charAt(i) + ".";

            } else if (regular.charAt(i) == ')' && regular.charAt(i + 1) == '(') {
                newRegular += regular.charAt(i) + ".";

            } else {
                newRegular += regular.charAt(i);
            }

        }
        newRegular += regular.charAt(regular.length() - 1);
        return newRegular;
    }

    // Return true if is part of the automata Language else is false
    private boolean isInputCharacter(char charAt) {

        if (op.contains(charAt)) {
            return false;
        }
        for (Character c : input) {
            if ((char) c == charAt && charAt != '(' && charAt != ')') {
                return true;
            }
        }
        return false;
    }


    public int getNumberOfLeafs() {
        return leafNodeID;
    }
    
    
    public void generarAnulable(Nodo nodo){
        if (nodo == null) {
            return;
        }
        if (!(nodo instanceof Hoja)) {
            Nodo izq = nodo.getizquierdo();
            Nodo der = nodo.getderecho();
            generarAnulable(izq);
            generarAnulable(der);
            switch (nodo.getdato()){
                case "|":
                    nodo.setEsanulable(izq.isEsanulable() | der.isEsanulable());
                    break;
                case ".":
                    nodo.setEsanulable(izq.isEsanulable() & der.isEsanulable());
                    break;
                case "*":
                    nodo.setEsanulable(true);
                    break;
                case "+":
                    nodo.setEsanulable(false);
                    break;
            }
        }
    }
    
    public void generarPrimeraUltimaPos(Nodo nodo){
        if (nodo == null) {
            return;
        }
        if (nodo instanceof Hoja) {
            Hoja node = (Hoja) nodo;
            
//            System.out.println("");
//            System.out.println(nodo.getdato()+""+h);
            if(!(nodo.getdato().equals("&"))){
                int h = node.getNum();
                nodo.añadirPrimeraPos(h);
                nodo.añadirUltimaPos(h);
            }
//            Set<Integer> h = nodo.getUltimaPos();
//            for (Integer integer : h) {
//                System.out.println(integer);
//            }
        }else{
            Nodo izq = nodo.getizquierdo();
            Nodo der = nodo.getderecho();
            generarPrimeraUltimaPos(izq);
            generarPrimeraUltimaPos(der);
            switch (nodo.getdato()){
                case "|":
                    nodo.añadirtodoPrimeraPos(izq.getPrimeraPos());
                    nodo.añadirtodoPrimeraPos(der.getPrimeraPos());
                    nodo.añadirtodoUltimaPos(izq.getUltimaPos());
                    nodo.añadirtodoUltimaPos(der.getUltimaPos());
                    break;
                case ".":
                    if (izq.isEsanulable()){
                        nodo.añadirtodoPrimeraPos(izq.getPrimeraPos());
                        nodo.añadirtodoPrimeraPos(der.getPrimeraPos());
                    } else {
                        nodo.añadirtodoPrimeraPos(izq.getPrimeraPos());
                    }
                    //
//                    System.out.println("");
//                    System.out.println(der.getdato()+"  " +der.isEsanulable());
                    if (der.isEsanulable()) {
//                        System.out.println("si");
                        nodo.añadirtodoUltimaPos(izq.getUltimaPos());
//                        for (Integer ultimaPo : izq.getUltimaPos()) {
//                            System.out.println(ultimaPo);
//                        }
                        nodo.añadirtodoUltimaPos(der.getUltimaPos());
//                        for (Integer ultimaPo : izq.getUltimaPos()) {
//                            System.out.println(ultimaPo);
//                        }
                    } else {
                        nodo.añadirtodoUltimaPos(der.getUltimaPos());
                    }
                    break;
                case "*":
                    nodo.añadirtodoPrimeraPos(izq.getPrimeraPos());
                    nodo.añadirtodoUltimaPos(izq.getUltimaPos());
                    break;
                case "+":
                    nodo.añadirtodoPrimeraPos(izq.getPrimeraPos());
                    nodo.añadirtodoUltimaPos(izq.getUltimaPos());
                    break;
            }
        }
        
    }
    
    public void generarsiguientePos(Nodo nodo){
        if (nodo == null) {
            return;
        }
        Nodo izq = nodo.getizquierdo();
        Nodo der = nodo.getderecho();
        switch (nodo.getdato()){
            case ".":
                Object ultimaposc1[] = izq.getUltimaPos().toArray();
                Set<Integer> firstpos_c2 = der.getPrimeraPos();
                for (int i = 0; i < ultimaposc1.length; i++) {
                    SiguientePos[(Integer) ultimaposc1[i]-1].addAll(firstpos_c2);
                }
             
                break;
            case "*":
                Object ultimapos[] =nodo.getUltimaPos().toArray();
                Set<Integer> PrimeraPos = nodo.getPrimeraPos();
                for (int i = 0; i < ultimapos.length; i++) {
                    SiguientePos[(Integer) ultimapos[i]-1].addAll(PrimeraPos);
                }
                break;
            case "+":
                Object ultimapos2[] =nodo.getUltimaPos().toArray();
                Set<Integer> PrimeraPos2 = nodo.getPrimeraPos();
                for (int i = 0; i < ultimapos2.length; i++) {
                    SiguientePos[(Integer) ultimapos2[i]-1].addAll(PrimeraPos2);
                }
                break;
        }
        
        
        generarsiguientePos(nodo.getizquierdo());
        generarsiguientePos(nodo.getizquierdo());
    }


    public Set<Integer>[] getSiguientePos() {
        return SiguientePos;
    }
    
    
}
