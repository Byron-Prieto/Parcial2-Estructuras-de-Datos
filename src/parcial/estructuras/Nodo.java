package parcial.estructuras;

/** Nodo simple de una lista enlazada. Es la base de la pila y de la cola propias. */
public class Nodo<T> {
    public T dato;
    public Nodo<T> siguiente;

    public Nodo(T dato) {
        this.dato = dato;
    }
}
