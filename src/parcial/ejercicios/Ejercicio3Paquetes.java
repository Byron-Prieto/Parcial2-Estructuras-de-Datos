package parcial.ejercicios;

import parcial.estructuras.Cola;
import parcial.modelo.Paquete;
import parcial.modelo.TipoEntrega;

/**
 * Ejercicio 3: selección de paquetes para una jornada.
 * Todo el procesamiento es recursivo (sin for, while ni do-while).
 */
public class Ejercicio3Paquetes {

    /** Si es true, se imprime cada paquete evaluado y si fue seleccionado o quedó pendiente. */
    public static boolean mostrarEvaluacion = false;

    /**
     * Retorna una cola con los paquetes seleccionados (primero urgentes, luego normales, cada grupo en
     * orden de registro) y los retira de 'pendientes'. Los que no caben quedan pendientes en su orden original.
     */
    public static Cola<Paquete> seleccionarPaquetes(Cola<Paquete> pendientes, int capacidad) {
        Cola<Paquete> seleccionados = new Cola<>();
        if (pendientes == null || pendientes.estaVacia() || capacidad <= 0) return seleccionados;

        // Pasada 1: urgentes. Pasada 2: normales. Cada pasada recorre la cola completa una sola vez.
        int restante = pasada(pendientes, seleccionados, pendientes.tamano(), capacidad, TipoEntrega.URGENTE);
        pasada(pendientes, seleccionados, pendientes.tamano(), restante, TipoEntrega.NORMAL);
        return seleccionados;
    }

    /**
     * Procesa 'n' elementos de la cola de pendientes. Si el paquete es del tipo buscado y cabe, se selecciona;
     * en cualquier otro caso se vuelve a encolar (así, tras n pasos el orden relativo de los pendientes
     * queda igual). Retorna la capacidad que queda.
     */
    private static int pasada(Cola<Paquete> pendientes, Cola<Paquete> seleccionados, int n, int capacidad, TipoEntrega tipo) {
        if (n == 0) return capacidad;
        Paquete p = pendientes.desencolar();
        if (p.getTipo() == tipo) {
            if (p.getPeso() <= capacidad) {
                seleccionados.encolar(p);
                if (mostrarEvaluacion)
                    System.out.println("  Evaluado " + p + " -> SELECCIONADO (capacidad restante: " + (capacidad - p.getPeso()) + " kg)");
                return pasada(pendientes, seleccionados, n - 1, capacidad - p.getPeso(), tipo);
            }
            if (mostrarEvaluacion)
                System.out.println("  Evaluado " + p + " -> PENDIENTE (no cabe, capacidad: " + capacidad + " kg)");
        }
        pendientes.encolar(p);
        return pasada(pendientes, seleccionados, n - 1, capacidad, tipo);
    }

    /** Suma recursiva de pesos (deja la cola intacta). */
    public static int pesoTotal(Cola<Paquete> cola) {
        return pesoTotal(cola, cola.tamano());
    }

    private static int pesoTotal(Cola<Paquete> cola, int n) {
        if (n == 0) return 0;
        Paquete p = cola.desencolar();
        cola.encolar(p);
        return p.getPeso() + pesoTotal(cola, n - 1);
    }
}
