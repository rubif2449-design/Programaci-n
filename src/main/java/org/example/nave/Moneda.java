package org.example.nave;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Moneda extends JLabel {
    private final int TAMANO = 20;

    public Moneda() {
        // Configuración visual
        setText("🪙"); // Emoji de Moneda
        setFont(getFont().deriveFont(18f));
        setSize(TAMANO, TAMANO);
        setOpaque(true);
        setBackground(Color.YELLOW);
        setHorizontalAlignment(CENTER);
    }

    // Método: Reposicionar la moneda en una nueva ubicación aleatoria
    public void reubicar(int maxX, int maxY) {
        Random rand = new Random();
        int newX = rand.nextInt(maxX - TAMANO);
        int newY = rand.nextInt(maxY - TAMANO);
        setLocation(newX, newY);
        setVisible(true);
    }

    // Propiedad (para colisión)
    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
}
