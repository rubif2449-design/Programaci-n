import java.awt.Color;
import java.awt.Rectangle;
import javax.swing.JLabel;

/**
 * Clase Jugador: Define los atributos y métodos del personaje principal.
 */
public class Jugador extends JLabel {

    private int puntos;
    private int vidas;

    public Jugador(int x, int y) {
        this.puntos = 0;
        this.vidas = 3;

        // Configuración visual (Hereda de JLabel)
        setText("Jugador");
        setFont(getFont().deriveFont(30f));
        setBounds(x, y, 40, 40);
        setOpaque(true);
        setBackground(Color.LIGHT_GRAY);
        setHorizontalAlignment(CENTER);
    }

    public void sumarPunto() {
        this.puntos++;
    }

    public void perderVida() {
        if (this.vidas > 0) {
            this.vidas--;
        }
    }

    public int getPuntos() {
        return puntos;
    }

    public int getVidas() {
        return vidas;
    }

    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
}
