package taller1;

public class Boleto {
    //Atributos
    private String ruta;
    private String cedula;
    private String nombre;
    private int cantidad;
    private double precioTotal;

    //Constructor
    public Boleto(String ruta, String cedula, String nombre, int cantidad, double precioTotal) {
        this.ruta = ruta;
        this.cedula = cedula;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioTotal = precioTotal;
    }

    //Métodos getter

    public String getRuta() {
        return ruta;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    @Override
    public String toString() {
        return "--- Boleto ----" +
                "\nRuta:  " + ruta +
                "\nCedula: " + cedula +
                "\nNombre: " + nombre +
                "\nCantidad: " + cantidad +
                "\nTotal: $" + precioTotal;
    }
}
