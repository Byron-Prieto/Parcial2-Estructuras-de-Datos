package parcial.ejercicios;

import parcial.estructuras.Cola;

/** Ejercicio 4: árbol binario de búsqueda (consulta por rango, recorridos y eliminación). */
public class Ejercicio4ABB {

    public static class NodoABB {
        public int clave;
        public NodoABB izq, der;
        public NodoABB(int clave) { this.clave = clave; }
    }

    /**
     * a) Cuenta los equipos con código en [inferior, superior] (extremos incluidos).
     * Usa el orden del árbol: si la clave es menor que el límite inferior, todo el subárbol izquierdo
     * es menor y se omite; si es mayor que el superior, se omite el derecho.
     */
    public static int contarEquiposEnRango(NodoABB raiz, int inferior, int superior) {
        if (raiz == null || inferior > superior) return 0;
        if (raiz.clave < inferior) return contarEquiposEnRango(raiz.der, inferior, superior);
        if (raiz.clave > superior) return contarEquiposEnRango(raiz.izq, inferior, superior);
        return 1 + contarEquiposEnRango(raiz.izq, inferior, superior)
                 + contarEquiposEnRango(raiz.der, inferior, superior);
    }

    // ---------- apoyo para el punto b (verificación del ejercicio hecho a mano) ----------

    /** Inserta ignorando claves repetidas (los códigos son únicos). */
    public static NodoABB insertar(NodoABB raiz, int clave) {
        if (raiz == null) return new NodoABB(clave);
        if (clave < raiz.clave) raiz.izq = insertar(raiz.izq, clave);
        else if (clave > raiz.clave) raiz.der = insertar(raiz.der, clave);
        return raiz;
    }

    /** Elimina una clave. Con dos hijos: la reemplaza por el MAYOR del subárbol izquierdo. */
    public static NodoABB eliminar(NodoABB raiz, int clave) {
        if (raiz == null) return null;
        if (clave < raiz.clave) raiz.izq = eliminar(raiz.izq, clave);
        else if (clave > raiz.clave) raiz.der = eliminar(raiz.der, clave);
        else {
            if (raiz.izq == null) return raiz.der;
            if (raiz.der == null) return raiz.izq;
            NodoABB mayor = raiz.izq;
            while (mayor.der != null) mayor = mayor.der;
            raiz.clave = mayor.clave;
            raiz.izq = eliminar(raiz.izq, mayor.clave);
        }
        return raiz;
    }

    public static String inorden(NodoABB n) {
        return n == null ? "" : (inorden(n.izq) + n.clave + " " + inorden(n.der));
    }

    public static String preorden(NodoABB n) {
        return n == null ? "" : (n.clave + " " + preorden(n.izq) + preorden(n.der));
    }

    public static String postorden(NodoABB n) {
        return n == null ? "" : (postorden(n.izq) + postorden(n.der) + n.clave + " ");
    }

    public static String amplitud(NodoABB raiz) {
        if (raiz == null) return "";
        Cola<NodoABB> cola = new Cola<>();
        cola.encolar(raiz);
        StringBuilder sb = new StringBuilder();
        while (!cola.estaVacia()) {
            NodoABB n = cola.desencolar();
            sb.append(n.clave).append(' ');
            if (n.izq != null) cola.encolar(n.izq);
            if (n.der != null) cola.encolar(n.der);
        }
        return sb.toString();
    }
}
