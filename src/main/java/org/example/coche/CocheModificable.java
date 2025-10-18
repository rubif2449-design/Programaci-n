package org.example.coche;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

/**
 * Clase Principal: Crea el objeto Coche y usa dos manejadores de eventos
 * distintos para activar el mismo método encapsulado.
 */
public class CocheModificable extends JFrame {

    private Coche miCoche;
    private JPanel panelJuego;

    public CocheModificable() {
        super("El Coche Modificable (Reutilización de Métodos)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 300);

        panelJuego = new JPanel();
        panelJuego.setLayout(null);
        panelJuego.setBackground(Color.DARK_GRAY);
        add(panelJuego);

        // 1. Crear la instancia (Objeto)
        miCoche = new Coche(10, 100, Color.YELLOW);
        panelJuego.add(miCoche);

        // 2. Configurar KeyListener (Para la ventana principal)
        setFocusable(true);
        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    // LLAMADA AL MÉTODO ENCAPSULADO (Evento de Teclado)
                    miCoche.avanzarConEfecto(panelJuego.getWidth());
                }
            }

            // Métodos no usados
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        // 3. Configurar MouseListener (Para el objeto Coche)
        miCoche.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // LLAMADA AL MISMO MÉTODO ENCAPSULADO (Evento de Mouse)
                miCoche.avanzarConEfecto(panelJuego.getWidth());
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CocheModificable());
    }
}
