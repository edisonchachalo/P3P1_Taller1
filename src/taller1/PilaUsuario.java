package taller1;

import java.util.Stack;

public class PilaUsuario {
    private Stack<Pasajero> pila;

    // Constructor
    public PilaUsuario() {
        pila = new Stack<>();
    }

    // Apilar un pasajero
    public void push(Pasajero p) {
        pila.push(p);
    }

    // Desapilar (eliminar el último pasajero registrado)
    public Pasajero pop() throws Exception {
        if (pila.isEmpty()) {
            throw new Exception("No hay pasajeros registrados en la pila");
        }
        return pila.pop();
    }

    // Ver el último pasajero registrado sin eliminarlo
    public Pasajero peek() throws Exception {
        if (pila.isEmpty()) {
            throw new Exception("No hay pasajeros registrados en la pila");
        }
        return pila.peek();
    }

    // Cantidad de pasajeros registrados
    public int size() {
        return pila.size();
    }

    // Mostrar todos los pasajeros en la pila (historial LIFO)
    @Override
    public String toString() {
        String resultado = "";
        for (Pasajero p : pila) {
            resultado += p + "\n";
        }
        return resultado;
    }
}
