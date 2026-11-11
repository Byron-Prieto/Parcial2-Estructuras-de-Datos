package parcial;

import parcial.ejercicios.*;
import parcial.ejercicios.Ejercicio4ABB.NodoABB;
import parcial.estructuras.Cola;
import parcial.estructuras.Pila;
import parcial.modelo.Caja;
import parcial.modelo.Paquete;
import parcial.modelo.TipoEntrega;

/** Casos límite verificados automáticamente (sin librerías externas). */
public class PruebasAutomaticas {
    private static int ok = 0, fallos = 0;

    static void verificar(boolean condicion, String descripcion) {
        if (condicion) ok++; else { fallos++; System.out.println("  FALLO: " + descripcion); }
    }

    public static void ejecutar() {
        Main.titulo("PRUEBAS DE CASOS LÍMITE");

        // Ejercicio 1
        Pila<Caja> vacia = new Pila<>();
        verificar(!Ejercicio1Cajas.retirarCaja(vacia, "C1"), "pila vacía debe retornar false");
        Pila<Caja> p = new Pila<>();
        p.push(new Caja("C1", 2)); p.push(new Caja("C2", 3)); p.push(new Caja("C3", 7));
        String antes = p.toString();
        verificar(!Ejercicio1Cajas.retirarCaja(p, "X9"), "código inexistente debe retornar false");
        verificar(antes.equals(p.toString()), "código inexistente no debe alterar la pila");
        verificar(Ejercicio1Cajas.retirarCaja(p, "C3"), "debe retirar C3");
        verificar(p.tamano() == 2, "tras retirar debe haber 2 cajas");

        Pila<Caja> fin = new Pila<>();           // pila final del enunciado, de fondo a cima: 3, 4, 9, 2
        fin.push(new Caja("C5", 3)); fin.push(new Caja("C2", 4)); fin.push(new Caja("C6", 9)); fin.push(new Caja("C1", 2));
        double[] vars = Ejercicio1Cajas.calcularVariables(fin);
        verificar(vars[0] == 2 && vars[1] == 4.5 && vars[2] == 6 && vars[3] == 18, "variables A=2, B=4.5, C=6, D=18");
        verificar(fin.tamano() == 4 && fin.peek().getCodigo().equals("C1"), "calcularVariables no altera la pila");

        // Ejercicio 2
        Pila<Integer> h = new Pila<>();
        for (int i = 1; i <= 5; i++) h.push(i);   // tope → fondo: 5 4 3 2 1
        verificar(Ejercicio2Historial.obtenerSegmento(h, 1, 5).toString().equals("[5 → 4 → 3 → 2 → 1]"), "segmento completo");
        verificar(Ejercicio2Historial.obtenerSegmento(h, 2, 3).toString().equals("[4 → 3]"), "segmento 2..3");
        verificar(Ejercicio2Historial.obtenerSegmento(h, 6, 6).estaVacia(), "fin > tamaño → vacía");
        verificar(Ejercicio2Historial.obtenerSegmento(h, 0, 3).estaVacia(), "inicio < 1 → vacía");
        verificar(h.toString().equals("[5 → 4 → 3 → 2 → 1]"), "la original no cambia");

        // Ejercicio 3
        Cola<Paquete> c = new Cola<>();
        c.encolar(new Paquete("A", TipoEntrega.NORMAL, 5));
        Cola<Paquete> r = Ejercicio3Paquetes.seleccionarPaquetes(c, 0);
        verificar(r.estaVacia() && c.tamano() == 1, "capacidad 0 → vacía y pendientes intactos");
        r = Ejercicio3Paquetes.seleccionarPaquetes(new Cola<>(), 10);
        verificar(r.estaVacia(), "cola vacía → vacía");
        r = Ejercicio3Paquetes.seleccionarPaquetes(c, 3);
        verificar(r.estaVacia() && c.tamano() == 1, "paquete que no cabe queda pendiente");

        // Ejercicio 4
        NodoABB raiz = null;
        for (int k : new int[]{50, 30, 70, 20, 40, 60, 80}) raiz = Ejercicio4ABB.insertar(raiz, k);
        verificar(Ejercicio4ABB.contarEquiposEnRango(raiz, 20, 80) == 7, "rango que abarca todo");
        verificar(Ejercicio4ABB.contarEquiposEnRango(raiz, 40, 60) == 3, "rango 40..60");
        verificar(Ejercicio4ABB.contarEquiposEnRango(raiz, 51, 59) == 0, "rango sin claves");
        verificar(Ejercicio4ABB.contarEquiposEnRango(raiz, 30, 30) == 1, "límites iguales");
        verificar(Ejercicio4ABB.contarEquiposEnRango(raiz, 80, 20) == 0, "inferior > superior → 0");

        System.out.println("Pruebas correctas: " + ok + "   Fallos: " + fallos);
    }
}
