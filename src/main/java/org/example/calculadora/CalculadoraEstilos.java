package org.example.calculadora;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraEstilos extends JFrame {

    public CalculadoraEstilos() {
        setTitle("La Fábrica de Botones con Herencia");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creamos un panel principal para los botones
        JPanel panelBotones = new JPanel(new GridLayout(4, 4, 10, 10)); // 4x4 con 10px de espacio
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Elementos de la Calculadora (Display simplificado)
        JTextField display = new JTextField("0");
        display.setFont(new Font("Arial", Font.PLAIN, 36));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);

        // 4. Creación de instancias usando Herencia (Polimorfismo implícito)
        // Se define el array de botones a crear
        String[] textos = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        for (String texto : textos) {
            BotonCalculadora boton;

            // Lógica para decidir qué objeto crear: Numerico u Operacion
            if (texto.matches("[0-9]")) {
                boton = new BotonNumerico(texto);
            } else {
                boton = new BotonOperacion(texto);
            }

            // Añadir un ActionListener simple para demostrar que funcionan
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Acción de ejemplo: mostrar el texto en la consola
                    System.out.println("Presionaste: " + boton.getText());
                    // En una calculadora real, aquí iría la lógica matemática
                }
            });

            panelBotones.add(boton);
        }

        // Diseño del JFrame
        setLayout(new BorderLayout());
        add(display, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);

        pack();
        setSize(400, 500);
        setLocationRelativeTo(null); // Centrar en la pantalla
        setVisible(true);
    }

    public static void main(String[] args) {
        // Ejecutar en el hilo de despacho de eventos de Swing (buenas prácticas)
        SwingUtilities.invokeLater(() -> new CalculadoraEstilos());
    }
}
