import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;

public class Personaje extends JLabel {
    private Color color;
    private final int tamano = 50;

    public Personaje(Color color) {
        this.color = color;

        setBackground(color);
        setOpaque(true);
        setPreferredSize(new Dimension(tamano, tamano));
        setSize(tamano, tamano);
        setText("Juego");
        setHorizontalAlignment(CENTER);
    }


    /*
        Metodo mover el personaje
     */
    public void mover(int dx, int dy) {
        // Obtener la posición actual
        int x = getX();
        int y = getY();

        // Mover el personaje a la nueva posición
        setLocation(x + dx, y + dy);
    }
}