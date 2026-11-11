package parcial;

import parcial.ejercicios.*;
import parcial.ejercicios.Ejercicio4ABB.NodoABB;
import parcial.estructuras.Cola;
import parcial.estructuras.Pila;
import parcial.modelo.Caja;
import parcial.modelo.Paquete;
import parcial.modelo.TipoEntrega;

/** Ejecuta las pruebas con los datos del enunciado de cada ejercicio. */
public class Main {

    public static void main(String[] args) {
        ejercicio1();
        ejercicio2();
        ejercicio3();
        ejercicio4();
        PruebasAutomaticas.ejecutar();
    }

    // ------------------------------------------------------------------ 1
    static void ejercicio1() {
        titulo("EJERCICIO 1 - Retiro de cajas y evaluación de la expresión");
        Pila<Caja> pila = new Pila<>();
        pila.push(new Caja("C1", 2)); pila.push(new Caja("C2", 4)); pila.push(new Caja("C3", 6));
        pila.push(new Caja("C4", 8)); pila.push(new Caja("C5", 3)); pila.push(new Caja("C6", 9));
        pila.push(new Caja("C7", 5));
        System.out.println("Pila inicial (tope → fondo): " + pila);

        for (String codigo : new String[]{"C3", "C4", "C7"}) {
            boolean ok = Ejercicio1Cajas.retirarCaja(pila, codigo);
            System.out.println("retirarCaja(" + codigo + ") = " + ok + "  →  " + pila);
        }
        System.out.println("Pila final (cima → fondo): " + pila);

        double[] v = Ejercicio1Cajas.calcularVariables(pila);
        System.out.println();
        System.out.println("A = " + ExpresionPostfija.formato(v[0]) + "   B = " + ExpresionPostfija.formato(v[1])
                + "   C = " + ExpresionPostfija.formato(v[2]) + "   D = " + ExpresionPostfija.formato(v[3]));

        System.out.println("\n-- Conversión de infija a postfija: (A + B) * C - (A - B) * D --");
        String[] infija = {"(", "A", "+", "B", ")", "*", "C", "-", "(", "A", "-", "B", ")", "*", "D"};
        Cola<String> postfija = ExpresionPostfija.aPostfija(infija, true);
        System.out.println("Postfija: " + postfija);

        Cola<String> conValores = ExpresionPostfija.sustituir(postfija, v[0], v[1], v[2], v[3]);
        System.out.println("\nCon valores: " + conValores);
        System.out.println("\n-- Evaluación con pila --");
        double resultado = ExpresionPostfija.evaluar(conValores, true);
        System.out.println("Resultado final = " + ExpresionPostfija.formato(resultado));
    }

    // ------------------------------------------------------------------ 2
    static void ejercicio2() {
        titulo("EJERCICIO 2 - Segmento del historial");
        Pila<String> historial = new Pila<>();
        for (int i = 1; i <= 6; i++) historial.push("A" + i);
        System.out.println("Original (tope → fondo): " + historial);
        System.out.println("inicio=2, fin=4 → " + Ejercicio2Historial.obtenerSegmento(historial, 2, 4));
        System.out.println("inicio=3, fin=3 → " + Ejercicio2Historial.obtenerSegmento(historial, 3, 3));
        System.out.println("inicio=0, fin=2 → " + Ejercicio2Historial.obtenerSegmento(historial, 0, 2));
        System.out.println("inicio=4, fin=2 → " + Ejercicio2Historial.obtenerSegmento(historial, 4, 2));
        System.out.println("inicio=5, fin=7 → " + Ejercicio2Historial.obtenerSegmento(historial, 5, 7));
        System.out.println("Original después (debe ser igual): " + historial);
    }

    // ------------------------------------------------------------------ 3
    static void ejercicio3() {
        titulo("EJERCICIO 3 - Selección de paquetes (capacidad 14 kg)");
        Cola<Paquete> cola = new Cola<>();
        cola.encolar(new Paquete("P1", TipoEntrega.NORMAL, 4));
        cola.encolar(new Paquete("P2", TipoEntrega.URGENTE, 7));
        cola.encolar(new Paquete("P3", TipoEntrega.NORMAL, 3));
        cola.encolar(new Paquete("P4", TipoEntrega.URGENTE, 5));
        cola.encolar(new Paquete("P5", TipoEntrega.NORMAL, 6));
        cola.encolar(new Paquete("P6", TipoEntrega.URGENTE, 4));
        cola.encolar(new Paquete("P7", TipoEntrega.NORMAL, 2));
        System.out.println("Cola inicial (frente → final): " + cola);

        Ejercicio3Paquetes.mostrarEvaluacion = true;
        System.out.println("Evaluación:");
        Cola<Paquete> seleccionados = Ejercicio3Paquetes.seleccionarPaquetes(cola, 14);
        Ejercicio3Paquetes.mostrarEvaluacion = false;

        System.out.println("Cola retornada (seleccionados): " + seleccionados);
        System.out.println("Cola de pendientes:             " + cola);
        System.out.println("Capacidad disponible restante:  " + (14 - Ejercicio3Paquetes.pesoTotal(seleccionados)) + " kg");
    }

    // ------------------------------------------------------------------ 4
    static void ejercicio4() {
        titulo("EJERCICIO 4 - ABB: consulta por rango y verificación del punto b");
        int[] claves = {41, 20, 52, 16, 6, 13, 12, 60, 22, 47, 50, 60, 43, 44};
        NodoABB raiz = null;
        for (int c : claves) raiz = Ejercicio4ABB.insertar(raiz, c);   // el segundo 60 se ignora (repetido)
        System.out.println("Inorden:   " + Ejercicio4ABB.inorden(raiz));
        System.out.println("Preorden:  " + Ejercicio4ABB.preorden(raiz));
        System.out.println("Postorden: " + Ejercicio4ABB.postorden(raiz));
        System.out.println("Amplitud:  " + Ejercicio4ABB.amplitud(raiz));

        System.out.println("\ncontarEquiposEnRango(13, 47) = " + Ejercicio4ABB.contarEquiposEnRango(raiz, 13, 47));
        System.out.println("contarEquiposEnRango(40, 50) = " + Ejercicio4ABB.contarEquiposEnRango(raiz, 40, 50));
        System.out.println("contarEquiposEnRango(70, 90) = " + Ejercicio4ABB.contarEquiposEnRango(raiz, 70, 90));
        System.out.println("contarEquiposEnRango(50, 10) = " + Ejercicio4ABB.contarEquiposEnRango(raiz, 50, 10));
        System.out.println("contarEquiposEnRango(null)   = " + Ejercicio4ABB.contarEquiposEnRango(null, 1, 10));

        raiz = Ejercicio4ABB.eliminar(raiz, 41);
        System.out.println("\nTras eliminar 41 (mayor del subárbol izquierdo = 22):");
        System.out.println("Raíz: " + raiz.clave);
        System.out.println("Inorden:   " + Ejercicio4ABB.inorden(raiz));
        System.out.println("Preorden:  " + Ejercicio4ABB.preorden(raiz));
        System.out.println("Amplitud:  " + Ejercicio4ABB.amplitud(raiz));
    }

    static void titulo(String t) {
        System.out.println("\n==============================================================");
        System.out.println(t);
        System.out.println("==============================================================");
    }
}
