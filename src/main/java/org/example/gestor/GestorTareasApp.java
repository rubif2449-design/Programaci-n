package org.example.gestor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestorTareasApp extends JFrame {

    private final JTextField campoTexto;
    private final PanelDeTareas panelTareas;

    public GestorTareasApp() {
        setTitle("Gestor de Tareas POO y Clases Internas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel Superior para añadir nuevas tareas
        JPanel panelInput = new JPanel(new BorderLayout(10, 10));
        campoTexto = new JTextField(30);
        JButton botonAgregar = new JButton("Agregar Tarea");

        panelInput.add(campoTexto, BorderLayout.CENTER);
        panelInput.add(botonAgregar, BorderLayout.EAST);
        panelInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelTareas = new PanelDeTareas();

        botonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nuevaDescripcion = campoTexto.getText().trim();
                if (!nuevaDescripcion.isEmpty()) {
                    panelTareas.agregarTarea(nuevaDescripcion);
                    campoTexto.setText("");
                    campoTexto.requestFocus();
                }
            }
        });

        add(panelInput, BorderLayout.NORTH);
        add(panelTareas, BorderLayout.CENTER);

        setSize(450, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GestorTareasApp());
    }
}
