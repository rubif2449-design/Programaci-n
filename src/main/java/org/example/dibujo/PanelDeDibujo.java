package org.example.dibujo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class PanelDeDibujo extends JPanel implements MouseMotionListener {

    // Atributos privados
    private final ArrayList<PuntoDibujo> historialPuntos;
    private Color colorActual = Color.RED; // Color de dibujo por defecto
    private final int TAMANIO_PUNTO = 5; // Radio del punto

    public PanelDeDibujo() {
        this.historialPuntos = new ArrayList<>();
        setBackground(Color.WHITE);

        // Implementación de MouseMotionListener
        addMouseMotionListener(this);

        // Opcional: Implementar MouseListener para capturar el primer clic
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Agregar el punto inicial al hacer clic
                agregarPunto(e.getX(), e.getY());
            }
        });
    }

    /**
     * Setter público para cambiar el color de dibujo desde el exterior.
     */
    public void setColorActual(Color nuevoColor) {
        this.colorActual = nuevoColor;
    }

    /**
     * Agrega un nuevo punto al historial con el color actual y fuerza el repintado.
     */
    private void agregarPunto(int x, int y) {
        PuntoDibujo nuevoPunto = new PuntoDibujo(x, y, colorActual);
        historialPuntos.add(nuevoPunto);
        repaint(); // Vuelve a llamar a paintComponent para redibujar
    }

    // --- Métodos de MouseMotionListener ---

    @Override
    public void mouseDragged(MouseEvent e) {
        // 2. Cada vez que el ratón es arrastrado, agregamos un punto al historial
        agregarPunto(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // No se utiliza para dibujar, pero debe implementarse
    }

    // --- Método de Dibujo Personalizado ---

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Usamos Graphics2D para dibujar puntos más bonitos
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 1. Recorre toda la lista de Puntos Dibujados
        for (PuntoDibujo punto : historialPuntos) {
            // 2. Configura el color para CADA punto
            g2d.setColor(punto.getColor());

            // 3. Dibuja un círculo relleno
            g2d.fillOval(
                    punto.getX() - TAMANIO_PUNTO / 2, // Centra el punto en X
                    punto.getY() - TAMANIO_PUNTO / 2, // Centra el punto en Y
                    TAMANIO_PUNTO,
                    TAMANIO_PUNTO
            );
        }
    }
}
