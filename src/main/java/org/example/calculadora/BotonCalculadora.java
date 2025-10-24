package org.example.calculadora;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// 1. Clase Base Abstracta que hereda de JButton
public abstract class BotonCalculadora extends JButton {
    // Propiedades comunes
    protected Color colorBase;
    protected Color colorPresionado;
    private static final Font FUENTE_BOTON = new Font("Arial", Font.BOLD, 20);

    public BotonCalculadora(String texto, Color colorBase) {
        super(texto);
        this.colorBase = colorBase;
        this.colorPresionado = colorBase.darker(); // El color presionado es la versión oscura del color base

        // Estilo común
        setOpaque(true); // Necesario para que el color de fondo se muestre
        setBorderPainted(false);
        setFont(FUENTE_BOTON);
        setBackground(colorBase);
        setForeground(Color.WHITE);
        setFocusPainted(false);

        // Llama al método abstracto para aplicar estilos únicos
        aplicarEstiloUnico();

        // Implementación del MouseListener (comportamiento común)
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Se invoca cuando el botón es presionado
                setBackground(colorPresionado);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Se invoca cuando el botón es soltado
                setBackground(colorBase);
            }
        });
    }

    // Método abstracto: Obliga a las subclases a definir su estilo específico
    protected abstract void aplicarEstiloUnico();
}
