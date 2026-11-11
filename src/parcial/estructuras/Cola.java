package parcial.estructuras;

/** Cola propia (FIFO) implementada con nodos enlazados. */
public class Cola<T> {
    private Nodo<T> frente;
    private Nodo<T> fin;
    private int tamano;

    public void encolar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (estaVacia()) frente = nuevo; else fin.siguiente = nuevo;
        fin = nuevo;
        tamano++;
    }

    public T desencolar() {
        if (estaVacia()) throw new IllegalStateException("La cola está vacía");
        T dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) fin = null;
        tamano--;
        return dato;
    }

    public T frente() {
        if (estaVacia()) throw new IllegalStateException("La cola está vacía");
        return frente.dato;
    }

    public boolean estaVacia() { return frente == null; }

    public int tamano() { return tamano; }

    /** Contenido desde el frente hasta el final. */
    @Override
    public String toString() {
        if (estaVacia()) return "[vacía]";
        return "[" + texto(frente) + "]";
    }

    private String texto(Nodo<T> n) {
        return n.siguiente == null ? String.valueOf(n.dato) : n.dato + " → " + texto(n.siguiente);
    }
}
