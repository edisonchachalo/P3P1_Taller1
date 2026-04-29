package taller1;

import javax.swing.*;

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
    private JButton btnOrdenAbordaje;
    private JButton btnUltimoRegistroUser;
    private JComboBox cmbPrioridad;
    private JButton btnMostrarTodos;
    private JButton btnPrimeroEnAbordar;

    private static final int max_asientos = 20;
    private static final int max_boletos_transaccion = 5;

    // Estructuras por ruta
    private ColaCompra colaQuitoGuayaquil;
    private ColaCompra colaQuitoCuenca;
    private ColaCompra colaQuitoLoja;

    // Estructuras globales
    private ColaAbordaje colaAbordaje;
    private PilaUsuario pilaUsuario;

    public Ventana() {
        colaQuitoGuayaquil = new ColaCompra();
        colaQuitoCuenca = new ColaCompra();
        colaQuitoLoja = new ColaCompra();

        colaAbordaje = new ColaAbordaje();
        pilaUsuario = new PilaUsuario();

        // Acción Comprar
        btnComprar.addActionListener(e -> {
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
                    textArea1.setText("Error: Formato de cédula incorrecto.\nIngrese número de cédula válido (1-10 dígitos)");
                    return;
                }

                if (cantidad <= 0 || cantidad > max_boletos_transaccion) {
                    textArea1.setText("Error: La cantidad de boletos debe estar entre 1 y 5");
                    return;
                }

                // Validar prioridad
                if (cmbPrioridad.getSelectedIndex() == 0) {
                    textArea1.setText("Error: Seleccione una prioridad válida");
                    return;
                }
                int prioridad = Integer.parseInt(cmbPrioridad.getSelectedItem().toString());

                // Obtener cola y precio según ruta
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

                // Validar asientos disponibles
                if (colaActual.totalBoletosVendidos() + cantidad > max_asientos) {
                    int disponibles = max_asientos - colaActual.totalBoletosVendidos();
                    textArea1.setText("Error: Solo quedan " + disponibles + " asientos disponibles en esta ruta");
                    return;
                }

                // Validar máximo boletos por cédula
                if (colaActual.boletosPorCedula(cedula) + cantidad > max_boletos_transaccion) {
                    int restantes = max_boletos_transaccion - colaActual.boletosPorCedula(cedula);
                    textArea1.setText("Error: La cédula " + cedula + " solo puede comprar " + restantes + " boleto(s) más en esta ruta");
                    return;
                }

                // Crear pasajero y boleto
                Pasajero pasajero = new Pasajero(nombre, cedula, prioridad);
                double precioTotal = cantidad * precioPorBoleto;
                Boleto boleto = new Boleto(ruta, pasajero, cantidad, precioTotal);

                // Guardar en estructuras
                colaActual.encolar(boleto);
                colaAbordaje.encolar(pasajero);
                pilaUsuario.push(pasajero);

                textArea1.setText("Compra exitosa\n" + boleto + "\n" + pasajero);

                setear();
                actualizarLabels();

            } catch (NumberFormatException ex) {
                textArea1.setText("Error: Ingrese un número válido en Cantidad boletos (máx. 5)");
            }
        });

        // Mostrar orden de abordaje
        btnOrdenAbordaje.addActionListener(e -> textArea1.setText(colaAbordaje.toString()));

        // Mostrar primero en abordar
        btnPrimeroEnAbordar.addActionListener(e -> {
            try {
                textArea1.setText("Primero en abordar:\n" + colaAbordaje.peek());
            } catch (Exception ex) {
                textArea1.setText(ex.getMessage());
            }
        });

        // Mostrar último registro
        btnUltimoRegistroUser.addActionListener(e -> {
            try {
                textArea1.setText("Último pasajero registrado:\n" + pilaUsuario.peek());
            } catch (Exception ex) {
                textArea1.setText(ex.getMessage());
            }
        });

        // Mostrar todos los boletos
        btnMostrarTodos.addActionListener(e -> {
            textArea1.setText(
                    "Quito - Guayaquil:\n" + colaQuitoGuayaquil.toString() +
                            "\nQuito - Cuenca:\n" + colaQuitoCuenca.toString() +
                            "\nQuito - Loja:\n" + colaQuitoLoja.toString()
            );
        });
    }

    // Método para limpiar campos
    public void setear() {
        txtCedula.setText("");
        txtNombre.setText("");
        txtCantidad.setText("");
        cmbRuta.setSelectedIndex(0);
        cmbPrioridad.setSelectedIndex(0);
    }

    // Método para actualizar labels
    public void actualizarLabels() {
        lblVendidosQG.setText("Vendidos Quito - Guayaquil: " + colaQuitoGuayaquil.totalBoletosVendidos());
        lblVendidosQC.setText("Vendidos Quito - Cuenca: " + colaQuitoCuenca.totalBoletosVendidos());
        lblVendidosQL.setText("Vendidos Quito - Loja: " + colaQuitoLoja.totalBoletosVendidos());

        lblDisponiblesQG.setText("Disponibles Quito - Guayaquil: " + (max_asientos - colaQuitoGuayaquil.totalBoletosVendidos()));
        lblDisponiblesQC.setText("Disponibles Quito - Cuenca: " + (max_asientos - colaQuitoCuenca.totalBoletosVendidos()));
        lblDisponiblesQL.setText("Disponibles Quito - Loja: " + (max_asientos - colaQuitoLoja.totalBoletosVendidos()));

        double montoTotal = colaQuitoGuayaquil.montoRecaudado()
                + colaQuitoCuenca.montoRecaudado()
                + colaQuitoLoja.montoRecaudado();
        lblMontoTotal.setText("Monto Total Recaudado: $" + montoTotal);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("UDLA RUTAS");
        frame.setContentPane(new Ventana().Ventana);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
