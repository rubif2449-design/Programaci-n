package org.example.orquesta;

import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal: Configura la ventana y demuestra el Polimorfismo.
 */
public class OrquestaPolimorfica extends JFrame {

    private List<Instrumento> orquesta;
    private JTextArea consolaSalida;

    public OrquestaPolimorfica() {
        super("La Orquesta Polimorfica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        // 1. Inicializar la orquesta y agregar los objetos (Instancias)
        orquesta = new ArrayList<>();
        orquesta.add(new Tambor());
        orquesta.add((Instrumento) new Piano());
        orquesta.add((Instrumento) new Trompeta());

        // 2. Crear el botón Director
        JButton botonDirector = new JButton("El Director dice: ¡A Tocar!");

        // Asignar el comportamiento al botón
        botonDirector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tocarOrquesta();
            }
        });

        // 3. Área de texto para mostrar la salida de la consola
        consolaSalida = new JTextArea("Presiona el botón para que la orquesta toque...\n", 10, 30);
        consolaSalida.setEditable(false);
        add(new JScrollPane(consolaSalida), BorderLayout.CENTER);
        add(botonDirector, BorderLayout.NORTH);

        setVisible(true);
    }

    /**
     * Muestra el concepto de Polimorfismo.
     * Recorre la lista de la interfaz Instrumento y llama al método tocar().
     * Java sabe qué implementación específica ejecutar en tiempo de ejecución.
     */
    private void tocarOrquesta() {
        consolaSalida.append("\n--- El Director da la señal ---\n");

        // Itera sobre la lista de objetos de tipo Instrumento
        for (Instrumento instrumento : orquesta) {
            instrumento.tocar();
        }
    }

    // Sobrescribe System.out.println para redirigir la salida a JTextArea
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OrquestaPolimorfica app = new OrquestaPolimorfica();

            // Lógica para redirigir la consola al JTextArea
            System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
                private StringBuilder buffer = new StringBuilder();
                @Override
                public void write(int b) {
                    char c = (char) b;
                    buffer.append(c);
                    if (c == '\n') {
                        // Usar SwingUtilities.invokeLater para actualizar la GUI
                        SwingUtilities.invokeLater(() -> {
                            app.consolaSalida.append(buffer.toString());
                            buffer.setLength(0);
                        });
                    }
                }
            }));
        });
    }
}

