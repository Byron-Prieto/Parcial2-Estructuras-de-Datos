package parcial.ejercicios;

import parcial.estructuras.Cola;
import parcial.estructuras.Pila;

/** Conversión de infija a postfija y evaluación con pila (con tabla paso a paso). */
public class ExpresionPostfija {

    private static int prioridad(String op) {
        switch (op) {
            case "*": case "/": return 2;
            case "+": case "-": return 1;
            default: return 0; // "("
        }
    }

    private static boolean esOperador(String t) {
        return t.equals("+") || t.equals("-") || t.equals("*") || t.equals("/");
    }

    /** Convierte una lista de tokens infijos a postfijos (algoritmo de la pila de operadores). */
    public static Cola<String> aPostfija(String[] tokens, boolean mostrarTabla) {
        Cola<String> salida = new Cola<>();
        Pila<String> operadores = new Pila<>();
        if (mostrarTabla) System.out.printf("%-10s | %-28s | %s%n", "Elemento", "Pila de operadores", "Salida");
        for (String t : tokens) {
            if (t.equals("(")) {
                operadores.push(t);
            } else if (t.equals(")")) {
                while (!operadores.peek().equals("(")) salida.encolar(operadores.pop());
                operadores.pop(); // descarta "("
            } else if (esOperador(t)) {
                while (!operadores.estaVacia() && prioridad(operadores.peek()) >= prioridad(t))
                    salida.encolar(operadores.pop());
                operadores.push(t);
            } else {
                salida.encolar(t); // operando
            }
            if (mostrarTabla) System.out.printf("%-10s | %-28s | %s%n", t, operadores, salida);
        }
        while (!operadores.estaVacia()) salida.encolar(operadores.pop());
        if (mostrarTabla) System.out.printf("%-10s | %-28s | %s%n", "(fin)", operadores, salida);
        return salida;
    }

    /** Reemplaza A, B, C, D por sus valores (la cola original no se modifica). */
    public static Cola<String> sustituir(Cola<String> postfija, double a, double b, double c, double d) {
        Cola<String> res = new Cola<>();
        int n = postfija.tamano();
        for (int i = 0; i < n; i++) {
            String t = postfija.desencolar();
            postfija.encolar(t);
            switch (t) {
                case "A": res.encolar(formato(a)); break;
                case "B": res.encolar(formato(b)); break;
                case "C": res.encolar(formato(c)); break;
                case "D": res.encolar(formato(d)); break;
                default: res.encolar(t);
            }
        }
        return res;
    }

    /** Evalúa una expresión postfija con una pila, mostrando elemento, operación y contenido de la pila. */
    public static double evaluar(Cola<String> postfija, boolean mostrarTabla) {
        Pila<Double> pila = new Pila<>();
        if (mostrarTabla) System.out.printf("%-4s | %-10s | %-26s | %s%n", "Paso", "Elemento", "Operación", "Pila (tope → fondo)");
        int paso = 1;
        while (!postfija.estaVacia()) {
            String t = postfija.desencolar();
            String operacion;
            if (esOperador(t)) {
                double derecho = pila.pop();
                double izquierdo = pila.pop();
                double r;
                switch (t) {
                    case "+": r = izquierdo + derecho; break;
                    case "-": r = izquierdo - derecho; break;
                    case "*": r = izquierdo * derecho; break;
                    default:  r = izquierdo / derecho;
                }
                operacion = formato(izquierdo) + " " + t + " " + formato(derecho) + " = " + formato(r);
                pila.push(r);
            } else {
                pila.push(Double.parseDouble(t));
                operacion = "apilar " + t;
            }
            if (mostrarTabla) System.out.printf("%-4d | %-10s | %-26s | %s%n", paso, t, operacion, textoPila(pila));
            paso++;
        }
        return pila.pop();
    }

    /** Contenido de la pila (tope → fondo) con números sin ".0" innecesario. */
    private static String textoPila(Pila<Double> p) {
        Pila<Double> aux = new Pila<>();
        while (!p.estaVacia()) aux.push(p.pop());          // aux: el fondo queda en la cima
        Pila<Double> mostrar = new Pila<>();
        while (!aux.estaVacia()) { double v = aux.pop(); p.push(v); mostrar.push(v); }
        StringBuilder sb = new StringBuilder("[");
        boolean primero = true;
        while (!mostrar.estaVacia()) {                     // mostrar: tope de p en la cima
            if (!primero) sb.append(" → ");
            sb.append(formato(mostrar.pop()));
            primero = false;
        }
        return sb.append("]").toString();
    }

    public static String formato(double v) {
        return v == Math.rint(v) ? String.valueOf((long) v) : String.valueOf(v);
    }
}
