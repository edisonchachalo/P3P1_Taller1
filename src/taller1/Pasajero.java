package taller1;

public class Pasajero implements Comparable<Pasajero> {
    private String nombre;
    private String cedula;
    private int prioridad; // 3 = Tercera Edad, 2 = Embarazada, 1 = Normal

    public Pasajero(String nombre, String cedula, int prioridad) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.prioridad = prioridad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public int getPrioridad() {
        return prioridad;
    }

    // Definimos el criterio de comparación para la cola de prioridad
    @Override
    public int compareTo(Pasajero p) {
        // Queremos que los de mayor prioridad (3) estén primero
        return Integer.compare(p.prioridad, this.prioridad);
    }

    @Override
    public String toString() {
        String tipoPrioridad;
        switch (prioridad) {
            case 3: tipoPrioridad = "Tercera Edad"; break;
            case 2: tipoPrioridad = "Embarazada"; break;
            case 1: tipoPrioridad = "Normal"; break;
            default: tipoPrioridad = "No definida"; break;
        }
        return "--- Pasajero ----" +
                "\nNombre: " + nombre +
                "\nCédula: " + cedula +
                "\nPrioridad: " + tipoPrioridad;
    }
}
