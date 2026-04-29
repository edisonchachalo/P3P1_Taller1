package taller1;

import java.util.PriorityQueue;

public class ColaAbordaje {
    private PriorityQueue<Pasajero> colaPrioridad;

    // Constructor
    public ColaAbordaje() {
        // Usa el compareTo de Pasajero
        colaPrioridad = new PriorityQueue<>();
    }

    // Encolar pasajero
    public void encolar(Pasajero p) {
        colaPrioridad.add(p);
    }

    // Desencolar pasajero (el de mayor prioridad)
    public Pasajero desencolar() throws Exception {
        if (colaPrioridad.isEmpty()) {
            throw new Exception("No hay pasajeros en la cola de abordaje");
        }
        return colaPrioridad.poll();
    }

    // Ver el primer pasajero en abordar sin eliminarlo
    public Pasajero peek() throws Exception {
        if (colaPrioridad.isEmpty()) {
            throw new Exception("No hay pasajeros en la cola de abordaje");
        }
        return colaPrioridad.peek();
    }

    // Cantidad de pasajeros en la cola
    public int size() {
        return colaPrioridad.size();
    }

    // Mostrar todos los pasajeros en la cola (orden de abordaje)
    @Override
    public String toString() {
        String resultado = "";
        for (Pasajero p : colaPrioridad) {
            resultado += p + "\n";
        }
        return resultado;
    }
}
