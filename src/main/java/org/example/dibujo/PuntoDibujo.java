package org.example.dibujo;

import java.awt.Color;

public class PuntoDibujo {
    private final int x;
    private final int y;
    private final Color color;

    public PuntoDibujo(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    // Getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }
}