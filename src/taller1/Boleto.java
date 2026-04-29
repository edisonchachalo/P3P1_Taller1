package taller1;

public class Boleto {
    private String ruta;
    private int cantidad;
    private double precioTotal;
    private Pasajero pasajero; // relación: cada boleto pertenece a un pasajero

    // Constructor
    public Boleto(String ruta, Pasajero pasajero, int cantidad, double precioTotal) {
        this.ruta = ruta;
        this.pasajero = pasajero;
        this.cantidad = cantidad;
        this.precioTotal = precioTotal;
    }

    // Getters
    public String getRuta() {
        return ruta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    @Override
    public String toString() {
        return "--- Boleto ----" +
                "\nRuta: " + ruta +
                "\nPasajero: " + pasajero.getNombre() +
                "\nCédula: " + pasajero.getCedula() +
                "\nCantidad: " + cantidad +
                "\nTotal: $" + precioTotal;
    }
}
