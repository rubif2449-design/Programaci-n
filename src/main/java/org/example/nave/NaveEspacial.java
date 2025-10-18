package org.example.nave;

import javax.swing.*;
import java.awt.*;

public class NaveEspacial extends JLabel {
    // Atributos privados (Encapsulación)
    private int puntos;
    private int vidas;

    // Constantes visuales
    private final int TAMANO = 40;

    public NaveEspacial(int x, int y) {
        this.puntos = 0;
        this.vidas = 3;

        // Configuración visual
        setText("🚀"); // Emoji de Nave
        setFont(getFont().deriveFont(30f));
        setBounds(x, y, TAMANO, TAMANO);
        setOpaque(true);
        setBackground(Color.BLUE);
        setHorizontalAlignment(CENTER);
    }

    // Métodos (Comportamiento)
    public void sumarPunto() {
        this.puntos++;
    }

    public void perderVida() {
        if (this.vidas > 0) {
            this.vidas--;
        }
    }

    // Método simulado: Mover (implementado en el KeyListener principal)
    public void mover(int dx, int dy) {
        setLocation(getX() + dx, getY() + dy);
    }

    // Getters para acceder a los atributos
    public int getPuntos() { return puntos; }
    public int getVidas() { return vidas; }

    // Propiedad (para colisión)
    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
}