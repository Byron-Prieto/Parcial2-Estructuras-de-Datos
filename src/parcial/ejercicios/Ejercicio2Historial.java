package parcial.ejercicios;

import parcial.estructuras.Pila;

/** Ejercicio 2: obtener un segmento del historial (pila). */
public class Ejercicio2Historial {

    /**
     * Retorna una nueva pila con las acciones entre las posiciones inicio y fin (ambas incluidas),
     * contadas desde la cima empezando en 1. La acción en 'inicio' queda en la cima del resultado.
     * La pila original conserva sus elementos y su orden.
     * Si inicio < 1, inicio > fin o fin supera el tamaño, retorna una pila vacía.
     */
    public static <T> Pila<T> obtenerSegmento(Pila<T> original, int inicio, int fin) {
        Pila<T> resultado = new Pila<>();
        if (original == null || inicio < 1 || inicio > fin || fin > original.tamano()) return resultado;

        Pila<T> aux = new Pila<>();      // guarda TODO lo sacado (para restaurar la original)
        Pila<T> segmento = new Pila<>(); // guarda el segmento en orden invertido
        int posicion = 1;
        while (!original.estaVacia()) {
            T accion = original.pop();
            if (posicion >= inicio && posicion <= fin) segmento.push(accion);
            aux.push(accion);
            posicion++;
        }
        while (!aux.estaVacia()) original.push(aux.pop());            // restaura el orden original
        while (!segmento.estaVacia()) resultado.push(segmento.pop()); // invierte: 'inicio' queda arriba
        return resultado;
    }
}
