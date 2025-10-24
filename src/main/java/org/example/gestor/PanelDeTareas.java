package org.example.gestor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelDeTareas extends JPanel {

    private final ArrayList<Tarea> listaTareas;
    private final JPanel listaVisualPanel;

    public PanelDeTareas() {
        this.listaTareas = new ArrayList<>();

        setLayout(new BorderLayout());

        listaVisualPanel = new JPanel();
        listaVisualPanel.setLayout(new BoxLayout(listaVisualPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(listaVisualPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Agrega una nueva tarea al modelo y actualiza la interfaz.
     */
    public void agregarTarea(String descripcion) {
        Tarea nuevaTarea = new Tarea(descripcion);
        listaTareas.add(nuevaTarea);
        actualizarUI();
    }

    /**
     * Elimina una tarea tanto del modelo como de la interfaz.
     */
    public void eliminarTarea(Tarea tarea) {
        listaTareas.remove(tarea);
        actualizarUI();
    }

    /**
     * Reconstruye la interfaz visual para reflejar el estado actual de la listaTareas.
     */
    private void actualizarUI() {
        // Limpia el panel visual antes de reconstruirlo
        listaVisualPanel.removeAll();

        for (Tarea tarea : listaTareas) {
            // Crea un componente visual (JPanel) para representar la tarea
            JPanel itemPanel = crearItemTarea(tarea);
            listaVisualPanel.add(itemPanel);
        }

        // Refresca la interfaz
        listaVisualPanel.revalidate();
        listaVisualPanel.repaint();
    }

    /**
     * Crea el JPanel interactivo para una tarea individual.
     */
    private JPanel crearItemTarea(Tarea tarea) {
        JPanel panel = new JPanel(new BorderLayout(10, 0));

        // Checkbox y descripción
        JCheckBox checkBox = new JCheckBox(tarea.getDescripcion());
        checkBox.setSelected(tarea.isCompletada());

        // Listener para marcar como completada
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tarea.setCompletada(checkBox.isSelected());
            }
        });

        // Botón Eliminar
        JButton botonEliminar = new JButton("X");
        botonEliminar.setForeground(Color.RED);
        botonEliminar.setPreferredSize(new Dimension(50, 25));

        // ------------------------------------------------------------------
        // Manejo de Eventos con CLASE INTERNA ANÓNIMA para eliminar
        // ------------------------------------------------------------------
        botonEliminar.addActionListener(new ActionListener() {
            // La clase anónima tiene acceso a la instancia 'tarea' del contexto exterior
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llama al método del contenedor (PanelDeTareas) para remover la tarea
                eliminarTarea(tarea);
            }
        });
        // ------------------------------------------------------------------

        panel.add(checkBox, BorderLayout.CENTER);
        panel.add(botonEliminar, BorderLayout.EAST);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Margen

        return panel;
    }
}