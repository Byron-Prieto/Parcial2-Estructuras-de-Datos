# Parcial II – Estructuras de Datos (práctica)

Ejercicios 1 a 4 en Java con **estructuras propias** (`Pila`, `Cola`, `Nodo`, `NodoABB`); no se usan
colecciones de `java.util`.

## Estructura

```
src/parcial/
  estructuras/  Nodo, Pila, Cola
  modelo/       Caja, Paquete, TipoEntrega
  ejercicios/   Ejercicio1Cajas, ExpresionPostfija, Ejercicio2Historial, Ejercicio3Paquetes, Ejercicio4ABB
  Main.java               ejecuta los datos de prueba de cada enunciado
  PruebasAutomaticas.java casos límite (pila vacía, código inexistente, rangos inválidos, etc.)
```

## Cómo ejecutar (JDK 11 o superior)

- Linux / macOS: `sh compilar_y_ejecutar.sh`
- Windows: `compilar_y_ejecutar.bat`
- Manual: `javac -encoding UTF-8 -d out $(find src -name "*.java")` y `java -cp out parcial.Main`

Los ejercicios 5 (AVL y rojo-negro) se resuelven en el documento de respuestas.
