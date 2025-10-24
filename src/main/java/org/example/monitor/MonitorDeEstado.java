package org.example.monitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MonitorDeEstado extends JFrame {

    private final BarraDeEstado barraJugador;

    public MonitorDeEstado() {
        setTitle("Monitor de Estado (Encapsulamiento)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // 1. Creación del Objeto principal
        barraJugador = new BarraDeEstado(100);
        barraJugador.setPreferredSize(new Dimension(350, 40));

        // Título o Etiqueta
        JLabel titulo = new JLabel("Estado del Jugador (Salud)", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));

        // Panel de Control
        JPanel panelControl = new JPanel(new GridLayout(1, 2, 10, 0));

        // 2. Creación de botones de control
        JButton botonDanio = new JButton("Ataque (-10 Salud)");
        JButton botonCurar = new JButton("Poción (+15 Salud)");

        // 3. Implementación de ActionListeners

        // Evento para recibir daño
        botonDanio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llama al método público de acceso controlado
                barraJugador.recibirDaño(10);
            }
        });

        // Evento para curar
        botonCurar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llama al método público de acceso controlado
                barraJugador.curar(15);
            }
        });

        // Configuración de la Interfaz
        panelControl.add(botonDanio);
        panelControl.add(botonCurar);

        add(titulo, BorderLayout.NORTH);
        add(barraJugador, BorderLayout.CENTER);
        add(panelControl, BorderLayout.SOUTH);

        // Ajustes finales de la ventana
        pack();
        setLocationRelativeTo(null); // Centrar la ventana
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MonitorDeEstado());
    }
}