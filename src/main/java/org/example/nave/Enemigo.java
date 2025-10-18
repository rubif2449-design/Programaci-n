package com.mx.curso.nave;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Enemigo extends JLabel {
    private final int TAMANO = 30;
    private Random rand = new Random();

    public Enemigo() {
        // Configuración visual
        setText("👾"); // Emoji de Enemigo
        setFont(getFont().deriveFont(25f));
        setSize(TAMANO, TAMANO);
        setOpaque(true);
        setBackground(Color.RED);
        setHorizontalAlignment(CENTER);
    }

    // Método: Comportamiento de movimiento aleatorio (llamado desde el Timer)
    public void moverAleatorio(int maxX, int maxY) {
        // Movimiento simple: caer hacia abajo y reaparecer o moverse lateralmente
        int dx = rand.nextInt(3) - 1; // -1, 0, o 1
        int dy = 5; // Siempre se mueve hacia abajo

        int newX = getX() + dx * 10;
        int newY = getY() + dy;

        // Si el enemigo sale por abajo, reaparece arriba
        if (newY > maxY) {
            newY = 0 - TAMANO;
            newX = rand.nextInt(maxX - TAMANO);
        }

        // Mantener dentro de los límites laterales
        newX = Math.max(0, Math.min(newX, maxX - TAMANO));

        setLocation(newX, newY);
    }

    // Propiedad (para colisión)
    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
}
