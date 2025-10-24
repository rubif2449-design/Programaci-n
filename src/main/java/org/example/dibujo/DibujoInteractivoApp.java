package org.example.dibujo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DibujoInteractivoApp extends JFrame {

    private final PanelDeDibujo panelDibujo;
    private final Color[] colores = {Color.RED, Color.BLUE, Color.GREEN, Color.BLACK};
    private int indiceColor = 0;

    public DibujoInteractivoApp() {
        setTitle("Dibujo Interactivo POO y Eventos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 1. Crear la instancia del Panel de Dibujo
        panelDibujo = new PanelDeDibujo();
        panelDibujo.setPreferredSize(new Dimension(600, 400));

        // 2. Crear el botón de control
        JButton botonCambiarColor = new JButton("Cambiar Color (Actual: Rojo)");
        botonCambiarColor.setBackground(colores[indiceColor]);
        botonCambiarColor.setForeground(Color.WHITE);

        // 3. Implementar el ActionListener para el cambio de color
        botonCambiarColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Avanza al siguiente color en la lista
                indiceColor = (indiceColor + 1) % colores.length;
                Color nuevoColor = colores[indiceColor];
                String nombreColor = getNombreColor(nuevoColor);

                // Llama al setter del objeto PanelDeDibujo (Encapsulamiento)
                panelDibujo.setColorActual(nuevoColor);

                // Actualiza la UI del botón
                botonCambiarColor.setBackground(nuevoColor);
                botonCambiarColor.setText("Cambiar Color (Actual: " + nombreColor + ")");
            }
        });

        // Ensamblaje de la Ventana
        JPanel panelControl = new JPanel();
        panelControl.add(botonCambiarColor);
        panelControl.setBackground(Color.LIGHT_GRAY);

        add(panelDibujo, BorderLayout.CENTER);
        add(panelControl, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Función auxiliar simple para mostrar el nombre del color
    private String getNombreColor(Color c) {
        if (c.equals(Color.RED)) return "Rojo";
        if (c.equals(Color.BLUE)) return "Azul";
        if (c.equals(Color.GREEN)) return "Verde";
        if (c.equals(Color.BLACK)) return "Negro";
        return "Desconocido";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DibujoInteractivoApp());
    }
}
