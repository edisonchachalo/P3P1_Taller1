package taller1;

import java.util.LinkedList;
import java.util.Queue;

public class ColaCompra {
    private Queue<Boleto> compras;

    // Constructor
    public ColaCompra() {
        compras = new LinkedList<>();
    }

    // Encolar un boleto (agregar al final)
    public void encolar(Boleto b) {
        compras.add(b);
    }

    // Desencolar un boleto (quitar el primero)
    public Boleto desencolar() throws Exception {
        if (compras.isEmpty()) {
            throw new Exception("No hay boletos comprados en la cola");
        }
        return compras.poll();
    }

    // Ver el primer boleto sin eliminarlo
    public Boleto peek() throws Exception {
        if (compras.isEmpty()) {
            throw new Exception("No hay boletos comprados en la cola");
        }
        return compras.peek();
    }

    // Cantidad de boletos en la cola
    public int size() {
        return compras.size();
    }

    // Total de boletos vendidos en la cola
    public int totalBoletosVendidos() {
        int total = 0;
        for (Boleto b : compras) {
            total += b.getCantidad();
        }
        return total;
    }

    // Monto recaudado en la cola
    public double montoRecaudado() {
        double total = 0;
        for (Boleto b : compras) {
            total += b.getPrecioTotal();
        }
        return total;
    }

    // Nuevo método: total de boletos comprados por una cédula
    public int boletosPorCedula(String cedula) {
        int total = 0;
        for (Boleto b : compras) {
            if (b.getPasajero().getCedula().equals(cedula)) {
                total += b.getCantidad();
            }
        }
        return total;
    }

    // Mostrar todos los boletos en la cola
    @Override
    public String toString() {
        String resultado = "";
        for (Boleto b : compras) {
            resultado += b + "\n";
        }
        return resultado;
    }

}
