package taller1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana {
    private JPanel Ventana;
    private JButton btnComprar;
    private JTextArea textArea1;
    private JComboBox cmbRuta;
    private JTextField txtCedula;
    private JTextField txtNombre;
    private JTextField txtCantidad;
    private JLabel lblVendidosQG;
    private JLabel lblVendidosQC;
    private JLabel lblVendidosQL;
    private JLabel lblDisponiblesQG;
    private JLabel lblDisponiblesQC;
    private JLabel lblDisponiblesQL;
    private JLabel lblMontoTotal;

    private static final int max_asientos = 20;
    private static final int max_boletos_transaccion = 5;

    private ColaCompra colaQuitoGuayaquil;
    private ColaCompra colaQuitoCuenca;
    private ColaCompra colaQuitoLoja;

    public Ventana() {
        colaQuitoGuayaquil = new ColaCompra();
        colaQuitoCuenca = new ColaCompra();
        colaQuitoLoja = new ColaCompra();


        btnComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String ruta = cmbRuta.getSelectedItem().toString();
                    String cedula = txtCedula.getText();
                    String nombre = txtNombre.getText();
                    int cantidad = Integer.parseInt(txtCantidad.getText());

                    if (cedula.isEmpty() || nombre.isEmpty()) {
                        textArea1.setText("Error: Ingrese cédula y nombre del pasajero");
                        return;
                    }

                    if (!cedula.matches("\\d{1,10}")) {
                        textArea1.setText("Error: La cédula debe contener solo números positivos (1 a 10 dígitos)");
                        return;
                    }

                    if (cantidad <= 0 || cantidad > max_boletos_transaccion) {
                        textArea1.setText("Error: La cantidad de boletos debe estar entre 1 y 5 boletos");
                        return;
                    }

                    //Obtener cola y precio según ruta seleccionada
                    ColaCompra colaActual;
                    double precioPorBoleto;

                    if (ruta.equals("Quito - Guayaquil")) {
                        colaActual = colaQuitoGuayaquil;
                        precioPorBoleto = 10.50;
                    } else if (ruta.equals("Quito - Cuenca")) {
                        colaActual = colaQuitoCuenca;
                        precioPorBoleto = 12.75;
                    } else {
                        colaActual = colaQuitoLoja;
                        precioPorBoleto = 15.00;
                    }

                    //Validar asientos disponibles en la ruta
                    if (colaActual.totalBoletosVendidos() + cantidad > max_asientos) {
                        int disponibles = max_asientos - colaActual.totalBoletosVendidos();
                        textArea1.setText("Error: Solo quedan " + disponibles + " asientos disponibles para esta ruta");
                        return;
                    }

                    //Validar 5 boletos por cédula
                    if (colaActual.boletosPorCedula(cedula) + cantidad > max_boletos_transaccion) {
                        int boletosRestantesCedula = max_boletos_transaccion - colaActual.boletosPorCedula(cedula);
                        textArea1.setText("Error: La cédula " + cedula + " solo puede comprar " + boletosRestantesCedula + " boleto(s) más para esta ruta");
                        return;
                    }

                    //Crear boleto
                    double precioTotal = cantidad * precioPorBoleto;
                    Boleto boleto = new Boleto(ruta, cedula, nombre, cantidad, precioTotal);
                    colaActual.encolar(boleto);
                    textArea1.setText("Compra exitosa\n"+boleto);

                    setear();

                    actualizarLabels();

                } catch (NumberFormatException ex) {
                    textArea1.setText("Error: Ingrese un número válido en el campo de Cantidad boletos. \nCantidad máxima 5 boletos");
                }
            }
        });
    }

    public void setear() {
        txtCedula.setText("");
        txtNombre.setText("");
        txtCantidad.setText("");
        cmbRuta.setSelectedIndex(0);
    }

    public void actualizarLabels() {
        lblVendidosQG.setText("Vendidos Quito - Guayaquil: " + colaQuitoGuayaquil.totalBoletosVendidos());
        lblVendidosQC.setText("Vendidos Quito - Cuenca: " + colaQuitoCuenca.totalBoletosVendidos());
        lblVendidosQL.setText("Vendidos Quito - Loja: " + colaQuitoLoja.totalBoletosVendidos());

        lblDisponiblesQG.setText("Disponibles Quito - Guayaquil: " + (max_asientos - colaQuitoGuayaquil.totalBoletosVendidos()));
        lblDisponiblesQC.setText("Disponibles Quito - Cuenca: " + (max_asientos - colaQuitoCuenca.totalBoletosVendidos()));
        lblDisponiblesQL.setText("Disponibles Quito - Loja: " + (max_asientos - colaQuitoLoja.totalBoletosVendidos()));

        double montoTotal = colaQuitoGuayaquil.montoRecaudado() + colaQuitoCuenca.montoRecaudado() + colaQuitoLoja.montoRecaudado();
        lblMontoTotal.setText("Monto Total Recaudado: $" + montoTotal);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().Ventana);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
