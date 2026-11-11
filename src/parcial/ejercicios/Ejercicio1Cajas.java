package parcial.ejercicios;

import parcial.estructuras.Pila;
import parcial.modelo.Caja;

/** Ejercicio 1: retiro de cajas y reorganización de la pila. */
public class Ejercicio1Cajas {

    /**
     * Retira la caja con el código dado y reorganiza las restantes:
     * - valor retirado par   -> posiciones impares arriba, luego las pares.
     * - valor retirado impar -> posiciones pares arriba, luego las impares.
     * Las posiciones se cuentan desde la cima (1, 2, 3...) con la caja ya retirada.
     * Cada grupo conserva su orden relativo.
     *
     * @return true si encontró y retiró la caja; false si no existe o la pila está vacía.
     */
    public static boolean retirarCaja(Pila<Caja> pila, String codigo) {
        if (pila == null || pila.estaVacia()) return false;

        // 1) Buscar la caja: se baja por la pila guardando lo que se va sacando
        Pila<Caja> aux = new Pila<>();
        Caja retirada = null;
        while (!pila.estaVacia() && retirada == null) {
            Caja c = pila.pop();
            if (c.getCodigo().equals(codigo)) retirada = c; else aux.push(c);
        }

        // 2) Devolver lo que estaba encima de la caja (queda el mismo orden, sin la retirada)
        while (!aux.estaVacia()) pila.push(aux.pop());
        if (retirada == null) return false; // no existía: la pila quedó idéntica

        // 3) Separar por posición (desde la cima, empezando en 1)
        Pila<Caja> impares = new Pila<>();
        Pila<Caja> pares = new Pila<>();
        int posicion = 1;
        while (!pila.estaVacia()) {
            Caja c = pila.pop();
            if (posicion % 2 == 1) impares.push(c); else pares.push(c);
            posicion++;
        }

        // 4) Rearmar: primero se apila el grupo que quedará ABAJO y luego el que queda ARRIBA.
        //    (cada pila auxiliar tiene el orden invertido, así que al pasarla vuelve el orden original)
        boolean valorPar = retirada.getValor() % 2 == 0;
        Pila<Caja> abajo = valorPar ? pares : impares;
        Pila<Caja> arriba = valorPar ? impares : pares;
        while (!abajo.estaVacia()) pila.push(abajo.pop());
        while (!arriba.estaVacia()) pila.push(arriba.pop());
        return true;
    }

    /** Variables A, B, C, D a partir de las cuatro cajas de la pila final (desde la cima). */
    public static double[] calcularVariables(Pila<Caja> pilaFinal) {
        // Se copia la pila sin perder la original: tras este bloque 'lectura' tiene la cima de la
        // original arriba (primera caja = cima) y la pila original queda como estaba.
        Pila<Caja> invertida = new Pila<>();
        while (!pilaFinal.estaVacia()) invertida.push(pilaFinal.pop());
        Pila<Caja> lectura = new Pila<>();
        while (!invertida.estaVacia()) { Caja c = invertida.pop(); pilaFinal.push(c); lectura.push(c); }

        double a = lectura.pop().getValor();
        double b = lectura.pop().getValor() / a;       // decimal
        double c = lectura.pop().getValor() + a;
        double d = (lectura.pop().getValor() + c) * a;
        return new double[]{a, b, c, d};
    }
}
