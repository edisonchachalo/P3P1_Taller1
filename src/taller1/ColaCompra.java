package taller1;

import java.util.LinkedList;
import java.util.Queue;

public class ColaCompra {
    private Queue<Boleto> compras;

    public ColaCompra(){
        compras = new LinkedList<Boleto>();
    }

    public void encolar(Boleto b){
        compras.add(b);
    }

    public Boleto desencolar() throws Exception{
        if (compras.isEmpty()){
            throw new Exception("No hay boletos comprados");
        }
        return compras.poll();
    }

    public Boleto primero() throws Exception{
        if (compras.isEmpty()){
            throw new Exception("No hay boletos comprados");
        }
        return compras.peek();
    }

    public int size(){
        return compras.size();
    }

    //Total de boletos vendidos por ruta (cola)
    public int totalBoletosVendidos(){
        int total=0;
        for (Boleto b : compras){
            total+=b.getCantidad();
        }
        return total;
    }

    //Total de boletos comprados por cédula
    public int boletosPorCedula(String cedula){
        int total = 0;
        for (Boleto b : compras){
            if (b.getCedula().equals(cedula)){
                total += b.getCantidad();
            }
        }
        return total;
    }

    //Monto recaudado por ruta (cola)
    public double montoRecaudado(){
        double total = 0;
        for (Boleto b : compras){
            total += b.getPrecioTotal();
        }
        return total;
    }

    @Override
    public String toString(){
        String resultado = "";
        for (Boleto b : compras){
            resultado += b + "\n";
        }
        return resultado;
    }
}
