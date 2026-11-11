package parcial.estructuras;

/** Pila propia (LIFO) implementada con nodos enlazados. */
public class Pila<T> {
    private Nodo<T> tope;
    private int tamano;

    public void push(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.siguiente = tope;
        tope = nuevo;
        tamano++;
    }

    public T pop() {
        if (estaVacia()) throw new IllegalStateException("La pila está vacía");
        T dato = tope.dato;
        tope = tope.siguiente;
        tamano--;
        return dato;
    }

    public T peek() {
        if (estaVacia()) throw new IllegalStateException("La pila está vacía");
        return tope.dato;
    }

    public boolean estaVacia() { return tope == null; }

    public int tamano() { return tamano; }

    /** Contenido desde el tope hasta el fondo. */
    @Override
    public String toString() {
        if (estaVacia()) return "[vacía]";
        return "[" + texto(tope) + "]";
    }

    private String texto(Nodo<T> n) {
        return n.siguiente == null ? String.valueOf(n.dato) : n.dato + " → " + texto(n.siguiente);
    }
}
